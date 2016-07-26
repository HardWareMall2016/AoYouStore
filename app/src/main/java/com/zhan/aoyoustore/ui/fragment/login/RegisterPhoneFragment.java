package com.zhan.aoyoustore.ui.fragment.login;

import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v4.app.FragmentActivity;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.zhan.aoyoustore.R;
import com.zhan.aoyoustore.beans.GetLoginCaptchaResponseBean;
import com.zhan.aoyoustore.beans.RegisterPhoneNextContent;
import com.zhan.aoyoustore.network.ApiUrls;
import com.zhan.aoyoustore.utils.Tools;
import com.zhan.framework.component.container.FragmentContainerActivity;
import com.zhan.framework.network.HttpRequestParams;
import com.zhan.framework.network.HttpRequestUtils;
import com.zhan.framework.support.inject.ViewInject;
import com.zhan.framework.ui.activity.ActionBarActivity;
import com.zhan.framework.ui.fragment.ABaseFragment;
import com.zhan.framework.utils.ToastUtils;

/**
 * 作者：keke on 2016/7/25 14:06
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
public class RegisterPhoneFragment extends ABaseFragment {
    @ViewInject(id = R.id.btn_next, click = "OnClick")
    Button mNext;
    @ViewInject(id = R.id.register_message_phone)
    EditText mEtPhone ;
    @ViewInject(id = R.id.register_message_code)
    EditText mEtMsgCode ;

    @ViewInject(id = R.id.register_phone_code,click = "OnClick")
    Button mPhoneCode ;

    private String phoneNum ;
    private String messageCode ;

    //Tools
    private CountDownTimer mTimer;

    @Override
    protected int inflateContentView() {
        return R.layout.frag_register_phone;
    }

    public static void launch(FragmentActivity activity) {
        FragmentContainerActivity.launch(activity, RegisterPhoneFragment.class, null);
    }

    @Override
    protected void layoutInit(LayoutInflater inflater, Bundle savedInstanceSate) {
        getActivity().setTitle("手机快速注册");
        ActionBarActivity actionBarActivity = (ActionBarActivity) getActivity();
        TextView rightMenu = actionBarActivity.getActionBarRightMenu();
        rightMenu.setText("注册");
        rightMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RegisterFragment.launch(getActivity());
            }
        });
    }

    @Override
    public void onDestroyView() {
        cancelCountDownTimer();
        super.onDestroyView();
    }

    void OnClick(View view) {
        switch (view.getId()) {
            case R.id.btn_next:
                if (!checkPhoneNumInput()) {
                    return;
                }
                if(!checkPhoneCode()){
                    return;
                }
                RegisterPhoneNextContent content = new RegisterPhoneNextContent();
                content.setVerifyCode("8888");
                RegisterPhoneNextFragment.launch(getActivity(),content);
                break;
            case R.id.register_phone_code:
                if (!checkPhoneNumInput()) {
                    return;
                }
                startCountDownTimer();
                break;
        }
    }

    private void startCountDownTimer() {
        if(mTimer!=null){
            cancelCountDownTimer();
        }

        mPhoneCode.setEnabled(false);
        mPhoneCode.setTextColor(Color.rgb(102, 102, 102));
        mTimer = new CountDownTimer(60 * 1000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                String formatStr = "%d秒后重试";
                mPhoneCode.setText(String.format(formatStr, millisUntilFinished / 1000));
            }

            @Override
            public void onFinish() {
                mPhoneCode.setEnabled(true);
                mPhoneCode.setText(R.string.get_sms_code);
                mPhoneCode.setTextColor(0xff9390A5);
            }
        };
        mTimer.start();

        getCodeRequest();
    }

    //获取短信验证码
    private void getCodeRequest() {
        if (isRequestProcessing(ApiUrls.GET_LOGIN_CAPTCHA)) {
            return;
        }

        HttpRequestParams requestParams = new HttpRequestParams();
        requestParams.put("phone", phoneNum);
        startFormRequest(ApiUrls.GET_LOGIN_CAPTCHA, requestParams, new BaseHttpRequestTask<GetLoginCaptchaResponseBean>() {
            @Override
            public GetLoginCaptchaResponseBean parseResponseToResult(String content) {
                return Tools.parseJson(content, GetLoginCaptchaResponseBean.class);
            }

            @Override
            public String verifyResponseResult(String result) {
                return Tools.verifyResponseResult(result);
            }

            @Override
            protected void onSuccess(GetLoginCaptchaResponseBean result) {
                super.onSuccess(result);
                ToastUtils.toast(result.getResult().getMsg());
            }
        }, HttpRequestUtils.RequestType.POST);

    }

    private void cancelCountDownTimer(){
        mPhoneCode.setEnabled(true);
        mPhoneCode.setText(R.string.get_sms_code);
        mPhoneCode.setTextColor(0xff9390A5);
        if(mTimer!=null){
            mTimer.cancel();
        }
        mTimer=null;
    }


    private boolean checkPhoneCode(){
        if (TextUtils.isEmpty(messageCode)) {
            ToastUtils.toast("请输入验证码");
            return false;
        }
        return true;
    }
    private boolean checkPhoneNumInput() {
        phoneNum = mEtPhone.getText().toString();
        messageCode = mEtMsgCode.getText().toString();

        if (TextUtils.isEmpty(phoneNum)) {
            ToastUtils.toast("请输入手机号");
            return false;
        }
        if (!Tools.checkMobilePhoneNumber(phoneNum)) {
            ToastUtils.toast("请输入正确的手机号");
            return false;
        }

        return true;
    }


}
