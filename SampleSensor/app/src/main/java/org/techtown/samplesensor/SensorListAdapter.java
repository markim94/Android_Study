package org.techtown.samplesensor;

import android.content.Context;
import android.hardware.Sensor;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class SensorListAdapter extends ArrayAdapter<Sensor> {
    Context context;
    List<Sensor> items;

    public SensorListAdapter(@NonNull Context context, int resource, @NonNull List objects) {
        super(context, resource, objects);

        this.context = context;
        this.items = objects;
    }

    public int getCount() {
        return ((items !=null) ? items.size():0); // items가 널이면 0, 아니라면 사이즈 리턴
    }

    public long getItemId(int position){
        return position;
    }


    // sensorlistAdapter가 이미 super로 context, resource, objects를 접근하므로 getView만 오버라이딩 해주어도 됨.

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = null;

        if(convertView == null){ // 재활용 뷰
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.sensor_item, null);
        } else {
            view = (convertView); // 똑같은 뷰타입이므로 캐스팅할 필요가 없음.
        }

        Sensor sensor = items.get(position); // 현재 센서


        // sensor_item.xml을 객체화 시켜 뷰에 지정했으므로 뷰는 리니어 레이아웃이 됨.
        TextView textView = view.findViewById(R.id.textView);
        TextView textView2 = view.findViewById(R.id.textView2);
        TextView textView3 = view.findViewById(R.id.textView3);

        String sensorName = sensor.getName();
        String sensorVendor = sensor.getVendor();
        int sensorVersion = sensor.getVersion();

        textView.setText("센서명 : " + sensorName);
        textView2.setText("센서제조사 : " + sensorVendor);
        textView3.setText("센서버전 : " + sensorVersion);

        return view;
    }
}
