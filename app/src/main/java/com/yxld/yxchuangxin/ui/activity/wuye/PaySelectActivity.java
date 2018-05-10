package com.yxld.yxchuangxin.ui.activity.wuye;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
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
import com.yxld.yxchuangxin.Utils.TimeUtil;
import com.yxld.yxchuangxin.Utils.ToastUtil;
import com.yxld.yxchuangxin.application.AppConfig;
import com.yxld.yxchuangxin.base.BaseActivity;
import com.yxld.yxchuangxin.contain.Contains;
import com.yxld.yxchuangxin.contain.PayContain;
import com.yxld.yxchuangxin.data.api.API;
import com.yxld.yxchuangxin.ui.activity.rim.WebViewActivity;
import com.yxld.yxchuangxin.ui.activity.wuye.component.DaggerPaySelectComponent;
import com.yxld.yxchuangxin.ui.activity.wuye.contract.PaySelectContract;
import com.yxld.yxchuangxin.ui.activity.wuye.module.PaySelectModule;
import com.yxld.yxchuangxin.ui.activity.wuye.presenter.PaySelectPresenter;
import com.yxld.yxchuangxin.xsq.wxapi.WechatPay;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.yxld.yxchuangxin.R.id.yinlian_checked;

/**
 * @author hu
 * @Package com.yxld.yxchuangxin.ui.activity.wuye
 * @Description: $description
 * @date 2017/07/07 11:10:46
 */

public class PaySelectActivity extends BaseActivity implements PaySelectContract.View {

    @Inject
    PaySelectPresenter mPresenter;
    @BindView(R.id.money)
    TextView money;
    @BindView(R.id.alipy_checked)
    CheckBox alipyChecked;
    @BindView(R.id.weixin_checked)
    CheckBox weixinChecked;
    @BindView(yinlian_checked)
    CheckBox yinlianChecked;
    @BindView(R.id.surePay)
    Button surePay;

    /** 支付方式 0 支付宝 1 微信 2 银联 */
    Integer paymentMethod = PAY_WEIXIN;
    public static final int PAY_ALIPY = 1;
    public static final int PAY_WEIXIN = 2;
    public static final int PAY_YINLIAN = 3;

    private AlipyUtil alipyUtil = new AlipyUtil();
    public final int SDK_PAY_FLAG = 1;

    //支付宝
    public final String PARTNER = "2088121188417300";
    public final String SELLER = "hnchxwl@163.com";
    public final String RSA_PRIVATE = "MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBAK5WXlFMEXppef4KaMuDX+GWZdK+VxlxLghJPJIhyelcJibVAmJZIAPKmVVEFhFilowin6KWtQ0SWIRRKEtt4zkTPtODdVh8aEBEzkdqWoiv99jOdRMz2GoeR4z5AoRfUTFTv6V7B09+C5UesF6EMJRzXfWvqQ9zyRjmogjwExtPAgMBAAECgYEAqw4FRwE7GP/K+a7e+egyOJan26p0rXr2bpzlOIC8qyKGMI3J5BOMrQupfRbsDCzOiDskpJP4mxXIEjPLNI9iZKxieStonOT829mDvuonnAE04JMkbFSD2l25nwfZ4MaX3hoNCLQCyyOhRWg5kToF2cnqIMZXZZWXmELJoTkCkukCQQDT1Ya0UZWnxPWImi+oe9+A57qPVLDu1nVEFCIREUQgIMhN/UEUw6+0lD+f8WA43uCF6ckqjAuGT/uDXB+/dSu9AkEA0q98S/o/lehnxrQRtt0go5d8c9dHsxkDA9X2BoalKcWc8vCdRSGLf1HNsi/HDq+XUecPKkJAWHtVN8qYPRUt+wJAJAEf4xgWyqwkW3JxdT6Qr3UzdVcct4uF5OtTGvmHTbqksPTBkgjsnVGxOrso8qGXIcupoGyrLMn9YsdOshj1NQJBAK3+BM2ONnLrwsBjt3loNutDUKEuOeVbk5TYX1zWV5Iew9YSBh+wa07TVOeB84daVcJq6qhAnHk2KZNwubdARX8CQCRwNRgAYE9ENAlxSIiBM2xhhzs2JK4Fty7++PoKbhD9uSWwDoZq06xoLAEX9YwLOOVZxiw3T1s3dcE9YTuriRE=";
    public final String RSA_PUBLIC = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCuVl5RTBF6aXn+CmjLg1/hlmXSvlcZcS4ISTySIcnpXCYm1QJiWSADyplVRBYRYpaMIp+ilrUNEliEUShLbeM5Ez7Tg3VYfGhARM5HalqIr/fYznUTM9hqHkeM+QKEX1ExU7+lewdPfguVHrBehDCUc131r6kPc8kY5qII8BMbTwIDAQAB";

