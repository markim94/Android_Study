package org.techtown.samplepush;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

public class MyFirebaseInstanceIDService extends FirebaseInstanceIdService {
    private static final String TAG = "MyID";

    @Override
    public void onTokenRefresh() {
        super.onTokenRefresh(); // token은 등록 id를 말하는 것임. 갱신되는 것.

        String token = FirebaseInstanceId.getInstance().getToken();

        /*

        이 토큰정보를 상대방에게 넘기기 위한 코드가 들어갈 부분.

         */

        Log.d(TAG, "onTokenRefresh() 호출됨");
    }
}
