package com.cau.goodbody;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ResultTextRecordActivity extends AppCompatActivity {

    private Toolbar sToolbar;
    private TextView printBW, printPT, printMR, printFM, printsdFM, printMM, printFC, printW, printSMM, printsdSMM;
    private FirebaseDatabase database;
    private DatabaseReference myRef,myRef2;
    private FirebaseUser c_user;
    float data_w, data_bw, data_pt, data_mr, data_fm, data_mm, data_fc, data_smm;
    private double  data_sd_smm_l, data_sd_smm_h;
    public static double data_sd_fm_l, data_sd_fm_h;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result_text_record);

        sToolbar = findViewById(R.id.toolbar);
        setSupportActionBar(sToolbar);
        getSupportActionBar().setTitle("결과지 분석");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        c_user = FirebaseAuth.getInstance().getCurrentUser();

        Bundle bundle = getIntent().getExtras();
        //xml에서 값 받아오기
        //체수분
        data_bw = bundle.getFloat("body_water_et");
        printBW = findViewById(R.id.print_bw);
        printBW.setText(String.valueOf(data_bw));
        //단백질
        data_pt = bundle.getFloat("protein_et");
        printPT = findViewById(R.id.print_pt);
        printPT.setText(String.valueOf(data_pt));
        //무기질
        data_mr = bundle.getFloat("mineral_et");
        printMR = findViewById(R.id.print_mr);
        printMR.setText(String.valueOf(data_mr));
        //체지방
        data_fm = bundle.getFloat("fat_mass_et");
        printFM = findViewById(R.id.print_fm);
        printFM.setText(String.valueOf(data_fm));

        //근육량 계산
        printMM = findViewById(R.id.print_mm);
        data_mm = data_bw+data_pt;
        printMM.setText(String.valueOf(data_mm));

        //제지방량 계산
        printFC = findViewById(R.id.print_fc);
        data_fc = data_mm + data_mr;
        printFC.setText(String.valueOf(data_fc));

        //체중 계산
        printW = findViewById(R.id.print_w);
        data_w = data_fc + data_fm;
        data_w = Float.parseFloat(String.format("%.2f",data_w));
        printW.setText(String.valueOf(data_w));

        //골격근량 계산
        printSMM = findViewById(R.id.print_smm);
        data_smm = data_mm * 0.57f;
        printSMM.setText(String.valueOf(data_smm));

        //골격근량 표준 계산
        float data_sd_smm = data_w * 0.45f;
        //골격근량 표준 최저 계산
        data_sd_smm_l = data_sd_smm * 0.9;
        data_sd_smm_l = Double.parseDouble(String.format("%.1f",data_sd_smm_l));
        //골격근량 표준 최고 계산
        data_sd_smm_h = data_sd_smm * 1.1;
        data_sd_smm_h = Double.parseDouble(String.format("%.1f",data_sd_smm_h));
        //골격근량 표준 범위 출력
        String data_sd_smm_toS = Double.toString(data_sd_smm_l) +"~"+ Double.toString(data_sd_smm_h);
        printsdSMM = findViewById(R.id.print_sd_smm);
        printsdSMM.setText(data_sd_smm_toS);

        //DB 객체 생성
        database = FirebaseDatabase.getInstance();

        //현재 로그인한 사용자의 성별 가져오기 위한 경로 지정
        myRef = database.getReference("users").child(c_user.getUid());

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                //현재 로그인한 사용자의 성별에 따른 체지방 표준 범위 계산
                User userdata = dataSnapshot.getValue(User.class);
                float data_sd_fm;
                //남성일 경우, 체중의 15%   여성일 경우, 체중의 23%
                if(userdata.getSex().equals("남성")){
                    data_sd_fm = data_w * 0.15f;
                }else{
                    data_sd_fm = data_w * 0.23f;
                }
                //체지방량 표준 최저 계산
                data_sd_fm_l = data_sd_fm * 0.8;
                data_sd_fm_l = Double.parseDouble(String.format("%.1f",data_sd_fm_l));
                myRef2 = database.getReference();
                myRef2.child("Inbody_result").child(c_user.getUid()).child("min_fm").setValue(data_sd_fm_l);//체지방 표준 최저
                //체지방량 표준 최고 계산
                data_sd_fm_h = data_sd_fm * 1.6;
                data_sd_fm_h = Double.parseDouble(String.format("%.1f",data_sd_fm_h));
                myRef2 = database.getReference();
                myRef2.child("Inbody_result").child(c_user.getUid()).child("max_fm").setValue(data_sd_fm_h);//체지방 표준 최저
                //체지방량 표준 범위 출력
                String data_sd_fm_toS = Double.toString(data_sd_fm_l) +"~"+ Double.toString(data_sd_fm_h);
                printsdFM = findViewById(R.id.print_sd_fm);
                printsdFM.setText(data_sd_fm_toS);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        //DB 데이터 업데이트를 위한 경로 지정
        myRef = database.getReference();

        myRef.child("Inbody_result").child(c_user.getUid()).child("weight").setValue(data_w);//체중
        myRef.child("Inbody_result").child(c_user.getUid()).child("body_water").setValue(data_bw);//체수분
        myRef.child("Inbody_result").child(c_user.getUid()).child("protein").setValue(data_pt);//단백질
        myRef.child("Inbody_result").child(c_user.getUid()).child("mineral").setValue(data_mr);//무기질
        myRef.child("Inbody_result").child(c_user.getUid()).child("fat_mass").setValue(data_fm);//체지방
        myRef.child("Inbody_result").child(c_user.getUid()).child("muscle_mass").setValue(data_mm);//근육량
        myRef.child("Inbody_result").child(c_user.getUid()).child("fat_free_mass").setValue(data_fm);//제지방량
        myRef.child("Inbody_result").child(c_user.getUid()).child("skeletal_muscle_mass").setValue(data_smm);//골격근량
        myRef.child("Inbody_result").child(c_user.getUid()).child("min_smm").setValue(data_sd_smm_l);//골격근 표준 최저
        myRef.child("Inbody_result").child(c_user.getUid()).child("max_smm").setValue(data_sd_smm_h);//골격근 표준 최고
//        myRef.child("Inbody_result").child(c_user.getUid()).child("min_fm").setValue(data_sd_fm_l);//체지방 표준 최저
//        myRef.child("Inbody_result").child(c_user.getUid()).child("max_fm").setValue(data_sd_fm_h);//체지방 표준 최고
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
