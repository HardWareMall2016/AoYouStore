package com.zhan.aoyoustore.ui.fragment.information;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import com.zhan.aoyoustore.R;
import com.zhan.aoyoustore.beans.GetArticleCategoryResponseBean;
import com.zhan.aoyoustore.network.ApiUrls;
import com.zhan.aoyoustore.utils.Tools;
import com.zhan.framework.network.HttpRequestUtils;
import com.zhan.framework.support.adapter.ABaseAdapter;
import com.zhan.framework.support.inject.ViewInject;
import com.zhan.framework.ui.fragment.APullToRefreshListFragment;

import java.util.ArrayList;
import java.util.List;

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
public class InformationMain extends APullToRefreshListFragment<InformationMain.ArticleCategory> {

    @Override
    protected int inflateContentView() {
        return R.layout.frag_information_main;
    }

    @Override
    protected ABaseAdapter.AbstractItemView<ArticleCategory> newItemView() {
        return new CategoryItemView();
    }

    @Override
    protected void requestData(RefreshMode mode) {
        startFormRequest(ApiUrls.GET_ARTICLE_CATEGORYS, null, new PagingTask<GetArticleCategoryResponseBean>(mode) {
            @Override
            public GetArticleCategoryResponseBean parseResponseToResult(String content) {
                return Tools.parseJson(content, GetArticleCategoryResponseBean.class);
            }

            @Override
            public String verifyResponseResult(String content) {
                return Tools.verifyResponseResult(content);
            }

            @Override
            protected List<ArticleCategory> parseResult(GetArticleCategoryResponseBean productBean) {
                List<ArticleCategory> products=new ArrayList<>();
                for(GetArticleCategoryResponseBean.ResultEntity.CategorysEntity bean:productBean.getResult().getCategorys()){
                    ArticleCategory articleCategory=new ArticleCategory();
                    articleCategory.categoryId=bean.getCategoryId();
                    articleCategory.categoryName=bean.getCategoryName();
                    articleCategory.categoryContent=bean.getCategoryContent();
                    products.add(articleCategory);
                }

                return products;
            }
        }, HttpRequestUtils.RequestType.POST);
    }


    private class CategoryItemView extends ABaseAdapter.AbstractItemView<ArticleCategory>{
        @ViewInject(id = R.id.icon)
        ImageView mViewIcon ;

        @ViewInject(id = R.id.title)
        TextView mViewTitle ;

        @ViewInject(id = R.id.summary)
        TextView mViewSummary ;

        @Override
        public int inflateViewId() {
            return R.layout.common_list_item1;
        }

        @Override
        public void bindingView(View convertView) {
            super.bindingView(convertView);
            mViewIcon.setVisibility(View.GONE);
        }

        @Override
        public void bindingData(View convertView, ArticleCategory data) {
            Tools.setTextView(mViewTitle,data.categoryName);
            Tools.setTextView(mViewSummary,data.categoryContent);
        }
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        ArticleCategory data=getAdapterItems().get((int)id);
        ArticleListFragment.launch(getActivity(),data.categoryId,data.categoryName);
    }

    public class ArticleCategory {
        int categoryId;
        String categoryName;
        String categoryContent;
    }
}
