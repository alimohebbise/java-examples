package com.example.javaexample.inheritance0;

public class Student01 extends Person1 {

    public Student01(String name) {
        super(name);
    }

    public static void main(String[] args) {
        Student01 student = new Student01("Ali");
        System.out.println(student.name);
    }
}
