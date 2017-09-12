package com.exam.admin.rongyundemo.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.exam.admin.rongyundemo.R;
import com.exam.admin.rongyundemo.utils.glide.CircleTransform;

import butterknife.BindView;
import butterknife.Unbinder;
import jp.wasabeef.glide.transformations.BlurTransformation;

/**
 * ========================
 * Name: MeFragment
 * Des:  我的
 * User: 吴飞
 * Date: 2017/8/15 9:48
 * =========================
 */

public class MeFragment extends BaseFragment {
    @BindView(R.id.iv_bg)
    ImageView ivBg;
    @BindView(R.id.iv_small)
    ImageView ivSmall;
    Unbinder unbinder;

    @Override
    public int setContent() {
        return R.layout.fragment_me;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        cancelLoading();
        String url = "http://upload.tradeaider.com/report/2017/09/01/47256164877c406790380ea780ec19e1.jpg";
        //高斯模糊图
        RequestOptions options = new RequestOptions();
        options.transform(new BlurTransformation(mContext, 23, 4));
        Glide.with(this)
                .load(url)
                .apply(options)
                .into(ivBg);
        //圆形图片
        RequestOptions options1 = new RequestOptions();
        options1.transform(new CircleTransform(mContext));
        Glide.with(this).load(url).apply(options1).into(ivSmall);

    }

    @Override
    protected void onErrorRefresh() {

    }

//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//        // TODO: inflate a fragment view
//        View rootView = super.onCreateView(inflater, container, savedInstanceState);
//        unbinder = ButterKnife.bind(this, rootView);
//        return rootView;
//    }
//
//    @Override
//    public void onDestroyView() {
//        super.onDestroyView();
//        unbinder.unbind();
//    }
}
