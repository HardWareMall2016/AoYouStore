package com.zhan.aoyoustore.ui.fragment.mine;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.zhan.aoyoustore.R;
import com.zhan.aoyoustore.base.UserInfo;
import com.zhan.aoyoustore.ui.activity.MainActivity;
import com.zhan.framework.support.inject.ViewInject;
import com.zhan.framework.ui.fragment.ABaseFragment;
import com.zhan.framework.utils.ToastUtils;

/**
 * 作者：蒲柯柯 on 2016/10/21 16:55
 * 邮箱：983198505@qq.com
 * 介绍:会员中心
 */
public class MineMain extends ABaseFragment {

    @ViewInject(id = R.id.tv_edit_person,click = "OnClick")
    TextView mTvEditPerson;

    @ViewInject(id = R.id.tv_Receipt_address,click = "OnClick")
    TextView mTvReceiptAddress;

    @ViewInject(id = R.id.tv_change_password,click = "OnClick")
    TextView mTvChangePsw;

    @ViewInject(id = R.id.tv_Order_management,click = "OnClick")
    TextView mTvOrderManager;

    @ViewInject(id = R.id.tv_Purchase_records,click = "OnClick")
    TextView mTvPurchaseRecords;

    @ViewInject(id = R.id.tv_Collection,click = "OnClick")
    TextView mTvCollection;

    @ViewInject(id = R.id.tv_contact,click = "OnClick")
    TextView mTvContact;

    @ViewInject(id = R.id.tv_quit,click = "OnClick")
    TextView mTvQuit;

    //Tools
    private LayoutInflater mInflater;

    @Override
    protected int inflateContentView() {
        return R.layout.frag_mine;
    }

    @Override
    protected void layoutInit(LayoutInflater inflater, Bundle savedInstanceSate) {
        super.layoutInit(inflater, savedInstanceSate);
        mInflater = inflater;
        getActivity().setTitle("会员中心");
    }

    void OnClick(View view){
        switch (view.getId()){
            case R.id.tv_edit_person:
                MinePersonFragment.launch(getActivity());
                break;
            case R.id.tv_Receipt_address:
                MineReceiptAddressFragment.launch(getActivity());
                break;
            case R.id.tv_change_password:
                MineChangePswFragment.launch(getActivity());
                break;
            case R.id.tv_Order_management:
                MineOrderManagementFragment.launch(getActivity());
                break;
            case R.id.tv_Purchase_records:
                PurchaseRecordFragment.launch(getActivity());
                break;
            case R.id.tv_Collection:
                MineCollectionFragment.launch(getActivity());
                break;
            case R.id.tv_contact:
                MineContactFragment.launch(getActivity());
                break;
            case R.id.tv_quit:
                UserInfo.logout();
                ToastUtils.toast("退出登录");
                Intent homePageIntent = new Intent(getActivity(), MainActivity.class);
                homePageIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(homePageIntent);
                break;
        }
    }

}
