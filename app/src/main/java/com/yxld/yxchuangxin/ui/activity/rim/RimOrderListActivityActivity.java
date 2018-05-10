package com.yxld.yxchuangxin.ui.activity.rim;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

import com.yxld.yxchuangxin.R;
import com.yxld.yxchuangxin.Utils.YinLianPayUtil;
import com.yxld.yxchuangxin.application.AppConfig;
import com.yxld.yxchuangxin.base.BaseActivity;
import com.yxld.yxchuangxin.ui.activity.rim.component.DaggerRimOrderListActivityComponent;
import com.yxld.yxchuangxin.ui.activity.rim.contract.RimOrderListActivityContract;
import com.yxld.yxchuangxin.ui.activity.rim.module.RimOrderListActivityModule;
import com.yxld.yxchuangxin.ui.activity.rim.presenter.RimOrderListActivityPresenter;
import com.yxld.yxchuangxin.view.AutoTabLayout;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author xlei
 * @Package com.yxld.yxchuangxin.ui.activity.rim
 * @Description: $description
 * @date 2017/12/14 08:25:35
 */

public class RimOrderListActivityActivity extends BaseActivity implements
        RimOrderListActivityContract.View {

    @Inject
    RimOrderListActivityPresenter mPresenter;
    @BindView(R.id.order_tabLayout)
    AutoTabLayout orderTabLayout;
    @BindView(R.id.order_viewPage)
    ViewPager orderViewPage;

    private  List<RimPublicOrderListFragment> mRimOrderFragments;
    private String[] mTitles = new String[]{"进行中", "待评价", "已完成"};
//    private  List<RimPublicOrderListFragment> mRimOrderFragments;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initView() {
        setContentView(R.layout.activity_rim_order_list);
        ButterKnife.bind(this);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mRimOrderFragments = new ArrayList<>();
        mRimOrderFragments.add(RimPublicOrderListFragment.newInstance(1));
        mRimOrderFragments.add(RimPublicOrderListFragment.newInstance(2));
        mRimOrderFragments.add(RimPublicOrderListFragment.newInstance(3));

        orderViewPage.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                RimPublicOrderListFragment oneFragment = null;

                if (position == 0) {
                    oneFragment =mRimOrderFragments.get(0);
                }
                if (position == 1) {
                    oneFragment = mRimOrderFragments.get(2);
                }
                if (position == 2) {
                    oneFragment = mRimOrderFragments.get(1);
                }
                return oneFragment;
            }

            @Override
            public int getCount() {
                return mTitles.length;
            }

            @Override
            public CharSequence getPageTitle(int position) {
                return mTitles[position];
            }

        });

        orderTabLayout.setupWithViewPager(orderViewPage);
        orderTabLayout.setTabMode(TabLayout.MODE_FIXED);
        orderViewPage.setOffscreenPageLimit(3);
        orderViewPage.setCurrentItem(0);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void setupActivityComponent() {
        DaggerRimOrderListActivityComponent
                .builder()
                .appComponent(((AppConfig) getApplication()).getApplicationComponent())
                .rimOrderListActivityModule(new RimOrderListActivityModule(this))
                .build()
                .inject(this);
    }

    @Override
    public void setPresenter(RimOrderListActivityContract.RimOrderListActivityContractPresenter
                                         presenter) {
        mPresenter = (RimOrderListActivityPresenter) presenter;
    }

    @Override
    public void showProgressDialog() {
        progressDialog.show();
    }

    @Override
    public void closeProgressDialog() {
        progressDialog.hide();
    }
    private YinLianPayUtil mYinLianPayUtil = new YinLianPayUtil();
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        mYinLianPayUtil.yinLianCallBack(data, new YinLianPayUtil.YinLianPayBack() {
//            @Override
//            public void onSuccess(String msg) {
//                ToastUtil.showShort(msg);
//                fun();
//            }
//
//            @Override
//            public void onFail(String msg) {
//                ToastUtil.showShort(msg);
//            }
//
//            @Override
//            public void onCanel(String msg) {
//                ToastUtil.showShort(msg);
//            }
//        });
//    }

    public  void fun() {
        // TODO: 2017/12/22 暂时先三个列表全刷新，所有状态确定之后再根据状态来刷新页面
        for (RimPublicOrderListFragment oneFragment : mRimOrderFragments) {
            oneFragment.initData();
        }
    }

    public  ViewPager getViewPager() {
        return orderViewPage;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.unsubscribe();
        mPresenter = null;
        mRimOrderFragments.clear();
        mRimOrderFragments = null;
        orderTabLayout=null;
        orderViewPage = null;
    }
}