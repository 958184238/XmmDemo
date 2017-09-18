package com.exam.admin.rongyundemo.adapter;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.exam.admin.rongyundemo.R;
import com.exam.admin.rongyundemo.utils.glide.GlideUtils;

import java.util.List;

/**
 * ========================
 * Name: OrientationAdapter
 * Des:
 * User: 吴飞
 * Date: 2017/9/12 16:44
 * =========================
 */

public class OrientationAdapter extends BaseQuickAdapter<String, BaseViewHolder> {

    private Context context;

    public OrientationAdapter(Context context, @LayoutRes int layoutResId, @Nullable List<String> data) {
        super(layoutResId, data);
        this.context = context;
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {
        ImageView ivBanner = helper.getView(R.id.iv_banner);
        GlideUtils.loadCropCircle(context, item, ivBanner);
    }
}
