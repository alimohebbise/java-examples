package com.example.javaexample.package2;

public class Student3 {
    private String name3;
    private  int grade3;

    public Student3(String name3, int grade3) {
        this.name3 = name3;
        this.grade3 = grade3;
    }

    public String getName3() {
        return name3;
    }

    public int getGradle3() {
        return grade3;
    }

    public void setName3(String name3) {
        this.name3 = name3;
    }

    public void setGrade3(int grade3) {
        this.grade3 = grade3;
    }

    public static void main(String[] args) {
        Student3 student3 = new Student3("Sara", 20);
        student3.setName3("Ali");
        student3.setGrade3(18);
        System.out.println(student3.getName3());
        System.out.println(student3.getGradle3());
    }
}
