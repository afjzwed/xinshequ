package com.yxld.yxchuangxin.ui.activity.main;

import android.os.Bundle;

import com.yxld.yxchuangxin.application.AppConfig;
import com.yxld.yxchuangxin.base.BaseActivity;
import com.yxld.yxchuangxin.ui.activity.main.component.DaggerTwoComponent;
import com.yxld.yxchuangxin.ui.activity.main.contract.TwoContract;
import com.yxld.yxchuangxin.ui.activity.main.module.TwoModule;
import com.yxld.yxchuangxin.ui.activity.main.presenter.TwoPresenter;

import javax.inject.Inject;

import butterknife.ButterKnife;

/**
 * @author hu
 * @Package com.example.hu.myapplication.ui.activity.main
 * @Description: $description
 * @date 2017/05/18
 */

public class TwoActivity extends BaseActivity implements TwoContract.View {

    @Inject
    TwoPresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_Two);
        ButterKnife.bind(this);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {

    }

    @Override
    protected void setupActivityComponent() {
        DaggerTwoComponent
                .builder()
                .appComponent(((AppConfig) getApplication()).getApplicationComponent())
                .twoModule(new TwoModule(this))
                .build()
                .inject(this);
    }
    @Override
    public void setPresenter(TwoContract.TwoContractPresenter presenter) {
        mPresenter = (TwoPresenter) presenter;
    }
}