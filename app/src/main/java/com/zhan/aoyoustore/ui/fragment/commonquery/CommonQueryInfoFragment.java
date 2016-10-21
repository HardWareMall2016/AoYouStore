package com.zhan.aoyoustore.ui.fragment.commonquery;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.webkit.WebView;

import com.zhan.aoyoustore.R;
import com.zhan.aoyoustore.beans.CommonQueryInfoResponseBean;
import com.zhan.aoyoustore.network.ApiUrls;
import com.zhan.aoyoustore.utils.Tools;
import com.zhan.framework.component.container.FragmentArgs;
import com.zhan.framework.component.container.FragmentContainerActivity;
import com.zhan.framework.network.HttpRequestParams;
import com.zhan.framework.network.HttpRequestUtils;
import com.zhan.framework.support.inject.ViewInject;
import com.zhan.framework.ui.fragment.ABaseFragment;

/**
 * 作者：蒲柯柯 on 2016/10/21 16:55
 * 邮箱：983198505@qq.com
 * 介绍:
 */
public class CommonQueryInfoFragment extends ABaseFragment{
    private final static String ARG_KEY = "common_query_info";
    private final static String ARG_KEY_TITLE = "common_query_info_title";

    @ViewInject(id = R.id.wb_info)
    WebView mWebView;

    private int mHelpId;
    private String mTitle;

    @Override
    protected int inflateContentView() {
        return R.layout.frag_information_main_info;
    }

    public static void launch(FragmentActivity activity, int helpId, String title) {
        FragmentArgs args = new FragmentArgs();
        args.add(ARG_KEY, helpId);
        args.add(ARG_KEY_TITLE, title);
        FragmentContainerActivity.launch(activity, CommonQueryInfoFragment.class, args);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mHelpId = savedInstanceState == null ? (int) getArguments().getSerializable(ARG_KEY) : (int) savedInstanceState.getSerializable(ARG_KEY);
        mTitle = savedInstanceState == null ? (String) getArguments().getSerializable(ARG_KEY_TITLE) : (String) savedInstanceState.getSerializable(ARG_KEY_TITLE);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable(ARG_KEY, mHelpId);
        outState.putSerializable(ARG_KEY_TITLE, mTitle);
    }

    @Override
    protected void layoutInit(LayoutInflater inflater, Bundle savedInstanceSate) {
        super.layoutInit(inflater, savedInstanceSate);
        getActivity().setTitle(mTitle);
    }


    @Override
    public void requestData() {
        HttpRequestParams requestParams = new HttpRequestParams();
        requestParams.put("helpId", mHelpId);
        startFormRequest(ApiUrls.GET_HELPS_INFO, requestParams, new BaseHttpRequestTask<CommonQueryInfoResponseBean>() {
            @Override
            public CommonQueryInfoResponseBean parseResponseToResult(String content) {
                return Tools.parseJson(content, CommonQueryInfoResponseBean.class);
            }

            @Override
            public String verifyResponseResult(String content) {
                return Tools.verifyResponseResult(content);
            }

            @Override
            protected void onSuccess(CommonQueryInfoResponseBean result) {
                super.onSuccess(result);
                if(result!=null&&result.getResult()!=null){
                    getActivity().setTitle(result.getResult().getTitle());
                    mWebView.getSettings().setDefaultTextEncodingName("utf-8");
                    mWebView.loadData(result.getResult().getContent(), "text/html;charset=UTF-8", "utf-8");
                }
            }
        }, HttpRequestUtils.RequestType.POST);
    }

}
