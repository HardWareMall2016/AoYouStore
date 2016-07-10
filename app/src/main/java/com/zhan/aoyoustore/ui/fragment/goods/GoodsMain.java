package com.zhan.aoyoustore.ui.fragment.goods;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
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
public class GoodsMain extends ABaseFragment implements AdapterView.OnItemClickListener {

    @ViewInject(id = R.id.gridView)
    PullToRefreshGridView mPullToRefreshGridView;

    //Tools
    private GoodsAdapter mGridViewAdapter;

    //Data
    private ArrayList<ShopCategory> mShopCategoryList = new ArrayList<>();

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
        mGridViewAdapter = new GoodsAdapter(mShopCategoryList, getActivity());
        mGridView.setAdapter(mGridViewAdapter);
        mGridView.setOnItemClickListener(this);
    }

    @Override
    public boolean isContentEmpty() {
        return mShopCategoryList.size()==0;
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
            public String verifyResponseResult(String result) {
                return Tools.verifyResponseResult(result);
            }

            @Override
            protected void onSuccess(GetShopCategoriesResponseBean result) {
                super.onSuccess(result);
                //这里加正确处理的逻辑就好了
                mShopCategoryList.clear();
                for(GetShopCategoriesResponseBean.ResultBean.CategoryBean categoryBean:result.getResult().getCategory()){
                    ShopCategory shopCategory=new ShopCategory();
                    shopCategory.cid=categoryBean.getCid();
                    shopCategory.icon=categoryBean.getIcon();
                    shopCategory.name=categoryBean.getName();
                    shopCategory.hasChildren=categoryBean.isHasChildren();
                    mShopCategoryList.add(shopCategory);
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

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        ShopCategory shopCategory= mShopCategoryList.get(position);
        if(!shopCategory.hasChildren){
            ProductListFragment.launchForResult(getActivity(),shopCategory.cid);
        }
    }

    private class GoodsAdapter extends ABaseAdapter<ShopCategory> {

        public GoodsAdapter(ArrayList<ShopCategory> datas, Activity context) {
            super(datas, context);
        }

        @Override
        protected AbstractItemView<ShopCategory> newItemView() {
            return new GoodsItemView();
        }
    }

    private class GoodsItemView extends ABaseAdapter.AbstractItemView<ShopCategory> {
        @ViewInject(id = R.id.goods_icon)
        private ImageView mViewGoodsType;

        @ViewInject(id = R.id.summary)
        private TextView mViewSummary;

        @Override
        public int inflateViewId() {
            return R.layout.list_item_goods_main;
        }

        @Override
        public void bindingData(View convertView, ShopCategory data) {
            ImageLoader.getInstance().displayImage(data.icon, mViewGoodsType, Tools.buildDisplayGoodsImgOptions());
            Tools.setTextView(mViewSummary,data.name);
        }
    }

    public class ShopCategory {
        int cid;
        String name;
        String icon;
        boolean hasChildren;
    }
}
