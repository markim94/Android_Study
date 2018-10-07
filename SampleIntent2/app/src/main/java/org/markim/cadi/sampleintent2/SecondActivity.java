package org.markim.cadi.sampleintent2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class SecondActivity extends AppCompatActivity {

    // 객체 생성
    TextView textView;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        // 객체 참조
        textView = (TextView) findViewById(R.id.textView);
        button = (Button) findViewById(R.id.button);

        // 인텐트를 받아오는 getIntent()를 이용하여 getIntent 정의
        Intent getIntent = getIntent();
        // 받아온 인텐트에 담긴 부가데이터를 받아옴(구분을 위한 키 입력)
        String string = getIntent.getExtras().getString("TagName");
        // 받아온 데이터를 textView에 뿌림
        textView.setText(string);

        // 버튼 클릭리스너
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 버튼 클릭시 원래의 액티비티로 돌아가는 인텐트 정의
                Intent passedIntent = new Intent();
                // 응답코드는 200으로 전함, 태그로 정의하지 않고 바로 숫자로 응답코드를 기입하여도 됨.
                setResult(200, passedIntent);
                // SecondActivity 소멸
                finish();
            }
        });
    }
}
