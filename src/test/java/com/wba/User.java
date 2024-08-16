package com.wba;

public class User {
    private int salary;
    private String name;
    User(UserBuilder userBuilder){
        this.salary = userBuilder.salary;
        this.name = userBuilder.name;
    }

    public int getSalary() {
        return salary;
    }

    public String getName() {
        return name;
    }

    public static class UserBuilder {
        private String name;
        private int salary;

        UserBuilder() {

        }


        public UserBuilder setSalary(int salary) {
            this.salary = salary;
            return this;
        }

        public UserBuilder setName(String name) {
            this.name = name;
            return this;
        }

        public User build() {
            User user = new User(this);
            return user;
        }


    }
}
