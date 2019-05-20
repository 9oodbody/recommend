package com.cau.goodbody;

public class MealDifference {
    double dif_carbohydrate;
    double dif_lipid;
    double dif_protein;
    double sum_dif=0;

    public MealDifference(){

    }

    public MealDifference(double dif_carbohydrate,double dif_protein, double dif_lipid) {
        this.dif_carbohydrate = dif_carbohydrate;
        this.dif_protein = dif_protein;
        this.dif_lipid = dif_lipid;
    }
}
