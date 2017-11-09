package com.exam.admin.rongyundemo.fragment.main;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.exam.admin.rongyundemo.R;
import com.exam.admin.rongyundemo.activity.demo.DialogActivity;
import com.exam.admin.rongyundemo.activity.demo.MultiImageActivity;
import com.exam.admin.rongyundemo.activity.demo.StaggeredActivity;
import com.exam.admin.rongyundemo.adapter.DiscoverAdapter;
import com.exam.admin.rongyundemo.fragment.BaseFragment;
import com.exam.admin.rongyundemo.utils.ToastUtils;
import com.jakewharton.rxbinding2.view.RxView;
import com.tbruyelle.rxpermissions2.RxPermissions;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.reactivex.functions.Consumer;

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
    @BindView(R.id.recyclerview)
    RecyclerView recyclerview;
    private FragmentActivity context;
    private RxPermissions rxPermission;

    @Override
    public int setContent() {
        return R.layout.discover_fragment;
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        cancelLoading();
        context = getActivity();
        rxPermission = new RxPermissions(context);
        List<String> list = new ArrayList<>();
        list.add("多图片上传");
        list.add("照片墙");
        list.add("Dialog");
        list.add("权限");
        LinearLayoutManager manager = new LinearLayoutManager(mContext);
        recyclerview.setLayoutManager(manager);
        DiscoverAdapter adapter = new DiscoverAdapter(mContext, R.layout.discover_item, list);
        recyclerview.setAdapter(adapter);
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                switch (position) {
                    case 0:
                        startActivity(new Intent(mContext, MultiImageActivity.class));
                        break;
                    case 1:
                        startActivity(new Intent(mContext, StaggeredActivity.class));
                        break;
                    case 2:
                        startActivity(new Intent(mContext, DialogActivity.class));
                        break;
                    case 3:
                        RxView.clicks(view)
                                .compose(rxPermission.ensure(Manifest.permission.CAMERA))
                                .subscribe(new Consumer<Boolean>() {
                                    @Override
                                    public void accept(Boolean aBoolean) throws Exception {
                                        if (aBoolean) {
                                            ToastUtils.showShort(context, "成功");
                                        } else {
                                            ToastUtils.showShort(context, "失败");

                                        }
                                    }
                                });
                        break;
                    default:
                        break;

                }
            }
        });
        adapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
//                switch (view.getId()) {
//                    case R.id.btn_1:
//                        ToastUtils.showShort(mContext, "btn_1_hahah_child" + position);
//                        break;
//                    case R.id.btn_2:
//                        ToastUtils.showShort(mContext, "btn_2_hahah_child" + position);
//                        break;
//                    case R.id.btn_3:
//                        ToastUtils.showShort(mContext, "btn_3_hahah_child" + position);
//                        break;
//                }
            }
        });
    }

    @Override
    protected void onErrorRefresh() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

}
