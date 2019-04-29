package com.cau.goodbody;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Toolbar mainToolbar;
    private Button toMainButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainToolbar=findViewById(R.id.main_toolbar);
        setSupportActionBar(mainToolbar);

        toMainButton = findViewById(R.id.toMain);
        toMainButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gsignUpIntent = new Intent(MainActivity.this, LoginCombActivity.class);
                startActivity(gsignUpIntent);
            }
        });
    }
}
