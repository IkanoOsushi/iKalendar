package org.t_robop.ikalendar;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

import io.realm.Realm;
import io.realm.RealmQuery;
import io.realm.RealmResults;

public class ReminderEditActivity extends AppCompatActivity {

    EditText editText;
    Realm realm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reminder_edit);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        Realm.init(this);
        realm = Realm.getDefaultInstance();

        String getIntentTime = getIntent().getStringExtra("time");     //ReminderActivityでタップされたListの時間を取得
        Intent intent =getIntent();
        String note = intent.getStringExtra("note");
        editText = (EditText) findViewById(R.id.edit);
        TextView time = (TextView) findViewById(R.id.EditNowTime);

        time.setText(String.valueOf(getIntentTime)+"の予定を編集中");
        editText.setText(note);
    }
    public void description(View v){
        new AlertDialog.Builder(this)
                .setTitle("このリマインダーについて")
                .setMessage("従来のリマインダーと違い、" +
                        "設定できる範囲は一日分となっています。\n" +
                        "そのため利用用途についても大きく異なります。\n" +
                        "\n崩れてしまった生活リズムの再構築をしたい方、" +
                        "治したい、新たに付けたい生活習慣のある方を対象とし、" +
                        "思い通りの生活習慣へ徐々に変えていく手助けをすることを目的とした" +
                        "「ルーティーンリマインダー」です。\n" +
                        "\n手順として、\n普段の生活パターンを一通り登録し\n" +
                        "治したい、新たに付けたい習慣を加えて" +
                        "登録したタイムラインを毎日確認して、矯正を行います。\n" +
                        "\n利用者のより良い生活へのサポートを一役買うことができれば幸いです")
                .setPositiveButton("OK", null)
                .show();
    }

    public void cancelClick(View v) {
        finish();
    }
    public void delete(View v){
        new AlertDialog.Builder(this)
                .setTitle("登録内容の削除")
                .setMessage("本当に削除しますか？")
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        editText.setText("");
                        addClick(null);
                    }
                })
                .setNegativeButton("Cancel", null)
                .show();}

    public void addClick(View v) {
        int getIntentPosition = getIntent().getIntExtra("position",0);  //ReminderActivityのListViewでタップされたpositionを取得
        Intent intent =getIntent();
        String note = intent.getStringExtra("note");
        String time = intent.getStringExtra("time");
        String text = editText.getText().toString();    //editした予定(文字列)を取得

        //検索用のクエリ作成
        RealmQuery<Reminder> reminderRealmQuery = realm.where(Reminder.class);
        //インスタンス生成し、その中にすべてのデータを入れる 今回なら全てのデータ
        final RealmResults<Reminder> reminders = reminderRealmQuery.equalTo("reminder_id",getIntentPosition).findAll();
        if(reminders.size()!=0){
            realm.beginTransaction();
            reminders.get(0).setReminderMemo(text);
            reminders.get(0).setReminderTime(time);
            reminders.get(0).setReminderId(getIntentPosition);
            realm.commitTransaction();
            Intent intent2 = new Intent();
            intent2.setClass(ReminderEditActivity.this,ReminderActivity.class);
            startActivity(intent2);
        }
        else{
            realm.beginTransaction();

            Reminder model = realm.createObject(Reminder.class);
            model.setReminderId(getIntentPosition);
            model.setReminderMemo(text);
            model.setReminderTime(time);
            realm.commitTransaction();
            Intent intent3 = new Intent();
            intent3.setClass(this,ReminderActivity.class);
            startActivity(intent3);
        }

    }

    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        boolean result = true;
        switch (id) {
            case android.R.id.home:
                Intent intent = new Intent(this, ReminderActivity.class);
                startActivity(intent);
                startActivity(intent);
                break;
            default:
                result = super.onOptionsItemSelected(item);

        }
        return true;
    }

}

