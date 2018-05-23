package org.techtown.samplenfc;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*
        태그 인식을 위해 띄워놓은 액티비티가 1순위
        action_ndef_discovered 액션을 인텐트 필터로 등록한 액티비티 2순위
        action_tech_discovered 3순위


       - 인텐트 필터 3종류

       - nfc 태그 데이터 구성
       NFC태그 안에 NdefMessage. NdefMessage 내부에 NdefRecord가 n개 속해있음.

       - 태그에 writer를 이용해서 데이터를 집어넣음.


         */


    }
}
