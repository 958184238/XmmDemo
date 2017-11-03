package com.exam.admin.rongyundemo;

import android.app.Application;
import android.content.Context;

/**
 * ========================
 * Name: App
 * Des:
 * User: 吴飞
 * Date: 2017/8/2 10:28
 * =========================
 */

public class App extends Application {

    private static App app;

    @Override
    public void onCreate() {
        super.onCreate();
//        RongIM.init(this);
        app = this;
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
//        MultiDex.install(this);
    }

    public static Context getInstance() {
        return app;
    }
}
