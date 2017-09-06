package com.exam.admin.rongyundemo.service.frame;

/**
 * Created by Administrator on 2017/4/14.
 */

class ErrResponse extends RuntimeException {
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
