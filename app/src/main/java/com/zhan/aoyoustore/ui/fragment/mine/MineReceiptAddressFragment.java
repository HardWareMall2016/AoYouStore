package com.zhan.aoyoustore.ui.fragment.mine;

import android.app.Activity;
import android.os.Bundle;
import android.provider.SyncStateContract;
import android.support.v4.app.FragmentActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.zhan.aoyoustore.R;
import com.zhan.aoyoustore.base.UserInfo;
import com.zhan.aoyoustore.beans.GetProductDetailResponseBean;
import com.zhan.aoyoustore.beans.MineReceiptAddressResponse;
import com.zhan.aoyoustore.network.ApiUrls;
import com.zhan.aoyoustore.ui.fragment.login.LoginFragment;
import com.zhan.aoyoustore.utils.Tools;
import com.zhan.framework.component.container.FragmentContainerActivity;
import com.zhan.framework.network.HttpRequestParams;
import com.zhan.framework.network.HttpRequestUtils;
import com.zhan.framework.support.adapter.ABaseAdapter;
import com.zhan.framework.support.inject.ViewInject;
import com.zhan.framework.ui.fragment.ABaseFragment;
import com.zhan.framework.ui.fragment.APullToRefreshListFragment;
import com.zhan.framework.ui.fragment.ARefreshFragment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by ${keke} on 16/10/21.
 */
public class MineReceiptAddressFragment extends APullToRefreshListFragment<MineReceiptAddressResponse.AddressInfo> {

    private final static int REQUEST_CODE = 100;

    @ViewInject(id = R.id.submit, click = "OnClick")
    private Button mBtnSubmit;

    private PullToRefreshListView mRefreshListView;

    private LayoutInflater mInflater;
    private TextView mActionBarMenu;
    private boolean mEditMode = false;

    @Override
    protected int inflateContentView() {
        return R.layout.frag_mine_receipt_address;
    }

    public static void launch(FragmentActivity activity) {
        FragmentContainerActivity.launch(activity, MineReceiptAddressFragment.class, null);
    }

    @Override
    protected void layoutInit(LayoutInflater inflater, Bundle savedInstanceSate) {
        super.layoutInit(inflater, savedInstanceSate);
        mInflater = inflater;
        getActivity().setTitle("管理收货地址");
        mEditMode = false;
        refreshViewByEditMode();
    }

    @Override
    public void onPrepareActionbarMenu(TextView menu, Activity activity) {
        menu.setText("编辑");
        mActionBarMenu = menu;
        mActionBarMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mEditMode = !mEditMode;
                refreshViewByEditMode();
            }
        });
    }

    private void refreshViewByEditMode() {
        if (mEditMode) {
            mActionBarMenu.setText("完成");
            mBtnSubmit.setText("确认删除");
        } else {
            mActionBarMenu.setText("编辑");
            mBtnSubmit.setText("新增收货地址");
        }
        notifyDataSetChanged();
    }


    @Override
    protected void setInitPullToRefresh(ListView listView, PullToRefreshListView pullToRefreshListView, Bundle savedInstanceState) {
        pullToRefreshListView.setMode(PullToRefreshBase.Mode.PULL_FROM_START);
        mRefreshListView = pullToRefreshListView;
    }

    @Override
    protected ABaseAdapter.AbstractItemView<MineReceiptAddressResponse.AddressInfo> newItemView() {
        return new AddressInfoItemView();
    }

    class AddressInfoItemView extends ABaseAdapter.AbstractItemView<MineReceiptAddressResponse.AddressInfo> {
        @ViewInject(id = R.id.name)
        private TextView mTvName;

        @ViewInject(id = R.id.phone)
        private TextView mTvPhone;

        @ViewInject(id = R.id.address)
        private TextView mTvAddress;

        @ViewInject(id = R.id.arrow_right)
        private ImageView mImgArrowRight;

        @ViewInject(id = R.id.select)
        private CheckBox mCkSelected;

        @Override
        public int inflateViewId() {
            return R.layout.address_list_item;
        }

        @Override
        public void bindingData(View convertView, final MineReceiptAddressResponse.AddressInfo data) {
            mTvName.setText(data.getReceiverPerson());
            mTvPhone.setText(data.getReceiverPhone());
            String address = data.getAddress();
            if (data.isIsDefault()) {
                address = "[默认]" + address;
            }
            mTvAddress.setText(address);

            if (mEditMode) {
                mImgArrowRight.setVisibility(View.GONE);
                mCkSelected.setVisibility(View.VISIBLE);
                mCkSelected.setChecked(data.isSelected());
            } else {
                mImgArrowRight.setVisibility(View.VISIBLE);
                mCkSelected.setVisibility(View.GONE);
            }
        }
    }

    @Override
    protected void requestData(RefreshMode mode) {
        String token = UserInfo.getCurrentUser().getToken();
        if (TextUtils.isEmpty(token)) {
            LoginFragment.launch(getActivity());
            return;
        }
        HttpRequestParams requestParams = new HttpRequestParams();
        requestParams.put("token", token);
        startFormRequest(ApiUrls.GET_ADDRE_LIST, requestParams, new BaseHttpRequestTask<MineReceiptAddressResponse>() {
            @Override
            public MineReceiptAddressResponse parseResponseToResult(String content) {
                return Tools.parseJson(content, MineReceiptAddressResponse.class);
            }

            @Override
            public String verifyResponseResult(String result) {
                return Tools.verifyResponseResult(result);
            }

            @Override
            protected void onSuccess(MineReceiptAddressResponse result) {
                super.onSuccess(result);
                //populateView(result);
            }
        }, HttpRequestUtils.RequestType.POST);
    }

    @Override
    protected void configRefresh(RefreshConfig config) {
        config.minResultSize = 10;
    }

    @Override
    public int getFirstPageIndex() {
        return 1;
    }


}
