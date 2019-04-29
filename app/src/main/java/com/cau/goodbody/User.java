package com.cau.goodbody;

public class User {
    private String email;

    public User(){

    }

    public User(String email) {
        this.email = email;
    }

    public String getUserEmail(){
        return email;
    }
}
