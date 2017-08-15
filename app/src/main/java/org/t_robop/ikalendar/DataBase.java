package org.t_robop.ikalendar;

import java.util.Date;

import io.realm.Realm;
import io.realm.RealmList;
import io.realm.RealmModel;
import io.realm.RealmObject;
import io.realm.annotations.Ignore;
import io.realm.annotations.PrimaryKey;


public class DataBase extends RealmObject {

    private RealmList<TimeTable> timetable;
    private RealmList<Reminder> reminder;
    private RealmList<Calender> calender;

    public RealmList getTimeTable(){
        return timetable;
    }

    public void setTimeTable(RealmList timetable) {
        this.timetable = timetable;
    }

    public RealmList getReminder(){
        return reminder;
    }

    public void setRiminder(RealmList reminder) {
        this.reminder = reminder;
    }

    public RealmList getCalender(){
        return calender;
    }

    public void setcalender(RealmList calender) {
        this.calender = calender;
    }


}
