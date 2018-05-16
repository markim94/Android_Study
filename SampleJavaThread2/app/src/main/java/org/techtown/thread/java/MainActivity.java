package org.techtown.thread.java;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

// 핸들러를 상속해서 일일이 처리하는 방식은 복잡하니 핸들러 객체를 통한 방법으로 스레드를 적용.

public class MainActivity extends AppCompatActivity {
    TextView textView;

    //int value = 0; 스레드 내부로 위치시킴

    Handler handler2 = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = (TextView) findViewById(R.id.textView);

        Button button = (Button)findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //BackgoroundThread thread = new BackgoroundThread(); 스레드 상속방법
                //thread.start();
                new Thread(new Runnable() { // 스레드를 직접
                    int value = 0;
                    boolean running = false;
                    @Override
                    public void run() {
                        running = true; // 플래그를 만들어야 이후 중단이 필요할때 사용할 수 있기 때문.
                        while (running) {

                            value += 1;

                            handler2.post(new Runnable() { // post: 던지다, runnable객체를 던짐
                                @Override
                                public void run() { // 마찬가지로 post메소드도 런을 갖고 있음
                                    textView.setText("현재값 : " + value);
                                    //핸들러 내부에 있는 것이므로 객체접근가능.
                                }
                            });

                            try { // 예외처리가 발생할 수 있으니.
                                Thread.sleep(1000); // 1초동안 쉬는 것
                            } catch (Exception e) {
                            }


                        }
                    }
                }).start();
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
