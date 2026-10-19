package com.example.javaexample.package2;
// Your exercise: Add a getGrade() method to the class to return the grade; then,
// instead of student1.name and student1.grade,
// use these two methods to print the information.
public class Student2 {
    private final String name1;
    private final int grade1;

    public Student2(String name1, int grade1) {
        this.name1 = name1;
        this.grade1 = grade1;
    }

    public String getName1() {
        return name1;
    }

    public int getGrade1() {
        return grade1;
    }

    public static void main(String[] args) {
        Student2 student2 = new Student2("Sara", 20);
    // به جای این دو آبجکت، باید گت نیم و گت گریدل استفاده کنیم
        System.out.println(student2.getName1());
        System.out.println(student2.getGrade1());
    }
}
