package org.t_robop.ikalendar;

import android.content.Intent;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.NavigationView;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.framgia.library.calendardayview.CalendarDayView;
import com.framgia.library.calendardayview.EventView;
import com.framgia.library.calendardayview.PopupView;
import com.framgia.library.calendardayview.data.IEvent;
import com.framgia.library.calendardayview.data.IPopup;
import com.framgia.library.calendardayview.decoration.CdvDecorationDefault;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import io.realm.Realm;
import io.realm.RealmQuery;
import io.realm.RealmResults;

public class CalenderDayViewActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener{

    CalendarDayView dayView;

    ArrayList<IEvent> events;
    ArrayList<IPopup> popups;

    Realm realm;

    Date dPlanDate;

    final SimpleDateFormat formatter = new SimpleDateFormat("yyyy年 MMM dd日");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calender_day_view);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //intentしてきたデータを取得
        Intent intent = getIntent();
        String sPlanDate = intent.getStringExtra("date");

        View inf = getLayoutInflater().inflate(R.layout.view_event, null);

        TextView PlanDateText = (TextView)inf.findViewById(R.id.item_event_header_text1);
        PlanDateText.setText(sPlanDate);
//        try {
//            dPlanDate = ParseStringToDate(sPlanDate);
//
//        } catch (ParseException e) {
//        }


        //ここからNavigation Drawerのやつ
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        //ここまでNavigation Drawerのやつ

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
                        if (data instanceof CalenderEvent) {
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
                        CalenderEvent event = new CalenderEvent(1, timeStart, timeEnd, calendars.get(i).getCalendarTitle(), "Hockaido", eventColor);
                        events.add(event);
                    }
                }
            }


//            Calendar timeStart = Calendar.getInstance();
//            timeStart.set(Calendar.HOUR_OF_DAY, 11);
//            timeStart.set(Calendar.MINUTE, 0);
//            Calendar timeEnd = (Calendar) timeStart.clone();
//            timeEnd.set(Calendar.HOUR_OF_DAY, 15);
//            timeEnd.set(Calendar.MINUTE, 30);
//            CalenderEvent event = new CalenderEvent(1, timeStart, timeEnd, "CalenderEvent", "Hockaido", eventColor);
//
//            events.add(event);
//        }
//
//        {
//            int eventColor = ContextCompat.getColor(this, R.color.eventColor);
//            Calendar timeStart = Calendar.getInstance();
//            timeStart.set(Calendar.HOUR_OF_DAY, 20);
//            timeStart.set(Calendar.MINUTE, 00);
//            Calendar timeEnd = (Calendar) timeStart.clone();
//            timeEnd.set(Calendar.HOUR_OF_DAY, 22);
//            timeEnd.set(Calendar.MINUTE, 00);
//            CalenderEvent event = new CalenderEvent(1, timeStart, timeEnd, "Another event", "Hockaido", eventColor);
//
//            events.add(event);
//        }

            popups = new ArrayList<>();

//            {
//                Calendar timeStart = Calendar.getInstance();
//                timeStart.set(Calendar.HOUR_OF_DAY, 12);
//                timeStart.set(Calendar.MINUTE, 0);
//                Calendar timeEnd = (Calendar) timeStart.clone();
//                timeEnd.set(Calendar.HOUR_OF_DAY, 13);
//                timeEnd.set(Calendar.MINUTE, 30);
//
//                CalenderPopup popup = new CalenderPopup();
//                popup.setStartTime(timeStart);
//                popup.setEndTime(timeEnd);
//                popup.setImageStart("http://sample.com/image.png");
//                popup.setTitle("event 1 with title");
//                popup.setDescription("Yuong alsdf");
//                popups.add(popup);
//            }
//
//            {
//                Calendar timeStart = Calendar.getInstance();
//                timeStart.set(Calendar.HOUR_OF_DAY, 20);
//                timeStart.set(Calendar.MINUTE, 30);
//                Calendar timeEnd = (Calendar) timeStart.clone();
//                timeEnd.set(Calendar.HOUR_OF_DAY, 21);
//                timeEnd.set(Calendar.MINUTE, 30);
//
//                CalenderPopup popup = new CalenderPopup();
//                popup.setStartTime(timeStart);
//                popup.setEndTime(timeEnd);
//                popup.setImageStart("http://sample.com/image.png");
//                popup.setTitle("event 2 with title");
//                popup.setDescription("Yuong alsdf");
//                popups.add(popup);
//            }

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
        } else if (id == R.id.nav_setting) {

        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
//    public Date ParseStringToDate(String sPlanDate) throws ParseException{
//
//        Date dPlanDate = formatter.parse(sPlanDate);
//        return dPlanDate;
//
//    }
}
