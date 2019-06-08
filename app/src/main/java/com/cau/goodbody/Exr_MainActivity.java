package com.cau.goodbody;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Exr_MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exr_main);

        Button button1;
        Button button2;

        button1 = (Button) findViewById(R.id.btn_allexer);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent_allexer = new Intent(Exr_MainActivity.this, AllExerciseActivity.class);
                startActivity(intent_allexer);
            }
        });

        button2 = (Button) findViewById(R.id.btn_recexer);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent_recexer = new Intent(Exr_MainActivity.this, RecommendExrActivity.class);
//                startActivity(intent_recexer);
            }
        });
    }

}
