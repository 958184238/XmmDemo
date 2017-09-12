package com.exam.admin.rongyundemo.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.exam.admin.rongyundemo.R;
import com.exam.admin.rongyundemo.contanst.SealConst;
import com.exam.admin.rongyundemo.service.frame.HttpSubscriber;
import com.exam.admin.rongyundemo.service.frame.SealBaseUrl;
import com.exam.admin.rongyundemo.service.frame.RetrofitAPIManager;
import com.exam.admin.rongyundemo.service.frame.SealApi;
import com.exam.admin.rongyundemo.service.request.LoginRequest;
import com.exam.admin.rongyundemo.service.response.LoginResponse;
import com.exam.admin.rongyundemo.utils.AMUtils;
import com.exam.admin.rongyundemo.utils.NToast;
import com.exam.admin.rongyundemo.utils.SPUtils;
import com.exam.admin.rongyundemo.widget.ClearWriteEditText;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class LoginActivity extends Activity {

    @BindView(R.id.de_img_backgroud)
    ImageView deImgBackgroud;
    @BindView(R.id.frm_bg)
    FrameLayout frmBg;
    @BindView(R.id.login_logo)
    ImageView loginLogo;
    @BindView(R.id.de_login_phone)
    ClearWriteEditText deLoginPhone;
    @BindView(R.id.de_login_password)
    ClearWriteEditText deLoginPassword;
    @BindView(R.id.de_login_sign)
    Button deLoginSign;
    @BindView(R.id.de_login_forgot)
    TextView deLoginForgot;
    @BindView(R.id.de_login_register)
    TextView deLoginRegister;
    private LoginActivity context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        context = this;
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Animation animation = AnimationUtils.loadAnimation(context, R.anim.translate_anim);
                deImgBackgroud.startAnimation(animation);
            }
        }, 200);
        String oldPhone = (String) SPUtils.get(context, SealConst.SEALTALK_LOGING_PHONE, "");
        String oldPassword = (String) SPUtils.get(context, SealConst.SEALTALK_LOGING_PASSWORD, "");

        if (!TextUtils.isEmpty(oldPhone) && !TextUtils.isEmpty(oldPassword)) {
            deLoginPhone.setText(oldPhone);
            deLoginPassword.setText(oldPassword);
        }

        //强制下线


    }

    @OnClick({R.id.de_login_sign, R.id.de_login_forgot, R.id.de_login_register})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.de_login_sign:
                String phoneString = deLoginPhone.getText().toString().trim();
                String passwordString = deLoginPassword.getText().toString().trim();
                if (TextUtils.isEmpty(phoneString)) {
                    NToast.shortToast(context, "手机号不能为空");
                    deLoginPhone.setShakeAnimation();
                    return;
                }

                if (!AMUtils.isMobile(phoneString)) {
                    NToast.shortToast(context, "非法手机号");
                    deLoginPhone.setShakeAnimation();
                    return;
                }

                if (TextUtils.isEmpty(passwordString)) {
                    NToast.shortToast(context, "密码不能为空");
                    deLoginPassword.setShakeAnimation();
                    return;
                }

                if (passwordString.contains(" ")) {
                    NToast.shortToast(context, "密码不能包含空格");
                    deLoginPassword.setShakeAnimation();
                    return;
                }


                // 通过一个特定的接口获取新的token，此处要用到同步的retrofit请求
                LoginRequest request = new LoginRequest("86", phoneString, passwordString);
                RetrofitAPIManager
                        .creatRetrofit(SealBaseUrl.USER)
                        .create(SealApi.class)
                        .login(request)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new HttpSubscriber<LoginResponse>(context) {
                            @Override
                            public void onNext(LoginResponse loginResponse) {
                                super.onNext(loginResponse);
//                                ToastUtils.showLong(mContext, "登录成功" + loginResponse.getCode());
                                String token = loginResponse.getResult().getToken();
                                SPUtils.put(context, SealConst.SEALTALK_LOGING_TOKEN, token);
                                startActivity(new Intent(context, MainActivity.class));
                            }

                            @Override
                            protected void doOnNext(LoginResponse loginResponse) {

                            }
                        });
                break;
            case R.id.de_login_forgot:
                break;
            case R.id.de_login_register:
                startActivityForResult(new Intent(this, RegisterActivity.class), 1);
                break;
        }
    }
}
