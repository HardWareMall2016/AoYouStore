package com.zhan.aoyoustore.ui.fragment.mine;

import android.os.Bundle;
import android.view.LayoutInflater;

import com.zhan.aoyoustore.R;
import com.zhan.framework.ui.fragment.ABaseFragment;

/**
 * 作者：蒲柯柯 on 2016/10/21 16:55
 * 邮箱：983198505@qq.com
 * 介绍:会员中心
 */
public class MineMain extends ABaseFragment {


    //Tools
    private LayoutInflater mInflater;

    @Override
    protected int inflateContentView() {
        return R.layout.frag_mine;
    }

    @Override
    protected void layoutInit(LayoutInflater inflater, Bundle savedInstanceSate) {
        super.layoutInit(inflater, savedInstanceSate);
        mInflater = inflater;
        getActivity().setTitle("会员中心");
    }


}
