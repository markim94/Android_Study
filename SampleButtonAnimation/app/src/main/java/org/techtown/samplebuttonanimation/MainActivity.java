package org.techtown.samplebuttonanimation;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {
    TextView textView;
    Animation flowAnim;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = (TextView) findViewById(R.id.textView);
        flowAnim = AnimationUtils.loadAnimation(this,R.anim.flow);
        flowAnim.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                Toast.makeText(getApplicationContext(),"애니메이션 종료됨", Toast.LENGTH_LONG).show(); // 애니메이션이 끝나고 해야할 동작 입력하는 곳

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

    }


    public void onButton1Clicked(View v){
        textView.startAnimation(flowAnim);
    }
}
