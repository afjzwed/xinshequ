package com.yxld.yxchuangxin.ui.activity.goods;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.socks.library.KLog;
import com.yxld.yxchuangxin.R;
import com.yxld.yxchuangxin.application.AppConfig;
import com.yxld.yxchuangxin.base.BaseActivity;
import com.yxld.yxchuangxin.ui.activity.goods.component.DaggerSecondShopCartComponent;
import com.yxld.yxchuangxin.ui.activity.goods.contract.SecondShopCartContract;
import com.yxld.yxchuangxin.ui.activity.goods.module.SecondShopCartModule;
import com.yxld.yxchuangxin.ui.activity.goods.presenter.SecondShopCartPresenter;

import javax.inject.Inject;

import butterknife.ButterKnife;

/**
 * @author Yuan.Y.Q
 * @Package com.yxld.yxchuangxin.ui.activity.goods
 * @Description: $description
 * @date 2017/07/05 14:05:30
 */

public class SecondShopCartActivity extends BaseActivity implements SecondShopCartContract.View {

    @Inject
    SecondShopCartPresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initView() {
        setContentView(R.layout.activity_second_shop_cart);
        ButterKnife.bind(this);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setBackgroundColor(getResources().getColor(R.color.color_ff9934));

        ShopCartFragment fragment = new ShopCartFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(ShopCartFragment.KEY_IN_TYPE, ShopCartFragment.IN_TYPE_OTHER);
        fragment.setArguments(bundle);

        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        transaction.replace(R.id.fragment_container, fragment).commit();
        fragment.setUserVisibleHint(true);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void setupActivityComponent() {
        DaggerSecondShopCartComponent
                .builder()
                .appComponent(((AppConfig) getApplication()).getApplicationComponent())
                .secondShopCartModule(new SecondShopCartModule(this))
                .build()
                .inject(this);
    }

    @Override
    public void setPresenter(SecondShopCartContract.SecondShopCartContractPresenter presenter) {
        mPresenter = (SecondShopCartPresenter) presenter;
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
        super.onDestroy();
        KLog.i("Second", "destroy");
    }
}