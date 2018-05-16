package org.techtown.sampleserver;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
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
                ServerThread thread = new ServerThread();
                thread.start();
            }
        });
    }

    class ServerThread extends Thread {
        public void run() {
            int port = 5001;

            try {
                ServerSocket server = new ServerSocket(port); // 요청 대기
                Log.d("ServerThread", "서버가 실행됨");

                while (true) {
                    Socket socket = server.accept(); // 대기하고 있다가 클라이언트가 요청한 정보를 읽어서 확인

                    ObjectInputStream instream = new ObjectInputStream(socket.getInputStream()); // 들어오는 데이터 처리
                    Object input = instream.readObject();
                    Log.d("ServerThread", "input : " + input);

                    ObjectOutputStream outstream = new ObjectOutputStream(socket.getOutputStream());
                    outstream.writeObject(input + "from server.");
                    outstream.flush(); //write를 할 시 버퍼에 남아있을 수 있으므로 flush해주어야 함.
                    Log.d("ServerThread", "Output내보냄");

                    socket.close(); // 더이상 소켓요청이 없으면 닫는다.
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }
}
