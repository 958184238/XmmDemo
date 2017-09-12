package com.exam.admin.rongyundemo.fragment.drysaltery;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.exam.admin.rongyundemo.R;
import com.exam.admin.rongyundemo.adapter.AllAdapter;
import com.exam.admin.rongyundemo.animation.CustomAnimation;
import com.exam.admin.rongyundemo.fragment.BaseFragment;
import com.exam.admin.rongyundemo.service.frame.HttpSubscriber;
import com.exam.admin.rongyundemo.service.frame.GankApi;
import com.exam.admin.rongyundemo.service.frame.GankBaseUrl;
import com.exam.admin.rongyundemo.service.frame.RetrofitAPIManager;
import com.exam.admin.rongyundemo.service.response.AllResponse;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * ========================
 * Name: AllFragment
 * Des:
 * User: 吴飞
 * Date: 2017/8/15 10:25
 * =========================
 */

public class AllFragment extends BaseFragment implements SwipeRefreshLayout.OnRefreshListener, BaseQuickAdapter.RequestLoadMoreListener {

    @BindView(R.id.recyclerview)
    RecyclerView recyclerview;
    @BindView(R.id.swiperefreshlayout)
    SwipeRefreshLayout swiperefreshlayout;
    private boolean isPrepared = false;
    private boolean isFirst = true;
    private List<AllResponse.ResultsBean> allList = new ArrayList<>();
    private AllAdapter adapter;
    private int pageNum = 1;

    @Override
    protected void loadData() {
        super.loadData();
//        if (!mIsVisible || !isPrepared || !isFirst) {
//            return;
//        }
//        getData();
    }

    private void getData(final boolean isRefesh, final boolean isLoadMore) {
        RetrofitAPIManager
                .creatRetrofit(GankBaseUrl.DATA)
                .create(GankApi.class)
                .getAll(pageNum)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new HttpSubscriber<AllResponse>(mContext) {

                    @Override
                    protected void doOnNext(AllResponse response) {
                        if (isRefesh) {
                            cancelLoading();
                            adapter.setNewData(response.getResults());
                            swiperefreshlayout.setRefreshing(false);
                            adapter.setEnableLoadMore(true);
                        } else if (isLoadMore) {
                            adapter.addData(response.getResults());
                            adapter.loadMoreComplete();
                        }
                    }
                });
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        recyclerview.setLayoutManager(new LinearLayoutManager(mContext));
        swiperefreshlayout.setColorSchemeResources(R.color.blue);
        swiperefreshlayout.setOnRefreshListener(this);
        initAdapter();
        showLoading();
        getData(true, false);
    }

    private void initAdapter() {
        adapter = new AllAdapter(R.layout.all_item, null);
        recyclerview.setAdapter(adapter);
        adapter.openLoadAnimation(new CustomAnimation());
        adapter.isFirstOnly(false);
        adapter.setPreLoadNumber(2);
        adapter.setOnLoadMoreListener(this, recyclerview);
    }

    @Override
    public void onErrorRefresh() {

    }


    @Override
    public int setContent() {
        return R.layout.all_fragment;
    }

    @Override
    public void onRefresh() {
        //刷新
        pageNum = 1;
        adapter.setEnableLoadMore(false);
        getData(true, false);
    }

    @Override
    public void onLoadMoreRequested() {
        //加载更多
        pageNum += 1;
        getData(false, true);
    }
}
