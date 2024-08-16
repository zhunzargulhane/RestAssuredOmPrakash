package com.wba;

public class MethodChaining {
    public static void main(String[] args) {
        a1().a2().a3();
    }


    public static MethodChaining a1(){
        System.out.println("i am a1");
        return new MethodChaining();
    }

    public MethodChaining a2(){
        System.out.println("i am a2");
        return this;
    }

    public MethodChaining a3(){
        System.out.println("i am a3");
        return this;
    }
}
