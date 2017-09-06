package com.exam.admin.rongyundemo.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;

import com.exam.admin.rongyundemo.R;
import com.exam.admin.rongyundemo.contanst.SealConst;
import com.exam.admin.rongyundemo.utils.SPUtils;

public class SplashActivity extends AppCompatActivity {

    private SplashActivity context;
    private Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        context = this;
        String loginToken = (String) SPUtils.get(context, SealConst.SEALTALK_LOGING_TOKEN, "");
        if (TextUtils.isEmpty(loginToken)) {
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    goToLogin();
                }
            }, 800);
        } else {
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    goToMain();
                }
            }, 800);
        }
    }

    private void goToMain() {
        startActivity(new Intent(context, MainActivity.class));
        finish();
    }

    private void goToLogin() {
        startActivity(new Intent(context, LoginActivity.class));
        finish();
    }
}
