package com.cau.goodbody;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.AdapterView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class SearchWorkoutActivity extends AppCompatActivity {
    private Toolbar sToolbar;
    private EditText searchWorkout;
    private Button searchWorkoutButton;

    private DatabaseReference mDatabase;
    private String str;

//    리스트뷰
    ListView listView;
    List fileList = new ArrayList<>();
    ArrayAdapter adapter;
    static boolean calledAlready = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_workout);

//        ListView 시도
        if(!calledAlready){
            FirebaseDatabase.getInstance().setPersistenceEnabled(true);
            calledAlready = true;
        }
        listView=findViewById(R.id.listview_w);
        adapter = new ArrayAdapter<String>(this,R.layout.activity_listitem,fileList);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(mItemClickListener);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference databaseRef = database.getReference("운동");

        // read from the database
        databaseRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot fileSnapshot:dataSnapshot.getChildren()){
                    str = fileSnapshot.getKey();
                    if (str != "유산소" && str != "무산소"){
                        Log.i("Tag: 값은 ",str);
                        fileList.add(str);
                    }
//                    fileList.add(str);
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.w("Tag: ","값 읽어오기 실패",databaseError.toException());
            }
        });


        sToolbar = findViewById(R.id.toolbar);
        setSupportActionBar(sToolbar);
        getSupportActionBar().setTitle("운동 기록");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        searchWorkout = findViewById(R.id.search_workout);
        searchWorkoutButton = findViewById(R.id.search_workout_button);
        searchWorkoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String string_sW = searchWorkout.getText().toString();


                mDatabase = FirebaseDatabase.getInstance().getReference();

                mDatabase.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        Workout data = dataSnapshot.child("운동").child(string_sW).getValue(Workout.class);
                        if(data != null){
//                            System.out.println(data.howto);
                            Intent pageIntent = new Intent(SearchWorkoutActivity.this, DetailRecordActivity.class);
                            pageIntent.putExtra("search_name", string_sW);
                            startActivity(pageIntent);
                        }else{
                            Toast.makeText(SearchWorkoutActivity.this, "검색명을 확인해주세요", Toast.LENGTH_LONG).show();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

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

    AdapterView.OnItemClickListener mItemClickListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            String mes;
            mes = "Select Item = "+fileList.get(position);
            Toast.makeText(SearchWorkoutActivity.this, mes, Toast.LENGTH_SHORT).show();
        }
    };
}
