package com.zhan.aoyoustore.ui.fragment.goods;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.zhan.aoyoustore.R;
import com.zhan.aoyoustore.beans.GetShopCategoriesResponseBean;
import com.zhan.aoyoustore.network.ApiUrls;
import com.zhan.aoyoustore.ui.widget.PinnedHeaderExpandableListView;
import com.zhan.aoyoustore.utils.Tools;
import com.zhan.framework.component.container.FragmentArgs;
import com.zhan.framework.component.container.FragmentContainerActivity;
import com.zhan.framework.network.HttpRequestParams;
import com.zhan.framework.network.HttpRequestUtils;
import com.zhan.framework.support.inject.InjectUtility;
import com.zhan.framework.support.inject.ViewInject;
import com.zhan.framework.ui.fragment.ABaseFragment;

import java.util.LinkedList;
import java.util.List;

/**
 * 作者：伍岳 on 2016/7/10 22:43
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
public class ShopCategoryListFragment extends ABaseFragment implements PinnedHeaderExpandableListView.OnHeaderUpdateListener {
    private final static String ARG_KEY_CATEGORY_ID = "categoryId";


    @ViewInject(id = R.id.shop_category_list)
    PinnedHeaderExpandableListView mShopCategoryList;

    //data
    private int mCategoryId;
    private List<GroupDataInfo> mDataList = new LinkedList<>();


    //tools
    private LayoutInflater mInflater;
    private ExpandableAdapter mAdapter;


    public static void launch(Activity activity, int categoryId) {
        FragmentArgs args = new FragmentArgs();
        args.add(ARG_KEY_CATEGORY_ID, categoryId);
        FragmentContainerActivity.launch(activity, ShopCategoryListFragment.class, args);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mCategoryId = savedInstanceState == null ? (int) getArguments().getSerializable(ARG_KEY_CATEGORY_ID) : (int) savedInstanceState.getSerializable(ARG_KEY_CATEGORY_ID);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable(ARG_KEY_CATEGORY_ID, mCategoryId);
    }

    @Override
    protected int inflateContentView() {
        return R.layout.frag_shop_category_list;
    }

    @Override
    protected void layoutInit(LayoutInflater inflater, Bundle savedInstanceSate) {
        super.layoutInit(inflater, savedInstanceSate);
        mInflater = inflater;
        mAdapter = new ExpandableAdapter();
        mShopCategoryList.setAdapter(mAdapter);
        mShopCategoryList.setOnHeaderUpdateListener(this);
        mShopCategoryList.setGroupIndicator(null);
        mShopCategoryList.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                ShopCategory shopCategory = mDataList.get(groupPosition).staffInfoList.get(childPosition);
                if (shopCategory.proCount > 0) {
                    ProductListFragment.launch(getActivity(), shopCategory.cid);
                    return true;
                }
                return false;
            }
        });
    }

    @Override
    public View getPinnedHeader() {
        View headerView = mInflater.inflate(R.layout.list_item_shop_category_list_group, null);
        headerView.setLayoutParams(new AbsListView.LayoutParams(AbsListView.LayoutParams.MATCH_PARENT, AbsListView.LayoutParams.MATCH_PARENT));
        return headerView;
    }

    @Override
    public void updatePinnedHeader(View headerView, int firstVisibleGroupPos) {
        GroupDataInfo firstVisibleGroup = (GroupDataInfo) mAdapter.getGroup(firstVisibleGroupPos);
        GroupHolder holder = new GroupHolder(headerView);
        holder.bindingData(headerView, firstVisibleGroup);
    }


    @Override
    public void requestData() {
        HttpRequestParams requestParams = new HttpRequestParams();
        requestParams.put("categoryId", mCategoryId);

        startFormRequest(ApiUrls.GET_SHOP_CATEGORIES, null, new BaseHttpRequestTask<GetShopCategoriesResponseBean>() {
            @Override
            public GetShopCategoriesResponseBean parseResponseToResult(String content) {
                return Tools.parseJson(content, GetShopCategoriesResponseBean.class);
            }

            @Override
            public String verifyResponseResult(String result) {
                return Tools.verifyResponseResult(result);
            }

            @Override
            protected void onSuccess(GetShopCategoriesResponseBean result) {
                super.onSuccess(result);
                mDataList.clear();
                for (GetShopCategoriesResponseBean.ResultEntity.CategoryEntity categoryBean : result.getResult().getCategory()) {
                    GroupDataInfo groupDataInfo = new GroupDataInfo();
                    groupDataInfo.cid = categoryBean.getCid();
                    groupDataInfo.icon = categoryBean.getIcon();
                    groupDataInfo.descriptions = categoryBean.getDescriptions();
                    groupDataInfo.hasChildren = categoryBean.isHasChildren();
                    groupDataInfo.name = categoryBean.getName();
                    mDataList.add(groupDataInfo);
                    if (categoryBean.isHasChildren()) {
                        for (GetShopCategoriesResponseBean.ResultEntity.CategoryEntity.SubsEntity subsEntity : categoryBean.getSubs()) {
                            ShopCategory shopCategory = new ShopCategory();
                            shopCategory.cid = subsEntity.getCid();
                            shopCategory.icon = subsEntity.getIcon();
                            shopCategory.name = subsEntity.getName();
                            shopCategory.proCount = subsEntity.getProCount();
                            shopCategory.descriptions = subsEntity.getDescriptions();
                            groupDataInfo.staffInfoList.add(shopCategory);
                        }
                    }
                }

                mAdapter.notifyDataSetChanged();
                int selectedPos = 0;
                for (int i = 0; i < mDataList.size(); i++) {
                    //展开指定
                    if (mDataList.get(i).cid == mCategoryId) {
                        mShopCategoryList.expandGroup(i);
                        selectedPos = i;
                    } else {
                        mShopCategoryList.collapseGroup(i);
                    }
                }
                mShopCategoryList.setSelectedGroup(selectedPos);
            }

        }, HttpRequestUtils.RequestType.POST);
    }

    public class ExpandableAdapter extends BaseExpandableListAdapter {

        @Override
        public int getGroupCount() {
            return mDataList.size();
        }

        @Override
        public int getChildrenCount(int groupPosition) {
            return mDataList.get(groupPosition).staffInfoList.size();
        }

        @Override
        public Object getGroup(int groupPosition) {
            return mDataList.get(groupPosition);
        }

        @Override
        public Object getChild(int groupPosition, int childPosition) {
            return mDataList.get(groupPosition).staffInfoList.get(childPosition);
        }

        @Override
        public long getGroupId(int groupPosition) {
            return groupPosition;
        }

        @Override
        public long getChildId(int groupPosition, int childPosition) {
            return childPosition;
        }

        @Override
        public boolean hasStableIds() {
            return false;
        }

        @Override
        public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
            GroupHolder holder;
            if (convertView == null) {
                convertView = mInflater.inflate(R.layout.list_item_shop_category_list_group, null);
                holder = new GroupHolder(convertView);
                convertView.setTag(holder);
            } else {
                holder = (GroupHolder) convertView.getTag();
            }
            mDataList.get(groupPosition).isExpanded = isExpanded;
            holder.bindingData(convertView, mDataList.get(groupPosition));

            return convertView;
        }

        @Override
        public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
            ChildHolder holder;
            if (convertView == null) {
                convertView = mInflater.inflate(R.layout.list_item_shop_category_list, null);
                holder = new ChildHolder(convertView);
                convertView.setTag(holder);
            } else {
                holder = (ChildHolder) convertView.getTag();
            }
            if (childPosition % 2 == 0) {
                convertView.setBackgroundResource(R.drawable.bg_gray_selector);
            } else {
                convertView.setBackgroundResource(R.drawable.bg_white_selector);
            }
            holder.bindingData(convertView, mDataList.get(groupPosition).staffInfoList.get(childPosition));
            return convertView;
        }

        @Override
        public boolean isChildSelectable(int groupPosition, int childPosition) {
            return true;
        }
    }

    private class GroupHolder {
        @ViewInject(id = R.id.icon)
        ImageView mImgIcon;

        @ViewInject(id = R.id.name)
        TextView mViewName;

        @ViewInject(id = R.id.arrow)
        ImageView mViewArrow;

        public GroupHolder(View convertView) {
            InjectUtility.initInjectedView(this, convertView);
        }

        public void bindingData(View convertView, GroupDataInfo data) {
            Tools.setTextView(mViewName, data.name);
            ImageLoader.getInstance().displayImage(data.icon, mImgIcon, Tools.buildDisplayGoodsImgOptions());
            if (data.isExpanded) {
                mViewArrow.setImageResource(R.drawable.arrow_up_small);
            } else {
                mViewArrow.setImageResource(R.drawable.arrow_down_small);
            }
        }
    }

    private class ChildHolder {
        @ViewInject(id = R.id.name)
        TextView mViewName;

        /*@ViewInject(id = R.id.descriptions)
        TextView mViewDescriptions ;*/

        @ViewInject(id = R.id.proCount)
        TextView mViewProCount;

        public ChildHolder(View convertView) {
            InjectUtility.initInjectedView(this, convertView);
        }

        public void bindingData(View convertView, ShopCategory data) {
            Tools.setTextView(mViewName, data.name);
            //Tools.setTextView(mViewDescriptions,data.descriptions);
            Tools.setTextView(mViewProCount, String.valueOf(data.proCount));
        }
    }

    private class GroupDataInfo {
        int cid;
        String name;
        String icon;
        boolean hasChildren;
        String descriptions;
        boolean isExpanded;
        List<ShopCategory> staffInfoList = new LinkedList<>();
    }

    public class ShopCategory {
        int cid;
        String name;
        String icon;
        String descriptions;
        int proCount;
    }

}
