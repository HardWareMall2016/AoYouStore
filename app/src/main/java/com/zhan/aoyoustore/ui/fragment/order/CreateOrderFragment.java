package com.zhan.aoyoustore.ui.fragment.order;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.zhan.aoyoustore.R;
import com.zhan.framework.component.container.FragmentArgs;
import com.zhan.framework.component.container.FragmentContainerActivity;
import com.zhan.framework.support.inject.ViewInject;
import com.zhan.framework.ui.fragment.ABaseFragment;

/**
 * 作者：伍岳 on 2016/10/22 22:32
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
public class CreateOrderFragment extends ABaseFragment {
    private final static String ARG_KEY="arg_key";

    @ViewInject(id = R.id.address_content, click = "OnClick")
    View mViewAddressContent;

    @ViewInject(id = R.id.consignee)
    TextView mViewConsignee;

    @ViewInject(id = R.id.phone)
    TextView mViewPhone;

    @ViewInject(id = R.id.address)
    TextView mViewAddress;

    @ViewInject(id = R.id.totalAmount)
    TextView mViewTotalAmount;

    @ViewInject(id = R.id.order_people_content, click = "OnClick")
    View mViewOrderPeopleContent;

    @ViewInject(id = R.id.delivery_way_content, click = "OnClick")
    View mViewDeliveryWayContent;

    @ViewInject(id = R.id.pay_way_content, click = "OnClick")
    View mViewPayWayContent;

    @ViewInject(id = R.id.submit, click = "OnClick")
    TextView mViewSubmit;

    public static void launch(Activity activity){
        FragmentArgs args = new FragmentArgs();
        args.add(ARG_KEY, 0);
        FragmentContainerActivity.launch(activity, CreateOrderFragment.class, args);
    }

    @Override
    protected int inflateContentView() {
        return R.layout.frag_create_order;
    }

    @Override
    protected void layoutInit(LayoutInflater inflater, Bundle savedInstanceSate) {
        super.layoutInit(inflater, savedInstanceSate);
        getActivity().setTitle("创建订单");
    }

    void OnClick(View v) {
        switch (v.getId()) {
            case R.id.address_content:
                break;
            case R.id.submit:
                break;
            case R.id.order_people_content:
                break;
            case R.id.delivery_way_content:
                break;
            case R.id.pay_way_content:
                break;
        }
    }
}
