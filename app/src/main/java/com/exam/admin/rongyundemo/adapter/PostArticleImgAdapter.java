package com.exam.admin.rongyundemo.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.exam.admin.rongyundemo.R;
import com.exam.admin.rongyundemo.activity.MultiImageActivity;
import com.exam.admin.rongyundemo.utils.glide.GlideUtils;

import java.util.List;

/**
 * 类名: PostArticleImgAdapter
 * 描述:
 * 作者: 吴飞
 * 日期: 2017/9/14 14:56
 */

public class PostArticleImgAdapter extends RecyclerView.Adapter<PostArticleImgAdapter.MyViewHolder> {

    private LayoutInflater mLayoutInflater;
    private Context context;
    private List<Uri> uris;

    public PostArticleImgAdapter(Context context, List<Uri> uris) {
        this.context = context;
        this.uris = uris;
        this.mLayoutInflater = LayoutInflater.from(context);
    }

    @Override
    public PostArticleImgAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyViewHolder(mLayoutInflater.inflate(R.layout.multi_image_item, parent, false));
    }

    @Override
    public void onBindViewHolder(PostArticleImgAdapter.MyViewHolder holder, int position) {
        if (position >= MultiImageActivity.MAX_IMAGE_SIZE) {
            //图片已选完，隐藏添加按钮
            holder.itemView.setVisibility(View.GONE);
        } else {
            holder.itemView.setVisibility(View.VISIBLE);
        }
        GlideUtils.loadNormal(context, uris.get(position).toString(), holder.imageView);
    }

    @Override
    public int getItemCount() {
        return uris == null ? 0 : uris.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;

        public MyViewHolder(View itemView) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.iv_detail);
        }
    }
}
