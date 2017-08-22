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
import android.widget.Button;
import android.widget.TextView;

import java.util.Calendar;

import io.realm.CalenderRealmProxy;
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

        TextView firstPeriod = (TextView) findViewById(R.id.first);
        TextView secondPeriod = (TextView) findViewById(R.id.second);
        TextView thirdPeriod = (TextView) findViewById(R.id.third);
        TextView fourthPeriod = (TextView) findViewById(R.id.fourth);
        TextView fifthPeriod = (TextView) findViewById(R.id.fifth);


        //ここからNavigation Drawerのやつ
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        //ここまでNavigation Drawerのやつ

        // デフォルトのCalendarオブジェクト
        Calendar cal = Calendar.getInstance();
        int week = cal.get(Calendar.DAY_OF_WEEK);
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DATE);


        Log.d("aaaa",String.valueOf(week));
        //Database初期化
        Realm.init(this);
        realm = Realm.getDefaultInstance();

        //検索用のクエリ作成
        RealmQuery<TimeTable> timetableQuery = realm.where(TimeTable.class);
        //インスタンス生成し、その中にすべてのデータを入れる 今回なら全てのデータ
        RealmResults<TimeTable> timetables = timetableQuery.findAll();

        TextView year_text = (TextView)findViewById(R.id.year_text);

        year_text.setText(year + "年");

        TextView month_day_text = (TextView)findViewById(R.id.month_day);

        month_day_text.setText(month + "/" + day);

        TextView week_text = (TextView)findViewById(R.id.week_text);

        switch(week){
            case 1:
                week_text.setText("日曜日");
                break;
            case 2:
                week_text.setText("月曜日");
                break;
            case 3:
                week_text.setText("火曜日");
                break;
            case 4:
                week_text.setText("水曜日");
                break;
            case 5:
                week_text.setText("木曜日");
                break;
            case 6:
                week_text.setText("金曜日");
                break;
            case 7:
                week_text.setText("土曜日");
                break;

        }


        if(timetables.size() != 0){
            //timetableの配列の要素の数が0の時の実行
            for(int i=0; i<timetables.size(); i++) {
                //timetablesの要素の数だけ回す
                switch(cal.get(Calendar.DAY_OF_WEEK)){
                    case 2:
                        switch (String.valueOf(timetables.get(i).getTimeTableId())) {
                            //string型（ボタン名）に直してtimetablesのi番目のIDをとってきて条件分け
                            case "button37":
                                firstPeriod.setText(timetables.get(i).getTimeTableSub());
                                break;
                            case "button30":
                                secondPeriod.setText(timetables.get(i).getTimeTableSub());
                                break;
                            case "button23":
                                thirdPeriod.setText(timetables.get(i).getTimeTableSub());
                                break;
                            case "button16":
                                fourthPeriod.setText(timetables.get(i).getTimeTableSub());
                                break;
                            case "button6":
                                fifthPeriod.setText(timetables.get(i).getTimeTableSub());
                                break;
                        }
                    case 3:
                        switch (String.valueOf(timetables.get(i).getTimeTableId())) {
                            //string型（ボタン名）に直してtimetablesのi番目のIDをとってきて条件分け
                            case "button36":
                                firstPeriod.setText(timetables.get(i).getTimeTableSub());
                                break;
                            case "button29":
                                secondPeriod.setText(timetables.get(i).getTimeTableSub());
                                break;
                            case "button22":
                                thirdPeriod.setText(timetables.get(i).getTimeTableSub());
                                break;
                            case "button15":
                                fourthPeriod.setText(timetables.get(i).getTimeTableSub());
                                break;
                            case "button5":
                                fifthPeriod.setText(timetables.get(i).getTimeTableSub());
                                break;
                        }

                    case 4:
                        switch (String.valueOf(timetables.get(i).getTimeTableId())) {
                            //string型（ボタン名）に直してtimetablesのi番目のIDをとってきて条件分け
                            case "button35":
                                firstPeriod.setText(timetables.get(i).getTimeTableSub());
                                break;
                            case "button28":
                                secondPeriod.setText(timetables.get(i).getTimeTableSub());
                                break;
                            case "button21":
                                thirdPeriod.setText(timetables.get(i).getTimeTableSub());
                                break;
                            case "button14":
                                fourthPeriod.setText(timetables.get(i).getTimeTableSub());
                                break;
                            case "button4":
                                fifthPeriod.setText(timetables.get(i).getTimeTableSub());
                                break;
                        }
                    case 5:
                        switch (String.valueOf(timetables.get(i).getTimeTableId())) {
                            //string型（ボタン名）に直してtimetablesのi番目のIDをとってきて条件分け
                            case "button34":
                                firstPeriod.setText(timetables.get(i).getTimeTableSub());
                                break;
                            case "button27":
                                secondPeriod.setText(timetables.get(i).getTimeTableSub());
                                break;
                            case "button20":
                                thirdPeriod.setText(timetables.get(i).getTimeTableSub());
                                break;
                            case "button13":
                                fourthPeriod.setText(timetables.get(i).getTimeTableSub());
                                break;
                            case "button3":
                                fifthPeriod.setText(timetables.get(i).getTimeTableSub());
                                break;
                        }
                    case 6:
                        switch (String.valueOf(timetables.get(i).getTimeTableId())) {
                            //string型（ボタン名）に直してtimetablesのi番目のIDをとってきて条件分け
                            case "button33":
                                firstPeriod.setText(timetables.get(i).getTimeTableSub());
                                break;
                            case "button26":
                                secondPeriod.setText(timetables.get(i).getTimeTableSub());
                                break;
                            case "button19":
                                thirdPeriod.setText(timetables.get(i).getTimeTableSub());
                                break;
                            case "button12":
                                fourthPeriod.setText(timetables.get(i).getTimeTableSub());
                                break;
                            case "button2":
                                fifthPeriod.setText(timetables.get(i).getTimeTableSub());
                                break;
                        }
                    case 7:
                        switch (String.valueOf(timetables.get(i).getTimeTableId())) {
                            //string型（ボタン名）に直してtimetablesのi番目のIDをとってきて条件分け
                            case "button32":
                                firstPeriod.setText(timetables.get(i).getTimeTableSub());
                                break;
                            case "button25":
                                secondPeriod.setText(timetables.get(i).getTimeTableSub());
                                break;
                            case "button18":
                                thirdPeriod.setText(timetables.get(i).getTimeTableSub());
                                break;
                            case "button11":
                                fourthPeriod.setText(timetables.get(i).getTimeTableSub());
                                break;
                            case "button":
                                fifthPeriod.setText(timetables.get(i).getTimeTableSub());
                                break;
                        }


                }
            Log.d("eeee",String.valueOf(timetables.get(i).getTimeTableId()));



            }}

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
