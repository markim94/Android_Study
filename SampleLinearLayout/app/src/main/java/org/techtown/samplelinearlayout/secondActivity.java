package org.techtown.samplelinearlayout;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class secondActivity extends AppCompatActivity {

    // 1일 경우 이미지 1번이 보여지는 상태, 2일 경우 2번이 보여지는 상태로 정의
    int statusImage = 1;

    Button btnChangeImage;
    ImageView imageView1;
    ImageView imageView2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        // 중첩된 이미지뷰의 이미지의 visibility 속성을 바꿀 버튼 객체참조
        btnChangeImage = (Button) findViewById(R.id.btn_ChangeImage);

        // 프레임 레이아웃에 위치한 이미지뷰 객체 참조
        imageView1 = (ImageView) findViewById(R.id.imageView1);
        imageView2 = (ImageView) findViewById(R.id.imageView2);

        // 버튼 클릭리스너
        btnChangeImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 1번이 보여지는 상태이면, 1번: invisible, 2번: visible로 변경
                if(statusImage==1){
                    imageView1.setVisibility(View.INVISIBLE);
                    imageView2.setVisibility(View.VISIBLE);
                    statusImage = 2;
                // else의 경우 2번이 보여지는 상태이므로 1번: visible, 2번: invisible로 변경
                }else {
                    imageView2.setVisibility(View.INVISIBLE);
                    imageView1.setVisibility(View.VISIBLE);
                    statusImage = 1;
                    imageView1.setImageResource();
                }
            }
        });
    }
}
