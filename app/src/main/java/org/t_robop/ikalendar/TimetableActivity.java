package org.t_robop.ikalendar;


import android.content.DialogInterface;
import android.content.Intent;
import android.provider.ContactsContract;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import io.realm.Realm;
import io.realm.RealmQuery;
import io.realm.RealmResults;

import static android.R.attr.id;
import static android.R.attr.switchMinWidth;
import static android.R.id.button1;
import static java.security.AccessController.getContext;

public class TimetableActivity extends AppCompatActivity {
    Realm realm;
    private View inputView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timetable);

        //Database初期化
        Realm.init(this);
        realm = Realm.getDefaultInstance();



        //検索用のクエリ作成
        RealmQuery<TimeTable> folderQuery = realm.where(TimeTable.class);
        //インスタンス生成し、その中にすべてのデータを入れる 今回なら全てのデータ
        RealmResults<TimeTable> timetables = folderQuery.findAll();


        if(timetables.size() != 0){
            for(int i=0; i<timetables.size(); i++){
                switch (String.valueOf(timetables.get(i).getTimeTableId())){
                    case "button37":
                        Button button37 = (Button)findViewById(R.id.button37);
                        button37.setText(timetables.get(i).getTimeTableSub());
                        break;
                    case "button36":
                        Button button36 = (Button)findViewById(R.id.button36);
                        button36.setText(timetables.get(i).getTimeTableSub());
                        break;
                    case "button35":
                        Button button35 = (Button)findViewById(R.id.button35);
                        button35.setText(timetables.get(i).getTimeTableSub());
                        break;
                    case "button34":
                        Button button34 = (Button)findViewById(R.id.button34);
                        button34.setText(timetables.get(i).getTimeTableSub());
                        break;
                    case "button33":
                        Button button33 = (Button)findViewById(R.id.button33);
                        button33.setText(timetables.get(i).getTimeTableSub());
                        break;
                    case "button32":
                        Button button32 = (Button)findViewById(R.id.button32);
                        button32.setText(timetables.get(i).getTimeTableSub());
                        break;

                    case "button30":
                        Button button30 = (Button)findViewById(R.id.button30);
                        button30.setText(timetables.get(i).getTimeTableSub());
                        break;
                    case "button29":
                        Button button29 = (Button)findViewById(R.id.button29);
                        button29.setText(timetables.get(i).getTimeTableSub());
                        break;
                    case "button28":
                        Button button28 = (Button)findViewById(R.id.button28);
                        button28.setText(timetables.get(i).getTimeTableSub());
                        break;
                    case "button27":
                        Button button27 = (Button)findViewById(R.id.button27);
                        button27.setText(timetables.get(i).getTimeTableSub());
                        break;
                    case "button26":
                        Button button26 = (Button)findViewById(R.id.button26);
                        button26.setText(timetables.get(i).getTimeTableSub());
                        break;
                    case "button25":
                        Button button25 = (Button)findViewById(R.id.button25);
                        button25.setText(timetables.get(i).getTimeTableSub());
                        break;

                    case "button23":
                        Button button23 = (Button)findViewById(R.id.button23);
                        button23.setText(timetables.get(i).getTimeTableSub());
                        break;
                    case "button22":
                        Button button22 = (Button)findViewById(R.id.button22);
                        button22.setText(timetables.get(i).getTimeTableSub());
                        break;
                    case "button21":
                        Button button21 = (Button)findViewById(R.id.button21);
                        button21.setText(timetables.get(i).getTimeTableSub());
                        break;
                    case "button20":
                        Button button20 = (Button)findViewById(R.id.button20);
                        button20.setText(timetables.get(i).getTimeTableSub());
                        break;
                    case "button19":
                        Button button19 = (Button)findViewById(R.id.button19);
                        button19.setText(timetables.get(i).getTimeTableSub());
                        break;

                    case "button16":
                        Button button16 = (Button)findViewById(R.id.button16);
                        button16.setText(timetables.get(i).getTimeTableSub());
                        break;
                    case "button15":
                        Button button15 = (Button)findViewById(R.id.button15);
                        button15.setText(timetables.get(i).getTimeTableSub());
                        break;
                    case "button14":
                        Button button14 = (Button)findViewById(R.id.button14);
                        button14.setText(timetables.get(i).getTimeTableSub());
                        break;
                    case "button13":
                        Button button13 = (Button)findViewById(R.id.button13);
                        button13.setText(timetables.get(i).getTimeTableSub());
                        break;
                    case "button12":
                        Button button12 = (Button)findViewById(R.id.button12);
                        button12.setText(timetables.get(i).getTimeTableSub());
                        break;
                    case "button11":
                        Button button11 = (Button)findViewById(R.id.button11);
                        button11.setText(timetables.get(i).getTimeTableSub());
                        break;

                    case "button6":
                        Button button6 = (Button)findViewById(R.id.button6);
                        button6.setText(timetables.get(i).getTimeTableSub());
                        break;
                    case "button5":
                        Button button5 = (Button)findViewById(R.id.button5);
                        button5.setText(timetables.get(i).getTimeTableSub());
                        break;
                    case "button4":
                        Button button4 = (Button)findViewById(R.id.button4);
                        button4.setText(timetables.get(i).getTimeTableSub());
                        break;
                    case "button3":
                        Button button3 = (Button)findViewById(R.id.button3);
                        button3.setText(timetables.get(i).getTimeTableSub());
                        break;
                    case "button2":
                        Button button2 = (Button)findViewById(R.id.button2);
                        button2.setText(timetables.get(i).getTimeTableSub());
                        break;
                    case "button":
                        Button button = (Button)findViewById(R.id.button37);
                        button.setText(timetables.get(i).getTimeTableSub());
                        break;

                }
            }
        }

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
                //TextViewの型を宣言（関連付け）
                TextView subjectname = (EditText) inputView.findViewById(R.id.subEdit);
                TextView roomname = (EditText) inputView.findViewById(R.id.roomEdit);
                TextView teachername = (EditText) inputView.findViewById(R.id.teacEdit);
                TextView memo = (EditText) inputView.findViewById(R.id.memoEdit);
                //ボタンの型を関連付け
                Button button = (Button) findViewById(view.getId());
                //string型のTextをとってきてstring型にする
                String subjecttext;
                subjecttext = subjectname.getText().toString();
                String roomtext;
                roomtext= roomname.getText().toString();
                String teachertext;
                teachertext= teachername.getText().toString();
                String memotext;
                memotext= memo.getText().toString();

                //ボタンに文字を表示される
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
                String rsName = getResources().getResourceEntryName(view.getId());
                myintent.putExtra("TTKey",rsName);
                startActivity(myintent);
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
        //テキストビューでダイアログのなかで表示する
       // TextView subjectnameView=(TextView) dialog.findViewById(R.id.subEdit);
        //TextView roomnameView=(TextView) dialog.findViewById(R.id.roomEdit);
        //TextView teachernameView=(TextView) dialog.findViewById(R.id.teacEdit);
        //文字を表示する
       // subjectnameView.setText("test");
        //roomnameView.setText("test");
        //teachernameView.setText("test");
    }
}