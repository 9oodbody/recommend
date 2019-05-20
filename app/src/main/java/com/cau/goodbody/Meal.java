package com.cau.goodbody;

public class Meal {
    private int carbohydrate;
    private String composition;
    private int kcal;
    private int lipid;
    private int protein;

    public Meal(){

    }

    public Meal(int carbohydrate,String composition,int kcal,int lipid,int protein) {
        this.carbohydrate = carbohydrate;
        this.composition = composition;
        this.kcal = kcal;
        this.lipid = lipid;
        this.protein = protein;
    }

    public String getComposition(){return composition;}
    public int getCarbohydrate(){return carbohydrate;}
    public int getLipid(){return lipid;}
    public int getProtein(){return protein;}
    public int getKcal(){return kcal;}
}
