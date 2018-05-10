package com.yxld.yxchuangxin.ui.activity.goods;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;

import com.socks.library.KLog;
import com.yxld.yxchuangxin.R;
import com.yxld.yxchuangxin.application.AppConfig;
import com.yxld.yxchuangxin.base.BaseActivity;
import com.yxld.yxchuangxin.ui.activity.goods.component.DaggerOrderListComponent;
import com.yxld.yxchuangxin.ui.activity.goods.contract.OrderListContract;
import com.yxld.yxchuangxin.ui.activity.goods.module.OrderListModule;
import com.yxld.yxchuangxin.ui.activity.goods.presenter.OrderListPartitionPresenter;
import com.yxld.yxchuangxin.ui.activity.goods.presenter.OrderListPresenter;
import com.yxld.yxchuangxin.ui.adapter.goods.MarketFragmentAdapter;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author Yuan.Y.Q
 * @Package com.yxld.yxchuangxin.ui.activity.goods
 * @Description: $description
 * @date 2017/06/21 17:03:40
 */

public class OrderListActivity extends BaseActivity implements OrderListContract.View, OrderListPartitionFragment.OnOrderChangedStatus {
    public static final String ITEM = "item";
    @Inject
    OrderListPresenter mPresenter;
    @BindView(R.id.tab_order_list)
    TabLayout tabOrderList;
    @BindView(R.id.vp_order_list_container)
    ViewPager vpOrderListContainer;

    private MarketFragmentAdapter mFragmentAdapter;
    private List<Fragment> mFragments;
    private List<String> mTitle;

    private int mItem;
    private boolean mHasOrderChanged;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initView() {
        setContentView(R.layout.activity_order_list);
        ButterKnife.bind(this);

        mItem = getIntent().getExtras().getInt(ITEM, 0);


        toolbar.setBackgroundColor(Color.parseColor("#ff9934"));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mFragments = new ArrayList<>();
        mTitle = new ArrayList<>();
        mTitle.add("全部");//全部
        mTitle.add("待支付");
        mTitle.add("待发货");
        mTitle.add("待收货");
        mTitle.add("待评价");
        //   mTitle.add("退款");

        for (String title : mTitle) {
            mFragments.add(OrderListPartitionFragment.newInstance(title));
        }
        initEvent();
    }

    private void initEvent() {
//        tabOrderList.setTabMode(TabLayout.MODE_SCROLLABLE);
        mFragmentAdapter = new MarketFragmentAdapter(getSupportFragmentManager(), mFragments, mTitle);
        vpOrderListContainer.setAdapter(mFragmentAdapter);
        tabOrderList.setupWithViewPager(vpOrderListContainer);
        vpOrderListContainer.setOffscreenPageLimit(5);
        vpOrderListContainer.setCurrentItem(mItem);

    }

    @Override
    protected void initData() {

    }

    @Override
    protected void setupActivityComponent() {
        DaggerOrderListComponent
                .builder()
                .appComponent(((AppConfig) getApplication()).getApplicationComponent())
                .orderListModule(new OrderListModule(this))
                .build()
                .inject(this);
    }

