package com.exam.admin.rongyundemo.activity.demo;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.exam.admin.rongyundemo.R;
import com.exam.admin.rongyundemo.activity.BaseActivity;
import com.exam.admin.rongyundemo.utils.DensityUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author Administrator
 */
public class AddAmountActivity extends BaseActivity {

    @BindView(R.id.tv_amount)
    TextView tvAmount;
    @BindView(R.id.btn_hide)
    Button btnHide;
    private int hideViewHeight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_amount);
        ButterKnife.bind(this);
        setBack();
        setTvTitle("Dialog");
        cancelLoading();
        tvTimer(tvAmount);
        //获取像素密度
//        mDensity = getResources().getDisplayMetrics().density;
        //获取布局的高度

        hideViewHeight = DensityUtils.dp2px(this, 60);
    }

    private void tvTimer(final TextView tvAmount) {
        ValueAnimator animator = ValueAnimator.ofFloat(0, (float) 5201314);
        animator.setDuration(3000);
        animator.start();
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                tvAmount.setText("$" + (float) animation.getAnimatedValue());
            }
        });
    }

    @Override
    protected void onErrorRefresh() {

    }

    @OnClick(R.id.btn_click_me)
    public void onViewClicked() {
        if (btnHide.getVisibility() == View.GONE) {
            //打开动画
            animateOpen(btnHide);
        } else {
            animateClose(btnHide);
        }
    }

    private void animateClose(final View view) {
        ValueAnimator animator = createDropAnimator(view, hideViewHeight, 0);
        animator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                view.setVisibility(View.GONE);
            }
        });
        animator.start();
    }

    private void animateOpen(View view) {
        view.setVisibility(View.VISIBLE);
        ValueAnimator animator = createDropAnimator(view, 0, hideViewHeight);
        animator.start();
    }

    private ValueAnimator createDropAnimator(final View view, int start, int end) {

        ValueAnimator animator = ValueAnimator.ofInt(start, end);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                int value = (int) animation.getAnimatedValue();
                ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
                layoutParams.height = value;
                view.setLayoutParams(layoutParams);
            }
        });
        animator.setDuration(1000);
        return animator;
    }
}
