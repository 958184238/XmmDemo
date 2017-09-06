package com.exam.admin.rongyundemo.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.exam.admin.rongyundemo.R;

import butterknife.ButterKnife;

public class RegisterActivity extends AppCompatActivity {

//    @BindView(R.id.ti_phone)
//    TextInputLayout tiPhone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);
//        tiPhone.setHint("手机号");
    }
}
