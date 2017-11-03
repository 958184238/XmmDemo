package com.exam.admin.rongyundemo.activity.demo;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.exam.admin.rongyundemo.R;
import com.exam.admin.rongyundemo.activity.BaseActivity;
import com.exam.admin.rongyundemo.adapter.GridSpacingItemDecoration;
import com.exam.admin.rongyundemo.adapter.WelfareAdapter;
import com.exam.admin.rongyundemo.http.response.WelfareResponse;
import com.exam.admin.rongyundemo.http.utils.BaseSubscriber;
import com.exam.admin.rongyundemo.http.utils.GankApi;
import com.exam.admin.rongyundemo.http.utils.GankBaseUrl;
import com.exam.admin.rongyundemo.http.utils.RetrofitFactory;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class StaggeredActivity extends BaseActivity implements SwipeRefreshLayout.OnRefreshListener, BaseQuickAdapter.RequestLoadMoreListener {

    @BindView(R.id.recyclerview)
    RecyclerView recyclerview;
    @BindView(R.id.swiperefreshlayout)
    SwipeRefreshLayout swiperefreshlayout;
    private StaggeredActivity mContext;
    private int pageNum = 1;
    private WelfareAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_staggered);
        mContext = this;
        ButterKnife.bind(this);
        initRefreshAndAdapter();
        getNewData(true);
        setBack();
        setTvTitle("瀑布照片墙");
    }

    @Override
    protected void onErrorRefresh() {
        getNewData(true);
    }

    private void initRefreshAndAdapter() {
        recyclerview.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
        recyclerview.addItemDecoration(new GridSpacingItemDecoration(2, 10, true));
        swiperefreshlayout.setColorSchemeResources(R.color.blue);
        swiperefreshlayout.setOnRefreshListener(this);
        adapter = new WelfareAdapter(R.layout.welfare_item, null);
        recyclerview.setAdapter(adapter);
        adapter.openLoadAnimation(BaseQuickAdapter.SCALEIN);
        adapter.setPreLoadNumber(2);
        adapter.setOnLoadMoreListener(this, recyclerview);
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                //过渡动画
//                WelfareResponse.ResultsBean resultsBean = allList.get(position);
//                String url = resultsBean.getUrl();
//                String desc = resultsBean.getDesc();
////                                    Intent intent = new Intent(mContext, CardActivity.class);
////                                    intent.putExtra("url", url);
////                                    intent.putExtra("desc", desc);
//
//                Intent intent = new Intent(mContext, DetailsActivity.class);
//                intent.putExtra("index", position);
//                intent.putParcelableArrayListExtra("allList", allList);
//                Bundle options = ActivityOptionsCompat.makeSceneTransitionAnimation(
//                        mContext,
//                        view.findViewById(R.id.iv_welfare),
//                        url
//                ).toBundle();
//                ActivityCompat.startActivity(mContext, intent, options);
            }
        });
    }


    private void getNewData(final boolean isRefesh) {
        RetrofitFactory
                .creatRetrofit(GankBaseUrl.DATA)
                .create(GankApi.class)
                .getWelfare(pageNum)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseSubscriber<WelfareResponse>() {
                    @Override
                    protected void doOnNext(WelfareResponse response) {
                        if (isRefesh) {
                            cancelLoading();
                            adapter.setNewData(response.getResults());
                            swiperefreshlayout.setRefreshing(false);//停止刷新
                            adapter.setEnableLoadMore(true);
                        } else {
                            if (response.getResults() == null || response.getResults().size() == 0) {
                                adapter.loadMoreEnd();//没有更多的数据了
                            } else {
                                adapter.addData(response.getResults());
                                adapter.loadMoreComplete();//加载完成
                            }
                        }
                    }

                    @Override
                    protected void doOnError(Throwable e) {

                    }
                });
    }


    @Override
    public void onRefresh() {
        //刷新
        pageNum = 1;
        adapter.setEnableLoadMore(false);
        getNewData(true);
    }

    @Override
    public void onLoadMoreRequested() {
        //加载更多
        pageNum += 1;
        getNewData(false);

    }
}
