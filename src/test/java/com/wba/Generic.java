package com.wba;

public class Generic {
    public static void main(String[] args) {
        MyInterface myInterface = new ResponseClassImpleInterface();
        myInterface.printMe();
        MyInterface myInterface1 = new RequestClassImpleInterface();
        myInterface1.printMe();
    }
}
