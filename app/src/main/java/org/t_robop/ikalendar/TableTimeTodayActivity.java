package org.t_robop.ikalendar;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.widget.EditText;
import android.widget.TextView;

import java.text.CollationElementIterator;

public class TableTimeTodayActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_taimetable_today);
        TextView texv = (TextView) findViewById(R.id.textView8);

    }
/*文字数よりは比率を固定する処理を書く。
 *10文字以上は省略する処理を書く。
        //文字数を省略する処理
    /*public TextView getTextView() {
        //文字数省略の処理
         if ( > 10) {
            TextView textView = (TextView) findViewById(R.id.);
            textView.setEllipsize(TextUtils.TruncateAt.END);
            textView.setSingleLine(true);
            return textView;
        }
    */
}


