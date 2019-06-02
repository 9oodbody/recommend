package com.cau.goodbody;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class TextRecordActivity extends AppCompatActivity {

    private Toolbar sToolbar;
    private EditText bodyWaterEt;
    private EditText proteinEt;
    private EditText mineralEt;
    private EditText fatMassEt;
    private Button btn;
    private FirebaseUser c_user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text_record);

        sToolbar = findViewById(R.id.toolbar);
        setSupportActionBar(sToolbar);
        getSupportActionBar().setTitle("결과지 입력");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        bodyWaterEt = findViewById(R.id.body_water_et);
        proteinEt = findViewById(R.id.protein_et);
        mineralEt = findViewById(R.id.mineral_et);
        fatMassEt = findViewById(R.id.fat_mass_et);

        c_user = FirebaseAuth.getInstance().getCurrentUser();

        btn = findViewById(R.id.text_record_btn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent pageIntent = new Intent(TextRecordActivity.this, ResultTextRecordActivity.class);
                pageIntent.putExtra("body_water_et", Float.parseFloat(bodyWaterEt.getText().toString()));
                pageIntent.putExtra("protein_et", Float.parseFloat(proteinEt.getText().toString()));
                pageIntent.putExtra("mineral_et", Float.parseFloat(mineralEt.getText().toString()));
                pageIntent.putExtra("fat_mass_et", Float.parseFloat(fatMassEt.getText().toString()));
                pageIntent.putExtra("current_user",c_user);
                bodyWaterEt.getText().clear();
                proteinEt.getText().clear();
                mineralEt.getText().clear();
                fatMassEt.getText().clear();
                fatMassEt.setCursorVisible(false);
                fatMassEt.setFocusable(false);
                fatMassEt.setFocusableInTouchMode(false);
                startActivity(pageIntent);
            }
        });
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
