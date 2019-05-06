package com.cau.goodbody;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;
import android.support.v7.widget.Toolbar;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class SignUpActivity extends BaseActivity {

    private static final String TAG = "회원가입";

    // [START declare_auth]
    private FirebaseAuth mAuth;
    // [END declare_auth]
    private DatabaseReference mDatabase;

    private Toolbar sToolbar;
    private EditText mNameField;
    private EditText mEmailField;
    private EditText mPasswordField;
    private EditText mCheckPasswordField;
    private RadioGroup rgSex;
    private Button signUpBtn;
    private Button verifyBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        sToolbar = findViewById(R.id.toolbar);
        setSupportActionBar(sToolbar);
        getSupportActionBar().setTitle("회원 가입");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mDatabase = FirebaseDatabase.getInstance().getReference();

        mNameField = findViewById(R.id.fieldName);
        mEmailField = findViewById(R.id.fieldEmail);
        mPasswordField = findViewById(R.id.fieldPassword);
        mCheckPasswordField = findViewById(R.id.fieldCheckPassword);

        rgSex = findViewById(R.id.rg_sex);

        // [START initialize_auth]
        mAuth = FirebaseAuth.getInstance();
        // [END initialize_auth]

//        verifyBtn = findViewById(R.id.verifyEmailButton);
//        verifyBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                sendEmailVerification();
//            }
//        });

        verifyBtn = findViewById(R.id.verifyEmailButton);
        verifyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createAccount(mEmailField.getText().toString(), mPasswordField.getText().toString());
            }
        });

        signUpBtn = findViewById(R.id.emailCreateAccountButton);
        signUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String pwd = mPasswordField.getText().toString();
                String ch_pwd = mCheckPasswordField.getText().toString();

                if(pwd.equals(ch_pwd)){
                    FirebaseUser user = mAuth.getCurrentUser();
                    Toast.makeText(SignUpActivity.this, "로그인 화면으로 넘어갑니다.", Toast.LENGTH_LONG).show();
                    mAuth.signOut();
                    finish();
                }else{
                    Toast.makeText(SignUpActivity.this, "비밀번호를 확인해주세요.", Toast.LENGTH_LONG).show();
                }

//                if(pwd.equals(ch_pwd)){
//                    FirebaseUser user = mAuth.getCurrentUser();
//                    System.out.println("유저다!!!!"+user.getEmail());
//                    System.out.println("유저다!!!!22222"+user.isEmailVerified());
//                    if (user.isEmailVerified()) {
//                        Toast.makeText(SignUpActivity.this, "\t회원가입 완료\n로그인 화면으로 넘어갑니다.",
//                                Toast.LENGTH_LONG).show();
//                        mAuth.signOut();
//                        finish();
//                    } else {
//                        Toast.makeText(SignUpActivity.this, "이메일 인증을 해주세요.",
//                                Toast.LENGTH_LONG).show();
//                    }
//                }else{
//                    Toast.makeText(SignUpActivity.this, "비밀번호를 확인해주세요.", Toast.LENGTH_LONG).show();
//                }
            }
        });
    }

    private void createAccount(final String email, final String password) {
        Log.d(TAG, "createAccount:" + email);
        if (!validateForm()) {
            return;
        }

        showProgressDialog();

        // [START create_user_with_email]
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "createUserWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            String cu = user.getUid();
                            String email = user.getEmail();

                            mNameField = findViewById(R.id.fieldName);
                            String name = mNameField.getText().toString();
                            RadioButton rd = findViewById(rgSex.getCheckedRadioButtonId());
                            String str_sex = rd.getText().toString();

                            User userdata = new User(name,email,str_sex);

                            mDatabase.child("users").child(cu).child("name").setValue(userdata.getName());
                            mDatabase.child("users").child(cu).child("email").setValue(userdata.getEmail());
                            mDatabase.child("users").child(cu).child("sex").setValue(userdata.getSex());


                            sendEmailVerification();
//                            verifyBtn = findViewById(R.id.verifyEmailButton);
//                            verifyBtn.setOnClickListener(new View.OnClickListener() {
//                                @Override
//                                public void onClick(View v) {
//                                    sendEmailVerification();
//                                }
//                            });
//                            if(user.isEmailVerified()){
//                                Toast.makeText(SignUpActivity.this, "회원가입 완료\n로그인 화면으로 넘어갑니다.",
//                                        Toast.LENGTH_LONG).show();
//                                finish();
//                            }else{
//                                Toast.makeText(SignUpActivity.this, "이메일 인증을 해주세요.",
//                                        Toast.LENGTH_LONG).show();
//                            }
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "createUserWithEmail:failure", task.getException());
                            Toast.makeText(SignUpActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        }

                        // [START_EXCLUDE]
                        hideProgressDialog();
                        // [END_EXCLUDE]
                    }
                });
        // [END create_user_with_email]
//        Intent mainpageIntent = new Intent(SignUpActivity.this, LoginCombActivity.class);
//        startActivity(mainpageIntent);
    }

    private boolean validateForm() {
        boolean valid = true;

        String email = mEmailField.getText().toString();
        if (TextUtils.isEmpty(email)) {
            mEmailField.setError("Required.");
            valid = false;
        } else {
            mEmailField.setError(null);
        }

        String password = mPasswordField.getText().toString();
        if (TextUtils.isEmpty(password)) {
            mPasswordField.setError("Required.");
            valid = false;
        } else {
            mPasswordField.setError(null);
        }

        return valid;
    }

    private void sendEmailVerification() {
        // Disable button
//        findViewById(R.id.verifyEmailButton).setEnabled(false);

        // Send verification email
        // [START send_email_verification]
        final FirebaseUser user = mAuth.getCurrentUser();
        user.sendEmailVerification()
                .addOnCompleteListener(this, new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        // [START_EXCLUDE]
                        // Re-enable button
//                        findViewById(R.id.verifyEmailButton).setEnabled(true);

                        if (task.isSuccessful()) {
                            Toast.makeText(SignUpActivity.this,
                                    "Verification email sent to " + user.getEmail(),
                                    Toast.LENGTH_LONG).show();
                        } else {
                            Log.e(TAG, "sendEmailVerification", task.getException());
                            Toast.makeText(SignUpActivity.this,
                                    "Failed to send verification email.",
                                    Toast.LENGTH_LONG).show();
                        }
                        // [END_EXCLUDE]
                    }
                });
        // [END send_email_verification]
    }
}
