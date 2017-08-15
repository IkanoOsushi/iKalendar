package org.t_robop.ikalendar;

import java.util.Date;

import io.realm.RealmObject;

/**
 * Created by ika on 2017/08/15.
 */

public class Reminder extends RealmObject {
    private int reminder_id;
    private Date reminder_time;
    private String reminder_memo;

    public int getReminderId() {
        return reminder_id;
    }

    public void setReminderId(int reminder_id) {
        this.reminder_id = reminder_id;
    }

    public Date getReminderTime() {
        return reminder_time;
    }

    public void setReminderTime(Date reminder_time) {
        this.reminder_time = reminder_time;
    }

    public String getReminderMemo() {
        return reminder_memo;
    }

    public void setReminderMemo(String reminder_memo) {
        this.reminder_memo = reminder_memo;
    }

}
