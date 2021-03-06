package com.exam.admin.rongyundemo.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Path;
import android.util.AttributeSet;

import com.exam.admin.rongyundemo.R;

/**
 * 类名: RoundedImageView
 * 描述:
 * 作者: 吴飞
 * 日期: 2017/9/20 11:36
 */

public class RoundedImageView extends android.support.v7.widget.AppCompatImageView {

    /**
     * view 的宽度
     */
    private int mViewWidth;
    /**
     * view 的高度
     */
    private int mViewHeight;

    /**
     * view四个圆角对应的半径大小
     */
    private float topLeftRadius = 0;
    private float topRightRadius = 0;
    private float bottomLeftRadius = 0;
    private float bottomRightRadius = 0;

    private Path roundedPath = null;

    public RoundedImageView(Context context) {
        this(context, null, 0);
    }

    public RoundedImageView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public RoundedImageView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        super.setScaleType(ScaleType.CENTER_CROP);

        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.Room_List_RoundedImageView, defStyle, 0);
        if (ta.hasValue(R.styleable.Room_List_RoundedImageView_room_list_riv_radius)) {
            float radius = ta.getDimensionPixelSize(R.styleable.Room_List_RoundedImageView_room_list_riv_radius, 0);
            if (radius >= 0) {
                topLeftRadius = radius;
                topRightRadius = radius;
                bottomLeftRadius = radius;
                bottomRightRadius = radius;
            }
            return;
        }
        topLeftRadius = ta.getDimensionPixelSize(R.styleable.Room_List_RoundedImageView_room_list_riv_topLeftRadius, 0);
        topRightRadius = ta.getDimensionPixelSize(R.styleable.Room_List_RoundedImageView_room_list_riv_topRightRadius, 0);
        bottomLeftRadius = ta.getDimensionPixelSize(R.styleable.Room_List_RoundedImageView_room_list_riv_bottomLeftRadius, 0);
        bottomRightRadius = ta.getDimensionPixelSize(R.styleable.Room_List_RoundedImageView_room_list_riv_bottomRightRadius, 0);
        ta.recycle();
    }

    public void setRadius(float radius) {
        if (radius >= 0) {
            topLeftRadius = radius;
            topRightRadius = radius;
            bottomLeftRadius = radius;
            bottomRightRadius = radius;
            invalidate();
        }
    }

    public void setRadius(float topLeftRadius, float topRightRadius, float bottomLeftRadius, float bottomRightRadius) {
        if (topLeftRadius >= 0) {
            this.topLeftRadius = topLeftRadius;
        }
        if (topRightRadius >= 0) {
            this.topRightRadius = topRightRadius;
        }
        if (bottomLeftRadius >= 0) {
            this.bottomLeftRadius = bottomLeftRadius;
        }
        if (bottomRightRadius >= 0) {
            this.bottomRightRadius = bottomRightRadius;
        }
        invalidate();
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        mViewWidth = getWidth();
        mViewHeight = getHeight();
        updateRoundedPath();
    }

    private void updateRoundedPath() {
        roundedPath = new Path();
//        roundedPath.addRoundRect(new RectF(0, 0, mViewWidth, mViewHeight),
//                new float[]{topLeftRadius, topLeftRadius, topRightRadius, topRightRadius,
//                        bottomLeftRadius, bottomLeftRadius, bottomRightRadius, bottomRightRadius},
//                Path.Direction.CW);

        roundedPath.lineTo(0, 0);
        roundedPath.lineTo(0, mViewHeight);
        roundedPath.lineTo(mViewWidth, mViewHeight * 2 / 3);
        roundedPath.lineTo(mViewWidth, 0);
        roundedPath.close();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if (null != roundedPath) {
            canvas.clipPath(roundedPath);
        }
        super.onDraw(canvas);
    }

//    作者：ForeverCy
//    链接：http://www.jianshu.com/p/f4088146f5ad
//    來源：简书
//    著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
}
