package com.cau.goodbody;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

public class RecordMealActivity extends AppCompatActivity {

    private Toolbar sToolbar;
    private FloatingActionButton fab_B, fab_L, fab_D;
    private TextView searchKcal_B, searchKcal_L, searchKcal_D, totalKcal, result_B, result_L, result_D, Ment_B, Ment_L, Ment_D;
    private LinearLayout LL_B, LL_L, LL_D;
    DatabaseReference mDatabase;
    String s_result;

    public int totalKcal_Int=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record_meal);

        sToolbar = findViewById(R.id.toolbar);
        setSupportActionBar(sToolbar);
        getSupportActionBar().setTitle("식단 기록");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        result_B = findViewById(R.id.search_result_b);
        result_L = findViewById(R.id.search_result_l);
        result_D = findViewById(R.id.search_result_d);
        totalKcal = findViewById(R.id.total_kcal);
        searchKcal_B = findViewById(R.id.search_kcal_b);
        searchKcal_L = findViewById(R.id.search_kcal_l);
        searchKcal_D = findViewById(R.id.search_kcal_d);
        Ment_B = findViewById(R.id.ment_b);
        Ment_L = findViewById(R.id.ment_l);
        Ment_D = findViewById(R.id.ment_d);
        LL_B = findViewById(R.id.ll_b);
        LL_L = findViewById(R.id.ll_l);
        LL_D = findViewById(R.id.ll_d);

        fab_B = findViewById(R.id.fab_breakfast);
        fab_B.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent pageIntent = new Intent(RecordMealActivity.this, SearchMealActivity.class);
                startActivityForResult(pageIntent,1);
            }
        });
        fab_L = findViewById(R.id.fab_lunch);
        fab_L.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent pageIntent = new Intent(RecordMealActivity.this, SearchMealActivity.class);
                startActivityForResult(pageIntent,2);
            }
        });
        fab_D = findViewById(R.id.fab_dinner);
        fab_D.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent pageIntent = new Intent(RecordMealActivity.this, SearchMealActivity.class);
                startActivityForResult(pageIntent,3);
            }
        });
    }
    @Override
    protected void onActivityResult(final int requestCode, int resultCode, Intent data) {
        if(requestCode==1){
            if(resultCode==RESULT_OK){
                //데이터 받기
                s_result= data.getStringExtra("search_name");
                result_B.setText(s_result);
                Ment_B.setVisibility(View.GONE);
                LL_B.setVisibility(View.VISIBLE);
            }
        }else if(requestCode==2){
            if(resultCode==RESULT_OK) {
                //데이터 받기
                s_result = data.getStringExtra("search_name");
                result_L.setText(s_result);
                Ment_L.setVisibility(View.GONE);
                LL_L.setVisibility(View.VISIBLE);
            }
        }else{
            if(resultCode==RESULT_OK) {
                //데이터 받기
                s_result= data.getStringExtra("search_name");
                result_D.setText(s_result);
                Ment_D.setVisibility(View.GONE);
                LL_D.setVisibility(View.VISIBLE);
            }
        }
        mDatabase = FirebaseDatabase.getInstance().getReference();

        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Food data = dataSnapshot.child("음식").child(s_result).getValue(Food.class);
//                    result.setText(dataSnapshot.getKey());
                    totalKcal.setText(Integer.toString(data.getKcal()));
                    totalKcal_Int += data.getKcal();
                    totalKcal.setText(Integer.toString(totalKcal_Int));
                    if(requestCode==1){
                        searchKcal_B.setText(Integer.toString(data.getKcal()));
                    }else if(requestCode==2){
                        searchKcal_L.setText(Integer.toString(data.getKcal()));
                    }else{
                    searchKcal_D.setText(Integer.toString(data.getKcal()));
                    }
                }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
