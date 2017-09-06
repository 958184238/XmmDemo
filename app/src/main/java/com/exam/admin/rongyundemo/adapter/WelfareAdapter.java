package com.exam.admin.rongyundemo.adapter;

import android.os.Build;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.exam.admin.rongyundemo.R;
import com.exam.admin.rongyundemo.service.response.WelfareResponse;
import com.squareup.picasso.Picasso;

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
//        GlideApp.with(mContext)
//                .load(item.getUrl() + "?imageView2/0/w/100")
//                .thumbnail(0.1f)
//                .into(ivWelfare);
        Picasso.with(mContext).load(item.getUrl() + "?imageView2/0/w/100").into(ivWelfare);
//        ivWelfare.setTag(item.getUrl());
        ivWelfare.setTransitionName(item.getUrl());
    }
}
