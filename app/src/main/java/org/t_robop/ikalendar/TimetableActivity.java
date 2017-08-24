package org.t_robop.ikalendar;


import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;

import io.realm.Realm;
import io.realm.RealmQuery;
import io.realm.RealmResults;

import static android.R.attr.color;
import static android.R.attr.entryValues;
import static android.R.attr.id;
import static android.R.attr.switchMinWidth;
import static android.R.attr.value;
import static android.R.id.button1;
import static java.security.AccessController.getContext;
import static org.t_robop.ikalendar.R.color.red;

public class TimetableActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    Realm realm;
    private View inputView;

    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timetable);

        Intent intent = getIntent();
        String timeTableColorData = intent.getStringExtra("colerSelect");
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


        //Database初期化
        Realm.init(this);
        realm = Realm.getDefaultInstance();


        //検索用のクエリ作成
        RealmQuery<TimeTable> timetableQuery = realm.where(TimeTable.class);
        //インスタンス生成し、その中にすべてのデータを入れる 今回なら全てのデータ
        RealmResults<TimeTable> timetables = timetableQuery.findAll();


        if (timetables.size() != 0) {
            //timetableの配列の要素の数が0の時の実行
            for (int i = 0; i < timetables.size(); i++) {
                // TODO ボタン振り分けのswitchを除去
                Button button = (Button) findViewById(getResources().getIdentifier(String.valueOf(timetables.get(i).getTimeTableId()), "id", getApplication().getPackageName()));
                // TODO カラーコードのswitchを除去しました 以下1行で対応
                button.setBackgroundColor(Color.parseColor(timetables.get(i).getTimeTableColorId()));
                //教科名表示
                button.setText(timetables.get(i).getTimeTableSub());
            }
        }
    }


    public void onClick(final View view) {

        //showDialog(CustomViewCallback)
        LayoutInflater factory = LayoutInflater.from(this);
        inputView = factory.inflate(R.layout.activity_timetable_dialog, null);

        AlertDialog.Builder builder = new AlertDialog.Builder(this); //アラートダイアログを作る
        builder.setView(inputView);

        TextView subText = (TextView) inputView.findViewById(R.id.subjectview);
        TextView classText = (TextView) inputView.findViewById(R.id.classTextview);
        TextView teaText = (TextView) inputView.findViewById(R.id.teacherTextview);
        TextView memoText = (TextView) inputView.findViewById(R.id.memoTextview);

        String rsName = getResources().getResourceEntryName(view.getId());
        //検索用のクエリ作成
        RealmQuery<TimeTable> timetableQuery = realm.where(TimeTable.class);
        //インスタンス生成し、その中にすべてのデータを入れる 今回なら全てのデータ
        RealmResults<TimeTable> timetables = timetableQuery.equalTo("time_table_id", rsName).findAll();

        if (timetables.size() != 0) {
            subText.setText(timetables.get(0).getTimeTableSub());
            classText.setText(timetables.get(0).getTimeTableClass());
            teaText.setText(timetables.get(0).getTimeTableTea());
            memoText.setText(timetables.get(0).getTimeTableMemo());
        }


        builder.setNegativeButton("戻る", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });
        if (subText.length() != 0) {

            builder.setNeutralButton("編集", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Intent myintent = new Intent(getApplicationContext(), TimetableEditActivity.class);//intentして画面遷移
                    String rsName = getResources().getResourceEntryName(view.getId());
                    myintent.putExtra("TTKey", rsName);//TTキーで受け渡す

                    startActivity(myintent);
                }
            });
            AlertDialog dialog = builder.create();
            dialog.show();
        } else {
            Intent myintent = new Intent(getApplication(), TimetableEditActivity.class);
            rsName = getResources().getResourceEntryName(view.getId());
            myintent.putExtra("TTKey", rsName);//TTキーで受け渡す
            startActivity(myintent);
        }


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
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.time_menu, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_call) {
            Intent intent = new Intent(this, TableTimeTodayActivity.class);
            startActivity(intent);
        }
        return true;
    }


}