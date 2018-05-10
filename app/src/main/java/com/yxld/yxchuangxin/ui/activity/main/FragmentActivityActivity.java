package com.yxld.yxchuangxin.ui.activity.main;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.yxld.yxchuangxin.R;
import com.yxld.yxchuangxin.application.AppConfig;
import com.yxld.yxchuangxin.base.BaseActivity;
import com.yxld.yxchuangxin.ui.activity.main.component.DaggerFragmentActivityComponent;
import com.yxld.yxchuangxin.ui.activity.main.contract.FragmentActivityContract;
import com.yxld.yxchuangxin.ui.activity.main.module.FragmentActivityModule;
import com.yxld.yxchuangxin.ui.activity.main.presenter.FragmentActivityPresenter;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author hu
 * @Package com.example.hu.myapplication.ui.activity.main
 * @Description: $description
 * @date 2017/05/18
 */

public class FragmentActivityActivity extends BaseActivity implements FragmentActivityContract.View {

    @Inject
    FragmentActivityPresenter mPresenter;
    @BindView(R.id.viewPager)
    ViewPager viewPager;
    @BindView(R.id.tabLayout)
    TabLayout tabLayout;
    private FragmentPagerAdapter mAdapter;
    String[] title = {"第一个", "第二个", "第三个"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setContentView(R.layout.activity_fragment_activity);
        ButterKnife.bind(this);
        setAdapter();
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {

    }

    @Override
    protected void setupActivityComponent() {
        DaggerFragmentActivityComponent
                .builder()
                .appComponent(((AppConfig) getApplication()).getApplicationComponent())
                .fragmentActivityModule(new FragmentActivityModule(this))
                .build()
                .inject(this);
    }

    @Override
    public void setPresenter(FragmentActivityContract.FragmentActivityContractPresenter presenter) {
        mPresenter = (FragmentActivityPresenter) presenter;
    }


    @Override
    public void setAdapter() {
        mAdapter = new FragmentPagerAdapter(getSupportFragmentManager()) {

            @Override
            public Fragment getItem(int position) {
                TestFragment testFragment = new TestFragment();
                Bundle args = new Bundle();
                args.putInt("arg", position);
                testFragment.setArguments(args);
                return testFragment;
            }

            @Override
            public int getCount() {
                return 3;
            }

            @Override
            public CharSequence getPageTitle(int position) {
                return title[position];
            }

        };
        viewPager.setAdapter(mAdapter);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.setVisibility(View.VISIBLE);
    }
}