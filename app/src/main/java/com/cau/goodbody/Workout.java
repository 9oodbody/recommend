package com.cau.goodbody;

public class Workout {
    String level;
    String howto;
    int number;
    String equip;
    int time;

    public Workout(){}

    public Workout(String level,String howto,int number,String equip,int time){
        this.level=level;
        this.howto=howto;
        this.number=number;
        this.equip=equip;
        this.time=time;
    }
}
