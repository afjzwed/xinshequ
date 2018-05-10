package com.yxld.yxchuangxin.ui.activity.goods;

import android.annotation.SuppressLint;
import android.app.Application;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
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
import com.yxld.yxchuangxin.Utils.ToastUtil;
import com.yxld.yxchuangxin.Utils.YinLianPayUtil;
import com.yxld.yxchuangxin.application.AppConfig;
import com.yxld.yxchuangxin.base.BaseActivity;
import com.yxld.yxchuangxin.contain.PayContain;
import com.yxld.yxchuangxin.ui.activity.goods.component.DaggerPayWaySelectComponent;
import com.yxld.yxchuangxin.ui.activity.goods.contract.PayWaySelectContract;
import com.yxld.yxchuangxin.ui.activity.goods.module.PayWaySelectModule;
import com.yxld.yxchuangxin.ui.activity.goods.presenter.PayWaySelectPresenter;
import com.yxld.yxchuangxin.xsq.wxapi.WechatPay;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.refactor.library.SmoothCheckBox;

import static com.yxld.yxchuangxin.R.id.yinlian_checked;
import static com.yxld.yxchuangxin.ui.activity.goods.OrderListActivity.IS_PAY_SUCCESS;
import static com.yxld.yxchuangxin.ui.activity.goods.OrderListActivity.IS_ZITI;

/**
 * @author wwx
 * @Package com.yxld.yxchuangxin.ui.activity.goods
 * @Description: 收银台
 * @date 2017/06/27 16:12:50
 */

public class PayWaySelectActivity extends BaseActivity implements PayWaySelectContract.View {
    public static final String KEY_IN_TYPE = "key_in_type";
    public static final int IN_TYPE_ORDER_LIST = 0x000001;//标识是从订单列表页面进入
    public static final int IN_TYPE_CONFROM_ORDER = 0x000002;//标识是从订单确认页面进入
    public static final int IN_TYPE_SAOMA_ORDER = 0x000003;//扫码支付页面进入
    @Inject
    PayWaySelectPresenter mPresenter;
    @BindView(R.id.money)
    TextView money;
    @BindView(R.id.alipy_checked)
    SmoothCheckBox alipyChecked;
    @BindView(R.id.weixin_checked)
    SmoothCheckBox weixinChecked;
    @BindView(yinlian_checked)
    SmoothCheckBox yinlianChecked;
    @BindView(R.id.surePay)
    Button surePay;

    /**
     * 支付方式 0 支付宝 1 微信 2 银联
     */
    Integer paymentMethod = PAY_WEIXIN;
    public static final int PAY_ALIPY = 0;
    public static final int PAY_WEIXIN = 1;
    public static final int PAY_YINLIAN = 2;
    @BindView(R.id.tv_shuoming)
    TextView mTvShuoming;

    /**
     * 订单ID
     */
    private String orderId = "";
    /**
     * 订单价格
     */
    private String orderMoney = "";
    /**
     * 商品名称或店铺名称
     */
    private String orderShop = "";
    /**
     * 详情
     */
    private String orderDetails = "";
    /**
     * 订单编号
     */
    private String orderBianhao = "";

    private AlipyUtil alipyUtil = new AlipyUtil();
    public final int SDK_PAY_FLAG = 1;

