package com.wba;

public class ResponseClassImpleInterface implements MyInterface {
    MyInterface MyInterface = null;

    public MyInterface printMe() {
        System.out.println("I am class 2 method of interface");
        return MyInterface;
    }
}
