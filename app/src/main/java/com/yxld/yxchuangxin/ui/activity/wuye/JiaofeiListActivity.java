package com.yxld.yxchuangxin.ui.activity.wuye;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.yxld.yxchuangxin.R;
import com.yxld.yxchuangxin.application.AppConfig;
import com.yxld.yxchuangxin.base.BaseActivity;
import com.yxld.yxchuangxin.contain.Contains;
import com.yxld.yxchuangxin.entity.JiaofeiMingxi;
import com.yxld.yxchuangxin.entity.WyFwApp;
import com.yxld.yxchuangxin.ui.activity.wuye.component.DaggerJiaofeiListComponent;
import com.yxld.yxchuangxin.ui.activity.wuye.contract.JiaofeiListContract;
import com.yxld.yxchuangxin.ui.activity.wuye.module.JiaofeiListModule;
import com.yxld.yxchuangxin.ui.activity.wuye.presenter.JiaofeiListPresenter;
import com.yxld.yxchuangxin.ui.adapter.wuye.JiaofeiMingxiAdapter;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author hu
 * @Package com.yxld.yxchuangxin.ui.activity.wuye
 * @Description: $description
 * @date 2017/07/01 13:34:37
 */

public class JiaofeiListActivity extends BaseActivity implements JiaofeiListContract.View {

    @Inject
    JiaofeiMingxiAdapter adapter;
    @Inject
    JiaofeiListPresenter mPresenter;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initView() {
        setContentView(R.layout.activity_jiaofei_list);
        ButterKnife.bind(this);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    protected void initData() {
        WyFwApp house = (WyFwApp) getIntent().getSerializableExtra("fangwu");
        Map<String, String> map = new HashMap<>();
        map.put("fwId", house.getFwId() + "");
        map.put("uuid", Contains.uuid);
        mPresenter.getList(map);
        View footerView;
        footerView = getLayoutInflater().inflate(R.layout.footer_view, (ViewGroup) recyclerView.getParent(), false);
        adapter.addFooterView(footerView, 0);
        recyclerView.setAdapter(adapter);
    }
    @Override
    protected void setupActivityComponent() {
        DaggerJiaofeiListComponent
                .builder()
                .appComponent(((AppConfig) getApplication()).getApplicationComponent())
                .jiaofeiListModule(new JiaofeiListModule(this))
                .build()
                .inject(this);
    }

    @Override
    public void setPresenter(JiaofeiListContract.JiaofeiListContractPresenter presenter) {
        mPresenter = (JiaofeiListPresenter) presenter;
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
    public void setList(JiaofeiMingxi mingxi) {
        adapter.setNewData(mingxi.getHouse());
    }

    @Override
    public void setMoreList() {

    }

    @Override
    protected void onDestroy() {
        mPresenter.unsubscribe();
        super.onDestroy();
    }

}