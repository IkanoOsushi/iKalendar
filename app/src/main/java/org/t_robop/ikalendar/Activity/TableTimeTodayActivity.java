package org.t_robop.ikalendar.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.t_robop.ikalendar.R;
import org.t_robop.ikalendar.database.TimeTable;

import io.realm.Realm;
import io.realm.RealmQuery;
import io.realm.RealmResults;

import java.util.Calendar;

public class TableTimeTodayActivity extends AppCompatActivity implements View.OnClickListener {

    Realm realm;

    Calendar cal = Calendar.getInstance();
    // 何曜日を表示するか
    int week = cal.get(Calendar.DAY_OF_WEEK);
    String[] weekStr = {"日曜日","月曜日","火曜日","水曜日","木曜日","金曜日","土曜日"};


    TextView[] subjectText = new TextView[5];
    TextView[] classText = new TextView[5];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_taimetable_today);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        Button backbutton = (Button) findViewById(R.id.fooder_exit);
        backbutton.setOnClickListener(this);
        Button nextbutton = (Button) findViewById(R.id.fooder_next);
        nextbutton.setOnClickListener(this);

        Intent intent = this.getIntent();
        int thisweek = intent.getIntExtra("thisweek", week);
        week = thisweek;

        //Database初期化
        Realm.init(this);
        realm = Realm.getDefaultInstance();

        for (int i = 0; i < subjectText.length; i++){
            // i+1の理由は、 iは0からだが、時間割は1からのため
            subjectText[i] = (TextView) findViewById(getResources().getIdentifier("subject"+(i+1), "id", getApplication().getPackageName()));
            classText[i] = (TextView)findViewById(getResources().getIdentifier("class"+(i+1), "id", getApplication().getPackageName()));
        }


        TextView week_text = (TextView) findViewById(R.id.nowDay);

        //検索用のクエリ作成
        RealmQuery<TimeTable> timetableQuery = realm.where(TimeTable.class);
        //インスタンス生成し、その中にすべてのデータを入れる 今回なら全てのデータ
        RealmResults<TimeTable> timeTablestoday = timetableQuery.equalTo("time_table_day_of_week", thisweek).findAll().sort("time_table_id");
        if (timeTablestoday.size() != 0) {
            for (int i = 0; i < timeTablestoday.size(); i++) {

                int hour = timeTablestoday.get(i).getTimeTableRow();

                subjectText[hour].setText(timeTablestoday.get(i).getTimeTableSub());
                classText[hour].setText(timeTablestoday.get(i).getTimeTableClass());

            }
        }

        week_text.setText(weekStr[thisweek-1]);
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.fooder_exit:
                if (week == 1) {
                    week = 7;
                    Intent intent = new Intent(this, TableTimeTodayActivity.class);
                    intent.putExtra("thisweek", week);
                    startActivity(intent);
                } else {
                    week--;
                    Intent intent = new Intent(this, TableTimeTodayActivity.class);
                    intent.putExtra("thisweek", week);
                    startActivity(intent);
                }
                break;
            case R.id.fooder_next:
                if (week == 7) {
                    week = 1;
                    Intent intent = new Intent(this, TableTimeTodayActivity.class);
                    intent.putExtra("thisweek", week);
                    startActivity(intent);
                } else {
                    week++;
                    Intent intent = new Intent(this, TableTimeTodayActivity.class);
                    intent.putExtra("thisweek", week);
                    startActivity(intent);
                }
                break;
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.time_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        boolean result = true;
        if (id == R.id.action_call) {
            Intent intent = new Intent(this, TimetableActivity.class);
            startActivity(intent);
        }
        switch (id) {
            case android.R.id.home:
                Intent intent = new Intent(this, TimetableActivity.class);
                startActivity(intent);
                startActivity(intent);
                break;
            default:
                result = super.onOptionsItemSelected(item);

        }
        return true;
    }

}