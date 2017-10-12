package com.exam.admin.rongyundemo.utils.rxbus;

import io.reactivex.Flowable;
import io.reactivex.processors.FlowableProcessor;
import io.reactivex.processors.PublishProcessor;
import io.reactivex.subscribers.SerializedSubscriber;

/**
 * 类名: RxBus
 * 描述:
 * 作者: 吴飞
 * 日期: 2017/9/28 16:42
 */

public class RxBus {

    private static volatile RxBus mInstance;
    private FlowableProcessor<Object> mBus;

    private RxBus() {
        mBus = PublishProcessor.create().toSerialized();
    }

    /**
     * 单例模式RxBus(双重查锁模式)
     *
     * @return
     */
    public static RxBus getInstance() {
        if (mInstance == null) {
            synchronized (RxBus.class) {
                if (mInstance == null) {
                    mInstance = new RxBus();
                }
            }
        }
        return mInstance;
    }

    /**
     * 发送消息
     *
     * @param object
     */
    public void post(Object object) {
        new SerializedSubscriber<>(mBus).onNext(object);
    }

    /**
     * 接收消息(确定接收消息的类型)
     *
     * @param aClass
     * @param <T>
     * @return
     */
    public <T> Flowable<T> toFlowable(Class<T> aClass) {
        return mBus.ofType(aClass);
    }

    /**
     * 判断是否有订阅者
     *
     * @return
     */
    public boolean hasSubscribers() {
        return mBus.hasSubscribers();
    }
}
