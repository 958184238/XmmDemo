package com.exam.admin.rongyundemo.fragment.main;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.exam.admin.rongyundemo.R;
import com.exam.admin.rongyundemo.fragment.BaseFragment;
import com.exam.admin.rongyundemo.utils.glide.GlideUtils;

import butterknife.BindView;

/**
 * ========================
 * Name: MeFragment
 * Des:  我的
 * User: 吴飞
 * Date: 2017/8/15 9:48
 * =========================
 */

public class MeFragment extends BaseFragment {
    @BindView(R.id.iv_bg)
    ImageView ivBg;
    @BindView(R.id.iv_small)
    ImageView ivSmall;

    @Override
    public int setContent() {
        return R.layout.fragment_me;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        cancelLoading();
        String url = "https://ws1.sinaimg.cn/large/610dc034ly1fjfae1hjslj20u00tyq4x.jpg";
        //高斯模糊图
        GlideUtils.loadBlur(mContext, url, ivBg);
        //圆形图片
        GlideUtils.loadCropCircle(mContext, url, ivSmall);
    }

    @Override
    protected void onErrorRefresh() {

    }
}
