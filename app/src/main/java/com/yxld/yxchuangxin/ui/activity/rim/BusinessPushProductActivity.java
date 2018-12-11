package com.yxld.yxchuangxin.ui.activity.rim;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.alipay.sdk.app.PayTask;
import com.socks.library.KLog;
import com.yxld.yxchuangxin.R;
import com.yxld.yxchuangxin.Utils.AlipyUtil;
import com.yxld.yxchuangxin.Utils.CxUtil;
import com.yxld.yxchuangxin.Utils.HttpUtils;
import com.yxld.yxchuangxin.Utils.PayResult;
import com.yxld.yxchuangxin.Utils.StringUitl;
import com.yxld.yxchuangxin.Utils.TimePickUtil;
import com.yxld.yxchuangxin.Utils.ToastUtil;
import com.yxld.yxchuangxin.Utils.YinLianPayUtil;
import com.yxld.yxchuangxin.application.AppConfig;
import com.yxld.yxchuangxin.base.BaseActivity;
import com.yxld.yxchuangxin.contain.PayContain;
import com.yxld.yxchuangxin.entity.PushOrder;
import com.yxld.yxchuangxin.entity.RimActivityDiscount;
import com.yxld.yxchuangxin.entity.ShopCarList;
import com.yxld.yxchuangxin.ui.activity.rim.component.DaggerBusinessPushProductComponent;
import com.yxld.yxchuangxin.ui.activity.rim.contract.BusinessPushProductContract;
import com.yxld.yxchuangxin.ui.activity.rim.module.BusinessPushProductModule;
import com.yxld.yxchuangxin.ui.activity.rim.presenter.BusinessPushProductPresenter;
import com.yxld.yxchuangxin.ui.adapter.rim.PushProductHorizenAdapter;
import com.yxld.yxchuangxin.xsq.wxapi.WechatPay;
import com.zhy.autolayout.AutoLinearLayout;
import com.zhy.autolayout.AutoRelativeLayout;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.refactor.library.SmoothCheckBox;

import static com.yxld.yxchuangxin.R.id.pop_pay;

/**
 * @author hu
 * @Package com.yxld.yxchuangxin.ui.activity.rim
 * @Description: $description
 * @date 2017/06/19 11:24:46
 */

