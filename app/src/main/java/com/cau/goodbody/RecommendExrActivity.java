package com.cau.goodbody;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ValueEventListener;

public class RecommendExrActivity extends AppCompatActivity {

    private TextView tv_recommend;
    private ImageButton ib_exr1, ib_exr2;
    private FirebaseUser c_user;
    private FirebaseDatabase database;
    private DatabaseReference mDatabase;
    String goal,caseType;
    double min_fm,max_fm,max_smm,min_smm,BMI;
    double minBMI = 18, maxBMI=24;
    float weight, muscle_mass,fat_mass,skeletal_muscle_mass;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recommendexr);

        tv_recommend = findViewById(R.id.tv_recommend);
        ib_exr1 = findViewById(R.id.ib_exr1);
        ib_exr2 = findViewById(R.id.ib_exr2);
        c_user = FirebaseAuth.getInstance().getCurrentUser();
        database = FirebaseDatabase.getInstance();

        mDatabase = database.getReference("users").child(c_user.getUid());
        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                User userdata = dataSnapshot.getValue(User.class);
                goal = userdata.getGoal();
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        //개인 정보 받아오기(몸무게,골격근량,체지방량)
        mDatabase = database.getReference("users").child(c_user.getUid()).child("Inbody_result");
        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                InbodyResult result = dataSnapshot.getValue(InbodyResult.class);
                muscle_mass = result.getMuscle_mass();
                fat_mass = result.getFat_mass();
                skeletal_muscle_mass = result.getSkeletal_muscle_mass();
                min_fm = result.getMin_fm();
                max_fm = result.getMax_fm();
                max_smm = result.getMax_smm();
                min_smm = result.getMin_smm();
                BMI = result.getBMI();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        //운동추천
        mDatabase = database.getReference("운동");

        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(goal.equals("다이어트")){
                    if(BMI>maxBMI) {//과체중
                        if (skeletal_muscle_mass < max_smm) {//표준이하,표준골격근량
                            if (fat_mass > min_fm) {//표준이상체지방량
                                caseType = "유산소운동";
                                tv_recommend.setText(caseType+"을 추천드립니다");
                            }
                        }
                    } else if(BMI<minBMI) {//저체중
                        if (skeletal_muscle_mass < min_smm) {//표준이하골격근량
                            if (fat_mass < min_fm) {//표준이하체지방량
                                caseType = "기초체력증진";
                                tv_recommend.setText(caseType+"을 추천드립니다");
                            }
                        }
                    }else{
                        tv_recommend.setText("추천운동 list");
                        ib_exr1.setImageResource(R.drawable.leg1);
                        ib_exr2.setImageResource(R.drawable.che1);

                        ImageButton button1;
                        button1 = (ImageButton) findViewById(R.id.ib_exr1);
                        button1.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent intent_leg01 = new Intent(RecommendExrActivity.this, Leg1listActivity.class);
                                startActivity(intent_leg01);
                            }
                        });
                    }
                }
                else if(goal.equals("체중유지")){
                    if(BMI<minBMI) {//저체중
                        if (skeletal_muscle_mass < min_smm) {//표준이하골격근량
                            if (fat_mass < min_fm) {//표준이하체지방량
                                caseType = "체력증진";
                                tv_recommend.setText(caseType+"을 추천드립니다");
                            }
                        }
                    }else{
                        tv_recommend.setText("추천운동 list");
                        ib_exr1.setImageResource(R.drawable.arm1);
                        ib_exr2.setImageResource(R.drawable.che1);

                        ImageButton button1;
                        button1 = (ImageButton) findViewById(R.id.ib_exr1);
                        button1.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent intent_arm01 = new Intent(RecommendExrActivity.this, Arm1listActivity.class);
                               startActivity(intent_arm01);
                            }
                        });
                        ImageButton button2;
                        button2 = (ImageButton) findViewById(R.id.ib_exr2);
                        button2.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent intent_che01 = new Intent(RecommendExrActivity.this, Che1listActivity.class);
                                startActivity(intent_che01);
                            }
                        });

                    }
                }
                else{//벌크업
                    if(BMI>maxBMI) {//과체중
                        if (skeletal_muscle_mass < max_smm) {//표준이하,표준골격근량
                            if (fat_mass > min_fm) {//표준이상체지방량
                                caseType = "유산소운동";
                                tv_recommend.setText(caseType+"을 추천드립니다");
                            }
                        }
                    }
                    else{
                        if(BMI<minBMI) {//저체중
                            if (skeletal_muscle_mass < min_smm) {//표준이하골격근량
                                if (fat_mass < min_fm) {//표준이하체지방량
                                    caseType = "체력증진";
                                    tv_recommend.setText(caseType+"을 추천드립니다");
                                }
                            }
                        }else {
                            caseType = "근력강화";
                            tv_recommend.setText("추천운동 list");
                            ib_exr1.setImageResource(R.drawable.che2);
                            ib_exr2.setImageResource(R.drawable.leg2);

                            ImageButton button1;
                            button1 = (ImageButton) findViewById(R.id.ib_exr1);
                            button1.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    Intent intent_che01 = new Intent(RecommendExrActivity.this, Che1listActivity.class);
                                    startActivity(intent_che01);
                                }
                            });
                            ImageButton button2;
                            button2 = (ImageButton) findViewById(R.id.ib_exr2);
                            button2.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    Intent intent_leg02 = new Intent(RecommendExrActivity.this, Leg2listActivity.class);
                                    startActivity(intent_leg02);
                                }
                            });
                        }
                    }
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
}

