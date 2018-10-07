package org.markim.cadi.sampleintent2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    // 요청코드를 담은 태그
    public static final int REQUEST_CODE_SECOND = 100;

    // 객체 생성
    EditText editText;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //객체 참조
        editText = (EditText) findViewById(R.id.editText);
        button = (Button) findViewById(R.id.button);

        // 버튼 클릭리스너
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // 메인->세컨드로 전환하는 인텐트 정의
                Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                // editText에 담긴 String을 getText()로 가져와 string에 담음
                String string = editText.getText().toString();
                // intent에 부가데이터를 구분할 태그 "TagName"에 string을 담음
                intent.putExtra("TagName", string);
                // 액티비티 전환(인텐트와 태그로 정의한 요청코드)
                startActivityForResult(intent, REQUEST_CODE_SECOND);

            }
        });
    }

    // setResult()의 응답코드에 따라 다양한 이벤트를 설정할 수 있는 onActivityResult()
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // SecondActivity로 부터 응답받은 코드가 200이면 아래의 토스트 메시지 출력
        if(resultCode==200){
            Toast.makeText(getApplicationContext(), "정상적으로 반환", Toast.LENGTH_SHORT).show();
        }
    }
}
