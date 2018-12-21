package com.yxld.yxchuangxin.ui.activity.goods;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.yxld.yxchuangxin.R;
import com.yxld.yxchuangxin.Utils.StringUitl;
import com.yxld.yxchuangxin.application.AppConfig;
import com.yxld.yxchuangxin.base.BaseActivity;
import com.yxld.yxchuangxin.entity.goods.MallOrderSuggest;
import com.yxld.yxchuangxin.ui.activity.goods.component.DaggerOrderComplainComponent;
import com.yxld.yxchuangxin.ui.activity.goods.contract.OrderComplainContract;
import com.yxld.yxchuangxin.ui.activity.goods.module.OrderComplainModule;
import com.yxld.yxchuangxin.ui.activity.goods.presenter.OrderComplainPresenter;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author Yuan.Y.Q
 * @Package com.yxld.yxchuangxin.ui.activity.goods
 * @Description: $description
 * @date 2017/06/22 18:01:39
 */

public class OrderComplainActivity extends BaseActivity implements OrderComplainContract.View {

    @Inject
    OrderComplainPresenter mPresenter;
    @BindView(R.id.tv_orderid)
    TextView mTvOrderid;
    @BindView(R.id.tv_zhuangtai)
    TextView mTvZhuangtai;
    @BindView(R.id.tv_time)
    TextView mTvTime;
    @BindView(R.id.tv_content)
    TextView mTvContent;
    @BindView(R.id.tv_huifu)
    TextView mTvHuifu;

    private String orderid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initView() {
        setContentView(R.layout.activity_order_complain);
        ButterKnife.bind(this);
        toolbar.setBackgroundColor(Color.parseColor("#ff9934"));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//        String ordid = getIntent().getStringExtra("orderid");
        //       OrderComplainPartitionFragment fragment = OrderComplainPartitionFragment.newInstance(ordid);
//        Bundle bundle = new Bundle();
//        bundle.putString("orderid", ordid);
//        fragment.setArguments(bundle);
//
//        FragmentManager fm = getSupportFragmentManager();
//        FragmentTransaction transaction = fm.beginTransaction();
//        transaction.replace(R.id.fragment_container, fragment).commit();
//        fragment.setUserVisibleHint(true);

    }

    @Override
    protected void initData() {
        Intent intent = getIntent();
        //已有投诉 从投诉订单进入
        if (null != intent.getStringExtra("time") && !"".equals(intent.getStringExtra("time"))) {
            mTvOrderid.setText(intent.getStringExtra("orderid"));
            // mTvZhuangtai.setText(intent.getStringExtra("zhuangtai").equals("1")?"处理中":intent.getStringExtra("zhuangtai").equals("2")?"处理完成":"未处理");
            mTvTime.setText(intent.getStringExtra("time"));
            mTvContent.setText(intent.getStringExtra("content"));
            if (!StringUitl.isEmpty(intent.getStringExtra("jieguo")) ) {
                mTvHuifu.setVisibility(View.VISIBLE);
                mTvHuifu.setText("商家回复:"+intent.getStringExtra("jieguo"));
                mTvHuifu.setBackgroundColor(Color.rgb(233,233,233));
            } else {
                mTvHuifu.setVisibility(View.GONE);
            }

        } else {
            //还没有投诉 投诉订单进入
            orderid = intent.getStringExtra("orderid");
            if (orderid != null || !"".equals(orderid)) {
                mPresenter.getCompainOrder(orderid);
            }
        }

    }

    @Override
    protected void setupActivityComponent() {
        DaggerOrderComplainComponent
                .builder()
                .appComponent(((AppConfig) getApplication()).getApplicationComponent())
                .orderComplainModule(new OrderComplainModule(this))
                .build()
                .inject(this);
    }

    @Override
    public void setPresenter(OrderComplainContract.OrderComplainContractPresenter presenter) {
        mPresenter = (OrderComplainPresenter) presenter;
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
    public void getCompainOrderSucceed(MallOrderSuggest mallOrderSuggest) {
        if (mallOrderSuggest.getStatus() == 1) {
            if (mallOrderSuggest.getRows().size() > 0) {
                mTvTime.setText(mallOrderSuggest.getRows().get(0).getTsjyTijiaoShijian());
//                mTvZhuangtai.setText(mallOrderSuggest.getRows().get(0).getTsjyZhuangtai() == 1 ?
//                        "处理中" : mallOrderSuggest.getRows().get(0).getTsjyZhuangtai() == 2 ? "处理完成" : "未处理");
                mTvContent.setText(mallOrderSuggest.getRows().get(0).getTsjyNeirong());
                mTvOrderid.setText(mallOrderSuggest.getRows().get(0).getTsjyDindanBianhao());
                if (!StringUitl.isEmpty(mallOrderSuggest.getRows().get(0).getTsjyChuliJieguo())) {
                    mTvHuifu.setVisibility(View.VISIBLE);
                    mTvHuifu.setText("商家回复:"+mallOrderSuggest.getRows().get(0).getTsjyChuliJieguo());
                    mTvHuifu.setBackgroundColor(Color.rgb(233,233,233));
                } else {
                    mTvHuifu.setVisibility(View.GONE);
                }
            }
        } else {
            onError(mallOrderSuggest.MSG, mallOrderSuggest.status);
        }

    }

}