package com.cau.goodbody;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.cau.goodbody.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Status extends AppCompatActivity {

    TextView txt_email,txt_status,txt_uid;
    Button btn_send,btn_refresh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_status);

        txt_email = findViewById(R.id.txt_email);
        txt_status = findViewById(R.id.txt_status);
        txt_uid = findViewById(R.id.txt_uid);

        btn_refresh= findViewById(R.id.btn_referesh);
        btn_send = findViewById(R.id.btn_send);

        setInfo();

        //set Event

        btn_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btn_send.setEnabled(false);
                FirebaseAuth.getInstance().getCurrentUser()
                        .sendEmailVerification()
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                btn_send.setEnabled(true);

                                if (task.isSuccessful()) {
                                    Toast.makeText(Status.this, "Verification Email sent to : " + FirebaseAuth.getInstance().getCurrentUser().getEmail(), Toast.LENGTH_SHORT).show();
                                } else {
                                    Toast.makeText(Status.this, "Failed to send verification email", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }
        });

        btn_refresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().getCurrentUser()
                        .reload()
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                setInfo();

                            }
                        });
            }
        });
    }

    private void setInfo(){
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        txt_email.setText(new StringBuilder("EMAIL : ").append(user.getEmail()));
        txt_uid.setText(new StringBuilder("UID : ").append(user.getUid()));
        txt_status.setText(new StringBuilder("Status : ").append(String.valueOf(user.isEmailVerified())));



    }


}
