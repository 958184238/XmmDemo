package com.exam.admin.rongyundemo.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;

import com.exam.admin.rongyundemo.R;

/**
 * @author wufei
 * @date 2017/11/21
 */

public class NumberProgressBar extends View {

    private Paint mTextPaint;
    private float mTextSize;
    private int mTextColor;
    private int number;
    private Paint mRectPaint;
    private int mRectColor;
    private Context context;
    private int measuredWidth;
    private int measuredHeight;

    public NumberProgressBar(Context context) {
        this(context, null);
    }

    public NumberProgressBar(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public NumberProgressBar(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs, defStyleAttr);
    }

    private void init(Context context, AttributeSet attrs, int defStyleAttr) {
        initDefaultValue();
        this.context = context;
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.NumberProgressBar, defStyleAttr, 0);
        int indexCount = ta.getIndexCount();
        for (int i = 0; i < indexCount; i++) {
            int index = ta.getIndex(i);
            switch (index) {
                case R.styleable.NumberProgressBar_rectColor:
                    mRectColor = ta.getColor(index, mRectColor);
                    break;
                case R.styleable.NumberProgressBar_rectTextColor:
                    mTextColor = ta.getColor(index, mTextColor);
                    break;
                case R.styleable.NumberProgressBar_rectTextSize:
                    mTextSize = ta.getDimension(index, mTextSize);
                    break;
                default:
                    break;
            }
        }

        ta.recycle();
        initPaint();

    }

    private void initDefaultValue() {
        //初始值
        mTextSize = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 14, getResources().getDisplayMetrics());
        mTextColor = 0xff000000;
        mRectColor = 0x331f2d3d;
    }

    private void initPaint() {
        mTextPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mTextPaint.setStyle(Paint.Style.FILL);
        mTextPaint.setTextSize(mTextSize);
        mTextPaint.setColor(mTextColor);

        mRectPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mRectPaint.setStyle(Paint.Style.FILL);
        mRectPaint.setColor(mRectColor);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        setMeasuredDimension(measureWidth(widthMeasureSpec), measureWidth(heightMeasureSpec));
        measuredWidth = getMeasuredWidth();
        measuredHeight = getMeasuredHeight();
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


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        //绘制矩形
        canvas.drawRect(0, ((float) number / 100) * measuredHeight, measuredWidth, measuredHeight, mRectPaint);

        String text = number + "%";
        Rect rect = new Rect();
        mTextPaint.getTextBounds(text, 0, text.length(), rect);
        //文字居中显示
        canvas.drawText(text, 0 - rect.left + (getWidth() - rect.width()) / 2, 0 - rect.top + (getHeight() - rect.height()) / 2, mTextPaint);
    }

    public void setNumberProgress(int number) {
        this.number = number;
        invalidate();
    }
}
