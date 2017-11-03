package com.exam.admin.rongyundemo.http.utils;

import android.content.Context;

import com.exam.admin.rongyundemo.utils.ToastUtils;

import java.net.ConnectException;
import java.net.SocketTimeoutException;

/**
 * @author Administrator
 * @date 2017/4/15
 */

public class ApiErrorHelper {
    //异常统一处理
    public static void handleCommonError(Context context, Throwable e) {
        if (e instanceof SocketTimeoutException) {
            ToastUtils.show(context, "连接超时", 1);
        } else if (e instanceof ConnectException) {
            ToastUtils.show(context, "无网络连接", 1);
        } else if (e instanceof ApiException) {
            //ApiException处理
            String msg = ((ApiException) e).getMsg();
            if (msg.equals("数据库无对应版本") || msg.equals("accessToken error")) {
                return;
            } else {
                if (msg != null) {
                    ToastUtils.show(context, msg, 1);
                }
            }

        }
    }
}
