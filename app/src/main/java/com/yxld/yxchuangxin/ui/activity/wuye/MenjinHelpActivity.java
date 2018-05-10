package com.yxld.yxchuangxin.ui.activity.wuye;

import android.os.Bundle;
import android.widget.ImageView;

import com.yxld.yxchuangxin.R;
import com.yxld.yxchuangxin.application.AppConfig;
import com.yxld.yxchuangxin.base.BaseActivity;
import com.yxld.yxchuangxin.ui.activity.wuye.component.DaggerMenjinHelpComponent;
import com.yxld.yxchuangxin.ui.activity.wuye.contract.MenjinHelpContract;
import com.yxld.yxchuangxin.ui.activity.wuye.module.MenjinHelpModule;
import com.yxld.yxchuangxin.ui.activity.wuye.presenter.MenjinHelpPresenter;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author hu
 * @Package com.yxld.yxchuangxin.ui.activity.wuye
 * @Description: $description
 * @date 2017/06/26 15:48:18
 */

public class MenjinHelpActivity extends BaseActivity implements MenjinHelpContract.View {

    @Inject
    MenjinHelpPresenter mPresenter;
    @BindView(R.id.imageview)
    ImageView imageview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initView() {
        setContentView(R.layout.activity_menjin_help);
        ButterKnife.bind(this);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        imageview.setImageDrawable(getResources().getDrawable(R.mipmap.kmzn));

    }

    @Override
    protected void initData() {

    }

    @Override
    protected void setupActivityComponent() {
        DaggerMenjinHelpComponent
                .builder()
                .appComponent(((AppConfig) getApplication()).getApplicationComponent())
                .menjinHelpModule(new MenjinHelpModule(this))
                .build()
                .inject(this);
    }

    @Override
    public void setPresenter(MenjinHelpContract.MenjinHelpContractPresenter presenter) {
        mPresenter = (MenjinHelpPresenter) presenter;
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