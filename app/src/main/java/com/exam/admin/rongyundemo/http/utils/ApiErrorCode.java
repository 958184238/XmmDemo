package com.exam.admin.rongyundemo.http.utils;

/**
 * @author Administrator
 * @date 2017/4/15
 */

public interface ApiErrorCode {
    /**
     * Token过期
     */
    int ERROR_TOKEN_TIMEOUT = 2;
//    /** 用户授权失败*/
//    int ERROR_USER_AUTHORIZED = 2;
//    /** 请求参数错误*/
//    int ERROR_REQUEST_PARAM = 3;
//    /** 参数检验不通过 */
//    int ERROR_PARAM_CHECK = 4;
//    /** 自定义错误*/
//    int ERROR_OTHER = 10;
    /**
     * 无网络连接
     */
    int ERROR_NO_INTERNET = 11;
}
