package com.zhan.aoyoustore.ui.fragment.commonquery;

import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebView;

import com.zhan.aoyoustore.R;
import com.zhan.aoyoustore.beans.GetArticleInfoByIdResponseBean;
import com.zhan.aoyoustore.network.ApiUrls;
import com.zhan.aoyoustore.utils.Tools;
import com.zhan.framework.component.container.FragmentArgs;
import com.zhan.framework.component.container.FragmentContainerActivity;
import com.zhan.framework.network.HttpRequestParams;
import com.zhan.framework.network.HttpRequestUtils;
import com.zhan.framework.support.inject.ViewInject;
import com.zhan.framework.ui.fragment.ABaseFragment;

/**
 * 作者：伍岳 on 2016/7/12 16:05
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
public class ArticleInfoFragment extends ABaseFragment {

    private final static String ARG_KEY = "arg_key";

    @ViewInject(id = R.id.web_view)
    WebView mWebView;

    //Data
    private int mArticleId;

    public static void launch(Activity activity, int articleId) {
        FragmentArgs args = new FragmentArgs();
        args.add(ARG_KEY, articleId);
        FragmentContainerActivity.launch(activity, ArticleInfoFragment.class, args);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mArticleId = savedInstanceState == null ? (int) getArguments().getSerializable(ARG_KEY) : (int) savedInstanceState.getSerializable(ARG_KEY);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable(ARG_KEY, mArticleId);
    }

    @Override
    protected int inflateContentView() {
        return R.layout.frag_article_info;
    }

    @Override
    public void requestData() {
        HttpRequestParams requestParams = new HttpRequestParams();
        requestParams.put("articleId", mArticleId);
        startFormRequest(ApiUrls.GET_ARTICLE_INFO, requestParams, new BaseHttpRequestTask<GetArticleInfoByIdResponseBean>() {
            @Override
            public GetArticleInfoByIdResponseBean parseResponseToResult(String content) {
                return Tools.parseJson(content, GetArticleInfoByIdResponseBean.class);
            }

            @Override
            public String verifyResponseResult(String content) {
                return Tools.verifyResponseResult(content);
            }

            @Override
            protected void onSuccess(GetArticleInfoByIdResponseBean result) {
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
