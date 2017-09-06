package com.exam.admin.rongyundemo.fragment;

import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.exam.admin.rongyundemo.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * ========================
 * Name: BaseFragment
 * Des:
 * User: 吴飞
 * Date: 2017/8/15 15:09
 * =========================
 */

public abstract class BaseFragment extends Fragment {

    // fragment是否显示了
    protected boolean mIsVisible = false;
    @BindView(R.id.rl_content_part)
    RelativeLayout rlContentPart;
    @BindView(R.id.ll_error_refresh)
    LinearLayout llErrorRefresh;
    @BindView(R.id.ll_progress_bar)
    LinearLayout llProgressBar;
    @BindView(R.id.ll_content)
    LinearLayout llContent;
    @BindView(R.id.container)
    RelativeLayout container;
    Unbinder unbinder;
    @BindView(R.id.iv_progress)
    ImageView ivProgress;
    private AnimationDrawable mAnimationDrawable;
    protected FragmentActivity mContext;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(setContent(), null);
        mContext = getActivity();
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    /**
     * 在这里实现Fragment数据的缓加载.
     */
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (getUserVisibleHint()) {
            mIsVisible = true;
            onVisible();
        } else {
            mIsVisible = false;
            onInvisible();
        }
    }

    protected void onVisible() {
        loadData();
    }

    /**
     * 显示时加载数据,需要这样的使用
     * 注意声明 isPrepared，先初始化
     * 生命周期会先执行 setUserVisibleHint 再执行onActivityCreated
     * 在 onActivityCreated 之后第一次显示加载数据，只加载一次
     */
    protected void loadData() {

    }

    protected void onInvisible() {

    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //加载动画
        mAnimationDrawable = (AnimationDrawable) ivProgress.getDrawable();
        //默认进入页面就开启动画
        if (!mAnimationDrawable.isRunning()) {
            mAnimationDrawable.start();
        }
        //点击加载失败布局
        llErrorRefresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showLoading();
                onRefresh();
            }
        });
    }

    /**
     * 加载失败后点击后的操作
     */
    protected void onRefresh() {

    }

    /**
     * 布局
     */
    public abstract
    @LayoutRes
    int setContent();

    /**
     * 显示加载中的状态
     */
    protected void showLoading() {
        if (llProgressBar.getVisibility() != View.VISIBLE) {
            llProgressBar.setVisibility(View.VISIBLE);
        }
        //开始动画
        if (!mAnimationDrawable.isRunning()) {
            mAnimationDrawable.start();
        }
        if (llErrorRefresh.getVisibility() != View.GONE) {
            llErrorRefresh.setVisibility(View.GONE);
        }
        if (llContent.getVisibility() != View.VISIBLE) {
            llContent.setVisibility(View.VISIBLE);
        }
    }

    /**
     * 加载完成的状态
     */
    protected void showContentView() {
        if (llProgressBar.getVisibility() != View.GONE) {
            llProgressBar.setVisibility(View.GONE);
        }
        // 停止动画
        if (mAnimationDrawable.isRunning()) {
            mAnimationDrawable.stop();
        }
        if (llErrorRefresh.getVisibility() != View.GONE) {
            llErrorRefresh.setVisibility(View.GONE);
        }


        if (llContent.getVisibility() != View.GONE) {
            llContent.setVisibility(View.GONE);
        }
    }

    /**
     * 加载失败点击重新加载的状态
     */
    protected void showError() {
        if (llProgressBar.getVisibility() != View.GONE) {
            llProgressBar.setVisibility(View.GONE);
        }
        // 停止动画
        if (mAnimationDrawable.isRunning()) {
            mAnimationDrawable.stop();
        }
        if (llErrorRefresh.getVisibility() != View.VISIBLE) {
            llErrorRefresh.setVisibility(View.VISIBLE);
        }
        if (container.getVisibility() != View.GONE) {
            container.setVisibility(View.GONE);
        }
    }
}
