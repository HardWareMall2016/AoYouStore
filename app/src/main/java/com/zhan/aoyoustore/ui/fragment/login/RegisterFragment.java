package com.zhan.aoyoustore.ui.fragment.login;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.zhan.aoyoustore.R;
import com.zhan.aoyoustore.base.UserInfo;
import com.zhan.aoyoustore.beans.RegisterResponseBean;
import com.zhan.aoyoustore.network.ApiUrls;
import com.zhan.aoyoustore.ui.activity.MainActivity;
import com.zhan.aoyoustore.utils.Tools;
import com.zhan.framework.component.container.FragmentContainerActivity;
import com.zhan.framework.network.HttpRequestParams;
import com.zhan.framework.network.HttpRequestUtils;
import com.zhan.framework.support.inject.ViewInject;
import com.zhan.framework.ui.fragment.ABaseFragment;
import com.zhan.framework.utils.ToastUtils;

/**
 * 作者：keke on 2016/7/25 10:23
 * //
 */
public class RegisterFragment extends ABaseFragment{
    @ViewInject(id = R.id.register_user_name)
    EditText mEtUserName ;
    @ViewInject(id = R.id.register_psword)
    EditText mEtPsword ;
    @ViewInject(id = R.id.register_next,click = "OnClick")
    Button mRegister ;

    private String username ;
    private String password ;



    @Override
    protected int inflateContentView() {
        return R.layout.frag_register;
    }

    public static void launch(FragmentActivity activity) {
        FragmentContainerActivity.launch(activity, RegisterFragment.class, null);
    }
    @Override
    protected void layoutInit(LayoutInflater inflater, Bundle savedInstanceSate) {
        super.layoutInit(inflater, savedInstanceSate);
        getActivity().setTitle("注册");
    }

    void OnClick(View view) {
        switch (view.getId()) {
            case R.id.register_next:
                if (!checkInput()) {
                    return;
                }
                if(isRequestProcessing(ApiUrls.REGISTER)){
                    return;
                }

                HttpRequestParams requestParams = new HttpRequestParams();
                requestParams.put("userName", username);
                requestParams.put("password",password);
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

    private boolean checkInput() {
        username = mEtUserName.getText().toString();
        password = mEtPsword.getText().toString();
        if(TextUtils.isEmpty(username)){
            ToastUtils.toast("请输入用户名");
            return false;
        }
        if(username.length() < 6 && username.length() > 50){
            ToastUtils.toast("请输入6-50个字符的用户名");
            return false;
        }
        if(TextUtils.isEmpty(password)){
            ToastUtils.toast("请输入密码");
            return false;
        }
        return true;
    }

}
