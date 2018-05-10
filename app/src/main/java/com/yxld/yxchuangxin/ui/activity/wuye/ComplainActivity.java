package com.yxld.yxchuangxin.ui.activity.wuye;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

import com.yxld.yxchuangxin.R;
import com.yxld.yxchuangxin.application.AppConfig;
import com.yxld.yxchuangxin.base.BaseActivity;
import com.yxld.yxchuangxin.ui.activity.wuye.component.DaggerComplainComponent;
import com.yxld.yxchuangxin.ui.activity.wuye.contract.ComplainContract;
import com.yxld.yxchuangxin.ui.activity.wuye.module.ComplainModule;
import com.yxld.yxchuangxin.ui.activity.wuye.presenter.ComplainPresenter;
import com.yxld.yxchuangxin.view.CustomPopWindow;

import java.util.ArrayList;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author hu
 * @Package com.yxld.yxchuangxin.ui.activity.wuye
 * @Description: $description投诉建议
 * @date 2017/06/20 09:58:43
 */

public class ComplainActivity extends BaseActivity implements ComplainContract.View {

    @Inject
    ComplainPresenter mPresenter;
    @BindView(R.id.tabLayout)
    TabLayout tabLayout;
    @BindView(R.id.viewPager)
    ViewPager viewPager;
    private ArrayList<String> titles;
    private FragmentPagerAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initView() {
        setContentView(R.layout.activity_complain);
        ButterKnife.bind(this);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    protected void initData() {
        titles = new ArrayList<>();
        titles.add("我要投诉");
        titles.add("我要建议");
        mAdapter = new FragmentPagerAdapter(getSupportFragmentManager()) {

            @Override
            public Fragment getItem(int position) {
                if (position == 0) {
                    return new WuyeTousuFragment();
                } else {
                    return new WuyeJianyiFragment();
                }
            }

            @Override
            public int getCount() {
                return titles.size();
            }

            @Override
            public CharSequence getPageTitle(int position) {
                return titles.get(position);
            }

        };
        viewPager.setAdapter(mAdapter);
        tabLayout.setupWithViewPager(viewPager);
        viewPager.setOffscreenPageLimit(2);
    }

    @Override
    protected void setupActivityComponent() {
        DaggerComplainComponent
                .builder()
                .appComponent(((AppConfig) getApplication()).getApplicationComponent())
                .complainModule(new ComplainModule(this))
                .build()
                .inject(this);
    }

    @Override
    public void setPresenter(ComplainContract.ComplainContractPresenter presenter) {
        mPresenter = (ComplainPresenter) presenter;
    }

    @Override
    public void showProgressDialog() {
        progressDialog.show();
    }

    @Override
    public void closeProgressDialog() {
        progressDialog.hide();
    }

    @Override
    public void onBackPressed() {
        if (CustomPopWindow.onBackPressed()) {
            return;
        }
        super.onBackPressed();
    }

    @Override
    protected void onDestroy() {
        mPresenter.unsubscribe();
        mAdapter = null;
        tabLayout = null;
        super.onDestroy();
    }
}