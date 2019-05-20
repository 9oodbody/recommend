package com.cau.goodbody;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

public class RecordMealActivity extends AppCompatActivity {

    private Toolbar sToolbar;
    private FloatingActionButton fab_B;
    private TextView result;
    private TextView result_kcal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record_meal);

        sToolbar = findViewById(R.id.toolbar);
        setSupportActionBar(sToolbar);
        getSupportActionBar().setTitle("식단 기록");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        result = findViewById(R.id.search_result);
        result_kcal = findViewById(R.id.search_result_kcal);

        fab_B = findViewById(R.id.fab_breakfast);
        fab_B.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent pageIntent = new Intent(RecordMealActivity.this, SearchMealActivity.class);
                startActivityForResult(pageIntent,1);
            }
        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode==1){
            if(resultCode==RESULT_OK){
                //데이터 받기
                String s_result = data.getStringExtra("search_name");
                String sk_result = data.getStringExtra("search_name_kcal");
                result.setText(s_result);
                result_kcal.setText(sk_result);
            }
        }
    }
}
