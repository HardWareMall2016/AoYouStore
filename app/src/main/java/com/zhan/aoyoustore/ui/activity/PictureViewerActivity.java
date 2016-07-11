package com.zhan.aoyoustore.ui.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.zhan.aoyoustore.ui.widget.SmoothImageView;
import com.zhan.aoyoustore.utils.Tools;

/**
 * 作者：伍岳 on 2016/7/11 22:35
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
public class PictureViewerActivity extends Activity implements View.OnClickListener,SmoothImageView.TransformListener {

    private SmoothImageView mSmoothImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String picUrl = getIntent().getStringExtra("image");

        int mLocationX = getIntent().getIntExtra("locationX", 0);
        int mLocationY = getIntent().getIntExtra("locationY", 0);
        int mWidth = getIntent().getIntExtra("width", 0);
        int mHeight = getIntent().getIntExtra("height", 0);

        mSmoothImageView = new SmoothImageView(this);
        mSmoothImageView.setOriginalInfo(mWidth, mHeight, mLocationX, mLocationY);
        mSmoothImageView.transformIn();
        mSmoothImageView.setLayoutParams(new ViewGroup.LayoutParams(-1, -1));
        mSmoothImageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
        setContentView(mSmoothImageView);

        ImageLoader.getInstance().displayImage(picUrl, mSmoothImageView, Tools.buildDisplayGoodsImgOptions());

        mSmoothImageView.setOnClickListener(this);
        mSmoothImageView.setOnTransformListener(this);
    }

    @Override
    public void onClick(View v) {
        mSmoothImageView.transformOut();
    }

    @Override
    public void onTransformComplete(int mode) {
        if(mode==SmoothImageView.STATE_TRANSFORM_OUT){
            finish();
            overridePendingTransition(0, 0);
        }
    }
}
