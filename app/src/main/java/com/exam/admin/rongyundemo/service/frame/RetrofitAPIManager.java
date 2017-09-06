package com.exam.admin.rongyundemo.service.frame;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;

/**
 * Created by Administrator on 2017/4/14.
 */

public class RetrofitAPIManager {

    public static Retrofit creatRetrofit(String baseUrl) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(getOkHttpClient())
                .addConverterFactory(MyGsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        return retrofit;
    }

//    //不带请求头,用于获取最新token
//    public static Retrofit creatRetrofit() {
//        Retrofit retrofit = new Retrofit.Builder()
//                .baseUrl(SealBaseUrl.QC_USER)
//                .client(getOkHttpClientWithOutHeader())
//                .addConverterFactory(MyGsonConverterFactory.create())
//                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
//                .build();
//        return retrofit;
//    }

    /**
     * 带拦截器
     * @return
     */
    public static OkHttpClient getOkHttpClient() {
        OkHttpClient httpClient = new OkHttpClient.Builder()
                .addInterceptor(new TokenInterceptor())
                .connectTimeout(10, TimeUnit.SECONDS)
                .writeTimeout(10, TimeUnit.SECONDS)
                .readTimeout(10, TimeUnit.SECONDS)
                .build();
        return httpClient;
    }

    /**
     * 不带拦截器
     * @return
     */
    public static OkHttpClient getOkHttpClientWithOutHeader() {
        OkHttpClient httpClient = new OkHttpClient.Builder()
                .connectTimeout(10, TimeUnit.SECONDS)
                .writeTimeout(10, TimeUnit.SECONDS)
                .readTimeout(10, TimeUnit.SECONDS)
                .build();
        return httpClient;
    }
}
