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

public class ShowInbodyResultActivity extends AppCompatActivity {

    private Toolbar sToolbar;
    private FirebaseUser c_user;
    private FirebaseDatabase database;
    private DatabaseReference myRef;
    private TextView printBW, printPT, printMR, printFM, printsdFM, printMM, printFC, printW, printSMM, printsdSMM, Username, printHeight, printBMI;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result_text_record);

        sToolbar = findViewById(R.id.toolbar);
        setSupportActionBar(sToolbar);
        getSupportActionBar().setTitle("결과지 분석");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        c_user = FirebaseAuth.getInstance().getCurrentUser();

        printBW = findViewById(R.id.print_bw);
        printPT = findViewById(R.id.print_pt);
        printMR = findViewById(R.id.print_mr);
        printFM = findViewById(R.id.print_fm);
        printMM = findViewById(R.id.print_mm);
        printFC = findViewById(R.id.print_fc);
        printW = findViewById(R.id.print_w);
        printSMM = findViewById(R.id.print_smm);
        printsdSMM = findViewById(R.id.print_sd_smm);
        Username = findViewById(R.id.username);
        printsdFM = findViewById(R.id.print_sd_fm);
        printHeight = findViewById(R.id.print_height);
        printBMI = findViewById(R.id.print_bmi);

        //DB 객체 생성
        database = FirebaseDatabase.getInstance();
        //사용자 이름 가져오기
        myRef = database.getReference("users").child(c_user.getUid());
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                User userdata = dataSnapshot.getValue(User.class);
                Username.setText(userdata.getName());
                printHeight.setText(String.valueOf(userdata.getHeight()));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

            //인바디 결과 있다면 가져오기
            myRef = database.getReference("users").child(c_user.getUid()).child("Inbody_result");
            myRef.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    InbodyResult result = dataSnapshot.getValue(InbodyResult.class);
                    if(result!=null){
                        printBW.setText(String.valueOf(result.getBody_water()));
                        printPT.setText(String.valueOf(result.getProtein()));
                        printMR.setText(String.valueOf(result.getMineral()));
                        printFM.setText(String.valueOf(result.getFat_mass()));
                        printMM.setText(String.valueOf(result.getMuscle_mass()));
                        printFC .setText(String.valueOf(result.getFat_free_mass()));
                        printW .setText(String.valueOf(result.getWeight()));
                        printSMM.setText(String.valueOf(result.getSkeletal_muscle_mass()));

                        String data_sd_smm_toS = Double.toString(result.getMin_smm()) +"~"+ Double.toString(result.getMax_smm());
                        printsdSMM.setText(data_sd_smm_toS);
                        String data_sd_fm_toS = Double.toString(result.getMin_fm()) +"~"+ Double.toString(result.getMax_fm());
                        printsdFM.setText(String.valueOf(data_sd_fm_toS));

                        printBMI.setText(String.valueOf(result.getBMI()));
                    }
//                    printBW.setText(String.valueOf(result.getBody_water()));
//                    printPT.setText(String.valueOf(result.getProtein()));
//                    printMR.setText(String.valueOf(result.getMineral()));
//                    printFM.setText(String.valueOf(result.getFat_mass()));
//                    printMM.setText(String.valueOf(result.getMuscle_mass()));
//                    printFC .setText(String.valueOf(result.getFat_free_mass()));
//                    printW .setText(String.valueOf(result.getWeight()));
//                    printSMM.setText(String.valueOf(result.getSkeletal_muscle_mass()));
//
//                    String data_sd_smm_toS = Double.toString(result.getMin_smm()) +"~"+ Double.toString(result.getMax_smm());
//                    printsdSMM.setText(data_sd_smm_toS);
//                    String data_sd_fm_toS = Double.toString(result.getMin_fm()) +"~"+ Double.toString(result.getMax_fm());
//                    printsdFM.setText(String.valueOf(data_sd_fm_toS));
//
//                    printBMI.setText(String.valueOf(result.getBMI()));
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });

//        //인바디 결과 있다면 가져오기
//        myRef = database.getReference("users").child(c_user.getUid()).child("Inbody_result");
//        myRef.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                InbodyResult result = dataSnapshot.getValue(InbodyResult.class);
//                printBW.setText(String.valueOf(result.getBody_water()));
//                printPT.setText(String.valueOf(result.getProtein()));
//                printMR.setText(String.valueOf(result.getMineral()));
//                printFM.setText(String.valueOf(result.getFat_mass()));
//                printMM.setText(String.valueOf(result.getMuscle_mass()));
//                printFC .setText(String.valueOf(result.getFat_free_mass()));
//                printW .setText(String.valueOf(result.getWeight()));
//                printSMM.setText(String.valueOf(result.getSkeletal_muscle_mass()));
//
//                String data_sd_smm_toS = Double.toString(result.getMin_smm()) +"~"+ Double.toString(result.getMax_smm());
//                printsdSMM.setText(data_sd_smm_toS);
//                String data_sd_fm_toS = Double.toString(result.getMin_fm()) +"~"+ Double.toString(result.getMax_fm());
//                printsdFM.setText(String.valueOf(data_sd_fm_toS));
//
//                printBMI.setText(String.valueOf(result.getBMI()));
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//
//            }
//        });
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
