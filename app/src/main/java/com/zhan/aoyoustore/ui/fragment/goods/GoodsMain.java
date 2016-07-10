package com.zhan.aoyoustore.ui.fragment.goods;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshGridView;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.zhan.aoyoustore.R;
import com.zhan.aoyoustore.beans.GetShopCategoriesResponseBean;
import com.zhan.aoyoustore.network.ApiUrls;
import com.zhan.aoyoustore.utils.Tools;
import com.zhan.framework.network.HttpRequestUtils;
import com.zhan.framework.support.adapter.ABaseAdapter;
import com.zhan.framework.support.inject.ViewInject;
import com.zhan.framework.ui.fragment.ABaseFragment;

import java.util.ArrayList;

/**
 * 作者：伍岳 on 2016/7/9 21:46
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
public class GoodsMain extends ABaseFragment {

    @ViewInject(id = R.id.gridView)
    PullToRefreshGridView mPullToRefreshGridView;

    //Tools
    private GoodsAdapter mGridViewAdapter;

    //Data
    private ArrayList<Goods> mGoodsList = new ArrayList<>();

    @Override
    protected int inflateContentView() {
        return R.layout.frag_goods_main;
    }

    @Override
    protected void layoutInit(LayoutInflater inflater, Bundle savedInstanceSate) {
        super.layoutInit(inflater, savedInstanceSate);
        mPullToRefreshGridView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<GridView>() {

            @Override
            public void onPullDownToRefresh(PullToRefreshBase<GridView> refreshView) {
                requestData();
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<GridView> refreshView) {

            }
        });

        GridView mGridView = mPullToRefreshGridView.getRefreshableView();
        mGridViewAdapter = new GoodsAdapter(mGoodsList, getActivity());
        mGridView.setAdapter(mGridViewAdapter);
    }

    @Override
    public boolean isContentEmpty() {
        return mGoodsList.size()==0;
    }

    @Override
    public void requestData() {
        if(isRequestProcessing(ApiUrls.GET_SHOP_CATEGORIES)){
            return;
        }
        startFormRequest(ApiUrls.GET_SHOP_CATEGORIES, null, new BaseHttpRequestTask<GetShopCategoriesResponseBean>() {
            @Override
            public GetShopCategoriesResponseBean parseResponseToResult(String content) {
                return Tools.parseJson(content,GetShopCategoriesResponseBean.class);
            }
            @Override
            public String verifyResponseResult(GetShopCategoriesResponseBean result) {
                return Tools.verifyResponseResult(result);
            }

            @Override
            protected void onSuccess(GetShopCategoriesResponseBean result) {
                super.onSuccess(result);
                //这里加正确处理的逻辑就好了
                mGoodsList.clear();
                for(GetShopCategoriesResponseBean.ResultBean.CategoryBean categoryBean:result.getResult().getCategory()){
                    Goods goods=new Goods();
                    goods.cid=categoryBean.getCid();
                    goods.icon=categoryBean.getIcon();
                    goods.name=categoryBean.getName();
                    mGoodsList.add(goods);
                }

                mGridViewAdapter.notifyDataSetChanged();
            }

            @Override
            public void onRequestFinished(ResultCode resultCode, String result) {
                super.onRequestFinished(resultCode, result);
                mPullToRefreshGridView.onRefreshComplete();
            }

        }, HttpRequestUtils.RequestType.POST);
    }

    private class GoodsAdapter extends ABaseAdapter<Goods> {

        public GoodsAdapter(ArrayList<Goods> datas, Activity context) {
            super(datas, context);
        }

        @Override
        protected AbstractItemView<Goods> newItemView() {
            return new GoodsItemView();
        }
    }

    private class GoodsItemView extends ABaseAdapter.AbstractItemView<Goods> {
        @ViewInject(id = R.id.goods_icon)
        private ImageView mViewGoodsType;

        @ViewInject(id = R.id.summary)
        private TextView mViewSummary;

        @Override
        public int inflateViewId() {
            return R.layout.list_item_goods_main;
        }

        @Override
        public void bindingData(View convertView, Goods data) {
            ImageLoader.getInstance().displayImage(data.icon, mViewGoodsType, Tools.buildDisplayGoodsImgOptions());
            Tools.setTextView(mViewSummary,data.name);
        }
    }

    public class Goods{
        int cid;
        String name;
        String icon;
    }
}
