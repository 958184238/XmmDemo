package com.exam.admin.rongyundemo.fragment.main;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.exam.admin.rongyundemo.R;
import com.exam.admin.rongyundemo.adapter.OrientationAdapter;
import com.exam.admin.rongyundemo.utils.ToastUtils;
import com.exam.admin.rongyundemo.utils.glide.GlideUtils;
import com.youth.banner.Banner;
import com.youth.banner.loader.ImageLoader;

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

    Unbinder unbinder;
    @BindView(R.id.banner)
    Banner banner;
    @BindView(R.id.orientation_recyclerview)
    RecyclerView horizontalRecyclerview;
    private FragmentActivity context;
    private int lv0Count = 2;
    private int lv1Count = 2;
    private int personCount = 5;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.homepage, null);
        context = getActivity();
        unbinder = ButterKnife.bind(this, view);
        initBanner();
        initOrientationRecyclerview();
        return view;
    }

    private void initOrientationRecyclerview() {
        LinearLayoutManager manager = new LinearLayoutManager(context);
        manager.setOrientation(LinearLayoutManager.HORIZONTAL);
        horizontalRecyclerview.setLayoutManager(manager);
        List<String> imageList = new ArrayList<>();
        imageList.add("https://ws1.sinaimg.cn/large/610dc034ly1fjfae1hjslj20u00tyq4x.jpg");
        imageList.add("http://ww1.sinaimg.cn/large/610dc034ly1fjaxhky81vj20u00u0ta1.jpg");
        imageList.add("https://ws1.sinaimg.cn/large/610dc034ly1fivohbbwlqj20u011idmx.jpg");
        imageList.add("https://ws1.sinaimg.cn/large/610dc034ly1fj78mpyvubj20u011idjg.jpg");
        imageList.add("https://ws1.sinaimg.cn/large/610dc034ly1fj3w0emfcbj20u011iabm.jpg");
        imageList.add("https://ws1.sinaimg.cn/large/610dc034ly1fj2ld81qvoj20u00xm0y0.jpg");
        OrientationAdapter adapter = new OrientationAdapter(context, R.layout.banner_item, imageList);
        horizontalRecyclerview.setAdapter(adapter);
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                ToastUtils.showLong(context, "sss" + position);
            }
        });
    }

    private void initBanner() {
        List<String> imageList = new ArrayList<>();
        imageList.add("https://ws1.sinaimg.cn/large/610dc034ly1fjfae1hjslj20u00tyq4x.jpg");
        imageList.add("http://ww1.sinaimg.cn/large/610dc034ly1fjaxhky81vj20u00u0ta1.jpg");
        imageList.add("https://ws1.sinaimg.cn/large/610dc034ly1fivohbbwlqj20u011idmx.jpg");
        imageList.add("https://ws1.sinaimg.cn/large/610dc034ly1fj78mpyvubj20u011idjg.jpg");
        imageList.add("https://ws1.sinaimg.cn/large/610dc034ly1fj3w0emfcbj20u011iabm.jpg");
        imageList.add("https://ws1.sinaimg.cn/large/610dc034ly1fj2ld81qvoj20u00xm0y0.jpg");
        banner.setImages(imageList).setImageLoader(new ImageLoader() {
            @Override
            public void displayImage(Context context, Object path, ImageView imageView) {
                GlideUtils.loadNormal(context, (String) path, imageView);
            }
        }).start();
    }

    @Override
    public void onStart() {
        super.onStart();
        banner.startAutoPlay();
    }

    @Override
    public void onStop() {
        super.onStop();
        banner.stopAutoPlay();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }


}
