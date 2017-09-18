//package com.exam.admin.rongyundemo.activity;
//
//import android.content.Intent;
//import android.os.Bundle;
//import android.support.v4.app.ActivityCompat;
//import android.support.v4.app.ActivityOptionsCompat;
//import android.support.v4.widget.SwipeRefreshLayout;
//import android.support.v7.app.AppCompatActivity;
//import android.support.v7.widget.RecyclerView;
//import android.support.v7.widget.StaggeredGridLayoutManager;
//import android.view.View;
//
//import com.chad.library.adapter.base.BaseQuickAdapter;
//import com.exam.admin.rongyundemo.R;
//import com.exam.admin.rongyundemo.adapter.GridSpacingItemDecoration;
//import com.exam.admin.rongyundemo.adapter.WelfareAdapter;
//import com.exam.admin.rongyundemo.service.frame.HttpSubscriber;
//import com.exam.admin.rongyundemo.service.frame.GankApi;
//import com.exam.admin.rongyundemo.service.frame.GankBaseUrl;
//import com.exam.admin.rongyundemo.service.frame.RetrofitAPIManager;
//import com.exam.admin.rongyundemo.service.response.WelfareResponse;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import butterknife.BindView;
//import butterknife.ButterKnife;
//import rx.android.schedulers.AndroidSchedulers;
//import rx.schedulers.Schedulers;
//
//public class CircleFriendsActivity extends AppCompatActivity {
//
//    @BindView(R.id.recyclerview)
//    RecyclerView recyclerview;
//    @BindView(R.id.swiperefreshlayout)
//    SwipeRefreshLayout swiperefreshlayout;
//    private CircleFriendsActivity mContext;
//    private int pageNum = 1;
//    ArrayList<WelfareResponse.ResultsBean> allList = new ArrayList<>();
//    private WelfareAdapter adapter;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
////        getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);
//        setContentView(R.layout.activity_circle_friends);
//        mContext = this;
//        ButterKnife.bind(this);
//        initRefresh();
//        initRecycle();
//        getNewData();
//    }
//
//    private void initRecycle() {
//        recyclerview.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
//        recyclerview.addItemDecoration(new GridSpacingItemDecoration(2, 10, true));
//    }
//
//    private void initRefresh() {
//        swiperefreshlayout.setColorSchemeResources(R.color.blue);
//        swiperefreshlayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
//            @Override
//            public void onRefresh() {
//                //关闭自动加载
//                adapter.setEnableLoadMore(false);
//                allList.clear();
//                adapter.notifyDataSetChanged();
//                pageNum = 1;
//                getNewData();
//            }
//        });
//    }
//
//    private void getNewData() {
//        RetrofitAPIManager
//                .creatRetrofit(GankBaseUrl.DATA)
//                .create(GankApi.class)
//                .getWelfare(pageNum)
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new HttpSubscriber<WelfareResponse>(mContext) {
//                    @Override
//                    public void onNext(WelfareResponse response) {
//                        super.onNext(response);
////                        cancelLoading();
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
//                                    String url = resultsBean.getUrl();
//                                    String desc = resultsBean.getDesc();
////                                    Intent intent = new Intent(mContext, CardActivity.class);
////                                    intent.putExtra("url", url);
////                                    intent.putExtra("desc", desc);
//
//                                    Intent intent = new Intent(mContext, DetailsActivity.class);
//                                    intent.putExtra("index", position);
//                                    intent.putParcelableArrayListExtra("allList", allList);
//                                    Bundle options = ActivityOptionsCompat.makeSceneTransitionAnimation(
//                                            mContext,
//                                            view.findViewById(R.id.iv_welfare),
//                                            url
//                                    ).toBundle();
//                                    ActivityCompat.startActivity(mContext, intent, options);
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
//                    protected void doOnNext(WelfareResponse welfareResponse) {
//
//                    }
//                });
//    }
//
//
//}
