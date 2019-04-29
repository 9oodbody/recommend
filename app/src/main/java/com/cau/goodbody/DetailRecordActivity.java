package com.cau.goodbody;

import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.support.v7.widget.Toolbar;

import java.lang.reflect.Array;

public class DetailRecordActivity extends AppCompatActivity {

    private Toolbar sToolbar;
    private TextView result;
    private TextView workoutTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_record);

        sToolbar = findViewById(R.id.toolbar);
        setSupportActionBar(sToolbar);
        getSupportActionBar().setTitle("운동 기록");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

//      SearchWorkoutActivity에서 입력값 받기
        Bundle extras = getIntent().getExtras();
        String search_name = extras.getString("search_name");

        result = findViewById(R.id.search_result);
        result.setText(search_name);

//        workoutTime = findViewById(R.id.workout_time);
//        workoutTime.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                AlertDialog.Builder alert = new AlertDialog.Builder(DetailRecordActivity.this);
//
//                setTitle("운동 시간");
//                Spinner spinner = findViewById(R.id.spinner_time);
////                ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.time_array,R.layout.simple)
//
//            }
//        });

        Spinner spinner_h = findViewById(R.id.spinner_time_hour);
        Spinner spinner_m = findViewById(R.id.spinner_time_minute);
//        spinner_m.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> parent) {
//
//            }
//        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:{ //toolbar의 back키 눌렀을 때 동작
                finish();
                return true;
            }
        }
        return super.onOptionsItemSelected(item);
    }
}
