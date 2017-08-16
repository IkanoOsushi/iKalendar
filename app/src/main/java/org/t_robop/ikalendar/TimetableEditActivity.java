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
import static org.t_robop.ikalendar.R.id.subEdit;
import static org.t_robop.ikalendar.R.id.time;

public class TimetableEditActivity extends AppCompatActivity {
      Realm realm;
    EditText subText;
    EditText roomText;
    EditText teacText;
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
        final Button buckbuttom = (Button) findViewById(R.id.button17);
        final Button savebutton = (Button) findViewById(id.button24);
        subText = (EditText) findViewById(id.subEdit);
        roomText = (EditText)findViewById(id.roomEdit);
        teacText =(EditText)findViewById(id.teacEdit);
        savebutton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
//                String idtext = value;
//                String subtext = subText.getText().toString();
//                String roomtext = roomText.getText().toString();
//                String teactext = teacText.getText().toString();

                realm.beginTransaction();

                TimeTable model = realm.createObject(TimeTable.class);

                model.setTimeTableId(value);
                model.setTimeTableSub(subText.getText().toString());
                model.setTimeTableClass(roomText.getText().toString());
                model.setTimeTableTea(teacText.getText().toString());
                model.setTimeTableColorId(timeTableColor);

                realm.commitTransaction();

//                //書き込みたいデータをインスタンスに入れる
//                model.setSessionId(1);
//                model.setTimeTableSub(editText.getText().toString());
//
//                //トランザクション終了 (データを書き込む)
//                realm.commitTransaction();

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




//ボタン処理を書く


    /*public void onClick(final View view) {
        //showDialog(CustomViewCallback)
        LayoutInflater factory = LayoutInflater.from(this);
        inputView = factory.inflate(R.layout.activity_timetable_edit, null);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setView(inputView);

        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int whichButton) {

                EditText sub = (EditText) inputView.findViewById(R.id.subEdit);
                String text = sub.getText().toString();
                EditText room = (EditText) inputView.findViewById(R.id.roomEdit);
                String text2 = room.getText().toString();
                EditText teac = (EditText) inputView.findViewById(R.id.teacEdit);
                String text3 = teac.getText().toString();
                EditText memo = (EditText) inputView.findViewById(R.id.memoEdit);
                String text4 = memo.getText().toString();

                Button button = (Button) findViewById(view.getId());
                button.setText(text);


            }
        });


        builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }*/