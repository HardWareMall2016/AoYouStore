package com.zhan.aoyoustore.ui.fragment.commonquery;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import com.zhan.aoyoustore.R;
import com.zhan.aoyoustore.beans.GetArticleCategoryResponseBean;
import com.zhan.aoyoustore.network.ApiUrls;
import com.zhan.aoyoustore.utils.Tools;
import com.zhan.framework.network.HttpRequestParams;
import com.zhan.framework.network.HttpRequestUtils;
import com.zhan.framework.support.adapter.ABaseAdapter;
import com.zhan.framework.support.inject.ViewInject;
import com.zhan.framework.ui.fragment.APullToRefreshListFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者：蒲柯柯 on 2016/7/9 21:46
 * 邮箱：983198505@qq.com
 * //
 */
public class CommonQueryMain extends APullToRefreshListFragment<CommonQueryMain.ArticleCategory> {

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

        startFormRequest(ApiUrls.GET_HELPS_BY_CATEGORYS_ID, null, new PagingTask<GetArticleCategoryResponseBean>(mode) {
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
                for(GetArticleCategoryResponseBean.ResultBean.HelpsBean helpsBean:productBean.getResult().getHelps()){
                    ArticleCategory articleCategory=new ArticleCategory();
                    articleCategory.helpId = helpsBean.getHelpId();
                    articleCategory.title = helpsBean.getTitle();
                    articleCategory.descriptions = helpsBean.getDescriptions();
                    articleCategory.createdAt = helpsBean.getCreatedAt();
                    products.add(articleCategory);
                }

                return products;
            }
        }, HttpRequestUtils.RequestType.POST);
    }


    private class CategoryItemView extends ABaseAdapter.AbstractItemView<ArticleCategory>{
        @ViewInject(id = R.id.title)
        TextView mViewTitle ;

        @Override
        public int inflateViewId() {
            return R.layout.common_query_list_item;
        }

        @Override
        public void bindingView(View convertView) {
            super.bindingView(convertView);
           // mViewIcon.setVisibility(View.GONE);
        }

        @Override
        public void bindingData(View convertView, ArticleCategory data) {
            Tools.setTextView(mViewTitle,data.title);
            //Tools.setTextView(mViewSummary,data.categoryContent);
        }
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        ArticleCategory data=getAdapterItems().get((int)id);
        //ArticleListFragment.launch(getActivity(),data.categoryId,data.categoryName);
        CommonQueryInfoFragment.launch(getActivity(),data.helpId,data.title);
    }

    public class ArticleCategory {
        int helpId;
        String title;
        String descriptions;
        String createdAt;

    }
}
