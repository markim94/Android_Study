package org.techtown.samplepush;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import java.util.Map;

//단말이 메시지를 받는
public class MyfirebaseMessagingService extends FirebaseMessagingService { // 라이브러리를 썼으므로 메시지 서비스 상속
    private static final String TAG = "MyMS"; // 로그 태그를 남기기 위해 태그 선언

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);

        Log.d(TAG, "onMessageReceived() 호출됨");
        //화면쪽으로 받은 것을 표현하기
        String from = remoteMessage.getFrom();
        Map<String,String> data = remoteMessage.getData();
        String contents = data.get("contents");

        Log.d(TAG, "from : "+ from + ", contents : "+ contents );

        sendToActivity(getApplicationContext(), from, contents); //화면으로 전달하기 위한 메소드
    }


    public void sendToActivity(Context context, String from, String contents){
        Intent intent = new Intent(context, MainActivity.class);
        intent.putExtra("from", from);
        intent.putExtra("contents", contents);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_SINGLE_TOP|Intent.FLAG_ACTIVITY_CLEAR_TOP);

        context.startActivity(intent);


    }

}
