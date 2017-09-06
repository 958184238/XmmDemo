package com.exam.admin.rongyundemo.utils.glide;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.Target;
import com.sunfusheng.glideimageview.GlideImageLoader;
import com.sunfusheng.glideimageview.progress.GlideApp;
import com.sunfusheng.glideimageview.progress.OnGlideImageViewListener;

/**
 * ========================
 * Name: GlideUtils
 * Des:
 * User: 吴飞
 * Date: 2017/8/17 14:46
 * =========================
 */

public class GlideUtils {

    /**
     * 加载网络图片(大图)
     *
     * @param context
     * @param url          原图路径
     * @param thumbnailUrl 缩略图路径
     * @param imageView
     */
    public static void loadImage(Context context, String url, String thumbnailUrl, ImageView imageView, final ProgressBar progressBar) {
        // 跳过内存缓存,不缓存到SDCard中
        RequestOptions requestOptions = new RequestOptions()
                .skipMemoryCache(true)
                .diskCacheStrategy(DiskCacheStrategy.NONE);

        GlideApp.with(context)
                .load(url)
//                .error(R.mipmap.jianli_icons)
                //加载缩略图
//                .thumbnail(Glide.with(mContext)
//                        .load(thumbnailUrl)
//                        .listener(new RequestListener<Drawable>() {
//                            @Override
//                            public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
//                                return false;
//                            }
//
//                            @Override
//                            public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
//                                progressBar.setVisibility(View.VISIBLE);
//                                return false;
//                            }
//                        })
//                        .apply(requestOptions))
                .thumbnail(0.1f)
                .transition(DrawableTransitionOptions.withCrossFade())
                .listener(new RequestListener<Drawable>() {
                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                        progressBar.setVisibility(View.GONE);
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                        progressBar.setVisibility(View.GONE);
//                        view.setVisibility(View.VISIBLE);
                        return false;
                    }
                })
                .into(imageView);
        GlideImageLoader loader = new GlideImageLoader(imageView);
        loader.setOnGlideImageViewListener(url, new OnGlideImageViewListener() {
            @Override
            public void onProgress(int percent, boolean isDone, GlideException exception) {
                progressBar.setProgress(percent);
                progressBar.setVisibility(isDone ? View.GONE : View.VISIBLE);
            }
        });


    }

    /**
     * 加载gif图片
     *
     * @param context
     * @param url
     * @param view
     */
    public static void loadGif(Context context, String url, ImageView view) {
        RequestOptions requestOptions = new RequestOptions();
        GlideApp.with(context)
                .load(url)
                .thumbnail(0.1f)
//                .transition(new )
                .into(view);
    }
}
