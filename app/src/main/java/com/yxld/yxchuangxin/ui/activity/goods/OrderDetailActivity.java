package com.yxld.yxchuangxin.ui.activity.goods;

import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.CharacterStyle;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.widget.TextView;

import com.yanzhenjie.permission.PermissionListener;
import com.yxld.yxchuangxin.R;
import com.yxld.yxchuangxin.Utils.PermissionUtil;
import com.yxld.yxchuangxin.Utils.StringUitl;
import com.yxld.yxchuangxin.Utils.ToastUtil;
import com.yxld.yxchuangxin.application.AppConfig;
import com.yxld.yxchuangxin.base.BaseActivity;
import com.yxld.yxchuangxin.entity.CxwyMallOrder;
import com.yxld.yxchuangxin.entity.CxwyMallSale;
import com.yxld.yxchuangxin.entity.OrderDetailEntity;
import com.yxld.yxchuangxin.entity.goods.MallNewOrder;
import com.yxld.yxchuangxin.entity.goods.MallNewOrderDetails;
import com.yxld.yxchuangxin.ui.activity.goods.component.DaggerOrderDetailComponent;
import com.yxld.yxchuangxin.ui.activity.goods.contract.OrderDetailContract;
import com.yxld.yxchuangxin.ui.activity.goods.module.OrderDetailModule;
import com.yxld.yxchuangxin.ui.activity.goods.presenter.OrderDetailPresenter;
import com.yxld.yxchuangxin.ui.adapter.goods.OrderListAdapter;
import com.yxld.yxchuangxin.view.OrderAddressView;
import com.yxld.yxchuangxin.view.OrderDetailSelectView;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author Yuan.Y.Q
 * @Package com.yxld.yxchuangxin.ui.activity.goods
 * @Description: $description
 * @date 2017/06/22 15:45:54
 */

public class OrderDetailActivity extends BaseActivity implements OrderDetailContract.View {
    public static final String KEY_ORDER_DETAIL = "key_order_detail";
    @Inject
    OrderDetailPresenter mPresenter;
    @BindView(R.id.recycler_order_detail_products)
    RecyclerView recyclerOrderDetailProducts;
    @BindView(R.id.tv_order_detail_place_order_time)
    OrderDetailSelectView tvOrderDetailPlaceOrderTime;
    @BindView(R.id.tv_order_detail_product_num)
    OrderDetailSelectView tvOrderDetailProductNum;
    @BindView(R.id.tv_order_detail_order_type)
    TextView tvOrderDetailOrderType;
    @BindView(R.id.tv_order_detail_order_num)
    TextView tvOrderDetailOrderNum;
    @BindView(R.id.address_order_detail)
    OrderAddressView addressOrderDetail;
    @BindView(R.id.buyer_remarks)
    OrderDetailSelectView buyerRemarks;
    @BindView(R.id.pay_method)
    OrderDetailSelectView payMethod;
    @BindView(R.id.deliver_name)
    OrderDetailSelectView deliverName;
    @BindView(R.id.goods_total_money)
    OrderDetailSelectView goodsTotalMoney;
    @BindView(R.id.pei_song_fei)
    OrderDetailSelectView peiSongFei;
    @BindView(R.id.dian_zi_quan)
    OrderDetailSelectView dianZiQuan;
    @BindView(R.id.cancel_reason)
    OrderDetailSelectView cancelReason;
    @BindView(R.id.tv_order_detail_total_money)
    TextView tvOrderDetailTotalMoney;
    @BindView(R.id.distribution_model)
    OrderDetailSelectView distributionModel;

    private List<MallNewOrderDetails> mProductDatas;
    private OrderListAdapter.OrderListProductAdapter mProductAdapter;
    private MallNewOrder mMallNewOrder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initView() {
        setContentView(R.layout.activity_order_detail);
        ButterKnife.bind(this);
        toolbar.setBackgroundColor(Color.parseColor("#ff9934"));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mMallNewOrder = getIntent().getExtras().getParcelable(KEY_ORDER_DETAIL);
        mProductDatas = mMallNewOrder.getOrderDetails();
        if (mProductDatas == null) {
            mProductDatas = new ArrayList<>();
        }

        mProductAdapter = new OrderListAdapter.OrderListProductAdapter(mProductDatas);
        recyclerOrderDetailProducts.setLayoutManager(new LinearLayoutManager(OrderDetailActivity.this));
        recyclerOrderDetailProducts.setAdapter(mProductAdapter);


        //        mProductAdapter.setOnProductClickListener(new OrderListAdapter.OnProductClickListener() {
        //            @Override
        //            public void onClick(String shangPingId) {
        //                Intent intent = new Intent(OrderDetailActivity.this, GoodDetailActivity.class);
        //                intent.putExtra(GoodDetailActivity.KEY_PRODUCT_ID, shangPingId);
        //                startActivity(intent);
        //            }
        //        });


    }

