package org.t_robop.ikalendar;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.TextView;

public class ReminderEditActivity extends AppCompatActivity {

    EditText editText;
    TextView nowtime;
    String note_id = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reminder_edit);

        //   String note = getIntent().getStringExtra("note");
        //   Log.d("aaaaa",note);

        editText = (EditText) findViewById(R.id.edit);
        nowtime = (TextView) findViewById(R.id.EditNowTime);

        Intent intent = getIntent();
        String time = intent.getStringExtra("e_time");

        //note_id = getIntent().getStringExtra("note_id");

        nowtime.setText(String.valueOf(time)+":00の予定を編集中");

    }

    public void cancel(View v) {
        finish();
        /*
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("note","");
        startActivity(intent);
        */
    }
    public void add2(View v) {
        String text = editText.getText().toString();
        //String note = getIntent().getStringExtra("note");

                Intent intent = new Intent(this, ReminderActivity.class);
                intent.putExtra("note",text);
                intent.putExtra("note_id",note_id);
                startActivity(intent);

    }
}
