package com.zhan.aoyoustore.ui.fragment.information;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.TextView;

import com.zhan.aoyoustore.R;
import com.zhan.aoyoustore.beans.getArticlesByCategoryIdResponseBean;
import com.zhan.aoyoustore.network.ApiUrls;
import com.zhan.aoyoustore.utils.Tools;
import com.zhan.framework.component.container.FragmentArgs;
import com.zhan.framework.component.container.FragmentContainerActivity;
import com.zhan.framework.network.HttpRequestParams;
import com.zhan.framework.network.HttpRequestUtils;
import com.zhan.framework.support.adapter.ABaseAdapter;
import com.zhan.framework.support.inject.ViewInject;
import com.zhan.framework.ui.fragment.APullToRefreshListFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者：伍岳 on 2016/7/12 15:38
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
public class ArticleListFragment  extends APullToRefreshListFragment<ArticleListFragment.Article> {

    private final static String ARG_KEY="arg_key";
    private final static String ARG_KEY2="arg_key2";

    //Data
    private int mCategoryId;
    private String mTitle;

    public static void launch(Activity activity,int categoryId,String title){
        FragmentArgs args = new FragmentArgs();
        args.add(ARG_KEY, categoryId);
        args.add(ARG_KEY2, title);
        FragmentContainerActivity.launch(activity, ArticleListFragment.class, args);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mTitle = savedInstanceState == null ? (String) getArguments().getSerializable(ARG_KEY2) : (String) savedInstanceState.getSerializable(ARG_KEY2);
        mCategoryId = savedInstanceState == null ? (int) getArguments().getSerializable(ARG_KEY) : (int) savedInstanceState.getSerializable(ARG_KEY);
    }
    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable(ARG_KEY, mCategoryId);
        outState.putSerializable(ARG_KEY2, mTitle);
    }

    @Override
    protected void layoutInit(LayoutInflater inflater, Bundle savedInstanceSate) {
        super.layoutInit(inflater, savedInstanceSate);
        getActivity().setTitle(mTitle);
    }

    @Override
    protected ABaseAdapter.AbstractItemView<Article> newItemView() {
        return new ArticleItemView();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Article data=getAdapterItems().get((int)id);
        ArticleInfoFragment.launch(getActivity(),data.articleId);
    }

    @Override
    protected void requestData(RefreshMode mode) {
        HttpRequestParams requestParams=new HttpRequestParams();
        requestParams.put("categoryId",mCategoryId);

        startFormRequest(ApiUrls.GET_ARTICLES_BY_CATEGORY_ID, requestParams, new PagingTask<getArticlesByCategoryIdResponseBean>(mode) {
            @Override
            public getArticlesByCategoryIdResponseBean parseResponseToResult(String content) {
                return Tools.parseJson(content, getArticlesByCategoryIdResponseBean.class);
            }

            @Override
            public String verifyResponseResult(String content) {
                return Tools.verifyResponseResult(content);
            }

            @Override
            protected List<Article> parseResult(getArticlesByCategoryIdResponseBean productBean) {

                if(productBean.getResult()!=null){
                    getActivity().setTitle(productBean.getResult().getCategoryName());
                }

                List<Article> dataList = new ArrayList<>();
                if(productBean.getResult()!=null&&productBean.getResult().getArticles()!=null){
                    for (getArticlesByCategoryIdResponseBean.ResultEntity.ArticlesEntity bean : productBean.getResult().getArticles()) {
                        Article data = new Article();
                        data.articleId = bean.getArticleId();
                        data.title = bean.getTitle();
                        data.descriptions = bean.getDescriptions();
                        data.createdAt= Long.parseLong(bean.getCreatedAt());
                        dataList.add(data);
                    }
                }
                return dataList;
            }
        }, HttpRequestUtils.RequestType.POST);
    }


    private class ArticleItemView extends ABaseAdapter.AbstractItemView<Article>{

        @ViewInject(id = R.id.title)
        TextView mViewTitle ;

        @ViewInject(id = R.id.summary)
        TextView mViewSummary ;

        @ViewInject(id = R.id.time)
        TextView mViewTime ;

        @Override
        public int inflateViewId() {
            return R.layout.list_item_article;
        }

        @Override
        public void bindingData(View convertView, Article data) {
            Tools.setTextView(mViewTitle,data.title);
            Tools.setTextView(mViewSummary,data.descriptions);
            mViewTime.setText(Tools.parseTime(data.createdAt*1000));
        }
    }

    public class Article{
        int articleId;
        String title;
        String descriptions;
        long createdAt;
    }

}