    @Override
    public void setPresenter(OrderListContract.OrderListContractPresenter presenter) {
        mPresenter = (OrderListPresenter) presenter;
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
    protected void onStop() {
        super.onStop();
        if (mHasOrderChanged) {
            Intent intent = new Intent(getResources().getString(R.string.update_mine));
            sendBroadcast(intent);
        }
    }


    public static Integer IS_PAY_SUCCESS = 0;//是否支付完成 0成功
    public static Integer IS_ZITI = 0;//1商城配送 2 门店自提

    @Override
    public void orderChangedStatus(String type, int changeStatus) {
        if (!mHasOrderChanged) {
            mHasOrderChanged = true;
        }
        OrderListPartitionFragment fragment1 = null;
        OrderListPartitionFragment fragment2 = null;
        if (changeStatus == OrderListPartitionPresenter.STATUS_ORDER_CANCEL) {
            //取消订单 -->全部
            if ("全部".equals(type)) {

                fragment1 = (OrderListPartitionFragment) mFragments.get(1);
            } else {
                fragment1 = (OrderListPartitionFragment) mFragments.get(0);
            }
        } else if (changeStatus == OrderListPartitionPresenter.STATUS_PAY_NOW) {
            //立即支付 -->待发货
            if ("全部".equals(type)) {
                //刷新待付款页面 以及 待发货页面
                fragment1 = (OrderListPartitionFragment) mFragments.get(1);
                fragment2 = (OrderListPartitionFragment) mFragments.get(2);
            } else {
                //从待付款页面进来  刷新全部 以及 待发货
                fragment1 = (OrderListPartitionFragment) mFragments.get(0);
                fragment2 = (OrderListPartitionFragment) mFragments.get(2);
            }
            KLog.i("type:" + type + "--》待发货" + "是否成功支付成功" + IS_PAY_SUCCESS);
            KLog.i("type:" + type + "--》待发货" + "是否自提" + IS_ZITI);
            // 1商城配送，2自提  支付成功并且是商城配送 跳到待发货页面
            if (IS_PAY_SUCCESS == 1 && IS_ZITI == 1) {
                vpOrderListContainer.setCurrentItem(2);
                IS_PAY_SUCCESS = 0;
                IS_ZITI = 0;
            } else if (IS_PAY_SUCCESS == 1 && IS_ZITI == 2) {
                //支付成功 并且自提 待收货界面
                vpOrderListContainer.setCurrentItem(3);
                IS_PAY_SUCCESS = 0;
                IS_ZITI = 0;
            }
        } else if (changeStatus == OrderListPartitionPresenter.STATUS_ORDER_TUI_KUAN) {
            //退款 -->退款中
            if ("全部".equals(type)) {
                //刷新待发货以及退款页面
                fragment1 = (OrderListPartitionFragment) mFragments.get(2);
                fragment2 = (OrderListPartitionFragment) mFragments.get(5);
            } else if ("待发货".equals(type)) {
                //全部 退款
                fragment1 = (OrderListPartitionFragment) mFragments.get(0);
                fragment2 = (OrderListPartitionFragment) mFragments.get(5);
            }
        } else if (changeStatus == OrderListPartitionPresenter.STATUS_ORDER_CONFIRM) {
            //确认收货  -->待评价
            if ("全部".equals(type)) {
                //待收货 待评价
                fragment1 = (OrderListPartitionFragment) mFragments.get(3);
                fragment2 = (OrderListPartitionFragment) mFragments.get(4);
            } else if ("待收货".equals(type)) {
                fragment1 = (OrderListPartitionFragment) mFragments.get(0);
                fragment2 = (OrderListPartitionFragment) mFragments.get(4);
            }
        } else if (changeStatus == OrderListPartitionPresenter.STATUS_COMMENT_NOW) {
            //立即评价 --> 已完成
            if ("全部".equals(type)) {
                fragment1 = (OrderListPartitionFragment) mFragments.get(4);
            } else if ("待评价".equals(type)) {
                fragment1 = (OrderListPartitionFragment) mFragments.get(0);
            }
        } else if (changeStatus == OrderListPartitionPresenter.STATUS_ORDER_TUI_HUO) {
            //申请退货 --> 退款
            if ("全部".equals(type)) {
                fragment1 = (OrderListPartitionFragment) mFragments.get(4);
                fragment2 = (OrderListPartitionFragment) mFragments.get(5);
            } else if ("待评价".equals(type)) {
                fragment1 = (OrderListPartitionFragment) mFragments.get(0);
                fragment2 = (OrderListPartitionFragment) mFragments.get(5);
            }
        } else if (changeStatus == OrderListPartitionPresenter.STATUS_ORDER_DELETE) {
            //删除订单 -->全部
            if ("全部".equals(type)) {
                fragment1 = (OrderListPartitionFragment) mFragments.get(0);
            } else if ("退款".equals(type)) {
                fragment1 = (OrderListPartitionFragment) mFragments.get(0);
            }
        } else if (changeStatus == OrderListPartitionPresenter.STATUS_SHOU_HOU) {
            //申请售后 -->全部 更新待评价和全部的界面 刷新售后申请按钮的显示
            if ("全部".equals(type)) {
                fragment1 = (OrderListPartitionFragment) mFragments.get(0);
                fragment2 = (OrderListPartitionFragment) mFragments.get(4);
            } else if ("待评价".equals(type)) {
                fragment1 = (OrderListPartitionFragment) mFragments.get(4);
                fragment2 = (OrderListPartitionFragment) mFragments.get(0);
            }
        }


        if (fragment1 != null) {
            fragment1.updateDataFromServer();
        }

        if (fragment2 != null) {
            fragment2.updateDataFromServer();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.unsubscribe();
        mPresenter = null;
        mFragmentAdapter.onDestroy();
        mFragmentAdapter = null;
        mFragments.clear();
        mFragments = null;
        mTitle.clear();
        mTitle = null;
        tabOrderList = null;
        vpOrderListContainer = null;
    }
}