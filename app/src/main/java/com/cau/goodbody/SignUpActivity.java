package com.cau.goodbody;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;
import android.support.v7.widget.Toolbar;

import com.facebook.login.Login;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SignUpActivity extends BaseActivity {

    private static final String TAG = "회원가입";

    // [START declare_auth]
    private FirebaseAuth mAuth;
    // [END declare_auth]
    private DatabaseReference mDatabase;

    private Toolbar sToolbar;
    private EditText mNameField,mEmailField,mPasswordField,mCheckPasswordField,mHeightField,mBirthField;
    private RadioGroup rgSex,rgGoal;
    private Button signUpBtn,toLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        sToolbar = findViewById(R.id.toolbar);
        setSupportActionBar(sToolbar);
        getSupportActionBar().setTitle("회원 가입");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mDatabase = FirebaseDatabase.getInstance().getReference();

        mEmailField = findViewById(R.id.fieldEmail);
        mPasswordField = findViewById(R.id.fieldPassword);
        mCheckPasswordField = findViewById(R.id.fieldCheckPassword);

        rgSex = findViewById(R.id.rg_sex);
        rgGoal = findViewById(R.id.rg_goal);

        // [START initialize_auth]
        mAuth = FirebaseAuth.getInstance();
        // [END initialize_auth]

        signUpBtn = findViewById(R.id.emailCreateAccountButton);
        signUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String pwd = mPasswordField.getText().toString();
                String ch_pwd = mCheckPasswordField.getText().toString();

                if(pwd.equals(ch_pwd)){
                    createAccount(mEmailField.getText().toString(), mPasswordField.getText().toString());
                }else{
                    Toast.makeText(SignUpActivity.this, "비밀번호를 확인해주세요.", Toast.LENGTH_LONG).show();
                }
            }
        });
        toLogin = findViewById(R.id.toLoginPage);
        toLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAuth.signOut();
                startActivity(new Intent(SignUpActivity.this, LoginCombActivity.class));
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
                            final FirebaseUser user = mAuth.getCurrentUser();
                            String cu = user.getUid();
                            final String email = user.getEmail();

                            //이름 받아오기
                            mNameField = findViewById(R.id.fieldName);
                            String name = mNameField.getText().toString();
                            //성별 받아오기
                            RadioButton rd = findViewById(rgSex.getCheckedRadioButtonId());
                            String str_sex = rd.getText().toString();
                            //키 받아오기
                            mHeightField = findViewById(R.id.fieldHeight);
                            int height = Integer.parseInt(mHeightField.getText().toString());
                            //출생년도 받아오기
                            mBirthField = findViewById(R.id.fieldBirth);
                            int birth = Integer.parseInt(mBirthField.getText().toString());
                            int age = 2019-birth+1;
                            //목표 받아오기
                            RadioButton rd2 = findViewById(rgGoal.getCheckedRadioButtonId());
                            String str_goal = rd2.getText().toString();

                            User userdata = new User(name,email,str_sex,height,age,str_goal);

                            mDatabase.child("users").child(cu).child("name").setValue(userdata.getName());
                            mDatabase.child("users").child(cu).child("email").setValue(userdata.getEmail());
                            mDatabase.child("users").child(cu).child("sex").setValue(userdata.getSex());
                            mDatabase.child("users").child(cu).child("height").setValue(userdata.getHeight());
                            mDatabase.child("users").child(cu).child("age").setValue(userdata.getAge());
                            mDatabase.child("users").child(cu).child("goal").setValue(userdata.getGoal());

                            user.sendEmailVerification()
                                    .addOnCompleteListener(SignUpActivity.this, new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            // [START_EXCLUDE]

                                            if (task.isSuccessful()) {
                                                Toast.makeText(SignUpActivity.this,
                                                        "메일 발송  " + user.getEmail(),
                                                        Toast.LENGTH_LONG).show();
                                                mNameField.getText().clear();
                                                mEmailField.getText().clear();
                                                mPasswordField.getText().clear();
                                                mCheckPasswordField.getText().clear();
                                                mHeightField.getText().clear();
                                                mBirthField.getText().clear();
                                                rgSex.clearCheck();
                                                rgGoal.clearCheck();
                                            } else {
                                                Log.e(TAG, "sendEmailVerification", task.getException());
                                                Toast.makeText(SignUpActivity.this,
                                                        "메일 발송 실패",
                                                        Toast.LENGTH_LONG).show();
                                            }
                                            // [END_EXCLUDE]
                                        }
                                    });
                            // [END send_email_verification]

                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "createUserWithEmail:failure", task.getException());
                            Toast.makeText(SignUpActivity.this, "이미 가입된 메일입니다.",
                                    Toast.LENGTH_SHORT).show();
                        }

                        // [START_EXCLUDE]
                        hideProgressDialog();
                        // [END_EXCLUDE]
                    }
                });
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