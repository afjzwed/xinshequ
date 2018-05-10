package com.yxld.yxchuangxin.ui.activity.wuye;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

import com.yxld.yxchuangxin.R;
import com.yxld.yxchuangxin.application.AppConfig;
import com.yxld.yxchuangxin.base.BaseActivity;
import com.yxld.yxchuangxin.ui.activity.wuye.component.DaggerMessageActivityComponent;
import com.yxld.yxchuangxin.ui.activity.wuye.contract.MessageActivityContract;
import com.yxld.yxchuangxin.ui.activity.wuye.module.MessageActivityModule;
import com.yxld.yxchuangxin.ui.activity.wuye.presenter.MessageActivityPresenter;

import java.util.ArrayList;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author hu
 * @Package com.yxld.yxchuangxin.ui.activity.wuye
 * @Description: $description
 * @date 2017/06/14
 */

public class MessageActivityActivity extends BaseActivity implements MessageActivityContract.View {

    @Inject
    MessageActivityPresenter mPresenter;
    @BindView(R.id.tabLayout)
    TabLayout tabLayout;
    @BindView(R.id.viewPager)
    ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initView() {
        setContentView(R.layout.activity_message_activity);
        ButterKnife.bind(this);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ArrayList<String> titles = new ArrayList<>();
        titles.add("通知");
        titles.add("活动");
        FragmentPagerAdapter mAdapter = new FragmentPagerAdapter(getSupportFragmentManager()) {

            @Override
            public Fragment getItem(int position) {
                if (position == 0) {
                    return new MessageFragment();
                } else {
                    return new ActivityFragment();
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
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void setupActivityComponent() {
        DaggerMessageActivityComponent
                .builder()
                .appComponent(((AppConfig) getApplication()).getApplicationComponent())
                .messageActivityModule(new MessageActivityModule(this))
                .build()
                .inject(this);
    }

    @Override
    public void setPresenter(MessageActivityContract.MessageActivityContractPresenter presenter) {
        mPresenter = (MessageActivityPresenter) presenter;
    }

    @Override
    protected void onDestroy() {
        mPresenter.unsubscribe();
        super.onDestroy();
    }
}