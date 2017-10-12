//package com.exam.admin.rongyundemo.utils.rxbus;
//
//import io.reactivex.android.schedulers.AndroidSchedulers;
//import io.reactivex.disposables.CompositeDisposable;
//import io.reactivex.schedulers.Schedulers;
//
///**
// * 类名: RxBusHelper
// * 描述: RxBus工具类
// * 作者: 吴飞
// * 日期: 2017/9/28 16:52
// */
//
//public class RxBusHelper {
//
//    /**
//     * 发布消息
//     *
//     * @param o
//     */
//    public static void post(Object o) {
//        RxBus.getInstance().post(o);
//    }
//
//
//    /**
//     * 接收消息,并在主线程处理
//     *
//     * @param aClass
//     * @param disposables 用于存放消息
//     * @param listener
//     * @param <T>
//     */
//    public static <T> void doOnMainThread(Class<T> aClass, CompositeDisposable disposables, OnEventListener<T> listener) {
//        disposables.add(RxBus.getInstance()
//                .toFlowable(aClass)
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(listener::onEvent, throwable ->
//                        listener.onError(new ErrorBean(ErrorCode.ERROR_CODE_RXBUS, ErrorCode.ERROR_DESC_RXBUS))));
//    }
//
//    public static <T> void doOnMainThread(Class<T> aClass, OnEventListener<T> listener) {
//        RxBus.getInstance()
//                .toFlowable(aClass)
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(listener::onEvent, throwable ->
//                        listener.onError(new ErrorBean(ErrorCode.ERROR_CODE_RXBUS, ErrorCode.ERROR_DESC_RXBUS)));
//    }
//
//    /**
//     * 接收消息,并在子线程处理
//     *
//     * @param aClass
//     * @param disposables
//     * @param listener
//     * @param <T>
//     */
//    public static <T> void doOnChildThread(Class<T> aClass, CompositeDisposable disposables, OnEventListener<T> listener) {
//        disposables.add(RxBus.getInstance()
//                .toFlowable(aClass)
//                .subscribeOn(Schedulers.newThread())
//                .subscribe(listener::onEvent, throwable ->
//                        listener.onError(new ErrorBean(ErrorCode.ERROR_CODE_RXBUS, ErrorCode.ERROR_DESC_RXBUS))));
//    }
//
//    public static <T> void doOnChildThread(Class<T> aClass, OnEventListener<T> listener) {
//        RxBus.getInstance()
//                .toFlowable(aClass)
//                .subscribeOn(Schedulers.newThread())
//                .subscribe(listener::onEvent, throwable ->
//                        listener.onError(new ErrorBean(ErrorCode.ERROR_CODE_RXBUS, ErrorCode.ERROR_DESC_RXBUS)));
//    }
//
//    public interface OnEventListener<T> {
//        void onEvent(T t);
//
//        void onError(ErrorBean errorBean);
//    }
//
////    作者：Lightofrain
////    链接：http://www.jianshu.com/p/61b67567bb8a
////    來源：简书
////    著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
//}
