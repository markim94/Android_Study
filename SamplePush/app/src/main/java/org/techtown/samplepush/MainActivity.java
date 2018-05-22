package org.techtown.samplepush;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.firebase.iid.FirebaseInstanceId;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    TextView logOutput;
    EditText dataInput;
    TextView dataOutput;
    String regId; //토큰 정보를 담을 스트링

    RequestQueue queue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        logOutput = (TextView) findViewById(R.id.logOutput);
        dataInput = (EditText) findViewById(R.id.dataInput); // dataInput을 파이어베이스 fcm으로 보내야함
        dataOutput = (TextView) findViewById(R.id.dataOutput);

        getRestrationId(); // 1단계 과정  : id 등록을 위한 메소드

        Button button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String data = dataInput.getText().toString().trim();
                send(data); // 별도의 send메소드 생성
            }
        });


        queue = Volley.newRequestQueue(getApplicationContext()); // 새로운 큐 객체, 이곳에 리퀘스트 객체를 넣어주어함.

        Intent passedIntent = getIntent(); //서비스에서 보낸 인텐트
        processIntent(passedIntent);

    }

    public void processIntent(Intent intent){  //
        if(intent!= null){
            String from = intent.getStringExtra("from");
            String contents = intent.getStringExtra("contents");

            println("DATA : "+ from + ", " + contents);
            dataOutput.setText("DATA : " + contents);

        }
    }

    @Override
    protected void onNewIntent(Intent intent) { // 메인액티비티가 떠있지 않을때 서비스, 새로운 인텐트 메소드
        super.onNewIntent(intent);

        processIntent(intent);
    }

    public void getRestrationId(){
        regId = FirebaseInstanceId.getInstance().getToken(); // 토큰 정보, 등록아이디를 받아오는 것
        println("등록 ID -> " + regId); // id등록을 확인하기 위해 3번째 레이아웃 텍스트뷰에 보여주기.
        // 실제적인 보내는 단말쪽으로 이 id를 보내주어야 함.

    }

    public void send(String input){ // input메시지를 fcm으로 보내기 위한 메소드,
        JSONObject requestData = new JSONObject();

        try{
            requestData.put("priorty", "high"); //우선순위를 높게 설정

            JSONObject dataObj = new JSONObject(); // 다시 한번 json 객체 생성 - 포맷을 따라야 하기 때문.
            dataObj.put("contents", input);
            requestData.put("data",dataObj);

            JSONArray idArray = new JSONArray();
            idArray.put(0, regId); // 0번 인덱스에 수신자의 등록아이디
            requestData.put("registration_ids",idArray);

        } catch (Exception e) {e.printStackTrace();}

        sendData(requestData, new SendResponseListener() {
            @Override
            public void onRequestStarted() {
                println("onRequestStarted() 호출됨");
            }

            @Override
            public void onRequestCompleted() {
                println("onRequestCompleted() 호출됨");
            }

            @Override
            public void onRequestError(VolleyError error) {
                println("onRequestError() 호출됨");
            }
        });
    }


    public interface SendResponseListener{
        public void onRequestStarted();
        public void onRequestCompleted();
        public void onRequestError(VolleyError error);
    }


    public void sendData(JSONObject requestData, final SendResponseListener listener) {  //전송을 하기 위한 메소드, volley 라이브러리 사용, 별도의 리스너로 결과 전달 - 효율적 사용을 위해


        JsonObjectRequest request = new JsonObjectRequest( // 볼리는 요청객체를 만들고 큐에 넣으면 알아서 전달하게 됨.
                Request.Method.POST,                    // 포스트 방식으로
                "https://fcm.googleapis.com/fcm/send",   // url, 웹서버쪽의 주소
                requestData,                                // 3번째 파라미터
                new Response.Listener<JSONObject>() {       // 4번째 파라미터 정상

                    @Override
                    public void onResponse(JSONObject response) {
                        listener.onRequestCompleted();
                    }
                },
                new Response.ErrorListener() {              //5번째 파라미터 에러가 발생하면 이곳으로
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        listener.onRequestError(error);

                    }
                }

        ){
            @Override // 아래 메소드들을 요청객체에 추가적으로 더 정의.
            public String getBodyContentType() {
                return "application/json"; // json타입으로 주고받겠다는 것.
            }

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {  //헤더정보를 넣어서 보내는
                Map<String,String> headers = new HashMap<String,String>();
                headers.put("Authorization", "firebase에서 설정 키값을 따오기"); //실제 키가 헤더에 포함되어 전달.

                return headers;
            }

            @Override
            protected Map<String, String> getParams() throws AuthFailureError { // 요청파라미터
                Map<String,String> params = new HashMap<String,String>();
                return params;
            }
        };

        request.setShouldCache(false); // 한번 요청된 것을 저장하는지를
        listener.onRequestStarted(); // 시작을 알림.
        queue.add(request); // 리퀘스트 객체를 큐에 넣음.
    }


    public void println(String data){
        logOutput.append(data + "\n"); // 3번째 레이아웃에 텍스트뷰에 append
    }
}
