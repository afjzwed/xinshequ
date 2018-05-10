package com.yxld.yxchuangxin.ui.activity.goods;

import android.os.Bundle;
import android.widget.Toast;

import com.yxld.yxchuangxin.application.AppConfig;
import com.yxld.yxchuangxin.base.BaseActivity;
import com.yxld.yxchuangxin.ui.activity.goods.component.DaggerNormalTypeGoodsListComponent;
import com.yxld.yxchuangxin.ui.activity.goods.contract.NormalTypeGoodsListContract;
import com.yxld.yxchuangxin.ui.activity.goods.module.NormalTypeGoodsListModule;
import com.yxld.yxchuangxin.ui.activity.goods.presenter.NormalTypeGoodsListPresenter;

import javax.inject.Inject;

import butterknife.ButterKnife;

/**
 * @author Yuan.Y.Q
 * @Package com.yxld.yxchuangxin.ui.activity.goods
 * @Description: $description
 * @date 2017/06/16
 */

public class NormalTypeGoodsListActivity extends BaseActivity implements NormalTypeGoodsListContract.View {
    public static final String TYPE = "PRODUCT_NAME";
    @Inject
    NormalTypeGoodsListPresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initView() {
//        setContentView(R.layout.activity_normalTypeGoodsList);
        ButterKnife.bind(this);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        int position = getIntent().getExtras().getInt(TYPE);
        Toast.makeText(this,"当前:"+position,Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void setupActivityComponent() {
       DaggerNormalTypeGoodsListComponent
               .builder()
               .appComponent(((AppConfig) getApplication()).getApplicationComponent())
               .normalTypeGoodsListModule(new NormalTypeGoodsListModule(this))
               .build()
               .inject(this);
    }
    @Override
    public void setPresenter(NormalTypeGoodsListContract.NormalTypeGoodsListContractPresenter presenter) {
        mPresenter = (NormalTypeGoodsListPresenter) presenter;
    }
}