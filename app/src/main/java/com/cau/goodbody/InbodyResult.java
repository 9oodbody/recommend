package com.cau.goodbody;

public class InbodyResult {
    private float body_water,fat_free_mass,fat_mass,mineral,muscle_mass,protein,skeletal_muscle_mass,weight;
    private double BMI,max_fm,max_smm,min_fm,min_smm;
    int height;

    public InbodyResult(){

    }

    public InbodyResult(double BMI, float body_water, float fat_free_mass, float fat_mass, int height, double max_fm, double max_smm, double min_fm, double min_smm, float mineral, float muscle_mass, float protein, float skeletal_muscle_mass, float weight){
        this.BMI = BMI;
        this.body_water = body_water;
        this.fat_free_mass = fat_free_mass;
        this.fat_mass = fat_mass;
        this.height = height;
        this.max_fm = max_fm;
        this.max_smm = max_smm;
        this.min_fm = min_fm;
        this.min_smm = min_smm;
        this.mineral = mineral;
        this.muscle_mass = muscle_mass;
        this.protein = protein;
        this.skeletal_muscle_mass = skeletal_muscle_mass;
        this.weight = weight;
    }

    public double getBMI(){return BMI;}
    public float getBody_water(){return body_water;}
    public float getFat_free_mass(){return fat_free_mass;}
    public float getFat_mass(){return fat_mass;}
    public double getMax_fm(){return max_fm;}
    public double getMax_smm(){return max_smm;}
    public double getMin_fm(){return min_fm;}
    public double getMin_smm(){return min_smm;}
    public float getMineral(){return mineral;}
    public float getMuscle_mass(){return muscle_mass;}
    public float getProtein(){return protein;}
    public float getSkeletal_muscle_mass(){return skeletal_muscle_mass;}
    public float getWeight(){return weight;}
    public int getHeight(){return height;}
}
