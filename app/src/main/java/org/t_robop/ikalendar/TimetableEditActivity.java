package org.t_robop.ikalendar;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import org.t_robop.ikalendar.database.TimeTable;

import io.realm.Realm;
import io.realm.RealmQuery;

import static org.t_robop.ikalendar.R.*;

public class TimetableEditActivity extends AppCompatActivity {
    Realm realm;
    EditText subText;
    EditText roomText;
    EditText teacText;
    EditText memoText;
    String timeTableColor ="#ffffff";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //戻るボタンの処理
        setContentView(layout.activity_timetable_edit);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        subText = (EditText) findViewById(id.subEdit);
        roomText = (EditText)findViewById(id.roomEdit);
        teacText =(EditText)findViewById(id.teacEdit);
        memoText=(EditText)findViewById(id.memoEdit);


        Intent intent = getIntent();
        final String buttonId = intent.getStringExtra("buttonId");//押されたボタンのIDを取得
        final int week = intent.getIntExtra("buttonTag",0);

        Realm.init(this);
        realm = Realm.getDefaultInstance();


        //検索用のクエリ作成
        RealmQuery<TimeTable> timetableQuery = realm.where(TimeTable.class);
        //インスタンス生成し、その中にすべてのデータを入れる 今回なら全てのデータ
        final TimeTable timetable = timetableQuery.equalTo("time_table_id",buttonId).findFirst();
        if(timetable!= null){
            subText.setText(timetable.getTimeTableSub());
            roomText.setText(timetable.getTimeTableClass());
            teacText.setText(timetable.getTimeTableTea());
            memoText.setText(timetable.getTimeTableMemo());
        }

        findViewById(R.id.editback).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplication(), TimetableActivity.class);
                startActivity(intent);
            }
        });

        findViewById(id.editstorage).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editstorageClick(timetable,buttonId,subText.getText().toString(),roomText.getText().toString(),teacText.getText().toString(),
                        memoText.getText().toString(),week);
            }
        });

    }

    public void colerSelected(View v) {
        // Viewの色を10進数で取得
        int colorInt = ((ColorDrawable) v.getBackground()).getColor();
        // 16進数に変換
        String hex = Integer.toHexString(colorInt);
        // いつも使ってる#付きに変換
        timeTableColor = "#"+hex;
        // タグから色の名前を取得
        String ColorStr = (String) v.getTag();
        Toast.makeText(this, ColorStr+"を選択しました。", Toast.LENGTH_SHORT).show();
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

    // 上書き用
    void realmSave(TimeTable model,String timeTableId,String timeTableSub,String timeTableClass,String timeTableTea,
                   String timeTableMemo,String timeTableColorId,int week,String buttonId){

        realm.beginTransaction();
        model.setTimeTableId(timeTableId);
        model.setTimeTableSub(timeTableSub);
        model.setTimeTableClass(timeTableClass);
        model.setTimeTableTea(timeTableTea);
        model.setTimeTableMemo(timeTableMemo);
        model.setTimeTableColorId(timeTableColorId);
        model.setTimeTableDayOfWeek(week);

        model.setTimeTableRow(idToRow(buttonId));

        realm.commitTransaction();

    }

    // 新規作成用
    void realmSave(String timeTableId,String timeTableSub,String timeTableClass,String timeTableTea,
                   String timeTableMemo,String timeTableColorId,int week,String buttonId){
        realm.beginTransaction();
        TimeTable model = realm.createObject(TimeTable.class);
        model.setTimeTableId(timeTableId);
        model.setTimeTableSub(timeTableSub);
        model.setTimeTableClass(timeTableClass);
        model.setTimeTableTea(timeTableTea);
        model.setTimeTableMemo(timeTableMemo);
        model.setTimeTableColorId(timeTableColorId);
        model.setTimeTableDayOfWeek(week);

        model.setTimeTableRow(idToRow(buttonId));

        realm.commitTransaction();

    }
    int idToRow(String buttonId){
        int num =  Integer.valueOf(buttonId.substring(6,8));


        return (int) (num/6.1f);
    }
    void editstorageClick(TimeTable timetable,String buttonId,String sub,String room,String teac,String memo,int week){
        if (timetable == null){
            realmSave(buttonId,sub,room,teac,memo,
                    timeTableColor,week,buttonId);
        }else{
            realmSave(timetable,buttonId,sub,room,teac,memo, timeTableColor,week,buttonId);
        }
        Toast.makeText(TimetableEditActivity.this,"保存しました",Toast.LENGTH_LONG).show();
        Intent intent = new Intent();
        intent.setClass(TimetableEditActivity.this,TimetableActivity.class);
        intent.putExtra("colerSelectkey",timeTableColor);
        startActivity(intent);


    }

}