package org.t_robop.ikalendar;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;


public class DebugActivity extends AppCompatActivity {

    ListView listView;
    ArrayAdapter arrayAdapter;
    ArrayList<Class> arrayList;


    //ここに入れればデバッグアクティビティのリストに追加される
    Class[] DebugClass={CalendarActivity.class,MainActivity.class,ReminderActivity.class,TimetableActivity.class};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_debug);

        listView = (ListView) findViewById(R.id.list);
        arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1);
        arrayList=new ArrayList<>();
        for (int i=0;i<DebugClass.length;i++){
            arrayList.add(DebugClass[i]);
            arrayAdapter.add(DebugClass[i].getName().toString());
        }
        listView.setAdapter(arrayAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent=new Intent(view.getContext(),arrayList.get(position));
                startActivity(intent);
            }
        });


    }


}

