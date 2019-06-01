package com.cau.goodbody;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MealRecommendation extends AppCompatActivity {

    private static final String TAG = "Meal Recommendation";

    private Toolbar sToolbar;
    private DatabaseReference mDatabase;

//    int height=156, weight=46, american_age=22;
//    String sex="여자";
//    String goal="다이어트";
//    double BMR;

    int height,weight,american_age;
    String sex,goal;
    double BMR;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meal_recommendation);

        sToolbar = findViewById(R.id.toolbar);
        setSupportActionBar(sToolbar);
        getSupportActionBar().setTitle("식단 추천");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference mDatabase = database.getReference("식단");


        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                //성별에 따른 기초대사량 계산(체중,키,나이 필요)
                if(sex.equals("남자")){
                    BMR = 88.362+(13.397*weight)+(4.799*height)-(5.677*american_age);
                }else{
                    BMR = 447.593+(9.247*weight)+(3.098*height)-(4.330*american_age);
                }
                System.out.println("기초대사량 BMR: "+BMR);
                //기초대사량으로 한 끼 권장섭취량 계산
                double recommended_intake = BMR * 1.55 / 3;
                System.out.println("한 끼 권장섭취량: "+recommended_intake);
                //순서대로 한 끼 권장 탄수화물, 단백질, 지방 변수 선언
                double rec_c,rec_p,rec_l;

                //목표에 맞는 한 끼 권장 탄,단,지 비율 계산
                if(goal.equals("다이어트")){
                    rec_c = recommended_intake * 0.3/4;//탄수화물 1g=4kcal
                    rec_p = recommended_intake * 0.4/4;//단백질 1g=4kcal
                    rec_l = recommended_intake * 0.3/9;//지방 1g=9kcal
                    System.out.println("권장 탄수화물: "+ rec_c + " 권장 단백질: "+ rec_p + " 권장 지방: "+ rec_l);
                }else if(goal.equals("체중유지")){
                    rec_c = recommended_intake * 0.5/4;
                    rec_p = recommended_intake * 0.3/4;
                    rec_l = recommended_intake * 0.2/9;
                    System.out.println("권장 탄수화물: "+ rec_c + " 권장 단백질: "+ rec_p + " 권장 지방: "+ rec_l);
                }else{
                    rec_c = recommended_intake * 0.4/4;
                    rec_p = recommended_intake * 0.4/4;
                    rec_l = recommended_intake * 0.2/9;
                    System.out.println("권장 탄수화물: "+ rec_c + " 권장 단백질: "+ rec_p + " 권장 지방: "+ rec_l);
                }

                int numMeal=0;
                Meal[] meals = new Meal[71];
                MealDifference[] meals_d = new MealDifference[71];

                for(DataSnapshot fileSnapshot:dataSnapshot.getChildren()){
                    //반복문으로 식단 DB 받아오기
                    meals[numMeal] =  fileSnapshot.getValue(Meal.class);

                    //각 식단의 탄,단,지와 한 끼 권장 탄,단,지의 절댓값 계산
                    double abs_c = Math.abs(meals[numMeal].getCarbohydrate()-rec_c);
                    double abs_p = Math.abs(meals[numMeal].getProtein()-rec_p);
                    double abs_l = Math.abs(meals[numMeal].getLipid()-rec_l);
                    meals_d[numMeal] = new MealDifference(abs_c,abs_p,abs_l);

                    //탄,단,지 차이 값의 총합 계산
                    meals_d[numMeal].sum_dif = abs_c + abs_p + abs_l;
                    Log.i(TAG,"식단"+numMeal+" 탄수화물 차: "+ meals_d[numMeal].dif_carbohydrate+" 단백질 차: "+meals_d[numMeal].dif_protein+" 지방 차:"+meals_d[numMeal].dif_lipid+"  ====> 차이 합: "+meals_d[numMeal].sum_dif);
                    numMeal++;
                }
                double min = meals_d[0].sum_dif;
                int min_index = 0;
                for(int i=1;i<numMeal;i++){
                    //차이 값의 총합 중에 최솟값 구하기
                    if(meals_d[i].sum_dif < min){
                        min = meals_d[i].sum_dif;
                        min_index = i;
                    }
                }
                System.out.println("추천 식단: "+meals[min_index].getComposition()+" 최소 차이 합: "+meals_d[min_index].sum_dif);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home: { //toolbar의 back키 눌렀을 때 동작
                finish();
                return true;
            }
        }
        return super.onOptionsItemSelected(item);
    }
}