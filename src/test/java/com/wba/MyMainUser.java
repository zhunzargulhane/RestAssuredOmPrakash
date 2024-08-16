package com.wba;

public class MyMainUser {
    public static void main(String[] args) {
        User user = new User.UserBuilder().setName("zhunzar").build();
        System.out.println(user.getName());
        System.out.println(user.getSalary());
    }
}
