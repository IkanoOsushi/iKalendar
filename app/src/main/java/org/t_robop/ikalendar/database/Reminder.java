package org.t_robop.ikalendar.database;

import java.util.Date;

import io.realm.RealmObject;

/**
 * Created by ika on 2017/08/15.
 */

public class Reminder extends RealmObject {
    private int reminder_id;
    private String reminder_time;
    private String reminder_memo;
    private String reminder_today;

    public int getReminderId() {
        return reminder_id;
    }

    public void setReminderId(int reminder_id) {
        this.reminder_id = reminder_id;
    }

    public String getReminderTime() {
        return reminder_time;
    }

    public void setReminderTime(String reminder_time) {
        this.reminder_time = reminder_time;
    }

    public String getReminderMemo() {
        return reminder_memo;
    }

    public void setReminderMemo(String reminder_memo) {
        this.reminder_memo = reminder_memo;
    }

    public String getReminderToday(){return reminder_today;}
    public void setReminderToday(String reminder_today){this.reminder_today = reminder_today;}

}
