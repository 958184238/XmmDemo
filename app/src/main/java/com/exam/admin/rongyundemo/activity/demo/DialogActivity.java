package com.exam.admin.rongyundemo.activity.demo;

import android.os.Bundle;

import com.exam.admin.rongyundemo.R;
import com.exam.admin.rongyundemo.activity.BaseActivity;

public class DialogActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog);
        setBack();
        setTvTitle("Dialog");
        cancelLoading();
    }

    @Override
    protected void onErrorRefresh() {

    }
}
