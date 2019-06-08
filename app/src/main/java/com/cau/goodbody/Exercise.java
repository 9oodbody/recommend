package com.cau.goodbody;

public class Exercise {
    private String category;
    private String equip;
    private String howto;
    private String image;
    private String level;
    private String name;
    private int num_per_set;
    private String part;
    private int time;

    public Exercise(){

    }

    public Exercise(String category, String equip, String howto, String image,String level,String name, int num_per_set,String part,int time){
        this.category=category;
        this.equip = equip;
        this.howto = howto;
        this.image = image;
        this.level=level;
        this.name=name;
        this.num_per_set=num_per_set;
        this.part=part;
        this.time=time;
    }

    public String getCategory(){return category;}
    public String getEquip() { return equip;    }
    public int getNum_per_set() {        return num_per_set;    }
    public int getTime() {        return time;    }
    public String getHowto() {        return howto;    }
    public String getImage() {        return image;    }
    public String getLevel() {        return level;    }
    public String getName() {        return name;    }
    public String getPart() {        return part;    }
}