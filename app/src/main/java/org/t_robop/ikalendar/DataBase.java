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

    @Ignore
    private int             sessionId;

    // Standard getters & setters generated by your IDE…
    public String getName() { return name; }
    public void   setName(String name) { this.name = name; }
    public int    getId() { return id; }
    public void   setId(int id) { this.id = id; }
    public int    getSessionId() { return sessionId; }
    public void   setSessionId(int sessionId) { this.sessionId = sessionId; }
    public int    getCalendar_id(){return calendar_id;}
    public void   setCalendar_id(int calendar_id){this.calendar_id=calendar_id;}
    public String getCalendarmemo() {return calendar_memo ;}
    public void   setCalendarmemo(String calendar_memo) { this.calendar_memo = calendar_memo ;}
    public Date   getCalendarstartdate() {return calendar_start_date;}
    public void   setCalendarstartdate(Date calendar_start_date){this.calendar_start_date = calendar_start_date;}
    public Date   getCalendarenddate() { return calendar_end_date;}
    public void   setCalendarenddate(Date calendar_end_date){this.calendar_end_date = calendar_end_date;}


}
