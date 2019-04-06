package com.cau.goodbody;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SignUpActivity extends AppCompatActivity {
    private static final String TAG = "EmailPassword";
    private EditText signUpEmail;
    private EditText signUpName;
    private EditText signUpPassword;
    private Button registerButton;
    private FirebaseAuth mAuth;
    private Button verifyButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_signup);
        super.onCreate(savedInstanceState);

        setTitle("Sign Up");
        mAuth=FirebaseAuth.getInstance();
        signUpEmail = findViewById(R.id.signup_email);
        signUpName = findViewById(R.id.signup_name);
        signUpPassword = findViewById(R.id.signup_password);
        registerButton = findViewById(R.id.register_button);
        verifyButton = findViewById(R.id.verify_button );

        verifyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createUser(signUpEmail.getText().toString(), signUpPassword.getText().toString());
//                Intent turnLoginIntent = new Intent(SignUpActivity.this, LoginActivity.class);
//                startActivity(turnLoginIntent);
//                System.out.print(signUpEmail);
            }
        });

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent turnLoginIntent = new Intent(SignUpActivity.this, LoginActivity.class);
                startActivity(turnLoginIntent);
            }
        });
    }

    private void createUser(String email, String password){
        final FirebaseUser user = mAuth.getCurrentUser();
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
//                            System.out.print(signUpEmail);
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "createUserWithEmail:success");
                            Toast.makeText(SignUpActivity.this,
                                    "Verification email sent to " + user.getEmail(),
                                    Toast.LENGTH_SHORT).show();
                            sendEmailVerification();

                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "createUserWithEmail:failure", task.getException());
                            Toast.makeText(SignUpActivity.this, "회원가입 실패",
                                    Toast.LENGTH_SHORT).show();
                        }

                        // ...
                    }
                });
    }


    private void sendEmailVerification() {
        // Disable button
        findViewById(R.id.verify_button).setEnabled(false);

        // Send verification email
        // [START send_email_verification]
        final FirebaseUser user = mAuth.getCurrentUser();
        user.sendEmailVerification()
                .addOnCompleteListener(this, new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        // [START_EXCLUDE]
                        // Re-enable button
                        findViewById(R.id.verify_button).setEnabled(true);

                        if (task.isSuccessful()) {
//                            Toast.makeText(SignUpActivity.this,
//                                    "Verification email sent to " + user.getEmail(),
//                                    Toast.LENGTH_SHORT).show();
                            Toast.makeText(SignUpActivity.this,
                                    "Create account successful with " + user.getEmail(),
                                    Toast.LENGTH_SHORT).show();
                        } else {
                            Log.e(TAG, "sendEmailVerification", task.getException());
                            Toast.makeText(SignUpActivity.this,
                                    "Failed to send verification email.",
                                    Toast.LENGTH_SHORT).show();
                        }
                        // [END_EXCLUDE]
                    }
                });
        // [END send_email_verification]
    }
}
