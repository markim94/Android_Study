package org.techtown.sampleservice;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editText = (EditText) findViewById(R.id.edittext);

        Intent passedIntent = getIntent();
        processIntent(passedIntent);

    }

    @Override
    protected void onNewIntent(Intent intent) {
        processIntent(intent);


        super.onNewIntent(intent);
    }

    private void processIntent(Intent intent){
        if (intent != null){
            String command = intent.getStringExtra("commnand");
            String name = intent.getStringExtra("name");

            Toast.makeText(this, "command :" + command + ", name : " + name, Toast.LENGTH_LONG).show();

        }
    }


    public void onButton1Clicked(View v){
        String name = editText.getText().toString();
        Intent intent = new Intent(this, MyService.class);
        intent.putExtra("command","show");
        intent.putExtra("name",name);
        startService(intent);
    }


}
