package com.exam.admin.rongyundemo.http.rxjava;


import com.trello.rxlifecycle2.RxLifecycle;
import com.trello.rxlifecycle2.android.ActivityEvent;
import com.trello.rxlifecycle2.android.FragmentEvent;
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;
import com.trello.rxlifecycle2.components.support.RxFragment;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * @author wufei
 * @date 2017/11/6
 */

public class RxHelper {

    /**
     * 普通线程切换: IO -> Main
     * RxLifecycle防止内存泄漏
     *
     * @param <T>
     * @return
     */
    public static <T> ObservableTransformer<T, T> normalSchedulers(final RxAppCompatActivity context) {

        return new ObservableTransformer<T, T>() {
            @Override
            public ObservableSource<T> apply(Observable<T> upstream) {
                return upstream
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .compose(RxLifecycle.<T, ActivityEvent>bindUntilEvent(context.lifecycle(), ActivityEvent.DESTROY));
            }
        };
    }

    public static <T> ObservableTransformer<T, T> normalSchedulers(final RxFragment context) {

        return new ObservableTransformer<T, T>() {
            @Override
            public ObservableSource<T> apply(Observable<T> upstream) {
                return upstream
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .compose(RxLifecycle.<T, FragmentEvent>bindUntilEvent(context.lifecycle(), FragmentEvent.DESTROY));
            }
        };
    }

//    /**
//     * 对RESTful返回结果做预处理，对逻辑错误抛出异常
//     *
//     * @param <T>
//     * @return
//     */
//    public static <T> Func1<RESTResult<T>, T> handleRESTFulResult() {
//        return new Func1<RESTResult<T>, T>() {
//            @Override
//            public T call(RESTResult<T> restResult) {
//                if (restResult.getRes() != RESTResult.SUCCESS) {
//                    throw new ApiException(restResult.getRes(), restResult.getMsg());
//                }
//                return restResult.getData();
//            }
//        };
//    }

}
