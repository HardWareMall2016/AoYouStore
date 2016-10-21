package com.zhan.aoyoustore.ui.fragment.goods;

import android.animation.LayoutTransition;
import android.app.Activity;
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
import com.zhan.aoyoustore.ui.fragment.common.PhotosFragment;
import com.zhan.aoyoustore.utils.Tools;
import com.zhan.framework.component.container.FragmentArgs;
import com.zhan.framework.component.container.FragmentContainerActivity;
import com.zhan.framework.network.HttpRequestParams;
import com.zhan.framework.network.HttpRequestUtils;
import com.zhan.framework.support.inject.ViewInject;
import com.zhan.framework.ui.fragment.ABaseFragment;
import com.zhan.framework.utils.PixelUtils;

import java.util.ArrayList;

/**
 * /**
 * 作者：蒲柯柯 on 2016/7/11 20:29
 * 邮箱：983198505@qq.com
 * 介绍:商品列表
 */

public class ProductDetailFragment extends ABaseFragment {

    private final static String ARG_KEY = "arg_key";

    @ViewInject(id = R.id.content)
    LinearLayout mViewContent;

    @ViewInject(id = R.id.product_name)
    TextView mViewProductName;

    @ViewInject(id = R.id.costPrice)
    TextView mViewCostPrice;

    @ViewInject(id = R.id.vistiCounts)
    TextView mViewVistiCounts;

    @ViewInject(id = R.id.weight)
    TextView mViewWeight;

    @ViewInject(id = R.id.marketPrice)
    TextView mViewMarketPrice;

    @ViewInject(id = R.id.isfreeShipping)
    TextView mViewIsfreeShipping;

    @ViewInject(id = R.id.saleCounts)
    TextView mViewSaleCounts;

    @ViewInject(id = R.id.stock)
    TextView mViewStock;

    @ViewInject(id = R.id.minSalePrice)
    TextView mViewMinSalePrice;

    @ViewInject(id = R.id.maxSalePrice)
    TextView mViewMaxSalePrice;

    @ViewInject(id = R.id.short_description)
    TextView mViewProductShortDescp;

    @ViewInject(id = R.id.product_skus)
    LinearLayout mViewProductSkusContent;

    @ViewInject(id = R.id.product_skus_title, click = "OnClick")
    TextView mViewProductSkusTitle;

    @ViewInject(id = R.id.product_pic_content)
    LinearLayout mViewProductPics;

    @ViewInject(id = R.id.tv_addCart,click = "OnClick")
    TextView mTvAddCart;

    //Data
    private int mProductId;
    private ArrayList<String> mPruductPhotos = new ArrayList<>();
    private boolean mShowProductSkusContent = true;

    //Tools
    private LayoutInflater mInflater;
    private LayoutTransition mLayoutTransition;

    public static void launch(Activity activity, int productId) {
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
        mInflater = inflater;
        getActivity().setTitle("商品详情");
        mLayoutTransition = new LayoutTransition();
        mViewContent.setLayoutTransition(mLayoutTransition);
        mLayoutTransition.setStagger(LayoutTransition.CHANGE_APPEARING, 30);
        mLayoutTransition.setStagger(LayoutTransition.CHANGE_DISAPPEARING, 30);
        //设置每个动画持续的时间
        mLayoutTransition.setDuration(300);
    }

    @Override
    protected int inflateContentView() {
        return R.layout.frag_product_detail;
    }

    @Override
    public void requestData() {
        HttpRequestParams requestParams = new HttpRequestParams();
        requestParams.put("productId", mProductId);
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

    private void populateView(GetProductDetailResponseBean result) {
        if (result == null || result.getResult() == null) {
            return;
        }
        populateProductInfo(result);
        populateProductPics(result);
        populateProductSku(result);
    }

    private void populateProductInfo(GetProductDetailResponseBean result) {
        Tools.setTextView(mViewProductName, result.getResult().getProductName());
        Tools.setTextView(mViewProductShortDescp, result.getResult().getProductName());

        Tools.setTextView(mViewVistiCounts, String.format("浏览次数%d", result.getResult().getVistiCounts()));
        Tools.setTextView(mViewCostPrice, String.format("成本价￥%.2f", result.getResult().getCostPrice()));
        Tools.setTextView(mViewWeight, String.format("重量 %.2fKg", result.getResult().getWeight()));
        Tools.setTextView(mViewMarketPrice, String.format("市场价￥%.2f", result.getResult().getWeight()));
        if (result.getResult().isIsfreeShipping()) {
            mViewIsfreeShipping.setText("包邮");
        } else {
            mViewIsfreeShipping.setText("不包邮");
        }
        Tools.setTextView(mViewSaleCounts, String.format("销量%d", result.getResult().getSaleCounts()));
        Tools.setTextView(mViewStock, String.format("库存%d", result.getResult().getStock()));

        Tools.setTextView(mViewMinSalePrice, String.format("最低价￥%.2f", result.getResult().getMinSalePrice()));
        Tools.setTextView(mViewMaxSalePrice, String.format("最高价￥%.2f", result.getResult().getMaxSalePrice()));
    }

    private void populateProductPics(GetProductDetailResponseBean result) {
        if (result.getResult().getPics() == null) {
            return;
        }
        mViewProductPics.removeAllViews();
        mPruductPhotos.clear();
        int position = 0;
        for (String picPath : result.getResult().getPics()) {
            ImageView pic = new ImageView(getActivity());
            pic.setScaleType(ImageView.ScaleType.CENTER);
            pic.setLayoutParams(new LinearLayout.LayoutParams(PixelUtils.dp2px(150), PixelUtils.dp2px(150)));
            ImageLoader.getInstance().displayImage(picPath, pic, Tools.buildDisplayGoodsImgOptions());
            pic.setTag(position);
            pic.setOnClickListener(mOnImgClickListener);
            mViewProductPics.addView(pic);

            mPruductPhotos.add(picPath);
            position++;
        }
    }

    private void populateProductSku(GetProductDetailResponseBean result) {
        if (result.getResult().getInfo() == null) {
            return;
        }
        mViewProductSkusContent.removeAllViews();
        for (GetProductDetailResponseBean.ResultBean.InfoBean skusBean : result.getResult().getInfo()) {
            View itemProductSku = mInflater.inflate(R.layout.item_product_sku, null);
            mViewProductSkusContent.addView(itemProductSku);
            TextView infoName = (TextView) itemProductSku.findViewById(R.id.info_name);
            TextView infoValue = (TextView) itemProductSku.findViewById(R.id.info_value);

            Tools.setTextView(infoName, skusBean.getInfoName());
            Tools.setTextView(infoValue, skusBean.getInfoValue());
        }
    }

    private View.OnClickListener mOnImgClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            int position = (int) v.getTag();
            PhotosFragment.launch(getActivity(), mPruductPhotos, position);
        }
    };

    void OnClick(View v) {
        switch (v.getId()) {
            case R.id.product_skus_title:
                if (mShowProductSkusContent) {
                    mShowProductSkusContent = false;
                    mViewProductSkusContent.setVisibility(View.GONE);
                    mViewProductSkusTitle.setCompoundDrawablesWithIntrinsicBounds(null, null, getResources().getDrawable(R.drawable.arrow_up_small), null);
                } else {
                    mShowProductSkusContent = true;
                    mViewProductSkusContent.setVisibility(View.VISIBLE);
                    mViewProductSkusTitle.setCompoundDrawablesWithIntrinsicBounds(null, null, getResources().getDrawable(R.drawable.arrow_down_small), null);
                }
                break;
            case R.id.tv_addCart:

                break;
        }
    }
}
