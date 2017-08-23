package org.t_robop.ikalendar;

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

public class ReminderEditActivity extends AppCompatActivity {

    EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reminder_edit);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        String getIntentTime = getIntent().getStringExtra("time");     //ReminderActivityでタップされたListの時間を取得
        Intent intent =getIntent();
        String note = intent.getStringExtra("note");
        editText = (EditText) findViewById(R.id.edit);
        TextView time = (TextView) findViewById(R.id.EditNowTime);

        time.setText(String.valueOf(getIntentTime)+"の予定を編集中");
        editText.setText(note);
    }
    public void cancelClick(View v) {
        finish();
    }

    public void addClick(View v) {
        int getIntentPosition = getIntent().getIntExtra("position",0);  //ReminderActivityのListViewでタップされたpositionを取得

        String text = editText.getText().toString();    //editした予定(文字列)を取得

        Intent intent = new Intent(this, ReminderActivity.class);
        intent.putExtra("note",text);   //予定(文字列)
        intent.putExtra("position",getIntentPosition);  //position
        setResult(RESULT_OK,intent);    //ReminderActivityのonActivityResultへ
        finish();
        //startActivity(intent);

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

