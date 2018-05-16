package org.techtown.samplesocket;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClientThread thread = new ClientThread();
                thread.start();
            }
        });
    }

    class ClientThread extends Thread{
        public void run(){

            String host = "localhost"; //자신의 호스트는 로컬호스트
            int port = 5001; // 서버와 동일한 포트

            try {
                Socket socket = new Socket(host, port);

                ObjectOutputStream outstream = new ObjectOutputStream(socket.getOutputStream()); // 클라이언트는 보내고 받기 서버는 반대로 받고 보내기
                outstream.writeObject("안녕!");
                outstream.flush();
                Log.d("ClientThread", "서버로 보냄");

                ObjectInputStream instream = new ObjectInputStream(socket.getInputStream());
                String input = (String) instream.readObject();
                Log.d("ClientThread", "받은 데이터 : "+ input);


            } catch (Exception e) {e.printStackTrace(); }

        }
    }
}
