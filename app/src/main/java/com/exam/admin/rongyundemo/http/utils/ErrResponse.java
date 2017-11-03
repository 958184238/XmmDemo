package com.exam.admin.rongyundemo.http.utils;

/**
 * @author Administrator
 * @date 2017/4/14
 */

public class ErrResponse extends RuntimeException {
    private String msg;
    private int errorCode = 0;

    public ErrResponse(int errorCode, String msg) {
        super(msg);
        this.errorCode = errorCode;
        this.msg = msg;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public String getMsg() {
        return msg;
    }
}
