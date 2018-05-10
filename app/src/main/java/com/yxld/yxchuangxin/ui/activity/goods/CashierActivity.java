package com.yxld.yxchuangxin.ui.activity.goods;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.TextView;

import com.yxld.yxchuangxin.R;
import com.yxld.yxchuangxin.application.AppConfig;
import com.yxld.yxchuangxin.base.BaseActivity;
import com.yxld.yxchuangxin.ui.activity.goods.component.DaggerCashierComponent;
import com.yxld.yxchuangxin.ui.activity.goods.contract.CashierContract;
import com.yxld.yxchuangxin.ui.activity.goods.module.CashierModule;
import com.yxld.yxchuangxin.ui.activity.goods.presenter.CashierPresenter;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.refactor.library.SmoothCheckBox;

/**
 * @author Yuan.Y.Q
 * @Package com.yxld.yxchuangxin.ui.activity.goods
 * @Description: $description
 * @date 2017/06/27 19:49:09
 */

public class CashierActivity extends BaseActivity implements CashierContract.View {
    public static final String KEY_ORDER_ID = "key_order_id";
    public static final String KEY_ORDER_SHOULD_PAY = "key_order_should_pay";
    public static final String KEY_IN_TYPE = "key_in_type";

    public static final int IN_TYPE_ORDER_CONFIRM = 0x000001;//从确认订单界面进来
    public static final int IN_TYPE_ORDER_LIST = 0x000000;//从订单列表页进来


    @Inject
    CashierPresenter mPresenter;
    @BindView(R.id.tv_cashier_price)
    TextView tvCashierPrice;
    @BindView(R.id.check_cashier_zhifubao)
    SmoothCheckBox checkCashierZhifubao;
    @BindView(R.id.check_cashier_weixin)
    SmoothCheckBox checkCashierWeixin;
    @BindView(R.id.check_cashier_bank)
    SmoothCheckBox checkCashierBank;

    private String mOrderId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initView() {
        setContentView(R.layout.activity_cashier);
        ButterKnife.bind(this);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setBackgroundColor(getResources().getColor(R.color.color_ff9934));
    }

    @Override
    protected void initData() {
        Bundle bundle = getIntent().getExtras();
        tvCashierPrice.setText("¥ "+bundle.getDouble(KEY_ORDER_SHOULD_PAY));
        mOrderId = bundle.getString(KEY_ORDER_ID);
    }

    @Override
    protected void setupActivityComponent() {
        DaggerCashierComponent
                .builder()
                .appComponent(((AppConfig) getApplication()).getApplicationComponent())
                .cashierModule(new CashierModule(this))
                .build()
                .inject(this);
    }

    @Override
    public void setPresenter(CashierContract.CashierContractPresenter presenter) {
        mPresenter = (CashierPresenter) presenter;
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