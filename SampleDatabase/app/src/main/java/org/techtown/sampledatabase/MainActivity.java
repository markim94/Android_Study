package org.techtown.sampledatabase;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    EditText editText;
    EditText editText2;
    EditText editText3;
    EditText editText4;
    EditText editText5;
    TextView textView;
    SQLiteDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button = (Button) findViewById(R.id.button);
        Button button2 = (Button) findViewById(R.id.button2);
        Button button3 = (Button) findViewById(R.id.button3);
        Button button4 = (Button) findViewById(R.id.button4);
        editText = (EditText)findViewById(R.id.editText);
        editText2 = (EditText)findViewById(R.id.editText2);
        editText3 = (EditText)findViewById(R.id.editText3);
        editText4 = (EditText)findViewById(R.id.editText4);
        editText5 = (EditText)findViewById(R.id.editText5);
        textView = (TextView) findViewById(R.id.textView);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String databaseName = editText.getText().toString();
                openDatabase(databaseName);   //데이터베이스 실행
            }
        });



        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //먼저 데이터베이스를 실행 (1번 과정을) 해주어야 함.
                String tableName = editText2.getText().toString();
                createTable(tableName);
            }
        });

        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = editText3.getText().toString().trim();
                String ageStr = editText4.getText().toString().trim(); //숫자로 바꾸어야 하므로 Str을 붙인 변수로 착오를 없애기. trim은 공백을 없애는 것 공백이 없어야 int형 전환에서 에러가 발생하지 않음
                String mobile = editText5.getText().toString().trim();


                int age = -1;
                try {
                    age = Integer.parseInt(ageStr); // int형으로 바꾸기
                } catch (Exception e) {}

                insertData(name, age, mobile);

            }
        });

        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String tableName = editText2.getText().toString();
                selectData(tableName);


            }
        });

    }


    public void openDatabase(String databaseName){
        println("openDatabase() 호출됨.");


        /*database = openOrCreateDatabase(databaseName, MODE_PRIVATE, null); // db생성 이름, 모드, null

        if (database != null) {
            println("데이터 베이스 오픈됨.");
        }
        */
        DatabaseHelper helper = new DatabaseHelper(this, databaseName, null, 2); // 헬퍼 객체 생성 버전 2로 표기되어있으므로 버전이 업그레이드가 바뀌었음을 알 수 있음
        database = helper.getWritableDatabase(); // 데이터베이스를 쓸수있는 권한


    }

    public void createTable(String tableName){
        println("createTable() 호출됨");

        if (database !=null) {
            // db가 먼저 생성되어야 하므로 체크하는 것이 좋음.
            String sql = "create table " + tableName + "(_id integer PRIMARY KEY autoincrement, name text, age integer, mobile text)" ; // sql문 작성
            database.execSQL(sql); //결과값을 받지 않으려면 execSQL메소드 사용
            println("테이블 생성됨.");

        } else {
            println("먼저 데이터베이스를 오픈하세요.");
        }
    }


    public void insertData(String name, int age, String mobile){
        println("insertData() 호출됨.");

        if(database !=null){

            String sql = "insert into customer(name, age, mobile) values(?, ?, ?)"; // ?는 추후에 따로 지정가능
            Object[] params = {name, age, mobile}; // 3개의 배열이 들어간 객체 생성
            database.execSQL(sql, params); // ?를 params로 대체함

            println("데이터 추가함");
        } else {
            println("먼저 데이터베이스를 오픈하세요.");
        }
    }

    public void selectData(String tableName){
        println("selectData() 호출됨");

        if(database !=null){
            String sql = "select name, age, mobile from " + tableName;


            Cursor cursor = database.rawQuery(sql, null); // 조회이므로 return값이 있어야함 rawQuery 사용
            println("조회 된 데이터 갯수 : " + cursor.getCount());

            for (int i = 0; i<cursor.getCount(); i++){
                cursor.moveToNext(); // 다음 레코드로 넘어가기 위함
                String name = cursor.getString(0); // 0은 첫번째 칼럼
                int age = cursor.getInt(1);
                String mobile = cursor.getString(2);

                println("@" + i + " -> " + name + ", " + age +", "+ mobile);
            }

            cursor.close(); // 실제 자원이 한정되어 있으므로
        }
    }

    public void println(String data){
        textView.append(data + "\n");

    }

    class DatabaseHelper extends SQLiteOpenHelper{ // db helper


        public DatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
            super(context, name, factory, version);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {

            println("onCreate() 호출됨");
            String tableName = "customer";

                // db가 먼저 생성되어야 하므로 체크하는 것이 좋음.
                String sql = "create table if not exists " + tableName + "(_id integer PRIMARY KEY autoincrement, name text, age integer, mobile text)" ; // sql문 작성
                db.execSQL(sql); //결과값을 받지 않으려면 execSQL메소드 사용
                println("테이블 생성됨."); // 데이터를 미리 넣는 경우는 이 곳에서 인서트 해줌.


        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            println("onUpgrade 호출됨 :" + oldVersion+", " + newVersion);
            //1에서 2로 바뀌었을 경우로 가정
            if(newVersion>1){
                String tableName = "customer";
                db.execSQL("drop table if exists " + tableName); // 테이블을 삭제 , 원래는 alter table로 대체하는 것이 맞음.

                String sql = "create table if not exists " + tableName + "(_id integer PRIMARY KEY autoincrement, name text, age integer, mobile text)" ; // sql문 작성
                db.execSQL(sql); //결과값을 받지 않으려면 execSQL메소드 사용
                println("테이블 새로생성됨.");

            }

        }
    }




}
