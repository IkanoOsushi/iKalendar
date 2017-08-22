package org.t_robop.ikalendar;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import io.realm.Realm;
import io.realm.RealmQuery;
import io.realm.RealmResults;

import java.text.CollationElementIterator;
import java.util.Calendar;
import java.util.HashMap;

public class TableTimeTodayActivity extends AppCompatActivity implements View.OnClickListener{

    Calendar cal = Calendar.getInstance();
    int week = cal.get(Calendar.DAY_OF_WEEK);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_taimetable_today);


    }

    @Override
    public void onClick(View view){

        switch (view.getId()){
            case R.id.fooder_exit:
                if(week==1){
                    week=7;
                }else{
                    week--;
                }
                datachanege();
                break;
            case R.id.fooder_next:
                if(week==7){
                    week=1;
                }else {
                    week++;
                }
                datachanege();
                break;
        }

    }

public void datachanege(){
        switch (week){
            case 1:{
                //データを曜日ごとに変える
            }
            case 2:{}
            case 3:{}
            case 4:{}
            case 5:{}
            case 6:{}
            case 7:{}
        }
    }

}

