package org.t_robop.ikalendar;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;

import java.util.Date;

public class CalenderAddPlanActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calender_add_plan);

        //intentしてきたデータを取得
        Intent i = getIntent();
        String PlanDate = i.getStringExtra("date");

        //アクションバーをセット
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(PlanDate + " 予定を追加");
        toolbar.setTitleTextColor(Color.WHITE);
        setSupportActionBar(toolbar);

    }

    public void setTimeButton(View v){
        CalenderDatePicker timePicker = new CalenderDatePicker();
        timePicker.show(getSupportFragmentManager(),"timePicker");
    }

    public void onReturnValue(int hourOfDay,int minute) {
        EditText setPlanTime = (EditText)findViewById(R.id.setPlanTime);
        setPlanTime.setText(String.valueOf(hourOfDay) + " 時 "+ String.valueOf(minute) + " 分 ");
    }
}
