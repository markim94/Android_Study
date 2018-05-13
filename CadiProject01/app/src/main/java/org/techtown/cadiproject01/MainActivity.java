package org.techtown.cadiproject01;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    public static final int REQUEST_CODE_SECOND = 101; //요청코드 101로 정의

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button = (Button) findViewById(R.id.button); //버튼 객체 참조

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), SecondActivity.class); //context객체 참조후 전달
                startActivityForResult(intent, REQUEST_CODE_SECOND); //ForResult의 경우는 새로 띄운 액티비티로 부터 응답 받을 수 있다.
                Toast.makeText(getApplicationContext(),"request_code: " + REQUEST_CODE_SECOND,Toast.LENGTH_SHORT).show(); //요청코드 토스트 메시지
            }
        });

    }
}
