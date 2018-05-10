package com.yxld.yxchuangxin.ui.activity.rim;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.yxld.yxchuangxin.R;
import com.yxld.yxchuangxin.application.AppConfig;
import com.yxld.yxchuangxin.base.BaseActivity;
import com.yxld.yxchuangxin.contain.Contains;
import com.yxld.yxchuangxin.entity.CxwyBusiness;
import com.yxld.yxchuangxin.ui.activity.main.HomeActivity;
import com.yxld.yxchuangxin.ui.activity.rim.component.DaggerBusinessListComponent;
import com.yxld.yxchuangxin.ui.activity.rim.contract.BusinessListContract;
import com.yxld.yxchuangxin.ui.activity.rim.module.BusinessListModule;
import com.yxld.yxchuangxin.ui.activity.rim.presenter.BusinessListPresenter;
import com.yxld.yxchuangxin.ui.adapter.rim.BusinessListAdapter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author hu
 * @Package com.yxld.yxchuangxin.ui.activity.rim
 * @Description: $description
 * @date 2017/06/16
 */

public class BusinessListActivity extends BaseActivity implements BusinessListContract.View {

    @Inject
    BusinessListPresenter mPresenter;
    @Inject
    BusinessListAdapter adapter;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.refreshLayout)
    SwipeRefreshLayout refreshLayout;
    @BindView(R.id.ly_loading_failed)
    LinearLayout lyLoadingFailed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initView() {
        setContentView(R.layout.activity_business_list);
        ButterKnife.bind(this);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    protected void initData() {
        //page=%1$s&rows=%2$s&type=%3$s&uuId=%4$s
        Map<String, String> map = new HashMap<>();
        map.put("page", "1");
        map.put("rows", "20");
        map.put("type", "1");
        map.put("uuId", Contains.uuid);
        mPresenter.getBusinessList(map);
    }

    @Override
    protected void setupActivityComponent() {
        DaggerBusinessListComponent
                .builder()
                .appComponent(((AppConfig) getApplication()).getApplicationComponent())
                .businessListModule(new BusinessListModule(this))
                .build()
                .inject(this);
    }

    @Override
    public void setPresenter(BusinessListContract.BusinessListContractPresenter presenter) {
        mPresenter = (BusinessListPresenter) presenter;
    }

    @Override
    public void setList(List<CxwyBusiness.DataBean> list) {
        adapter.setNewData(list);
        recyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                Intent businessIntent = new Intent(BusinessListActivity.this, BusinessActivity.class);
                businessIntent.putExtra("flag", "nomodify");
                businessIntent.putExtra("businessNumber", adapter.getData().get(i).getBusiness().getBusinessNumber());
                startActivity(businessIntent);

            }
        });
    }
}