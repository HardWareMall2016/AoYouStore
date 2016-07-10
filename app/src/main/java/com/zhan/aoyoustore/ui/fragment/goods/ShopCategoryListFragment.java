package com.zhan.aoyoustore.ui.fragment.goods;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.zhan.aoyoustore.R;
import com.zhan.aoyoustore.base.AppConstants;
import com.zhan.aoyoustore.beans.GetShopCategoriesResponseBean;
import com.zhan.aoyoustore.network.ApiUrls;
import com.zhan.aoyoustore.utils.Tools;
import com.zhan.framework.component.container.FragmentArgs;
import com.zhan.framework.component.container.FragmentContainerActivity;
import com.zhan.framework.network.HttpRequestParams;
import com.zhan.framework.network.HttpRequestUtils;
import com.zhan.framework.support.adapter.ABaseAdapter;
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
public class ShopCategoryListFragment extends APullToRefreshListFragment<ShopCategoryListFragment.ShopCategory> {
    private final static String ARG_KEY="arg_key";

    //data
    private int mCategoryId;

    public static void launch(Activity activity,int categoryId){
        FragmentArgs args = new FragmentArgs();
        args.add(ARG_KEY, categoryId);
        FragmentContainerActivity.launch(activity, ShopCategoryListFragment.class, args);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mCategoryId = savedInstanceState == null ? (int) getArguments().getSerializable(ARG_KEY) : (int) savedInstanceState.getSerializable(ARG_KEY);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable(ARG_KEY, mCategoryId);
    }

    @Override
    protected void setInitPullToRefresh(ListView listView, PullToRefreshListView pullToRefreshListView, Bundle savedInstanceState) {
        pullToRefreshListView.setMode(PullToRefreshBase.Mode.PULL_FROM_START);
    }

    @Override
    protected ABaseAdapter.AbstractItemView<ShopCategory> newItemView() {
        return new ShopCategoryItemView();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        ShopCategory shopCategory=getAdapterItems().get((int) id);
        ProductListFragment.launch(getActivity(), shopCategory.cid);
    }

    @Override
    protected void requestData(RefreshMode mode) {
        HttpRequestParams requestParams=new HttpRequestParams();
        requestParams.put("categoryId",mCategoryId);

        startFormRequest(ApiUrls.GET_SHOP_CATEGORIES, requestParams, new PagingTask<GetShopCategoriesResponseBean>(mode) {
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
                for(GetShopCategoriesResponseBean.ResultBean.CategoryBean categoryBean:result.getResult().getCategory()){
                    ShopCategory shopCategory=new ShopCategory();
                    shopCategory.cid=categoryBean.getCid();
                    shopCategory.icon=categoryBean.getIcon();
                    shopCategory.name=categoryBean.getName();
                    shopCategory.hasChildren=categoryBean.isHasChildren();
                    shopCategories.add(shopCategory);
                }
                return shopCategories;
            }

        }, HttpRequestUtils.RequestType.POST);
    }

    private class ShopCategoryItemView extends ABaseAdapter.AbstractItemView<ShopCategory>{
        @Override
        public int inflateViewId() {
            return R.layout.list_item_shop_category_list;
        }

        @Override
        public void bindingData(View convertView, ShopCategory data) {

        }
    }

    public class ShopCategory{
        int cid;
        String name;
        String icon;
        boolean hasChildren;
    }

}
