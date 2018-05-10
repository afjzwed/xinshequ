package com.yxld.yxchuangxin.ui.activity.rim;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.yxld.yxchuangxin.R;
import com.yxld.yxchuangxin.Utils.StringUitl;
import com.yxld.yxchuangxin.Utils.ToastUtil;
import com.yxld.yxchuangxin.application.AppConfig;
import com.yxld.yxchuangxin.base.BaseActivity;
import com.yxld.yxchuangxin.base.BaseEntity;
import com.yxld.yxchuangxin.contain.Contains;
import com.yxld.yxchuangxin.entity.CxwyOrderInfo;
import com.yxld.yxchuangxin.entity.ShopCarList;
import com.yxld.yxchuangxin.ui.activity.rim.component.DaggerBusinessOrderDetailComponent;
import com.yxld.yxchuangxin.ui.activity.rim.contract.BusinessOrderDetailContract;
import com.yxld.yxchuangxin.ui.activity.rim.module.BusinessOrderDetailModule;
import com.yxld.yxchuangxin.ui.activity.rim.presenter.BusinessOrderDetailPresenter;
import com.yxld.yxchuangxin.ui.adapter.rim.PushOrderDetailVerticalAdapter;
import com.yxld.yxchuangxin.ui.adapter.rim.PushProductHorizenAdapter;
import com.zhy.autolayout.AutoLinearLayout;
import com.zhy.autolayout.AutoRelativeLayout;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.refactor.library.SmoothCheckBox;


/**
 * @author hu
 * @Package com.yxld.yxchuangxin.ui.activity.rim
 * @Description: $description
 * @date 2017/06/20 14:05:57
 */

