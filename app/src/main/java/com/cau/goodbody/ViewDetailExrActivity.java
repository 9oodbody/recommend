package com.cau.goodbody;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ViewDetailExrActivity extends AppCompatActivity {

    private TextView tv_Exrname, tv_Exrhowto, tv_Exrlevel, tv_Exrpart;
    private ImageView iv_Exrimage;
    private   String exr_imageurl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewdetailexr);

        tv_Exrhowto = findViewById(R.id.tv_Exrhowto);
        tv_Exrname = findViewById(R.id.tv_Exrname);
        tv_Exrlevel = findViewById(R.id.tv_Exrlevel);
        tv_Exrpart = findViewById(R.id.tv_Exrpart);
        iv_Exrimage = findViewById(R.id.iv_Exrimage);

        Intent intent = getIntent();
        String result = intent.getStringExtra("search_name");


        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference databaseRef = database.getReference("운동");
        Log.i("TAG:result is",result);
        databaseRef.child(result).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Exercise exercise = dataSnapshot.getValue(Exercise.class);
                tv_Exrname.setText(exercise.getName());
                //        System.out.println("운동이름"+exercise.getName());
                tv_Exrhowto.setText(exercise.getHowto());
                tv_Exrlevel.setText("난이도 : "+exercise.getLevel());
                tv_Exrpart.setText("부위 : " +exercise.getPart());
                Glide.with(getApplicationContext()).load(exercise.getImage()).into(iv_Exrimage);
                //        System.out.println("운동URL"+exercise.getImage());
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
                //  Log.w("TAG: ", "Failed to read value", databaseError.toException());
            }
        });
        System.out.println("out:"+exr_imageurl);
        // Glide.with(this).load(exr_image).into(iv_Exrimage);
        //   ImageView iv = (ImageView)findViewById(R.id.iv);
        //    Glide.with(this).load(R.drawable.img).into(iv);


    }
}
