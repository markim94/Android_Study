package org.techtown.samplelistview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    EditText editText;
    ListView listView;
    SingerAdapter adapter;

    class SingerAdapter extends BaseAdapter{
        ArrayList<SingerItemView.SingerItem> items = new ArrayList<SingerItemView.SingerItem>();

        @Override
        public int getCount() {
            return items.size();
        }

        public void addItem(SingerItemView.SingerItem item){
            items.add(item);
        }

        @Override
        public Object getItem(int position) {
            return items.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            SingerItemView view = new SingerItemView(getApplicationContext());
            SingerItemView.SingerItem item = items.get(position);
            view.setName(item.getName());
            view.setMobile(item.getMobile());
            view.setAge(item.getAge());
            view.setImage(item.getResId());

            return view;

        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = (ListView) findViewById(R.id.listView);

        adapter = new SingerAdapter();

        adapter.addItem(new SingerItemView.SingerItem("소녀시대", "010-1000-1000", 20, R.drawable.singer));
        adapter.addItem(new SingerItemView.SingerItem("걸스데이", "010-1000-1000", 22, R.drawable.singer2));
        adapter.addItem(new SingerItemView.SingerItem("여자친구", "010-1000-1000", 21, R.drawable.singer3));
        adapter.addItem(new SingerItemView.SingerItem("티아라", "010-1000-1000", 24, R.drawable.singer4));
        adapter.addItem(new SingerItemView.SingerItem("AOA", "010-1000-1000", 25, R.drawable.singer5));

        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                SingerItemView.SingerItem item =(SingerItemView.SingerItem) adapter.getItem(position);
                Toast.makeText(getApplicationContext(),"선택 : " + item.getName(),Toast.LENGTH_LONG).show();
            }
        });
    }
}
