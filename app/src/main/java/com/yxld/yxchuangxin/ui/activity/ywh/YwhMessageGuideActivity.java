package com.yxld.yxchuangxin.ui.activity.ywh;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.shizhefei.view.indicator.IndicatorViewPager;
import com.shizhefei.view.indicator.ScrollIndicatorView;
import com.shizhefei.view.indicator.slidebar.ColorBar;
import com.shizhefei.view.indicator.slidebar.ScrollBar;
import com.yxld.yxchuangxin.R;
import com.yxld.yxchuangxin.application.AppConfig;
import com.yxld.yxchuangxin.base.BaseActivity;
import com.yxld.yxchuangxin.ui.activity.ywh.component.DaggerYwhMessageGuideComponent;
import com.yxld.yxchuangxin.ui.activity.ywh.contract.YwhMessageGuideContract;
import com.yxld.yxchuangxin.ui.activity.ywh.module.YwhMessageGuideModule;
import com.yxld.yxchuangxin.ui.activity.ywh.presenter.YwhMessageGuidePresenter;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author William
 * @Package com.yxld.yxchuangxin.ui.activity.ywh
 * @Description: 业委会信息指导
 * @date 2018/11/09 16:59:26
 */

public class YwhMessageGuideActivity extends BaseActivity implements YwhMessageGuideContract.View {

    @Inject
    YwhMessageGuidePresenter mPresenter;
    @BindView(R.id.tab_indicator)
    ScrollIndicatorView tabIndicator;
    @BindView(R.id.tab_viewPager)
    ViewPager tabViewPager;


    private IndicatorViewPager indicatorViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initView() {
        setContentView(R.layout.activity_ywh_nessageguide);
        ButterKnife.bind(this);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        tabIndicator.setScrollBar(new ColorBar(getApplicationContext(), Color.WHITE, 0, ScrollBar.Gravity.CENTENT_BACKGROUND));

        indicatorViewPager = new IndicatorViewPager(tabIndicator, tabViewPager);
        indicatorViewPager.setAdapter(new YwhMessageGuideActivity.MyAdapter(getSupportFragmentManager()));

        //设置viewpager保留界面不重新加载的页面数量
        tabViewPager.setOffscreenPageLimit(6);
        tabIndicator.setSplitAuto(true);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void setupActivityComponent() {
        DaggerYwhMessageGuideComponent
                .builder()
                .appComponent(((AppConfig) getApplication()).getApplicationComponent())
                .ywhMessageGuideModule(new YwhMessageGuideModule(this))
                .build()
                .inject(this);
    }

    @Override
    public void setPresenter(YwhMessageGuideContract.YwhMessageGuideContractPresenter presenter) {
        mPresenter = (YwhMessageGuidePresenter) presenter;
    }

    @Override
    public void showProgressDialog() {
        progressDialog.show();
    }

    @Override
    public void closeProgressDialog() {
        progressDialog.hide();
    }

    public class MyAdapter extends IndicatorViewPager.IndicatorFragmentPagerAdapter {
        private String[] tabNames = {"开始成立", "成立筹备组", "筹备组工作", "候选人确认", "业主大会"};
        private LayoutInflater inflater;

        public MyAdapter(FragmentManager fragmentManager) {
            super(fragmentManager);
            inflater = LayoutInflater.from(getApplicationContext());
        }

        @Override
        public int getCount() {
            return tabNames.length;
        }

        @Override
        public View getViewForTab(int position, View convertView, ViewGroup container) {
            View view = View.inflate(getApplicationContext(), R.layout.yeh_tab_message, null);
            TextView textView =(TextView)  view.findViewById(R.id.tv_selector);
            textView.setText(tabNames[position]);
            return view;
        }

        @Override
        public Fragment getFragmentForPage(int position) {
            YwhMessageFragment mainFragment = new YwhMessageFragment();
            Bundle bundle = new Bundle();
            bundle.putInt("position", position);
            mainFragment.setArguments(bundle);
            return mainFragment;
        }
    }

    @Override
    protected void onDestroy() {
        mPresenter.unsubscribe();
        super.onDestroy();
    }
}