    private int mCurrentInType; //是从订单列表进来的还是从订单确认界面进来的


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initView() {
        setContentView(R.layout.payway_select_activity_layout);
        ButterKnife.bind(this);
        toolbar.setBackgroundColor(ContextCompat.getColor(this, R.color.color_ff9934));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    protected void initData() {
        //获取订单信息
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        orderId = bundle.getString("orderId");
        orderMoney = bundle.getString("orderMoney");
        // orderMoney = "0.01";
        orderShop = bundle.getString("orderShop");
        orderBianhao = bundle.getString("orderBianhao");
        orderDetails = bundle.getString("orderDetails");
        mCurrentInType = bundle.getInt(KEY_IN_TYPE);
        if (mCurrentInType == IN_TYPE_SAOMA_ORDER) {
            //表示从扫码支付进来 不需要提示 15分钟未支付取消的提醒显示
            mTvShuoming.setVisibility(View.INVISIBLE);
        }
        KLog.i("订单数据: orderId" + orderId + ",orderMoney+" + orderMoney + ",orderShop+" + orderShop + ",orderBianhao+" + orderBianhao + ",orderDetails" + orderDetails);
        //设置金额
        if (StringUitl.isNoEmpty(orderMoney)) {
            money.setText("¥" + StringUitl.get2xiaoshu(Double.parseDouble(orderMoney)));
        }

        initEvent();
    }

    private void initEvent() {
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
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            if (PayContain.requestPayModule != null && PayContain.requestPayModule == PayContain.MODULE_MALL_ORDER) {
                jumpOrderListView();
                PayContain.weixinPayResult = null;
                PayContain.requestPayModule = null;
            }
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void setupActivityComponent() {
        DaggerPayWaySelectComponent
                .builder()
                .appComponent(((AppConfig) getApplication()).getApplicationComponent())
                .payWaySelectModule(new PayWaySelectModule(this))
                .build()
                .inject(this);
    }

    @Override
    public void setPresenter(PayWaySelectContract.PayWaySelectContractPresenter presenter) {
        mPresenter = (PayWaySelectPresenter) presenter;
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
    public void selectPaymentMethod(Integer method) {
        if (method == null) {
            ToastUtil.show(PayWaySelectActivity.this, getResources().getString(R.string.select_payment_method));
            return;
        }

        switch (method) {
            case PAY_ALIPY:
                alipayPay();
                break;
            case PAY_WEIXIN:
                weixinPay();
                break;
            case PAY_YINLIAN:
                yinlianPay();
                break;
            default:
                break;
        }
    }


    @OnClick({R.id.alipy_checked, R.id.weixin_checked, yinlian_checked, R.id.surePay})
    public void onViewClicked(View view) {
        switch (view.getId()) {
//            case R.id.alipy_checked:   //支付宝
//                if((((SmoothCheckBox) view).isChecked())){
//                    paymentMethod = PAY_ALIPY;
//                    weixinChecked.setChecked(false);
//                    yinlianChecked.setChecked(false);
//                }else{
//                    paymentMethod = null;
//                }
//                break;
//            case R.id.weixin_checked: //微信
//                if((((SmoothCheckBox) view).isChecked())){
//                    paymentMethod = PAY_WEIXIN;
//                    alipyChecked.setChecked(false);
//                    yinlianChecked.setChecked(false);
//                }else{
//                    paymentMethod = null;
//                }
//                break;
//            case yinlian_checked:  //银联
//                if((((SmoothCheckBox) view).isChecked())){
//                    paymentMethod = PAY_YINLIAN;
//                    weixinChecked.setChecked(false);
//                    alipyChecked.setChecked(false);
//                }else{
//                    paymentMethod = null;
//                }
//                break;
            case R.id.surePay:  //确认支付
                selectPaymentMethod(paymentMethod);
                break;
        }
    }


    @Override
    protected void onResume() {
        super.onResume();
        if (PayContain.weixinPayResult != null && PayContain.weixinPayResult == PayContain.WEI_XIN_CHECKED &&
                PayContain.payResult != null && PayContain.payResult == PayContain.PAY_SUCCESS
                && PayContain.requestPayModule != null && PayContain.requestPayModule == PayContain.MODULE_MALL_ORDER) {
            jumpOrderListView();
            KLog.i("onResume 支付成功");
        }
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
            if (PayContain.requestPayModule != null && PayContain.requestPayModule == PayContain.MODULE_MALL_ORDER) {
                jumpOrderListView();
                PayContain.weixinPayResult = null;
                PayContain.requestPayModule = null;
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        PayContain.weixinPayResult = null;
        PayContain.requestPayModule = null;
        PayContain.yinLianPay = null;
        PayContain.payResult = null;
    }

    /*************************************************支付开始********************************************************************/

    @Override
    public void alipayPay() {

        alipyUtil.getOrderInfo(orderBianhao, orderShop, orderDetails, orderMoney, new HttpUtils.CallBack() {
            @Override
            public void onRequestComplete(String payInfo) {
                Runnable payRunnable = new Runnable() {

                    @Override
                    public void run() {
                        // 构造PayTask 对象
                        PayTask alipay = new PayTask(PayWaySelectActivity.this);
                        // 调用支付接口，获取支付结果
                        String result = alipay.pay(payInfo, true);

                        Message msg = new Message();
                        msg.what = SDK_PAY_FLAG;
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

    @Override
    public void weixinPay() {
        boolean wx = CxUtil.isWeixinAvilible(PayWaySelectActivity.this);
        if (!wx) {
            Toast.makeText(PayWaySelectActivity.this, getResources().getString(R.string.weixin_no_install), Toast.LENGTH_SHORT).show();
        } else {
            PayContain.weixinPayResult = PayContain.WEI_XIN_CHECKED;
            new CreateOrderThread().start();
        }
    }

    //    @Override
//    public void yinlianPay() {
//        String yuming_api = "http://www.hnchxwl.com/";
//        if (!StringUitl.isNoEmpty(orderMoney)) {
//            return;
//        }
//        float a = Float.parseFloat(orderMoney) * 100;
//        int money = (int) a;
//        Intent intent = new Intent();
//        intent.setClass(PayWaySelectActivity.this, WebViewActivity.class);
//        Bundle ylzf = new Bundle();
//        //Todo 多了一个斜杠？
//        String str = yuming_api + "/CHINAPAY_DEMO/signServlet.do?BusiType=0001&Version=20140728&CommodityMsg=wwxtest&MerPageUrl=" + yuming_api + "/CHINAPAY_DEMO/pgReturn.do&MerBgUrl=" + yuming_api + "/CHINAPAY_DEMO/bgReturn.do&MerId=531121608230001&" +
//                "MerOrderNo=" + orderBianhao + "&OrderAmt=" + money + "&TranDate=" + StringUitl.getNowDateShort() + "&TranTime=" + StringUitl.getTimeShort() + "&MerResv=1";
//        ylzf.putString("name", "银联支付");
//        ylzf.putString("address", str);
////        KLog.i("YinLian",str);
//
//        intent.putExtras(ylzf);
//        PayContain.yinLianPay = PayContain.YIN_LIAN_PAY_CHECKED;
//        startActivity(intent);
//    }
    private YinLianPayUtil mYinLianPayUtil = new YinLianPayUtil();

    @Override
    public void yinlianPay() {
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
                        mYinLianPayUtil.doStartUnionPayPlugin(PayWaySelectActivity.this, result, "01");
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
                PayContain.payResult = PayContain.PAY_SUCCESS;
                jumpOrderListView();
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

    public boolean isPaySucceed() {
        return (PayContain.payResult != null && PayContain.payResult == PayContain.PAY_SUCCESS
                && PayContain.requestPayModule != null && PayContain.requestPayModule == PayContain.MODULE_MALL_ORDER);
    }

    //微信支付
    public class CreateOrderThread extends Thread {
        @Override
        public void run() {
            String result = WechatPay.createOrder(orderBianhao, orderMoney, orderShop, orderBianhao);
            Message msg = createOrderHandler.obtainMessage();
            msg.what = 0;
            msg.obj = result;
            createOrderHandler.sendMessage(msg);
        }
    }

    //微信支付
    Handler createOrderHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == 0) {
                String result = (String) msg.obj;
                WechatPay.pay(PayWaySelectActivity.this, result);
            }
        }
    };


    //支付宝支付回调
    @SuppressLint("HandlerLeak")
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case SDK_PAY_FLAG: {
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
                        Toast.makeText(PayWaySelectActivity.this, "支付成功", Toast.LENGTH_SHORT).show();
                        Log.d("geek", "支付成功result" + payResult.getResult());
//                        if (PayContain.requestPayModule != null && PayContain.requestPayModule == PayContain.MODULE_MALL_ORDER) {
//                            /** 用于请求参数 */
//                            Map<String, String> map = new HashMap<String, String>();
//                            map.put("ord.dingdanId", orderId);
//                            map.put("ord.dingdanZhuangtai", "待发货");
//                            map.put("ord.dingdanBeiyong1", "支付宝支付");
//                            map.put("uuid", Contains.uuid);
//                            mPresenter.alipyPaySuccess(map);
//                        }
                        onAlipaySucceed();
                    } else {
                        // 判断resultStatus 为非"9000"则代表可能支付失败
                        // "8000"代表支付结果因为支付渠道原因或者系统原因还在等待支付结果确认，最终交易是否成功以服务端异步通知为准（小概率状态）
                        if (TextUtils.equals(resultStatus, "8000")) {
                            Toast.makeText(PayWaySelectActivity.this, "支付结果确认中", Toast.LENGTH_SHORT).show();
                        } else {
                            // 其他值就可以判断为支付失败，包括用户主动取消支付，或者系统返回的错误
                            Toast.makeText(PayWaySelectActivity.this, "支付失败,请重试", Toast.LENGTH_SHORT).show();
                        }
                    }
                    break;
                }
                default:
                    break;
            }
        }

    };

    // TODO: 2017/11/10 支付成功后进入的方法
    @Override
    public void jumpOrderListView() {
        if (mCurrentInType == IN_TYPE_ORDER_LIST) {
            if (PayContain.payResult == null) {
                IS_PAY_SUCCESS = 0;
            } else {
                IS_PAY_SUCCESS = PayContain.payResult;
            }
            setResult(OrderListPartitionFragment.CODE_REQUEST_PAY);
        } else if (mCurrentInType == IN_TYPE_CONFROM_ORDER) {
            Intent intent = new Intent(PayWaySelectActivity.this, OrderListActivity.class);
            if (isPaySucceed()) {
                //支付成功
                //门店自提 直接跳待收货界面
                if (IS_ZITI == 2) {
                    intent.putExtra(OrderListActivity.ITEM, 3);
                    IS_ZITI = 0;
                } else {
                    intent.putExtra(OrderListActivity.ITEM, 2);
                }
            } else {
                //支付失败
                intent.putExtra(OrderListActivity.ITEM, 1);
            }
            startActivity(intent);
        } else if (mCurrentInType == IN_TYPE_SAOMA_ORDER) {
            if (isPaySucceed()) {
                KLog.i("支付成功");

            } else {
                KLog.i("支付失败");
            }

        }
        finish();
    }

    @Override
    public void requestAlipyError(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onAlipaySucceed() {
        PayContain.payResult = PayContain.PAY_SUCCESS;
        jumpOrderListView();
    }
}