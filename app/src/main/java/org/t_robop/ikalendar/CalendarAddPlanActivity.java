package org.t_robop.ikalendar;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
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

public class CalendarAddPlanActivity extends AppCompatActivity {

    int planStartHourOfDay;
    int planStartMinute;
    int planEndHourOfDay;
    int planEndMinute;

    //上書きか新規作成化を判別するためのflag
    boolean editFlag = false;

    String beforeEditTitle;
    String beforeEditStartTime;
    String beforeEditEndTime;
    int beforeEditStartHourOfDay;
    int beforeEditStartMinute;
    int beforeEditEndHourOfDay;
    int beforeEditEndMinute;

    String planDate;

    Realm realm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar_add_plan);

        //intentしてきたデータを取得
        Intent i = getIntent();
        String PlanDate = i.getStringExtra("date");
        String PlanDateFromDayView = i.getStringExtra("dayviewdate");

        boolean fromDayViewFlag = i.getBooleanExtra("fromDayView",false);

        if(fromDayViewFlag == true) {
            planDate = PlanDateFromDayView;
        }else {
            planDate = PlanDate;
        }

        editFlag = getIntent().getBooleanExtra("EditFlag",false);

        EditText setPlanName = (EditText)findViewById(R.id.setPlanName);
        EditText setStartPlanTime = (EditText)findViewById(R.id.setStartPlanTime);
        EditText setEndPlanTime = (EditText)findViewById(R.id.setEndPlanTime);

        if ( editFlag == true ){

            setPlanName.setText(getIntent().getStringExtra("PlanTitle"));
            setStartPlanTime.setText(getIntent().getStringExtra("StartTime"));
            setEndPlanTime.setText(getIntent().getStringExtra("EndTime"));

            planDate = getIntent().getStringExtra("PlanDate");

            beforeEditTitle = getIntent().getStringExtra("PlanTitle");
            beforeEditStartTime = getIntent().getStringExtra("StartTime");
            beforeEditEndTime = getIntent().getStringExtra("EndTime");
            beforeEditStartHourOfDay = getIntent().getIntExtra("StartHourOfDay",0);
            beforeEditStartMinute = getIntent().getIntExtra("StartMinute",0);
            beforeEditEndHourOfDay = getIntent().getIntExtra("EndHourOfDay",0);
            beforeEditEndMinute = getIntent().getIntExtra("EndMinute",0);

            planStartHourOfDay = getIntent().getIntExtra("StartHourOfDay",0);
            planStartMinute = getIntent().getIntExtra("StartMinute",0);
            planEndHourOfDay = getIntent().getIntExtra("EndHourOfDay",0);
            planEndMinute = getIntent().getIntExtra("EndMinute",0);
        }

        setStartPlanTime.setOnTouchListener(startOtl);
        setEndPlanTime.setOnTouchListener(endOtl);

        //アクションバーをセット
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(planDate + " 予定を追加");
        toolbar.setTitleTextColor(Color.WHITE);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

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

        if((PlanName.equals("") || planStartHourOfDay == 0 || planEndHourOfDay == 0) && editFlag == false){

            Toast.makeText(getApplicationContext(), "項目に入力してください",
                    Toast.LENGTH_SHORT).show();
        }

        else if(planStartHourOfDay > planEndHourOfDay || (planStartHourOfDay == planEndHourOfDay && planStartMinute > planEndMinute)){
            Toast.makeText(getApplicationContext(), "開始時刻と終了時刻を正しく入力してください",
                    Toast.LENGTH_SHORT).show();
        }

        else {
            sStartPlanTime = (planDate + " " + String.valueOf(planStartHourOfDay) + ":" + String.valueOf(planStartMinute));
            Date dStartPlanTime = sdf.parse(sStartPlanTime);

            sEndPlanTime = (planDate + " " + String.valueOf(planEndHourOfDay) + ":" + String.valueOf(planEndMinute));
            Date dEndPlanTime = sdf.parse(sEndPlanTime);

            //realm追加開始
            //realm.beginTransaction();

            if(editFlag == true){

                //検索用のクエリ作成
                RealmQuery<Calender> calenderQuery = realm.where(Calender.class);
                //インスタンス生成
                //final RealmResults<Calender> calenders = calenderQuery.equalTo("calendar_title",beforeEditTitle).equalTo("calendar_end_minute",beforeEditEndMinute).findAll();
                final RealmResults<Calender> calenders = calenderQuery.findAll();
                for(int i = 0;i < calenders.size();i++){
                if(calenders.get(i).getCalendarTitle().equals(beforeEditTitle) && calenders.get(i).getCalendarStartHourOfDay() == beforeEditStartHourOfDay && calenders.get(i).getCalendarStartMinute() == beforeEditStartMinute && calenders.get(i).getCalendarEndHourOfDay() == beforeEditEndHourOfDay && calenders.get(i).getCalendarEndMinute() == beforeEditEndMinute) {
                    realm.beginTransaction();

                    calenders.get(0).setCalendarTitle(PlanName);
                    calenders.get(0).setCalendarstartdate(dStartPlanTime);
                    calenders.get(0).setCalendarenddate(dEndPlanTime);
                    calenders.get(0).setCalendarStartHourOfDay(planStartHourOfDay);
                    calenders.get(0).setCalendarEndHourOfDay(planEndHourOfDay);
                    calenders.get(0).setCalendarStartMinute(planStartMinute);
                    calenders.get(0).setCalendarEndMinute(planEndMinute);

                    realm.commitTransaction();
                    Toast.makeText(CalendarAddPlanActivity.this, "保存しました", Toast.LENGTH_SHORT).show();

                    Intent intent = new Intent(CalendarAddPlanActivity.this, org.t_robop.ikalendar.CalendarActivity.class);
                    startActivity(intent);
                }
                }
            }
            else {
                realm.beginTransaction();

                Calender model = realm.createObject(Calender.class);

                model.setCalendarTitle(PlanName);
                model.setCalendarstartdate(dStartPlanTime);
                model.setCalendarenddate(dEndPlanTime);
                model.setCalendarStartHourOfDay(planStartHourOfDay);
                model.setCalendarStartMinute(planStartMinute);
                model.setCalendarEndHourOfDay(planEndHourOfDay);
                model.setCalendarEndMinute(planEndMinute);

                realm.commitTransaction();
                Toast.makeText(CalendarAddPlanActivity.this, "保存しました", Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(CalendarAddPlanActivity.this, org.t_robop.ikalendar.CalendarActivity.class);
                startActivity(intent);
            }
        }

    }

}
