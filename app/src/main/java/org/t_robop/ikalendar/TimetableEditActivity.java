package org.t_robop.ikalendar;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.sql.Time;
import java.util.Arrays;
import java.util.Calendar;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmQuery;
import io.realm.RealmResults;

import static org.t_robop.ikalendar.R.*;
import static org.t_robop.ikalendar.R.id.memoText;
import static org.t_robop.ikalendar.R.id.subEdit;
import static org.t_robop.ikalendar.R.id.time;

public class TimetableEditActivity extends AppCompatActivity {
    Realm realm;
    EditText subText;
    EditText roomText;
    EditText teacText;
    EditText memoText;
    private View inputView;
    String timeTableColor ="#ffffff";
    int weeks;

    String[] mon = {"button37","button30","button23","button16","button6"};
    String[] tus = {"button36","button29","button22","button15","button5"};
    String[] wed = {"button35","button28","button21","button14","button4"};
    String[] thu = {"button34","button27","button20","button13","button3"};
    String[] fri = {"button33","button26","button19","button12","button2"};
    String[] sat = {"button32","button25","button18","button11","button"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //戻るボタンの処理
        setContentView(layout.activity_timetable_edit);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        Intent intent = getIntent();
        final String value = intent.getStringExtra("TTKey");//設定したkeyで取り出す

        if(Arrays.asList(mon).contains(value)){
            weeks=2;
        }
       else if(Arrays.asList(tus).contains(value)){
            weeks=3;
        }
      else  if(Arrays.asList(wed).contains(value)){
            weeks=4;
        }
        else if(Arrays.asList(thu).contains(value)){
            weeks=5;
        }
       else if(Arrays.asList(fri).contains(value)){
            weeks=6;
        }
       else if(Arrays.asList(sat).contains(value)){
            weeks=7;
        }

        Realm.init(this);
        realm = Realm.getDefaultInstance();


        final Button buckbuttom = (Button) findViewById(R.id.editback);
        final Button savebutton = (Button) findViewById(id.editstorage);
        subText = (EditText) findViewById(id.subEdit);
        roomText = (EditText)findViewById(id.roomEdit);
        teacText =(EditText)findViewById(id.teacEdit);
        memoText=(EditText)findViewById(id.memoEdit);


        //検索用のクエリ作成
        RealmQuery<TimeTable> timetableQuery = realm.where(TimeTable.class);
        //インスタンス生成し、その中にすべてのデータを入れる 今回なら全てのデータ
        final RealmResults<TimeTable> timetables = timetableQuery.equalTo("time_table_id",value).findAll();
        if(timetables.size()!=0){
            subText.setText(timetables.get(0).getTimeTableSub());
            roomText.setText(timetables.get(0).getTimeTableClass());
            teacText.setText(timetables.get(0).getTimeTableTea());
            memoText.setText(timetables.get(0).getTimeTableMemo());

            savebutton.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {


                    realm.beginTransaction();

                    timetables.get(0).setTimeTableId(value);
                    timetables.get(0).setTimeTableSub(subText.getText().toString());
                    timetables.get(0).setTimeTableClass(roomText.getText().toString());
                    timetables.get(0).setTimeTableTea(teacText.getText().toString());
                    timetables.get(0).setTimeTableMemo(memoText.getText().toString());
                    timetables.get(0).setTimeTableColorId(timeTableColor);
                    timetables.get(0).setTimeTableDayOfWeek(weeks);

                    realm.commitTransaction();
                    Toast.makeText(TimetableEditActivity.this,"保存しました",Toast.LENGTH_SHORT).show();

                    Intent intent = new Intent();
                    intent.setClass(TimetableEditActivity.this,TimetableActivity.class);

                    intent.putExtra("colerSelect",timeTableColor);
                    startActivity(intent);

                }
            });
        }
        else{

            savebutton.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {


                    realm.beginTransaction();

                    TimeTable model = realm.createObject(TimeTable.class);

                    model.setTimeTableId(value);
                    model.setTimeTableSub(subText.getText().toString());
                    model.setTimeTableClass(roomText.getText().toString());
                    model.setTimeTableTea(teacText.getText().toString());
                    model.setTimeTableMemo(memoText.getText().toString());
                    model.setTimeTableColorId(timeTableColor);
                    model.setTimeTableDayOfWeek(weeks);

                realm.commitTransaction();
                Toast.makeText(TimetableEditActivity.this,"保存しました",Toast.LENGTH_LONG).show();

                Intent intent = new Intent();
                intent.setClass(TimetableEditActivity.this,TimetableActivity.class);

                intent.putExtra("colerSelectkey",timeTableColor);
                startActivity(intent);

                }
            });


        }



        buckbuttom.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {

                //ボタン押した後の処理
                Intent intent = new Intent(getApplication(), TimetableActivity.class);
                startActivity(intent);
            }

        });
    }

    public void colerSelected(View v) {
        switch (v.getId()) {
            case R.id.button44:
                timeTableColor="#d5fc5555";
                Toast.makeText(this, "赤を選択しました。", Toast.LENGTH_SHORT).show();

                break;
            case R.id.button45:
                timeTableColor = "#d5fd7ccb";
                Toast.makeText(this, "ピンクを選択しました。", Toast.LENGTH_SHORT).show();

                break;
            case R.id.button46:
                timeTableColor ="#d5fda956";
                Toast.makeText(this, "オレンジを選択しました。", Toast.LENGTH_SHORT).show();

                break;
            case R.id.button47:
                timeTableColor ="#d5ffe449";
                Toast.makeText(this, "黄色を選択しました。", Toast.LENGTH_SHORT).show();

                break;
            case R.id.button48:
                timeTableColor = "#d5a7ff49";
                Toast.makeText(this, "黄緑を選択しました。", Toast.LENGTH_SHORT).show();

                break;
            case R.id.button49:
                timeTableColor = "#d529c203";
                Toast.makeText(this, "緑を選択しました。", Toast.LENGTH_SHORT).show();

                break;
            case R.id.button50:
                timeTableColor = "#d549dbff";
                Toast.makeText(this, "水色を選択しました。", Toast.LENGTH_SHORT).show();

                break;
            case R.id.button51:
                timeTableColor = "#d53066f9";
                Toast.makeText(this, "青を選択しました。", Toast.LENGTH_SHORT).show();

                break;
            case R.id.button52:
                timeTableColor ="#d59b49ff" ;
                Toast.makeText(this, "紫を選択しました。", Toast.LENGTH_SHORT).show();

                break;
            case R.id.button53:
                timeTableColor = "#d5c4c4c4";
                Toast.makeText(this, "グレーを選択しました。", Toast.LENGTH_SHORT).show();

                break;
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        boolean result = true;
        switch (id) {
            case android.R.id.home:
                Intent intent = new Intent(this, TimetableActivity.class);
                startActivity(intent);
                startActivity(intent);
                break;
            default:
                result = super.onOptionsItemSelected(item);

        }
        return true;
    }

}