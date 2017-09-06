package com.exam.admin.rongyundemo.service.frame;

import android.app.Dialog;
import android.content.Context;

import com.exam.admin.rongyundemo.utils.NetUtils;

import rx.Subscriber;

/**
 * Created by Administrator on 2017/4/15.
 */

public class BaseSubscriber<T> extends Subscriber<T> {
    private Dialog mDialog;
    private Context mContext;

    public BaseSubscriber() {
    }

    public BaseSubscriber(Context context) {
        this.mContext = context;
    }


    public BaseSubscriber(Context context, Dialog dialog) {
        this.mContext = context;
        this.mDialog = dialog;
    }

    @Override
    public void onStart() {
        //请求开始之前，检查是否有网络。无网络直接抛出异常
        //另外，在你无法确定当前代码运行在什么线程的时候，不要将UI的相关操作放在这里。
        if (!NetUtils.isConnected(mContext)) {
//            if (mListView != null) {
//                mListView.onRefreshComplete();
//            }
            if (mDialog != null) {
                mDialog.dismiss();
            }
            return;
        }

    }


    @Override
    public void onCompleted() {

    }

    @Override
    public void onError(Throwable e) {
        ApiErrorHelper.handleCommonError(mContext, e);
//        if (mListView != null) {
//            mListView.onRefreshComplete();
//        }
//        if (mDialog != null) {
//            mDialog.dismiss();
//        }
    }

    @Override
    public void onNext(T t) {
//        if (mListView != null) {
//            mListView.onRefreshComplete();
//        }
//        if (mDialog != null) {
//            mDialog.dismiss();
//        }
    }


}
