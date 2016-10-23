package com.zhan.aoyoustore.ui.fragment.shoppingCart;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.zhan.aoyoustore.R;
import com.zhan.aoyoustore.base.BaseResponseBean;
import com.zhan.aoyoustore.base.UserInfo;
import com.zhan.aoyoustore.beans.SetShoppingCartQuantityResponseBean;
import com.zhan.aoyoustore.beans.ShoppingCartResponseBean;
import com.zhan.aoyoustore.network.ApiUrls;
import com.zhan.aoyoustore.utils.Tools;
import com.zhan.framework.network.HttpRequestHandler;
import com.zhan.framework.network.HttpRequestParams;
import com.zhan.framework.network.HttpRequestUtils;
import com.zhan.framework.support.adapter.ABaseAdapter;
import com.zhan.framework.support.inject.ViewInject;
import com.zhan.framework.ui.fragment.APullToRefreshListFragment;
import com.zhan.framework.ui.fragment.ARefreshFragment;
import com.zhan.framework.utils.ToastUtils;

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
    protected void layoutInit(LayoutInflater inflater, Bundle savedInstanceSate) {
        super.layoutInit(inflater, savedInstanceSate);
        mViewSelAll.setOnCheckedChangeListener(mAllCheckChange);
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
            public void onRequestFinished(ResultCode resultCode, String result) {
                super.onRequestFinished(resultCode, result);
                refreshView();
            }

            @Override
            protected List<CardInfo> parseResult(ShoppingCartResponseBean responseBean) {
                ArrayList<CardInfo> cardInfoArrayList = new ArrayList<>();
                if (responseBean != null && responseBean.getResult() != null) {

                    List<ShoppingCartResponseBean.ResultBean.CartItemInfoBean> cardList = responseBean.getResult().getCartItemInfo();
                    if (cardList != null) {
                        for (ShoppingCartResponseBean.ResultBean.CartItemInfoBean cardinfo : cardList) {
                            CardInfo cardInfo = new CardInfo();
                            cardInfo.skuID=cardinfo.getSkuID();
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

        @ViewInject(id = R.id.minus)
        TextView tvMinus;

        @ViewInject(id = R.id.tvNum)
        TextView tvNum;

        @ViewInject(id = R.id.plus)
        TextView tvPlus;

        @ViewInject(id = R.id.totalPrice)
        TextView tvTotalPrice;

        @ViewInject(id = R.id.stock)
        TextView tvStock;

        @Override
        public int inflateViewId() {
            return R.layout.list_item_card_info;
        }


        @Override
        public void bindingView(View convertView) {
            super.bindingView(convertView);
            checkBox.setOnClickListener(mOnClicked);
            tvDelete.setOnClickListener(mOnClicked);
            tvMinus.setOnClickListener(mOnClicked);
            tvPlus.setOnClickListener(mOnClicked);
        }

        @Override
        public void bindingData(View convertView, CardInfo data) {
            checkBox.setTag(data);
            tvDelete.setTag(data);
            tvMinus.setTag(data);
            tvPlus.setTag(data);

            checkBox.setChecked(data.selected);
            tvProductCode.setText(String.format("商品编码：%s", data.productCode));

            ImageLoader.getInstance().displayImage(data.thumbnailUr, ivProductPhoto, Tools.buildDisplayGoodsImgOptions());

            Tools.setTextView(tvProductName, data.name);
            Tools.setTextView(tvShortDescription, data.shortDescription);

            tvBrand.setText(String.format("品牌：%s", data.brand));

            tvPrice.setText(Tools.formatNumberWithMoney(data.memberPrice));
            tvNum.setText(String.valueOf(data.quantity));

            tvTotalPrice.setText(String.format("合计：%s", Tools.formatNumberWithMoney(data.subTotal)));

            tvStock.setText(String.format("库存：%d", data.stock));
        }
    }

    private View.OnClickListener mOnClicked = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            CardInfo data=(CardInfo)v.getTag();
            switch (v.getId()) {
                case R.id.ck_sel:
                    data.selected=!data.selected;
                    refreshView();
                    break;
                case R.id.btn_delete:
                    delCardItem(data);
                    break;
                case R.id.minus:
                    if(data.quantity>1){
                        data.quantity--;
                        setShoppingCartQuantity(data,data.quantity);
                    }
                    break;
                case R.id.plus:
                    data.quantity++;
                    setShoppingCartQuantity(data,data.quantity);
                    break;
            }
        }
    };

    private void refreshView(){
        double totalPrice=0;
        for(CardInfo cardInfo:getAdapterItems()){
            if(cardInfo.selected){
                totalPrice+=cardInfo.subTotal;
            }
        }
        mTvTotalPrice.setText(Tools.formatNumberWithMoney(totalPrice));

        notifyDataSetChanged();
    }

    private CompoundButton.OnCheckedChangeListener mAllCheckChange = new CompoundButton.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            for (CardInfo cardInfo : getAdapterItems()) {
                cardInfo.selected = isChecked;
            }
            notifyDataSetChanged();

            double totalPrice = 0;
            if(isChecked){
                for (CardInfo cardInfo : getAdapterItems()) {
                    totalPrice += cardInfo.subTotal;
                }
            }

            mTvTotalPrice.setText(Tools.formatNumberWithMoney(totalPrice));
        }
    };

    private void setShoppingCartQuantity(CardInfo data,int quantity){
        if(isRequestProcessing(ApiUrls.SET_SHOPPING_CART_QUANTITY)){
            return;
        }
        showRotateProgressDialog("修改中..",true);

        HttpRequestParams requestParams = new HttpRequestParams();
        requestParams.put("token", UserInfo.getCurrentUser().getToken());
        requestParams.put("skuId", data.skuID);
        requestParams.put("quantity", quantity);

        startFormRequest(ApiUrls.SET_SHOPPING_CART_QUANTITY, requestParams, new HttpRequestHandler(this,data) {
            @Override
            public void onRequestFinished(ResultCode resultCode, String result) {
                closeRotateProgressDialog();
                CardInfo data=(CardInfo)getTag();
                switch (resultCode) {
                    case success:
                        SetShoppingCartQuantityResponseBean responseBean = Tools.parseJsonTostError(result, SetShoppingCartQuantityResponseBean.class);
                        if (responseBean != null) {
                            data.memberPrice=responseBean.getResult().getPrice();
                            data.stock=responseBean.getResult().getStock();
                            data.subTotal=responseBean.getResult().getTotalPrice();

                            refreshView();
                        }
                        break;
                    default:
                        ToastUtils.toast(result);
                        break;
                }
            }
        }, HttpRequestUtils.RequestType.POST);
    }

    private void delCardItem(CardInfo data){
        if(isRequestProcessing(ApiUrls.DEL_CART_ITEM)){
            return;
        }
        showRotateProgressDialog("删除中...", true);

        HttpRequestParams requestParams = new HttpRequestParams();
        requestParams.put("token", UserInfo.getCurrentUser().getToken());
        requestParams.put("skuIds", data.skuID);

        startFormRequest(ApiUrls.DEL_CART_ITEM, requestParams, new HttpRequestHandler(this) {
            @Override
            public void onRequestFinished(ResultCode resultCode, String result) {
                closeRotateProgressDialog();
                switch (resultCode) {
                    case success:
                        BaseResponseBean responseBean = Tools.parseJsonTostError(result, BaseResponseBean.class);
                        if (responseBean != null) {
                            requestData();
                        }
                        break;
                    default:
                        ToastUtils.toast(result);
                        break;
                }
            }
        }, HttpRequestUtils.RequestType.POST);
    }

    public static class CardInfo {
        boolean selected = false;
        String skuID;
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
