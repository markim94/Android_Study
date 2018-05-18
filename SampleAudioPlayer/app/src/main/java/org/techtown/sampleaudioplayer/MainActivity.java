package org.techtown.sampleaudioplayer;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

// 멀티미디어 기능은 에뮬레이터가 아닌 실제 단말로 해야 함. 에뮬레이터에서 기능을 제공하지 않을 수 있을 가능성이 큼.
public class MainActivity extends AppCompatActivity {
    public static String url = "http://sites.google.com/site/ubiaccessmobile/sample_audio.amr"; // 재생할 url 설정


    MediaPlayer player;
    int position = 0; // 현재 재생의 위치를 찾기 위함임.


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playAudio();

            }
        });

        Button button2 = (Button) findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pauseAudio();

            }
        });

        Button button3 = (Button) findViewById(R.id.button3);
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resumeAudio();

            }
        });

        Button button4 = (Button) findViewById(R.id.button4);
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopAudio();

            }
        });
    }

    public void playAudio() {
        player = new MediaPlayer(); // 미디어 플레이어 초기화
        try {

            closePlayer(); //계속해서 버튼을 누르면 플레이어가 만들어지므로
            player.setDataSource(url);
            player.prepare();
            player.start();

            Toast.makeText(this, "재생 시작됨.", Toast.LENGTH_SHORT).show();

        } catch (Exception e) {
            e.printStackTrace(); // 에러가 나면 로그에 표시
        }

    }

    public void closePlayer() {
        if(player!=null){
            player.release();
            player = null;
        }

    }

    public void pauseAudio() {
        if(player!=null){
            position = player.getCurrentPosition(); //어디까지 플레이가 됐는지 포지션으로 지정
            player.pause(); //일시정지
            Toast.makeText(this, "일시정지 됨.", Toast.LENGTH_SHORT).show();
        }
    }


    public void resumeAudio() {
        if(player!=null && !player.isPlaying()){
            player.seekTo(position); // 포지션 위치로 지정해서 재개
            player.start();

            Toast.makeText(this, "재시작 됨.", Toast.LENGTH_SHORT).show();
        }
    }

    public void stopAudio(){
        if(player!=null&& player.isPlaying()){
            player.stop();

            Toast.makeText(this, "중지 됨.", Toast.LENGTH_SHORT).show();
        }
    }

}


