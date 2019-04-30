package com.cau.goodbody;

import java.util.Locale;

public class Workout {
    String category;
    String equip;
    String howto;
    String level;
    String name;
    int num_per_set;
    String part;
    int time;

    public Workout(){}

    public Workout(String category,String equip,String howto,String level,String name,int num_per_set,String part,int time){
        this.category = category;
        this.equip = equip;
        this.howto = howto;
        this.level = level;
        this.name = name;
        this.num_per_set = num_per_set;
        this.part = part;
        this.time = time;
    }
}
