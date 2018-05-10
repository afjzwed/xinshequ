package com.yxld.yxchuangxin.ui.activity.goods;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SwitchCompat;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.style.AbsoluteSizeSpan;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.socks.library.KLog;
import com.uuzuche.lib_zxing.activity.CodeUtils;
import com.yxld.yxchuangxin.R;
import com.yxld.yxchuangxin.Utils.StringUitl;
import com.yxld.yxchuangxin.Utils.ToastUtil;
import com.yxld.yxchuangxin.application.AppConfig;
import com.yxld.yxchuangxin.base.BaseActivity;
import com.yxld.yxchuangxin.base.BaseEntity;
import com.yxld.yxchuangxin.contain.Contains;
import com.yxld.yxchuangxin.contain.PayContain;
import com.yxld.yxchuangxin.entity.SureOrderEntity;
import com.yxld.yxchuangxin.entity.goods.BaseEntityAll;
import com.yxld.yxchuangxin.entity.goods.DiZiJuanGuiZei;
import com.yxld.yxchuangxin.entity.goods.MallNewOrder;
import com.yxld.yxchuangxin.entity.goods.MallNewOrderDetails;
import com.yxld.yxchuangxin.entity.goods.ShopDianZiJuan;
import com.yxld.yxchuangxin.ui.activity.goods.component.DaggerConfirmOrderCodeComponent;
import com.yxld.yxchuangxin.ui.activity.goods.contract.ConfirmOrderCodeContract;
import com.yxld.yxchuangxin.ui.activity.goods.module.ConfirmOrderCodeModule;
import com.yxld.yxchuangxin.ui.activity.goods.presenter.ConfirmOrderCodePresenter;
import com.yxld.yxchuangxin.ui.adapter.goods.ConfirmOrderAdapter;
import com.yxld.yxchuangxin.view.ConfirmOrderSelectView;
import com.zhy.autolayout.AutoFrameLayout;
import com.zhy.autolayout.AutoLinearLayout;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author xlei
 * @Package com.yxld.yxchuangxin.ui.activity.goods
 * @Description: $description
 * @date 2018/03/21 08:46:31
 */

public class ConfirmOrderCodeActivity extends BaseActivity implements ConfirmOrderCodeContract.View {

    @Inject
    ConfirmOrderCodePresenter mPresenter;
    @BindView(R.id.recycler_confirm_order)
    RecyclerView recyclerConfirmOrder;
    @BindView(R.id.select_view_use_coupons)
    ConfirmOrderSelectView selectViewUseCoupons;
    @BindView(R.id.select_view_remain_coupons)
    ConfirmOrderSelectView selectViewRemainCoupons;
    @BindView(R.id.select_view_product_prices)
    ConfirmOrderSelectView selectViewProductPrices;
    @BindView(R.id.tv_zuiduo)
    TextView mTvZuiduo;
    @BindView(R.id.tv_yunfei_desc)
    TextView tvYunfeiDesc;
    @BindView(R.id.tv_confirm_order_price)
    TextView tvConfirmOrderPrice;
    @BindView(R.id.select_view_order_type)
    ConfirmOrderSelectView selectViewOrderType;
    @BindView(R.id.select_view_yingshou_prices)
    ConfirmOrderSelectView selectViewYingShouPrices;
    @BindView(R.id.select_view_dikou_prices)
    ConfirmOrderSelectView selectViewDikouPrices;
    @BindView(R.id.auto_layout_guizi)
    AutoFrameLayout autoLayoutGuizi;
    @BindView(R.id.swipeLayouts)
    SmartRefreshLayout mSwipeLayouts;
    @BindView(R.id.ly_empty_data)
    AutoLinearLayout mLyEmptyData;
    @BindView(R.id.scroll_view)
    NestedScrollView mScrollView;
    @BindView(R.id.btn_confirm_order_to_balance)
    Button mBtnConfirmOrderToBalance;

