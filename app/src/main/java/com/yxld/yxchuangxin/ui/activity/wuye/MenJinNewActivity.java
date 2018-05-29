package com.yxld.yxchuangxin.ui.activity.wuye;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

import com.yxld.yxchuangxin.R;
import com.yxld.yxchuangxin.application.AppConfig;
import com.yxld.yxchuangxin.base.BaseActivity;
import com.yxld.yxchuangxin.ui.activity.wuye.component.DaggerMenJinNewComponent;
import com.yxld.yxchuangxin.ui.activity.wuye.contract.MenJinNewContract;
import com.yxld.yxchuangxin.ui.activity.wuye.module.MenJinNewModule;
import com.yxld.yxchuangxin.ui.activity.wuye.presenter.MenJinNewPresenter;

import java.util.ArrayList;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author xlei
 * @Package com.yxld.yxchuangxin.ui.activity.wuye
 * @Description: $description
 * @date 2018/05/26 11:11:05
 */

public class MenJinNewActivity extends BaseActivity implements MenJinNewContract.View {

    @Inject
    MenJinNewPresenter mPresenter;
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
        setContentView(R.layout.activity_menjin_new);
        ButterKnife.bind(this);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ArrayList<String> titles = new ArrayList<>();
        titles.add("门禁列表");
        titles.add("密码分享");
        FragmentPagerAdapter mAdapter = new FragmentPagerAdapter(getSupportFragmentManager()) {

            @Override
            public Fragment getItem(int position) {
                if (position == 0) {
                    return new MenJinListFragment();
                } else {
                    return new MenJinShareFragment();
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
       DaggerMenJinNewComponent
               .builder()
               .appComponent(((AppConfig) getApplication()).getApplicationComponent())
               .menJinNewModule(new MenJinNewModule(this))
               .build()
               .inject(this);
    }
    @Override
    public void setPresenter(MenJinNewContract.MenJinNewContractPresenter presenter) {
        mPresenter = (MenJinNewPresenter) presenter;
    }

    @Override
    public void showProgressDialog() {
        progressDialog.show();
    }

    @Override
    public void closeProgressDialog() {
        progressDialog.hide();
    }

}