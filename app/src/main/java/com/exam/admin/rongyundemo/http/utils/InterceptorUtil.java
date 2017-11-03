package com.exam.admin.rongyundemo.http.utils;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;

/**
 * @author wufei
 * @date 2017/11/1
 * @description
 */

public class InterceptorUtil {
    public static String TAG = "----";

    /**
     * 日志拦截器
     */
    public static HttpLoggingInterceptor logInterceptor() {
        return new HttpLoggingInterceptor()
                .setLevel(HttpLoggingInterceptor.Level.BODY);
    }

    /**
     * Interceptor拦截器
     *
     * @return
     */
    public static Interceptor headerInterceptor() {
        return new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request mRequest = chain.request();
                //在这里你可以做一些想做的事,比如token失效时,重新获取token
                //或者添加header等等,PS我会在下一篇文章总写拦截token方法
                return chain.proceed(mRequest);
            }
        };

    }
}
