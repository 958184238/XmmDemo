package com.exam.admin.rongyundemo.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.entity.AbstractExpandableItem;
import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.exam.admin.rongyundemo.R;

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
    @BindView(R.id.recyclerview)
    RecyclerView recyclerview;
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
        init(view);
        return view;
    }

    private void init(View view) {
        //树形列表

    }

    private ArrayList<MultiItemEntity> generateData() {
        ArrayList<MultiItemEntity> res = new ArrayList<>();
        for (int i = 0; i < lv0Count; i++) {
            Level0Item lv0 = new Level0Item("haha" + i);
            for (int j = 0; j < lv1Count; j++) {
                Level1Item lv1 = new Level1Item("haha" + j);
                for (int k = 0; k < personCount; k++) {
                    lv1.addSubItem(new Person());
                }
                lv0.addSubItem(lv1);
            }
            res.add(lv0);
        }
        return res;
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    public class ExpandableItemAdapter extends BaseMultiItemQuickAdapter<MultiItemEntity, BaseViewHolder> {

        public ExpandableItemAdapter(List<MultiItemEntity> data) {
            super(data);
        }


        @Override
        protected void convert(BaseViewHolder helper, MultiItemEntity item) {

        }
    }

    public class Level0Item extends AbstractExpandableItem<Level1Item> implements MultiItemEntity {
        public Level0Item(String s) {

        }

        @Override
        public int getLevel() {
            return 0;
        }

        @Override
        public int getItemType() {
            return 0;
        }
    }

    public class Level1Item extends AbstractExpandableItem<Person> {
        public Level1Item(String s) {

        }

        @Override
        public int getLevel() {
            return 0;
        }
    }

    public class Person {
    }


}
