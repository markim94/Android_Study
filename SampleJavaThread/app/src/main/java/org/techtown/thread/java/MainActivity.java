package org.techtown.thread.java;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView textView;

    //int value = 0; 스레드 내부로 위치시킴

    ValueHandler handler = new ValueHandler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = (TextView) findViewById(R.id.textView);

        Button button = (Button)findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                BackgoroundThread thread = new BackgoroundThread();
                thread.start();
            }
        });

        Button button2 = (Button)findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // textView.setText("현재값: "+ value);   직접 버튼을 눌렀을때마다 진행값을 바꾸고 싶을 경우에 적용

            }
        });
    }


    class BackgoroundThread extends Thread{
        int value = 0;
        boolean running = false;
        public void run(){ // 스레드 클래스 생성방법 선택, 클래스 내부에는 런 메소드가 있어야 함
            running = true; // 플래그를 만들어야 이후 중단이 필요할때 사용할 수 있기 때문.
            while(running){

                value +=1;
                // textView.setText("현재값: "+ value); - 직접 메인스레드에 접근해줄 수가 없음. 그러므로 핸들러가 필요함.

                Message message = handler.obtainMessage(); // 메시지 객체 생성
                Bundle bundle = new Bundle(); // 번들 객체 생성, 메시지에 데이터를 지정하기 위함임
                bundle.putInt("value", value);
                message.setData(bundle); // 번들 객체를 셋 데이터해줌
                handler.sendMessage(message);

                try{ // 예외처리가 발생할 수 있으니.
                    Thread.sleep(1000); // 1초동안 쉬는 것
                } catch(Exception e){}


            }
        }
    }

    class ValueHandler extends Handler{
        @Override
        public void handleMessage(Message msg) { // ui직접 접근이 불가하니 핸들러가 메시지를 받을 수 있게 해주는 메시지
            super.handleMessage(msg);

            Bundle bundle = msg.getData();
            int value = bundle.getInt("value");
            textView.setText("현재값: "+ value);

        }
    }

}
