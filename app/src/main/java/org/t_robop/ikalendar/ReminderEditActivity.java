package org.t_robop.ikalendar;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
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
        String getIntentTime = getIntent().getStringExtra("time");     //ReminderActivityでタップされたListの時間を取得
        Intent intent =getIntent();
        String note = intent.getStringExtra("note");
        editText = (EditText) findViewById(R.id.edit);
        TextView time = (TextView) findViewById(R.id.EditNowTime);

        time.setText(String.valueOf(getIntentTime)+"の予定を編集中");
        editText.setText(note);
    }
    public void spaceClick(View v){
        editText.setText("仏説摩訶般若波羅蜜多心経\n" +
                "\n" +
                "観自在菩薩　行深般若波羅蜜多時　照見五蘊皆空　\n" +
                "度一切苦厄　舎利子　色不異空　空不異色　色即是空　\n" +
                "空即是色　受想行識亦復如是　舎利子　是諸法空相　\n" +
                "不生不滅　不垢不浄　不増不減　是故空中　\n" +
                "無色　無受想行識　無眼耳鼻舌身意　無色声香味触法　\n" +
                "無眼界　乃至無意識界　無無明亦　無無明尽　\n" +
                "乃至無老死　亦無老死尽　無苦集滅道　無智亦無得　\n" +
                "以無所得故　菩提薩埵　依般若波羅蜜多故　\n" +
                "心無罣礙　無罣礙故　無有恐怖　遠離一切顛倒夢想　\n" +
                "究竟涅槃　三世諸仏　依般若波羅蜜多故　\n" +
                "得阿耨多羅三藐三菩提　故知般若波羅蜜多　\n" +
                "是大神呪　是大明呪　是無上呪　是無等等呪　\n" +
                "能除一切苦　真実不虚　故説般若波羅蜜多呪　\n" +
                "即説呪日　羯諦　羯諦　波羅羯諦　波羅僧羯諦　\n" +
                "菩提薩婆訶　般若心経　");
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
}

