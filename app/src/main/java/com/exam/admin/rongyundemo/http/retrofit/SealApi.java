package com.exam.admin.rongyundemo.http.retrofit;


import com.exam.admin.rongyundemo.http.request.LoginRequest;
import com.exam.admin.rongyundemo.http.response.LoginResponse;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * 2017/8/4 16:06
 *
 * @author wufei
 */

public interface SealApi {

    @POST("login")
    Observable<LoginResponse> login(@Body LoginRequest request);


}

