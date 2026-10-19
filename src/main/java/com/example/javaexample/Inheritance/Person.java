package com.example.javaexample.Inheritance;
// وراثت
class Person {
    String name;

    public void introduce() {
        System.out.println("My name is " + name);
    }
}

class Student extends Person {            // این کلاس نباید public باشد؛ زیرا باید یک فایل دیگر تشکیل بدهیم
    public static void main(String[] args) {
        Student student = new Student();
        student.name = "Ali";
        student.introduce();
    }
}