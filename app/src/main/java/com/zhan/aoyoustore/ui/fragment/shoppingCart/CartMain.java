package com.zhan.aoyoustore.ui.fragment.shoppingCart;

import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.zhan.aoyoustore.R;
import com.zhan.aoyoustore.base.BaseResponseBean;
import com.zhan.aoyoustore.base.UserInfo;
import com.zhan.aoyoustore.beans.ShoppingCartResponseBean;
import com.zhan.aoyoustore.network.ApiUrls;
import com.zhan.aoyoustore.utils.Tools;
import com.zhan.framework.network.HttpRequestParams;
import com.zhan.framework.network.HttpRequestUtils;
import com.zhan.framework.support.adapter.ABaseAdapter;
import com.zhan.framework.support.inject.ViewInject;
import com.zhan.framework.ui.fragment.APullToRefreshListFragment;
import com.zhan.framework.ui.fragment.ARefreshFragment;

import java.util.ArrayList;
import java.util.List;


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
public class CartMain extends APullToRefreshListFragment<CartMain.CardInfo> {

    @ViewInject(id = R.id.ck_sel_all)
    CheckBox mViewSelAll;

    @ViewInject(id = R.id.submit, click = "OnClick")
    View mViewSubmit;

    @ViewInject(id = R.id.totalPrice)
    TextView mTvTotalPrice;

    @Override
    protected int inflateContentView() {
        return R.layout.frag_cart_main;
    }

    @Override
    protected ABaseAdapter.AbstractItemView<CardInfo> newItemView() {
        return new ItemView();
    }

    @Override
    protected void setInitPullToRefresh(ListView listView, PullToRefreshListView pullToRefreshListView, Bundle savedInstanceState) {
        super.setInitPullToRefresh(listView, pullToRefreshListView, savedInstanceState);
        pullToRefreshListView.setMode(PullToRefreshBase.Mode.PULL_FROM_START);
    }

    @Override
    protected void requestData(ARefreshFragment.RefreshMode mode) {
        if (isRequestProcessing(ApiUrls.GET_SHOPPING_CART)) {
            return;
        }
        HttpRequestParams requestParams = new HttpRequestParams();
        requestParams.put("token", UserInfo.getCurrentUser().getToken());

        startFormRequest(ApiUrls.GET_SHOPPING_CART, requestParams, new PagingTask<ShoppingCartResponseBean>(mode) {
            @Override
            public ShoppingCartResponseBean parseResponseToResult(String content) {
                return Tools.parseJson(content, ShoppingCartResponseBean.class);
            }

            @Override
            public String verifyResponseResult(String result) {
                return Tools.verifyResponseResult(result);
            }

            @Override
            protected List<CardInfo> parseResult(ShoppingCartResponseBean responseBean) {
                ArrayList<CardInfo> cardInfoArrayList = new ArrayList<CardInfo>();
                if (responseBean != null && responseBean.getResult() != null) {

                    List<ShoppingCartResponseBean.ResultBean.CartItemInfoBean> cardList = responseBean.getResult().getCartItemInfo();
                    if (cardList != null) {
                        for (ShoppingCartResponseBean.ResultBean.CartItemInfoBean cardinfo : cardList) {
                            CardInfo cardInfo = new CardInfo();
                            cardInfo.name = cardinfo.getName();//商品名称
                            cardInfo.productCode = cardinfo.getProductCode();//商品编码
                            cardInfo.brand = cardinfo.getBrand();//品牌
                            cardInfo.shortDescription = cardinfo.getShortDescription();//商品简介
                            cardInfo.quantity = cardinfo.getQuantity();//商品数量
                            cardInfo.stock = cardinfo.getStock();//商品库存
                            cardInfo.subTotal = cardinfo.getSubTotal();//商品总价
                            cardInfo.thumbnailUr = cardinfo.getThumbnailUrl100();//图片
                            cardInfo.memberPrice = cardinfo.getMemberPrice();//会员价

                            cardInfoArrayList.add(cardInfo);
                        }
                    }
                }

                return cardInfoArrayList;
            }

        }, HttpRequestUtils.RequestType.POST);
    }

    void OnClick(View v) {
        switch (v.getId()) {
            case R.id.submit:
                break;
        }
    }

    private class ItemView extends ABaseAdapter.AbstractItemView<CardInfo> {
        @ViewInject(id = R.id.ck_sel)
        CheckBox checkBox;

        @ViewInject(id = R.id.productCode)
        TextView tvProductCode;

        @ViewInject(id = R.id.btn_delete)
        TextView tvDelete;

        @ViewInject(id = R.id.img)
        ImageView ivProductPhoto;

        @ViewInject(id = R.id.name)
        TextView tvProductName;

        @ViewInject(id = R.id.shortDescription)
        TextView tvShortDescription;

        @ViewInject(id = R.id.brand)
        TextView tvBrand;

        @ViewInject(id = R.id.price)
        TextView tvPrice;

        @Override
        public int inflateViewId() {
            return R.layout.list_item_card_info;
        }

        @Override
        public void bindingData(View convertView, CardInfo data) {

        }
    }

    public static class CardInfo {
        String productCode;//商品编码
        String name;//商品名称
        String brand;//品牌
        String shortDescription;//商品简介
        int quantity;//商品数量
        int stock;//商品库存
        double subTotal;//商品总价
        String thumbnailUr;//图片
        double memberPrice;//会员价
    }

}
