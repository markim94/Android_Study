package org.techtown.samplesensor;

import android.app.ListActivity;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import java.util.List;

public class MainActivity extends ListActivity { // 리스트 액티비티로 표현할 것이므로 리스트 액티비티 상속.
    SensorManager manager; // 센서 매니저 객체 생성
    List<Sensor> sensors; // 여러개의 센서 정보가 들어간 리스트


    SensorListAdapter adapter; // 센서들의 정보가 들어간 어댑터 객체

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        manager = (SensorManager) getSystemService(SENSOR_SERVICE); // 센서 매니저 객체 참조
        sensors = manager.getSensorList(Sensor.TYPE_ALL); // 모든 유형의 센서를 리스트형에 리턴

        // 리스트 뷰가 리스트 액티비티에 미리 들어가있으므로 어댑터를 만들면 됨. -> SensorListAdapter 자바 클래스 생성

        adapter = new SensorListAdapter(this, R.layout.sensor_item, sensors);
        setListAdapter(adapter);

    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);

        Sensor sensor = sensors.get(position);
        String sensorName = sensor.getName();

        Intent intent = new Intent (this, DataActivity.class);
        intent.putExtra("SensorIntent", position);
        intent.putExtra("SensorName", sensorName);
        startActivity(intent);
    }
}
