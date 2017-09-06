package com.exam.admin.rongyundemo.service.frame;

import java.io.IOException;
import java.nio.charset.Charset;

import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okio.Buffer;
import okio.BufferedSource;

/**
 * TokenInterceptor
 * Token拦截器并自动刷新
 */

public class TokenInterceptor implements Interceptor {

    private static final Charset UTF8 = Charset.forName("UTF-8");

    @Override
    public Response intercept(final Chain chain) throws IOException {
//        MyApp instance = MyApp.getInstance();
//        String uid = instance.getUid();
//        String token = instance.getToken();
        Request request = chain.request().newBuilder()
                .addHeader("appType", "1")
//                .addHeader("uid", uid)
//                .addHeader("token", token)
                .build();
        Response response = chain.proceed(request);
        /**通过如下的办法曲线取到请求完成的数据
         * 原本想通过  originalResponse.body().string()
         * 去取到请求完成的数据,但是一直报错,不知道是okhttp的bug还是操作不当
         * 然后去看了okhttp的源码,找到了这个曲线方法,取到请求完成的数据后,根据特定的判断条件去判断token过期
         */
        ResponseBody responseBody = response.body();
        BufferedSource source = responseBody.source();
        source.request(Long.MAX_VALUE); // Buffer the entire body.
        Buffer buffer = source.buffer();
        Charset charset = UTF8;
        MediaType contentType = responseBody.contentType();
        if (contentType != null) {
            charset = contentType.charset(UTF8);
        }
        String bodyString = buffer.clone().readString(charset);
        String json = bodyString;
        Result result = GsonUtil.parseJsonWithGson(json, Result.class);
        int statusCode = result.getCode();
        if (isTokenExpired(statusCode)) {//根据和服务端的约定判断token过期
            //同步请求方式，获取最新的Token
            String newToken = getNewToken();
            //重新请求
            Request newRequest = chain.request()
                    .newBuilder()
                    .addHeader("appType", "1")
//                    .addHeader("uid", uid)
                    .addHeader("token", newToken)
                    .build();
            return chain.proceed(newRequest);
        } else {
            return response;
        }
    }

    /**
     * 根据Response，判断Token是否失效
     *
     * @param statusCode
     * @return
     */
    private boolean isTokenExpired(int statusCode) {
        if (statusCode == 2) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 同步请求方式，获取最新的Token
     *
     * @return
     */
    private String getNewToken() throws IOException {
        // 通过一个特定的接口获取新的token，此处要用到同步的retrofit请求
//        HashMap<String, Object> map = new HashMap<>();
//        MyApp instance = MyApp.getInstance();
//        String phone = instance.getLogin_username();
//        String passwd = instance.getLogin_userpawwrod();
//        String version = MyOrderUtils.getVersionName(instance);
//        map.put("phone", phone);
//        map.put("passwd", passwd);
////        map.put("deviceType", "2");
////        map.put("version", version);
//        RequestBody requestBody = RequestBodyUtils.getRequestBody(map);
//        String token = RetrofitAPIManager
//                .creatRetrofit()
//                .create(OrderApi.class)
//                .getNewToken(requestBody)
//                .execute()
//                .body()
//                .getData().getToken();
//        //将最新token存在客户端并返回.
//        instance.setToken(token);
        return null;

    }

}
