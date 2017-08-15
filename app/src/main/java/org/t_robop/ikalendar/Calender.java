package org.t_robop.ikalendar;

import java.util.Date;

import io.realm.RealmObject;

/**
 * Created by ika on 2017/08/15.
 */

public class Calender extends RealmObject {

    private int calendar_id;
    private String calendar_memo;
    private Date calendar_start_date;
    private Date calendar_end_date;

    public int getCalenderId() {
        return calendar_id;
    }

    public void setCalenderId(int calendar_id) {
        this.calendar_id = calendar_id;
    }

    public String getCalendarmemo() {
        return calendar_memo;
    }

    public void setCalendarmemo(String calendar_memo) {
        this.calendar_memo = calendar_memo;
    }

    public Date getCalendarstartdate() {
        return calendar_start_date;
    }

    public void setCalendarstartdate(Date calendar_start_date) {
        this.calendar_start_date = calendar_start_date;
    }

    public Date getCalendarenddate() {
        return calendar_end_date;
    }

    public void setCalendarenddate(Date calendar_end_date) {
        this.calendar_end_date = calendar_end_date;
    }


}
