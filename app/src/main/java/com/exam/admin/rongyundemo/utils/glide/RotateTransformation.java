//package com.exam.admin.rongyundemo.utils.glide;
//
//import android.graphics.Bitmap;
//import android.graphics.Matrix;
//import android.support.annotation.NonNull;
//
//import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
//import com.bumptech.glide.load.resource.bitmap.BitmapTransformation;
//
//import java.security.MessageDigest;
//
///**
// * ========================
// * Name: RotateTransformation
// * Des:  图片旋转
// * User: 吴飞
// * Date: 2017/7/13 17:13
// * =========================
// */
//
//public class RotateTransformation extends BitmapTransformation {
//
//    public RotateTransformation(float rotateRotationAngle) {
//        this.rotateRotationAngle = rotateRotationAngle;
//    }
//
//    private float rotateRotationAngle = 0f;
//
//    @Override
//    protected Bitmap transform(@NonNull BitmapPool pool, @NonNull Bitmap toTransform, int outWidth, int outHeight) {
//        Matrix matrix = new Matrix();
//        matrix.postRotate(rotateRotationAngle);
//        return Bitmap.createBitmap(toTransform, 0, 0, toTransform.getWidth(), toTransform.getHeight(), matrix, true);
//    }
//
//    @Override
//    public void updateDiskCacheKey(MessageDigest messageDigest) {
//
//    }
//
////    private float rotateRotationAngle = 0f;
////
////    public RotateTransformation(Context context, float rotateRotationAngle) {
////        super(context);
////        this.rotateRotationAngle = rotateRotationAngle;
////    }
////
////    @Override
////    protected Bitmap transform(BitmapPool pool, Bitmap toTransform, int outWidth, int outHeight) {
////        Matrix matrix = new Matrix();
////        matrix.postRotate(rotateRotationAngle);
////        return Bitmap.createBitmap(toTransform, 0, 0, toTransform.getWidth(), toTransform.getHeight(), matrix, true);
////    }
////
//////    @Override
//////    public String getId() {
//////        return "rotate" + rotateRotationAngle;
//////    }
////
////
////    @Override
////    public void updateDiskCacheKey(MessageDigest messageDigest) {
////
////    }
//}
