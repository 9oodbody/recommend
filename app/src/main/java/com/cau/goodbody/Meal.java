package com.cau.goodbody;

public class Meal {
    private int carbohydrate;
    private String composition;
    private int kcal;
    private int lipid;
    private int protein;
    private String image;

    public Meal(){

    }

    public Meal(int carbohydrate,String composition,int kcal,int lipid,int protein,String image) {
        this.carbohydrate = carbohydrate;
        this.composition = composition;
        this.kcal = kcal;
        this.lipid = lipid;
        this.protein = protein;
        this.image = image;
    }

    public String getComposition(){return composition;}
    public int getCarbohydrate(){return carbohydrate;}
    public int getLipid(){return lipid;}
    public int getProtein(){return protein;}
    public int getKcal(){return kcal;}
    public String getImage(){return image;}
}
