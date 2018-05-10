package com.yxld.yxchuangxin.ui.activity.wuye;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.socks.library.KLog;
import com.yxld.yxchuangxin.R;
import com.yxld.yxchuangxin.Utils.UIUtils;
import com.yxld.yxchuangxin.application.AppConfig;
import com.yxld.yxchuangxin.base.BaseActivity;
import com.yxld.yxchuangxin.entity.CarJiaofeiRecord;
import com.yxld.yxchuangxin.entity.CarList;
import com.yxld.yxchuangxin.ui.activity.wuye.component.DaggerCarMoneyRecordComponent;
import com.yxld.yxchuangxin.ui.activity.wuye.contract.CarMoneyRecordContract;
import com.yxld.yxchuangxin.ui.activity.wuye.module.CarMoneyRecordModule;
import com.yxld.yxchuangxin.ui.activity.wuye.presenter.CarMoneyRecordPresenter;
import com.yxld.yxchuangxin.ui.adapter.wuye.CarJiaofeiRecordAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author hu
 * @Package com.yxld.yxchuangxin.ui.activity.wuye
 * @Description: $description
 * @date 2017/07/06 18:00:14
 */

public class CarMoneyRecordActivity extends BaseActivity implements CarMoneyRecordContract.View, SwipeRefreshLayout.OnRefreshListener, BaseQuickAdapter.RequestLoadMoreListener {

    @Inject
    CarMoneyRecordPresenter mPresenter;

    @Inject
    CarJiaofeiRecordAdapter adapter;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.swip_refresh)
    SwipeRefreshLayout mSwipRefresh;

    private CarList.DataBean car;
    private int page = 1;
    private int rows = 10;
    private List<CarJiaofeiRecord.DataBean> dataList;
    private List<CarJiaofeiRecord.DataBean> currentList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initView() {
        setContentView(R.layout.activity_car_money_record);
        ButterKnife.bind(this);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        UIUtils.configSwipeRefreshLayoutColors(mSwipRefresh);
        mSwipRefresh.setOnRefreshListener(this);
        recyclerView.setAdapter(adapter);
        adapter.setOnLoadMoreListener(this, recyclerView);
    }

    @Override
    protected void initData() {
        if (dataList == null) {
            dataList = new ArrayList<>();
        }
        if (currentList == null) {
            currentList = new ArrayList<>();
        }
        car = (CarList.DataBean) getIntent().getSerializableExtra("car");
        Map<String, String> map = new HashMap<>();
        map.put("rows", rows + "");
        map.put("page", page + "");
        map.put("mediaNo", car.getClmediaNo());
        map.put("isnew", "1");
        mPresenter.getCarMoneyRecord(map);
    }

    @Override
    protected void setupActivityComponent() {
        DaggerCarMoneyRecordComponent
                .builder()
                .appComponent(((AppConfig) getApplication()).getApplicationComponent())
                .carMoneyRecordModule(new CarMoneyRecordModule(this))
                .build()
                .inject(this);
    }

    @Override
    public void setPresenter(CarMoneyRecordContract.CarMoneyRecordContractPresenter presenter) {
        mPresenter = (CarMoneyRecordPresenter) presenter;
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
    public void setList(CarJiaofeiRecord record) {
        adapter.loadMoreComplete();
        mSwipRefresh.setRefreshing(false);
        currentList = record.getData();
        if (page == 1) {
            dataList.clear();
        }
        if (record.getData().size() != 0 && record.getData().size() <= rows) {
            dataList.addAll(record.getData());
            page++;
        }
        adapter.setNewData(dataList);
    }

    @Override
    protected void onDestroy() {
        mPresenter.unsubscribe();
        super.onDestroy();
    }

    @Override
    public void onRefresh() {
        page = 1;
        initData();
    }

    @Override
    public void onLoadMoreRequested() {
        KLog.i(dataList.size() + "---" + currentList.size());
        if (dataList.size() < rows || currentList.size() == 0) {
            adapter.loadMoreEnd(false);
            return;
        }
        initData();
    }
}