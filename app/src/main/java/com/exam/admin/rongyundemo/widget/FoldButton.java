package com.exam.admin.rongyundemo.widget;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.View;

import com.exam.admin.rongyundemo.R;

/**
 * 折叠按钮
 *
 * @author wufei
 * @date 2017/11/10
 */

public class FoldButton extends View {
    private static final int DEFAULT_DURATION = 350;
    private float mTextSize;
    private int mBgColor;
    private String mText;
    private int mPerAnimDuration = DEFAULT_DURATION;
    private ValueAnimator mAnimReduceHint;
    private ValueAnimator mAnimExpandHint;
    private int mWidth;
    private int mHeight;
    private Paint mHintPaint;
    private int mLeft;
    private int mTop;
    private float mRadius;
    private float mAnimReduceHintFraction;
    private float mCircleWidth;
    private int mHintBgRoundValue;
    private float mAnimExpandHintFraction;

    public FoldButton(Context context) {
        this(context, null);
    }

    public FoldButton(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public FoldButton(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs, defStyleAttr);
    }

    private void init(Context context, AttributeSet attrs, int defStyleAttr) {
        initDefaultValue();
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.FoldButton, defStyleAttr, 0);
        int indexCount = ta.getIndexCount();
        for (int i = 0; i < indexCount; i++) {
            int index = ta.getIndex(i);
            switch (index) {
                case R.styleable.FoldButton_bgColor:
                    mBgColor = ta.getColor(index, mBgColor);
                    break;
                case R.styleable.FoldButton_text:
                    mText = ta.getString(index);
                    break;
                case R.styleable.FoldButton_textSize:
                    mTextSize = ta.getDimension(index, mTextSize);
                    break;
                default:
                    break;
            }
        }
        ta.recycle();
        //收缩动画
        mAnimReduceHint = ValueAnimator.ofFloat(0, 1);
        mAnimReduceHint.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                mAnimReduceHintFraction = (float) animation.getAnimatedValue();
                invalidate();
            }
        });
        mAnimReduceHint.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);

            }

            @Override
            public void onAnimationStart(Animator animation) {
                super.onAnimationStart(animation);

            }
        });
        mAnimReduceHint.setDuration(mPerAnimDuration);

        //展开动画
        mAnimExpandHint = ValueAnimator.ofFloat(1, 0);
        mAnimExpandHint.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                mAnimExpandHintFraction = (float) animation.getAnimatedValue();
                invalidate();
            }
        });
        mAnimExpandHint.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationStart(Animator animation, boolean isReverse) {

            }

            @Override
            public void onAnimationEnd(Animator animation, boolean isReverse) {

            }
        });
        mAnimExpandHint.setDuration(mPerAnimDuration);

        mHintPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mHintPaint.setStyle(Paint.Style.FILL);
        mHintPaint.setTextSize(mTextSize);
    }

    /**
     * 设置初始值
     */
    private void initDefaultValue() {
        mTextSize = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 14, getResources().getDisplayMetrics());
        mRadius = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 12.5f, getResources().getDisplayMetrics());
        mCircleWidth = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 1f, getResources().getDisplayMetrics());
        mHintBgRoundValue = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 5, getResources().getDisplayMetrics());
        mBgColor = 0xff00a0e9;
        mText = "预约qc";
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mLeft = getPaddingLeft();
        mTop = getPaddingTop();
        mWidth = w;
        mHeight = h;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        setMeasuredDimension(measureWidth(widthMeasureSpec), measureWidth(heightMeasureSpec));
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //绘制圆角矩形
        //背景色
        mHintPaint.setColor(mBgColor);
        RectF rectF = new RectF((float) (mLeft + (mWidth - mRadius * 2) * mAnimReduceHintFraction * 0.5), mTop
                , mWidth - mCircleWidth, mHeight - mCircleWidth);
//        canvas.drawRect(rectF,mHintPaint);

        canvas.drawRoundRect(rectF, mHintBgRoundValue, mHintBgRoundValue, mHintPaint);
    }

    /**
     * 通用的测量模板
     *
     * @param measureSpec
     * @return
     */
    private int measureWidth(int measureSpec) {
        int result = 0;
        int specMode = MeasureSpec.getMode(measureSpec);
        int specSize = MeasureSpec.getSize(measureSpec);
        if (specMode == MeasureSpec.EXACTLY) {
            result = specSize;
        } else {
            result = 400;
            if (specMode == MeasureSpec.AT_MOST) {
                result = Math.min(result, specSize);
            }
        }

        return result;
    }

    /**
     * 暂停所有动画
     */
    private void cancelAllAnim() {
        if (mAnimExpandHint != null && mAnimExpandHint.isRunning()) {
            mAnimExpandHint.cancel();
        }
        if (mAnimReduceHint != null && mAnimReduceHint.isRunning()) {
            mAnimReduceHint.cancel();
        }
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int action = event.getAction();
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                cancelAllAnim();
                mAnimReduceHint.start();
//                mAnimExpandHint.start();
                break;
        }
        return super.onTouchEvent(event);
    }
}
