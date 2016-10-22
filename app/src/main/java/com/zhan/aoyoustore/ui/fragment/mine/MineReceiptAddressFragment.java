package com.zhan.aoyoustore.ui.fragment.mine;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;

import com.zhan.aoyoustore.R;
import com.zhan.framework.component.container.FragmentContainerActivity;
import com.zhan.framework.ui.fragment.ABaseFragment;

/**
 * Created by ${keke} on 16/10/21.
 */
public class MineReceiptAddressFragment extends ABaseFragment {

    private LayoutInflater mInflater;

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
    }
}
