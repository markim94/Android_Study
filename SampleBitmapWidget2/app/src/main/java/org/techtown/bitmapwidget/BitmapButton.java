package org.techtown.bitmapwidget;


import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v7.widget.AppCompatButton;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * Created by kimjunhyeong on 2018. 5. 4..
 */

public class BitmapButton extends AppCompatButton {
    int iconNormal = R.drawable.bitmap_button_normal;
    int iconClicked = R.drawable.bitmap_button_clicked; // 아이콘 리소스 정의

    int iconStatus = STATUS_NORMAR;
    public static int STATUS_NORMAR = 0;
    public static int STATUS_CLICKED = 1; //아이콘 상태의 정의

    public BitmapButton(Context context){
        super(context);

        init();
    }

    public BitmapButton(Context context, AttributeSet atts){
        super(context, atts);

        init();
    }


    private void init() {
        setBackgroundResource(iconNormal);

        int defaultTextColor = Color.WHITE;
        float defaultTextSize = getResources().getDimension(R.dimen.text_size); // dp설정을 위해 dimen이용 ->value/dimens.xml
        Typeface defaultTypeface = Typeface.DEFAULT_BOLD;

        setTextColor(defaultTextColor);
        setTextSize(defaultTextSize);
        setTypeface(defaultTypeface);
    }

    public void setIcon(int iconNormal, int iconClicked){
        this.iconNormal = iconNormal;
        this.iconClicked = iconClicked;  // 아이콘 리소스 설정
    }

    public boolean onTouchEvent(MotionEvent event){
        super.onTouchEvent(event);

        int action = event.getAction();

        switch(action){
            case MotionEvent.ACTION_DOWN:
                setBackgroundResource(this.iconClicked);

                iconStatus = STATUS_CLICKED;

                break;

                case MotionEvent.ACTION_OUTSIDE:
                case MotionEvent.ACTION_CANCEL:
                case MotionEvent.ACTION_UP:
                    setBackgroundResource(this.iconNormal);

                    iconStatus = STATUS_NORMAR;

                    break;
        }

        invalidate();

        return true;

    }



}
