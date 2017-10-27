package com.exam.admin.rongyundemo.utils.glide;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;

import jp.wasabeef.glide.transformations.BlurTransformation;
import jp.wasabeef.glide.transformations.CropCircleTransformation;

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
    public static void loadImage(Context context, String url, String thumbnailUrl, final ImageView imageView, final ProgressBar progressBar) {
        // 跳过内存缓存,不缓存到SDCard中
        Glide.with(context)
                .load(url)
                .thumbnail(Glide.with(context)
                        .load(thumbnailUrl)
                        .skipMemoryCache(true)
                        .diskCacheStrategy(DiskCacheStrategy.NONE)
                        .listener(new RequestListener<String, GlideDrawable>() {
                                      @Override
                                      public boolean onException(Exception e, String model, Target<GlideDrawable> target, boolean isFirstResource) {
                                          return false;
                                      }

                                      @Override
                                      public boolean onResourceReady(GlideDrawable resource, String model, Target<GlideDrawable> target, boolean isFromMemoryCache, boolean isFirstResource) {
                                          progressBar.setVisibility(View.VISIBLE);
                                          return false;
                                      }
                                  }

                        )).listener(new RequestListener<String, GlideDrawable>() {
            @Override
            public boolean onException(Exception e, String model, Target<GlideDrawable> target, boolean isFirstResource) {
                progressBar.setVisibility(View.GONE);
                return false;
            }

            @Override
            public boolean onResourceReady(GlideDrawable resource, String model, Target<GlideDrawable> target, boolean isFromMemoryCache, boolean isFirstResource) {
                progressBar.setVisibility(View.GONE);
                imageView.setVisibility(View.VISIBLE);
                return false;
            }
        }).into(imageView);
    }

    /**
     * 加载普通图片
     *
     * @param context
     * @param url
     * @param view
     */
    public static void loadNormal(Context context, String url, ImageView view) {
        Glide.with(context)
                .load(url)
//                .thumbnail(0.1f)
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
        Glide.with(context)
                .load(url)
//                .thumbnail(0.1f)
                .bitmapTransform(new BlurTransformation(context, 23, 4))
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
        Glide.with(context)
                .load(url)
//                .thumbnail(0.1f)
                .bitmapTransform(new CropCircleTransformation(context))
                .into(view);
    }
}
