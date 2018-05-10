package com.yxld.yxchuangxin.ui.activity.camera;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.socks.library.KLog;
import com.yxld.yxchuangxin.R;
import com.yxld.yxchuangxin.application.AppConfig;
import com.yxld.yxchuangxin.base.BaseActivity;
import com.yxld.yxchuangxin.contain.Contains;
import com.yxld.yxchuangxin.entity.BaoJingEntity;
import com.yxld.yxchuangxin.ui.activity.camera.component.DaggerAllBaoJingComponent;
import com.yxld.yxchuangxin.ui.activity.camera.contract.AllBaoJingContract;
import com.yxld.yxchuangxin.ui.activity.camera.module.AllBaoJingModule;
import com.yxld.yxchuangxin.ui.activity.camera.presenter.AllBaoJingPresenter;
import com.yxld.yxchuangxin.ui.adapter.camera.BaoJingAdapter;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author hzp
 * @Package com.yxld.yxchuangxin.ui.activity.camera
 * @Description: $description
 * @date 2017/09/07 01:26:53
 */

public class AllBaoJingActivity extends BaseActivity implements AllBaoJingContract.View,BaseQuickAdapter.RequestLoadMoreListener, SwipeRefreshLayout.OnRefreshListener {

    @Inject
    AllBaoJingPresenter mPresenter;

    @Inject
    BaoJingAdapter baoJingAdapter;

    @BindView(R.id.recycerView)
    RecyclerView recycerView;
    @BindView(R.id.refreshLayout)
    SwipeRefreshLayout refreshLayout;

    private int mNextPage;
    private int mTotalNum;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initView() {
        setContentView(R.layout.activity_all_bao_jing);
        ButterKnife.bind(this);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        LinearLayoutManager ll = new LinearLayoutManager(this);
        recycerView.setLayoutManager(ll);
        recycerView.setAdapter(baoJingAdapter);
        baoJingAdapter.setOnLoadMoreListener(this, recycerView);
        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                initData();
            }
        });
    }

    @Override
    protected void initData() {
        mNextPage = 1;
        Map<String, String> map = new HashMap<>();
        map.put("mac", getIntent().getStringExtra("mac"));
        map.put("uuid", Contains.uuid);
        map.put("page", mNextPage + "");
        map.put("rows", "10");
        mPresenter.getBaoJingList(map);
    }

    @Override
    protected void setupActivityComponent() {
        DaggerAllBaoJingComponent
                .builder()
                .appComponent(((AppConfig) getApplication()).getApplicationComponent())
                .allBaoJingModule(new AllBaoJingModule(this))
                .build()
                .inject(this);
    }

    @Override
    public void setPresenter(AllBaoJingContract.AllBaoJingContractPresenter presenter) {
        mPresenter = (AllBaoJingPresenter) presenter;
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
    public void onBaoJingDataBack() {
        refreshLayout.setRefreshing(false);
    }

    @Override
    public void setAdapter(BaoJingEntity baoJingEntity) {
        refreshLayout.setRefreshing(false);
        baoJingAdapter.loadMoreComplete();
        mTotalNum = baoJingEntity.getData().getCount();
        if (mNextPage == 1) {
            baoJingAdapter.setNewData(baoJingEntity.getData().getRows());
            mNextPage++;
        } else {
            baoJingAdapter.addData(baoJingEntity.getData().getRows());
            mNextPage++;
        }
    }

    @Override
    public void onRefresh() {
        initData();
    }

    @Override
    public void onLoadMoreRequested() {
        KLog.i("请求加载更多。。。");
        if (baoJingAdapter.getData().size() < 10 || baoJingAdapter.getData().size() >= mTotalNum) {
            baoJingAdapter.loadMoreEnd(false);
            return;
        }
        KLog.i("开始请求加载更多。。。");
        Map<String, String> map = new HashMap<>();
        map.put("mac", getIntent().getStringExtra("mac"));
        map.put("uuid", Contains.uuid);
        map.put("page", mNextPage + "");
        map.put("rows", "10");
        mPresenter.getBaoJingList(map);
    }
}