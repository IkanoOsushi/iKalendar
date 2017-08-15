package org.t_robop.ikalendar;

import android.app.Application;

import io.realm.Realm;

/**
 * Created by ika on 2017/08/15.
 */

public class app extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Realm.init(this);
    }
}