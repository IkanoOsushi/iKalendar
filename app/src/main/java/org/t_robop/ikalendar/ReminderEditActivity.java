package org.t_robop.ikalendar;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class ReminderEditActivity extends AppCompatActivity {
    int listPos;
    EditText editText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reminder_edit);
        Intent intent = getIntent();
        String memo = intent.getStringExtra("memo");
        listPos = intent.getIntExtra("pos",-1);
        //Log.d("aaaaaa",String.valueOf(listPos));
        editText = (EditText)findViewById(R.id.edit);
        editText.setText(memo);

    }
    public void set(View v){
        String text = editText.getText().toString();

        Intent intent = new Intent(this,ReminderActivity.class);
        intent.putExtra("memo",text);
        intent.putExtra("pos",listPos);
        startActivity(intent);
    }


}
