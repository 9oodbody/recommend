package com.cau.goodbody;

public class User {
    private String name;
    private String email;
    private String sex;
    private int height;
    private int birth;
    private String goal;

    public User(){

    }

    public User(String name,String email,String sex,int height,int birth,String goal) {
        this.name = name;
        this.email = email;
        this.sex = sex;
        this.height = height;
        this.birth = birth;
        this.goal = goal;
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

    public int getHeight(){
        return height;
    }

    public int getBirth(){
        return birth;
    }

    public String getGoal(){
        return goal;
    }
}


