package com.zhan.aoyoustore.ui.activity;

import android.app.Activity;
import android.content.Context;
import android.view.Gravity;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.zhan.aoyoustore.R;
import com.zhan.aoyoustore.ui.fragment.goods.GoodsMain;
import com.zhan.aoyoustore.ui.fragment.commonquery.CommonQueryMain;
import com.zhan.aoyoustore.ui.fragment.mine.MineMain;
import com.zhan.aoyoustore.ui.fragment.shoppingCart.CartMain;
import com.zhan.aoyoustore.utils.Tools;
import com.zhan.framework.common.context.GlobalContext;
import com.zhan.framework.ui.fragment.ABaseFragment;
import com.zhan.framework.utils.PixelUtils;

import java.util.ArrayList;
import java.util.List;

public class PageFactory {
    private Activity mActivity;
    public PageFactory(Activity activity) {
        mActivity = activity;
    }

    public List<Page> createPages() {
        return generateManagementPages();
    }

    private List<Page> generateManagementPages() {
        List<Page> pageList = new ArrayList<>();
        //商品
        GoodsMain goodsMain = new GoodsMain();
        pageList.add(generatePage(goodsMain, R.string.module_goods, R.drawable.tab_home_unselect, R.drawable.tab_home_selected));
        //购物车
        CartMain cartMain = new CartMain();
        pageList.add(generatePage(cartMain, R.string.module_shopping_cart, R.drawable.tab_cart_unselect, R.drawable.tab_cart_selected));
        //会员中心
        MineMain mineMain = new MineMain();
        pageList.add(generatePage(mineMain, R.string.module_mine, R.drawable.tab_me_unselect, R.drawable.tab_me_selected));
        //常用查询
        CommonQueryMain commonQueryMain = new CommonQueryMain();
        pageList.add(generatePage(commonQueryMain, R.string.module_information, R.drawable.tab_goods_unselect, R.drawable.tab_goods_selected));
        return pageList;
    }

    private Page generatePage(ABaseFragment fragment, int titleRes, int normalDrawableId, int selectedDrawableId) {
        Page page = new Page();
        page.TAG = fragment.getClass().getSimpleName();
        page.bottomTitle = generateBottomTextView(mActivity, GlobalContext.getInstance().getString(titleRes));
        page.bottomTitle.setTag(page);
        page.pageFragment = fragment;
        page.normalDrawableId = normalDrawableId;
        page.selectedDrawableId = selectedDrawableId;
        return page;
    }

    private TextView generateBottomTextView(Context context, String bottomTitle) {
        TextView bottomTextView = new TextView(context);
        bottomTextView.setText(bottomTitle);
        bottomTextView.setCompoundDrawablePadding(PixelUtils.dp2px(2));
        bottomTextView.setTextSize(16);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        params.weight = 1.0f;
        params.gravity = Gravity.CENTER;
        bottomTextView.setLayoutParams(params);
        bottomTextView.setGravity(Gravity.CENTER);
        return bottomTextView;
    }

    /***
     * 进入
     * @return
     */
    public boolean showOrLauncherLoginPage(String pageTag){
        //购物车,我的需要登录才能进入
        if(CartMain.class.getSimpleName().equals(pageTag)||MineMain.class.getSimpleName().equals(pageTag)){
            return Tools.checkIsLogin(mActivity);
        }
        return true;
    }
}
