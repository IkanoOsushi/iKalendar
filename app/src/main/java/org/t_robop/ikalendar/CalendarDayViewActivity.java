package org.t_robop.ikalendar;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.framgia.library.calendardayview.CalendarDayView;
import com.framgia.library.calendardayview.EventView;
import com.framgia.library.calendardayview.PopupView;
import com.framgia.library.calendardayview.data.IEvent;
import com.framgia.library.calendardayview.data.IPopup;
import com.framgia.library.calendardayview.decoration.CdvDecorationDefault;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import io.realm.Realm;
import io.realm.RealmQuery;
import io.realm.RealmResults;

public class CalendarDayViewActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener{

    CalendarDayView dayView;

    ArrayList<IEvent> events;
    ArrayList<IPopup> popups;

    String sPlanDate;

    Realm realm;

    Date dPlanDate;

    final SimpleDateFormat formatter = new SimpleDateFormat("yyyy年 MMM dd日");
    final SimpleDateFormat dateformatter = new SimpleDateFormat("yyyy HH 時 mm 分");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar_day_view);

        //intentしてきたデータを取得
        Intent intent = getIntent();
        sPlanDate = intent.getStringExtra("date");

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(sPlanDate);
        toolbar.setTitleTextColor(Color.WHITE);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        View inf = getLayoutInflater().inflate(R.layout.view_event, null);

        TextView PlanDateText = (TextView)inf.findViewById(R.id.item_event_header_text1);
        PlanDateText.setText(sPlanDate);
//        try {
//            dPlanDate = ParseStringToDate(sPlanDate);
//
//        } catch (ParseException e) {
//        }



        dayView = (CalendarDayView) findViewById(R.id.calendar);
        dayView.setLimitTime(0, 24);

        ((CdvDecorationDefault) (dayView.getDecoration())).setOnEventClickListener(
                new EventView.OnEventClickListener() {
                    @Override
                    public void onEventClick(EventView view, IEvent data) {
                        Log.e("TAG", "onEventClick:" + data.getName());
                    }

                    @Override
                    public void onEventViewClick(View view, EventView eventView, IEvent data) {
                        Log.e("TAG", "onEventViewClick:" + data.getName());

                        String sPlanTitle = data.getName();

                        String sPlanStartTime = (data.getStartTime().get(Calendar.HOUR_OF_DAY)+ " 時 " + data.getStartTime().get(Calendar.MINUTE) + " 分");

                        String sPlanEndTime = (data.getEndTime().get(Calendar.HOUR_OF_DAY) + " 時 " + data.getEndTime().get(Calendar.MINUTE) + " 分");

                        Bundle editPlanData = new Bundle();
                        editPlanData.putString("PlanTitle",sPlanTitle);
                        editPlanData.putString("PlanDate",sPlanDate);
                        editPlanData.putInt("StartHourOfDay",data.getStartTime().get(Calendar.HOUR_OF_DAY));
                        editPlanData.putInt("StartMinute",data.getStartTime().get(Calendar.MINUTE));
                        editPlanData.putInt("EndHourOfDay",data.getEndTime().get(Calendar.HOUR_OF_DAY));
                        editPlanData.putInt("EndMinute",data.getEndTime().get(Calendar.MINUTE));
                        editPlanData.putString("StartTime",sPlanStartTime);
                        editPlanData.putString("EndTime",sPlanEndTime);
                        editPlanData.putBoolean("EditFlag",true);

                        Intent intent = new Intent(CalendarDayViewActivity.this,CalendarAddPlanActivity.class);
                        intent.putExtras(editPlanData);
                        startActivity(intent);
                        if (data instanceof CalendarEvent) {
                            // change event (ex: set event color)
                            dayView.setEvents(events);
                        }
                    }
                });

        ((CdvDecorationDefault) (dayView.getDecoration())).setOnPopupClickListener(
                new PopupView.OnEventPopupClickListener() {
                    @Override
                    public void onPopupClick(PopupView view, IPopup data) {
                        Log.e("TAG", "onPopupClick:" + data.getTitle());
                    }

                    @Override
                    public void onQuoteClick(PopupView view, IPopup data) {
                        Log.e("TAG", "onQuoteClick:" + data.getTitle());
                    }

                    @Override
                    public void onLoadData(PopupView view, ImageView start, ImageView end,
                                           IPopup data) {
                        start.setImageResource(R.drawable.ic_main);
                    }
                });

        events = new ArrayList<>();

        {

            int eventColor = ContextCompat.getColor(this, R.color.eventColor);

            //Database初期化
            Realm.init(this);
            realm = Realm.getDefaultInstance();

            //検索用のクエリ作成
            RealmQuery<Calender> calenderRealmQuery = realm.where(Calender.class);
            //インスタンス生成し、その中にすべてのデータを入れる 今回なら全てのデータ
            RealmResults<Calender> calendars = calenderRealmQuery.findAll();

            if (calendars.size() != 0) {
                //timetableの配列の要素の数が0じゃない時の実行
                for (int i = 0; i < calendars.size(); i++) {
                    if(formatter.format(calendars.get(i).getCalendarstartdate()).equals(sPlanDate)) {
                        //timetablesの要素の数だけ回す
                        int startPlanHourOfDay = calendars.get(i).getCalendarStartHourOfDay();
                        int startPlanMinute = calendars.get(i).getCalendarStartMinute();
                        int endPlanHourOfDay = calendars.get(i).getCalendarEndHourOfDay();
                        int endPlanMinute = calendars.get(i).getCalendarEndMinute();

                        Calendar timeStart = Calendar.getInstance();
                        timeStart.set(Calendar.HOUR_OF_DAY, startPlanHourOfDay);
                        timeStart.set(Calendar.MINUTE, startPlanMinute);
                        Calendar timeEnd = (Calendar) timeStart.clone();
                        timeEnd.set(Calendar.HOUR_OF_DAY, endPlanHourOfDay);
                        timeEnd.set(Calendar.MINUTE, endPlanMinute);
                        CalendarEvent event = new CalendarEvent(1, timeStart, timeEnd, calendars.get(i).getCalendarTitle(), "Hockaido", eventColor);
                        events.add(event);
                    }
                }
            }


            popups = new ArrayList<>();


            dayView.setEvents(events);
            dayView.setPopups(popups);


        }
    }


    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_main) {
            Intent intent = new Intent(this, ReminderActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_calendar) {
            Intent intent = new Intent(this, CalendarActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_timetable) {
            Intent intent = new Intent(this, TimetableActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_reminder) {
            Intent intent = new Intent(this, ReminderActivity.class);
            startActivity(intent);
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.calender_add, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        boolean result = true;
        if (id == R.id.action_call) {
            Intent intent = new Intent(this, CalendarAddPlanActivity.class);
            intent.putExtra("dayviewdate",sPlanDate);
            intent.putExtra("fromDayView",true);
            startActivity(intent);
        }
        switch (id) {
            case android.R.id.home:
                Intent intent = new Intent(this, CalendarActivity.class);
                startActivity(intent);
                startActivity(intent);
                break;
            default:
                result = super.onOptionsItemSelected(item);

        }
        return true;
    }
}
