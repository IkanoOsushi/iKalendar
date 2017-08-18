package org.t_robop.ikalendar;

import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.sql.Time;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmQuery;
import io.realm.RealmResults;

import static org.t_robop.ikalendar.R.*;
import static org.t_robop.ikalendar.R.id.editText;
import static org.t_robop.ikalendar.R.id.memoText;
import static org.t_robop.ikalendar.R.id.subEdit;
import static org.t_robop.ikalendar.R.id.time;

public class TimetableEditActivity extends AppCompatActivity {
    Realm realm;
    EditText subText;
    EditText roomText;
    EditText teacText;
   // EditText memoText;
    private View inputView;
    int timeTableColor =0;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //戻るボタンの処理
        setContentView(layout.activity_timetable_edit);


        Intent intent = getIntent();
        final String value = intent.getStringExtra("TTKey");//設定したkeyで取り出す

        Realm.init(this);


        realm = Realm.getDefaultInstance();
        final Button buckbuttom = (Button) findViewById(R.id.editback);
        final Button savebutton = (Button) findViewById(id.editstorage);
        subText = (EditText) findViewById(id.subEdit);
        roomText = (EditText)findViewById(id.roomEdit);
        teacText =(EditText)findViewById(id.teacEdit);
        savebutton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {


                realm.beginTransaction();

                TimeTable model = realm.createObject(TimeTable.class);

                model.setTimeTableId(value);
                model.setTimeTableSub(subText.getText().toString());
                model.setTimeTableClass(roomText.getText().toString());
                model.setTimeTableTea(teacText.getText().toString());
               // model.setTimeTableMemo(memoText.getText().toString());
                model.setTimeTableColorId(timeTableColor);

                realm.commitTransaction();
                Toast.makeText(TimetableEditActivity.this,"保存しました",Toast.LENGTH_SHORT).show();

                Intent intent = new Intent();
                intent.setClass(TimetableEditActivity.this,TimetableActivity.class);

                intent.putExtra("colerSelect",timeTableColor);
                startActivity(intent);

            }
        });


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
                timeTableColor = 1;
                Toast.makeText(this, "赤を選択しました。", Toast.LENGTH_LONG).show();

                break;
            case R.id.button45:
                timeTableColor = 2;
                Toast.makeText(this, "ピンクを選択しました。", Toast.LENGTH_LONG).show();

                break;
            case R.id.button46:
                timeTableColor = 3;
                Toast.makeText(this, "オレンジを選択しました。", Toast.LENGTH_LONG).show();

                break;
            case R.id.button47:
                timeTableColor = 4;
                Toast.makeText(this, "黄色を選択しました。", Toast.LENGTH_LONG).show();

                break;
            case R.id.button48:
                timeTableColor = 5;
                Toast.makeText(this, "黄緑を選択しました。", Toast.LENGTH_LONG).show();

                break;
            case R.id.button49:
                timeTableColor = 6;
                Toast.makeText(this, "緑を選択しました。", Toast.LENGTH_LONG).show();

                break;
            case R.id.button50:
                timeTableColor = 7;
                Toast.makeText(this, "水色を選択しました。", Toast.LENGTH_LONG).show();

                break;
            case R.id.button51:
                timeTableColor = 8;
                Toast.makeText(this, "青を選択しました。", Toast.LENGTH_LONG).show();

                break;
            case R.id.button52:
                timeTableColor = 9;
                Toast.makeText(this, "紫を選択しました。", Toast.LENGTH_LONG).show();

                break;
            case R.id.button53:
                timeTableColor = 10;
                Toast.makeText(this, "グレーを選択しました。", Toast.LENGTH_LONG).show();

                break;
        }
    }
}