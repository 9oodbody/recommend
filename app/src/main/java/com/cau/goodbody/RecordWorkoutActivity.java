package com.cau.goodbody;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class RecordWorkoutActivity extends AppCompatActivity {

    private Button addRecord_w;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record_workout);
        addRecord_w = findViewById(R.id.addRecordWorkOut);
        addRecord_w.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    Intent pageIntent = new Intent(RecordWorkoutActivity.this, SearchWorkoutActivity.class);
                    startActivity(pageIntent);
            }
        });
    }
}
