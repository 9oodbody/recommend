package com.cau.goodbody;

import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;

import java.lang.reflect.Array;

public class DetailRecordActivity extends AppCompatActivity implements View.OnClickListener {

    private Toolbar sToolbar;
    private TextView result;
    private TextView workoutTime;
    private RadioGroup rg;
    private String choice_h="";
    private String choice_m="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_record);

        findViewById(R.id.addRecordWorkOut).setOnClickListener(this);

//      툴바 설정
        sToolbar = findViewById(R.id.toolbar);
        setSupportActionBar(sToolbar);
        getSupportActionBar().setTitle("운동 기록");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


//      SearchWorkoutActivity에서 운동명 받아오기
        Bundle extras = getIntent().getExtras();
        String search_name = extras.getString("search_name");

        result = findViewById(R.id.search_result);
        result.setText(search_name);

//        라디오버튼 값 받아오기
        rg = findViewById(R.id.rg_workout_level);

//        스피너 값 받아오기
        final ArrayAdapter<CharSequence> adspin1,adspin2;
        final Spinner spinner_h = findViewById(R.id.spinner_time_hour);
        final Spinner spinner_m = findViewById(R.id.spinner_time_minute);

        adspin1 = ArrayAdapter.createFromResource(this,R.array.time_array_hour,R.layout.support_simple_spinner_dropdown_item);
        adspin1.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        spinner_h.setAdapter(adspin1);

        adspin2 = ArrayAdapter.createFromResource(this,R.array.time_array_minute,R.layout.support_simple_spinner_dropdown_item);
        adspin2.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        spinner_m.setAdapter(adspin2);

        spinner_h.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                choice_h = adspin1.getItem(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

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

    @Override
    public void onClick(View v) {
        int i = v.getId();
        if (i == R.id.addRecordWorkOut) {
            RadioButton rd = findViewById(rg.getCheckedRadioButtonId());
            String str_level = rd.getText().toString();

            Toast.makeText(getApplicationContext(),str_level+choice_h,Toast.LENGTH_LONG).show();


        }
    }
}