    @Override
    protected void initData() {
        tvOrderDetailOrderNum.setText(mMallNewOrder.getBianhao());
        tvOrderDetailOrderType.setText(mMallNewOrder.getZhuangTaiString(mMallNewOrder.getZhuangtai()));
        tvOrderDetailPlaceOrderTime.setDesc(mMallNewOrder.getXiadanShijian());
        tvOrderDetailProductNum.setDesc(mMallNewOrder.getShangpinNum() + "件");
        addressOrderDetail.setAddress(mMallNewOrder.getShouhuorenMing(), mMallNewOrder.getShouhuoDizhi(), mMallNewOrder.getShouhuoDianhua());
        buyerRemarks.setDesc(mMallNewOrder.getBeizhu());
        payMethod.setDesc(mMallNewOrder.getFukuanFangshi() == 1 ? "支付宝" : mMallNewOrder.getFukuanFangshi() == 2 ?
                "微信" : mMallNewOrder.getFukuanFangshi() == 3 ? "银联" : "");
        //// TODO: 2017/10/30 字段没绑定
        //        SpannableStringBuilder spannable = new SpannableStringBuilder(order.getDingdanBeiyong5() + " " + entity.phone);
        //        CharacterStyle span1 = new ForegroundColorSpan(Color.BLACK);
        //        CharacterStyle span2 = new ForegroundColorSpan(Color.RED);
        //        spannable.setSpan(span1, 0, order.getDingdanBeiyong5().length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        //        spannable.setSpan(span2, order.getDingdanBeiyong5().length(), order.getDingdanBeiyong5().length() + entity.phone.length() + 1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        if (!StringUitl.isEmpty(mMallNewOrder.getPeisongrenMing())) {
            deliverName.setDesc(mMallNewOrder.getPeisongrenMing());
            Drawable dwLeft = getResources().getDrawable(R.mipmap.phonekf);
            dwLeft.setBounds(0, 0, dwLeft.getMinimumWidth(), dwLeft.getMinimumHeight());

            deliverName.getTvDesc().setCompoundDrawables(null, null, dwLeft, null);
        }
        goodsTotalMoney.setDesc("¥ " + StringUitl.get2xiaoshu(mMallNewOrder.getZongjine()));
        peiSongFei.setDesc("+ " + StringUitl.get2xiaoshu(mMallNewOrder.getPeisongfei()));
        dianZiQuan.setDesc("- " + StringUitl.get2xiaoshu(mMallNewOrder.getDianziquan()));
        if (mMallNewOrder.getZhuangtai() == 9) {
            cancelReason.setVisibility(View.VISIBLE);
            cancelReason.setDesc(mMallNewOrder.getQuxiaoYuanyin());
        } else {
            cancelReason.setVisibility(View.GONE);
        }
        tvOrderDetailTotalMoney.setText("¥ " + StringUitl.get2xiaoshu(mMallNewOrder.getShijiJine()));
        distributionModel.setDesc(mMallNewOrder.getPeisongFangshi() == 1 ? "商城配送" : mMallNewOrder.getPeisongFangshi() == 2 ? "自提" : "");
        if (mMallNewOrder.getPeisongFangshi() == 2) {
            deliverName.setVisibility(View.GONE);
        }
    }


    @Override
    protected void setupActivityComponent() {
        DaggerOrderDetailComponent
                .builder()
                .appComponent(((AppConfig) getApplication()).getApplicationComponent())
                .orderDetailModule(new OrderDetailModule(this))
                .build()
                .inject(this);
    }

    @Override
    public void setPresenter(OrderDetailContract.OrderDetailContractPresenter presenter) {
        mPresenter = (OrderDetailPresenter) presenter;
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
    public void onLoadOrderDetailFailed() {

    }


    @Override
    public void onLoadOrderDetailSucceed(OrderDetailEntity entity) {
        if (entity.getStatus() == STATUS_CODE_OK) {
            CxwyMallOrder order = entity.getOrder();
            tvOrderDetailOrderNum.setText(order.getDingdanBianhao());
            tvOrderDetailOrderType.setText(order.getDingdanZhuangtai());
            tvOrderDetailPlaceOrderTime.setDesc(order.getDingdanXiadanTime());
            tvOrderDetailProductNum.setDesc(order.getDingdanGoodsnum() + "件");
            addressOrderDetail.setAddress(order.getDingdanName(), order.getDingdanDizhi(), order.getDingdanTel());
            buyerRemarks.setDesc(order.getDingdanBeiyong3());
            payMethod.setDesc(order.getDingdanBeiyong1());
            SpannableStringBuilder spannable = new SpannableStringBuilder(order.getDingdanBeiyong5() + " " + entity.phone);
            CharacterStyle span1 = new ForegroundColorSpan(Color.BLACK);
            CharacterStyle span2 = new ForegroundColorSpan(Color.RED);
            spannable.setSpan(span1, 0, order.getDingdanBeiyong5().length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            spannable.setSpan(span2, order.getDingdanBeiyong5().length(), order.getDingdanBeiyong5().length() + entity.phone.length() + 1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            deliverName.setDesc(spannable);
            goodsTotalMoney.setDesc("¥ " + getGoodsTotalMoney(entity.getSaleList()));
            peiSongFei.setDesc("+ " + order.getDingdanPeisongfei());
            dianZiQuan.setDesc("- " + order.getDingdanYouhuijia());
            cancelReason.setDesc(order.getDingdanBeiyong4());
            tvOrderDetailTotalMoney.setText("¥ " + order.getDingdanTotalRmb());

            mProductDatas.clear();
            // mProductDatas.addAll(entity.getSaleList());
            mProductAdapter.notifyDataSetChanged();

            deliverName.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (StringUitl.isTelNum(entity.phone)) {
                        showTelDialog(entity.phone);
                    } else {
                        ToastUtil.show(OrderDetailActivity.this, "未获取到配送员的联系电话");
                    }

                }
            });
        } else {
            onError(entity.MSG, entity.status);
        }
    }

    private void showTelDialog(String phone) {
        AlertDialog.Builder builder = new AlertDialog.Builder(OrderDetailActivity.this);
        builder.setTitle("提示:拨打电话 " + phone);
        builder.setNegativeButton("取消", null);
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                PermissionUtil.permission(OrderDetailActivity.this, 0x000501, new PermissionListener() {
                    @Override
                    public void onSucceed(int requestCode, @NonNull List<String> grantPermissions) {
                        if (requestCode == 0x000501) {
                            Uri uri = Uri.parse("tel:" + phone);
                            Intent intent = new Intent(Intent.ACTION_DIAL, uri);
                            startActivity(intent);
                        }
                    }

                    @Override
                    public void onFailed(int requestCode, @NonNull List<String> deniedPermissions) {
                        ToastUtil.show(OrderDetailActivity.this, "请提供对应的权限");
                    }
                }, Manifest.permission.CALL_PHONE);
            }
        });
        builder.show();

    }

    private String getGoodsTotalMoney(List<CxwyMallSale> saleList) {
        double total = 0;
        for (CxwyMallSale sale : saleList) {
            total += sale.getSaleTotalRmb();
        }
        DecimalFormat decimalFormat = new DecimalFormat("#0.00");
        return decimalFormat.format(total);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.unsubscribe();
    }

    /**
     * 打电话给配送元
     *
     * @param phone
     */
    private void callPhone(String phone) {
        Intent intent = new Intent(Intent.ACTION_DIAL);
        Uri data = Uri.parse("tel:" + phone);
        intent.setData(data);
        startActivity(intent);
    }

    @OnClick(R.id.deliver_name)
    public void onViewClicked() {
        if (!StringUitl.isEmpty(mMallNewOrder.getPeisongrenTel())) {
            callPhone(mMallNewOrder.getPeisongrenTel());
        }

    }
}