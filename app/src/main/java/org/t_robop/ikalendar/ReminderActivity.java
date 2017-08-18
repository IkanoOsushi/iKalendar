package org.t_robop.ikalendar;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;


import java.util.ArrayList;
//package org.t_robop.urano.reminder_test;

public class ReminderActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, AdapterView.OnItemClickListener {

    String time = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reminder);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //ここから時間取得&表示
        Date now = new Date(System.currentTimeMillis());
        DateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
        String nowText = formatter.format(now);

        TextView tv = (TextView)findViewById(R.id.date);
        tv.setText(nowText);
        //ここまで時間取得&表示

        //ここからNavigation Drawerのやつ
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        //ここまでNavigation Drawerのやつ


        //activity_reminder.xml内ListViewのidと連携

        ListView listView = (ListView)findViewById(R.id.customScrollListView);

        listView.setOnItemClickListener(this);

        //ListViewに表示する要素を設定
        ArrayList<CustomListItem> listItems = new ArrayList<>();
        for(int i=0; i<24; i++){
            time = String.valueOf(i)+":00";
            CustomListItem item = new CustomListItem(time,"予定");
            listItems.add(item);
        }

        //listItemsをカスタムアダプターに入れてlistViewにセット
        CustomListAdapter adapter = new CustomListAdapter(this,R.layout.custom_scrollistview_item,listItems);
        listView.setAdapter(adapter);

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

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        //ListView listView =(ListView)parent;
        //CustomListItem item = (CustomListItem)listView.getItemAtPosition(position);

        Intent intent = new Intent(this, ReminderEditActivity.class);
        intent.putExtra("time",time);
        startActivity(intent);
    }
}

