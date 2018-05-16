package org.techtown.thread.delayed;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

public class MainActivity extends AppCompatActivity {
    ProgressBar progressBar;

    Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        Button button = (Button) findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        ProgressThread thread = new ProgressThread();
                        thread.start();
                    }
                }, 5000);  // 핸들러를 통해 5초 후에 진행, 즉 핸들러로 지연이 가능하다.

                //ProgressThread thread = new ProgressThread();
                //thread.start();

            }
        });
    }

    class ProgressThread extends Thread{ // new Thread가 아닌 별도의 클래스로 스레드 생성
        int value = 0;

        public void run(){
            while(true){
                if(value >100) {
                    break;
                }

                value +=1;

                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        progressBar.setProgress(value);
                    }
                });

                try {
                    Thread.sleep(500);
                } catch(Exception e){}
            }

        }
    }
}
