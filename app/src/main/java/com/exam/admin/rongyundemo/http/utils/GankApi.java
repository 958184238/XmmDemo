package com.exam.admin.rongyundemo.http.utils;


import com.exam.admin.rongyundemo.http.response.AllResponse;
import com.exam.admin.rongyundemo.http.response.WelfareResponse;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * ========================
 * Name: GankApi
 * Des:  干货API
 * User: 吴飞
 * Date: 2017/8/4 16:06
 * =========================
 */

public interface GankApi {

    //All
    @GET("all/10/{pageNum}")
    Observable<AllResponse> getAll(@Path("pageNum") int pageNum);

    @GET("福利/10/{pageNum}")
    Observable<WelfareResponse> getWelfare(@Path("pageNum") int pageNum);


}

