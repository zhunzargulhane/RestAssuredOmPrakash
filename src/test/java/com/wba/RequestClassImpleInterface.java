package com.wba;

public class RequestClassImpleInterface implements MyInterface {
    MyInterface MyInterface = null;
    public MyInterface printMe() {
        System.out.println("I am class 1 method of interface");
        return MyInterface;
    }
}
