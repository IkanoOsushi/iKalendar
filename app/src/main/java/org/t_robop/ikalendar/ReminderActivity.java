package org.t_robop.ikalendar;

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
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import org.t_robop.ikalendar.Calender.CalendarActivity;
import org.t_robop.ikalendar.database.Reminder;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;


import java.util.ArrayList;

import io.realm.Realm;
import io.realm.RealmQuery;
import io.realm.RealmResults;

public class ReminderActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, AdapterView.OnItemClickListener {

    private static final int REQUEST_CODE = 1;

    ListView listView;
    ArrayList<CustomListItem> listItems;
    CustomListAdapter customListAdapter;
    String getResultText;
    int d_position;
    Realm realm;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reminder);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Realm.init(this);
        realm = Realm.getDefaultInstance();

        //ここから時間取得&表示
        Date now = new Date(System.currentTimeMillis());
        DateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
        String nowText = formatter.format(now);

        // クエリを発行し結果を取得
        final RealmResults<Reminder> treminders = realm.where(Reminder.class).findAll();

// 変更操作はトランザクションの中で実行する必要あり
        if(treminders.size()!=0) {
            if (!nowText.equals(String.valueOf(treminders.get(0).getReminderToday()))) {
                realm.executeTransaction(new Realm.Transaction() {
                    @Override
                    public void execute(Realm realm) {
                        // すべてのオブジェクトを削除
                        treminders.deleteAllFromRealm();
                    }
                });
            }
        }
//        //検索用のクエリ作成
//        RealmQuery<Reminder> tset = realm.where(Reminder.class);
//        //インスタンス生成し、その中にすべてのデータを入れる 今回なら全てのデータ
//        RealmResults<Reminder>treminders= tset.findAll();
//        if(treminders.size()!=0){
//            String aToday = String.valueOf(treminders.get(0).getReminderToday());
//
//            if(!nowText.equals(aToday)){
//                final RealmResults<Reminder> results = realm.where(Reminder.class).findAll();
//                for(Reminder r : results){
//                    r.deleteFromRealm();
//                }
//            }
//        }
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
        //検索用のクエリ作成
        RealmQuery<Reminder> reminderRealmQuery = realm.where(Reminder.class);
        //インスタンス生成し、その中にすべてのデータを入れる 今回なら全てのデータ
        RealmResults<Reminder> reminders = reminderRealmQuery.findAll();

        if (reminders.size() == 0){
            for(int i=0;i<24;i++) {
                realm.beginTransaction();
                Reminder model = realm.createObject(Reminder.class);
                model.setReminderId(i);
                model.setReminderMemo("");
                model.setReminderTime(i + ":00");
                model.setReminderToday(nowText);
                realm.commitTransaction();
            }
        }


        //ToDo 文字列を予定で初期化してるので、別ActivityからstartActivityで起動されたときに再初期化される　データベースからitemをgetしたい
        for (int i = 0; i < 24; i++) {
            String memo = String.valueOf(reminders.get(i).getReminderMemo());
            Log.d("iii",String.valueOf(reminders.get(i)));
            if(!memo.equals("")) {
                if (reminders.size() != 0) {
                        CustomListItem defaultItem = new CustomListItem(String.valueOf(reminders.get(i).getReminderTime()),String.valueOf(reminders.get(i).getReminderMemo()));
                        listItems.add(defaultItem);
                }
            }
            else {
                CustomListItem defaultItem = new CustomListItem(String.valueOf(i) + ":00", null);
                listItems.add(defaultItem);
            }
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
            Intent intent = new Intent(this, MainActivity.class);
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
        alertDlg.setNeutralButton(
                "削除",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        d_position = position;
                        delete(null);
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

                }
                break;
            default:
                break;
        }

    }
    public void delete(View v){
        new AlertDialog.Builder(this)
                .setTitle("登録内容の削除")
                .setMessage("本当に削除しますか？")
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {


                        //検索用のクエリ作成
                        RealmQuery<Reminder> reminderRealmQuery = realm.where(Reminder.class);
                        //インスタンス生成し、その中にすべてのデータを入れる 今回なら全てのデータ
                        final RealmResults<Reminder> reminders = reminderRealmQuery.equalTo("reminder_id",d_position).findAll();
                        if(reminders.size()!=0){
                            realm.beginTransaction();
                            reminders.get(0).setReminderMemo("");
                            reminders.get(0).setReminderTime(String.valueOf(d_position));
                            reminders.get(0).setReminderId(d_position);
                            realm.commitTransaction();
                        }
                        else{
                            realm.beginTransaction();

                            Reminder model = realm.createObject(Reminder.class);
                            model.setReminderId(d_position);
                            model.setReminderMemo("");
                            model.setReminderTime(String.valueOf(d_position));
                            realm.commitTransaction();
                        }
                        CustomListItem editItem = new CustomListItem(String.valueOf(d_position) + ":00", "");
                        listItems.set(d_position,editItem);

                        Intent intent=new Intent(ReminderActivity.this,ReminderActivity.class);
                        startActivity(intent);

                    }
                })
                .setNegativeButton("Cancel", null)
                .show();}
}