public class BusinessOrderDetailActivity extends BaseActivity implements BusinessOrderDetailContract.View, View
        .OnClickListener {

    @Inject
    BusinessOrderDetailPresenter mPresenter;
    @BindView(R.id.tv_order_number)
    TextView tvOrderNumber;
    @BindView(R.id.tv_order_state)
    TextView tvOrderState;
    @BindView(R.id.tv_push_name)
    TextView tvPushName;
    @BindView(R.id.tv_push_phone)
    TextView tvPushPhone;
    @BindView(R.id.tv_push_address)
    TextView tvPushAddress;
    @BindView(R.id.rl_pick_address)
    AutoRelativeLayout rlPickAddress;
    @BindView(R.id.tv_push_pick_time)
    TextView tvPushPickTime;
    @BindView(R.id.tv_choose_time)
    TextView tvChooseTime;
    @BindView(R.id.tv_business_name)
    TextView tvBusinessName;
    @BindView(R.id.modify_product)
    TextView modifyProduct;
    @BindView(R.id.recyclerview_prudoct_horizen)
    RecyclerView recyclerviewPrudoctHorizen;
    @BindView(R.id.tv_product_count)
    TextView tvProductCount;
    @BindView(R.id.tv1)
    TextView tv1;
    @BindView(R.id.tv_remark)
    TextView tvRemark;
    @BindView(R.id.tv2)
    TextView tv2;
    @BindView(R.id.tv_order_time)
    TextView tvOrderTime;
    @BindView(R.id.tv3)
    TextView tv3;
    @BindView(R.id.tv_order_pay_style)
    TextView tvOrderPayStyle;
    @BindView(R.id.ll_pay_style)
    AutoRelativeLayout llPayStyle;
    @BindView(R.id.tv_push_price_count)
    TextView tvPushPriceCount;
    @BindView(R.id.tv_send_money)
    TextView tvSendMoney;
    @BindView(R.id.tv_money_count)
    TextView tvMoneyCount;
    @BindView(R.id.btn_order_connect_business)
    TextView btnOrderConnectBusiness;
    @BindView(R.id.rl_connect_business)
    AutoRelativeLayout rlConnectBusiness;
    @BindView(R.id.btn_order_connect_sender)
    TextView btnOrderConnectSender;
    @BindView(R.id.rl_connect_sender)
    AutoRelativeLayout rlConnectSender;
    @BindView(R.id.ll_link)
    AutoLinearLayout llLink;
    @BindView(R.id.btn_order_tousu)
    Button btnOrderTousu;
    @BindView(R.id.btn_order_genzong)
    Button btnOrderGenzong;
    @BindView(R.id.btn_order_operation)
    Button btnOrderOperation;
    @BindView(R.id.ll_operation)
    AutoLinearLayout llOperation;
    @BindView(R.id.tv_comfirm_pay)
    TextView tvComfirmPay;
    @BindView(R.id.tv_cancal_pay)
    TextView tvCancalPay;
    @BindView(R.id.tv_yuji)
    TextView tvYuji;
    @BindView(R.id.tv_fuwu)
    TextView tvFuwu;
    @BindView(R.id.iv_icon1)
    ImageView ivIcon1;
    @BindView(R.id.checkBoxAliPay)
    SmoothCheckBox checkBoxAliPay;
    @BindView(R.id.iv_icon2)
    ImageView ivIcon2;
    @BindView(R.id.checkBoxWeiXin)
    SmoothCheckBox checkBoxWeiXin;
    @BindView(R.id.iv_icon3)
    ImageView ivIcon3;
    @BindView(R.id.checkBoxYl)
    SmoothCheckBox checkBoxYl;
    @BindView(R.id.pop_pay)
    AutoLinearLayout popPay;
    @BindView(R.id.pop_pay_mask)
    AutoRelativeLayout popPayMask;
    @BindView(R.id.tv_cancal_order_1)
    TextView tvCancalOrder1;
    @BindView(R.id.tv_cancal_order_2)
    TextView tvCancalOrder2;
    @BindView(R.id.tv_cancal_order_other)
    TextView tvCancalOrderOther;
    @BindView(R.id.tv_cancal_order_cancal)
    TextView tvCancalOrderCancal;
    @BindView(R.id.pop_cancal_order)
    AutoLinearLayout popCancalOrder;
    @BindView(R.id.pop_cancal_order_mask)
    AutoRelativeLayout popCancalOrderMask;
    @BindView(R.id.tv_dazhe_name)
    TextView mTvDazheName;
    @BindView(R.id.tv_dazhe_money)
    TextView mTvDazheMoney;
    @BindView(R.id.rl_dazhe)
    AutoRelativeLayout mRldazhe;
    private String businessNumber;
    private String orderNumber;
    private int businessProduceType; //1 商家2 服务
    private String businessPhone; //1 商家电话
    private String orderSendPhone; //1 配送员电话

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initView() {
        setContentView(R.layout.activity_business_order_detail);
        ButterKnife.bind(this);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //  recyclerviewPrudoctHorizen.setNestedScrollingEnabled(false);//设置嵌套滑动不能用
    }

    @Override
    protected void initData() {
        //?orderNumber=%1$s&uuId=%2$s
        businessNumber = getIntent().getStringExtra("businessNumber");
        orderNumber = getIntent().getStringExtra("orderNumber");
        businessProduceType = getIntent().getIntExtra("businessProduceType", 1);
        if (businessProduceType == 1) {
            //商家
            tvFuwu.setText("配送费:");
            tvYuji.setText("商品总额:");

        } else {

        }
        Map<String, String> map = new HashMap<>();
        map.put("orderNumber", getIntent().getStringExtra("orderNumber"));
        map.put("uuId", Contains.uuid);
        mPresenter.getBusinessOrderDetail(map);
        //订单已经提交，需要关闭这些界面
        AppConfig.getInstance().mAppActivityManager.finishActivity(BusinessPushProductActivity.class);
        AppConfig.getInstance().mAppActivityManager.finishActivity(BusinessActivity.class);
        AppConfig.getInstance().mAppActivityManager.finishActivity(BusinessListActivity.class);
        btnOrderOperation.setOnClickListener(this);
        btnOrderTousu.setOnClickListener(this);
        btnOrderGenzong.setOnClickListener(this);
        btnOrderConnectBusiness.setOnClickListener(this);
        btnOrderConnectSender.setOnClickListener(this);
    }

    @Override
    protected void setupActivityComponent() {
        DaggerBusinessOrderDetailComponent.builder().appComponent(((AppConfig) getApplication())
                .getApplicationComponent()).businessOrderDetailModule(new BusinessOrderDetailModule(this)).build()
                .inject(this);
    }

    @Override
    public void setPresenter(BusinessOrderDetailContract.BusinessOrderDetailContractPresenter presenter) {
        mPresenter = (BusinessOrderDetailPresenter) presenter;
    }

    @Override
    protected void onRestart() {
        Map<String, String> map = new HashMap<>();
        map.put("orderNumber", getIntent().getStringExtra("orderNumber"));
        map.put("uuId", Contains.uuid);
        mPresenter.getBusinessOrderDetail(map);
        super.onRestart();
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
    public void setOrderInfo(CxwyOrderInfo info, List<ShopCarList.ShopCarBean> spInfos) {
        /**
         * 待接单2
         * 待取件10
         * 待发货3
         * 已完成7
         * 待付款1
         * 清洗中11
         * 送还中12
         * 待回复6或7
         * 待评价5
         * 已取消9
         *待确认13  4
         */
        /**
         * 订单状态（1-待付款；2-待接单；3-待发货；
         * 4-待确认；5-待评价；6-订单完成；
         * 7-待回复；8-已拒单；9-取消订单；
         * 10-待取件；11-处理中；12-送还中；13-退款中；14-服务中）
         */
        LinearLayoutManager lm = new LinearLayoutManager(BusinessOrderDetailActivity.this);

        PushProductHorizenAdapter pushProductHorizenAdapter = new PushProductHorizenAdapter(spInfos);
        PushOrderDetailVerticalAdapter pushOrderDetailVerticalAdapter = new PushOrderDetailVerticalAdapter(spInfos);

        //根据商品和服务显示不同的预约时间
        if (businessProduceType == 1) {
            tvPushPickTime.setText("配送时间");
        } else {
            tvPushPickTime.setText("取件时间");
        }
        //初始化商家电话 和配送员电话
        businessPhone = info.getData().getOrder().getBusinessPhone();
        orderSendPhone = info.getData().getOrder().getOrderSenderPhone();

        switch (info.getData().getOrder().getOrderStatus()) {
            case 1:
                modifyProduct.setVisibility(View.VISIBLE);
                tvOrderState.setText("待支付");
                lm.setOrientation(LinearLayoutManager.VERTICAL);
                recyclerviewPrudoctHorizen.setLayoutManager(lm);
                recyclerviewPrudoctHorizen.setAdapter(pushProductHorizenAdapter);
                modifyProduct.setOnClickListener(BusinessOrderDetailActivity.this);
                tvComfirmPay.setOnClickListener(BusinessOrderDetailActivity.this);
                tvCancalPay.setOnClickListener(BusinessOrderDetailActivity.this);
                btnOrderOperation.setVisibility(View.VISIBLE);
                btnOrderOperation.setText("立即支付");
//                checkBoxYl.setOnCheckedChangeListener(yl);
//                checkBoxWeiXin.setOnCheckedChangeListener(weixin);
//                checkBoxAliPay.setOnCheckedChangeListener(alipay);
                btnOrderOperation.setTextColor(getResources().getColor(R.color.color_0079C2));
                btnOrderOperation.setBackground(getResources().getDrawable(R.drawable.buttom_border_14_0079c2_all));
                break;
            case 2:
                modifyProduct.setVisibility(View.VISIBLE);
                tvOrderState.setText("待接单");
                lm.setOrientation(LinearLayoutManager.VERTICAL);
                recyclerviewPrudoctHorizen.setLayoutManager(lm);
                recyclerviewPrudoctHorizen.setAdapter(pushProductHorizenAdapter);
                modifyProduct.setOnClickListener(BusinessOrderDetailActivity.this);
                btnOrderOperation.setVisibility(View.VISIBLE);
                btnOrderOperation.setText("取消订单");
                btnOrderOperation.setOnClickListener(BusinessOrderDetailActivity.this);
                tvCancalPay.setOnClickListener(BusinessOrderDetailActivity.this);
                tvComfirmPay.setOnClickListener(BusinessOrderDetailActivity.this);
                break;
            case 3:
                tvOrderState.setText("待发货");
                lm.setOrientation(LinearLayoutManager.VERTICAL);
                recyclerviewPrudoctHorizen.setLayoutManager(lm);
                recyclerviewPrudoctHorizen.setAdapter(pushProductHorizenAdapter);
                rlConnectSender.setVisibility(View.GONE);
                llOperation.setVisibility(View.GONE);
                break;
            case 4:
                tvOrderState.setText("待确认");
                lm.setOrientation(LinearLayoutManager.VERTICAL);
                recyclerviewPrudoctHorizen.setLayoutManager(lm);
                recyclerviewPrudoctHorizen.setAdapter(pushProductHorizenAdapter);
                llPayStyle.setVisibility(View.VISIBLE);
                try {
                    tvOrderPayStyle.setText(info.getData().getOrder().getOrderPayType());
                } catch (Exception e) {

                }
                break;
            case 5:
                tvOrderState.setText("待评价");
                lm.setOrientation(LinearLayoutManager.VERTICAL);
                recyclerviewPrudoctHorizen.setLayoutManager(lm);
                recyclerviewPrudoctHorizen.setAdapter(pushProductHorizenAdapter);
                tvProductCount.setVisibility(View.GONE);
                rlConnectSender.setVisibility(View.VISIBLE);
                btnOrderOperation.setVisibility(View.VISIBLE);
                llPayStyle.setVisibility(View.VISIBLE);
                btnOrderOperation.setText("立即评价");
                tvPushPickTime.setText("预计送回");
                try {
                    tvOrderPayStyle.setText(info.getData().getOrder().getOrderPayType());
                } catch (Exception e) {

                }
                btnOrderOperation.setTextColor(getResources().getColor(R.color.color_0079C2));
                btnOrderOperation.setBackground(getResources().getDrawable(R.drawable.buttom_border_14_0079c2_all));
                break;
            case 6:
                tvOrderState.setText("已完成");
                lm.setOrientation(LinearLayoutManager.VERTICAL);
                recyclerviewPrudoctHorizen.setLayoutManager(lm);
                llPayStyle.setVisibility(View.VISIBLE);
                try {
                    tvOrderPayStyle.setText(info.getData().getOrder().getOrderPayType());
                } catch (Exception e) {

                }
                recyclerviewPrudoctHorizen.setAdapter(pushProductHorizenAdapter);
                tvProductCount.setVisibility(View.GONE);
                rlConnectSender.setVisibility(View.VISIBLE);
                btnOrderOperation.setVisibility(View.GONE);
                break;
            case 7:
                tvOrderState.setText("待回复");
                lm.setOrientation(LinearLayoutManager.VERTICAL);
                recyclerviewPrudoctHorizen.setLayoutManager(lm);
                recyclerviewPrudoctHorizen.setAdapter(pushProductHorizenAdapter);
                tvProductCount.setVisibility(View.GONE);
                llPayStyle.setVisibility(View.VISIBLE);
                try {
                    tvOrderPayStyle.setText(info.getData().getOrder().getOrderPayType());
                } catch (Exception e) {

                }
                rlConnectSender.setVisibility(View.VISIBLE);
                btnOrderOperation.setVisibility(View.GONE);
                break;
            case 8:
                tvOrderState.setText("已拒单");
                lm.setOrientation(LinearLayoutManager.VERTICAL);
                recyclerviewPrudoctHorizen.setLayoutManager(lm);
                recyclerviewPrudoctHorizen.setAdapter(pushProductHorizenAdapter);
                break;
            case 9:
                lm.setOrientation(LinearLayoutManager.VERTICAL);
                recyclerviewPrudoctHorizen.setLayoutManager(lm);
                modifyProduct.setVisibility(View.GONE);
                recyclerviewPrudoctHorizen.setAdapter(pushProductHorizenAdapter);
                tvOrderState.setText("已取消");
                llLink.setVisibility(View.GONE);
                llOperation.setVisibility(View.GONE);
                break;
            case 10:
                tvOrderState.setText("待取件");
                lm.setOrientation(LinearLayoutManager.VERTICAL);
                recyclerviewPrudoctHorizen.setLayoutManager(lm);
                recyclerviewPrudoctHorizen.setAdapter(pushProductHorizenAdapter);
                modifyProduct.setVisibility(View.GONE);
                btnOrderOperation.setVisibility(View.GONE);
                rlConnectSender.setVisibility(View.VISIBLE);
                modifyProduct.setVisibility(View.GONE);
                break;

            case 11:
                tvOrderState.setText("服务中");
                lm.setOrientation(LinearLayoutManager.VERTICAL);
                recyclerviewPrudoctHorizen.setLayoutManager(lm);
                recyclerviewPrudoctHorizen.setAdapter(pushProductHorizenAdapter);
                tvProductCount.setVisibility(View.GONE);
                llPayStyle.setVisibility(View.VISIBLE);
                tvPushPickTime.setText("预计送回");
                try {
                    tvOrderPayStyle.setText(info.getData().getOrder().getOrderPayType());
                } catch (Exception e) {

                }
                rlConnectSender.setVisibility(View.VISIBLE);
                break;
            case 12:
                tvOrderState.setText("送还中");
                lm.setOrientation(LinearLayoutManager.VERTICAL);
                recyclerviewPrudoctHorizen.setLayoutManager(lm);
                recyclerviewPrudoctHorizen.setAdapter(pushProductHorizenAdapter);
                tvProductCount.setVisibility(View.GONE);
                llPayStyle.setVisibility(View.VISIBLE);
                tvPushPickTime.setText("预计送回");
                try {
                    tvOrderPayStyle.setText(info.getData().getOrder().getOrderPayType());
                } catch (Exception e) {

                }
                rlConnectSender.setVisibility(View.VISIBLE);
                break;
            case 13:
                tvOrderState.setText("退款中");
                lm.setOrientation(LinearLayoutManager.VERTICAL);
                recyclerviewPrudoctHorizen.setLayoutManager(lm);
                recyclerviewPrudoctHorizen.setAdapter(pushProductHorizenAdapter);
                tvProductCount.setVisibility(View.GONE);
                rlConnectSender.setVisibility(View.VISIBLE);
                btnOrderOperation.setVisibility(View.VISIBLE);
                llPayStyle.setVisibility(View.VISIBLE);
                btnOrderOperation.setVisibility(View.VISIBLE);
                btnOrderOperation.setText("确认送达");
                btnOrderOperation.setTextColor(getResources().getColor(R.color.color_0079C2));
                btnOrderOperation.setBackground(getResources().getDrawable(R.drawable.buttom_border_14_0079c2_all));
                tvPushPickTime.setText("预计送回");
                try {
                    tvOrderPayStyle.setText(info.getData().getOrder().getOrderPayType());
                } catch (Exception e) {

                }
                break;
            case 14:
                tvOrderState.setText("服务中");
                lm.setOrientation(LinearLayoutManager.VERTICAL);
                recyclerviewPrudoctHorizen.setLayoutManager(lm);
                recyclerviewPrudoctHorizen.setAdapter(pushProductHorizenAdapter);
                break;
            default:
                break;
        }
        if (businessProduceType == 1) {
            //商家任何状态不显示修改订单
            modifyProduct.setVisibility(View.GONE);

        }
        tvOrderNumber.setText("订单编号：" + info.getData().getOrder().getOrderNumber());
        String time = info.getData().getOrder().getOrderBespeakTime();
        if (StringUitl.isNoEmpty(time)) {
            tvChooseTime.setText(time);
        }
//        try {
//            if (time.contains(",")) {
//                tvChooseTime.setText(time.substring(time.indexOf(",") + 1, time.length()));
//            } else {
//                tvChooseTime.setText(time);
//            }
//        } catch (Exception e) {
//            tvChooseTime.setText(time);
//        }

        tvPushName.setText(info.getData().getOrder().getOrderUserName());
        tvPushPhone.setText(info.getData().getOrder().getOrderUserPhone());
        tvPushAddress.setText(info.getData().getOrder().getOrderUserAddress());
        tvBusinessName.setText(info.getData().getOrder().getOrderBusinessName());
        tvPushPriceCount.setText("¥ " + StringUitl.get2xiaoshu(info.getData().getOrder().getOrderMoney()));
        tvSendMoney.setText("¥ " + StringUitl.get2xiaoshu(info.getData().getOrder().getOrderSendMoney()));
        tvRemark.setText(info.getData().getOrder().getOrderRemark());
        tvMoneyCount.setText("小计:¥ " + StringUitl.get2xiaoshu((info.getData().getOrder().getOrderFactMoney())));
        tvOrderTime.setText(info.getData().getOrder().getOrderOrderTime());
        mTvDazheName.setText(info.getData().getOrder().getDiscountContent());
        mTvDazheMoney.setText("- ¥ " + StringUitl.get2xiaoshu(info.getData().getOrder().getDiscountMoney()));
//        if (StringUitl.get2xiaoshu(info.getData().getOrder().getDiscountMoney()).equals("0.00")) {
//            mRldazhe.setVisibility(View.GONE);
//        }

    }

    @Override
    public void setCountAndPrice(String count) {
        tvProductCount.setText("共" + count + "件");
    }

    @Override
    public void detailsToCarSuccess(BaseEntity baseEntity) {
        if (baseEntity.status == 1) {
            Intent intent = new Intent(BusinessOrderDetailActivity.this, BusinessActivity.class);
            intent.putExtra("flag", "modify");
            intent.putExtra("businessNumber", businessNumber);
            intent.putExtra("orderNumber", orderNumber);
            startActivityForResult(intent, 0);
        } else {
            onError(baseEntity.msg, baseEntity.status);
        }
    }

    @Override
    protected void onDestroy() {
        Contains.chooseClassifyList.clear();
        super.onDestroy();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.modify_product:
                //修改订单
                Map<String, String> map = new HashMap<>();
                map.put("uuId", Contains.uuid);
                map.put("orderId", orderNumber);
                map.put("businessNumber", businessNumber);
                mPresenter.detailToCar(map);
                break;
            case R.id.btn_order_operation:
                mPresenter.opreateOrder(btnOrderOperation);
                break;
            case R.id.tv_cancal_pay:
                popPayMask.setVisibility(View.GONE);
                break;
            case R.id.pop_pay_mask:
                //订单支付
//          showOrOffPayPop();
                break;
            case R.id.btn_order_connect_business:
                //联系商家
                if (StringUitl.isEmpty(businessPhone)) {
                    ToastUtil.show(BusinessOrderDetailActivity.this, "暂无商家联系方式");
                    return;
                }
                callPhone(businessPhone);
                break;
            case R.id.btn_order_connect_sender:
                if (StringUitl.isEmpty(orderSendPhone)) {
                    ToastUtil.show(BusinessOrderDetailActivity.this, "暂无配送员联系方式");
                    return;
                }
                //联系配送员
                callPhone(orderSendPhone);
                break;
            case R.id.pop_pay:
                break;
            case R.id.tv_comfirm_pay:
                if (checkBoxYl.isChecked()) {
                    Toast.makeText(this, "暂时无法支持银联支付", Toast.LENGTH_SHORT).show();
                } else if (checkBoxWeiXin.isChecked()) {
//                    surePay();
                } else if (checkBoxAliPay.isChecked()) {
//                    surePay();
                }
                break;
            case R.id.btn_order_tousu:
                mPresenter.Complain();
                break;
            case R.id.btn_order_genzong:
                mPresenter.getOrderDynamic();
                break;
        }
    }

    public void showOrOffPayPop(View v) {

        popPayMask.setVisibility(View.VISIBLE);
    }

    //联系电话
    private void callPhone(String phone) {
        if (StringUitl.isEmpty(phone)) {
            return;
        }
        Intent intent = new Intent(Intent.ACTION_DIAL);
        Uri data = Uri.parse("tel:" + phone);
        intent.setData(data);
        startActivity(intent);
    }
}