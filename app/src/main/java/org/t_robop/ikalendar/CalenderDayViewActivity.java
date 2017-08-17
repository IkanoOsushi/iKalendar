package org.t_robop.ikalendar;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.framgia.library.calendardayview.CalendarDayView;
import com.framgia.library.calendardayview.EventView;
import com.framgia.library.calendardayview.PopupView;
import com.framgia.library.calendardayview.data.IEvent;
import com.framgia.library.calendardayview.data.IPopup;
import com.framgia.library.calendardayview.decoration.CdvDecorationDefault;
import java.util.ArrayList;
import java.util.Calendar;

public class CalenderDayViewActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener{

    CalendarDayView dayView;

    ArrayList<IEvent> events;
    ArrayList<IPopup> popups;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calender_day_view);
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

        dayView = (CalendarDayView) findViewById(R.id.calendar);
        dayView.setLimitTime(9, 22);

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
            Calendar timeStart = Calendar.getInstance();
            timeStart.set(Calendar.HOUR_OF_DAY, 11);
            timeStart.set(Calendar.MINUTE, 0);
            Calendar timeEnd = (Calendar) timeStart.clone();
            timeEnd.set(Calendar.HOUR_OF_DAY, 15);
            timeEnd.set(Calendar.MINUTE, 30);
            CalenderEvent event = new CalenderEvent(1, timeStart, timeEnd, "CalenderEvent", "Hockaido", eventColor);

            events.add(event);
        }

        {
            int eventColor = ContextCompat.getColor(this, R.color.eventColor);
            Calendar timeStart = Calendar.getInstance();
            timeStart.set(Calendar.HOUR_OF_DAY, 20);
            timeStart.set(Calendar.MINUTE, 00);
            Calendar timeEnd = (Calendar) timeStart.clone();
            timeEnd.set(Calendar.HOUR_OF_DAY, 22);
            timeEnd.set(Calendar.MINUTE, 00);
            CalenderEvent event = new CalenderEvent(1, timeStart, timeEnd, "Another event", "Hockaido", eventColor);

            events.add(event);
        }

        popups = new ArrayList<>();

        {
            Calendar timeStart = Calendar.getInstance();
            timeStart.set(Calendar.HOUR_OF_DAY, 12);
            timeStart.set(Calendar.MINUTE, 0);
            Calendar timeEnd = (Calendar) timeStart.clone();
            timeEnd.set(Calendar.HOUR_OF_DAY, 13);
            timeEnd.set(Calendar.MINUTE, 30);

            CalenderPopup popup = new CalenderPopup();
            popup.setStartTime(timeStart);
            popup.setEndTime(timeEnd);
            popup.setImageStart("http://sample.com/image.png");
            popup.setTitle("event 1 with title");
            popup.setDescription("Yuong alsdf");
            popups.add(popup);
        }

        {
            Calendar timeStart = Calendar.getInstance();
            timeStart.set(Calendar.HOUR_OF_DAY, 20);
            timeStart.set(Calendar.MINUTE, 30);
            Calendar timeEnd = (Calendar) timeStart.clone();
            timeEnd.set(Calendar.HOUR_OF_DAY, 21);
            timeEnd.set(Calendar.MINUTE, 30);

            CalenderPopup popup = new CalenderPopup();
            popup.setStartTime(timeStart);
            popup.setEndTime(timeEnd);
            popup.setImageStart("http://sample.com/image.png");
            popup.setTitle("event 2 with title");
            popup.setDescription("Yuong alsdf");
            popups.add(popup);
        }

        dayView.setEvents(events);
        dayView.setPopups(popups);


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
}
