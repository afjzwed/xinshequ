package com.yxld.yxchuangxin.ui.activity.wuye;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

import com.yxld.yxchuangxin.R;
import com.yxld.yxchuangxin.application.AppConfig;
import com.yxld.yxchuangxin.base.BaseActivity;
import com.yxld.yxchuangxin.ui.activity.wuye.component.DaggerFixListComponent;
import com.yxld.yxchuangxin.ui.activity.wuye.contract.FixListContract;
import com.yxld.yxchuangxin.ui.activity.wuye.module.FixListModule;
import com.yxld.yxchuangxin.ui.activity.wuye.presenter.FixListPresenter;

import java.util.ArrayList;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author hu
 * @Package com.yxld.yxchuangxin.ui.activity.wuye
 * @Description: $description
 * @date 2017/06/15
 */

public class FixListActivity extends BaseActivity implements FixListContract.View {

    @Inject
    FixListPresenter mPresenter;
    @BindView(R.id.tabLayout)
    TabLayout tabLayout;
    @BindView(R.id.viewPager)
    ViewPager viewPager;

    /** 记录查询的状态 1."" ，2，回执 3，填单*/
    private String repairId = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initView() {
        setContentView(R.layout.activity_fix_list);
        ButterKnife.bind(this);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    protected void initData() {
        ArrayList<String> titles = new ArrayList<>();
        titles.add("全部报修");
        titles.add("处理完");
        titles.add("处理中");
        FragmentPagerAdapter mAdapter = new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                if (position == 0) {
                    FixInFragment fragment = new FixInFragment();
                    Bundle bundle = new Bundle();
                    bundle.putString("repairId", "");
                    fragment.setArguments(bundle);
                    return fragment;
                } else if (position == 1){
                    FixInFragment fragment = new FixInFragment();
                    Bundle bundle = new Bundle();
                    bundle.putString("repairId", "7");
                    fragment.setArguments(bundle);
                    return fragment;
                } else {
                    FixInFragment fragment = new FixInFragment();
                    Bundle bundle = new Bundle();
                    bundle.putString("repairId", "1");
                    fragment.setArguments(bundle);
                    return fragment;
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
        viewPager.setOffscreenPageLimit(2);
        tabLayout.setupWithViewPager(viewPager);
    }

    @Override
    protected void setupActivityComponent() {
        DaggerFixListComponent
                .builder()
                .appComponent(((AppConfig) getApplication()).getApplicationComponent())
                .fixListModule(new FixListModule(this))
                .build()
                .inject(this);
    }

    @Override
    public void setPresenter(FixListContract.FixListContractPresenter presenter) {
        mPresenter = (FixListPresenter) presenter;
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
    protected void onDestroy() {
        mPresenter.unsubscribe();
        super.onDestroy();
    }
}