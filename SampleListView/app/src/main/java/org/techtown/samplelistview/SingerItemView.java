package org.techtown.samplelistview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by kimjunhyeong on 2018. 5. 5..
 */

public class SingerItemView extends LinearLayout {
    TextView textView;
    TextView textView2;
    TextView textView3;
    ImageView imageView;

    public SingerItemView(Context context){ // 생성자는 두개, init으로 초기화
        super(context);
        init(context);
    }

    public SingerItemView(Context context, AttributeSet attrs){
        super(context,attrs);
        init(context);
    }

    public void init(Context context){ // 설정
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.singer_item, this, true); // xml파일 인플레이터

        textView = (TextView) findViewById(R.id.textView);
        textView2 = (TextView) findViewById(R.id.textView2);
        textView3 = (TextView) findViewById(R.id.textView3);
        imageView = (ImageView) findViewById(R.id.imageView);
    }

    public void setName(String name){
        textView.setText(name);
    }

    public void setMobile(String mobile){
        textView2.setText(mobile);
    }

    public void setAge(int age){
        textView3.setText(String.valueOf(age));
    }

    public void setImage(int resId){
        imageView.setImageResource(resId);
    }

    public static class SingerItem{
        String name;
        String mobile;
        int age;
        int resId;

        public SingerItem(String name, String mobile){
            this.name = name;
            this.mobile = mobile;
        }

        public SingerItem(String name, String mobile, int age, int resId){
            this.name = name;
            this.mobile = mobile;
            this.age = age;
            this.resId = resId;
        }

        public int getAge(){
            return age;
        }

        public void setAge(int age){
            this.age = age;
        }

        public int getResId(){
            return resId;
        }

        public void setResId(){
            this.resId = resId;
        }

        public String getMobile(){
            return mobile;
        }

        public void setMobile(String mobile){
            this.mobile = mobile;
        }

        public String getName(){
            return name;
        }

        public void setName(String name){
            this.name = name;
        }

    }

}
