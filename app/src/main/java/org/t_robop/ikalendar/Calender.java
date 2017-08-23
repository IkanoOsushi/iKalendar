package org.t_robop.ikalendar;

import java.util.Date;

import io.realm.RealmObject;

/**
 * Created by ika on 2017/08/15.
 */

public class Calender extends RealmObject {

    private int calendar_id;
    private String calendar_title;
    private Date calendar_start_date;
    private Date calendar_end_date;
    private int calendar_start_hourofday;
    private int calendar_end_hourofday;
    private int calendar_start_minute;
    private int calendar_end_minute;

    public int getCalendarId() {
        return calendar_id;
    }

    public void setCalendarId(int calendar_id) {
        this.calendar_id = calendar_id;
    }

    public String getCalendarTitle() {
        return calendar_title;
    }

    public void setCalendarTitle(String calendar_title) {
        this.calendar_title = calendar_title;
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

    public int getCalendarStartHourOfDay(){
        return calendar_start_hourofday;
    }

    public void setCalendarStartHourOfDay(int calendar_start_hourofday){
        this.calendar_start_hourofday = calendar_start_hourofday;
    }

    public int getCalendarEndHourOfDay(){
        return calendar_end_hourofday;
    }

    public void setCalendarEndHourOfDay(int calendar_end_hourofday){
        this.calendar_end_hourofday = calendar_end_hourofday;
    }

    public int getCalendarStartMinute(){
        return calendar_start_minute;
    }

    public void setCalendarStartMinute(int calendar_start_minute){
        this.calendar_start_minute = calendar_start_minute;
    }

    public int getCalendarEndMinute(){
        return calendar_end_minute;
    }

    public void setCalendarEndMinute(int calendar_end_minute){
        this.calendar_end_minute = calendar_end_minute;
    }
}
