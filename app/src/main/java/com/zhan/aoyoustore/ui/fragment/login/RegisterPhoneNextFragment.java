package com.zhan.aoyoustore.ui.fragment.login;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.zhan.aoyoustore.R;
import com.zhan.aoyoustore.base.UserInfo;
import com.zhan.aoyoustore.beans.GetLoginCaptchaResponseBean;
import com.zhan.aoyoustore.beans.RegisterPhoneNextContent;
import com.zhan.aoyoustore.beans.RegisterResponseBean;
import com.zhan.aoyoustore.network.ApiUrls;
import com.zhan.aoyoustore.ui.activity.MainActivity;
import com.zhan.aoyoustore.utils.Tools;
import com.zhan.framework.component.container.FragmentArgs;
import com.zhan.framework.component.container.FragmentContainerActivity;
import com.zhan.framework.network.HttpRequestParams;
import com.zhan.framework.network.HttpRequestUtils;
import com.zhan.framework.support.inject.ViewInject;
import com.zhan.framework.ui.activity.ActionBarActivity;
import com.zhan.framework.ui.fragment.ABaseFragment;
import com.zhan.framework.utils.ToastUtils;

/**
 * 作者：keke on 2016/7/26 11:23
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
public class RegisterPhoneNextFragment extends ABaseFragment {
    private final static String ARG_KEY = "registerphonenext";

    @ViewInject(id = R.id.register_next_user_name)
    EditText mEtUserName;
    @ViewInject(id = R.id.register_next_psword)
    EditText mEtPsw;
    @ViewInject(id = R.id.register_next, click = "OnClick")
    Button mNext;

    private String userName;
    private String pssword;

    private RegisterPhoneNextContent content;


    public static void launch(FragmentActivity activity, RegisterPhoneNextContent content) {
        FragmentArgs args = new FragmentArgs();
        args.add(ARG_KEY, content);
        FragmentContainerActivity.launch(activity, RegisterPhoneNextFragment.class, args);
    }

    @Override
    protected int inflateContentView() {
        return R.layout.frag_register_phone_next;
    }

    @Override
    protected void layoutInit(LayoutInflater inflater, Bundle savedInstanceSate) {
        getActivity().setTitle("手机号注册");
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        content = savedInstanceState == null ? (RegisterPhoneNextContent) getArguments().getSerializable(ARG_KEY) : (RegisterPhoneNextContent) savedInstanceState.getSerializable(ARG_KEY);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable(ARG_KEY, content);
    }

    void OnClick(View view) {
        switch (view.getId()) {
            case R.id.register_next:
                if (!checkPhoneNumInput()) {
                    return;
                }
                if (isRequestProcessing(ApiUrls.REGISTER)) {
                    return;
                }

                HttpRequestParams requestParams = new HttpRequestParams();
                requestParams.put("userName", userName);
                requestParams.put("password", pssword);
                requestParams.put("verifyCode", content.getVerifyCode());
                startFormRequest(ApiUrls.REGISTER, requestParams, new BaseHttpRequestTask<RegisterResponseBean>() {
                    @Override
                    public RegisterResponseBean parseResponseToResult(String content) {
                        return Tools.parseJson(content, RegisterResponseBean.class);
                    }

                    @Override
                    public String verifyResponseResult(String result) {
                        return Tools.verifyResponseResult(result);
                    }

                    @Override
                    protected void onSuccess(RegisterResponseBean result) {
                        super.onSuccess(result);
                        ToastUtils.toast("注册成功");
                        UserInfo user = new UserInfo();
                        user.setRes(result.getResult().getRes());
                        user.setToken(result.getResult().getToken());
                        user.setJpush(result.getResult().getJpush());
                        user.setUid(result.getResult().getUid());
                        user.setUserName(result.getResult().getUserName());
                        user.setGradeName(result.getResult().getGradeName());
                        user.setNickName(result.getResult().getNickName());
                        user.setRealName(result.getResult().getRealName());
                        user.setWaitPayCount(result.getResult().getWaitPayCount());
                        user.setWaitFinishCount(result.getResult().getWaitFinishCount());
                        user.setOrderNumber(result.getResult().getOrderNumber());
                        user.setExpenditure(result.getResult().getExpenditure());
                        user.setPoints(result.getResult().getPoints());
                        user.setGender(result.getResult().getGender());
                        user.setPicture(result.getResult().getPicture());
                        user.setCouponsCount(result.getResult().getCouponsCount());
                        user.setIsOpenBalance(result.getResult().isIsOpenBalance());
                        user.setBalance(result.getResult().getBalance());
                        user.setHasBalance(result.getResult().getHasBalance());
                        user.setFrozenBalance(result.getResult().getFrozenBalance());
                        user.setEmail(result.getResult().getEmail());
                        user.setMobile(result.getResult().getMobile());
                        user.setCompanyName(result.getResult().getCompanyName());
                        user.setCompanyType(result.getResult().getCompanyType());
                        user.setCompanyJob(result.getResult().getCompanyJob());
                        user.setCompanySite(result.getResult().getCompanySite());
                        user.setTelPhone(result.getResult().getTelPhone());
                        user.setFax(result.getResult().getFax());
                        user.setReferralStatus(result.getResult().getReferralStatus());
                        user.setProvince(result.getResult().getProvince());
                        user.setCity(result.getResult().getCity());
                        user.setDistrict(result.getResult().getDistrict());
                        user.setAddress(result.getResult().getAddress());
                        user.setQq(result.getResult().getQq());
                        user.setMsn(result.getResult().getMsn());
                        user.setWangWang(result.getResult().getWangWang());
                        user.setUserCode(result.getResult().getUserCode());
                        user.setIsLogin(true);
                        UserInfo.saveLoginUserInfo(user);

                        Intent homePageIntent = new Intent(getActivity(), MainActivity.class);
                        homePageIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(homePageIntent);
                        getActivity().finish();
                    }
                }, HttpRequestUtils.RequestType.POST);

                break;
        }
    }

    private boolean checkPhoneNumInput() {
        userName = mEtUserName.getText().toString();
        pssword = mEtPsw.getText().toString();

        if (TextUtils.isEmpty(userName)) {
            ToastUtils.toast("请输入手机号");
            return false;
        }
        if (!Tools.checkMobilePhoneNumber(userName)) {
            ToastUtils.toast("请输入正确的手机号");
            return false;
        }
        if (TextUtils.isEmpty(pssword)) {
            ToastUtils.toast("请输入密码");
            return false;
        }
        return true;
    }

}