    public final static int PAY_RESULT_SUCCESS = 1;
//    /** 订单ID */
//    private String orderId = "";
    /** 订单价格 */
    private int orderMoney;
    /** 商品名称或店铺名称 */
    private String orderShop = "车辆缴费";
    /** 详情 */
    private String orderDetails = "车辆缴费";
    /** 订单编号 */
    private String orderBianhao = "";

    private PaySelectActivity activity;

    /** 支付结果的intent**/
    private Intent payIntent;

    /**
     * 缴费到期时间
     */
//    private String lastTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initView() {
        setContentView(R.layout.activity_pay_select);
        ButterKnife.bind(this);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        activity = this;
    }

    @Override
    protected void initData() {
        orderMoney = Integer.valueOf(getIntent().getStringExtra("orderMoney"));
//        lastTime = getIntent().getStringExtra("lastTime");
//        orderMoney = 1;
//        KLog.i(orderMoney);
        money.setText("¥ " + orderMoney);
    }

    @Override
    protected void setupActivityComponent() {
        DaggerPaySelectComponent
                .builder()
                .appComponent(((AppConfig) getApplication()).getApplicationComponent())
                .paySelectModule(new PaySelectModule(this))
                .build()
                .inject(this);
    }

    @Override
    public void setPresenter(PaySelectContract.PaySelectContractPresenter presenter) {
        mPresenter = (PaySelectPresenter) presenter;
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
            ToastUtil.show(this, getResources().getString(R.string.select_payment_method));
            return;
        }
        orderBianhao = Contains.user.getYezhuId() + "cl" + System.currentTimeMillis() + "";
        Map<String, String> map = new HashMap();
        map.put("rechargeType", paymentMethod + "");     //订单编号
        map.put("money", orderMoney+ "");  //金额
        map.put("month", getIntent().getStringExtra("month"));    //月数
        map.put("parkNo", getIntent().getStringExtra("parkNo"));   //车场号
        map.put("mediaNo", getIntent().getStringExtra("mediaNo"));   //车牌号
        map.put("startTime", TimeUtil.timesTamp2YearMonthDay(Long.parseLong(getIntent().getStringExtra("startTime"))));
        map.put("term", TimeUtil.timesTamp2YearMonthDay(Long.parseLong(getIntent().getStringExtra("cardTimr"))));      //时间
        KLog.i(map);
        mPresenter.prePay(map);
    }


    @OnClick({R.id.alipy_checked, R.id.weixin_checked, yinlian_checked, R.id.surePay})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.alipy_checked:   //支付宝
                if ((((CheckBox) view).isChecked())) {
                    paymentMethod = PAY_ALIPY;
                    weixinChecked.setChecked(false);
                    yinlianChecked.setChecked(false);
                } else {
                    paymentMethod = null;
                }
                break;
            case R.id.weixin_checked: //微信
                if ((((CheckBox) view).isChecked())) {
                    paymentMethod = PAY_WEIXIN;
                    alipyChecked.setChecked(false);
                    yinlianChecked.setChecked(false);
                } else {
                    paymentMethod = null;
                }
                break;
            case yinlian_checked:  //银联
                if ((((CheckBox) view).isChecked())) {
                    paymentMethod = PAY_YINLIAN;
                    weixinChecked.setChecked(false);
                    alipyChecked.setChecked(false);
                } else {
                    paymentMethod = null;
                }
                break;
            case R.id.surePay:  //确认支付
                selectPaymentMethod(paymentMethod);
                break;
        }
    }


    @Override
    protected void onResume() {
        super.onResume();
        if (PayContain.weixinPayResult != null && PayContain.weixinPayResult==PayContain.WEI_XIN_CHECKED
                && PayContain.payResult !=null && PayContain.payResult == PayContain.PAY_SUCCESS
                && PayContain.requestPayModule != null && PayContain.requestPayModule == PayContain.MODULE_CL_ORDER) {
            PayContain.weixinPayResult = null;
            PayContain.requestPayModule = null;
            payIntent = new Intent();
            payIntent.putExtra("orderBianhao", orderBianhao);
            payIntent.putExtra("payWay", "2");
            paySuccess();
        }
    }


    @Override
    protected void onRestart() {
        super.onRestart();
        if (paymentMethod == PAY_YINLIAN) {
            payIntent = new Intent();
            payIntent.putExtra("orderBianhao", orderBianhao);
            payIntent.putExtra("payWay", "3");
            paySuccess();
            return;
        }
    }

