package com.zhan.aoyoustore.ui.fragment.shoppingCart;

import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import com.handmark.pulltorefresh.library.PullToRefreshExpandableListView;
import com.zhan.aoyoustore.R;
import com.zhan.framework.support.inject.ViewInject;
import com.zhan.framework.ui.fragment.ABaseFragment;


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
public class CartMain extends ABaseFragment {
    @ViewInject(id = R.id.my_order_list)
    PullToRefreshExpandableListView mMyOrderListView;
    @ViewInject(id = R.id.sel_all_order)
    CheckBox mCkSelelctAll;
    @ViewInject(id = R.id.all_product_total_price)
    TextView mTvTotalPrice;
    @ViewInject(id = R.id.all_product_total_count)
    TextView mTvTotalCount;
    @ViewInject(id = R.id.right_menu, click = "OnClick")
    TextView mTvRightMenu;
    @ViewInject(id = R.id.to_pay, click = "OnClick")
    TextView mTvToPay;
    @ViewInject(id = R.id.delete, click = "OnClick")
    TextView mTvDelete;
    @ViewInject(id = R.id.move_to_fav, click = "OnClick")
    TextView mTvMoveToFac;
    @ViewInject(id = R.id.go_home_page, click = "OnClick")
    Button mBtnGoHomePage;

    @Override
    protected int inflateContentView() {
        return R.layout.frag_cart_main;
    }


}
