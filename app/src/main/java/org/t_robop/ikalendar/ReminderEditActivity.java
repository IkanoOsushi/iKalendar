package org.t_robop.ikalendar;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class ReminderEditActivity extends AppCompatActivity {

    EditText editText;
    TextView time;
    String getIntentTime = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reminder_edit);

        getIntentTime = getIntent().getStringExtra("time");     //ReminderActivityでタップされたListの時間を取得

        editText = (EditText) findViewById(R.id.edit);
        time = (TextView) findViewById(R.id.EditNowTime);

        time.setText(String.valueOf(getIntentTime));

    }

    public void cancelClick(View v) {
        finish();
    }
    public void addClick(View v) {
        String text = editText.getText().toString();    //editした予定(文字列)を取得

        Intent intent = new Intent(this, ReminderActivity.class);
        intent.putExtra("note",text);   //予定(文字列)
        startActivity(intent);

    }
}
