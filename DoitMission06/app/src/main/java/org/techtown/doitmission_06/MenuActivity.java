package org.techtown.doitmission_06;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MenuActivity extends AppCompatActivity {
    public static final int REQUEST_CODE_SERVE_1 = 201;
    public static final int REQUEST_CODE_SERVE_2 = 202;
    public static final int REQUEST_CODE_SERVE_3 = 203;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
    }

    public void onButton2Clicked(View v){
        Intent intent = new Intent(getApplicationContext(), serve01Activity.class);
        startActivityForResult(intent, REQUEST_CODE_SERVE_1);

        finish();
    }


    public void onButton3Clicked(View v){
        Intent newIntent = new Intent(getApplicationContext(), serve02Activity.class);
        startActivityForResult(newIntent, REQUEST_CODE_SERVE_2);

        finish();
    }


    public void onButton4Clicked(View v){
        Intent newnewIntent = new Intent(getApplicationContext(), serve03Activity.class);
        startActivityForResult(newnewIntent, REQUEST_CODE_SERVE_3);

        finish();
    }


}
