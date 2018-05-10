package com.yxld.yxchuangxin.ui.activity.main;

import android.graphics.Color;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.yxld.yxchuangxin.R;
import com.yxld.yxchuangxin.Utils.ToastUtil;
import com.yxld.yxchuangxin.application.AppConfig;
import com.yxld.yxchuangxin.base.BaseActivity;
import com.yxld.yxchuangxin.entity.ActivityOrder;
import com.yxld.yxchuangxin.ui.activity.main.component.DaggerSecondComponent;
import com.yxld.yxchuangxin.ui.activity.main.contract.SecondContract;
import com.yxld.yxchuangxin.ui.activity.main.module.SecondModule;
import com.yxld.yxchuangxin.ui.activity.main.presenter.SecondPresenter;
import com.yxld.yxchuangxin.ui.adapter.main.ActivityAdapter;

import java.util.LinkedHashMap;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by hu on 2017/5/16.
 */

public class SecondActivity extends BaseActivity implements SecondContract.View, BaseQuickAdapter.RequestLoadMoreListener {
    @BindView(R.id.recycerView)
    RecyclerView recycerView;
    @BindView(R.id.refreshLayout)
    SwipeRefreshLayout refreshLayout;

    @Inject
    SecondPresenter mPresenter;

    @Inject
    ActivityAdapter activityAdapter;

    @Inject
    public ActivityOrder data;

    int total = 0;
    int pageSize = 10;
    private boolean isErr = true;
    private boolean mLoadMoreEndGone = false;
    private int mCurrentCounter = 0;

    @Override
    protected void initData() {
        LinkedHashMap<String, String> data = new LinkedHashMap<>();
        data.put("lat", "112.991725");
        data.put("lng", "28.158146");
        data.put("userCode", "XM161031151357652659");
        data.put("pageNum", "1");   //
        data.put("sex", "女");
        data.put("orderByTime", "0");
        data.put("strs", "0,1,2,3,4,5,6,7");
        data.put("pageSize", pageSize + "");
        mPresenter.getList(data);
        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                LinkedHashMap<String, String> data = new LinkedHashMap<>();
                data.put("lat", "112.991725");
                data.put("lng", "28.158146");
                data.put("userCode", "XM161031151357652659");
                data.put("pageNum", "1");   //
                data.put("sex", "女");
                data.put("orderByTime", "0");
                data.put("strs", "0,1,2,3,4,5,6,7");
                data.put("pageSize", pageSize + "");
                mPresenter.getList(data);
            }
        });
    }

    @Override
    protected void initView() {
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setContentView(R.layout.activity_second);
        ButterKnife.bind(this);
        refreshLayout.setColorSchemeColors(Color.rgb(47, 223, 189));
        refreshLayout.setRefreshing(true);
    }

    @Override
    protected void setupActivityComponent() {
        DaggerSecondComponent
                .builder()
                .appComponent(((AppConfig) getApplication()).getApplicationComponent())
                .secondModule(new SecondModule(this))
                .build()
                .inject(this);
    }

    @Override
    public void setPresenter(SecondContract.SecondPresenter presenter) {
        mPresenter = (SecondPresenter) presenter;
    }

    @Override
    public void setData(ActivityOrder data) {
        if (data.getData().getPageNum() == 1) {
            total = Integer.parseInt(data.getData().getTotal());
            refreshLayout.setRefreshing(false);
            recycerView.setLayoutManager(new LinearLayoutManager(this));
            activityAdapter.setOnLoadMoreListener(this, recycerView);
            activityAdapter.setNewData(data.getData().getList());
            recycerView.setAdapter(activityAdapter);
            mCurrentCounter = activityAdapter.getData().size();
        } else {
            activityAdapter.addData(data.getData().getList());
            activityAdapter.notifyDataSetChanged();
            activityAdapter.loadMoreComplete();
            mCurrentCounter = activityAdapter.getData().size();
        }
    }

    @Override
    public void setError() {
        refreshLayout.setRefreshing(false);
        ToastUtil.show(this, "activityzhong");
    }

    @Override
    public void onLoadMoreRequested() {
        if (activityAdapter.getData().size() < pageSize) {
            //显示的总数都小于一次请求的数量
            activityAdapter.loadMoreEnd(true);
        } else {
            //当前显示的item大于总数
            if (mCurrentCounter >= total) {
                activityAdapter.loadMoreEnd(mLoadMoreEndGone);//true is gone,false is visible
            } else {
                if (isErr) {
                    //加载更多的逻辑
                    LinkedHashMap<String, String> data = new LinkedHashMap<>();
                    data.put("lat", "112.991725");
                    data.put("lng", "28.158146");
                    data.put("userCode", "XM161031151357652659");
                    if (activityAdapter.getData().size() % pageSize == 0) {
                        data.put("pageNum", (activityAdapter.getData().size() / pageSize + 1) + "");
                    } else {
                        data.put("pageNum", (activityAdapter.getData().size() / pageSize + 2) + "");
                    }
                    data.put("sex", "女");
                    data.put("orderByTime", "0");
                    data.put("strs", "0,1,2,3,4,5,6,7");
                    data.put("pageSize", pageSize + "");
                    mPresenter.getList(data);
                } else {
                    isErr = true;
//                    Toast.makeText(this, R.string.umeng_socialize_network_break_alert, Toast.LENGTH_LONG).show();
                    activityAdapter.loadMoreFail();
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
