package org.techtown.samplereceiver;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class SmsActivity extends AppCompatActivity {
    EditText editText;
    EditText editText2;
    EditText editText3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sms);

        editText = (EditText) findViewById(R.id.editText);
        editText2 = (EditText) findViewById(R.id.editText2);
        editText3 = (EditText)findViewById(R.id.editText3);
        Button button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        Intent passedIntent = getIntent();
        processIntent(passedIntent);
    }

    protected void onNewIntent(Intent intent) {
        processIntent(intent);

        super.onNewIntent(intent);
    }

    private void processIntent(Intent intent){
        if(intent != null){
            String sender = intent.getStringExtra("sender");
            String content = intent.getStringExtra("content");
            String receivedDate = intent.getStringExtra("receivedDate");

            editText.setText(sender);
            editText2.setText(content);
            editText3.setText(receivedDate);
        }
    }


}
