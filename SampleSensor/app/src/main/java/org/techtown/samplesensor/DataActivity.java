package org.techtown.samplesensor;

import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.util.List;

public class DataActivity extends AppCompatActivity implements SensorEventListener{
    TextView textView;
    TextView textView2;
    TextView textView3;

    SensorManager manager;
    List<Sensor> sensors;

    int sensorIndex = 0;
    String sensorName = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data);

        textView = findViewById(R.id.textView);
        textView2 = findViewById(R.id.textView2);
        textView3 = findViewById(R.id.textView3);

        manager = (SensorManager) getSystemService(SENSOR_SERVICE);
        sensors = manager.getSensorList(Sensor.TYPE_ALL);

        Intent passedIntent = getIntent();
        processCommand(passedIntent);
    }


    public void processCommand(Intent intent){
        if (intent!=null){
            sensorIndex = intent.getIntExtra("SensorIndex", 0);
            sensorName = intent.getStringExtra("SensorName");
            textView.setText(sensorName);
        }
    }


    @Override
    protected void onPause() {
        super.onPause();

        manager.unregisterListener(this);
    }

    @Override
    protected void onResume() { //화면이 보이기 전에 리스너쪽으로 등록
        super.onResume();

        manager.registerListener(this, sensors.get(sensorIndex), SensorManager.SENSOR_DELAY_UI);

    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        String timestamp = "Sensor Timestamp -> " + event.timestamp;
        textView2.setText(timestamp);

        String sensorValue = event.values[0] + ", " + event.values[1] + ", " + event.values[2];
        textView3.setText(sensorValue);
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}
