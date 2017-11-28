package com.exam.admin.rongyundemo.utils.glide;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.Target;

import jp.wasabeef.glide.transformations.BlurTransformation;

/**
 * Name: GlideUtils
 * Date: 2017/8/17 14:46
 *
 * @author wufei
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
    public static void loadImage(Context context, String url, String thumbnailUrl, final ImageView imageView, final ProgressBar progressBar) {
        // 跳过内存缓存,不缓存到SDCard中
        GlideApp.with(context)
                .load(url)
                .thumbnail(GlideApp.with(context)
                        .load(thumbnailUrl)
                        .skipMemoryCache(true)
                        .diskCacheStrategy(DiskCacheStrategy.NONE)
                        .listener(new RequestListener<Drawable>() {
                            @Override
                            public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                                return false;
                            }

                            @Override
                            public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                                progressBar.setVisibility(View.VISIBLE);
                                return false;
                            }
                        }))
                .listener(new RequestListener<Drawable>() {
                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                        progressBar.setVisibility(View.GONE);
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                        progressBar.setVisibility(View.GONE);
                        imageView.setVisibility(View.VISIBLE);
                        return false;
                    }
                })
                .into(imageView);
    }

    /**
     * 加载普通图片
     *
     * @param context
     * @param url
     * @param view
     */
    public static void loadNormal(Context context, String url, ImageView view) {
        GlideApp.with(context)
                .load(url)
                .into(view);
    }

    /**
     * 加载高斯模糊图片
     *
     * @param context
     * @param url
     * @param view
     */
    public static void loadBlur(Context context, String url, ImageView view) {
        GlideApp.with(context)
                .load(url)
                .apply(RequestOptions.bitmapTransform(new BlurTransformation(23, 4)))
                .into(view);
    }

    /**
     * 加载圆形图片
     *
     * @param context
     * @param url
     * @param view
     */

    public static void loadCropCircle(Context context, String url, ImageView view) {
        GlideApp.with(context)
                .load(url)
                .apply(RequestOptions.circleCropTransform())
                .into(view);
    }
}
