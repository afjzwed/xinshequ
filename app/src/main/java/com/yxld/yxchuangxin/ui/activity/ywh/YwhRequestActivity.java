package com.yxld.yxchuangxin.ui.activity.ywh;

import android.os.Bundle;

import com.yxld.yxchuangxin.R;
import com.yxld.yxchuangxin.application.AppConfig;
import com.yxld.yxchuangxin.base.BaseActivity;

import com.yxld.yxchuangxin.ui.activity.ywh.component.DaggerYwhRequestComponent;
import com.yxld.yxchuangxin.ui.activity.ywh.contract.YwhRequestContract;
import com.yxld.yxchuangxin.ui.activity.ywh.module.YwhRequestModule;
import com.yxld.yxchuangxin.ui.activity.ywh.presenter.YwhRequestPresenter;

import javax.inject.Inject;

import butterknife.ButterKnife;

/**
 * @author William
 * @Package com.yxld.yxchuangxin.ui.activity.wuye
 * @Description: 业委会成立条件 完成
 * @date 2018/11/07 18:59:19
 */

public class YwhRequestActivity extends BaseActivity implements YwhRequestContract.View {

    @Inject
    YwhRequestPresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initView() {
        setContentView(R.layout.activity_ywh_request);
        ButterKnife.bind(this);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void setupActivityComponent() {
       DaggerYwhRequestComponent
               .builder()
               .appComponent(((AppConfig) getApplication()).getApplicationComponent())
               .ywhRequestModule(new YwhRequestModule(this))
               .build()
               .inject(this);
    }
    @Override
    public void setPresenter(YwhRequestContract.YwhRequestContractPresenter presenter) {
        mPresenter = (YwhRequestPresenter) presenter;
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
    protected void onDestroy() {
        mPresenter.unsubscribe();
        super.onDestroy();
    }

}