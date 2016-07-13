package com.zhan.aoyoustore.ui.fragment.goods;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.zhan.aoyoustore.R;
import com.zhan.aoyoustore.beans.GetShopCategoriesResponseBean;
import com.zhan.aoyoustore.network.ApiUrls;
import com.zhan.aoyoustore.utils.Tools;
import com.zhan.framework.component.container.FragmentArgs;
import com.zhan.framework.component.container.FragmentContainerActivity;
import com.zhan.framework.network.HttpRequestParams;
import com.zhan.framework.network.HttpRequestUtils;
import com.zhan.framework.support.adapter.ABaseAdapter;
import com.zhan.framework.support.inject.ViewInject;
import com.zhan.framework.ui.fragment.APullToRefreshListFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者：伍岳 on 2016/7/10 22:43
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
public class ShopCategoryListFragment_bak extends APullToRefreshListFragment<ShopCategoryListFragment_bak.ShopCategory> {
    private final static String ARG_KEY_CATEGORY_ID="categoryId";
    private final static String ARG_KEY_CATEGORY_NAME="categoryName";

    //data
    private int mCategoryId;
    private String mTitle;

    public static void launch(Activity activity,int categoryId,String title){
        FragmentArgs args = new FragmentArgs();
        args.add(ARG_KEY_CATEGORY_ID, categoryId);
        args.add(ARG_KEY_CATEGORY_NAME, title);
        FragmentContainerActivity.launch(activity, ShopCategoryListFragment_bak.class, args);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mCategoryId = savedInstanceState == null ? (int) getArguments().getSerializable(ARG_KEY_CATEGORY_ID) : (int) savedInstanceState.getSerializable(ARG_KEY_CATEGORY_ID);
        mTitle = savedInstanceState == null ? (String) getArguments().getSerializable(ARG_KEY_CATEGORY_NAME) : (String) savedInstanceState.getSerializable(ARG_KEY_CATEGORY_NAME);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable(ARG_KEY_CATEGORY_ID, mCategoryId);
        outState.putSerializable(ARG_KEY_CATEGORY_NAME, mTitle);
    }

    @Override
    protected void setInitPullToRefresh(ListView listView, PullToRefreshListView pullToRefreshListView, Bundle savedInstanceState) {
        pullToRefreshListView.setMode(PullToRefreshBase.Mode.PULL_FROM_START);
    }

    @Override
    protected void layoutInit(LayoutInflater inflater, Bundle savedInstanceSate) {
        super.layoutInit(inflater, savedInstanceSate);
        getActivity().setTitle(mTitle);
    }

    @Override
    protected ABaseAdapter.AbstractItemView<ShopCategory> newItemView() {
        return new ShopCategoryItemView();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        ShopCategory shopCategory=getAdapterItems().get((int) id);
        if(shopCategory.proCount>0){
            ProductListFragment.launch(getActivity(), shopCategory.cid);
        }
    }

    @Override
    protected void requestData(RefreshMode mode) {
        HttpRequestParams requestParams=new HttpRequestParams();
        requestParams.put("categoryId",mCategoryId);

        startFormRequest(ApiUrls.GET_SHOP_CATEGORIES, null, new PagingTask<GetShopCategoriesResponseBean>(mode) {
            @Override
            public GetShopCategoriesResponseBean parseResponseToResult(String content) {
                return Tools.parseJson(content, GetShopCategoriesResponseBean.class);
            }

            @Override
            public String verifyResponseResult(String result) {
                return Tools.verifyResponseResult(result);
            }

            @Override
            protected List<ShopCategory> parseResult(GetShopCategoriesResponseBean result) {
                ArrayList<ShopCategory> shopCategories = new ArrayList<>();
                for(GetShopCategoriesResponseBean.ResultEntity.CategoryEntity categoryBean:result.getResult().getCategory()){
                    if(mCategoryId==categoryBean.getCid()){
                        for(GetShopCategoriesResponseBean.ResultEntity.CategoryEntity.SubsEntity subsEntity:categoryBean.getSubs()){
                            ShopCategory shopCategory=new ShopCategory();
                            shopCategory.cid=subsEntity.getCid();
                            shopCategory.icon=subsEntity.getIcon();
                            shopCategory.name=subsEntity.getName();
                            shopCategory.proCount=subsEntity.getProCount();
                            shopCategory.descriptions=subsEntity.getDescriptions();
                            shopCategories.add(shopCategory);
                        }
                        break;
                    }
                }
                return shopCategories;
            }

        }, HttpRequestUtils.RequestType.POST);
    }

    private class ShopCategoryItemView extends ABaseAdapter.AbstractItemView<ShopCategory>{

        @ViewInject(id = R.id.name)
        TextView mViewName ;

        /*@ViewInject(id = R.id.descriptions)
        TextView mViewDescriptions ;*/

        @ViewInject(id = R.id.proCount)
        TextView mViewProCount ;

        @Override
        public int inflateViewId() {
            return R.layout.list_item_shop_category_list;
        }

        @Override
        public void bindingData(View convertView, ShopCategory data) {
            Tools.setTextView(mViewName,data.name);
            //Tools.setTextView(mViewDescriptions,data.descriptions);
            Tools.setTextView(mViewProCount, String.valueOf(data.proCount));
        }
    }

    public class ShopCategory{
        int cid;
        String name;
        String icon;
        String descriptions;
        int proCount;
    }

}
