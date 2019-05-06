package com.cau.goodbody;

public class User {
    private String name;
    private String email;
    private String sex;
//    private float weight;

    public User(){

    }

//    public User(String name,String email,String sex,float weight) {
//        this.name = name;
//        this.email = email;
//        this.sex = sex;
//        this.weight = weight;
//    }
    public User(String name,String email,String sex) {
        this.name = name;
        this.email = email;
        this.sex = sex;
    }


    public String getEmail() {
        return email;
    }

    public String getName(){
        return name;
    }

    public String getSex(){
        return sex;
    }

//    public float getWeight(){
//        return weight;
//    }
}


