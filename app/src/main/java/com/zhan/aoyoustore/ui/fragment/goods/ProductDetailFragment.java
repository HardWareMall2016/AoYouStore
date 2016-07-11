package com.zhan.aoyoustore.ui.fragment.goods;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.zhan.aoyoustore.R;
import com.zhan.aoyoustore.beans.GetProductDetailResponseBean;
import com.zhan.aoyoustore.network.ApiUrls;
import com.zhan.aoyoustore.ui.activity.PictureViewerActivity;
import com.zhan.aoyoustore.utils.Tools;
import com.zhan.framework.component.container.FragmentArgs;
import com.zhan.framework.component.container.FragmentContainerActivity;
import com.zhan.framework.network.HttpRequestParams;
import com.zhan.framework.network.HttpRequestUtils;
import com.zhan.framework.support.inject.ViewInject;
import com.zhan.framework.ui.fragment.ABaseFragment;
import com.zhan.framework.utils.PixelUtils;

/**
 * 作者：伍岳 on 2016/7/11 20:29
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
public class ProductDetailFragment extends ABaseFragment {

    private final static String ARG_KEY="arg_key";

    @ViewInject(id = R.id.product_name)
    TextView mViewProductName;

    @ViewInject(id = R.id.short_description)
    TextView mViewProductShortDescp;

    @ViewInject(id = R.id.product_skus)
    LinearLayout mViewProductSkusContent;

    @ViewInject(id = R.id.product_pic_content)
    LinearLayout mViewProductPics;
    //Data
    private int mProductId;

    //Tools
    private LayoutInflater mInflater;

    public static void launch(Activity activity,int productId){
        FragmentArgs args = new FragmentArgs();
        args.add(ARG_KEY, productId);
        FragmentContainerActivity.launch(activity, ProductDetailFragment.class, args);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mProductId = savedInstanceState == null ? (int) getArguments().getSerializable(ARG_KEY) : (int) savedInstanceState.getSerializable(ARG_KEY);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable(ARG_KEY, mProductId);
    }

    @Override
    protected void layoutInit(LayoutInflater inflater, Bundle savedInstanceSate) {
        super.layoutInit(inflater, savedInstanceSate);
        mInflater=inflater;
        getActivity().setTitle("商品详情");
    }

    @Override
    protected int inflateContentView() {
        return R.layout.frag_product_detail;
    }

    @Override
    public void requestData() {
        HttpRequestParams requestParams=new HttpRequestParams();
        requestParams.put("productId",mProductId);
        startFormRequest(ApiUrls.GET_PRODUCT_DETAIL, requestParams, new BaseHttpRequestTask<GetProductDetailResponseBean>() {
            @Override
            public GetProductDetailResponseBean parseResponseToResult(String content) {
                return Tools.parseJson(content, GetProductDetailResponseBean.class);
            }

            @Override
            public String verifyResponseResult(String result) {
                return Tools.verifyResponseResult(result);
            }

            @Override
            protected void onSuccess(GetProductDetailResponseBean result) {
                super.onSuccess(result);
                populateView(result);
            }
        }, HttpRequestUtils.RequestType.POST);
    }

    private void populateView(GetProductDetailResponseBean result){
        if(result==null||result.getResult()==null){
            return;
        }
        populateProductInfo(result);
        populateProductPics(result);
        populateProductSku(result);
    }

    private void populateProductInfo(GetProductDetailResponseBean result){
        Tools.setTextView(mViewProductName,result.getResult().getProductName());
        Tools.setTextView(mViewProductShortDescp,result.getResult().getProductName());
    }

    private void populateProductPics(GetProductDetailResponseBean result){
        if(result.getResult().getPics()==null){
            return;
        }
        mViewProductPics.removeAllViews();
        for(String picPath:result.getResult().getPics()){
            ImageView pic=new ImageView(getActivity());
            pic.setScaleType(ImageView.ScaleType.CENTER);
            pic.setLayoutParams(new LinearLayout.LayoutParams(PixelUtils.dp2px(150), PixelUtils.dp2px(150)));
            ImageLoader.getInstance().displayImage(picPath, pic, Tools.buildDisplayGoodsImgOptions());
            pic.setTag(picPath);
            pic.setOnClickListener(mOnImgClickListener);
            mViewProductPics.addView(pic);

        }
    }

    private void populateProductSku(GetProductDetailResponseBean result){
        if(result.getResult().getInfo()==null){
            return;
        }
        mViewProductSkusContent.removeAllViews();
        for(GetProductDetailResponseBean.ResultBean.InfoBean skusBean:result.getResult().getInfo()){
            View itemProductSku=mInflater.inflate(R.layout.item_product_sku,null);
            mViewProductSkusContent.addView(itemProductSku);
            TextView infoName=(TextView)itemProductSku.findViewById(R.id.info_name);
            TextView infoValue=(TextView)itemProductSku.findViewById(R.id.info_value);

            Tools.setTextView(infoName,skusBean.getInfoName());
            Tools.setTextView(infoValue,skusBean.getInfoValue());
        }
    }

    private View.OnClickListener mOnImgClickListener=new View.OnClickListener(){
        @Override
        public void onClick(View v) {
            String picPath= (String)v.getTag();

            Intent intent = new Intent(getActivity(), PictureViewerActivity.class);
            intent.putExtra("image", picPath);//非必须

            int[] location = new int[2];
            v.getLocationOnScreen(location);
            intent.putExtra("locationX", location[0]);//必须
            intent.putExtra("locationY", location[1]);//必须

            intent.putExtra("width", v.getWidth());//必须
            intent.putExtra("height", v.getHeight());//必须
            startActivity(intent);
            getActivity().overridePendingTransition(0, 0);
        }
    };
}
