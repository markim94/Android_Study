package org.techtown.sampleaudiorecorder;

import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.File;

public class MainActivity extends AppCompatActivity {
    public static String url = "http://sites.google.com/site/ubiaccessmobile/sample_audio.amr"; // 재생할 url 설정

    MediaRecorder recorder;
    MediaPlayer player;
    int position = 0; // 현재 재생의 위치를 찾기 위함임.
    String filename; // sdcard쪽으로 filename을 저장 할 것임.


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        File sdcard = Environment.getExternalStorageDirectory(); // sdcard 폴더 참조
        File file = new File(sdcard, "recorded.mp4");
        filename = file.getAbsolutePath();
        Log.d("MainActivity", "저장할 파일명 " +filename);

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

        Button button5 = (Button) findViewById(R.id.button5);
        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                recordAudio();

            }
        });

        Button button6 = (Button) findViewById(R.id.button6);
        button6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopRecording();

            }
        });


    }

    public void stopRecording() {
        if(recorder!=null){
            recorder.stop(); //녹음 중지
            recorder.release(); //리소스 해제
            recorder=null; //널값 으로 완전 초기화

            Toast.makeText(this, "녹음 중지됨", Toast.LENGTH_SHORT).show();
        }
    }


    public void recordAudio() {
        recorder = new MediaRecorder();//객체생성

        //리코더 설정을 먼저 해준다. 프레임등의 설정

        recorder.setAudioSource(MediaRecorder.AudioSource.MIC); //마이크에서 음성을 받는다
        recorder.setOutputFormat(MediaRecorder.OutputFormat.MPEG_4); // 녹음 확장자 설정
        recorder.setAudioEncoder(MediaRecorder.AudioEncoder.DEFAULT); //인코더 값 설정

        recorder.setOutputFile(filename); // 녹음을 만들 파일

        try {
            recorder.prepare();
            recorder.start();

            Toast.makeText(this, "녹음 시작됨", Toast.LENGTH_SHORT).show();
        } catch (Exception e){
            e.printStackTrace();
        }

    }



    public void playAudio() {
        player = new MediaPlayer(); // 미디어 플레이어 초기화
        try {

            closePlayer(); //계속해서 버튼을 누르면 플레이어가 만들어지므로
            //player.setDataSource(url);
            player.setDataSource(filename); // 외부 파일로 데이터를 바꿈
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
