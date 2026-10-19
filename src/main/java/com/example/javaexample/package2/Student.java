package com.example.javaexample.package2;

public class Student {
    public String name;
    public int grade;               // نمره

    public static void main(String[] args) {
        Student student = new Student();

        student.name = "Ali";
        student.grade = 18;

        System.out.println(student.name);
        System.out.println(student.grade);
    }
}
