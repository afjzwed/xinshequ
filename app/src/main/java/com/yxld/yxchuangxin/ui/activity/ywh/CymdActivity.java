package com.yxld.yxchuangxin.ui.activity.ywh;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.yxld.yxchuangxin.R;
import com.yxld.yxchuangxin.application.AppConfig;
import com.yxld.yxchuangxin.base.BaseActivity;
import com.yxld.yxchuangxin.base.BaseEntity;
import com.yxld.yxchuangxin.entity.YwhCurrentflow;
import com.yxld.yxchuangxin.ui.activity.ywh.component.DaggerCymdComponent;
import com.yxld.yxchuangxin.ui.activity.ywh.contract.CymdContract;
import com.yxld.yxchuangxin.ui.activity.ywh.module.CymdModule;
import com.yxld.yxchuangxin.ui.activity.ywh.presenter.CymdPresenter;
import com.yxld.yxchuangxin.ui.adapter.ywh.CymdAdapter;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author xlei
 * @Package com.yxld.yxchuangxin.ui.activity.ywh
 * @Description: $description
 * @date 2018/11/09 09:18:52
 */

public class CymdActivity extends BaseActivity implements CymdContract.View {

    @Inject
    CymdPresenter mPresenter;
    @BindView(R.id.rv) RecyclerView rv;
    private List<YwhCurrentflow.DataBean.FlowBean.ConfirmPeopleBean> data;
    private CymdAdapter adapter;
    private int isYjfk = 0;//意见反馈是否显示的标志 0显示 1不显示

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initView() {
        setContentView(R.layout.activity_cymd);
        ButterKnife.bind(this);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        data = getIntent().getParcelableArrayListExtra("data");
        isYjfk = getIntent().getIntExtra("isYjfk", 0);
        toolbar.setTitle("成员名单公示");
        toolbar.setBackgroundColor(getResources().getColor(R.color.color_2d97ff));
        //人员名单确认以后意见反馈隐藏
        if (isYjfk == 0) {
            tvMenu.setVisibility(View.VISIBLE);
        } else {
            tvMenu.setVisibility(View.GONE);
        }
        tvMenu.setText("反馈意见");
        tvMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(FkyjActivity.class);
            }
        });
        initRv();

    }

    private void initRv() {
        rv.setLayoutManager(new LinearLayoutManager(this));
        adapter = new CymdAdapter();
        rv.setAdapter(adapter);
        if (data != null && data.size() > 0) {
            adapter.setNewData(data);
        }
    }

    @Override
    protected void initData() {
//        mPresenter.getList();
    }

    @Override
    protected void setupActivityComponent() {
        DaggerCymdComponent
                .builder()
                .appComponent(((AppConfig) getApplication()).getApplicationComponent())
                .cymdModule(new CymdModule(this))
                .build()
                .inject(this);
    }

    @Override
    public void setPresenter(CymdContract.CymdContractPresenter presenter) {
        mPresenter = (CymdPresenter) presenter;
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
    public void getListSuccess(BaseEntity baseEntity) {

    }

}