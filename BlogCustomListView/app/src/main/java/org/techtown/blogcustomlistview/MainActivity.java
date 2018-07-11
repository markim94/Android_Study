package org.techtown.blogcustomlistview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ListViewAdapter adapter;// 클릭리스너에서 사용하기 위해 밖에 꺼냄.
    EditText editText1;
    EditText editText2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView listView = (ListView) findViewById(R.id.listView); //리스트뷰 객체 참조
        editText1 = (EditText) findViewById(R.id.editText1);
        editText2 = (EditText) findViewById(R.id.editText2);

        // 어댑터 지정
        adapter = new ListViewAdapter();

        // items에 item 삽입
        adapter.addItem(new ListItem("홍길동", "010-1234-1234", R.drawable.icon01));
        adapter.addItem(new ListItem("김길동", "010-1234-1234", R.drawable.icon02));
        adapter.addItem(new ListItem("이길동", "010-1234-1234", R.drawable.icon03));
        adapter.addItem(new ListItem("류길동", "010-1234-1234", R.drawable.icon04));
        adapter.addItem(new ListItem("서길동", "010-1234-1234", R.drawable.icon05));

        listView.setAdapter(adapter);


        // 아이템 클릭 리스너
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ListItem item = (ListItem) adapter.getItem(position);
                Toast.makeText(getApplicationContext(), "선택 : "+ item.getName(), Toast.LENGTH_SHORT).show();
            }
        });

        Button button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = editText1.getText().toString();
                String mobile = editText2.getText().toString();

                adapter.addItem(new ListItem(name, mobile, R.drawable.icon01));
                // 리스트뷰를 갱신하여야 하는데 이 메소드가 어댑터에서 리스트뷰쪽으로 갱신하라고 알려줌
                adapter.notifyDataSetChanged();
            }
        });



    }

    class ListViewAdapter extends BaseAdapter {

        ArrayList<ListItem> items = new ArrayList<ListItem>(); // 관리할 데이터
        // 데이터를 담을 수 있는 별도의 객체가 필요. 리스트 아이템이 하나의 문자만 갖고 있는 것이 아니기에 ListItem.class를 따로 설정

        @Override
        public int getCount() {
            // 리스트뷰 데이터가 몇 개인지 먼저 파악.
            return items.size();
        }

        @Override
        public Object getItem(int position) {
            // position에 해당한 items 아이템을 get함
            return items.get(position);
        }

        @Override
        public long getItemId(int position) {
            // 특정 id를 만들어 return해도 됨
            return position;
        }

        // items arraylist에 item add
        public void addItem(ListItem item){
            items.add(item);
        }

        /**
         * 데이터를 관리하는 어댑터가 화면에 보여질 각각의 아이템의 뷰도 만듬
         * 이름이나 전화번호부, 이미지등이 화면에 표시되어지기 위해서 리턴되는 뷰는 레이아웃으로 구성되어야 함.
         * 레이아웃으로 구성되어진 것을 부분화면으로 정의하고 그것을 이용해 객체를 만들어 리턴을 해주는 것이 가장 좋은 방법
         * @param position
         * @param convertView
         * @param parent
         * @return
         */
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ListItemView view = new ListItemView(getApplicationContext());

            // 몇번째 인덱스에 관한 뷰의 정보를 넣어주어야
            // item에 데이터가 들어있으므로 이를 가져와서 뷰에 정해주어야
            ListItem item = items.get(position);
            view.setName(item.getName());
            view.setMobile(item.getMobile());
            view.setImage(item.getResId());

            return view;

        }

    }
}
