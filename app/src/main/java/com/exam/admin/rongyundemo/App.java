package com.exam.admin.rongyundemo;

import android.app.Application;
import android.content.Context;

import io.rong.imkit.RongIM;

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
        RongIM.init(this);
    }


    public static Context getInstance() {
        return app;
    }
}
