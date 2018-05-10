package com.yxld.yxchuangxin.ui.activity.rim;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.yxld.yxchuangxin.R;
import com.yxld.yxchuangxin.application.AppConfig;
import com.yxld.yxchuangxin.base.BaseActivity;
import com.yxld.yxchuangxin.contain.Contains;
import com.yxld.yxchuangxin.entity.SJOrderStatus;
import com.yxld.yxchuangxin.ui.activity.rim.component.DaggerRimOrderDynamicComponent;
import com.yxld.yxchuangxin.ui.activity.rim.contract.RimOrderDynamicContract;
import com.yxld.yxchuangxin.ui.activity.rim.module.RimOrderDynamicModule;
import com.yxld.yxchuangxin.ui.activity.rim.presenter.RimOrderDynamicPresenter;
import com.yxld.yxchuangxin.ui.adapter.rim.OrderDynamicAdapter;
import com.zhy.autolayout.AutoLinearLayout;

import java.util.LinkedHashMap;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author wwx
 * @Package com.yxld.yxchuangxin.ui.activity.rim
 * @Description: 欣周边 动态跟踪
 * @date 2017/06/17
 */

public class RimOrderDynamicActivity extends BaseActivity implements RimOrderDynamicContract.View, SwipeRefreshLayout.OnRefreshListener {

    @Inject
    RimOrderDynamicPresenter mPresenter;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.swipeLayouts)
    SwipeRefreshLayout swipeRefresh;
    @BindView(R.id.public_recylist_main_id)
    AutoLinearLayout publicRecylistMainId;

    private OrderDynamicAdapter dynamicAdapter;
    private SJOrderStatus listData = new SJOrderStatus();

    private String orderNumber;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initView() {
        setContentView(R.layout.public_recyclerview);
        ButterKnife.bind(this);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        publicRecylistMainId.setBackgroundColor(Color.WHITE);

        swipeRefresh.setOnRefreshListener(this);
        swipeRefresh.setColorSchemeResources(android.R.color.holo_blue_bright,
                android.R.color.holo_green_light, android.R.color.holo_orange_light,
                android.R.color.holo_red_light);

        dynamicAdapter = new OrderDynamicAdapter(this,listData.getData());
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(dynamicAdapter);
    }



    @Override
    protected void initData() {
        orderNumber = getIntent().getStringExtra("orderNumber");
        LinkedHashMap<String, String> data = new LinkedHashMap<>();
        data.put("orderNumber", orderNumber);
        data.put("uuId",Contains.uuid);
        mPresenter.requestDynamicData(data);
    }

    @Override
    protected void setupActivityComponent() {
        DaggerRimOrderDynamicComponent
                .builder()
                .appComponent(((AppConfig) getApplication()).getApplicationComponent())
                .rimOrderDynamicModule(new RimOrderDynamicModule(this))
                .build()
                .inject(this);
    }

    @Override
    public void setPresenter(RimOrderDynamicContract.RimOrderDynamicContractPresenter presenter) {
        mPresenter = (RimOrderDynamicPresenter) presenter;
    }

    @Override
    public void requestSuccess(SJOrderStatus info) {
        if (swipeRefresh != null && swipeRefresh.isRefreshing()) {
            swipeRefresh.setRefreshing(false);
        }
        listData = info;
        dynamicAdapter.setNewData(listData.getData());
    }

    @Override
    public void requestError(String msg) {
        if (progressDialog != null && progressDialog.isShowing()) {
            progressDialog.hide();
        }
        if (swipeRefresh != null && swipeRefresh.isRefreshing()) {
            swipeRefresh.setRefreshing(false);
        }
        onError(msg);
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
    public void onRefresh() {
        LinkedHashMap<String, String> data = new LinkedHashMap<>();
        data.put("orderNumber", orderNumber);
        data.put("uuId",Contains.uuid);
        mPresenter.requestDynamicData(data);
    }
}