package org.t_robop.ikalendar.database;

import org.t_robop.ikalendar.Calender.Calender;
import org.t_robop.ikalendar.database.Reminder;
import org.t_robop.ikalendar.database.TimeTable;

import io.realm.RealmList;
import io.realm.RealmObject;


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
