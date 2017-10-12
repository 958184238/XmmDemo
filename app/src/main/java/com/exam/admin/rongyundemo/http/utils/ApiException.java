package com.exam.admin.rongyundemo.http.utils;

/**
 * Created by Administrator on 2017/4/15.
 */

class ApiException extends RuntimeException {
    private String msg;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    private int errorCode;

    public ApiException(int code, String msg) {
        super(msg);
        this.errorCode = code;
        this.msg = msg;
    }

    public int getErrorCode() {
        return errorCode;
    }
}