package org.t_robop.ikalendar;


import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class TimetableActivity extends AppCompatActivity {
    private View inputView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timetable);

    }

    /* ここは詳細を表示するだけなので下の処理はSubjectEditActivityに書いて
    *  OKとかのボタンも消して閉じるボタンと編集ボタンだけ置きましょう
    *  setPositiveButtonの中には何も処理を書かない、setNegativeButtonには編集場面アクティビティに飛ぶ処理を書く
    *
    *  レイアウトのEditTextをTextViewにする
    *  ここを表示する時に、前に入力した情報を表示しておくように*/

    public void onClick(final View view) {
        //showDialog(CustomViewCallback)
        LayoutInflater factory = LayoutInflater.from(this);
        inputView = factory.inflate(R.layout.activity_timetable_dialog, null);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setView(inputView);

        builder.setPositiveButton("保存", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int whichButton) {
                //教科名、先生、教室をとってきて表示
                TextView subjectname = (EditText) inputView.findViewById(R.id.subEdit);
                String subjecttext = subjectname.getText().toString();
                TextView roomname = (EditText) inputView.findViewById(R.id.roomEdit);
                String roomtext = roomname.getText().toString();
                TextView teachername = (EditText) inputView.findViewById(R.id.teacEdit);
                String teachertext = teachername.getText().toString();
                TextView memo = (EditText) inputView.findViewById(R.id.memoEdit);
                String memotext = memo.getText().toString();

                Button button = (Button) findViewById(view.getId());
                button.setText(subjecttext + "\n\n" + roomtext);


            }
        });
        builder.setNegativeButton("戻る", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });

        builder.setNeutralButton("編集", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent myintent = new Intent(getApplicationContext(), TimetableEditActivity.class);
                myintent.putExtra("TimetableEditActivity", "");
                startActivity(myintent);

            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }
}