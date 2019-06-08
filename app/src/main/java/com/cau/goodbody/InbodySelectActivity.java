package com.cau.goodbody;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class InbodySelectActivity extends AppCompatActivity {

    private Button toManual,toShow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inbody_select);

        toManual = findViewById(R.id.to_manual_result);
        toManual.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mainpageIntent = new Intent(InbodySelectActivity.this, TextRecordActivity.class);
                startActivity(mainpageIntent);
            }
        });
        toShow = findViewById(R.id.to_show_result);
        toShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mainpageIntent = new Intent(InbodySelectActivity.this, ShowInbodyResultActivity.class);
                startActivity(mainpageIntent);
            }
        });
    }

}
