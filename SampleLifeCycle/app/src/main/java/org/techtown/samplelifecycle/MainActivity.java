package org.techtown.samplelifecycle;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText nameInput;
    public static final int REQUEST_CODE_MENU=101;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        EditText editText = (EditText) findViewById(R.id.editText);

        Toast.makeText(getApplicationContext(),"onCreate 호출", Toast.LENGTH_LONG).show();

        TextView textView = (TextView) findViewById(R.id.textview);
        Button button = (Button) findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MenuActivity.class);
                startActivityForResult(intent, REQUEST_CODE_MENU);
            }
        });




    }

    @Override
    protected void onStart() {
        super.onStart();

        Toast.makeText(getApplicationContext(),"onStart 호출",Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onStop() {
        super.onStop();

        Toast.makeText(getApplicationContext(),"onStop 호출",Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        Toast.makeText(getApplicationContext(),"onDestroy 호출",Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onPause() {
        super.onPause();

        Toast.makeText(getApplicationContext(),"onPause 호출",Toast.LENGTH_LONG).show();
        saveState();
    }


    @Override
    protected void onResume() {
        super.onResume();

        Toast.makeText(getApplicationContext(),"onResume 호출",Toast.LENGTH_LONG).show();
        restoreState();
    }

    protected void restoreState(){
        SharedPreferences pref =getSharedPreferences("pref", Activity.MODE_PRIVATE);
        if((pref!=null)&&(pref.contains("name"))){
            String name = pref.getString("name","");
            nameInput.setText(name);
        }
    }

    protected void saveState(){
        SharedPreferences pref = getSharedPreferences("pref", Activity.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putString("name",nameInput.getText().toString());
        editor.commit();
    }

    protected void clearMyPrefs(){
        SharedPreferences pref = getSharedPreferences("pref", Activity.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.clear();
        editor.commit();
    }
}
