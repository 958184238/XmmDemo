package com.exam.admin.rongyundemo.http.retrofit;

import com.exam.admin.rongyundemo.http.utils.InterceptorUtil;
import com.exam.admin.rongyundemo.http.utils.MyGsonConverterFactory;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

/**
 * @author Administrator
 * @date 2017/4/14
 */

public class RetrofitHelper {

    public static Retrofit createRetrofit(String baseUrl) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(okHttpClient())
                .addConverterFactory(MyGsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        return retrofit;
    }

//    //不带请求头,用于获取最新token
//    public static Retrofit createRetrofit() {
//        Retrofit retrofit = new Retrofit.Builder()
//                .baseUrl(SealBaseUrl.QC_USER)
//                .client(okHttpClientWithOutHeader())
//                .addConverterFactory(MyGsonConverterFactory.create())
//                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
//                .build();
//        return retrofit;
//    }

    /**
     * 带拦截器
     *
     * @return
     */
    private static OkHttpClient okHttpClient() {
        OkHttpClient httpClient = new OkHttpClient.Builder()
                .addInterceptor(InterceptorUtil.headerInterceptor())
                .addInterceptor(InterceptorUtil.logInterceptor())
                .connectTimeout(10, TimeUnit.SECONDS)
                .writeTimeout(10, TimeUnit.SECONDS)
                .readTimeout(10, TimeUnit.SECONDS)
                .build();
        return httpClient;
    }

    /**
     * 不带拦截器
     *
     * @return
     */
    private static OkHttpClient okHttpClientWithOutHeader() {
        OkHttpClient httpClient = new OkHttpClient.Builder()
                .connectTimeout(10, TimeUnit.SECONDS)
                .writeTimeout(10, TimeUnit.SECONDS)
                .readTimeout(10, TimeUnit.SECONDS)
                .build();
        return httpClient;
    }
}
