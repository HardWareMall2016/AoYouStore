package com.zhan.aoyoustore.ui.fragment.common;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.zhan.aoyoustore.R;
import com.zhan.aoyoustore.utils.Tools;
import com.zhan.framework.component.container.FragmentArgs;
import com.zhan.framework.component.container.FragmentContainerActivity;
import com.zhan.framework.support.inject.ViewInject;
import com.zhan.framework.ui.activity.BaseActivity;
import com.zhan.framework.ui.fragment.ABaseFragment;

import android.view.ViewGroup.LayoutParams;

import java.util.ArrayList;

import uk.co.senab.photoview.PhotoView;
import uk.co.senab.photoview.PhotoViewAttacher;

/**
 * 作者：伍岳 on 2016/7/12 13:55
 * 邮箱：wuyue8512@163.com
 * //
 * //         .............................................
 * //                  美女坐镇                  BUG辟易
 * //         .............................................
 * //
 * //                       .::::.
 * //                     .::::::::.
 * //                    :::::::::::
 * //                 ..:::::::::::'
 * //              '::::::::::::'
 * //                .::::::::::
 * //           '::::::::::::::..
 * //                ..::::::::::::.
 * //              ``::::::::::::::::
 * //               ::::``:::::::::'        .:::.
 * //              ::::'   ':::::'       .::::::::.
 * //            .::::'      ::::     .:::::::'::::.
 * //           .:::'       :::::  .:::::::::' ':::::.
 * //          .::'        :::::.:::::::::'      ':::::.
 * //         .::'         ::::::::::::::'         ``::::.
 * //     ...:::           ::::::::::::'              ``::.
 * //    ```` ':.          ':::::::::'                  ::::..
 * //                       '.:::::'                    ':'````..
 * //
 */
public class PhotosFragment extends ABaseFragment implements PhotoViewAttacher.OnPhotoTapListener {
    private final static String ARG_KEY_DATAS = "arg_key_pics";
    private final static String ARG_KEY_POS = "arg_key_pos";

    @ViewInject(id = R.id.view_pager)
    ViewPager mViewPager;

    //data
    private ArrayList<String> mPhotos;
    private int mPosition;

    public static void launch(Activity activity, ArrayList<String> photos, int showPos) {
        FragmentArgs args = new FragmentArgs();
        args.add(ARG_KEY_DATAS, photos);
        args.add(ARG_KEY_POS, showPos);
        FragmentContainerActivity.launch(activity, PhotosFragment.class, args);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPosition = savedInstanceState == null ? (int) getArguments().getSerializable(ARG_KEY_POS) : (int) savedInstanceState.getSerializable(ARG_KEY_POS);
        mPhotos = savedInstanceState == null ? (ArrayList<String>) getArguments().getSerializable(ARG_KEY_DATAS) : (ArrayList<String>) savedInstanceState.getSerializable(ARG_KEY_DATAS);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable(ARG_KEY_DATAS, mPhotos);
        outState.putSerializable(ARG_KEY_POS, mPosition);
    }

    @Override
    public void onPrepareSetContentView(BaseActivity activity) {
        activity.setOverlay(true);
        activity.showActionbar(false);
    }

    @Override
    protected int inflateContentView() {
        return R.layout.frag_photos;
    }

    @Override
    protected void layoutInit(LayoutInflater inflater, Bundle savedInstanceSate) {
        mViewPager.setAdapter(new SamplePagerAdapter());
        if (mPosition < mPhotos.size() && mPosition >= 0) {
            mViewPager.setCurrentItem(mPosition);
        }
    }

    @Override
    public void onPhotoTap(View view, float v, float v1) {

    }

    @Override
    public void onOutsidePhotoTap() {
        getActivity().finish();
    }

    class SamplePagerAdapter extends PagerAdapter {

        @Override
        public int getCount() {
            return mPhotos.size();
        }

        @Override
        public View instantiateItem(ViewGroup container, int position) {
            PhotoView photoView = new PhotoView(container.getContext());
            ImageLoader.getInstance().displayImage(mPhotos.get(position), photoView, Tools.buildDisplayGoodsImgOptions());
            photoView.setOnPhotoTapListener(PhotosFragment.this);
            // Now just add PhotoView to ViewPager and return it
            container.addView(photoView, LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);

            return photoView;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

    }
}
