package org.t_robop.ikalendar;

import android.content.Intent;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;

import java.util.Calendar;

import io.realm.Realm;
import io.realm.RealmQuery;
import io.realm.RealmResults;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener{

    Realm realm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //ここからNavigation Drawerのやつ
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        //ここまでNavigation Drawerのやつ

        //Database初期化
        Realm.init(this);
        realm = Realm.getDefaultInstance();

        //検索用のクエリ作成
        RealmQuery<TimeTable> timetableQuery = realm.where(TimeTable.class);
        //インスタンス生成し、その中にすべてのデータを入れる 今回なら全てのデータ
        RealmResults<TimeTable> timetables = timetableQuery.findAll();

        if(timetables.size() != 0){
            //timetableの配列の要素の数が0の時の実行
            for(int i=0; i<timetables.size(); i++) {
                //timetablesの要素の数だけ回す
            Log.d("aaaa",String.valueOf(timetables.get(i).getTimeTableId()));



            }}



                // デフォルトのCalendarオブジェクト
        Calendar cal = Calendar.getInstance();

        Log.d("aaaaa",String.valueOf(cal));

    }
    //Navigation Drawer内のメニューを押した時の動作
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        if (id == R.id.nav_main) {
            Intent intent = new Intent(this,MainActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_calendar) {
            Intent intent = new Intent(this,CalendarActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_timetable) {
            Intent intent = new Intent(this,TimetableActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_reminder) {
            Intent intent = new Intent(this,ReminderActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_setting) {

        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
