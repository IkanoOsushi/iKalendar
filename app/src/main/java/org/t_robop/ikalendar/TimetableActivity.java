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
                Log.d("aaaaa", String.valueOf(timetables.get(i)));
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