package com.example.javaexample.package1;

class User {
    String name;
    int age;
}

public class Main {
    public static void main(String[] args) {

        User user1 = new User();

        user1.name = "Ali";
        user1.age = 25;

        System.out.println(user1.name);
        System.out.println(user1.age);
    }
}