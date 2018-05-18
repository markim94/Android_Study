package org.techtown.samplecaptureintent;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.io.File;

public class MainActivity extends AppCompatActivity {
    ImageView imageView;
    File file;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        File sdcard = Environment.getExternalStorageDirectory();
        file = new File(sdcard, "capture.jpg"); //확장자 jpg

        imageView = (ImageView) findViewById(R.id.imageView);

        Button button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                capture();
            }
        });
    }

    public void capture() {



        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE); //미리 인텐트에 정의된 액션정보로 인텐트를 만들고 시스템에 요청 -> 사진을 찍도록 띄워준다.
        intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(file)); //부가 데이터 삽입,  단말에서 어떤 파일의 확장자로 저장할 것인지 지정

        //혹시 카메라 앱이 없을수 있으니 확인이 필요하다면 체크해주어야 함.

        startActivityForResult(intent, 101); //인텐트 시작
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) { //인텐트 응답
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode ==101 && resultCode == Activity.RESULT_OK){
            //이미지 뷰로 보여줄때 파일을 그대로 이미지뷰에 보여주면 앱의 메모리가 부족할 수 있음. 사진 용량이 크므로, 그래서 비트맵 객체로 로딩하고 설정하는 것이 좋음

            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inSampleSize = 8; //8사이즈로 조정
            Bitmap bitmap = BitmapFactory.decodeFile(file.getAbsolutePath(), options);

            imageView.setImageBitmap(bitmap);

        }
    }
}
