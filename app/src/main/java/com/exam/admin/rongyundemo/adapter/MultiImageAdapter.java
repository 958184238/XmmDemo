package com.exam.admin.rongyundemo.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.exam.admin.rongyundemo.R;
import com.exam.admin.rongyundemo.activity.MultiImageActivity;
import com.exam.admin.rongyundemo.utils.glide.GlideUtils;

import java.util.List;

/**
 * 类名: MultiImageAdapter
 * 描述:
 * 作者: 吴飞
 * 日期: 2017/9/13 16:06
 */

public class MultiImageAdapter extends BaseQuickAdapter<Uri, BaseViewHolder> {

    private List<Uri> data;
    private Context context;

    public MultiImageAdapter(Context context, @LayoutRes int layoutResId, @Nullable List<Uri> data) {
        super(layoutResId, data);
        this.context = context;
        this.data = data;
    }

    @Override
    protected void convert(BaseViewHolder helper, Uri uri) {
        ImageView ivDetail = helper.getView(R.id.iv_detail);
        GlideUtils.loadNormal(context, uri.toString(), ivDetail);
        if (helper.getAdapterPosition() == MultiImageActivity.MAX_IMAGE_SIZE) {
            ivDetail.setVisibility(View.GONE);
        } else {
            ivDetail.setVisibility(View.VISIBLE);
        }
    }
}
