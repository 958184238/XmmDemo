package com.exam.admin.rongyundemo.activity;

import android.support.annotation.LayoutRes;
import android.support.v7.app.AppCompatActivity;

/**
 * ========================
 * Name: BaseActivity
 * Des:
 * User: 吴飞
 * Date: 2017/8/23 17:08
 * =========================
 */

public abstract class BaseActivity extends AppCompatActivity{

    @Override
    public void setContentView(@LayoutRes int layoutResID) {
        super.setContentView(layoutResID);

    }
}
