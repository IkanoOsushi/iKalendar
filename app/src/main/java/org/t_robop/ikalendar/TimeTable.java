package org.t_robop.ikalendar;

import io.realm.RealmObject;

/**
 * Created by ika on 2017/08/15.
 */

public class TimeTable extends RealmObject {
    private String time_table_id;
    private int time_table_color_id;
    private String time_table_sub;
    private String time_table_memo;
    private String time_table_class;
    private String time_table_tea;

    public String getTimeTableId() {
        return time_table_id;
    }

    public void setTimeTableId(String time_table_id) {
        this.time_table_id = time_table_id;
    }

    public int getTimeTableColorId() {
        return time_table_color_id;
    }

    public void setTimeTableColorId(int time_table_color_id) {
        this.time_table_color_id = time_table_color_id;
    }

    public String getTimeTableSub() {
        return time_table_sub;
    }

    public void setTimeTableSub(String time_table_sub) {
        this.time_table_sub = time_table_sub;
    }

    public String getTimeTableMemo() {
        return time_table_memo;
    }

    public void setTimeTableMemo(String time_table_memo) {
        this.time_table_memo = time_table_memo;
    }

    public String getTimeTableClass() {
        return time_table_class;
    }

    public void setTimeTableClass(String time_table_class) {
        this.time_table_class = time_table_class;
    }

    public String getTimeTableTea() {
        return time_table_tea;
    }

    public void setTimeTableTea(String time_table_tea) {
        this.time_table_tea = time_table_tea;
    }

}
