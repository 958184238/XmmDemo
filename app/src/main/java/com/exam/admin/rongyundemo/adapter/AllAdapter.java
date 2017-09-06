package com.exam.admin.rongyundemo.adapter;

import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.exam.admin.rongyundemo.R;
import com.exam.admin.rongyundemo.activity.webview.WebViewActivity;
import com.exam.admin.rongyundemo.service.response.AllResponse;
import com.sunfusheng.glideimageview.progress.GlideApp;

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
        ImageView ivAndroidPic = holder.getView(R.id.iv_android_pic);
        LinearLayout llAll = holder.getView(R.id.ll_all);
        if ("福利".equals(item.getType())) {
            ivAllWelfare.setVisibility(View.VISIBLE);
            llWelfareOther.setVisibility(View.GONE);
            GlideApp.with(mContext)
                    .load(item.getUrl()    + "?imageView2/0/w/100")
                    .thumbnail(0.1f)
                    .into(ivAllWelfare);
        } else {
            ivAllWelfare.setVisibility(View.GONE);
            llWelfareOther.setVisibility(View.VISIBLE);
        }
//
//        if (isAll) {
//            tvContentType.setVisibility(View.VISIBLE);
//            tvContentType.setText(" · " + item.getType());
//        } else {
//            tvContentType.setVisibility(View.GONE);
//
//        }
//        binding.setResultsBean(item);
//        binding.executePendingBindings();

        // 显示gif图片会很耗内存
        if (item.getImages() != null
                && item.getImages().size() > 0
                && !TextUtils.isEmpty(item.getImages().get(0))) {
            ivAndroidPic.setVisibility(View.VISIBLE);
//            llWelfareOther.setVisibility(View.GONE);
            GlideApp.with(mContext)
                    .load(item.getImages().get(0))
                    .thumbnail(0.1f)
                    .into(ivAndroidPic);
        } else {
            ivAndroidPic.setVisibility(View.GONE);
        }

        llAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                WebViewActivity.loadUrl(v.getContext(), item.getUrl(), "加载中...");
            }
        });
    }
}
