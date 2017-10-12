package com.exam.admin.rongyundemo.activity.demo;

import android.content.ContentResolver;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;
import android.widget.TextView;

import com.exam.admin.rongyundemo.R;
import com.exam.admin.rongyundemo.activity.BaseActivity;
import com.exam.admin.rongyundemo.adapter.GridSpacingItemDecoration;
import com.exam.admin.rongyundemo.adapter.PostArticleImgAdapter;
import com.exam.admin.rongyundemo.interfa.GifSizeFilter;
import com.exam.admin.rongyundemo.interfa.MyCallBack;
import com.exam.admin.rongyundemo.interfa.OnRecyclerItemClickListener;
import com.zhihu.matisse.Matisse;
import com.zhihu.matisse.MimeType;
import com.zhihu.matisse.engine.impl.GlideEngine;
import com.zhihu.matisse.filter.Filter;
import com.zhihu.matisse.internal.entity.CaptureStrategy;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MultiImageActivity extends BaseActivity {

    private static final int REQUEST_CODE_CHOOSE = 10086;
    public static final int MAX_IMAGE_SIZE = 9;
    @BindView(R.id.recyclerview)
    RecyclerView recyclerview;
    @BindView(R.id.tv)
    TextView tv;
    private MultiImageActivity context;
    private List<Uri> uris;
    //    private MultiImageAdapter adapter;
    private ItemTouchHelper itemTouchHelper;
    private PostArticleImgAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multi_image);
        ButterKnife.bind(this);
        context = this;
        recyclerview.setLayoutManager(new StaggeredGridLayoutManager(5, StaggeredGridLayoutManager.VERTICAL));
        recyclerview.addItemDecoration(new GridSpacingItemDecoration(5, 10, true));
        uris = new ArrayList<>();
        Uri uri = Uri.parse(ContentResolver.SCHEME_ANDROID_RESOURCE + "://" + getResources().getResourcePackageName(R.drawable.add) + "/" + getResources().getResourceTypeName(R.drawable.add) + "/" + getResources().getResourceEntryName(R.drawable.add));
        uris.add(uri);
        adapter = new PostArticleImgAdapter(context, uris);
        recyclerview.setAdapter(adapter);
//        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
//            @Override
//            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
//                if (position == uris.size() - 1 && position < MAX_IMAGE_SIZE - 1) {
//                    Matisse.from(context)
//                            .choose(MimeType.ofAll())
//                            .countable(true)
//                            .maxSelectable(MAX_IMAGE_SIZE + 1 - uris.size())
//                            .capture(true)
//                            .captureStrategy(new CaptureStrategy(true, "com.exam.admin.rongyundemo.FileProvider"))
//                            .addFilter(new GifSizeFilter(320, 320, 5 * Filter.K * Filter.K))
//                            .gridExpectedSize(getResources().getDimensionPixelSize(R.dimen.grid_expected_size))
//                            .restrictOrientation(ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED)
//                            .thumbnailScale(0.85f)
//                            .imageEngine(new GlideEngine())
//                            .forResult(REQUEST_CODE_CHOOSE);
//                }
//            }
//        });
        MyCallBack myCallBack = new MyCallBack(context, adapter, uris);
        itemTouchHelper = new ItemTouchHelper(myCallBack);
        myCallBack.setDragListener(new MyCallBack.DragListener() {
            @Override
            public void deleteState(boolean delete) {
                if (delete) {
                    tv.setBackgroundResource(R.color.holo_red_dark);
                    tv.setText(getString(R.string.post_delete_tv_d));
                } else {
                    tv.setBackgroundResource(R.color.holo_red_light);
                    tv.setText(getString(R.string.post_delete_tv_s));
                }
            }

            @Override
            public void dragState(boolean start) {
                if (start) {
                    tv.setVisibility(View.VISIBLE);
                } else {
                    tv.setVisibility(View.GONE);
                }
            }
        });
        //关联对应的recyclerview
        itemTouchHelper.attachToRecyclerView(recyclerview);
        //事件监听
        recyclerview.addOnItemTouchListener(new OnRecyclerItemClickListener(recyclerview) {
            @Override
            public void onItemClick(RecyclerView.ViewHolder holder) {
                Matisse.from(context)
                        .choose(MimeType.ofAll())
                        .countable(true)
                        .maxSelectable(MAX_IMAGE_SIZE + 1 - uris.size())
                        .capture(true)
                        .captureStrategy(new CaptureStrategy(true, "com.exam.admin.rongyundemo.FileProvider"))
                        .addFilter(new GifSizeFilter(320, 320, 5 * Filter.K * Filter.K))
                        .gridExpectedSize(getResources().getDimensionPixelSize(R.dimen.grid_expected_size))
                        .restrictOrientation(ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED)
                        .thumbnailScale(0.85f)
                        .imageEngine(new GlideEngine())
                        .forResult(REQUEST_CODE_CHOOSE);
            }

            @Override
            public void onItemLongClick(RecyclerView.ViewHolder holder) {
                //如果item不是最后一个，则执行拖拽
                if (holder.getLayoutPosition() != uris.size() - 1) {
                    itemTouchHelper.startDrag(holder);
                }
            }
        });

        setBack();
        setTvTitle("仿微信上传图片Demo");
        cancelLoading();
    }

    @Override
    protected void onErrorRefresh() {

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == REQUEST_CODE_CHOOSE) {
                List<Uri> obtainResult = Matisse.obtainResult(data);
                uris.addAll(0, obtainResult);
//                adapter.addData(obtainResult);
                adapter.notifyDataSetChanged();
            }
        }
    }

    private ItemTouchHelper.Callback callback = new ItemTouchHelper.Callback() {
        @Override
        public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
            return 0;
        }

        @Override
        public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
            return false;
        }

        @Override
        public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {

        }
    };
}
