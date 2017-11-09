package com.exam.admin.rongyundemo.activity;

import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.exam.admin.rongyundemo.R;
import com.exam.admin.rongyundemo.utils.NetUtils;
import com.exam.admin.rongyundemo.utils.StatusUtils;
import com.gyf.barlibrary.ImmersionBar;
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;

/**
 * ========================
 * Name: BaseActivity
 * Des:
 * User: 吴飞
 * Date: 2017/8/23 17:08
 * =========================
 *
 * @author Administrator
 */

public abstract class BaseActivity extends RxAppCompatActivity {

    private ImageView back;
    private TextView tvTitle;
    private LinearLayout llContent;
    private ImageView ivProgress;
    private LinearLayout llErrorRefresh;
    private LinearLayout llProgressBar;
    private AnimationDrawable mAnimationDrawable;
    private TextView tvSubTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.activity_base);
        //透明状态栏
        ImmersionBar.with(this)
                .statusBarColor(R.color.blue)
                .fitsSystemWindows(true)
                .init();
        StatusUtils.flymeSetStatusBarLightMode(getWindow(), true);
        initToolbar();
        //加载动画
        mAnimationDrawable = (AnimationDrawable) ivProgress.getDrawable();
        //默认进入页面开启动画
        if (!mAnimationDrawable.isRunning()) {
            mAnimationDrawable.start();
        }
        if (!NetUtils.isConnected(this)) {
            showError();
        }
        //点击加载失败的布局
        llErrorRefresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showLoading();
                onErrorRefresh();
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ImmersionBar.with(this)
                .statusBarColor(R.color.blue)
                .fitsSystemWindows(true)
                .destroy();
    }

    /**
     * 加载失败后点击后的操作
     */
    protected abstract void onErrorRefresh();

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
     * 取消加载
     */
    protected void cancelLoading() {
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
//        if (llContent.getVisibility() != View.GONE) {
//            llContent.setVisibility(View.GONE);
//        }
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
//        if (container.getVisibility() != View.GONE) {
//            container.setVisibility(View.GONE);
//        }
    }

    /**
     * 设置标题
     */
    protected void setTvTitle(String title) {
        if (tvTitle != null) {
            tvTitle.setVisibility(View.VISIBLE);
            tvTitle.setText(title);
        }
    }

    /**
     * 设置小标题
     */
    protected void setSubTitle(String title) {
        if (tvSubTitle != null) {
            tvSubTitle.setVisibility(View.VISIBLE);
            tvSubTitle.setText(title);
        }
    }

    private void initToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        if (toolbar != null) {
            setSupportActionBar(toolbar);
        }
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(false);
            actionBar.setDisplayShowTitleEnabled(false);
        }
        back = (ImageView) findViewById(R.id.img_back);
        tvTitle = (TextView) findViewById(R.id.title);
        tvSubTitle = (TextView) findViewById(R.id.tv_sub_title);
        ivProgress = (ImageView) findViewById(R.id.iv_progress);
        llErrorRefresh = (LinearLayout) findViewById(R.id.ll_error_refresh);
        llContent = (LinearLayout) findViewById(R.id.ll_content);
        llProgressBar = (LinearLayout) findViewById(R.id.ll_progress_bar);
    }

    @Override
    public void setContentView(@LayoutRes int layoutResID) {
        setContentView(View.inflate(this, layoutResID, null));
    }


    @Override
    public void setContentView(View view) {
        if (llContent == null) {
            return;
        }
        llContent.addView(view, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        initToolbar();
    }

    protected void setBack() {
        if (back != null) {
            back.setVisibility(View.VISIBLE);
            back.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finish();
                }
            });
        }
    }
}
