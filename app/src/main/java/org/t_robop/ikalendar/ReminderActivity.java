package org.t_robop.ikalendar;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
//package org.t_robop.urano.reminder_test;

public class ReminderActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    TextView tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reminder);
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

        Intent intent = getIntent();
        //String text= getIntent().getStringExtra("note");
        String text= intent.getStringExtra("note");
        String note_id = getIntent().getStringExtra("note_id");

        tv = (TextView)findViewById(R.id.note0000);


        if(text != null) {
            switch (note_id) {
                case "note0000":
                    tv.setText(String.valueOf(text));
                    break;
                case "note0100":
                    tv.setText(String.valueOf(text));
                    break;
                case "note0200":
                    tv.setText(String.valueOf(text));
                    break;
                case "note0300":
                    tv.setText(String.valueOf(text));
                    break;
                case "note0400":
                    tv.setText(String.valueOf(text));
                    break;
                case "note0500":
                    tv.setText(String.valueOf(text));
                    break;
                case "note0600":
                    tv.setText(String.valueOf(text));
                    break;
                case "note0700":
                    tv.setText(String.valueOf(text));
                    break;
                case "note0800":
                    tv.setText(String.valueOf(text));
                    break;
                case "note0900":
                    tv.setText(String.valueOf(text));
                    break;
                case "note1000":
                    tv.setText(String.valueOf(text));
                    break;
                case "note1100":
                    tv.setText(String.valueOf(text));
                    break;
                case "note1200":
                    tv.setText(String.valueOf(text));
                    break;
                case "note1300":
                    tv.setText(String.valueOf(text));
                    break;
                case "note1400":
                    tv.setText(String.valueOf(text));
                    break;
                case "note1500":
                    tv.setText(String.valueOf(text));
                    break;
                case "note1600":
                    tv.setText(String.valueOf(text));
                    break;
                case "note1700":
                    tv.setText(String.valueOf(text));
                    break;
                case "note1800":
                    tv.setText(String.valueOf(text));
                    break;
                case "note1900":
                    tv.setText(String.valueOf(text));
                    break;
                case "note2000":
                    tv.setText(String.valueOf(text));
                    break;
                case "note2100":
                    tv.setText(String.valueOf(text));
                    break;
                case "note2200":
                    tv.setText(String.valueOf(text));
                    break;
                case "note2300":
                    tv.setText(String.valueOf(text));
                    break;
            }
        }

    }

    public void add(View v) {
        Intent intent = new Intent(this, ReminderEditActivity.class);
        String rsName = getResources().getResourceEntryName(v.getId());
        intent.putExtra("note_id",rsName);
        intent.putExtra("note","");
        startActivity(intent);
    }

    //Navigation Drawer内のメニューを押した時の動作
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_main) {
            Intent intent = new Intent(this,ReminderActivity.class);
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

