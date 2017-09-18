package com.exam.admin.rongyundemo.adapter;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.widget.Button;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.exam.admin.rongyundemo.R;

import java.util.List;

/**
 * 类名: DiscoverAdapter
 * 描述:
 * 作者: 吴飞
 * 日期: 2017/9/13 15:30
 */

public class DiscoverAdapter extends BaseQuickAdapter<String, BaseViewHolder> {

    private Context context;

    public DiscoverAdapter(Context context, @LayoutRes int layoutResId, @Nullable List<String> data) {
        super(layoutResId, data);
        this.context = context;
    }

    @Override
    protected void convert(BaseViewHolder holder, String item) {
        Button btn_1 = holder.getView(R.id.btn_1);
        Button btn_2 = holder.getView(R.id.btn_2);
        Button btn_3 = holder.getView(R.id.btn_3);
        holder.addOnClickListener(R.id.btn_1);
        holder.addOnClickListener(R.id.btn_2);
        holder.addOnClickListener(R.id.btn_3);
        btn_1.setText("btn_1---" + holder.getLayoutPosition());
        btn_2.setText("btn_2---" + holder.getLayoutPosition());
        btn_3.setText("btn_3---" + holder.getLayoutPosition());
        holder.setText(R.id.tv_title, item);
    }
}