public class BusinessPushProductActivity extends BaseActivity implements
        BusinessPushProductContract.View, TimePickUtil.OnSubmitClickListener {

    @Inject
    BusinessPushProductPresenter mPresenter;
    @Inject
    PushProductHorizenAdapter pushProductHorizenAdapter;
    @BindView(R.id.tv_push_name)
    TextView tvPushName;
    @BindView(R.id.tv_push_phone)
    TextView tvPushPhone;
    @BindView(R.id.tv_push_address)
    TextView tvPushAddress;
    @BindView(R.id.llAddrTip)
    LinearLayout llAddrTip;
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
    @BindView(R.id.et_remark)
    EditText etRemark;
    @BindView(R.id.tv_push_price_count)
    TextView tvPushPriceCount;
    @BindView(R.id.bt_push)
    Button btPush;
    @BindView(R.id.tv_hint)
    TextView tvHint;
    @BindView(R.id.tv_service_price)
    TextView tvServicePrice;
    @BindView(R.id.tv_discount_name)
    TextView tvDiscountName;
    @BindView(R.id.tv_discount_price)
    TextView tvDiscountPrice;
    @BindView(R.id.tv_final_money)
    TextView tvFinalMoney;
    @BindView(R.id.tv_fuwufei)
    TextView tvFuwufei;
    @BindView(R.id.tv_yuji)
    TextView tvYuji;
    private String businessNumber;//店铺序列号
    private Map<String, String> mMap;//提交订单的数据
    private List<RimActivityDiscount.DataBean.DeliversBean> mDeliversBeenList;
    private double spTotalMoney; //商品总金额
    private RimActivityDiscount.DataBean.DeliversBean mDeliversBean;
    private RimActivityDiscount mRimActivityDiscount;
    private List<ShopCarList.ShopCarBean> mShopCarBeanList;

    private String orderBianhao;
    private String orderShop;
    private String hejiMoney;
    private int businessProduceType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initView() {
        setContentView(R.layout.activity_business_push_product);
        ButterKnife.bind(this);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        businessNumber = getIntent().getStringExtra("businessNumber");
        mShopCarBeanList = getIntent().getParcelableArrayListExtra("ShopCarBeanList");
        businessProduceType = getIntent().getIntExtra("businessProduceType", 0);
        if (mShopCarBeanList.size() > 1) {
            orderShop = mShopCarBeanList.get(0).getProductName() + "...等";
        } else {
            orderShop = mShopCarBeanList.get(0).getProductName();
        }
        if (businessProduceType == 1) {
            btPush.setText("立即支付");
            toolbar.setTitle("提交订单");
            tvFuwufei.setText("配送费:");
            tvYuji.setText("商品总额:");
            tvPushPickTime.setText("配送时间");
            tvChooseTime.setText("请选择上门配送时间");
        } else if (businessProduceType == 2) {
            btPush.setText("提交预约");
            tvPushPickTime.setText("取件时间");
            tvChooseTime.setText("请选择上门取件时间");
        }
        // recyclerviewPrudoctHorizen.setNestedScrollingEnabled(false);//设置嵌套滑动不能用
        mPresenter.getAddress();
        mPresenter.getBusinessInfoAndProduct(businessNumber, mShopCarBeanList);
    }

    @Override
    protected void initData() {
        mMap = new HashMap<>();

        StringUitl.forbidEmoji(etRemark, 30, this);//限制输入字数和表情
    }

    @Override
    protected void setupActivityComponent() {
        DaggerBusinessPushProductComponent.builder().appComponent(((AppConfig) getApplication())
                .getApplicationComponent()).businessPushProductModule(new
                BusinessPushProductModule(this)).build().inject(this);
    }

    @Override
    public void setPresenter(BusinessPushProductContract.BusinessPushProductContractPresenter
                                         presenter) {
        mPresenter = (BusinessPushProductPresenter) presenter;
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
    public void upDateAddress(String name, String shouji, String roomInfo) {
        // 更新默认地址 ,如果收货人、联系电话和地址为空，则显示选择地址提示框
        if (name == null || shouji == null || roomInfo == null) {
            tvPushName.setText("");
            tvPushName.setVisibility(View.GONE);
            tvPushPhone.setText("");
            tvPushPhone.setVisibility(View.GONE);
            tvPushAddress.setText("");
            tvPushAddress.setVisibility(View.GONE);
            tvHint.setVisibility(View.GONE);
            llAddrTip.setVisibility(View.VISIBLE);
            return;
        }
        tvPushName.setVisibility(View.VISIBLE);
        tvPushName.setText(name);
        tvPushPhone.setVisibility(View.VISIBLE);
        tvPushPhone.setText(shouji);
        tvPushAddress.setVisibility(View.VISIBLE);
        tvPushAddress.setText(roomInfo);
        tvHint.setVisibility(View.VISIBLE);
        llAddrTip.setVisibility(View.GONE);
    }

    @Override
    public void upDateBusinessInfoAndProduct(List<ShopCarList.ShopCarBean> chooseClassifyList) {
        pushProductHorizenAdapter.setNewData(chooseClassifyList);
        recyclerviewPrudoctHorizen.setAdapter(pushProductHorizenAdapter);
        mPresenter.getAllPriceAndProductCount(businessNumber);//获取打折信息
    }

    @Override
    public void setProductInfo(String count, String price, String businessName) {
        tvProductCount.setText("共" + count + "件");
        tvBusinessName.setText(businessName);
        spTotalMoney = Double.parseDouble(price);
        tvPushPriceCount.setText("¥ " + StringUitl.get2xiaoshu(spTotalMoney));

    }

    @Override
    public void setDiscountInfo(RimActivityDiscount data) {
        if ("1".equals(data.getSuccess())) {
            mRimActivityDiscount = data;
            tvDiscountPrice.setText("-¥ " + StringUitl.get2xiaoshu(data.getData()
                    .getDiscountMoney()) + "");
            tvDiscountName.setText(data.getData().getActivityContent());
            if (data.getData().getDelivers() != null && data.getData().getDelivers().size() > 0) {
                mDeliversBean = data.getData().getDelivers().get(0);
                getXiaoJi();
                tvServicePrice.setText("¥ " + StringUitl.get2xiaoshu(data.getData().getDelivers()
                        .get(0).getDistributionFee()));
            } else {

            }
        } else {
            onError(data.msg);
        }
    }

    /**
     * 提交订单成功
     *
     * @param data
     */
    @Override
    public void commitOrdSuccess(PushOrder data) {
        if (data.status == 1) {
            orderBianhao = data.getData();
            KLog.i("onSuccesse");
            if (businessProduceType == 1) {
                //商家
                // TODO: 2018/12/11 暂时关闭支付
                ToastUtil.showShort("支付通道暂时关闭");
//                showPayPop();
            } else if (businessProduceType == 2) {
                //服务
                startToOrderListAcitivity();
            }

        } else {
            onError(data.msg, data.status);
        }
    }

    private void startToOrderListAcitivity() {
        Intent intent = new Intent(BusinessPushProductActivity.this, RimOrderListActivityActivity
                .class);
        startActivity(intent);
        AppConfig.getInstance().mAppActivityManager.finishActivity(BusinessActivity.class);
        finish();
    }


    /**
     * 支付方式 0 支付宝 1 微信 2 银联
     */
    private Integer paymentMethod = 1;//默认支付方式为微信
    private final int PAY_ALIPY = 0;
    private final int PAY_WEIXIN = 1;
    private final int PAY_YINLIAN = 2;
    private AlipyUtil alipyUtil = new AlipyUtil();
    PopupWindow payPop;

    private void showPayPop() {
        payPop = new PopupWindow(this);
        View view = getLayoutInflater().inflate(R.layout.rim_order_pay_popupwindows, null);
        AutoLinearLayout llPaypopup = (AutoLinearLayout) view.findViewById(pop_pay);

        payPop.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        payPop.setHeight(ViewGroup.LayoutParams.MATCH_PARENT);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            KLog.i("have bg");
            payPop.setBackgroundDrawable(new BitmapDrawable());
        } else {
            KLog.i("nohave bg");
            payPop.setBackgroundDrawable(null);
        }
        payPop.setFocusable(false);
        payPop.setOutsideTouchable(false);
        payPop.setContentView(view);
        //因为某些机型是虚拟按键的,所以要加上以下设置防止挡住按键.
        payPop.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        //屏蔽back 不能设置背景
        view.setFocusableInTouchMode(true);
        view.setFocusable(true);
        view.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                KLog.i("222222222");
                return false;
            }
        });

        TextView comfirmPay = (TextView) view.findViewById(R.id.tv_comfirm_pay);
        TextView cancalPay = (TextView) view.findViewById(R.id.tv_cancal_pay);
        SmoothCheckBox alipyChecked = (SmoothCheckBox) view.findViewById(R.id.checkBoxAliPay);
        SmoothCheckBox weixinChecked = (SmoothCheckBox) view.findViewById(R.id.checkBoxWeiXin);
        SmoothCheckBox yinlianChecked = (SmoothCheckBox) view.findViewById(R.id.checkBoxYl);

        switch (paymentMethod) {
            case PAY_ALIPY:
                alipyChecked.setChecked(true, true);
                break;
            case PAY_WEIXIN:
                weixinChecked.setChecked(true, true);
                break;
            case PAY_YINLIAN:
                yinlianChecked.setChecked(true, true);
                break;
            default:
                break;
        }

        cancalPay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startToOrderListAcitivity();
                payPop.dismiss();
                llPaypopup.clearAnimation();
            }
        });
        comfirmPay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                payPop.dismiss();
                llPaypopup.clearAnimation();
                payStart(paymentMethod);
            }
        });

        alipyChecked.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alipyChecked.setChecked(!alipyChecked.isChecked(), true);
                if (alipyChecked.isChecked()) {
                    paymentMethod = PAY_ALIPY;
                    if (weixinChecked.isChecked()) {
                        weixinChecked.setChecked(false, true);
                    }
                    if (yinlianChecked.isChecked()) {
                        yinlianChecked.setChecked(false, true);
                    }
                } else {
                    paymentMethod = null;
                }
            }
        });
        weixinChecked.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                weixinChecked.setChecked(!weixinChecked.isChecked(), true);
                if (weixinChecked.isChecked()) {
                    paymentMethod = PAY_WEIXIN;
                    if (alipyChecked.isChecked()) {
                        alipyChecked.setChecked(false, true);
                    }
                    if (yinlianChecked.isChecked()) {
                        yinlianChecked.setChecked(false, true);
                    }
                } else {
                    paymentMethod = null;
                }
            }
        });

        yinlianChecked.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                yinlianChecked.setChecked(!yinlianChecked.isChecked(), true);
                if (yinlianChecked.isChecked()) {
                    paymentMethod = PAY_YINLIAN;
                    if (alipyChecked.isChecked()) {
                        alipyChecked.setChecked(false, true);
                    }
                    if (weixinChecked.isChecked()) {
                        weixinChecked.setChecked(false, true);
                    }
                } else {
                    paymentMethod = null;
                }
            }
        });
        llPaypopup.startAnimation(AnimationUtils.loadAnimation(this, R.anim.activity_translate_in));

        payPop.showAtLocation(view, Gravity.BOTTOM, 0, 0);
    }

    @Override
    public void onBackPressed() {
        KLog.i("6666666");
        if (payPop != null && payPop.isShowing()) {
            return;
        }
        super.onBackPressed();
    }

    private void payStart(Integer paymentMethod) {
        if (paymentMethod == null) {
            ToastUtil.showShort("请选择支付方式");
            return;
        }
        if (paymentMethod == PAY_ALIPY) {
            //支付宝支付
            alipayPay();
        } else if (paymentMethod == PAY_WEIXIN) {
            //微信支付
            weixinPay();
        } else if (paymentMethod == PAY_YINLIAN) {
            //银联
            yinlianPay();
        }

    }

    private YinLianPayUtil mYinLianPayUtil = new YinLianPayUtil();

    /**************************************银联支付*******************************/
    private void yinlianPay() {
        //银联的钱单位 分    ×100
        mYinLianPayUtil.getTN(orderBianhao, "1", new HttpUtils.CallBack() {
            @Override
            public void onRequestComplete(String result) {
                KLog.i(result + "---");
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (StringUitl.isEmpty(result)) {
                            ToastUtil.showShort("验签失败");
                            return;
                        }
                        mYinLianPayUtil.doStartUnionPayPlugin(BusinessPushProductActivity.this,
                                result, "01");
                    }
                });

            }
        });
        //  mYinLianPayUtil.doStartUnionPayPlugin(this,"123456", "01");
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        mYinLianPayUtil.yinLianCallBack(data, new YinLianPayUtil.YinLianPayBack() {
            @Override
            public void onSuccess(String msg) {
                ToastUtil.showShort(msg);
                startToOrderListAcitivity();
            }

            @Override
            public void onFail(String msg) {
                ToastUtil.showShort(msg);
            }

            @Override
            public void onCanel(String msg) {
                ToastUtil.showShort(msg);
            }
        });
    }

    /****************************************************支付宝支付**************************/
    public void alipayPay() {

        alipyUtil.getOrderInfo(orderBianhao, orderShop, orderBianhao, hejiMoney, new HttpUtils
                .CallBack() {
            @Override
            public void onRequestComplete(String orderInfo) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        KLog.i("orderInfo" + orderInfo);
                        if (StringUitl.isEmpty(orderInfo)) {
                            ToastUtil.showShort("订单消息不全");
                            return;
                        }
                        Runnable payRunnable = new Runnable() {
                            @Override
                            public void run() {
                                // 构造PayTask 对象
                                PayTask alipay = new PayTask(BusinessPushProductActivity.this);
                                // 调用支付接口，获取支付结果
                                String result = alipay.pay(orderInfo, true);
                                Message msg = new Message();
                                msg.what = 1;
                                msg.obj = result;
                                mHandler.sendMessage(msg);
                            }
                        };
                        // 必须异步调用
                        Thread payThread = new Thread(payRunnable);
                        payThread.start();
                    }
                });
            }
        });
    }

    //支付宝支付回调
    @SuppressLint("HandlerLeak")
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1: {
                    PayResult payResult = new PayResult((String) msg.obj);
                    /**
                     * 同步返回的结果必须放置到服务端进行验证（验证的规则请看https://doc.open.alipay.com/doc2/
                     * detail.htm?spm=0.0.0.0.xdvAU6&treeId=59&articleId=103665&
                     * docType=1) 建议商户依赖异步通知
                     */
                    String resultInfo = payResult.getResult();// 同步返回需要验证的信息
                    String resultStatus = payResult.getResultStatus();
                    // 判断resultStatus 为“9000”则代表支付成功，具体状态码代表含义可参考接口文档
                    if (TextUtils.equals(resultStatus, "9000")) {
                        Toast.makeText(BusinessPushProductActivity.this, "支付成功", Toast
                                .LENGTH_SHORT).show();
                        KLog.i("支付宝支付成功result" + payResult.getResult());
                        startToOrderListAcitivity();
                    } else {
                        // 判断resultStatus 为非"9000"则代表可能支付失败
                        // "8000"代表支付结果因为支付渠道原因或者系统原因还在等待支付结果确认，最终交易是否成功以服务端异步通知为准（小概率状态）
                        if (TextUtils.equals(resultStatus, "8000")) {
                            Toast.makeText(BusinessPushProductActivity.this, "支付结果确认中", Toast
                                    .LENGTH_SHORT).show();
                        } else {
                            // 其他值就可以判断为支付失败，包括用户主动取消支付，或者系统返回的错误
                            Toast.makeText(BusinessPushProductActivity.this, "支付失败,请重试", Toast
                                    .LENGTH_SHORT).show();
                        }
                        KLog.i("支付宝支付失败result" + payResult.getResult());
                        startToOrderListAcitivity();
                    }
                    break;
                }

                default:
                    break;
            }
        }

    };

    /***********************************************微信支付********************************/
    public void weixinPay() {
        boolean wx = CxUtil.isWeixinAvilible(this);
        if (!wx) {
            Toast.makeText(this, getResources().getString(R.string.weixin_no_install), Toast
                    .LENGTH_SHORT).show();
        } else {
            PayContain.weixinPayResult = PayContain.WEI_XIN_CHECKED;
            new CreateOrderThread().start();
        }
    }

    public class CreateOrderThread extends Thread {
        @Override
        public void run() {
            String result = WechatPay.createOrder(orderBianhao, hejiMoney, orderShop,
                    orderBianhao);
            Message msg = createOrderHandler.obtainMessage();
            msg.what = 0;
            msg.obj = result;
            createOrderHandler.sendMessage(msg);
        }
    }

    Handler createOrderHandler = new Handler() {
        public void handleMessage(Message msg) {
            if (msg.what == 0) {
                String result = (String) msg.obj;
                WechatPay.pay(BusinessPushProductActivity.this, result);
            }
        }
    };

    @Override
    protected void onResume() {
        super.onResume();
        if (PayContain.weixinPayResult != null && PayContain.weixinPayResult == PayContain
                .WEI_XIN_CHECKED && PayContain.payResult != null && PayContain.payResult ==
                PayContain.PAY_SUCCESS) {
            PayContain.weixinPayResult = null;
            PayContain.payResult = null;
            startToOrderListAcitivity();
            KLog.i("onResume 微信支付成功");
        } else if (PayContain.weixinPayResult != null && PayContain.weixinPayResult == PayContain
                .WEI_XIN_CHECKED && PayContain.payResult != null && PayContain.payResult ==
                PayContain.PAY_FAIL) {
            startToOrderListAcitivity();
            KLog.i("onResume 微信支付失败");
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        PayContain.weixinPayResult = null;
        PayContain.payResult = null;

    }

    @OnClick({R.id.rl_pick_address, R.id.modify_product, R.id.bt_push, R.id.tv_choose_time})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rl_pick_address:
                break;
            case R.id.modify_product:
                //修改商品
                setResult(2);
                finish();
                break;
            case R.id.bt_push:
                if (businessProduceType == 1) {
                    if (tvChooseTime.getText().equals("请选择上门配送时间")) {
                        ToastUtil.show(BusinessPushProductActivity.this, "订单预约配送时间不能为空");
                        return;
                    }
                } else if (businessProduceType == 2) {
                    if (tvChooseTime.getText().equals("请选择上门取件时间")) {
                        ToastUtil.show(BusinessPushProductActivity.this, "订单预约取件时间不能为空");
                        return;
                    }
                }

                if (mDeliversBean == null) {
                    KLog.i("服务费规则等为空");
                    ToastUtil.show(BusinessPushProductActivity.this, "请设置配送费规则");
                    return;
                }
                mMap.put("orderBespeakTime", getBespeakTime());//预约时间
                mMap.put("orderRemark", etRemark.getText().toString());//留言
                mMap.put("orderDeliverMethods", mDeliversBean.getDeliverMethods() + "");// 配送方式：
                // (1.商家承运 2.平台承运)
                mMap.put("orderPaydeliverfee", mDeliversBean.getOrderPaydeliverfee() + "");//
                // 配送费承担(1.客户承担 2.商家承担)
                mMap.put("orderMoney", spTotalMoney + "");  //订单总金额
                mMap.put("orderSendMoney", mDeliversBean.getDistributionFee() + "");    //订单配送费
                mMap.put("orderFactMoney", getXiaoJi());   // 实付金额
                mMap.put("discountMoney", mRimActivityDiscount.getData().getDiscountMoney() + "")
                ;   // 优惠金额
                mMap.put("orderSettleMoney", "20");  //结算金额
                mPresenter.upLoadOrderInfo(mMap);
                break;
            case R.id.tv_choose_time:
                TimePickUtil util = new TimePickUtil();
                util.showDatePickerPop(this, tvChooseTime);
                util.setOnSubmitClickListener(this);
                break;
        }
    }


    /**
     * @return
     */
    private String getXiaoJi() {
        hejiMoney = "";
        if (mRimActivityDiscount != null && mDeliversBean != null) {
            hejiMoney = StringUitl.get2xiaoshu((double) (spTotalMoney + mDeliversBean.getDistributionFee
                    () - mRimActivityDiscount.getData().getDiscountMoney()));
            tvFinalMoney.setText("合计：¥ " + hejiMoney);
        }
        return hejiMoney;
    }

    @Override
    public void onSubmitClick(String time) {
        tvChooseTime.setText(time);
    }

    /**
     * 上传用的预约时间
     *
     * @return
     */
    private String getBespeakTime() {
        String s = tvChooseTime.getText().toString();
//        int i = s.indexOf(" ");
//        String substring = s.substring(i, s.length());
        return s;
    }
}