package com.exam.admin.rongyundemo.activity;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.exam.admin.rongyundemo.R;
import com.exam.admin.rongyundemo.fragment.DiscoverFragment;
import com.exam.admin.rongyundemo.fragment.HomePageFragment;
import com.exam.admin.rongyundemo.fragment.MeFragment;
import com.exam.admin.rongyundemo.fragment.drysaltery.AllFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @BindView(R.id.main_bottom_container)
    LinearLayout bottom;
    private Fragment[] fragments = new Fragment[4];
    private boolean isQuit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        // 遍历bottom的所有的孩子，设置点击事件
        int childCount = bottom.getChildCount();
        for (int i = 0; i < childCount; i++) {
            bottom.getChildAt(i).setOnClickListener(this);
        }

        onClick(bottom.getChildAt(0));
    }

    @Override
    public void onClick(View v) {
        // 找到点击的index
        int index = bottom.indexOfChild(v);
        // 更新底部的Ui
        updateBottomUi(index);
        // 切换Fragment
        switchFragment(index);
    }


    private void updateBottomUi(int index) {
        // 遍历bottom所有的孩子，检查角标，如果和index一样，就enable =false，如果不一样，就true
        int childCount = bottom.getChildCount();
        for (int i = 0; i < childCount; i++) {
            setEnable(bottom.getChildAt(i), i != index);
        }
    }

    private void switchFragment(int index) {
        android.support.v4.app.FragmentTransaction mTransaction = getSupportFragmentManager().beginTransaction();
        hideFragments(mTransaction);
        if (fragments[index] != null) {
            mTransaction.show(fragments[index]);
        } else {
            switch (index) {
                case 0:
                    fragments[index] = new HomePageFragment();
                    mTransaction.add(R.id.main_fragment_container, fragments[index]);
                    break;
                case 1:
                    fragments[index] = new AllFragment();
                    mTransaction.add(R.id.main_fragment_container, fragments[index]);
                    break;
                case 2:
                    fragments[index] = new DiscoverFragment();
                    mTransaction.add(R.id.main_fragment_container, fragments[index]);
                    break;
                case 3:
                    fragments[index] = new MeFragment();
                    mTransaction.add(R.id.main_fragment_container, fragments[index]);
                    break;
            }
        }
        mTransaction.commit();

    }

    private void hideFragments(android.support.v4.app.FragmentTransaction mTransaction) {
        for (int i = 0; i < fragments.length; i++) {
            if (null != fragments[i])
                mTransaction.hide(fragments[i]);
        }
    }

    public static void setEnable(View v, boolean enable) {
        v.setEnabled(enable);
        // 如果传入的是ViewGroup，我们还把孩子也递归地设置为同样的enable
        if (v instanceof ViewGroup) {
            int childCount = ((ViewGroup) v).getChildCount();
            for (int i = 0; i < childCount; i++) {
                setEnable(((ViewGroup) v).getChildAt(i), enable);
            }
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {

            if (isQuit == false) {
                isQuit = true;
                Toast.makeText(getBaseContext(), "再按一次退出", Toast.LENGTH_SHORT).show();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        isQuit = false;
                    }
                }, 2000);
            } else {
                finish();
            }
        }
        return true;
    }
}
