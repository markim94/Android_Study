package org.techtown.a001hello;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import static android.content.Intent.ACTION_VIEW;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    public void onButton1Clicked(View v) {
        Intent myIntent = new Intent(ACTION_VIEW, Uri.parse("https://m.naver.com"));
        startActivity(myIntent);
    }

    public void onButton2Clicked(View v) {
        Intent myIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("tel:010-1000-1000"));
        startActivity(myIntent);
    }

    public void onButton3Clicked(View v) {
        Intent Intent = new Intent(getApplicationContext(), Menuactivity.class);
        startActivity(Intent);
    }


}
