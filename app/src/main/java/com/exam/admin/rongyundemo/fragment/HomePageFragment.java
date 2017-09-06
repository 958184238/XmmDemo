package com.exam.admin.rongyundemo.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.exam.admin.rongyundemo.R;
import com.exam.admin.rongyundemo.fragment.drysaltery.AllFragment;
import com.exam.admin.rongyundemo.fragment.drysaltery.AndroidFragment;
import com.exam.admin.rongyundemo.fragment.drysaltery.EntertainmenteFragment;
import com.exam.admin.rongyundemo.fragment.drysaltery.ExpandFragment;
import com.exam.admin.rongyundemo.fragment.drysaltery.FrontFragment;
import com.exam.admin.rongyundemo.fragment.drysaltery.IOSFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * ========================
 * Name: HomePageFragment
 * Des: 首页
 * User: 吴飞
 * Date: 2017/8/15 9:45
 * =========================
 */

public class HomePageFragment extends Fragment {

    @BindView(R.id.tablayout)
    TabLayout tablayout;
    @BindView(R.id.viewpager)
    ViewPager viewpager;
    Unbinder unbinder;
    private FragmentActivity context;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.homepage, null);
        context = getActivity();
        unbinder = ButterKnife.bind(this, view);

        tablayout = (TabLayout) view.findViewById(R.id.tablayout);
        // 将ViewPager和TabLayout绑定
        tablayout.setupWithViewPager(viewpager);
        // 设置tab文本的没有选中（第一个参数）和选中（第二个参数）的颜色
        int blue = ContextCompat.getColor(context, R.color.blue);
        int gray = ContextCompat.getColor(context, R.color.gray);
        tablayout.setTabTextColors(gray, blue);
//        福利 | Android | iOS | 休息视频 | 拓展资源 | 前端 | all
        final List<Fragment> list = new ArrayList<>();
        list.add(new AllFragment());
        list.add(new WelfareFragment());
        list.add(new AndroidFragment());
        list.add(new IOSFragment());
        list.add(new EntertainmenteFragment());
        list.add(new ExpandFragment());
        list.add(new FrontFragment());
        final String[] title = new String[]{"All", "福利", "Android", "iOS", "休息视频", "拓展资源", "前端"};
        viewpager.setOffscreenPageLimit(5);
        FragmentPagerAdapter fragmentPagerAdapter = new FragmentPagerAdapter(getChildFragmentManager()) {
            @Override
            public int getCount() {
                return list.size();
            }

            @Override
            public Fragment getItem(int position) {
                return list.get(position);
            }

            @Override
            public CharSequence getPageTitle(int position) {
                return title[position];
            }
        };
        viewpager.setAdapter(fragmentPagerAdapter);
        return view;
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
