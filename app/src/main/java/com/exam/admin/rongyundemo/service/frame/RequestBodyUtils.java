package com.exam.admin.rongyundemo.service.frame;


import org.json.JSONObject;

import java.util.Map;

import okhttp3.MediaType;
import okhttp3.RequestBody;

/**
 * Created by Administrator on 2017/4/19.
 */

public class RequestBodyUtils {

    public static RequestBody getRequestBody(Map<String, Object> map) {
        final JSONObject jsonObject = new JSONObject(map);
        MediaType JSON = MediaType.parse("application/json; charset=utf-8");
        RequestBody body = RequestBody.create(JSON, jsonObject.toString());
        return body;
    }

    public static RequestBody getRequestBody(String json) {
        MediaType JSON = MediaType.parse("application/json; charset=utf-8");
        RequestBody body = RequestBody.create(JSON, json);
        return body;
    }



}
