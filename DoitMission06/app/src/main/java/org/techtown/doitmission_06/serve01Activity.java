package org.techtown.doitmission_06;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class serve01Activity extends AppCompatActivity {
    public static final int REQUEST_CODE_MAIN_01 = 301;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_serve01);
    }

    public void onButton1Clicked(View v){
        Intent intent = new Intent();
        setResult(RESULT_OK, intent);

        finish();

    }

    public void onButton2Clicked(View v){
        Intent loginIntent = new Intent(getApplicationContext(), MainActivity.class);
        startActivityForResult(loginIntent, REQUEST_CODE_MAIN_01);
    }

}
