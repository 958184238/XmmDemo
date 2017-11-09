package com.exam.admin.rongyundemo.http.retrofit;


import android.os.NetworkOnMainThreadException;

import java.io.IOException;
import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.net.UnknownServiceException;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import retrofit2.HttpException;

/**
 * 观察者
 *
 * @author Administrator
 * @date 2017/4/15
 */

public abstract class BaseObserver<T> implements Observer<T> {
    /**
     * 自定义的业务逻辑，成功返回积极数据
     */
    private final int RESPONSE_CODE_OK = 0;
    /**
     * 返回数据失败,严重的错误
     */
    private final int RESPONSE_FATAL_EOR = -1;

    private int errorCode;
    private String errorMsg;

    @Override
    public void onSubscribe(Disposable d) {
        //onSubscribe 是2.x新添加的方法，在发射数据前被调用，相当于1.x的onStart方法
    }


    @Override
    public void onNext(T t) {
        onSuccess(t);
    }

//    protected abstract void onFailure(int code, String error);

    @Override
    public void onError(Throwable t) {
        if (t instanceof HttpException) {
            HttpException httpException = (HttpException) t;
            errorCode = httpException.code();
            errorMsg = httpException.getMessage();
            getErrorMsg(httpException);
        } else if (t instanceof SocketTimeoutException) {
            //VPN open
            errorCode = RESPONSE_FATAL_EOR;
            errorMsg = "服务器响应超时";
        } else if (t instanceof ConnectException) {
            errorCode = RESPONSE_FATAL_EOR;
            errorMsg = "网络连接异常，请检查网络";
        } else if (t instanceof UnknownHostException) {
            errorCode = RESPONSE_FATAL_EOR;
            errorMsg = "无法解析主机，请检查网络连接";
        } else if (t instanceof UnknownServiceException) {
            errorCode = RESPONSE_FATAL_EOR;
            errorMsg = "未知的服务器错误";
        } else if (t instanceof IOException) {
            //飞行模式等
            errorCode = RESPONSE_FATAL_EOR;
            errorMsg = "没有网络，请检查网络连接";
        } else if (t instanceof NetworkOnMainThreadException) {
            //主线程不能网络请求，这个很容易发现
            errorCode = RESPONSE_FATAL_EOR;
            errorMsg = "主线程不能网络请求";
        } else if (t instanceof RuntimeException) {
            //很多的错误都是extends RuntimeException
            errorCode = RESPONSE_FATAL_EOR;
            errorMsg = "运行时错误";
        }

//        onFailure(errorCode, errorMsg);
    }

    @Override
    public void onComplete() {
    }

    /**
     * 根据具体的Api 业务逻辑去重写 onSuccess 方法！Error 是选择重写，but 必须Super ！
     *
     * @param
     * @param result
     */
    protected abstract void onSuccess(T result);

    protected abstract void doOnError(Throwable e);

    /**
     * 获取详细的错误的信息 errorCode,errorMsg
     * 以登录的时候的Grant_type 故意写错为例子,这个时候的http 应该是直接的返回401=httpException.code()
     * 但是是怎么导致401的？我们的服务器会在respose.errorBody 中的content 中说明
     */
    private final void getErrorMsg(HttpException httpException) {
//        String errorBodyStr = "";
//        try {      //我们的项目需要的UniCode转码 ,!!!!!!!!!!!!!!
//            errorBodyStr = TextUtils.convertUnicode(httpException.response().errorBody().string());
//        } catch (IOException ioe) {
//            Log.e("errorBodyStr ioe:", ioe.toString());
//        }
//        try {
//            HttpResponse errorResponse = gson.fromJson(errorBodyStr, HttpResponse.class);
//            if (null != errorResponse) {
//                errorCode = errorResponse.getCode();
//                errorMsg = errorResponse.getError();
//            }
//        } catch (Exception jsonException) {
//            jsonException.printStackTrace();
//        }
    }
}
