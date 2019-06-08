package com.cau.goodbody;


import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

public class Leg1listActivity extends AppCompatActivity {
    private ListView mListView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leg1list);

        /* 위젯과 멤버변수 참조 획득 */
        mListView = (ListView)findViewById(R.id.listView);

        /* 아이템 추가 및 어댑터 등록 */
        dataSetting();
    }

    private void dataSetting(){

        MyAdapter mMyAdapter = new MyAdapter();

        mMyAdapter.addItem(ContextCompat.getDrawable(getApplicationContext(), R.drawable.icon), "좌우뛰기", "00:30");
        mMyAdapter.addItem(ContextCompat.getDrawable(getApplicationContext(), R.drawable.icon), "스쿼트", "X12");
        mMyAdapter.addItem(ContextCompat.getDrawable(getApplicationContext(), R.drawable.icon), "스쿼트", "X12");
        mMyAdapter.addItem(ContextCompat.getDrawable(getApplicationContext(), R.drawable.icon), "옆으로 누워 다리 올리기 왼쪽", "X12");
        mMyAdapter.addItem(ContextCompat.getDrawable(getApplicationContext(), R.drawable.icon), "옆으로 누워 다리 올리기 오른쪽", "X12");
        mMyAdapter.addItem(ContextCompat.getDrawable(getApplicationContext(), R.drawable.icon), "뒤 런지", "X14");
        mMyAdapter.addItem(ContextCompat.getDrawable(getApplicationContext(), R.drawable.icon), "동키 킥 레프트", "X16");
        mMyAdapter.addItem(ContextCompat.getDrawable(getApplicationContext(), R.drawable.icon), "뒤 런지", "X14");
        mMyAdapter.addItem(ContextCompat.getDrawable(getApplicationContext(), R.drawable.icon), "동키 킥 라이드", "X16");
        mMyAdapter.addItem(ContextCompat.getDrawable(getApplicationContext(), R.drawable.icon), "벽 짚고 허벅지 스트레칭 왼쪽", "00:30");
        mMyAdapter.addItem(ContextCompat.getDrawable(getApplicationContext(), R.drawable.icon), "벽 짚고 허벅지 스트레칭 오른쪽", "00:30");
        mMyAdapter.addItem(ContextCompat.getDrawable(getApplicationContext(), R.drawable.icon), "무릎 당기기 스트레칭", "00:30");
        mMyAdapter.addItem(ContextCompat.getDrawable(getApplicationContext(), R.drawable.icon), "종아리 들기", "X12");
        mMyAdapter.addItem(ContextCompat.getDrawable(getApplicationContext(), R.drawable.icon), "종아리 들기", "X12");
        mMyAdapter.addItem(ContextCompat.getDrawable(getApplicationContext(), R.drawable.icon), "종아리 스트레칭", "00:30");

        /* 리스트뷰에 어댑터 등록 */
        mListView.setAdapter(mMyAdapter);

    }
}

