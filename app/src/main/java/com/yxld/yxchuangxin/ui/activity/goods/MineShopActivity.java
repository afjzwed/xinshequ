package com.yxld.yxchuangxin.ui.activity.goods;

import android.os.Bundle;

import com.yxld.yxchuangxin.R;
import com.yxld.yxchuangxin.application.AppConfig;
import com.yxld.yxchuangxin.base.BaseActivity;
import com.yxld.yxchuangxin.ui.activity.goods.component.DaggerMineShopComponent;
import com.yxld.yxchuangxin.ui.activity.goods.contract.MineShopContract;
import com.yxld.yxchuangxin.ui.activity.goods.module.MineShopModule;
import com.yxld.yxchuangxin.ui.activity.goods.presenter.MineShopPresenter;
import com.yxld.yxchuangxin.view.CustomPopWindow;

import javax.inject.Inject;

import butterknife.ButterKnife;

/**
 * @author hzp
 * @Package com.yxld.yxchuangxin.ui.activity.goods
 * @Description: $description
 * @date 2017/10/19 15:23:24
 */

public class MineShopActivity extends BaseActivity implements MineShopContract.View {

    @Inject
    MineShopPresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        needFront = true;
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initView() {
        setContentView(R.layout.activity_mine_shop);
        ButterKnife.bind(this);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void setupActivityComponent() {
       DaggerMineShopComponent
               .builder()
               .appComponent(((AppConfig) getApplication()).getApplicationComponent())
               .mineShopModule(new MineShopModule(this))
               .build()
               .inject(this);
    }
    @Override
    public void setPresenter(MineShopContract.MineShopContractPresenter presenter) {
        mPresenter = (MineShopPresenter) presenter;
    }

    @Override
    public void onBackPressed() {
        if (CustomPopWindow.onBackPressed()) {
            return;
        }
        super.onBackPressed();
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