package org.t_robop.ikalendar;

import java.util.Date;

import io.realm.Realm;
import io.realm.RealmObject;
import io.realm.annotations.Ignore;


/**
 * Created by Kai on 2017/07/03.
 */

public class DataBase extends RealmObject {

    private String          name;
    private int             id;
    private int             calendar_id;
    private String          calendar_memo;
    private Date            calendar_start_date;
    private Date            calendar_end_date;
    private int             reminder_id;
    private Date            reminder_time;
    private String          reminder_memo;
    private int             time_table_id;
    private String          time_table_sub;
    private String          time_table_memo;
    private String          time_table_class;
    private String          time_table_tea;
    private String          time_table_color;

    @Ignore
    private int             sessionId;

    // Standard getters & setters generated by your IDE…
    public String getName() { return name; }
    public void   setName(String name) { this.name = name; }
    public int    getId() { return id; }
    public void   setId(int id) { this.id = id; }

    public int    getSessionId() { return sessionId; }
    public void   setSessionId(int sessionId) { this.sessionId = sessionId; }

    public int    getCalenderId(){return calendar_id;}
    public void   setCalenderId(int calendar_id){this.calendar_id=calendar_id;}
    public String getCalendarmemo() {return calendar_memo ;}
    public void   setCalendarmemo(String calendar_memo) { this.calendar_memo = calendar_memo ;}
    public Date   getCalendarstartdate() {return calendar_start_date;}
    public void   setCalendarstartdate(Date calendar_start_date){this.calendar_start_date = calendar_start_date;}
    public Date   getCalendarenddate() { return calendar_end_date;}
    public void   setCalendarenddate(Date calendar_end_date){this.calendar_end_date = calendar_end_date;}

    public int    getReminderId(){return reminder_id;}
    public void   setReminderId(int reminder_id){this.reminder_id=reminder_id;}
    public Date   getReminderTime(){return reminder_time;}
    public void   setReminderTime(Date reminder_time){this.reminder_time = reminder_time;}
    public String getReminderMemo(){return reminder_memo;}
    public void   setReminderMemo(String reminder_memo){this.reminder_memo = reminder_memo;}

    public int    getTimeTableId(){return time_table_id;}
    public void   setTimeTableId(int time_table_id){this.time_table_id=time_table_id;}
    public String getTimeTableSub(){return time_table_sub;}
    public void   setTimeTableSub(String time_table_sub){this.time_table_sub = time_table_sub;}
    public String getTimeTableMemo(){return time_table_memo;}
    public void   setTimeTableMemo(String time_table_memo){this.time_table_memo = time_table_memo;}
    public String getTimeTableClass(){return time_table_class;}
    public void   setTimeTableClass(String time_table_class){this.time_table_class = time_table_class;}
    public String getTimeTableTea(){return time_table_tea;}
    public void   setTimeTableTea(String time_table_tea){this.time_table_tea = time_table_tea;}
    public String getTimeTableColor(){return time_table_color;}
    public void   setTimeTableColor(String time_table_color){this.time_table_color = time_table_color;}





}
