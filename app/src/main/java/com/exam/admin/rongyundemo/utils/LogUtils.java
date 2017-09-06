package com.exam.admin.rongyundemo.utils;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

/**
 * ========================
 * Name: LogUtils
 * Des: 日志工具类
 * User: 吴飞
 * Date: 2017/8/2 17:29
 * =========================
 */

public class LogUtils {

    public static final String TAG = "wufei";
    public static final boolean DEBUG = true;

    public static void toast(Context context, String content) {
        Toast.makeText(context, content, Toast.LENGTH_SHORT).show();
    }

    public static void debug(String tag, String msg) {
        if (DEBUG) {
            Log.d(tag, msg);
        }
    }

    public static void debug(String msg) {
        if (DEBUG) {
            Log.d(TAG, msg);
        }
    }

    public static void error(String tag, String error) {

        if (DEBUG) {

            Log.e(tag, error);
        }
    }

    public static void error(String error) {

        if (DEBUG) {

            Log.e(TAG, error);
        }
    }
}
