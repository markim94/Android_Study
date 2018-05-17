package org.techtown.samplehttp;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity {
    EditText editText;
    TextView textView;
    String urlStr;

    Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = (EditText) findViewById(R.id.editText);
        textView = (TextView)findViewById(R.id.textView);

        Button button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                urlStr = editText.getText().toString();

                RequestThread thread = new RequestThread();
                thread.start();

            }
        });
    }


    class RequestThread extends Thread{
        public void run() {

            try {
                URL url = new URL(urlStr);
                HttpURLConnection conn = (HttpURLConnection)url.openConnection();

                // 라이브러리 사용시 아래와 같이 스레드와 핸들러를 신경쓰지 않아도 됨.

                if(conn !=null){
                    conn.setConnectTimeout(10000); //10초동안 기다리다가 응답이 없으면 끝내기
                    conn.setRequestMethod("GET"); // get방식으로
                    conn.setDoInput(true); // 서버에서 받는 것
                    conn.setDoOutput(true); //서버로 보내는 것을 가능하게 하기

                    int resCode = conn.getResponseCode(); // 연결
                    // if(resCode == HttpURLConnection.HTTP_OK)

                    BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                    String line = null;

                    while(true){
                        line = reader.readLine(); //한줄 씩 읽기
                        if(line == null){
                            break; // 다 읽었으니 나가기.
                        }

                        println(line);

                    }

                    reader.close(); // 사용 다하고 close
                    conn.disconnect(); // 연결 종료
                }

            } catch (Exception e){
                e.printStackTrace();
            }
        }
    }


    public void println(final String data){
        handler.post(new Runnable() {
            @Override
            public void run() {

                textView.append(data+"\n");

            }
        });
    }


}
