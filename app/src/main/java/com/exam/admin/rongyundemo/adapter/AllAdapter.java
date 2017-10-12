package com.exam.admin.rongyundemo.adapter;

import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.exam.admin.rongyundemo.R;
import com.exam.admin.rongyundemo.http.response.AllResponse;

import java.util.List;

/**
 * ========================
 * Name: AllAdapter
 * Des:
 * User: 吴飞
 * Date: 2017/8/16 17:27
 * =========================
 */

public class AllAdapter extends BaseQuickAdapter<AllResponse.ResultsBean, BaseViewHolder> {

    private boolean isAll;

    public AllAdapter(@LayoutRes int layoutResId, @Nullable List<AllResponse.ResultsBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder holder, final AllResponse.ResultsBean item) {
        ImageView ivAllWelfare = holder.getView(R.id.iv_all_welfare);
        LinearLayout llWelfareOther = holder.getView(R.id.ll_welfare_other);
        TextView tvContentType = holder.getView(R.id.tv_content_type);
        TextView tvAndroidDes = holder.getView(R.id.tv_android_des);
        TextView tvAndroidWho = holder.getView(R.id.tv_android_who);
        TextView tvAndroidTime = holder.getView(R.id.tv_android_time);
        ImageView ivAndroidPic = holder.getView(R.id.iv_android_pic);
        LinearLayout llAll = holder.getView(R.id.ll_all);
        String url = item.getUrl();
        if (url.endsWith("jpg")) {
            ivAllWelfare.setVisibility(View.VISIBLE);
            llWelfareOther.setVisibility(View.GONE);
            Glide.with(mContext)
                    .load(item.getUrl())
                    .thumbnail(0.1f)
                    .into(ivAllWelfare);
        } else {
            ivAllWelfare.setVisibility(View.GONE);
            llWelfareOther.setVisibility(View.VISIBLE);
            tvAndroidDes.setText(item.getDesc());

            // 显示gif图片会很耗内存
            if (item.getImages() != null && item.getImages().size() > 0 && !TextUtils.isEmpty(item.getImages().get(0))) {
                ivAndroidPic.setVisibility(View.VISIBLE);
                Glide.with(mContext)
                        .load(item.getImages().get(0))
                        .thumbnail(0.1f)
                        .into(ivAndroidPic);
            } else {
                ivAndroidPic.setVisibility(View.GONE);
            }
        }
        tvAndroidWho.setText(item.getWho());
        tvAndroidTime.setText(item.getPublishedAt());
    }
}
