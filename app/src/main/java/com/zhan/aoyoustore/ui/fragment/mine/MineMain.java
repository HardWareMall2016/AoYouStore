package com.zhan.aoyoustore.ui.fragment.mine;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.zhan.aoyoustore.R;
import com.zhan.aoyoustore.base.UserInfo;
import com.zhan.aoyoustore.ui.activity.MainActivity;
import com.zhan.aoyoustore.utils.Tools;
import com.zhan.framework.common.setting.SettingUtility;
import com.zhan.framework.support.inject.ViewInject;
import com.zhan.framework.ui.fragment.ABaseFragment;
import com.zhan.framework.ui.widget.RippleButton;
import com.zhan.framework.utils.Consts;
import com.zhan.framework.utils.ToastUtils;

/**
 * 作者：蒲柯柯 on 2016/10/21 16:55
 * 邮箱：983198505@qq.com
 * 介绍:会员中心
 */
public class MineMain extends ABaseFragment {

    @ViewInject(id = R.id.tv_user_code)
    TextView mTvUserCode;

    @ViewInject(id = R.id.mine_photo)
    ImageView mViewHeadPortrait;

    @ViewInject(id = R.id.Tv_gradename)
    TextView mTvGradeName;

    @ViewInject(id = R.id.tv_membername)
    TextView mTvMemberName;

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
    RippleButton mTvQuit;

    @ViewInject(id = R.id.rl_account,click = "OnClick")
    RelativeLayout mRlAccount;
    @ViewInject(id = R.id.ll_account)
    LinearLayout mLlAcount;
    @ViewInject(id = R.id.iv_account_arrow)
    ImageView mIVAccountArrow;

    @ViewInject(id = R.id.rl_order,click = "OnClick")
    RelativeLayout mRlOrder;
    @ViewInject(id = R.id.ll_order_manager)
    LinearLayout mLlOrderMagager;
    @ViewInject(id = R.id.iv_order_arrow)
    ImageView mIvOrderArrow;

    private boolean AccountFlag = true;
    private boolean OrederFlag = true;

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

        ImageLoader.getInstance().displayImage(UserInfo.getCurrentUser().getPicture(), mViewHeadPortrait, Tools.buildDisplayImageOptionsForAvatar());
//        int UserPoint = UserInfo.getCurrentUser().getPoints();
//        if(UserPoint < 100001){
//            mTvGradeName.setText("会员等级：铜牌");
//        }else if(UserPoint >100000 && UserPoint <500001){
//            mTvGradeName.setText("会员等级：银牌");
//        }else if(UserPoint > 500000 && UserPoint < 2000001){
//            mTvGradeName.setText("会员等级：金牌");
//        }else{
//            mTvGradeName.setText("会员等级：钻石");
//        }
        mTvUserCode.setText("客户编号："+UserInfo.getCurrentUser().getUserCode());
        mTvGradeName.setText("会员等级："+UserInfo.getCurrentUser().getGradeName());
        mTvMemberName.setText("会员名称："+UserInfo.getCurrentUser().getNickName());
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
            case R.id.rl_account:
                if (AccountFlag){
                    mLlAcount.setVisibility(View.VISIBLE);
                    AccountFlag = false;
                    mIVAccountArrow.setImageResource(R.drawable.arrow_up_small);
                }else{
                    mLlAcount.setVisibility(View.GONE);
                    AccountFlag = true;
                    mIVAccountArrow.setImageResource(R.drawable.arrow_right_small);
                }
                break;
            case R.id.rl_order:
                if(OrederFlag){
                    mLlOrderMagager.setVisibility(View.VISIBLE);
                    OrederFlag = false;
                    mIvOrderArrow.setImageResource(R.drawable.arrow_up_small);
                }else {
                    mLlOrderMagager.setVisibility(View.GONE);
                    OrederFlag = true;
                    mIvOrderArrow.setImageResource(R.drawable.arrow_right_small);
                }
                break;
        }
    }

}
