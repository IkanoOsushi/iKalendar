package org.t_robop.ikalendar;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class CustomListAdapter extends ArrayAdapter<CustomListItem> {
    private int mResource;
    private List<CustomListItem> mItems;
    private LayoutInflater mInflater;

    /**
     * コンストラクタ
     * @param context コンテキスト
     * @param resource リソースID
     * @param items リストビューの要素
     */

    //コンストラクタ
    public CustomListAdapter(Context context, int resource, List<CustomListItem> items){
        super(context,resource,items);

        mResource = resource;
        mItems = items;
        mInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    //view作成
    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        View view;

        if(convertView != null){
            view = convertView;
        }
        else {
            view = mInflater.inflate(mResource,null);
        }
        CustomListItem item = mItems.get(position);     //ListViewに表示する要素を取得

        //時間(文字列)を設定
        TextView time = (TextView)view.findViewById(R.id.time);
        time.setText(item.getmTime());

        //予定(文字列)を設定
        TextView noteText = (TextView)view.findViewById(R.id.noteText);
        noteText.setText(item.getmNoteText());

        return view;
    }
}
