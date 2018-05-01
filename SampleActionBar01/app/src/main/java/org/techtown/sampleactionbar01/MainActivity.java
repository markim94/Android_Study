package org.techtown.sampleactionbar01;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    android.app.ActionBar abar = getActionBar();

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int curId = item.getItemId();
        switch (curId) {
            case R.id.menu_refresh:
                Toast.makeText(this, "새로고침 메뉴가 선택되었습니다", Toast.LENGTH_LONG).show();
                break;

            case R.id.menu_search:
                Toast.makeText(this, "검색 메뉴가 선택되었습니다", Toast.LENGTH_LONG).show();
                break;
            case R.id.menu_settings:
                Toast.makeText(this, "설정 메뉴가 선택되었습니다", Toast.LENGTH_LONG).show();
                break;
            default:
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        abar = getActionBar();
    }

    public void onButton1Clicked(View v){
        abar.setLogo(R.drawable.home);
        abar.setDisplayOptions(abar.DISPLAY_SHOW_HOME| abar.DISPLAY_USE_LOGO);
    }

}
