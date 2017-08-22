package org.t_robop.ikalendar;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.roomorama.caldroid.CaldroidFragment;
import com.roomorama.caldroid.CaldroidListener;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import io.realm.Realm;
import io.realm.RealmQuery;
import io.realm.RealmResults;

import static android.view.ViewGroup.LayoutParams.MATCH_PARENT;

@SuppressLint("SimpleDateFormat")
public class CalendarActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener{

    final SimpleDateFormat formatter = new SimpleDateFormat("yyyy年 MMM dd日");
    final String PATTERN = "yyyy-MM-dd";

    Realm realm;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);
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

        CaldroidFragment caldroidFragment = new CaldroidFragment();
        Bundle args = new Bundle();
        Calendar cal = Calendar.getInstance();
        args.putBoolean(CaldroidFragment.SQUARE_TEXT_VIEW_CELL,true);
        args.putInt(CaldroidFragment.MONTH, cal.get(Calendar.MONTH) + 1);
        args.putInt(CaldroidFragment.YEAR, cal.get(Calendar.YEAR));
        caldroidFragment.setArguments(args);

        final CaldroidListener listener = new CaldroidListener() {

            @Override
            public void onSelectDate(Date date, View view) {
                String PlanDate = formatter.format(date);
                Intent intent = new Intent(CalendarActivity.this,org.t_robop.ikalendar.CalenderDayViewActivity.class);
                intent.putExtra("date",PlanDate);
                startActivity(intent);

            }

            @Override
            public void onChangeMonth(int month, int year) {
                String text = "month: " + month + " year: " + year;
                Toast.makeText(getApplicationContext(), text,
                        Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onLongClickDate(Date date, View view) {
                //intentで日付を渡すためにdateをString型に変換
                String PlanDate = formatter.format(date);

                //登録画面に日付を渡しintentする
                Intent intent = new Intent(CalendarActivity.this,org.t_robop.ikalendar.CalenderAddPlanActivity.class);
                intent.putExtra("date",PlanDate);
                startActivity(intent);

            }

//            @Override
//            public void onCaldroidViewCreated() {
//                Toast.makeText(getApplicationContext(),
//                        "Caldroid view is created",
//                        Toast.LENGTH_SHORT).show();
//            }

        };

        //Database初期化
        Realm.init(this);
        realm = Realm.getDefaultInstance();

        //検索用のクエリ作成
        RealmQuery<Calender> timetableQuery = realm.where(Calender.class);
        //インスタンス生成し、その中にすべてのデータを入れる 今回なら全てのデータ
        RealmResults<Calender> timetables = timetableQuery.findAll();

        if(timetables.size() != 0) {
            //timetableの配列の要素の数が0の時の実行
            for (int i = 0; i < timetables.size(); i++) {
                //timetablesの要素の数だけ回す
                Date dPlanDate = timetables.get(i).getCalendarstartdate();
                caldroidFragment.setCaldroidListener(listener);
                caldroidFragment.setBackgroundDrawableForDate(getResources().getDrawable(R.drawable.ic_squid), dPlanDate);
            }
        }

        caldroidFragment.setCaldroidListener(listener);

        android.support.v4.app.FragmentTransaction t = getSupportFragmentManager().beginTransaction();
        t.replace(R.id.calender_layout, caldroidFragment);

        LinearLayout calendarLayout = (LinearLayout) findViewById(R.id.calendar_layout_2);
        ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams)calendarLayout.getLayoutParams();
        marginLayoutParams.height = MATCH_PARENT;
        calendarLayout.setLayoutParams(marginLayoutParams);

        t.commit();


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
