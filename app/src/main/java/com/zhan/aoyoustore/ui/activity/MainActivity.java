package com.zhan.aoyoustore.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.text.TextUtils;
import android.view.View;
import android.widget.LinearLayout;

import com.zhan.aoyoustore.R;
import com.zhan.framework.ui.activity.BaseActivity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity {

    public final static String EXT_KEY_SHOW_PAGE = "show_page";

    private PageFactory mPageFactory;

    private List<Page> mPageList = new ArrayList<>();

    private Page mCurPage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        showActionbar(false);
        //setOverlay(true);
        setContentView(R.layout.activity_main);
        initPages();
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        if ((Intent.FLAG_ACTIVITY_CLEAR_TOP & intent.getFlags()) != 0) {
            String showPage = intent.getStringExtra(EXT_KEY_SHOW_PAGE);
            if (TextUtils.isEmpty(showPage)) {
                //退出跳转到主页,强制刷新主页
                showFirstPage();
            } else {
                showPage(showPage);
            }
        }
    }

    private void initPages() {
        LinearLayout mBottomContainer = (LinearLayout) findViewById(R.id.bottom_bar);
        mBottomContainer.removeAllViews();

        mPageFactory = new PageFactory(this);
        mPageList = mPageFactory.createPages();

        for (Page page : mPageList) {
            mBottomContainer.addView(page.bottomTitle);
            page.bottomTitle.setOnClickListener(mOnBottomButtonClickListener);
        }

        String showPage = getIntent().getStringExtra(EXT_KEY_SHOW_PAGE);
        if (TextUtils.isEmpty(showPage)) {
            showPage = mPageList.get(0).TAG;
        }
        showPage(showPage);
    }

    private void showPage(String tag) {
        if(!mPageFactory.showOrLauncherLoginPage(tag)){
            return;
        }

        for (Page page : mPageList) {
            if (page.TAG.equals(tag)) {
                if (mCurPage != page) {
                    FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                    transaction.replace(R.id.content, page.pageFragment);
                    transaction.commit();
                    page.bottomTitle.setCompoundDrawablesWithIntrinsicBounds(0, page.selectedDrawableId, 0, 0);
                    page.bottomTitle.setTextColor(getResources().getColor(R.color.yellow));
                }
                mCurPage = page;
            } else {
                page.bottomTitle.setCompoundDrawablesWithIntrinsicBounds(0, page.normalDrawableId, 0, 0);
                page.bottomTitle.setTextColor(getResources().getColor(R.color.text_color_content));
            }
        }
    }

    private View.OnClickListener mOnBottomButtonClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Page page = (Page) view.getTag();
            showPage(page.TAG);
        }
    };

    private void showFirstPage() {
        showPage(mPageList.get(0).TAG);
    }
}
