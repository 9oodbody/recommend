package com.cau.goodbody;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

public class Arm1listActivity extends AppCompatActivity {

    private ListView mListView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_arm1list);

        /* 위젯과 멤버변수 참조 획득 */
        mListView = (ListView)findViewById(R.id.listView);

        /* 아이템 추가 및 어댑터 등록 */
        dataSetting();
    }

    private void dataSetting(){

        MyAdapter mMyAdapter = new MyAdapter();

        mMyAdapter.addItem(ContextCompat.getDrawable(getApplicationContext(), R.drawable.icon), "팔 들어올리기", "X30");
        mMyAdapter.addItem(ContextCompat.getDrawable(getApplicationContext(), R.drawable.icon), "옆으로 팔 올리기", "X30");
        mMyAdapter.addItem(ContextCompat.getDrawable(getApplicationContext(), R.drawable.icon), "트리셉스 체어 딥", "X10");
        mMyAdapter.addItem(ContextCompat.getDrawable(getApplicationContext(), R.drawable.icon), "시계 방향으로 팔 돌리기", "00:30");
        mMyAdapter.addItem(ContextCompat.getDrawable(getApplicationContext(), R.drawable.icon), "시계 반대 방향으로 팔 돌리기", "00:30");
        mMyAdapter.addItem(ContextCompat.getDrawable(getApplicationContext(), R.drawable.icon), "다이아몬드 푸시업", "X6");
        mMyAdapter.addItem(ContextCompat.getDrawable(getApplicationContext(), R.drawable.icon), "점핑잭", "00:30");
        mMyAdapter.addItem(ContextCompat.getDrawable(getApplicationContext(), R.drawable.icon), "가슴모아 들어올리기", "00:16");
        mMyAdapter.addItem(ContextCompat.getDrawable(getApplicationContext(), R.drawable.icon), "다리 역기 컬 왼쪽", "X8");
        mMyAdapter.addItem(ContextCompat.getDrawable(getApplicationContext(), R.drawable.icon), "다리 역기 컬 오른쪽", "X8");
        mMyAdapter.addItem(ContextCompat.getDrawable(getApplicationContext(), R.drawable.icon), "사선플랭크", "X10");
        mMyAdapter.addItem(ContextCompat.getDrawable(getApplicationContext(), R.drawable.icon), "펀치", "00:30");
        mMyAdapter.addItem(ContextCompat.getDrawable(getApplicationContext(), R.drawable.icon), "팔굽혀펴기", "X10");
        mMyAdapter.addItem(ContextCompat.getDrawable(getApplicationContext(), R.drawable.icon), "땅 짚고 올라가기", "X8");
        mMyAdapter.addItem(ContextCompat.getDrawable(getApplicationContext(), R.drawable.icon), "벽 푸시업", "x12");
        mMyAdapter.addItem(ContextCompat.getDrawable(getApplicationContext(), R.drawable.icon), "삼두근 스트레칭 왼쪽", "00:30");
        mMyAdapter.addItem(ContextCompat.getDrawable(getApplicationContext(), R.drawable.icon), "삼두근 스트레칭 오른쪽", "00:30");
        mMyAdapter.addItem(ContextCompat.getDrawable(getApplicationContext(), R.drawable.icon), "스탠딩 이두근 스트레칭 왼쪽", "00:30");
        mMyAdapter.addItem(ContextCompat.getDrawable(getApplicationContext(), R.drawable.icon), "스탠딩 이두근 스트레칭 오른쪽", "00:30");



        /* 리스트뷰에 어댑터 등록 */
        mListView.setAdapter(mMyAdapter);
//        mListView.setOnItemClickListener(listener);
    }
    AdapterView.OnItemClickListener listener= new AdapterView.OnItemClickListener() {
        //ListView의 아이템 중 하나가 클릭될 때 호출되는 메소드
        //첫번째 파라미터 : 클릭된 아이템을 보여주고 있는 AdapterView 객체(여기서는 ListView객체)
        //두번째 파라미터 : 클릭된 아이템 뷰
        //세번째 파라미터 : 클릭된 아이템의 위치(ListView이 첫번째 아이템(가장위쪽)부터 차례대로 0,1,2,3.....)
        //네번재 파리미터 : 클릭된 아이템의 아이디(특별한 설정이 없다면 세번째 파라미터인 position과 같은 값)

        @Override

        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            // TODO Auto-generated method stub
//            //클릭된 아이템의 위치를 이용하여 데이터인 문자열을 Toast로 출력
//            Toast.makeText(Arm1listActivity.this, (CharSequence) MyAdapter.mItems.get(position), Toast.LENGTH_SHORT).show();
//            Intent pageIntent = new Intent(Arm1listActivity.this, ViewDetailExrActivity.class);
//            pageIntent.putExtra("search_name", (CharSequence)MyAdapter.mItems.get(position));
//            setResult(RESULT_OK,pageIntent);
//            startActivity(pageIntent);
        }
    };
}


