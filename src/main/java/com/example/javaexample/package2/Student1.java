package com.example.javaexample.package2;

public class Student1 {


    private final String name;
    private final int grade;

    public Student1(String name, int grade) {
        this.name = name;                               // یعنی هرجا کلاس اشاره به نیم شد ، منظورش همین نیمه
        this.grade = grade;
    }

    public static void main(String[] args) {
        Student1 student1 = new Student1("Sara", 20);

        System.out.println(student1.name);
        System.out.println(student1.grade);
    }
}
