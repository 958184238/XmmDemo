package com.exam.admin.rongyundemo.fragment;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.SharedElementCallback;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.view.ViewTreeObserver;

import com.exam.admin.rongyundemo.R;
import com.exam.admin.rongyundemo.adapter.GridSpacingItemDecoration;
import com.exam.admin.rongyundemo.adapter.WelfareAdapter;
import com.exam.admin.rongyundemo.contanst.SealConst;
import com.exam.admin.rongyundemo.http.response.WelfareResponse;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import butterknife.BindView;

/**
 * ========================
 * Name: WelfareFragment
 * Des:  福利
 * User: 吴飞
 * Date: 2017/8/15 10:21
 * =========================
 */

public class WelfareFragment extends BaseFragment {

    @BindView(R.id.recyclerview)
    RecyclerView recyclerview;
    @BindView(R.id.swiperefreshlayout)
    SwipeRefreshLayout swiperefreshlayout;

    public static final String EXTRA_STARTING_ALBUM_POSITION = "extra_starting_item_position";
    public static final String EXTRA_CURRENT_ALBUM_POSITION = "extra_current_item_position";

    ArrayList<WelfareResponse.ResultsBean> allList = new ArrayList<>();
    private boolean isPrepared = false;
    private boolean isFirst = true;
    private WelfareAdapter adapter;
    private Bundle mTmpReenterState;


    private final SharedElementCallback mCallback = new SharedElementCallback() {
        @Override
        public void onMapSharedElements(List<String> names, Map<String, View> sharedElements) {
            if (mTmpReenterState != null) {
                int startingPosition = mTmpReenterState.getInt(EXTRA_STARTING_ALBUM_POSITION);
                int currentPosition = mTmpReenterState.getInt(EXTRA_CURRENT_ALBUM_POSITION);
                if (startingPosition != currentPosition) {
                    // If startingPosition != currentPosition the user must have swiped to a
                    // different page in the DetailsActivity. We must update the shared element
                    // so that the correct one falls into place.
                    String newTransitionName = allList.get(currentPosition).getUrl();
                    View newSharedElement = recyclerview.findViewWithTag(newTransitionName);
                    if (newSharedElement != null) {
                        names.clear();
                        names.add(newTransitionName);
                        sharedElements.clear();
                        sharedElements.put(newTransitionName, newSharedElement);
                    }
                }

                mTmpReenterState = null;
            } else {
//                onMapSharedElements(names, sharedElements);
//                // If mTmpReenterState is null, then the activity is exiting.
//                View navigationBar = findViewById(android.R.id.navigationBarBackground);
//                View statusBar = findViewById(android.R.id.statusBarBackground);
//                if (navigationBar != null) {
//                    names.add(navigationBar.getTransitionName());
//                    sharedElements.put(navigationBar.getTransitionName(), navigationBar);
//                }
//                if (statusBar != null) {
//                    names.add(statusBar.getTransitionName());
//                    sharedElements.put(statusBar.getTransitionName(), statusBar);
//                }
            }
        }
    };


    @Override
    protected void loadData() {
        super.loadData();
        if (!mIsVisible || !isPrepared || !isFirst) {
            return;
        }
        getNewData();
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        refresh();
        showLoading();
        initRecycle();
        isPrepared = true;
        ActivityCompat.setExitSharedElementCallback(mContext, mCallback);
    }

    @Override
    protected void onErrorRefresh() {

    }

    private void initRecycle() {
        recyclerview.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
        recyclerview.addItemDecoration(new GridSpacingItemDecoration(2, 10, true));
    }

    private BroadcastReceiver receiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (action.equals(SealConst.ACTION_NAME)) {
                mTmpReenterState = new Bundle(intent.getExtras());
//                mTmpReenterState = new Bundle(data.getExtras());
                int startingPosition = mTmpReenterState.getInt(EXTRA_STARTING_ALBUM_POSITION);
                int currentPosition = mTmpReenterState.getInt(EXTRA_CURRENT_ALBUM_POSITION);
                if (startingPosition != currentPosition) {
                    recyclerview.scrollToPosition(currentPosition);
                }
                postponeEnterTransition();
                recyclerview.getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
                    @Override
                    public boolean onPreDraw() {
                        recyclerview.getViewTreeObserver().removeOnPreDrawListener(this);
                        // TODO: figure out why it is necessary to request layout here in order to get a smooth transition.
                        recyclerview.requestLayout();
                        startPostponedEnterTransition();
                        return true;
                    }
                });
            }
        }
    };

    @Override
    public void onStart() {
        super.onStart();
        IntentFilter filter = new IntentFilter();
        filter.addAction(SealConst.ACTION_NAME);
        mContext.registerReceiver(receiver, filter);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mContext.unregisterReceiver(receiver);
    }