    private DecimalFormat decimalFormat;
    private ConfirmOrderAdapter mAdapter;

    /**
     * 实付款 最后付款价格 配送费+总价-可以使用的电子券
     */
    private double mRealPay;

    /**
     * 使用的电子券数量
     */
    private int mUseDianZiQuanCount;
    /**
     * 总共可以使用的电子券数量 电子券余额
     */
    private int mTotalDianZiQuan;

    /**
     * 最多优惠的电子券数量
     */
    private int mRemainDianZiQuan;

    /**
     * 商品金额  商品的总价
     */
    private double mTotalPrice;
    /**
     * 抵扣金额
     */
    private double mDiKouPrices;
    /**
     * 应付金额 =商品金额-抵扣金额
     */
    private double mYingfuPrices;

    /**
     * 扫码返回的订单编号
     */
    private String dingDanBianHao;

    /**
     * 所有的商品名字拼接而成的字符串
     */
    private String mProductNames;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initView() {
        setContentView(R.layout.activity_confirm_order_code);
        ButterKnife.bind(this);
        toolbar.setBackgroundColor(Color.parseColor("#ff9934"));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Contains.sureOrderList.clear();
        mAdapter = new ConfirmOrderAdapter(Contains.sureOrderList);
        LinearLayoutManager manager = new LinearLayoutManager(ConfirmOrderCodeActivity.this, LinearLayoutManager.VERTICAL, false);
        recyclerConfirmOrder.setLayoutManager(manager);
        recyclerConfirmOrder.setAdapter(mAdapter);
    }

    @Override
    protected void initData() {
        mTotalDianZiQuan = 0;
        mRemainDianZiQuan = 0;
        decimalFormat = new DecimalFormat("#0.00");
        dingDanBianHao = getIntent().getStringExtra(CodeUtils.RESULT_STRING);
        initEvent();
        showProgressDialog();
        loadDataFromSever();
    }

    private void loadDataFromSever() {
        //加载电子券余额
        mPresenter.loadDianZiQuan();
        //加载订单信息
        Map<String, String> map = new HashMap<>();
        map.put("bianhao", dingDanBianHao);
        map.put("uuid", Contains.uuid);
        mPresenter.getPosOrderDetail(map);
    }

