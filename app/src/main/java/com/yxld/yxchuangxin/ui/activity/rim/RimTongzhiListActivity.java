package com.yxld.yxchuangxin.ui.activity.rim;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.yxld.yxchuangxin.R;
import com.yxld.yxchuangxin.Utils.ToastUtil;
import com.yxld.yxchuangxin.application.AppConfig;
import com.yxld.yxchuangxin.base.BaseActivity;
import com.yxld.yxchuangxin.contain.Contains;
import com.yxld.yxchuangxin.entity.SJOrder;
import com.yxld.yxchuangxin.ui.activity.rim.component.DaggerRimTongzhiListComponent;
import com.yxld.yxchuangxin.ui.activity.rim.contract.RimTongzhiListContract;
import com.yxld.yxchuangxin.ui.activity.rim.module.RimTongzhiListModule;
import com.yxld.yxchuangxin.ui.activity.rim.presenter.RimTongzhiListPresenter;
import com.yxld.yxchuangxin.ui.adapter.rim.RimTongzhiListAdapter;
import com.zhy.autolayout.AutoLinearLayout;

import java.util.ArrayList;
import java.util.LinkedHashMap;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author wwx
 * @Package com.yxld.yxchuangxin.ui.activity.rim
 * @Description: 欣周边 通知列表
 * @date 2017/06/17
 */

public class RimTongzhiListActivity extends BaseActivity implements RimTongzhiListContract.View , BaseQuickAdapter.RequestLoadMoreListener{

    @Inject
    RimTongzhiListPresenter mPresenter;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.swipeLayouts)
    SwipeRefreshLayout swipeLayouts;
    @BindView(R.id.public_recylist_main_id)
    AutoLinearLayout publicRecylistMainId;


    @Inject
    RimTongzhiListAdapter rimTongzhiListAdapter;
    @Inject
    public SJOrder data;

    int total = 0;
    int pageSize = 10;
    private boolean isErr = true;
    private boolean mLoadMoreEndGone = false;
    private int mCurrentCounter = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initView() {
        setContentView(R.layout.public_recyclerview);
        ButterKnife.bind(this);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        swipeLayouts.setColorSchemeColors(Color.rgb(47, 223, 189));
        swipeLayouts.setRefreshing(true);


        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(rimTongzhiListAdapter);
        rimTongzhiListAdapter.setOnLoadMoreListener(this, recyclerView);
    }

    @Override
    protected void initData() {
        LinkedHashMap<String, String> data = new LinkedHashMap<>();
        data.put("orderStatus", "1");
        data.put("uuId", Contains.uuid);
        data.put("page", "1");
        data.put("rows", pageSize + "");
        mPresenter.getRimTongzhiList(data);
        swipeLayouts.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                LinkedHashMap<String, String> data = new LinkedHashMap<>();
                data.put("orderStatus", "1");
                data.put("uuId",Contains.uuid);
                data.put("page", "1");
                data.put("rows", pageSize + "");
                setEmptyData();
                mPresenter.getRimTongzhiList(data);
            }
        });
    }

    @Override
    protected void setupActivityComponent() {
        DaggerRimTongzhiListComponent
                .builder()
                .appComponent(((AppConfig) getApplication()).getApplicationComponent())
                .rimTongzhiListModule(new RimTongzhiListModule(this))
                .build()
                .inject(this);
    }

    @Override
    public void setPresenter(RimTongzhiListContract.RimTongzhiListContractPresenter presenter) {
        mPresenter = (RimTongzhiListPresenter) presenter;
    }

    @Override
    public void setData(SJOrder data) {
        if (data.getTotal() == 1) {
            total = data.getTotal();
            swipeLayouts.setRefreshing(false);
            rimTongzhiListAdapter.setOnLoadMoreListener(this, recyclerView);
            rimTongzhiListAdapter.setNewData(data.getData());
            mCurrentCounter = rimTongzhiListAdapter.getData().size();
        } else {
            total = data.getTotal();
            rimTongzhiListAdapter.addData(data.getData());
            rimTongzhiListAdapter.notifyDataSetChanged();
            rimTongzhiListAdapter.loadMoreComplete();
            mCurrentCounter = rimTongzhiListAdapter.getData().size();
        }
    }

    @Override
    public void setError() {
        swipeLayouts.setRefreshing(false);
        ToastUtil.show(this, "activityzhong");
    }

    @Override
    public void setEmptyData() {
        SJOrder siorder = new SJOrder();
        siorder.setData(new ArrayList<>());
        rimTongzhiListAdapter.setNewData(siorder.getData());
        rimTongzhiListAdapter.notifyDataSetChanged();
    }

    @Override
    public void onLoadMoreRequested() {
        if (rimTongzhiListAdapter.getData().size() < pageSize) {
            //显示的总数都小于一次请求的数量
            rimTongzhiListAdapter.loadMoreEnd(true);
        } else {
            //当前显示的item大于总数
            if (mCurrentCounter >= total) {
                rimTongzhiListAdapter.loadMoreEnd(mLoadMoreEndGone);//true is gone,false is visible
            } else {
                if (isErr) {
                    //加载更多的逻辑
                    LinkedHashMap<String, String> data = new LinkedHashMap<>();
                    data.put("orderStatus", "1");
                    data.put("uuId",Contains.uuid);
                    data.put("rows", pageSize + "");
                    if (rimTongzhiListAdapter.getData().size() % pageSize == 0) {
                        data.put("page", (rimTongzhiListAdapter.getData().size() / pageSize + 1) + "");
                    } else {
                        data.put("page", (rimTongzhiListAdapter.getData().size() / pageSize + 2) + "");
                    }
                    mPresenter.getRimTongzhiList(data);
                } else {
                    isErr = true;
//                    Toast.makeText(this, R.string.umeng_socialize_network_break_alert, Toast.LENGTH_LONG).show();
                    rimTongzhiListAdapter.loadMoreFail();
                }
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.unsubscribe();
    }
}