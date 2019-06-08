package com.cau.goodbody;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class WorkoutSelectActivity extends AppCompatActivity {

    private Button toAll,toRec;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workout_select);

        toAll = findViewById(R.id.to_all);
        toAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mainpageIntent = new Intent(WorkoutSelectActivity.this, AllExerciseActivity.class);
                startActivity(mainpageIntent);
            }
        });
        toRec = findViewById(R.id.to_rec);
        toRec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mainpageIntent = new Intent(WorkoutSelectActivity.this, RecommendExrActivity.class);
                startActivity(mainpageIntent);
            }
        });
    }
}