    /**
     * 事件监听
     */
    private void initEvent() {
        selectViewUseCoupons.setOnUseDianZiQuanListener(new ConfirmOrderSelectView.OnUseDianZiQuanListener() {
            @Override
            public void onUserDianZiQuanListener(SwitchCompat view, boolean isChecked) {
                if (isChecked) {
                    if (mTotalDianZiQuan <= 0) {
                        view.setChecked(false);
                        ToastUtil.show(ConfirmOrderCodeActivity.this, "当前没有电子券");
                        return;
                    }

                    //加载电子券
                    Map<String, String> params = new HashMap<>();
                    params.put("money", mYingfuPrices + "");
                    params.put("peisongfei", 0 + "");
                    params.put("uuid", Contains.uuid);
                    mPresenter.loadOrderRemainDianZiQuan(params);


                } else {
                    //关闭电子券规则的显示
                    autoLayoutGuizi.setVisibility(View.GONE);
                    //关闭使用电子券 默认使用的电子券为0
                    mUseDianZiQuanCount = 0;
                    selectViewRemainCoupons.setDianZiQuanUesCount(mUseDianZiQuanCount);
                    //计算实际金额
                    setRealPay();
                    //关闭电子券使用的显示
                    selectViewRemainCoupons.setVisibility(View.GONE);
                    mTvZuiduo.setVisibility(View.GONE);
                }
            }
        });
        selectViewRemainCoupons.setOnDianZiQuanCountChanged(new ConfirmOrderSelectView.OnDianZiQuanCountChanged()

        {
            @Override
            public void onDianZiQuanMinus() {
                // if (mUseDianZiQuanCount == 0 || (mRealPay - 1) < 0) {
                if (mUseDianZiQuanCount == 0) {
                    ToastUtil.show(ConfirmOrderCodeActivity.this, getResources().getString(R.string.cannot_minus_cart));
                    return;
                }
                mUseDianZiQuanCount--;
                selectViewRemainCoupons.setDianZiQuanUesCount(mUseDianZiQuanCount);
                setRealPay();
            }

            @Override
            public void onDianZiQuanPlus() {
                if (mUseDianZiQuanCount >= mRemainDianZiQuan) {
                    // ToastUtil.show(ConfirmOrderCodeActivity.this, "没有剩余的电子券了，客官");
                    ToastUtil.show(ConfirmOrderCodeActivity.this, "抱歉,当前订单限制使用" + mRemainDianZiQuan + "张");
                    return;
                }

                if (mUseDianZiQuanCount >= mTotalDianZiQuan) {
                    ToastUtil.show(ConfirmOrderCodeActivity.this, "抱歉,当前订单限制使用" + mTotalDianZiQuan + "张");
                    return;
                }
                mUseDianZiQuanCount++;
                selectViewRemainCoupons.setDianZiQuanUesCount(mUseDianZiQuanCount);
                setRealPay();
            }

            @Override
            public void onDianZiQuanInput(String num) {
                if (TextUtils.isEmpty(num)) {
                    return;
                }

                if (!num.matches("\\d+")) {
                    ToastUtil.show(ConfirmOrderCodeActivity.this, "请输入正确的格式");
                    selectViewRemainCoupons.setDianZiQuanUesCount(0);
                    return;
                }

                int count = Integer.parseInt(num);
                if (count > mRemainDianZiQuan) {
                    ToastUtil.show(ConfirmOrderCodeActivity.this, "没有剩余的电子券了，客官");
                    selectViewRemainCoupons.setDianZiQuanUesCount(0);
                    return;
                }

                if (count > mTotalDianZiQuan) {
                    ToastUtil.show(ConfirmOrderCodeActivity.this, "抱歉,当前订单限制使用" + mTotalDianZiQuan + "张");
                    selectViewRemainCoupons.setDianZiQuanUesCount(0);
                    return;
                }
                mUseDianZiQuanCount = count;
                setRealPay();
            }
        });
        mSwipeLayouts.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                refreshlayout.finishRefresh();
                loadDataFromSever();
            }
        });
    }


    @Override
    protected void setupActivityComponent() {
        DaggerConfirmOrderCodeComponent
                .builder()
                .appComponent(((AppConfig) getApplication()).getApplicationComponent())
                .confirmOrderCodeModule(new ConfirmOrderCodeModule(this))
                .build()
                .inject(this);
    }

    @Override
    public void setPresenter(ConfirmOrderCodeContract.ConfirmOrderCodeContractPresenter presenter) {
        mPresenter = (ConfirmOrderCodePresenter) presenter;
    }

    @Override
    public void showProgressDialog() {
        progressDialog.show();
        progressDialog.setCancelable(false);
    }

    @Override
    public void closeProgressDialog() {
        progressDialog.hide();
    }

    /**
     * 加载电子券规则成功
     *
     * @param entity
     */
    @Override
    public void onLoadOrderRemainDianZiQuanSucceed(DiZiJuanGuiZei entity) {
        if (entity.getStatus() == 1) {
            tvYunfeiDesc.setText("电子券规则：" + entity.getRows().getExplain());
            mRemainDianZiQuan = entity.getRows().getUseTicket();
            selectViewRemainCoupons.setVisibility(View.VISIBLE);
            //打开电子券规则的显示
            autoLayoutGuizi.setVisibility(View.VISIBLE);
            selectViewRemainCoupons.setDianZiQuanShengYuDesc(mTotalDianZiQuan);
            mTvZuiduo.setVisibility(View.VISIBLE);
            mTvZuiduo.setText("最多使用" + mRemainDianZiQuan + "张");
            //设置默认使用最大的值
            mUseDianZiQuanCount = mRemainDianZiQuan;
            selectViewRemainCoupons.setDianZiQuanUesCount(mUseDianZiQuanCount);
            if (mUseDianZiQuanCount > mRemainDianZiQuan) {
                mUseDianZiQuanCount = mRemainDianZiQuan;
                selectViewRemainCoupons.setDianZiQuanUesCount(mUseDianZiQuanCount);
            }
            setRealPay();
        } else {
            selectViewUseCoupons.setSwitchChecked(false);
            onError(entity.MSG, entity.status);
        }
    }

    @Override
    public void onLoadDianZiQuanSucceed(ShopDianZiJuan dzq) {
        closeProgressDialog();
        if (dzq.getStatus() == 1) {
            mTotalDianZiQuan = dzq.getRows().getBalance();
            KLog.i("电子券余额： " + mTotalDianZiQuan);

        } else {
            onError(dzq.MSG, dzq.getStatus());
        }
    }

    /**
     * 加载订单信息成功 设置数据等
     *
     * @param order
     */
    @Override
    public void getOrderDetailSucceed(MallNewOrder order) {
        closeProgressDialog();
        if (order.status == 1) {
            dismisssEmptylayou();
            if (order.getOrders().size() <= 0 || order.getOrderDetails().size() <= 0) {
                return;
            }
            //设置订单总金额
            mTotalPrice = order.getOrders().get(0).getOrderMoney();
            selectViewProductPrices.settvPrice(setSpan(decimalFormat.format(mTotalPrice)));
            //设置订单类型 1普通订单 2 兑奖订单
            if (order.getOrders().get(0).getOrderType() == 1) {
                selectViewOrderType.settvPrice("普通订单");
                selectViewOrderType.settvPriceColor(Color.parseColor("#323232"));
                selectViewDikouPrices.setVisibility(View.GONE);
            } else if (order.getOrders().get(0).getOrderType() == 2) {
                //设置抵扣金额
                selectViewOrderType.settvPrice("兑奖订单");
                selectViewOrderType.settvPriceColor(Color.parseColor("#323232"));
                mDiKouPrices = order.getDuijiangdikou();
                selectViewDikouPrices.setVisibility(View.VISIBLE);
                selectViewDikouPrices.settvPrice(setSpan("- ¥ " + decimalFormat.format(order.getDuijiangdikou())));
            }
            //设置应付金额
            //mYingfuPrices = mTotalPrice - mDiKouPrices;
            selectViewYingShouPrices.settvPrice(setSpan("¥ " + calculateYinFuMoney()));

            //设置商品数据
            Contains.sureOrderList.clear();
            List<MallNewOrderDetails> orderDetails = order.getOrderDetails();
            for (MallNewOrderDetails mProduct : orderDetails) {
                SureOrderEntity entity = new SureOrderEntity(mProduct.getId() + "", mProduct.getShuliang() + "",
                        "", "", mProduct.getPrice() + "",
                        mProduct.getShangpinName(), "", "", "");
                Contains.sureOrderList.add(entity);
            }
            mAdapter.setNewData(Contains.sureOrderList);
            //设置订单的名字 （支付需要显示）
            if (Contains.sureOrderList.size() > 1) {
                mProductNames = (Contains.sureOrderList.get(0).getGoodsShop() + "...等");
            } else {
                mProductNames = (Contains.sureOrderList.get(0).getGoodsShop());
            }
            //设置金额
            setRealPay();
        } else {
            showEmptylayou();
            onError(order.getMSG(), order.status);

        }
    }

    /**
     * 加载订单信息失败
     */
    @Override
    public void getOrderDetailFailed() {
        closeProgressDialog();
        KLog.e("加载订单信息失败");
        showEmptylayou();


    }

    /**
     * 提交订单成功
     *
     * @param entity
     */
    @Override
    public void confirmOrderSucceed(BaseEntityAll entity) {
        if (entity.status == 1) {
            KLog.i(entity.toString() + "");
            Intent intent = new Intent(this, PayWaySelectActivity.class);
            intent.putExtra("orderId", entity.getRows());
            intent.putExtra("orderMoney", calculateRealPay());
            intent.putExtra("orderShop", mProductNames);
            intent.putExtra("orderBianhao", entity.getRows());
            intent.putExtra("orderDetails", mProductNames);
            intent.putExtra(PayWaySelectActivity.KEY_IN_TYPE, PayWaySelectActivity.IN_TYPE_SAOMA_ORDER);
            PayContain.requestPayModule = PayContain.MODULE_MALL_ORDER;
            startActivity(intent);
            finish();
        } else {
            onError(entity.getMsg(), entity.status);
        }
    }

    /**
     * 设置实际付款金额
     */
    private void setRealPay() {
        tvConfirmOrderPrice.setText(setSpan("¥ " + calculateRealPay()));
    }

    /**
     * 计算实际金额
     *
     * @return
     */
    private String calculateRealPay() {
        if (mTotalPrice == 0) {
            return "0.00";
        }
        mRealPay = mTotalPrice - mUseDianZiQuanCount - mDiKouPrices;
        KLog.i("商品总额" + mTotalPrice + "使用的电子券" + mUseDianZiQuanCount + "抵扣金额" + mDiKouPrices);
        if (mRealPay <= 0) {
            return "0.01";
        }
        return decimalFormat.format(mRealPay);
    }

    /**
     * 计算应付金额
     *
     * @return
     */
    private String calculateYinFuMoney() {
        mYingfuPrices = mTotalPrice - mDiKouPrices;
        return decimalFormat.format(mYingfuPrices);
    }

    /**
     * 确认提交订单的点击事件
     */
    @OnClick(R.id.btn_confirm_order_to_balance)
    public void onViewClicked() {
        if (mTotalPrice == 0) {
            return;
        }
        //确认订单
        //orderBianhao=&orderMoney=&realityMoney=&dianziquan=&receivableMoney=&uuid =
        Map<String, String> map = new HashMap<>();
        map.put("orderBianhao", dingDanBianHao);
        map.put("orderMoney", mTotalPrice + "");
        map.put("realityMoney", calculateRealPay());
        map.put("dianziquan", mUseDianZiQuanCount + "");
        //应收金额=总金额-奖券抵扣金额
        map.put("receivableMoney", calculateYinFuMoney());
        map.put("uuid", Contains.uuid);
        mPresenter.confirmOrder(map);

    }

    private void showEmptylayou() {
        mLyEmptyData.setVisibility(View.VISIBLE);
        mScrollView.setVisibility(View.GONE);
        //设置商品金额为0
        mTotalPrice = 0;
        mBtnConfirmOrderToBalance.setBackgroundColor(getResources().getColor(R.color.demo_desc));
        setRealPay();
    }

    private void dismisssEmptylayou() {
        mLyEmptyData.setVisibility(View.GONE);
        mScrollView.setVisibility(View.VISIBLE);
        mBtnConfirmOrderToBalance.setBackgroundColor(getResources().getColor(R.color.color_ff9934));
    }

    /**
     * 设置金额后两位变小
     *
     * @param str
     * @return
     */

    private SpannableString setSpan(String str) {
        SpannableString spanString = new SpannableString(str);
        AbsoluteSizeSpan span = new AbsoluteSizeSpan(35);
        AbsoluteSizeSpan span1 = new AbsoluteSizeSpan(50);
        spanString.setSpan(span, str.length() - 2, str.length(), Spannable.SPAN_INCLUSIVE_INCLUSIVE);
        spanString.setSpan(span1, 0, str.length() - 3, Spannable.SPAN_INCLUSIVE_INCLUSIVE);
        return spanString;
    }

}