//package com.exam.admin.rongyundemo.activity;
//
//import android.content.Intent;
//import android.graphics.Bitmap;
//import android.os.Bundle;
//import android.support.v4.app.FragmentActivity;
//import android.support.v4.app.SharedElementCallback;
//import android.support.v4.view.ViewCompat;
//import android.support.v4.view.ViewPager;
//import android.transition.Explode;
//import android.view.View;
//import android.view.Window;
//import android.widget.RelativeLayout;
//import android.widget.TextView;
//
//import com.exam.admin.rongyundemo.adapter.QuickPageAdapter;
//import com.exam.admin.rongyundemo.contanst.SealConst;
//import com.exam.admin.rongyundemo.fragment.DetailsFragment;
//import com.exam.admin.rongyundemo.service.response.WelfareResponse;
//import com.exam.admin.rongyundemo.utils.glide.CircleProgressView;
//import com.exam.admin.rongyundemo.utils.glide.GlideUtils;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Map;
//
//import butterknife.BindView;
//import butterknife.ButterKnife;
//import uk.co.senab.photoview.PhotoView;
//
//import static com.exam.admin.rongyundemo.fragment.WelfareFragment.EXTRA_CURRENT_ALBUM_POSITION;
//import static com.exam.admin.rongyundemo.fragment.WelfareFragment.EXTRA_STARTING_ALBUM_POSITION;
//
//
///***
// * 类名 DetailsActivity
// * 类描述 查看图片
// * 作者 吴飞--1.0
// * 日期 2017-3-16
// */
//public class DetailsActivity extends FragmentActivity {
//
//    @BindView(R.id.viewpager)
//    ViewPager viewpager;
//    @BindView(R.id.tv_index)
//    TextView tvIndex;
//    //    @BindView(R.id.tv_index)
////    TextView tvIndex;
////    @BindView(R.id.tv_save)
////    TextView tvSave;
////    @BindView(R.id.MainView)
////    RelativeLayout MainView;
////    @BindView(R.id.progressView)
////    ProgressBar progressbar;
////    @BindView(R.id.rl_bottom)
////    RelativeLayout rlBottom;
////    @BindView(R.id.imageview)
////    ImageView imageview;
////    @BindView(R.id.ll_top)
////    LinearLayout llTop;
//    private DetailsActivity context;
//    private int index;
//    private QuickPageAdapter<View> adapter;
//    private List<View> listViews = new ArrayList<>();
//    private ArrayList<WelfareResponse.ResultsBean> imgList;
//    private int rotate;
//    private Bitmap newBitmap;
//    private String newPath;
//    private String oldPath;
//    private int attachmentId;
//    private int mStartingPosition;
//    private int mCurrentPosition;
//    private static final String STATE_CURRENT_PAGE_POSITION = "state_current_page_position";
//
//    private final SharedElementCallback mCallback = new SharedElementCallback() {
//        @Override
//        public void onMapSharedElements(List<String> names, Map<String, View> sharedElements) {
////            if (mIsReturning) {
////                ImageView sharedElement = mCurrentDetailsFragment.getAlbumImage();
////                if (sharedElement == null) {
////                    // If shared element is null, then it has been scrolled off screen and
////                    // no longer visible. In this case we cancel the shared element transition by
////                    // removing the shared element from the shared elements map.
////                    names.clear();
////                    sharedElements.clear();
////                } else if (mStartingPosition != mCurrentPosition) {
////                    // If the user has swiped to a different ViewPager page, then we need to
////                    // remove the old shared element and replace it with the new shared element
////                    // that should be transitioned instead.
////                    names.clear();
////                    names.add(sharedElement.getTransitionName());
////                    sharedElements.clear();
////                    sharedElements.put(sharedElement.getTransitionName(), sharedElement);
////                }
////            }
//        }
//    };
//    private boolean mIsReturning;
//    private DetailsFragment mCurrentDetailsFragment;
//
//    //    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);
//        getWindow().setExitTransition(new Explode());
//        setContentView(R.layout.activity_details);
//
////        postponeEnterTransition();
////        setEnterSharedElementCallback(mCallback);
//        ButterKnife.bind(this);
//        context = this;
//        Intent intent = getIntent();
//        mStartingPosition = intent.getIntExtra("index", 0);
//        if (savedInstanceState == null) {
//            mCurrentPosition = mStartingPosition;
//        } else {
//            mCurrentPosition = savedInstanceState.getInt(STATE_CURRENT_PAGE_POSITION);
//        }
//        imgList = (ArrayList<WelfareResponse.ResultsBean>) getIntent().getSerializableExtra("allList");
//        viewpager.addOnPageChangeListener(pageChangeListener);
//        for (int i = 0; i < imgList.size(); i++) {
//            WelfareResponse.ResultsBean resultsBean = imgList.get(i);
//            String url = resultsBean.getUrl();
//            View view = View.inflate(context, R.layout.view_pictrue, null);
//            CircleProgressView progressView = (CircleProgressView) view.findViewById(R.id.progressView);
//            PhotoView photoview = (PhotoView) view.findViewById(R.id.photoview);
//            RelativeLayout relativelayout = (RelativeLayout) view.findViewById(R.id.relativelayout);
//            ViewCompat.setTransitionName(photoview, url);
//            String thumbnailUrl = url + "?imageView2/2/w/300/h/220/q/100";
//            GlideUtils.loadImage(context, url, thumbnailUrl, photoview, progressView);
//            relativelayout.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    finishAfterTransition();
//                }
//            });
//            listViews.add(view);
//        }
//        adapter = new QuickPageAdapter<>(listViews);
//        viewpager.setAdapter(adapter);
//        viewpager.setCurrentItem(mStartingPosition);
//        tvIndex.setText(String.format(getString(R.string.index), mStartingPosition + 1, listViews.size()));
//    }
//
//    @Override
//    protected void onSaveInstanceState(Bundle outState) {
//        super.onSaveInstanceState(outState);
//        outState.putInt(STATE_CURRENT_PAGE_POSITION, mCurrentPosition);
//    }
//
//
//    //过渡动画结束后
//    @Override
//    public void finishAfterTransition() {
//        super.finishAfterTransition();
//        mIsReturning = true;
//        Intent data = new Intent(SealConst.ACTION_NAME);
//        data.putExtra(EXTRA_STARTING_ALBUM_POSITION, mStartingPosition);
//        data.putExtra(EXTRA_CURRENT_ALBUM_POSITION, mCurrentPosition);
//        setResult(RESULT_OK, data);
////        sendBroadcast(data);
//    }
//
//
//    private ViewPager.OnPageChangeListener pageChangeListener = new ViewPager.OnPageChangeListener() {
//
//        public void onPageSelected(int arg0) {
//            mCurrentPosition = arg0;
//            tvIndex.setText(String.format(getString(R.string.index), arg0 + 1, listViews.size()));
//        }
//
//        public void onPageScrolled(int arg0, float arg1, int arg2) {
//
//        }
//
//        public void onPageScrollStateChanged(int arg0) {
//
//        }
//    };
//
////    private class DetailsFragmentPagerAdapter extends FragmentStatePagerAdapter {
////        public DetailsFragmentPagerAdapter(FragmentManager fragmentManager) {
////            super(fragmentManager);
////        }
////
////        @Override
////        public Fragment getItem(int position) {
////            String url = imgList.get(position).getUrl();
////            return DetailsFragment.newInstance(position, mStartingPosition, url);
////        }
////
////        @Override
////        public void setPrimaryItem(ViewGroup container, int position, Object object) {
////            super.setPrimaryItem(container, position, object);
////            mCurrentDetailsFragment = (DetailsFragment) object;
////        }
////
////        @Override
////        public int getCount() {
////            return imgList.size();
////        }
////    }
//
//
//}
