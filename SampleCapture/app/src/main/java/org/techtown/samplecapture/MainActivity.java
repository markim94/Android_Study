package org.techtown.samplecapture;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.hardware.Camera;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    ImageView imageView;
    CameraSurfaceView surfaceView;


    //서피스 뷰를 통한 캡처

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageView = (ImageView) findViewById(R.id.imageView);
        surfaceView = (CameraSurfaceView) findViewById(R.id.surfaceView);

        Button button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                capture();
            }
        });
    }


    public void capture() {
        surfaceView.capture(new Camera.PictureCallback() {
            @Override
            public void onPictureTaken(byte[] data, Camera camera) { //사진을 찍으면 메모리로 전달

                BitmapFactory.Options options = new BitmapFactory.Options();
                options.inSampleSize= 8;
                Bitmap bitmap = BitmapFactory.decodeByteArray(data, 0, data.length); // 0부터 데이터 길이까지

                imageView.setImageBitmap(bitmap);

                camera.startPreview();

            }
        });
    }
}
