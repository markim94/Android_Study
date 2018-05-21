package org.techtown.samplelocation;

import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = (TextView) findViewById(R.id.textView2);
        Button button = (Button) findViewById(R.id.button2);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startLocationService();
            }
        });
    }

    public void startLocationService(){
        long minTime = 10000; // 위치정보를 너무 많이 전달하면 성능에 문제가 생기므로 최소 10초단위로 위치 정보 전달
        float minDistance = 0; // 조금 이동하여도 정보를 전달한다면 자주 정보를 주므로 최소 이동거리 움직이면 위치정보를 업데이트 시킬 수 있도록.
        LocationManager manager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        manager.requestLocationUpdates(
                LocationManager.GPS_PROVIDER, // network가 아닌 gps 이용해서 위치확인
                minTime,
                minDistance,
                new LocationListener() {
                    @Override
                    public void onLocationChanged(Location location) {
                        Double latitude = location.getLatitude();   // 위도
                        Double longitude = location.getLongitude(); // 경도

                        textView.setText("내 위치 : "+latitude+", "+longitude);
                    }

                    @Override
                    public void onStatusChanged(String provider, int status, Bundle extras) {

                    }

                    @Override
                    public void onProviderEnabled(String provider) {

                    }

                    @Override
                    public void onProviderDisabled(String provider) {

                    }
                });
    }
}
