package com.cau.goodbody;

import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

public class Che1listActivity extends AppCompatActivity {
    private ListView mListView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_che1list);

        /* 위젯과 멤버변수 참조 획득 */
        mListView = (ListView)findViewById(R.id.listView);

        /* 아이템 추가 및 어댑터 등록 */
        dataSetting();
    }

    private void dataSetting(){

        MyAdapter mMyAdapter = new MyAdapter();

        mMyAdapter.addItem(ContextCompat.getDrawable(getApplicationContext(), R.drawable.icon), "점핑잭", "00:30");
        mMyAdapter.addItem(ContextCompat.getDrawable(getApplicationContext(), R.drawable.icon), "인클라인 푸시업", "X6");
        mMyAdapter.addItem(ContextCompat.getDrawable(getApplicationContext(), R.drawable.icon), "팔굽혀펴기", "X4");
        mMyAdapter.addItem(ContextCompat.getDrawable(getApplicationContext(), R.drawable.icon), "팔 벌려 푸시업", "X4");
        mMyAdapter.addItem(ContextCompat.getDrawable(getApplicationContext(), R.drawable.icon), "트리셉스 체어 딥", "X6");
        mMyAdapter.addItem(ContextCompat.getDrawable(getApplicationContext(), R.drawable.icon), "팔 벌려 푸시업", "X4");
        mMyAdapter.addItem(ContextCompat.getDrawable(getApplicationContext(), R.drawable.icon), "인클라인 푸시업", "X4");
        mMyAdapter.addItem(ContextCompat.getDrawable(getApplicationContext(), R.drawable.icon), "무릎굽혀 푸시업", "X4");
        mMyAdapter.addItem(ContextCompat.getDrawable(getApplicationContext(), R.drawable.icon), "코브라 스트레칭", "00:20");
        mMyAdapter.addItem(ContextCompat.getDrawable(getApplicationContext(), R.drawable.icon), "트리셉스 체어 딥", "X6");;
        mMyAdapter.addItem(ContextCompat.getDrawable(getApplicationContext(), R.drawable.icon), "가슴 스트레칭", "00:20");




        /* 리스트뷰에 어댑터 등록 */
        mListView.setAdapter(mMyAdapter);

    }
}
