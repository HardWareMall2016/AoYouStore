package com.zhan.aoyoustore.ui.fragment.goods;

import android.app.Activity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.zhan.aoyoustore.R;
import com.zhan.aoyoustore.beans.SearchKeyBean;
import com.zhan.framework.cache.CacheUtility;
import com.zhan.framework.component.container.FragmentContainerActivity;
import com.zhan.framework.support.adapter.ABaseAdapter;
import com.zhan.framework.support.inject.ViewInject;
import com.zhan.framework.ui.activity.BaseActivity;
import com.zhan.framework.ui.fragment.ABaseFragment;
import com.zhan.framework.utils.ToastUtils;

import org.aisen.orm.extra.Extra;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者：伍岳 on 2016/7/12 17:05
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
public class SearchProductFragment extends ABaseFragment implements AdapterView.OnItemClickListener {

    @ViewInject(id = R.id.history_list)
    ListView mListViewHistory;

    @ViewInject(id = R.id.search_btn, click = "OnClick")
    ImageView mViewSearchBtn;

    @ViewInject(id = R.id.search_content)
    TextView mViewSearchContent;

    //data
    private ArrayList<SearchKeyBean> mHistoryData = new ArrayList<>();

    //tools
    private HistoryAdapter mAdapter;

    public static void launch(Activity activity) {
        FragmentContainerActivity.launch(activity, SearchProductFragment.class, null);
    }

    @Override
    public void onPrepareSetContentView(BaseActivity activity) {
        activity.showActionbar(false);
    }

    @Override
    protected int inflateContentView() {
        return R.layout.frag_search_product;
    }

    @Override
    protected void layoutInit(LayoutInflater inflater, Bundle savedInstanceSate) {
        super.layoutInit(inflater, savedInstanceSate);

        mHistoryData.clear();
        Extra extra = createExtraKey();
        List<SearchKeyBean> dataList = CacheUtility.findCacheData(extra, SearchKeyBean.class);
        if (dataList != null) {
            mHistoryData.addAll(dataList);
        }

        if (mHistoryData.size() > 0) {
            final View footView = inflater.inflate(R.layout.list_item_clear_history, null);
            mListViewHistory.addFooterView(footView);

            footView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    CacheUtility.clearCacheData(SearchKeyBean.class);
                    mHistoryData.clear();
                    mListViewHistory.removeFooterView(footView);
                    mAdapter.notifyDataSetChanged();
                }
            });
        }

        mAdapter = new HistoryAdapter(getActivity());
        mListViewHistory.setAdapter(mAdapter);
        mListViewHistory.setOnItemClickListener(this);
    }

    void OnClick(View v) {
        switch (v.getId()) {
            case R.id.search_btn:
                String keyWord = mViewSearchContent.getText().toString().trim();
                if (TextUtils.isEmpty(keyWord)) {
                    ToastUtils.toast("请输入搜索内容");
                } else {
                    saveToHistory(keyWord);
                    startSearch(keyWord);
                }
                break;
        }
    }

    private void saveToHistory(String searchKey) {
        Extra extra = createExtraKey();
        boolean exists = false;
        List<SearchKeyBean> dataList = CacheUtility.findCacheData(extra, SearchKeyBean.class);
        if (dataList != null) {
            for (SearchKeyBean data : dataList) {
                if (data.getSearchKey().equals(searchKey)) {
                    exists = true;
                    break;
                }
            }
            if (!exists) {
                SearchKeyBean bean = new SearchKeyBean();
                bean.setSearchKey(searchKey);
                dataList.add(0,bean);
                CacheUtility.putCacheData(extra, dataList, SearchKeyBean.class);
            }
        }
    }

    private Extra createExtraKey(){
        Extra extra = new Extra();
        extra.setOwner("all");
        extra.setKey("product_keyword");
        return extra;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        SearchKeyBean data=mHistoryData.get(position);
        startSearch(data.getSearchKey());
    }

    private void startSearch(String searchKey){
        ProductListFragment.launchSearchMode(getActivity(),searchKey);
        getActivity().finish();
    }

    private class HistoryAdapter extends ABaseAdapter<SearchKeyBean> {

        public HistoryAdapter(Activity context) {
            super(mHistoryData, context);
        }

        @Override
        protected AbstractItemView<SearchKeyBean> newItemView() {
            return new HistoryItemView();
        }
    }

    private class HistoryItemView extends ABaseAdapter.AbstractItemView<SearchKeyBean> {
        @ViewInject(id = R.id.list_item)
        TextView mViewTitle;

        @Override
        public int inflateViewId() {
            return R.layout.list_item_search_history;
        }

        @Override
        public void bindingData(View convertView, SearchKeyBean data) {
            mViewTitle.setText(data.getSearchKey());
        }
    }

}
