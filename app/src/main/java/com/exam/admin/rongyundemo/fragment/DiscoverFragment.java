package com.exam.admin.rongyundemo.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.exam.admin.rongyundemo.R;
import com.exam.admin.rongyundemo.activity.CircleFriendsActivity;

import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * ========================
 * Name: DiscoverFragment
 * Des:  发现
 * User: 吴飞
 * Date: 2017/8/15 9:47
 * =========================
 */

public class DiscoverFragment extends BaseFragment {


    Unbinder unbinder;

    @Override
    public int setContent() {
        return R.layout.discover_fragment;
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        cancelLoading();
    }

    @Override
    protected void onErrorRefresh() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick(R.id.btn_circle_friends)
    public void onViewClicked() {
        startActivity(new Intent(mContext, CircleFriendsActivity.class));
    }
}
