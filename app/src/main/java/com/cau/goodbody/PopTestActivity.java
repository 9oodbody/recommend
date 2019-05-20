package com.cau.goodbody;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class PopTestActivity extends AppCompatActivity {

    private EditText searchMeal;
    private Button searchMealButton;
    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_pop_test);

        searchMeal = findViewById(R.id.search_meal);
        searchMealButton = findViewById(R.id.search_meal_button);
        searchMealButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String string_sW = searchMeal.getText().toString();


                mDatabase = FirebaseDatabase.getInstance().getReference();

                mDatabase.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        Food data = dataSnapshot.child("음식").child(string_sW).getValue(Food.class);
                        if(data != null){
//                            System.out.println(data.howto);
                            Intent pageIntent = new Intent(PopTestActivity.this, RecordMealMainActivity.class);
                            pageIntent.putExtra("search_name", string_sW);
                            pageIntent.putExtra("search_name_kcal", Integer.toString(data.kcal));
                            Log.i("TAG",Integer.toString(data.kcal));
                            setResult(RESULT_OK,pageIntent);
                            finish();
                        }else{
                            Toast.makeText(PopTestActivity.this, "검색명을 확인해주세요", Toast.LENGTH_LONG).show();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

            }
        });
    }
}