//    @Override
//    public boolean onKeyDown(int keyCode, KeyEvent event) {
//        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
//            if (PayContain.requestPayModule != null && PayContain.requestPayModule == PayContain.MODULE_CL_ORDER) {
//                PayContain.weixinPayResult = null;
//                PayContain.requestPayModule = null;
//                payIntent = new Intent();
//                payIntent.putExtra("orderBianhao", orderBianhao);
//                payIntent.putExtra("payWay", "2");
//                paySuccess();
//                return true;
//            }
//            return false;
//        }
//        return super.onKeyDown(keyCode, event);
//    }

    /*************************************************支付开始********************************************************************/

    @Override
    public void alipayPay() {

        alipyUtil.getOrderInfo(orderBianhao, orderDetails, orderDetails, orderMoney + "", new HttpUtils.CallBack() {
            @Override
            public void onRequestComplete(String payInfo) {
                Runnable payRunnable = new Runnable() {

                    @Override
                    public void run() {
                        // 构造PayTask 对象
                        PayTask alipay = new PayTask(activity);
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
        boolean wx = CxUtil.isWeixinAvilible(this);
        if (!wx) {
            Toast.makeText(this, getResources().getString(R.string.weixin_no_install), Toast.LENGTH_SHORT).show();
        } else {
            PayContain.weixinPayResult = PayContain.WEI_XIN_CHECKED;
            PayContain.requestPayModule = PayContain.MODULE_CL_ORDER;
            new CreateOrderThread().start();
        }
    }

    @Override
    public void yinlianPay() {
//        String yuming_api = "http://dz.hnchxwl.com/cxwy_daozha/config/paysuccess/";
        String yuming_api = "http://www.hnchxwl.com/";
        if (orderMoney ==0) {
            return;
        }
        float a = orderMoney * 100;
        int money = (int) a;
        Intent intent = new Intent();
        intent.setClass(this, // context
                WebViewActivity.class);// 跳转的activity
        Bundle ylzf = new Bundle();
        ylzf.putString("name", "银联支付");
        ylzf.putString("address", yuming_api+"/CHINAPAY_DEMO/signServlet.do?BusiType=0001&Version=20140728&CommodityMsg=wwxtest&MerPageUrl="+yuming_api+"/CHINAPAY_DEMO/pgReturn.do&MerBgUrl="+yuming_api+"/CHINAPAY_DEMO/bgReturn.do&MerId=531121608230001&" +
                "MerOrderNo=" + orderBianhao + "&OrderAmt=" + money + "&TranDate=" + StringUitl.getNowDateShort() + "&TranTime=" + StringUitl.getTimeShort() + "&MerResv=1");
        intent.putExtras(ylzf);
        startActivity(intent);
    }

    //微信支付
    public class CreateOrderThread extends Thread {
        @Override
        public void run() {
            String result = WechatPay.createOrder(orderBianhao, orderMoney + "", orderShop, orderBianhao);
            Message msg = createOrderHandler.obtainMessage();
            msg.what = 0;
            msg.obj = result;
            createOrderHandler.sendMessage(msg);
        }
    }

    //微信支付
    Handler createOrderHandler = new Handler() {
        public void handleMessage(Message msg) {
            if (msg.what == 0) {
                String result = (String) msg.obj;
                WechatPay.pay(PaySelectActivity.this, result);
            }
        }
    };


    //支付宝支付回调
    @SuppressLint("HandlerLeak")
    private Handler mHandler = new Handler() {
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
                        Toast.makeText(PaySelectActivity.this, "支付成功", Toast.LENGTH_SHORT).show();
                        Log.d("geek", "支付成功result" + payResult.getResult());
                        payIntent = new Intent();
                        payIntent.putExtra("orderBianhao", orderBianhao);
                        payIntent.putExtra("payWay", "1");
                        paySuccess();
                    } else {
                        // 判断resultStatus 为非"9000"则代表可能支付失败
                        // "8000"代表支付结果因为支付渠道原因或者系统原因还在等待支付结果确认，最终交易是否成功以服务端异步通知为准（小概率状态）
                        if (TextUtils.equals(resultStatus, "8000")) {
                            Toast.makeText(PaySelectActivity.this, "支付结果确认中", Toast.LENGTH_SHORT).show();
                        } else {
                            // 其他值就可以判断为支付失败，包括用户主动取消支付，或者系统返回的错误
                            Toast.makeText(PaySelectActivity.this, "支付失败,请重试", Toast.LENGTH_SHORT).show();
                        }
                    }
                    break;
                }
                default:
                    break;
            }
        }

    };

    private void paySuccess() {
        AppConfig.getInstance().mAppActivityManager.finishActivity(CarAddMoneyActivity.class);
//        setResult(PAY_RESULT_SUCCESS, payIntent);
        finish();
    }

    @Override
    public void jumpOrderListView() {
//        setResult(OrderListPartitionFragment.CODE_REQUEST_PAY);
//        Intent intent = new Intent(this, OrderListActivity.class);
//        intent.putExtra(OrderListActivity.ITEM, 2);
//        startActivity(intent);
//        finish();
    }

    @Override
    public void requestAlipyError(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void prePayBack(String orderBianhao) {
        String[] temp = orderBianhao.split("cw");
        orderBianhao = temp[0] + "cw";
        KLog.i(orderBianhao);
        this.orderBianhao = orderBianhao;
        switch (paymentMethod) {
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

    @OnClick(R.id.surePay)
    public void onViewClicked() {
    }
}