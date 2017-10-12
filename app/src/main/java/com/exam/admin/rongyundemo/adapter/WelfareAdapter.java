package com.exam.admin.rongyundemo.adapter;

import android.os.Build;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.exam.admin.rongyundemo.R;
import com.exam.admin.rongyundemo.http.response.WelfareResponse;
import com.exam.admin.rongyundemo.utils.glide.GlideUtils;

import java.util.List;

/**
 * ========================
 * Name: WelfareAdapter
 * Des:
 * User: 吴飞
 * Date: 2017/8/15 13:45
 * =========================
 */

public class WelfareAdapter extends BaseQuickAdapter<WelfareResponse.ResultsBean, BaseViewHolder> {
    public WelfareAdapter(@LayoutRes int layoutResId, @Nullable List<WelfareResponse.ResultsBean> data) {
        super(layoutResId, data);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void convert(BaseViewHolder holder, WelfareResponse.ResultsBean item) {
        ImageView ivWelfare = holder.getView(R.id.iv_welfare);
        GlideUtils.loadNormal(mContext, item.getUrl(), ivWelfare);
//        ivWelfare.setTransitionName(item.getUrl());
    }
}
