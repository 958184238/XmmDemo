package com.exam.admin.rongyundemo.fragment.main;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.exam.admin.rongyundemo.R;
import com.exam.admin.rongyundemo.adapter.AllAdapter;
import com.exam.admin.rongyundemo.animation.CustomAnimation;
import com.exam.admin.rongyundemo.fragment.BaseFragment;
import com.exam.admin.rongyundemo.http.response.AllResult;
import com.exam.admin.rongyundemo.http.retrofit.BaseObserver;
import com.exam.admin.rongyundemo.http.retrofit.GankApi;
import com.exam.admin.rongyundemo.http.retrofit.RetrofitHelper;
import com.exam.admin.rongyundemo.http.rxjava.RxHelper;
import com.exam.admin.rongyundemo.http.utils.GankBaseUrl;
import com.exam.admin.rongyundemo.utils.ToastUtils;

import java.util.ArrayList;

import butterknife.BindView;

/**
 * Name: AllFragment
 * Des:
 * User: 吴飞
 * Date: 2017/8/15 10:25
 *
 * @author Administrator
 */

public class AllFragment extends BaseFragment implements SwipeRefreshLayout.OnRefreshListener, BaseQuickAdapter.RequestLoadMoreListener {

    @BindView(R.id.recyclerview)
    RecyclerView recyclerview;
    @BindView(R.id.swiperefreshlayout)
    SwipeRefreshLayout swiperefreshlayout;
    private AllAdapter adapter;
    private int pageNum = 1;

    @Override
    protected void loadData() {
        super.loadData();
    }

    private void getData(final boolean isRefesh) {
        RetrofitHelper
                .createRetrofit(GankBaseUrl.DATA)
                .create(GankApi.class)
                .getAll(pageNum)
                .compose(RxHelper.<AllResult>normalSchedulers(this))
                .subscribe(new BaseObserver<AllResult>() {
                    @Override
                    protected void onSuccess(AllResult result) {
                        new ArrayList<>();
                    }

                    @Override
                    protected void doOnError(Throwable e) {

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
        getData(true);
    }

    private void initAdapter() {
        adapter = new AllAdapter(R.layout.all_item, null);
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                ToastUtils.showShort(mContext, "ssss" + position);
            }
        });
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
        getData(true);
    }

    @Override
    public void onLoadMoreRequested() {
        //加载更多
        pageNum += 1;
        getData(false);
    }
}
