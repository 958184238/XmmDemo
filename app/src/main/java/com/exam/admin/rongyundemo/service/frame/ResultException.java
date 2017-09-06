package com.exam.admin.rongyundemo.service.frame;

/**
 * Created by Administrator on 2017/4/14.
 */

class ResultException extends RuntimeException {
    private int errCode = 0;

    public ResultException(int errCode, String msg) {
        super(msg);
        this.errCode = errCode;
    }

    public int getErrCode() {
        return errCode;
    }
}
