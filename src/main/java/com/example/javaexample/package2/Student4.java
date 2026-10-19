package com.example.javaexample.package2;

public class Student4 {

    private String name;

    public Student4(String name) {
        this.name = name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public static void main(String[] args) {

        Student4 student = new Student4("Sara");

        System.out.println(student.getName());

        student.setName("Ali");

        System.out.println(student.getName());
    }
}
