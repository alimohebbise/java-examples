<#
.SYNOPSIS
Installs the latest Eclipse Temurin JDK locally for this project.

.DESCRIPTION
Downloads the latest GA JDK for Windows x64 into .jdks and activates it in
the current PowerShell process only. Dot-source this script to keep JAVA_HOME
and PATH changes in the calling shell.

.EXAMPLE
. .\setup-java.ps1
#>

$ErrorActionPreference = 'Stop'

if (-not $PSScriptRoot) {
    throw 'Save this script to a file before running it.'
}

$projectRoot = $PSScriptRoot
$jdkRoot = Join-Path $projectRoot '.jdks'
$releaseInfoUrl = 'https://api.adoptium.net/v3/info/available_releases'
$releaseInfo = Invoke-RestMethod -Uri $releaseInfoUrl -TimeoutSec 60
$featureRelease = $releaseInfo.most_recent_feature_release

if (-not $featureRelease) {
    throw 'Adoptium did not report the latest GA feature release.'
}

$assetUrl = "https://api.adoptium.net/v3/assets/latest/$featureRelease/hotspot?architecture=x64&image_type=jdk&os=windows&page_size=1"
$assets = Invoke-RestMethod -Uri $assetUrl -TimeoutSec 60
$asset = $assets | Select-Object -First 1

if (-not $asset.binary.package.link -or -not $asset.binary.package.checksum -or -not $asset.version.semver) {
    throw "Adoptium did not return a Windows x64 JDK package for feature release $featureRelease."
}

$installPath = Join-Path $jdkRoot "temurin-$($asset.version.semver)"
$javaPath = Join-Path $installPath 'bin\java.exe'

if (-not (Test-Path -LiteralPath $javaPath -PathType Leaf)) {
    if (Test-Path -LiteralPath $installPath) {
        throw "The JDK directory exists but is incomplete: $installPath. Remove that directory and rerun this script."
    }

    New-Item -ItemType Directory -Path $jdkRoot -Force | Out-Null
    $temporaryPath = Join-Path $jdkRoot ([guid]::NewGuid().ToString('N'))
    $archivePath = Join-Path $temporaryPath $asset.binary.package.name
    $extractPath = Join-Path $temporaryPath 'extracted'

    New-Item -ItemType Directory -Path $temporaryPath | Out-Null
    try {
        Invoke-WebRequest -Uri $asset.binary.package.link -OutFile $archivePath -TimeoutSec 600

        $actualChecksum = (Get-FileHash -LiteralPath $archivePath -Algorithm SHA256).Hash.ToLowerInvariant()
        $expectedChecksum = $asset.binary.package.checksum.ToLowerInvariant()
        if ($actualChecksum -ne $expectedChecksum) {
            throw "The downloaded JDK checksum did not match Adoptium's published SHA-256 checksum."
        }

        Expand-Archive -LiteralPath $archivePath -DestinationPath $extractPath
        $javaExecutables = @(Get-ChildItem -LiteralPath $extractPath -Filter 'java.exe' -File -Recurse |
            Where-Object { $_.Directory.Name -eq 'bin' })

        if ($javaExecutables.Count -eq 0) {
            throw 'The downloaded archive did not contain a JDK bin\java.exe.'
        }

        $extractedJdk = $javaExecutables[0].Directory.Parent.FullName
        Move-Item -LiteralPath $extractedJdk -Destination $installPath
    }
    finally {
        Remove-Item -LiteralPath $temporaryPath -Recurse -Force
    }
}

$javaBin = Join-Path $installPath 'bin'
$projectJdkPrefix = $jdkRoot.TrimEnd('\') + '\'
$otherPathEntries = @($env:Path -split ';' | Where-Object {
    $_ -and -not $_.StartsWith($projectJdkPrefix, [StringComparison]::OrdinalIgnoreCase)
})

$env:JAVA_HOME = $installPath
$env:Path = (@($javaBin) + $otherPathEntries) -join ';'

Write-Host "Activated Eclipse Temurin JDK $($asset.version.semver) for this PowerShell session."
Write-Host "JAVA_HOME=$env:JAVA_HOME"
& (Join-Path $javaBin 'java.exe') -version
