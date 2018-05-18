package org.techtown.samplevideoplayer;

import android.media.MediaPlayer;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.Toast;
import android.widget.VideoView;

import java.net.URI;

public class MainActivity extends AppCompatActivity {
    VideoView videoView;
    public static String url = "http://sites.google.com/site/ubiaccessmobile/sample_video.mp4";




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        videoView = (VideoView) findViewById(R.id.videoView);

        MediaController controller = new MediaController(this);
        videoView.setMediaController(controller); // 컨트롤러는 재생, 일시정지를 말하는 것임
        videoView.setVideoURI(Uri.parse(url)); // 비디오 url 지정
        videoView.requestFocus(); //파일에 대한 정보를 갖고옴

        videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                Toast.makeText(getApplicationContext(), "동영상 준비됨",Toast.LENGTH_SHORT).show();
            }

        });



        Button button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                videoView.seekTo(0); //처음위치로 조정
                videoView.start(); //동영상 시작

            }
        });
    }
}
