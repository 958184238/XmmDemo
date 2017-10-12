package com.exam.admin.rongyundemo.http.utils;


import com.exam.admin.rongyundemo.http.request.LoginRequest;
import com.exam.admin.rongyundemo.http.response.LoginResponse;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * ========================
 * Name: SealApi
 * Des:
 * User: 吴飞
 * Date: 2017/8/4 16:06
 * =========================
 */

public interface SealApi {

    @POST("login")
    Observable<LoginResponse> login(@Body LoginRequest request);


}

