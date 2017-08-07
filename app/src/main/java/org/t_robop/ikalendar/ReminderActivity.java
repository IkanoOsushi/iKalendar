package org.t_robop.ikalendar;

import android.content.Intent;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class ReminderActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener{
    ListView listView;
    static ArrayList<String> arrayList;
    ArrayAdapter<String> arrayAdapter;

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


    listView = (ListView)findViewById(R.id.list);
    arrayAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_expandable_list_item_1);

    Intent intent = getIntent();
    final String memo = intent.getStringExtra("memo");
    int listpos = intent.getIntExtra("pos",-1);

    //初回起動時のみ
        if(arrayList==null){
        arrayList = new ArrayList<>();
        //   arrayList.add(memo);

    }

        if(memo != null){
        if(listpos == -1) {
            arrayList.add(memo);
        }
        else{
            arrayList.set(listpos,memo);
        }
    }

        for(int i = 0; i<arrayList.size();i++){
        arrayAdapter.add(arrayList.get(i));
    }
        listView.setAdapter(arrayAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
        @Override
        public void onItemClick(AdapterView<?>parent, View view,int position, long id){

            String[] memoText = (String[]) arrayList.toArray(new String[]{});
            String selectedText = memoText[position];
            Intent intent = new Intent(ReminderActivity.this, ReminderEditActivity.class);
            intent.putExtra("pos",position);
            intent.putExtra("memo",selectedText);

            startActivity(intent);


        }
    });
}

    public void add(View v) {
        Intent intent = new Intent(this, ReminderEditActivity.class);
        intent.putExtra("memo","");
        startActivity(intent);
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