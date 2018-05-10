package com.yxld.yxchuangxin.ui.activity.wuye;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.Menu;
import android.view.MenuItem;

import com.yxld.yxchuangxin.R;
import com.yxld.yxchuangxin.Utils.ScreenUtil;
import com.yxld.yxchuangxin.application.AppConfig;
import com.yxld.yxchuangxin.base.BaseActivity;
import com.yxld.yxchuangxin.ui.activity.wuye.component.DaggerMenJinComponent;
import com.yxld.yxchuangxin.ui.activity.wuye.contract.MenJinContract;
import com.yxld.yxchuangxin.ui.activity.wuye.module.MenJinModule;
import com.yxld.yxchuangxin.ui.activity.wuye.presenter.MenJinPresenter;

import java.util.ArrayList;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author hu
 * @Package com.yxld.yxchuangxin.ui.activity.wuye
 * @Description: $description
 * @date 2017/06/06
 */

public class MenJinActivity extends BaseActivity implements MenJinContract.View {

    @Inject
    MenJinPresenter mPresenter;
    @BindView(R.id.tabLayout)
    TabLayout tabLayout;
    @BindView(R.id.viewPager)
    ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initData() {
        ScreenUtil.setBrightness(this, 255);
    }

    @Override
    protected void initView() {
        setContentView(R.layout.activity_menjin);
        ButterKnife.bind(this);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ArrayList<String> titles = new ArrayList<>();
        titles.add("业主二维码");
        titles.add("来访邀请");
        FragmentPagerAdapter mAdapter = new FragmentPagerAdapter(getSupportFragmentManager()) {

            @Override
            public Fragment getItem(int position) {
                if (position == 0) {
                    return new YeZhuFragment();
                } else {
                    return new LaiFangFragment();
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
    protected void setupActivityComponent() {
        DaggerMenJinComponent
                .builder()
                .appComponent(((AppConfig) getApplication()).getApplicationComponent())
                .menJinModule(new MenJinModule(this))
                .build()
                .inject(this);
    }

    @Override
    public void setPresenter(MenJinContract.MenJinContractPresenter presenter) {
        mPresenter = (MenJinPresenter) presenter;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_help, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.help:
                startActivity(MenjinHelpActivity.class);
                break;
            case android.R.id.home:
                finish();
                System.gc();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onDestroy() {
        mPresenter.unsubscribe();
        super.onDestroy();
    }
}