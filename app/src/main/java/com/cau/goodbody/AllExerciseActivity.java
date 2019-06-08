package com.cau.goodbody;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class AllExerciseActivity extends AppCompatActivity {

    private FirebaseDatabase database;
    private DatabaseReference mDatabase;
    private ListView listView;
    List fileList = new ArrayList<>();
    ArrayAdapter adapter;
    static boolean calledAlready = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_allexercise);
//        if (!calledAlready)
//        {
//            FirebaseDatabase.getInstance().setPersistenceEnabled(true); // 다른 인스턴스보다 먼저 실행되어야 한다.
//            calledAlready = true;
//        }

        listView= (ListView)  findViewById(R.id.lv_fileList);
        adapter = new ArrayAdapter<String>(this, R.layout.activity_list_item, fileList);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(listener);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference databaseRef = database.getReference("운동");
        databaseRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot fileSnapshot : dataSnapshot.getChildren()) {
                    String str = fileSnapshot.child("name").getValue(String.class);
                    Log.i("TAG: value is ", str);
                    fileList.add(str);
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    AdapterView.OnItemClickListener listener= new AdapterView.OnItemClickListener() {
        //ListView의 아이템 중 하나가 클릭될 때 호출되는 메소드
        //첫번째 파라미터 : 클릭된 아이템을 보여주고 있는 AdapterView 객체(여기서는 ListView객체)
        //두번째 파라미터 : 클릭된 아이템 뷰
        //세번째 파라미터 : 클릭된 아이템의 위치(ListView이 첫번째 아이템(가장위쪽)부터 차례대로 0,1,2,3.....)
        //네번재 파리미터 : 클릭된 아이템의 아이디(특별한 설정이 없다면 세번째 파라이터인 position과 같은 값)

        @Override

        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            // TODO Auto-generated method stub
//            //클릭된 아이템의 위치를 이용하여 데이터인 문자열을 Toast로 출력
            Toast.makeText(AllExerciseActivity.this, (CharSequence) fileList.get(position), Toast.LENGTH_SHORT).show();
            Intent pageIntent = new Intent(AllExerciseActivity.this, ViewDetailExrActivity.class);
            pageIntent.putExtra("search_name", (CharSequence)fileList.get(position));
            setResult(RESULT_OK,pageIntent);
            startActivity(pageIntent);
        }
    };
}