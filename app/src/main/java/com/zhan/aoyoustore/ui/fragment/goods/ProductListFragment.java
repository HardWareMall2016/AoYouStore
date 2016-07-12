package com.zhan.aoyoustore.ui.fragment.goods;

import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.app.Activity;
import android.graphics.Paint;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;
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
import com.zhan.framework.support.inject.ViewInject;
import com.zhan.framework.ui.fragment.APullToRefreshListFragment;
import com.zhan.framework.utils.PixelUtils;

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

    private final static String ARG_KEY_CATEGORY_ID = "arg_key_categoryId";
    private final static String ARG_KEY_SEARCH_KEY = "arg_key_search";

    @ViewInject(id = R.id.header_content)
    TextView mContentHeader;

    //data
    private int mCategoryId;
    private String mSearchKey;

    private ListView mListView;

    private int lastFirstVisibleItem = 0;
    private int lastTop = 0;
    private boolean isMoving = false;

    public static void launch(Activity activity, int categoryId) {
        FragmentArgs args = new FragmentArgs();
        args.add(ARG_KEY_CATEGORY_ID, categoryId);
        args.add(ARG_KEY_SEARCH_KEY, "");
        FragmentContainerActivity.launch(activity, ProductListFragment.class, args);
    }

    public static void launchSearchMode(Activity activity, String searchKey) {
        FragmentArgs args = new FragmentArgs();
        args.add(ARG_KEY_CATEGORY_ID, 0);
        args.add(ARG_KEY_SEARCH_KEY, searchKey);
        FragmentContainerActivity.launch(activity, ProductListFragment.class, args);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mCategoryId = savedInstanceState == null ? (int) getArguments().getSerializable(ARG_KEY_CATEGORY_ID) : (int) savedInstanceState.getSerializable(ARG_KEY_CATEGORY_ID);
        mSearchKey = savedInstanceState == null ? (String) getArguments().getSerializable(ARG_KEY_SEARCH_KEY) : (String) savedInstanceState.getSerializable(ARG_KEY_SEARCH_KEY);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable(ARG_KEY_CATEGORY_ID, mCategoryId);
        outState.putSerializable(ARG_KEY_SEARCH_KEY, mSearchKey);
    }

    @Override
    protected int inflateContentView() {
        return R.layout.frag_product_list;
    }

    @Override
    protected void layoutInit(LayoutInflater inflater, Bundle savedInstanceSate) {
        super.layoutInit(inflater, savedInstanceSate);
        getActivity().setTitle("商品列表");

        mListView = mPullToRefreshListView.getRefreshableView();
        mListView.setClipChildren(false);
        mListView.setClipToPadding(false);
        mListView.setPadding(0, PixelUtils.dp2px(50), 0, 0);
        mListView.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                if (scrollState == SCROLL_STATE_IDLE) {
                    isMoving = false;
                    lastTop = 0;
                }
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                if (mListView.getChildCount() == 0)
                    return;

                if (isMoving) {
                    View firstChild = view.getChildAt(0);

                    if (firstVisibleItem == 0) {
                        toggleHeaderShown(true);
                    } else if (firstVisibleItem > lastFirstVisibleItem) {
                        toggleHeaderShown(false);
                    } else if (firstVisibleItem < lastFirstVisibleItem) {
                        toggleHeaderShown(true);
                    } else {
                        int height = firstChild.getHeight();
                        if (height > PixelUtils.dp2px(200)) {
                            if (lastTop == 0) {
                                lastTop = firstChild.getTop();
                            } else {
                                int diffTop = firstChild.getTop() - lastTop;
                                if (Math.abs(diffTop) >= PixelUtils.dp2px(150)) {
                                    toggleHeaderShown(diffTop > 0);
                                }
                            }
                        }
                    }
                }

                lastFirstVisibleItem = firstVisibleItem;
            }
        });

        mListView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                } else if (event.getAction() == MotionEvent.ACTION_MOVE) {
                    isMoving = true;
                } else if (event.getAction() == MotionEvent.ACTION_UP) {
                }
                return false;
            }
        });
    }

    private void toggleHeaderShown(boolean shown) {
        lastTop = 0;
        if (shown) {
            showHeaderBar();
        } else {
            hideHeaderBar();
        }
    }

    /*Tool bar is show */
    private boolean isHeaderbarShown() {
        return mContentHeader.getTranslationY() >= 0;
    }

    public void hideHeaderBar() {
        if (isHeaderbarShown()) {
            toggleHeaderBarShown(false);
        }
    }

    public void showHeaderBar() {
        if (!isHeaderbarShown()) {
            toggleHeaderBarShown(true);
        }
    }

    private ObjectAnimator filterBarObjectAnim;

    public void toggleHeaderBarShown(boolean shown) {

        if (filterBarObjectAnim != null && filterBarObjectAnim.isRunning())
            return;

        if (isHeaderbarShown() && shown)
            return;
        else if (!isHeaderbarShown() && !shown)
            return;

        PropertyValuesHolder filterHolder = null;
        if (shown) {
            filterHolder = PropertyValuesHolder.ofFloat("translationY", -1 * mContentHeader.getHeight(), 0);
        } else {
            filterHolder = PropertyValuesHolder.ofFloat("translationY", 0, -1 * mContentHeader.getHeight());
        }
        filterBarObjectAnim = ObjectAnimator.ofPropertyValuesHolder(mContentHeader, filterHolder);
        filterBarObjectAnim.setDuration(150);

        filterBarObjectAnim.start();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Product product = getAdapterItems().get((int) id);
        ProductDetailFragment.launch(getActivity(), product.pid);
    }

    @Override
    protected ABaseAdapter.AbstractItemView<Product> newItemView() {
        return new ProductItemView();
    }

    @Override
    protected void configRefresh(RefreshConfig config) {
        config.minResultSize = AppConstants.DEF_PAGE_SIZE;
    }

    @Override
    protected void requestData(RefreshMode mode) {
        int page = getNextPage(mode);

        HttpRequestParams requestParams = new HttpRequestParams();
        if (TextUtils.isEmpty(mSearchKey)) {
            requestParams.put("categoryId", mCategoryId);
        } else {
            requestParams.put("keyword", mSearchKey);
        }
        requestParams.put("pageIndex", page);
        requestParams.put("pageSize", getRefreshConfig().minResultSize);

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
                List<Product> products = new ArrayList<>();
                if (productBean != null && productBean.getResult() != null) {
                    mContentHeader.setText(String.format("一共%d条结果", productBean.getResult().getTotals()));

                    for (GetProductListResponseBean.ResultBean.ProductsBean productsBean : productBean.getResult().getProducts()) {
                        Product productItem = new Product();
                        productItem.pid = productsBean.getPid();
                        productItem.discount = productsBean.getDiscount();
                        productItem.marketPrice = productsBean.getMarketPrice();
                        productItem.discount = productsBean.getDiscount();
                        productItem.name = productsBean.getName();
                        productItem.pic = productsBean.getPic();
                        productItem.saleCounts = productsBean.getSaleCounts();
                        productItem.price = productsBean.getPrice();
                        productItem.saleCounts = productsBean.getSaleCounts();
                        productItem.url = productsBean.getUrl();
                        products.add(productItem);
                    }
                }
                return products;
            }
        }, HttpRequestUtils.RequestType.POST);
    }

    private class ProductItemView extends ABaseAdapter.AbstractItemView<Product> {
        @ViewInject(id = R.id.goods_icon)
        ImageView mViewGoodsIcon;

        @ViewInject(id = R.id.name)
        TextView mViewName;

        @ViewInject(id = R.id.price)
        TextView mViewPrice;

        @ViewInject(id = R.id.marketPrice)
        TextView mViewMarketPrice;

        @ViewInject(id = R.id.saleCounts)
        TextView mViewSaleCounts;

        @ViewInject(id = R.id.discount)
        TextView mViewDiscount;

        @Override
        public int inflateViewId() {
            return R.layout.list_item_get_product_list;
        }

        @Override
        public void bindingView(View convertView) {
            super.bindingView(convertView);
            mViewMarketPrice.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
        }

        @Override
        public void bindingData(View convertView, Product data) {
            ImageLoader.getInstance().displayImage(data.pic, mViewGoodsIcon, Tools.buildDisplayGoodsImgOptions());
            Tools.setTextView(mViewName, data.name);
            Tools.setTextView(mViewPrice, "￥" + data.price);
            Tools.setTextView(mViewMarketPrice, "￥" + data.marketPrice);
            Tools.setTextView(mViewSaleCounts, "销量:" + data.saleCounts);

            String discountStr = "折扣:";
            if (TextUtils.isEmpty(data.discount)) {
                discountStr += "无";
            } else {
                discountStr += data.discount;
            }
            Tools.setTextView(mViewDiscount, discountStr);
        }
    }

    public class Product {
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
