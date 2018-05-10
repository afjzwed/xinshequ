package com.yxld.yxchuangxin.ui.activity.goods;

import android.os.Bundle;

import com.yxld.yxchuangxin.R;
import com.yxld.yxchuangxin.application.AppConfig;
import com.yxld.yxchuangxin.base.BaseActivity;
import com.yxld.yxchuangxin.entity.OrderComplainEntity;
import com.yxld.yxchuangxin.ui.activity.goods.component.DaggerOrderComplainDetailComponent;
import com.yxld.yxchuangxin.ui.activity.goods.contract.OrderComplainDetailContract;
import com.yxld.yxchuangxin.ui.activity.goods.module.OrderComplainDetailModule;
import com.yxld.yxchuangxin.ui.activity.goods.presenter.OrderComplainDetailPresenter;
import com.yxld.yxchuangxin.view.OrderComplainDetailItemView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author Yuan.Y.Q
 * @Package com.yxld.yxchuangxin.ui.activity.goods
 * @Description: $description
 * @date 2017/07/05 14:52:21
 */

public class OrderComplainDetailActivity extends BaseActivity implements OrderComplainDetailContract.View {
    public static final String KEY_ENTITY = "key_entity";
    @Inject
    OrderComplainDetailPresenter mPresenter;
    @BindView(R.id.tv_order_num)
    OrderComplainDetailItemView tvOrderNum;
    @BindView(R.id.tv_order_complain_time)
    OrderComplainDetailItemView tvOrderComplainTime;
    @BindView(R.id.tv_order_complain_type)
    OrderComplainDetailItemView tvOrderComplainType;
    @BindView(R.id.tv_order_complain_content)
    OrderComplainDetailItemView tvOrderComplainContent;
    @BindView(R.id.tv_order_complain_response)
    OrderComplainDetailItemView tvOrderComplainResponse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initView() {
        setContentView(R.layout.activity_order_complain_detail);
        ButterKnife.bind(this);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setBackgroundColor(getResources().getColor(R.color.color_ff9934));
        Bundle bundle = getIntent().getExtras();
        if(bundle!=null){
            OrderComplainEntity entity = bundle.getParcelable(KEY_ENTITY);
            if(entity!=null){
                tvOrderNum.setContent(entity.getTsjyDindanbianhao());
                tvOrderComplainContent.setContent(entity.getTsjyNeirong());
                tvOrderComplainType.setContent("0".equals(entity.getTssjyTest3())?"商品":"服务");
                tvOrderComplainTime.setContent(handlerTime(entity.getTsjyShijian().getTime()));
                tvOrderComplainResponse.setContent(entity.getTsjyTest2());
            }
        }


    }

    private String handlerTime(long time) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss", Locale.CHINA);
        return format.format(new Date(time));
    }


    @Override
    protected void initData() {

    }

    @Override
    protected void setupActivityComponent() {
        DaggerOrderComplainDetailComponent
                .builder()
                .appComponent(((AppConfig) getApplication()).getApplicationComponent())
                .orderComplainDetailModule(new OrderComplainDetailModule(this))
                .build()
                .inject(this);
    }

    @Override
    public void setPresenter(OrderComplainDetailContract.OrderComplainDetailContractPresenter presenter) {
        mPresenter = (OrderComplainDetailPresenter) presenter;
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