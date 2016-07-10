package com.zhan.aoyoustore.ui.fragment.goods;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.zhan.aoyoustore.R;
import com.zhan.aoyoustore.base.AppConstants;
import com.zhan.aoyoustore.beans.GetProductListResponseBean;
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
 * 作者：伍岳 on 2016/7/10 19:45
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
public class ProductListFragment extends APullToRefreshListFragment<ProductListFragment.Product> {

    private final static String ARG_KEY="arg_key";

    //data
    private int mCategoryId;

    public static void launchForResult(Activity activity,int categoryId){
        FragmentArgs args = new FragmentArgs();
        args.add(ARG_KEY, categoryId);
        FragmentContainerActivity.launch(activity, ProductListFragment.class, args);
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
    protected ABaseAdapter.AbstractItemView<Product> newItemView() {
        return new ProductItemView();
    }

    @Override
    protected void configRefresh(RefreshConfig config) {
        config.minResultSize= AppConstants.DEF_PAGE_SIZE;
    }

    @Override
    protected void requestData(RefreshMode mode) {
        int page=getNextPage(mode);

        HttpRequestParams requestParams=new HttpRequestParams();
        requestParams.put("pageIndex",page);
        requestParams.put("pageSize",getRefreshConfig().minResultSize);

        startFormRequest(ApiUrls.GET_PRODUCT_LIST, requestParams, new PagingTask<GetProductListResponseBean>(mode) {
            @Override
            public GetProductListResponseBean parseResponseToResult(String content) {
                return Tools.parseJson(content, GetProductListResponseBean.class);
            }

            @Override
            public String verifyResponseResult(String content) {
                return Tools.verifyResponseResult(content);
            }

            @Override
            protected List<Product> parseResult(GetProductListResponseBean productBean) {
                List<Product> products=new ArrayList<>();
                for(GetProductListResponseBean.ResultBean.ProductsBean productsBean:productBean.getResult().getProducts()){
                    Product productItem=new Product();
                    productItem.pid=productsBean.getPid();
                    productItem.discount=productsBean.getDiscount();
                    productItem.marketPrice=productsBean.getMarketPrice();
                    productItem.discount=productsBean.getDiscount();
                    productItem.name=productsBean.getName();
                    productItem.pic=productsBean.getPic();
                    productItem.saleCounts=productsBean.getSaleCounts();
                    productItem.price=productsBean.getPrice();
                    productItem.saleCounts=productsBean.getSaleCounts();
                    productItem.url=productsBean.getUrl();
                    products.add(productItem);
                }

                return products;
            }
        }, HttpRequestUtils.RequestType.POST);
    }

    private class ProductItemView extends ABaseAdapter.AbstractItemView<Product>{

        @Override
        public int inflateViewId() {
            return R.layout.list_item_get_product_list;
        }

        @Override
        public void bindingData(View convertView, Product data) {

        }
    }

    public class Product{
        int pid;
        String name;
        String pic;
        double price;
        double marketPrice;
        String discount;
        int saleCounts;
        String url;
    }

}
