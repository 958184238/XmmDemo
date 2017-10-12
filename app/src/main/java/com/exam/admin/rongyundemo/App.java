package com.exam.admin.rongyundemo;

import android.content.Context;
import android.support.multidex.MultiDexApplication;

import io.rong.imkit.RongIM;

/**
 * ========================
 * Name: App
 * Des:
 * User: 吴飞
 * Date: 2017/8/2 10:28
 * =========================
 */

public class App extends MultiDexApplication {

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
