package com.example.javaexample.package2;
// آموزش تفاوت static و Method معمولی
public class Student6 {

    String name;

    public Student6(String name) {
        this.name = name;                       // یه نام تو سازنده ساخته
    }

    public void introduce() {                   // معرفی کردن
        System.out.println("My name is " + name);  // صدا زدن نام خط 5
    }

    public static void showMessage() {                      // تعریف کردن یک متد دلخواه
        System.out.println("Welcome!");
    }

    public static void main(String[] args) {
        Student6 student6 = new Student6("Ali");    // ساخت یک آبجکت از کلاس

        student6.introduce();                  //  صدا زدن متدی که از متغیر محلی استفاده کرده - این با نام آبجکتی که از کلاس ساخته شده به علاوه متد صدا زده شده
        Student6.showMessage();                // صدا زدن متدی که یک چیز خودش چاپ کرده - این با نام کلاس صدا زده شده
    //  Student6.instroduce();
    }
}
