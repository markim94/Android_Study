package org.techtown.doitmission_06;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    public static final int REQUEST_CODE_MENU = 101;
    EditText edittext1;
    EditText edittext2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edittext1 = (EditText) findViewById(R.id.edittext1);
        edittext2 = (EditText) findViewById(R.id.edittext2);
        Button button1 = (Button) findViewById(R.id.button1);

    }


    public void onButton1Clicked(View v) {
        if (edittext1.getText().toString().replace(" ","").equals("") || edittext2.getText().toString().replace(" ","").equals("")) {
            Toast.makeText(getApplicationContext(), "입력칸을 채워주세요", Toast.LENGTH_LONG).show();
        } else {
            Intent intent = new Intent(getApplicationContext(), MenuActivity.class);
            startActivityForResult(intent, REQUEST_CODE_MENU);
        }
    }
}
