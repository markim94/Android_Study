package org.techtown.samplepagesliding;

import android.support.v4.widget.SlidingPaneLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {
    boolean isPageOpen = false;

    Animation translateLeftAnim;
    Animation translateRightAnim;
    LinearLayout page;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        page = (LinearLayout) findViewById(R.id.page); // 슬라이딩 메뉴 객체

        translateLeftAnim = AnimationUtils.loadAnimation(this,R.anim.translate_left);
        translateRightAnim = AnimationUtils.loadAnimation(this, R.anim.translate_right);

        SlidingPageAnimationListener animListener = new SlidingPageAnimationListener();
        translateLeftAnim.setAnimationListener(animListener);
        translateRightAnim.setAnimationListener(animListener);

    }
    public void onButton1Clicked(View v){
        if(isPageOpen){
            page.startAnimation(translateRightAnim);
        } else{
            page.setVisibility(View.VISIBLE);
            page.startAnimation(translateLeftAnim);
        }
    }


    private class SlidingPageAnimationListener implements Animation.AnimationListener{
        @Override
        public void onAnimationStart(Animation animation) {

        }
        public void onAnimationEnd(Animation animation){
            if(isPageOpen){
                page.setVisibility(View.INVISIBLE);

                button.setText("open");
                isPageOpen=false;
            } else {
                button.setText("close");
                isPageOpen=true;
            }
        }

        @Override
        public void onAnimationRepeat(Animation animation) {

        }
    }

}

