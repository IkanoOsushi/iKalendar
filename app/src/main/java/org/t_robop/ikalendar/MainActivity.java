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
import android.widget.ListView;
import android.widget.TextView;

import org.t_robop.ikalendar.Calender.CalendarActivity;
import org.t_robop.ikalendar.database.Reminder;
import org.t_robop.ikalendar.database.TimeTable;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Calendar;

import io.realm.Realm;
import io.realm.RealmQuery;
import io.realm.RealmResults;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener{

    Realm realm;
    ListView listView;
    ArrayList<CustomListItem> listItems;
    CustomListAdapter customListAdapter;
    String[] weeks = {"日曜日","月曜日","火曜日","水曜日","木曜日","金曜日","土曜日"};

    TextView[] subjectText = new TextView[5];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        for (int i = 0; i < subjectText.length; i++){
            // i+1の理由は、 iは0からだが、時間割は1からのため
            subjectText[i] = (TextView) findViewById(getResources().getIdentifier("subject"+(i+1), "id", getApplication().getPackageName()));
        }

        listView = (ListView) findViewById(R.id.main_ListView);



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

        //Database初期化
        Realm.init(this);
        realm = Realm.getDefaultInstance();

        //ListViewに表示する要素を設定
        listItems = new ArrayList<>();
        //検索用のクエリ作成
        RealmQuery<Reminder> reminderRealmQuery = realm.where(Reminder.class);
        //インスタンス生成し、その中にすべてのデータを入れる 今回なら全てのデータ
        RealmResults<Reminder> reminders = reminderRealmQuery.findAll();

        for(int i=0;i<reminders.size();i++) {
            if (!String.valueOf(reminders.get(i).getReminderMemo()).equals("")){
                    CustomListItem defaultItem = new CustomListItem(String.valueOf(reminders.get(i).getReminderTime()), String.valueOf(reminders.get(i).getReminderMemo()));
                    listItems.add(defaultItem);

            }

        }
        //listItemsをカスタムアダプターに入れてlistViewにセット
        customListAdapter = new CustomListAdapter(this, R.layout.custom_scrollistview_item, listItems);
        listView.setAdapter(customListAdapter);


        TextView year_text = (TextView)findViewById(R.id.year_text);
        TextView month_day_text = (TextView)findViewById(R.id.month_day);
        TextView week_text = (TextView)findViewById(R.id.week_text);

        year_text.setText(year + "年");
        month_day_text.setText(month + "/" + day);
        // weekの値が1~7で来てるから、配列の値を-1してる
        week_text.setText(weeks[week-1]);


        //検索用のクエリ作成
        RealmQuery<TimeTable> timetableQuery = realm.where(TimeTable.class);
        //インスタンス生成し、その中にすべてのデータを入れる 今回なら全てのデータ
        RealmResults<TimeTable> timeTablestoday = timetableQuery.equalTo("time_table_day_of_week", week).findAll().sort("time_table_id");
        if (timeTablestoday.size() != 0) {
            for (int i = 0; i < timeTablestoday.size(); i++) {
                int hour = timeTablestoday.get(i).getTimeTableRow();
                subjectText[hour].setText(timeTablestoday.get(i).getTimeTableSub());
            }
        }

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
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