//    @Override
//    public void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        if (resultCode == RESULT_OK) {
//            if (requestCode == 1001) {
////                int index = data.getIntExtra("index", 0);
//
//                mTmpReenterState = new Bundle(data.getExtras());
//                int startingPosition = mTmpReenterState.getInt(EXTRA_STARTING_ALBUM_POSITION);
//                int currentPosition = mTmpReenterState.getInt(EXTRA_CURRENT_ALBUM_POSITION);
//                if (startingPosition != currentPosition) {
//                    recyclerview.scrollToPosition(currentPosition);
//                }
//                postponeEnterTransition();
//                recyclerview.getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
//                    @Override
//                    public boolean onPreDraw() {
//                        recyclerview.getViewTreeObserver().removeOnPreDrawListener(this);
//                        // TODO: figure out why it is necessary to request layout here in order to get a smooth transition.
//                        recyclerview.requestLayout();
//                        startPostponedEnterTransition();
//                        return true;
//                    }
//                });
//            }
//        }
//    }


    private void getNewData() {
//        RetrofitHelper
//                .createRetrofit(GankBaseUrl.DATA)
//                .create(GankApi.class)
//                .getWelfare(pageNum)
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new HttpSubscriber<WelfareResponse>(mContext) {
//                    @Override
//                    public void onNext(WelfareResponse response) {
//                        super.onNext(response);
//                        cancelLoading();
//                        List<WelfareResponse.ResultsBean> list = response.getResults();
//                        allList.addAll(list);
//                        if (adapter == null) {
//                            adapter = new WelfareAdapter(R.layout.welfare_item, allList);
//                            recyclerview.setAdapter(adapter);
//                            //  加载动画
//                            adapter.openLoadAnimation(BaseQuickAdapter.SCALEIN);
//                            //  预加载
//                            //  当列表滑动到倒数第N个Item的时候(默认是1)回调onLoadMoreRequested方法
//                            adapter.setPreLoadNumber(2);
//                            //  自动加载
//                            //  滑动最后一个Item的时候回调onLoadMoreRequested方法
//                            adapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
//                                @Override
//                                public void onLoadMoreRequested() {
//                                    pageNum += 1;
//                                    getNewData();
//                                }
//                            }, recyclerview);
//
//                            adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
//                                @Override
//                                public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
//                                    //过渡动画
//                                    WelfareResponse.ResultsBean resultsBean = allList.get(position);
////                                    Intent intent = new Intent(getContext(), DetailsActivity.class);
////                                    intent.putExtra("index", position);
////                                    intent.putExtra("allList", allList);
////                                    ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation(
////                                            getActivity(),
////                                            view.findViewById(R.id.iv_welfare),
////                                            resultsBean.getUrl()
////                                    );
////                                    ActivityCompat.startActivity((Activity) getContext(), intent, options.toBundle());
//                                }
//                            });
//                        } else {
//                            adapter.notifyDataSetChanged();
//                        }
//
//                        //刷新完成
//                        swiperefreshlayout.setRefreshing(false);
//                        adapter.setEnableLoadMore(true);
//                        //加载完成
//                        adapter.loadMoreComplete();
//                        //没有数据了
//                        if (list.size() == 0) {
//                            adapter.loadMoreEnd();
//                        }
//                    }
//
//                    @Override
//                    protected void onSuccess(WelfareResponse welfareResponse) {
//
//                    }
//                });
    }

    private int pageNum = 1;

    private void refresh() {
        swiperefreshlayout.setColorSchemeResources(R.color.blue);
        swiperefreshlayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                //关闭自动加载
                adapter.setEnableLoadMore(false);
                allList.clear();
                adapter.notifyDataSetChanged();
                pageNum = 1;
                getNewData();
            }
        });
    }

    @Override
    public int setContent() {
        return R.layout.welfare_fragment;
    }
}
