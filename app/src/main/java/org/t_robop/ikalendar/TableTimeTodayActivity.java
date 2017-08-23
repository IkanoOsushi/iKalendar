package org.t_robop.ikalendar;

import android.content.Intent;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

import io.realm.Realm;
import io.realm.RealmQuery;
import io.realm.RealmResults;

import java.text.CollationElementIterator;
import java.util.Calendar;
import java.util.HashMap;

public class TableTimeTodayActivity extends AppCompatActivity implements View.OnClickListener,NavigationView.OnNavigationItemSelectedListener{

    Realm realm;

    Calendar cal = Calendar.getInstance();
    int week = cal.get(Calendar.DAY_OF_WEEK);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_taimetable_today);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Button backbutton =(Button)findViewById(R.id.fooder_exit);
        backbutton.setOnClickListener(this);
        Button nextbutton =(Button)findViewById(R.id.fooder_next);
        nextbutton.setOnClickListener(this);

        Intent intent = this.getIntent();
        int thisweek = intent.getIntExtra("thisweek",week);
        week = thisweek;

        //Database初期化
        Realm.init(this);
        realm = Realm.getDefaultInstance();

        //ここからNavigation Drawerのやつ
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        //ここまでNavigation Drawerのやつ


        TextView week_text = (TextView)findViewById(R.id.nowDay);
        TextView tFirst =(TextView)findViewById(R.id.tFirst);
        TextView tSecond=(TextView)findViewById(R.id.tSecond);
        TextView tThird=(TextView)findViewById(R.id.tThird);
        TextView tFourth=(TextView)findViewById(R.id.tFourth);
        TextView tFifth=(TextView)findViewById(R.id.tFifth);
        TextView tFirstClass =(TextView)findViewById(R.id.tFirstClass);
        TextView tSecondClass=(TextView)findViewById(R.id.tSecondClass);
        TextView tThirdClass=(TextView)findViewById(R.id.tThirdClass);
        TextView tFourthClass=(TextView)findViewById(R.id.tFourthClass);
        TextView tFifthClass=(TextView)findViewById(R.id.tFifthClass);


        //検索用のクエリ作成
        RealmQuery<TimeTable> timetableQuery = realm.where(TimeTable.class);
        //インスタンス生成し、その中にすべてのデータを入れる 今回なら全てのデータ
        RealmResults<TimeTable> timeTables = timetableQuery.findAll();

        RealmResults<TimeTable>timeTablestoday = timetableQuery.equalTo("time_table_day_of_week",thisweek).findAll();
        if(timeTablestoday.size() != 0){
            //timetableの配列の要素の数が0の時の実行
            for(int i=0; i<timeTablestoday.size(); i++) {
                //timetablesの要素の数だけ回す
                switch(week){
                    case 1:
                            break;
                    case 2:
                        switch (String.valueOf(timeTablestoday.get(i).getTimeTableId())) {
                            //string型（ボタン名）に直してtimetablesのi番目のIDをとってきて条件分け
                            case "button37":
                                tFirst.setText(timeTablestoday.get(i).getTimeTableSub());
                                tFirstClass.setText(timeTablestoday.get(i).getTimeTableClass());
                                break;
                            case "button30":
                                tSecond.setText(timeTablestoday.get(i).getTimeTableSub());
                                tSecondClass.setText(timeTablestoday.get(i).getTimeTableClass());

                                break;
                            case "button23":
                                tThird.setText(timeTablestoday.get(i).getTimeTableSub());
                                tThirdClass.setText(timeTablestoday.get(i).getTimeTableClass());

                                break;
                            case "button16":
                                tFourth.setText(timeTablestoday.get(i).getTimeTableSub());
                                tFourthClass.setText(timeTablestoday.get(i).getTimeTableClass());

                                break;
                            case "button6":
                                tFifth.setText(timeTablestoday.get(i).getTimeTableSub());
                                tFifthClass.setText(timeTablestoday.get(i).getTimeTableClass());

                                break;
                        }
                    case 3:
                        switch (String.valueOf(timeTablestoday.get(i).getTimeTableId())) {
                            //string型（ボタン名）に直してtimetablesのi番目のIDをとってきて条件分け
                            case "button36":
                                tFirst.setText(timeTablestoday.get(i).getTimeTableSub());
                                tFirstClass.setText(timeTablestoday.get(i).getTimeTableClass());

                                break;
                            case "button29":
                                tSecond.setText(timeTablestoday.get(i).getTimeTableSub());
                                tSecondClass.setText(timeTablestoday.get(i).getTimeTableClass());

                                break;
                            case "button22":
                                tThird.setText(timeTablestoday.get(i).getTimeTableSub());
                                tThirdClass.setText(timeTablestoday.get(i).getTimeTableClass());

                                break;
                            case "button15":
                                tFourth.setText(timeTablestoday.get(i).getTimeTableSub());
                                tFourthClass.setText(timeTablestoday.get(i).getTimeTableClass());

                                break;
                            case "button5":
                                tFifth.setText(timeTablestoday.get(i).getTimeTableSub());
                                tFifthClass.setText(timeTablestoday.get(i).getTimeTableClass());

                                break;
                        }

                    case 4:
                        switch (String.valueOf(timeTablestoday.get(i).getTimeTableId())) {
                            //string型（ボタン名）に直してtimetablesのi番目のIDをとってきて条件分け
                            case "button35":
                                tFirst.setText(timeTablestoday.get(i).getTimeTableSub());
                                tFirstClass.setText(timeTablestoday.get(i).getTimeTableClass());

                                break;
                            case "button28":
                                tSecond.setText(timeTablestoday.get(i).getTimeTableSub());
                                tSecondClass.setText(timeTablestoday.get(i).getTimeTableClass());

                                break;
                            case "button21":
                                tThird.setText(timeTablestoday.get(i).getTimeTableSub());
                                tThirdClass.setText(timeTablestoday.get(i).getTimeTableClass());

                                break;
                            case "button14":
                                tFourth.setText(timeTablestoday.get(i).getTimeTableSub());
                                tFourthClass.setText(timeTablestoday.get(i).getTimeTableClass());

                                break;
                            case "button4":
                                tFifth.setText(timeTablestoday.get(i).getTimeTableSub());
                                tFifthClass.setText(timeTablestoday.get(i).getTimeTableClass());

                                break;
                        }
                    case 5:
                        switch (String.valueOf(timeTablestoday.get(i).getTimeTableId())) {
                            //string型（ボタン名）に直してtimetablesのi番目のIDをとってきて条件分け
                            case "button34":
                                tFirst.setText(timeTablestoday.get(i).getTimeTableSub());
                                tFirstClass.setText(timeTablestoday.get(i).getTimeTableClass());

                                break;
                            case "button27":
                                tSecond.setText(timeTablestoday.get(i).getTimeTableSub());
                                tSecondClass.setText(timeTablestoday.get(i).getTimeTableClass());

                                break;
                            case "button20":
                                tThird.setText(timeTablestoday.get(i).getTimeTableSub());
                                tThirdClass.setText(timeTablestoday.get(i).getTimeTableClass());

                                break;
                            case "button13":
                                tFourth.setText(timeTablestoday.get(i).getTimeTableSub());
                                tFourthClass.setText(timeTablestoday.get(i).getTimeTableClass());

                                break;
                            case "button3":
                                tFifth.setText(timeTablestoday.get(i).getTimeTableSub());
                                tFifthClass.setText(timeTablestoday.get(i).getTimeTableClass());

                                break;
                        }
                    case 6:
                        switch (String.valueOf(timeTablestoday.get(i).getTimeTableId())) {
                            //string型（ボタン名）に直してtimetablesのi番目のIDをとってきて条件分け
                            case "button33":
                                tFirst.setText(timeTablestoday.get(i).getTimeTableSub());
                                tFirstClass.setText(timeTablestoday.get(i).getTimeTableClass());

                                break;
                            case "button26":
                                tSecond.setText(timeTablestoday.get(i).getTimeTableSub());
                                tSecondClass.setText(timeTablestoday.get(i).getTimeTableClass());

                                break;
                            case "button19":
                                tThird.setText(timeTablestoday.get(i).getTimeTableSub());
                                tThirdClass.setText(timeTablestoday.get(i).getTimeTableClass());

                                break;
                            case "button12":
                                tFourth.setText(timeTablestoday.get(i).getTimeTableSub());
                                tFourthClass.setText(timeTablestoday.get(i).getTimeTableClass());

                                break;
                            case "button2":
                                tFifth.setText(timeTablestoday.get(i).getTimeTableSub());
                                tFifthClass.setText(timeTablestoday.get(i).getTimeTableClass());

                                break;
                        }
                    case 7:
                        switch (String.valueOf(timeTablestoday.get(i).getTimeTableId())) {
                            //string型（ボタン名）に直してtimetablesのi番目のIDをとってきて条件分け
                            case "button32":
                                tFirst.setText(timeTablestoday.get(i).getTimeTableSub());
                                tFirstClass.setText(timeTablestoday.get(i).getTimeTableClass());

                                break;
                            case "button25":
                                tSecond.setText(timeTablestoday.get(i).getTimeTableSub());
                                tSecondClass.setText(timeTablestoday.get(i).getTimeTableClass());

                                break;
                            case "button18":
                                tThird.setText(timeTablestoday.get(i).getTimeTableSub());
                                tThirdClass.setText(timeTablestoday.get(i).getTimeTableClass());

                                break;
                            case "button11":
                                tFourth.setText(timeTablestoday.get(i).getTimeTableSub());
                                tFourthClass.setText(timeTablestoday.get(i).getTimeTableClass());

                                break;
                            case "button":
                                tFifth.setText(timeTablestoday.get(i).getTimeTableSub());
                                tFifthClass.setText(timeTablestoday.get(i).getTimeTableClass());

                                break;
                        }

                }

            }}
        switch(thisweek){
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
    }
    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.fooder_exit:
                if (week == 1) {
                    week = 7;
                    Intent intent = new Intent(this,TableTimeTodayActivity.class);
                    intent.putExtra("thisweek", week);
                    startActivity(intent);
                } else {
                    week--;
                    Intent intent = new Intent(this,TableTimeTodayActivity.class);
                    intent.putExtra("thisweek", week);
                    startActivity(intent);
                }
                break;
            case R.id.fooder_next:
                if (week == 7) {
                    week = 1;
                    Intent intent = new Intent(this,TableTimeTodayActivity.class);
                    intent.putExtra("thisweek", week);
                    startActivity(intent);
                } else {
                    week++;
                    Intent intent = new Intent(this,TableTimeTodayActivity.class);
                    intent.putExtra("thisweek", week);
                    startActivity(intent);
                }
                break;
        }
    }


/*
public void datachanege() {
    Calendar cal = Calendar.getInstance();
    int week = cal.get(Calendar.DAY_OF_WEEK);

    switch (week) {
        case 1: {
            //データを曜日ごとに変える
        }
    }
    }
    */



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.time_menu, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_call) {
            Intent intent = new Intent(this, TimetableActivity.class);
            startActivity(intent);
        }
        return true;
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
