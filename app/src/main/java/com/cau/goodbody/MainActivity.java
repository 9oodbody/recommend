package com.cau.goodbody;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    private Toolbar sToolbar;
    private FirebaseUser c_user;
    private TextView mStatusTextView;
    private TextView mDetailTextView;
    private Button signOutBtn;
    private Button verifyEmailBtn;
    private Button toTextRecord,toResultTextRecord;
    private Button toMealRecom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sToolbar = findViewById(R.id.toolbar);
        setSupportActionBar(sToolbar);
        getSupportActionBar().setTitle("메인 페이지");

        mStatusTextView = findViewById(R.id.status);
        mDetailTextView = findViewById(R.id.detail);

        c_user = FirebaseAuth.getInstance().getCurrentUser();

        mStatusTextView.setText(getString(R.string.emailpassword_status_fmt, c_user.getEmail(), c_user.isEmailVerified()));
        mDetailTextView.setText(getString(R.string.firebase_status_fmt, c_user.getUid()));
        signOutBtn = findViewById(R.id.signOutButton);
        signOutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signOut();
                Intent pageIntent = new Intent(MainActivity.this, LoginCombActivity.class);
                startActivity(pageIntent);
            }
        });

        if(c_user.isEmailVerified()){
            findViewById(R.id.verifyEmailButton).setEnabled(false);
        }

        verifyEmailBtn = findViewById(R.id.verifyEmailButton);
        verifyEmailBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendEmailVerification();
            }
        });

        toTextRecord = findViewById(R.id.to_text_record);
        toTextRecord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mainpageIntent = new Intent(MainActivity.this, TextRecordActivity.class);
                mainpageIntent.putExtra("current_user",c_user);
                startActivity(mainpageIntent);
            }
        });

        toResultTextRecord = findViewById(R.id.to_result_text_record);
        toResultTextRecord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mainpageIntent = new Intent(MainActivity.this, ResultTextRecordActivity.class);
                mainpageIntent.putExtra("current_user",c_user);
                startActivity(mainpageIntent);
            }
        });

        toMealRecom = findViewById(R.id.to_meal_recommendation);
        toMealRecom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mainpageIntent = new Intent(MainActivity.this, MealRecommendation.class);
//                mainpageIntent.putExtra("current_user",c_user);
                startActivity(mainpageIntent);
            }
        });
    }

    private void signOut() {
        FirebaseAuth.getInstance().signOut();
    }

    public void sendEmailVerification() {
        // [START send_email_verification]
        FirebaseAuth auth = FirebaseAuth.getInstance();
        FirebaseUser user = auth.getCurrentUser();

        user.sendEmailVerification()
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Log.d(TAG, "Email sent.");
                        }
                    }
                });
        // [END send_email_verification]
    }

}
