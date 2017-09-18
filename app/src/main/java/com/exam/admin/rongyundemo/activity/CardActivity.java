//package com.exam.admin.rongyundemo.activity;
//
//import android.content.Intent;
//import android.os.Bundle;
//import android.support.v4.app.ActivityCompat;
//import android.support.v4.view.ViewCompat;
//import android.support.v7.app.AppCompatActivity;
//import android.transition.Explode;
//import android.view.View;
//import android.view.Window;
//import android.widget.ImageView;
//import android.widget.TextView;
//
//import com.exam.admin.rongyundemo.R;
//import com.sunfusheng.glideimageview.progress.GlideApp;
//
//public class CardActivity extends AppCompatActivity {
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);
//        getWindow().setExitTransition(new Explode());
//        setContentView(R.layout.activity_card);
//        Intent intent = getIntent();
//        String url = intent.getStringExtra("url");
//        String desc = intent.getStringExtra("desc");
//        ImageView imageView = (ImageView) findViewById(R.id.iv_detail);
//        ViewCompat.setTransitionName(imageView, url);
//        TextView textView = (TextView) findViewById(R.id.tv_detail_desc);
//        findViewById(R.id.rl_detail_root).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                ActivityCompat.finishAfterTransition(CardActivity.this);
//            }
//        });
//        GlideApp.with(this).load(url).into(imageView);
//        textView.setText(desc);
//    }
//}
