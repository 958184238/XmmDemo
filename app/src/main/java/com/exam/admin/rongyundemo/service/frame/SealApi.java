package com.exam.admin.rongyundemo.service.frame;


import com.exam.admin.rongyundemo.service.request.LoginRequest;
import com.exam.admin.rongyundemo.service.response.LoginResponse;

import retrofit2.http.Body;
import retrofit2.http.POST;
import rx.Observable;

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

