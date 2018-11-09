package com.yxld.yxchuangxin.ui.activity.ywh;

import android.os.Bundle;

import com.yxld.yxchuangxin.R;
import com.yxld.yxchuangxin.application.AppConfig;
import com.yxld.yxchuangxin.base.BaseActivity;
import com.yxld.yxchuangxin.ui.activity.ywh.component.DaggerPqrzComponent;
import com.yxld.yxchuangxin.ui.activity.ywh.contract.PqrzContract;
import com.yxld.yxchuangxin.ui.activity.ywh.module.PqrzModule;
import com.yxld.yxchuangxin.ui.activity.ywh.presenter.PqrzPresenter;

import javax.inject.Inject;

import butterknife.ButterKnife;

/**
 * @author xlei
 * @Package com.yxld.yxchuangxin.ui.activity.ywh
 * @Description: $description
 * @date 2018/11/09 08:55:03
 */

public class PqrzActivity extends BaseActivity implements PqrzContract.View {

    @Inject
    PqrzPresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initView() {
        setContentView(R.layout.activity_pqrz);
        ButterKnife.bind(this);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void setupActivityComponent() {
       DaggerPqrzComponent
               .builder()
               .appComponent(((AppConfig) getApplication()).getApplicationComponent())
               .pqrzModule(new PqrzModule(this))
               .build()
               .inject(this);
    }
    @Override
    public void setPresenter(PqrzContract.PqrzContractPresenter presenter) {
        mPresenter = (PqrzPresenter) presenter;
    }

    @Override
    public void showProgressDialog() {
        progressDialog.show();
    }

    @Override
    public void closeProgressDialog() {
        progressDialog.hide();
    }

}