package com.exam.admin.rongyundemo.fragment.drysaltery;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.exam.admin.rongyundemo.R;
import com.exam.admin.rongyundemo.adapter.AllAdapter;
import com.exam.admin.rongyundemo.fragment.BaseFragment;
import com.exam.admin.rongyundemo.service.frame.BaseSubscriber;
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

public class AllFragment extends BaseFragment {

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
//        getNewData();
    }

    private void getNewData() {
        RetrofitAPIManager
                .creatRetrofit(GankBaseUrl.DATA)
                .create(GankApi.class)
                .getAll(pageNum)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseSubscriber<AllResponse>(mContext) {
                    @Override
                    public void onNext(AllResponse response) {
                        super.onNext(response);
                        showContentView();
                        List<AllResponse.ResultsBean> list = response.getResults();
                        allList.addAll(list);
                        if (adapter == null) {
                            adapter = new AllAdapter(R.layout.all_item, allList);
                            recyclerview.setAdapter(adapter);
                            //  加载动画
                            adapter.openLoadAnimation(BaseQuickAdapter.SCALEIN);
                            //  预加载
                            //  当列表滑动到倒数第N个Item的时候(默认是1)回调onLoadMoreRequested方法
                            adapter.setPreLoadNumber(2);
                            //  自动加载
                            //  滑动最后一个Item的时候回调onLoadMoreRequested方法
                            adapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
                                @Override
                                public void onLoadMoreRequested() {
                                    pageNum += 1;
                                    getNewData();
                                }
                            }, recyclerview);
                        } else {
                            adapter.notifyDataSetChanged();
                        }

                        //刷新完成
                        swiperefreshlayout.setRefreshing(false);
                        adapter.setEnableLoadMore(true);
                        //加载完成
                        adapter.loadMoreComplete();
                        //没有数据了
                        if (list.size() == 0) {
                            adapter.loadMoreEnd();
                        }
                    }
                });
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        refresh();
        showLoading();
        recyclerview.setLayoutManager(new LinearLayoutManager(mContext));
//        recyclerview.addItemDecoration(new GridSpacingItemDecoration(2, 10, true));
        isPrepared = true;

        getNewData();
    }

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
        return R.layout.all_fragment;
    }
}
