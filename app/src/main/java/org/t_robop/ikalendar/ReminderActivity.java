package org.t_robop.ikalendar;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;


import java.util.ArrayList;
//package org.t_robop.urano.reminder_test;

public class ReminderActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, AdapterView.OnItemClickListener {

    private static final int REQUEST_CODE = 1;

    ListView listView;
    ArrayList<CustomListItem> listItems;
    CustomListAdapter customListAdapter;
    String getResultText;


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

        TextView tv = (TextView) findViewById(R.id.date);
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
        listView = (ListView) findViewById(R.id.customScrollListView);

        listView.setOnItemClickListener(this);

        //ListViewに表示する要素を設定
        listItems = new ArrayList<>();

        //ToDo 文字列を予定で初期化してるので、別ActivityからstartActivityで起動されたときに再初期化される　データベースからitemをgetしたい
        for (int i = 0; i < 24; i++) {
            CustomListItem defaultItem = new CustomListItem(String.valueOf(i) + ":00", "予定");
            listItems.add(defaultItem);
        }

        //listItemsをカスタムアダプターに入れてlistViewにセット
        customListAdapter = new CustomListAdapter(this, R.layout.custom_scrollistview_item, listItems);
        listView.setAdapter(customListAdapter);

    }

    //Navigation Drawer内のメニューを押した時の動作
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

    @Override
    public void onItemClick(final AdapterView<?> parent, View view, final int position, long id) {
        ListView listView = (ListView) parent;
        CustomListItem clickedItem = (CustomListItem) listView.getItemAtPosition(position);     //listViewのタップされた場所の情報を変数itemに代入

        final String clickedItemTime = clickedItem.getmTime();   //タップされた場所の時間を取得
        final String clickedItemText = clickedItem.getmNoteText();
        final Intent intent = new Intent(this, ReminderEditActivity.class);

        AlertDialog.Builder alertDlg = new AlertDialog.Builder(this);
        alertDlg.setTitle(position +":00の予定");
        alertDlg.setMessage(clickedItemText);
        alertDlg.setPositiveButton(
                "編集",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {

                        intent.putExtra("note",clickedItemText);     //タップされた場所に元々入っているテキスト
                        intent.putExtra("time", clickedItemTime);   //タップされた場所の時間
                        intent.putExtra("position", position);      //タップされたposition

                        startActivityForResult(intent, REQUEST_CODE);
                    }
                });
        alertDlg.setNegativeButton(
                "キャンセル",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // Cancel ボタンクリック処理
                    }
                });

        // 表示
        alertDlg.create().show();
    }

    //startActivityForResultで遷移したActivityからのコールバック
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case (REQUEST_CODE):
                if (resultCode == RESULT_OK) {
                    //追加ボタンを押して戻ってきたときの処理
                    getResultText = data.getStringExtra("note");         //ReminderEditActivityで編集した予定(文字列)の取得
                    int getResultPosition = data.getIntExtra("position", 0);    //onItemClickのposition

                    CustomListItem editItem = new CustomListItem(String.valueOf(getResultPosition) + ":00", getResultText);     //変更されるListViewを作成
                    listItems.set(getResultPosition, editItem);     //変更されるListViewの列を更新

                    //ToDo ここでデータベースにlistItemsを保存(set)して、onCreateでgetできるようにしたい

                    customListAdapter = new CustomListAdapter(this, R.layout.custom_scrollistview_item, listItems);
                    listView.setAdapter(customListAdapter);

                } else if (resultCode == RESULT_CANCELED) {
                    //キャンセルボタンを押して戻ってきたときの処理
                }
                break;
            default:
                break;
        }
    }
}

