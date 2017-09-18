//package com.exam.admin.rongyundemo.fragment;
//
//import android.graphics.Rect;
//import android.os.Bundle;
//import android.support.annotation.NonNull;
//import android.support.annotation.Nullable;
//import android.support.v4.app.Fragment;
//import android.support.v4.app.FragmentActivity;
//import android.support.v4.view.ViewCompat;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.ImageView;
//
//import com.exam.admin.rongyundemo.R;
//import com.exam.admin.rongyundemo.contanst.SealConst;
//import com.exam.admin.rongyundemo.utils.glide.GlideUtils;
//import com.sunfusheng.glideimageview.progress.CircleProgressView;
//
//import butterknife.BindView;
//import butterknife.ButterKnife;
//import butterknife.Unbinder;
//import uk.co.senab.photoview.PhotoView;
//
///**
// * ========================
// * Name: DetailsFragment
// * Des:
// * User: 吴飞
// * Date: 2017/8/18 11:38
// * =========================
// */
//
//public class DetailsFragment extends Fragment {
//
//    private static final String ARG_ALBUM_IMAGE_POSITION = "arg_album_image_position";
//    private static final String ARG_STARTING_ALBUM_IMAGE_POSITION = "arg_starting_album_image_position";
//    @BindView(R.id.progressView)
//    CircleProgressView progressView;
//    //    @BindView(R.id.imageview)
////    ImageView imageview;
//    @BindView(R.id.photoview)
//    PhotoView photoview;
//    Unbinder unbinder;
//    private ImageView mAlbumImage;
//    private int mStartingPosition;
//    private int mAlbumPosition;
//    private boolean mIsTransitioning;
//    private long mBackgroundImageFadeMillis;
//    //    private ArrayList<WelfareResponse.ResultsBean> list;
//    private FragmentActivity context;
//    private String url;
//
//    public static DetailsFragment newInstance(int position, int startingPosition, String url) {
//        Bundle args = new Bundle();
//        args.putInt(ARG_ALBUM_IMAGE_POSITION, position);
//        args.putInt(ARG_STARTING_ALBUM_IMAGE_POSITION, startingPosition);
//        args.putString("url", url);
//        DetailsFragment fragment = new DetailsFragment();
//        fragment.setArguments(args);
//        return fragment;
//    }
//
//    @Nullable
//    @Override
//    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        View view = inflater.inflate(R.layout.fragment_details, container, false);
//        unbinder = ButterKnife.bind(this, view);
//        context = getActivity();
////        ViewCompat.setTransitionName(photoview, url);
//        ViewCompat.setTransitionName(photoview, SealConst.SHARED_IMAGE_ELEMENT_NAME);
//        String thumbnailUrl = url + "?imageView2/2/w/300/h/220/q/100";
//        GlideUtils.loadImage(context, url, thumbnailUrl, photoview, progressView);
//        return view;
//    }
//
//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        Bundle bundle = getArguments();
//        mStartingPosition = bundle.getInt(ARG_STARTING_ALBUM_IMAGE_POSITION);
//        mAlbumPosition = bundle.getInt(ARG_ALBUM_IMAGE_POSITION);
//        url = bundle.getString("url");
//        mIsTransitioning = savedInstanceState == null && mStartingPosition == mAlbumPosition;
//        mBackgroundImageFadeMillis = 1000;
//    }
//
//    @Override
//    public void onDestroyView() {
//        super.onDestroyView();
//        unbinder.unbind();
//    }
//
//    @Nullable
//    public ImageView getAlbumImage() {
//        if (isViewInBounds(getActivity().getWindow().getDecorView(), mAlbumImage)) {
//            return mAlbumImage;
//        }
//        return null;
//    }
//
//    /**
//     * Returns true if {@param view} is contained within {@param container}'s bounds.
//     */
//    private static boolean isViewInBounds(@NonNull View container, @NonNull View view) {
//        Rect containerBounds = new Rect();
//        container.getHitRect(containerBounds);
//        return view.getLocalVisibleRect(containerBounds);
//    }
//}
