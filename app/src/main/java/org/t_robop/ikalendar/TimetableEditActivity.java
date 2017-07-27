package org.t_robop.ikalendar;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class TimetableEditActivity extends AppCompatActivity {
    private View inputView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timetable_edit);
    }
//ぼたんのしょりをかく
    //



    /*public void onClick(final View view) {
        //showDialog(CustomViewCallback)
        LayoutInflater factory = LayoutInflater.from(this);
        inputView = factory.inflate(R.layout.activity_timetable_edit, null);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setView(inputView);

        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int whichButton) {

                EditText sub = (EditText) inputView.findViewById(R.id.subEdit);
                String text = sub.getText().toString();
                EditText room = (EditText) inputView.findViewById(R.id.roomEdit);
                String text2 = room.getText().toString();
                EditText teac = (EditText) inputView.findViewById(R.id.teacEdit);
                String text3 = teac.getText().toString();
                EditText memo = (EditText) inputView.findViewById(R.id.memoEdit);
                String text4 = memo.getText().toString();

                Button button = (Button) findViewById(view.getId());
                button.setText(text);


            }
        });


        builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }*/
}

