package org.t_robop.ikalendar;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import io.realm.Realm;
import io.realm.RealmQuery;
import io.realm.RealmResults;

public class CalenderAddPlanActivity extends AppCompatActivity {

    int planStartHourOfDay;
    int planStartMinute;

    int planEndHourOfDay;
    int planEndMinute;

    String planDate;

    Realm realm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calender_add_plan);

        //intentしてきたデータを取得
        Intent i = getIntent();
        String PlanDate = i.getStringExtra("date");

        planDate = PlanDate;

        EditText setStartPlanTime = (EditText)findViewById(R.id.setStartPlanTime);
        EditText setEndPlanTime = (EditText)findViewById(R.id.setEndPlanTime);

        setStartPlanTime.setOnTouchListener(startOtl);
        setEndPlanTime.setOnTouchListener(endOtl);

        //アクションバーをセット
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(PlanDate + " 予定を追加");
        toolbar.setTitleTextColor(Color.WHITE);
        setSupportActionBar(toolbar);

        //Database初期化
        Realm.init(this);
        realm = Realm.getDefaultInstance();

    }

    //
    private View.OnTouchListener startOtl = new View.OnTouchListener() {
        public boolean onTouch (View v, MotionEvent event) {
            if(event.getAction() == MotionEvent.ACTION_UP){
                // event.getAction()で押したか離したか等が取れる。
               // MotionEvent.ACTION_UPが取れたら離したとき。
               // MotionEvent.ACTION_DOWNが取れたときは押したときです。
                CalendarStartDatePicker timePicker = new CalendarStartDatePicker();
                timePicker.show(getSupportFragmentManager(),"timePicker");
            }
            return true; // the listener has consumed the event
    }
    };

    private View.OnTouchListener endOtl = new View.OnTouchListener() {
        public boolean onTouch (View v, MotionEvent event) {
            if(event.getAction() == MotionEvent.ACTION_UP){
                // event.getAction()で押したか離したか等が取れる。
                // MotionEvent.ACTION_UPが取れたら離したとき。
                // MotionEvent.ACTION_DOWNが取れたときは押したときです。
                CalendarEndDatePicker timePicker = new CalendarEndDatePicker();
                timePicker.show(getSupportFragmentManager(),"timePicker");
            }
            return true; // the listener has consumed the event
        }
    };

//    public void setTimeButton(View v){
//        CalendarStartDatePicker timePicker = new CalendarStartDatePicker();
//        timePicker.show(getSupportFragmentManager(),"timePicker");
//    }

    public void onReturnStartDate(int startHourOfDay,int startMinute) {
        EditText setStartPlanTime = (EditText)findViewById(R.id.setStartPlanTime);
        setStartPlanTime.setText(String.valueOf(startHourOfDay) + " 時 "+ String.valueOf(startMinute) + " 分 ");
        planStartHourOfDay = startHourOfDay;
        planStartMinute = startMinute;
    }

    public void onReturnEndDate(int endHourOfDay,int endMinute){
        EditText setEndPlanTime = (EditText)findViewById(R.id.setEndPlanTime);
        setEndPlanTime.setText(String.valueOf(endHourOfDay) + " 時 "+ String.valueOf(endMinute) + " 分 ");
        planEndHourOfDay = endHourOfDay;
        planEndMinute = endMinute;

    }

    public void setPlanRegistration(View v) throws ParseException {
        String sStartPlanTime;
        String sEndPlanTime;
        EditText setPlanName = (EditText)findViewById(R.id.setPlanName);
        String PlanName = String.valueOf(setPlanName.getText());

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy年 MM月 dd日 HH:mm");

        sStartPlanTime = (planDate +" "+String.valueOf(planStartHourOfDay)+":"+String.valueOf(planStartMinute));
        Date dStartPlanTime= sdf.parse(sStartPlanTime);

        sEndPlanTime = (planDate +" "+String.valueOf(planEndHourOfDay)+":"+String.valueOf(planEndMinute));
        Date dEndPlanTime= sdf.parse(sEndPlanTime);

        //realm追加開始
        realm.beginTransaction();

        Calender model = realm.createObject(Calender.class);

        model.setCalendarTitle(PlanName);
        model.setCalendarstartdate(dStartPlanTime);
        model.setCalendarenddate(dEndPlanTime);

        realm.commitTransaction();
        Toast.makeText(CalenderAddPlanActivity.this,"保存しました",Toast.LENGTH_SHORT).show();
        //検索用のクエリ作成
        RealmQuery<Calender> timetableQuery = realm.where(Calender.class);
        //インスタンス生成し、その中にすべてのデータを入れる 今回なら全てのデータ
        RealmResults<Calender> timetables = timetableQuery.findAll();
        for(int i=0; i<timetables.size(); i++) {

            Log.d("eeee",String.valueOf(timetables.get(i).getCalendarTitle()));
            Log.d("eeee",String.valueOf(timetables.get(i).getCalendarstartdate()));
            Log.d("eeee",String.valueOf(timetables.get(i).getCalendarenddate()));

        }

    }

}
