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
    String note_id = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reminder_edit);

        //   String note = getIntent().getStringExtra("note");
        //   Log.d("aaaaa",note);


        editText = (EditText) findViewById(R.id.edit);
        time = (TextView) findViewById(R.id.EditNowTime);

        note_id = getIntent().getStringExtra("note_id");

        String digitime = "0:00";
        switch (note_id) {
            case "note0000":
                digitime = "00:00";
                break;
            case "note0100":
                digitime = "01:00";
                break;
            case "note0200":
                digitime = "02:00";
                break;
            case "note0300":
                digitime = "03:00";
                break;
            case "note0400":
                digitime = "04:00";
                break;
            case "note0500":
                digitime = "05:00";
                break;
            case "note0600":
                digitime = "06:00";
                break;
            case "note0700":
                digitime = "07:00";
                break;
            case "note0800":
                digitime = "08:00";
                break;
            case "note0900":
                digitime = "09:00";
                break;
            case "note1000":
                digitime = "10:00";
                break;
            case "note1100":
                digitime = "11:00";
                break;
            case "note1200":
                digitime = "12:00";
                break;
            case "note1300":
                digitime = "13:00";
                break;
            case "note1400":
                digitime = "14:00";
                break;
            case "note1500":
                digitime = "15:00";
                break;
            case "note1600":
                digitime = "16:00";
                break;
            case "note1700":
                digitime = "17:00";
                break;
            case "note1800":
                digitime = "18:00";
                break;
            case "note1900":
                digitime = "19:00";
                break;
            case "note2000":
                digitime = "20:00";
                break;
            case "note2100":
                digitime = "21:00";
                break;
            case "note2200":
                digitime = "22:00";
                break;
            case "note2300":
                digitime = "23:00";
                break;


        }
        time.setText(String.valueOf(digitime));

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
        String note = getIntent().getStringExtra("note");

        switch (note_id) {
            case "note0000":
                Intent intent = new Intent(this, ReminderActivity.class);
                intent.putExtra("note",text);
                intent.putExtra("note_id",note_id);
                startActivity(intent);
                break;
        }

    }
}
