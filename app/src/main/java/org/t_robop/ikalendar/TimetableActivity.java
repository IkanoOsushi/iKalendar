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
        implements NavigationView.OnNavigationItemSelectedListener{
    Realm realm;
    private View inputView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timetable);

        Intent intent =  getIntent();
        String timeTableColorData =  intent.getStringExtra("colerSelect");

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

        if(timetables.size() != 0){
            //timetableの配列の要素の数が0の時の実行
            for(int i=0; i<timetables.size(); i++){
                //timetablesの要素の数だけ回す
                switch (String.valueOf(timetables.get(i).getTimeTableId())){
                    //string型（ボタン名）に直してtimetablesのi番目のIDをとってきて条件分け
                    case "button37":
                        Button button37 = (Button)findViewById(R.id.button37);//関連付け
                        button37.setText(timetables.get(i).getTimeTableSub());//教科名表示
                      //  switch (timeTableColorData){
                           /* case 1:
                                button37.setBackgroundColor(Color.parseColor("#d5fc5555"));
                                break;
                            case 2:
                                button37.setBackgroundColor(Color.parseColor("#d5fd7ccb"));
                                break;
                            case 3:
                                button37.setBackgroundColor(Color.parseColor("#d5fda956"));
                                break;
                            case 4:
                                button37.setBackgroundColor(Color.parseColor("#d5ffe449"));
                                break;
                            case 5:
                                button37.setBackgroundColor(Color.parseColor("#d5a7ff49"));
                                break;
                            case 6:
                                button37.setBackgroundColor(Color.parseColor("#d529c203"));
                                break;
                            case 7:
                                button37.setBackgroundColor(Color.parseColor("d549dbff"));
                                break;
                            case 8:
                                button37.setBackgroundColor(Color.parseColor("#d53066f9"));
                                break;
                            case 9:
                                button37.setBackgroundColor(Color.parseColor("#d59b49ff"));
                                break;
                            case 10:
                                button37.setBackgroundColor(Color.parseColor("#d5c4c4c4"));
                                break;*/
                      //  }

                        break;
                    case "button36":
                        Button button36 = (Button)findViewById(R.id.button36);
                        button36.setText(timetables.get(i).getTimeTableSub());
                        break;
                    case "button35":
                        Button button35 = (Button)findViewById(R.id.button35);
                        button35.setText(timetables.get(i).getTimeTableSub());
                        break;
                    case "button34":
                        Button button34 = (Button)findViewById(R.id.button34);
                        button34.setText(timetables.get(i).getTimeTableSub());
                        break;
                    case "button33":
                        Button button33 = (Button)findViewById(R.id.button33);
                        button33.setText(timetables.get(i).getTimeTableSub());
                        break;
                    case "button32":
                        Button button32 = (Button)findViewById(R.id.button32);
                        button32.setText(timetables.get(i).getTimeTableSub());
                        break;

                    case "button30":
                        Button button30 = (Button)findViewById(R.id.button30);
                        button30.setText(timetables.get(i).getTimeTableSub());
                        break;
                    case "button29":
                        Button button29 = (Button)findViewById(R.id.button29);
                        button29.setText(timetables.get(i).getTimeTableSub());
                        break;
                    case "button28":
                        Button button28 = (Button)findViewById(R.id.button28);
                        button28.setText(timetables.get(i).getTimeTableSub());
                        break;
                    case "button27":
                        Button button27 = (Button)findViewById(R.id.button27);
                        button27.setText(timetables.get(i).getTimeTableSub());
                        break;
                    case "button26":
                        Button button26 = (Button)findViewById(R.id.button26);
                        button26.setText(timetables.get(i).getTimeTableSub());
                        break;
                    case "button25":
                        Button button25 = (Button)findViewById(R.id.button25);
                        button25.setText(timetables.get(i).getTimeTableSub());
                        break;

                    case "button23":
                        Button button23 = (Button)findViewById(R.id.button23);
                        button23.setText(timetables.get(i).getTimeTableSub());
                        break;
                    case "button22":
                        Button button22 = (Button)findViewById(R.id.button22);
                        button22.setText(timetables.get(i).getTimeTableSub());
                        break;
                    case "button21":
                        Button button21 = (Button)findViewById(R.id.button21);
                        button21.setText(timetables.get(i).getTimeTableSub());
                        break;
                    case "button20":
                        Button button20 = (Button)findViewById(R.id.button20);
                        button20.setText(timetables.get(i).getTimeTableSub());
                        break;
                    case "button19":
                        Button button19 = (Button)findViewById(R.id.button19);
                        button19.setText(timetables.get(i).getTimeTableSub());
                        break;

                    case "button16":
                        Button button16 = (Button)findViewById(R.id.button16);
                        button16.setText(timetables.get(i).getTimeTableSub());
                        break;
                    case "button15":
                        Button button15 = (Button)findViewById(R.id.button15);
                        button15.setText(timetables.get(i).getTimeTableSub());
                        break;
                    case "button14":
                        Button button14 = (Button)findViewById(R.id.button14);
                        button14.setText(timetables.get(i).getTimeTableSub());
                        break;
                    case "button13":
                        Button button13 = (Button)findViewById(R.id.button13);
                        button13.setText(timetables.get(i).getTimeTableSub());
                        break;
                    case "button12":
                        Button button12 = (Button)findViewById(R.id.button12);
                        button12.setText(timetables.get(i).getTimeTableSub());
                        break;
                    case "button11":
                        Button button11 = (Button)findViewById(R.id.button11);
                        button11.setText(timetables.get(i).getTimeTableSub());
                        break;

                    case "button6":
                        Button button6 = (Button)findViewById(R.id.button6);
                        button6.setText(timetables.get(i).getTimeTableSub());
                        break;
                    case "button5":
                        Button button5 = (Button)findViewById(R.id.button5);
                        button5.setText(timetables.get(i).getTimeTableSub());
                        break;
                    case "button4":
                        Button button4 = (Button)findViewById(R.id.button4);
                        button4.setText(timetables.get(i).getTimeTableSub());
                        break;
                    case "button3":
                        Button button3 = (Button)findViewById(R.id.button3);
                        button3.setText(timetables.get(i).getTimeTableSub());
                        break;
                    case "button2":
                        Button button2 = (Button)findViewById(R.id.button2);
                        button2.setText(timetables.get(i).getTimeTableSub());
                        break;
                    case "button":
                        Button button = (Button)findViewById(R.id.button37);
                        button.setText(timetables.get(i).getTimeTableSub());
                        break;

                }
            }
        }

    }

    public void onClick(final View view) {
        //showDialog(CustomViewCallback)
        LayoutInflater factory = LayoutInflater.from(this);
        inputView = factory.inflate(R.layout.activity_timetable_dialog, null);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setView(inputView);

        builder.setNegativeButton("戻る", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });
        builder.setNeutralButton("編集", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent myintent = new Intent(getApplicationContext(), TimetableEditActivity.class);
                String rsName = getResources().getResourceEntryName(view.getId());
                myintent.putExtra("TTKey",rsName);
                startActivity(myintent);
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
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