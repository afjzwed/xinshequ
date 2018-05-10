package com.yxld.yxchuangxin.ui.activity.wuye.presenter;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

import com.alipay.sdk.app.PayTask;
import com.socks.library.KLog;
import com.yxld.yxchuangxin.Utils.AlipyUtil;
import com.yxld.yxchuangxin.Utils.HttpUtils;
import com.yxld.yxchuangxin.Utils.PayResult;
import com.yxld.yxchuangxin.Utils.SignUtils;
import com.yxld.yxchuangxin.data.api.API;
import com.yxld.yxchuangxin.data.api.HttpAPIWrapper;
import com.yxld.yxchuangxin.entity.AppJiaofei;
import com.yxld.yxchuangxin.entity.WyFwApp;
import com.yxld.yxchuangxin.ui.activity.wuye.JiaofeiListActivity;
import com.yxld.yxchuangxin.ui.activity.wuye.WuyeMoneyActivity;
import com.yxld.yxchuangxin.ui.activity.wuye.contract.WuyeMoneyContract;
import com.yxld.yxchuangxin.xsq.wxapi.WechatPay;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;

import static com.yxld.yxchuangxin.contain.Contains.pay;

/**
 * @author hu
 * @Package com.yxld.yxchuangxin.ui.activity.wuye
 * @Description: presenter of WuyeMoneyActivity
 * @date 2017/06/06
 */
public class WuyeMoneyPresenter implements WuyeMoneyContract.WuyeMoneyContractPresenter {

    HttpAPIWrapper httpAPIWrapper;
    private WuyeMoneyContract.View mView;
    private CompositeDisposable mCompositeDisposable;
    private WuyeMoneyActivity mActivity;

    //支付宝
    public final String PARTNER = "2088121188417300";
    public final String SELLER = "hnchxwl@163.com";
    public final String RSA_PRIVATE = "MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBAK5WXlFMEXppef4KaMuDX+GWZdK+VxlxLghJPJIhyelcJibVAmJZIAPKmVVEFhFilowin6KWtQ0SWIRRKEtt4zkTPtODdVh8aEBEzkdqWoiv99jOdRMz2GoeR4z5AoRfUTFTv6V7B09+C5UesF6EMJRzXfWvqQ9zyRjmogjwExtPAgMBAAECgYEAqw4FRwE7GP/K+a7e+egyOJan26p0rXr2bpzlOIC8qyKGMI3J5BOMrQupfRbsDCzOiDskpJP4mxXIEjPLNI9iZKxieStonOT829mDvuonnAE04JMkbFSD2l25nwfZ4MaX3hoNCLQCyyOhRWg5kToF2cnqIMZXZZWXmELJoTkCkukCQQDT1Ya0UZWnxPWImi+oe9+A57qPVLDu1nVEFCIREUQgIMhN/UEUw6+0lD+f8WA43uCF6ckqjAuGT/uDXB+/dSu9AkEA0q98S/o/lehnxrQRtt0go5d8c9dHsxkDA9X2BoalKcWc8vCdRSGLf1HNsi/HDq+XUecPKkJAWHtVN8qYPRUt+wJAJAEf4xgWyqwkW3JxdT6Qr3UzdVcct4uF5OtTGvmHTbqksPTBkgjsnVGxOrso8qGXIcupoGyrLMn9YsdOshj1NQJBAK3+BM2ONnLrwsBjt3loNutDUKEuOeVbk5TYX1zWV5Iew9YSBh+wa07TVOeB84daVcJq6qhAnHk2KZNwubdARX8CQCRwNRgAYE9ENAlxSIiBM2xhhzs2JK4Fty7++PoKbhD9uSWwDoZq06xoLAEX9YwLOOVZxiw3T1s3dcE9YTuriRE=";
    public final String RSA_PUBLIC = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCuVl5RTBF6aXn+CmjLg1/hlmXSvlcZcS4ISTySIcnpXCYm1QJiWSADyplVRBYRYpaMIp+ilrUNEliEUShLbeM5Ez7Tg3VYfGhARM5HalqIr/fYznUTM9hqHkeM+QKEX1ExU7+lewdPfguVHrBehDCUc131r6kPc8kY5qII8BMbTwIDAQAB";
    public final int SDK_PAY_FLAG = 1;
    private int jfWyRecId;

    //订单编号，随时间生成
    private String bianhao;

    //总计交的费用
    private double allMoney;

    //房屋
    private WyFwApp allwyFwApp;

    @SuppressLint("HandlerLeak")
    private Handler mHandler = new Handler() {
        @SuppressWarnings("unused")
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
                        Toast.makeText(mActivity, "支付成功", Toast.LENGTH_SHORT).show();
                        Log.d("geek", "支付成功result" + payResult.getResult());
//                        EventBus.getDefault().post("支付成功");
                        mView.onPaySuccess();
//                        amount_view.setText("0");
                    } else {
                        // 判断resultStatus 为非"9000"则代表可能支付失败
                        // "8000"代表支付结果因为支付渠道原因或者系统原因还在等待支付结果确认，最终交易是否成功以服务端异步通知为准（小概率状态）
                        if (TextUtils.equals(resultStatus, "8000")) {
//                            amount_view.setText("0");
                            Toast.makeText(mActivity, "支付结果确认中", Toast.LENGTH_SHORT).show();
                        } else {
//                            amount_view.setText("0");
                            // 其他值就可以判断为支付失败，包括用户主动取消支付，或者系统返回的错误
                            Toast.makeText(mActivity, "支付失败,请重试", Toast.LENGTH_SHORT).show();
                        }
                    }
                    break;
                }
            }
        }
    };

    @Inject
    public WuyeMoneyPresenter(@NonNull HttpAPIWrapper httpAPIWrapper, @NonNull WuyeMoneyContract.View view, WuyeMoneyActivity activity) {
        mView = view;
        this.httpAPIWrapper = httpAPIWrapper;
        mCompositeDisposable = new CompositeDisposable();
        mActivity = activity;
    }

    @Override
    public void subscribe() {

    }

    @Override
    public void unsubscribe() {
        if (!mCompositeDisposable.isDisposed()) {
            mCompositeDisposable.dispose();
        }
        mView = null;
    }

    @Override
    public void getHouse(Map map) {
        mView.showProgressDialog();
        Disposable disposable = httpAPIWrapper.getHouse(map)
                .subscribe(new Consumer<WyFwApp>() {
                    @Override
                    public void accept(WyFwApp wyFwApp) throws Exception {
                        //isSuccesse
                        KLog.i("onSuccesse");
                        mView.closeProgressDialog();
                        mView.showInfo(wyFwApp);
                        allwyFwApp = wyFwApp;
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        //onError
                        KLog.i("onError");
                        throwable.printStackTrace();
                        mView.closeProgressDialog();
//                        ToastUtil.show(mActivity, mActivity.getString(R.string.loading_failed_1));
                    }
                }, new Action() {
                    @Override
                    public void run() throws Exception {
                        //onComplete
                        KLog.i("onComplete");
                    }
                });
        mCompositeDisposable.add(disposable);
    }

    @Override
    public void getwuyePay(Map map) {
        mView.showProgressDialog();
        Disposable disposable = httpAPIWrapper.getWuyePay(map)
                .subscribe(jiaofei -> {
                    //isSuccesse
                    KLog.i("onSuccesse");
                    mView.closeProgressDialog();
                    if (jiaofei.status == 0) {
                        initBack(jiaofei);
                    } else {
                        Toast.makeText(mActivity, jiaofei.msg, Toast.LENGTH_SHORT).show();
                    }

                }, throwable -> {
                    //onError
                    KLog.i("onError");
                    throwable.printStackTrace();
                    mView.closeProgressDialog();
//                    ToastUtil.show(mActivity, mActivity.getString(R.string.loading_failed_1));
                });
        mCompositeDisposable.add(disposable);
    }

    @Override
    public void setOtherParam(String bianhao, double allMoney) {
        this.bianhao = bianhao;
        this.allMoney = allMoney;
    }

    @Override
    public void mingxiOnclick(int position) {
        Intent intent = new Intent(mActivity, JiaofeiListActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("fangwu", allwyFwApp.getHouse().get(position));
        intent.putExtras(bundle);
        mActivity.startActivity(intent);
    }

    @Override
    public float caculateZhiNaJin(int needJiao, int arrMonth, float rate) {
        return 0;
    }

    private AlipyUtil mAlipyUtil = new AlipyUtil();

    private void initBack(AppJiaofei info) {
        jfWyRecId = info.getJfWyRecId();
        switch (pay) {
            case 3:
                mView.yinlianPay(jfWyRecId);
                break;
            case 2:
                boolean wx = isWeixinAvilible(mActivity);
                if (wx == false) {
                    Toast.makeText(mActivity, "微信没安装,您无法使用微信支付", Toast.LENGTH_SHORT).show();
                } else {
//                    amount_view.setText("0");
                    new CreateOrderThread().start();
                }
                break;
            case 1:
                mAlipyUtil.getOrderInfo(bianhao, "物业费缴纳", "物业费缴纳", allMoney + "", new HttpUtils.CallBack() {
                    @Override
                    public void onRequestComplete(String payInfo) {
                        Runnable payRunnable = new Runnable() {

                            @Override
                            public void run() {
                                // 构造PayTask 对象
                                PayTask alipay = new PayTask(mActivity);
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

                break;

        }
    }

    //微信支付
    public class CreateOrderThread extends Thread {
        @Override
        public void run() {
            // TODO: 2018/1/19  修改type改为编号不拼接wy等
            KLog.i("订单编号："+bianhao);
            String result = WechatPay.createOrder(bianhao, allMoney+"", "物业费缴纳", bianhao);
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
                WechatPay.pay(mActivity, result);
            }
        }
    };

    /**
     * create the order info. 创建订单信息
     */
    private String getOrderInfo(String dingdanBianhao, String subject, String body, String price) {

        // 签约合作者身份ID
        String orderInfo = "partner=" + "\"" + PARTNER + "\"";

        // 签约卖家支付宝账号
        orderInfo += "&seller_id=" + "\"" + SELLER + "\"";

        // 商户网站唯一订单号
        orderInfo += "&out_trade_no=" + "\"" + dingdanBianhao + "\"";

        // 商品名称
        orderInfo += "&subject=" + "\"" + subject + "\"";

        // 商品详情
        orderInfo += "&body=" + "\"" + body + "\"";

        // 商品金额
        orderInfo += "&total_fee=" + "\"" + price + "\"";

        // 服务器异步通知页面路径
        orderInfo += "&notify_url=" + "\"" + API.IP_PRODUCT + "/notify_url.jsp" + "\"";
        //http://222.240.1.133/wygl/mall/androidOrder_alipayUpdateOrder
        // 服务接口名称， 固定值
        orderInfo += "&service=\"mobile.securitypay.pay\"";

        // 支付类型， 固定值
        orderInfo += "&payment_type=\"1\"";

        // 参数编码， 固定值
        orderInfo += "&_input_charset=\"utf-8\"";

        // 设置未付款交易的超时时间
        // 默认30分钟，一旦超时，该笔交易就会自动被关闭。
        // 取值范围：1m～15d。
        // m-分钟，h-小时，d-天，1c-当天（无论交易何时创建，都在0点关闭）。
        // 该参数数值不接受小数点，如1.5h，可转换为90m。
        orderInfo += "&it_b_pay=\"30m\"";

        // extern_token为经过快登授权获取到的alipay_open_id,带上此参数用户将使用授权的账户进行支付
        // orderInfo += "&extern_token=" + "\"" + extern_token + "\"";

        // 支付宝处理完请求后，当前页面跳转到商户指定页面的路径，可空
        orderInfo += "&return_url=\"m.alipay.com\"";

        // 调用银行卡支付，需配置此参数，参与签名， 固定值 （需要签约《无线银行卡快捷支付》才能使用）
        // orderInfo += "&paymethod=\"expressGateway\"";

        Log.d("geek", "订单信息 = " + orderInfo);
        return orderInfo;
    }

    /**
     * sign the order info. 对订单信息进行签名
     *
     * @param content 待签名订单信息
     */
    private String sign(String content) {
        return SignUtils.sign(content, RSA_PRIVATE);
    }

    /**
     * get the sign type we use. 获取签名方式
     */
    private String getSignType() {
        return "sign_type=\"RSA\"";
    }

    public static boolean isWeixinAvilible(Context context) {
        final PackageManager packageManager = context.getPackageManager();// 获取packagemanager
        List<PackageInfo> pinfo = packageManager.getInstalledPackages(0);// 获取所有已安装程序的包信息
        if (pinfo != null) {
            for (int i = 0; i < pinfo.size(); i++) {
                String pn = pinfo.get(i).packageName;
                if (pn.equals("com.tencent.mm")) {
                    return true;
                }
            }
        }

        return false;
    }

    /**
     * @param s 缴费的截止日期
     * @param n 交的月份数
     * @return
     */
    public static String addDay(String s, int n) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Calendar cd = Calendar.getInstance();
            cd.setTime(sdf.parse(s));
            cd.add(Calendar.MONTH, n);//增加n个月
            Calendar cd2 = Calendar.getInstance();
            cd2.setTimeInMillis(cd.getTimeInMillis());
            cd2.add(Calendar.DAY_OF_MONTH, 4);
            int day = cd2.get(Calendar.DAY_OF_MONTH);
            KLog.i(day);
            //最后一天为28
            if (day == 1) {
                KLog.i("添加3天");
                cd.add(Calendar.DAY_OF_MONTH, 3);
            }
            //最后一天为29
            if (day == 2) {
                KLog.i("添加2天");
                cd.add(Calendar.DAY_OF_MONTH, 2);
            }
            //最后一天为31
            if (day == 3) {
                KLog.i("添加1天");
                cd.add(Calendar.DAY_OF_MONTH, 1);
            }
            //最后一天为31
            if (day == 4) {

            }
            return sdf.format(cd.getTime());
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 增加自然月
     *
     * @param date  要被处理的日期
     * @param month 要增加的月份数
     * @return 正确的日期
     * @author Zc
     * @since 2017年7月6日 下午12:35:14
     */
    public static String addMonth(String date, int month) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Calendar calendar = Calendar.getInstance();
        try {
            calendar.setTime(sdf.parse(date));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        /* 得到当月的日期 */
        int day = calendar.get(Calendar.DATE);
        calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH) + 1);
        calendar.set(Calendar.DATE, 0);
        /* 得到当月的总天数 */
        int monthDay = calendar.get(Calendar.DATE);
        /* 得到目标月数的总天数 */
        calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH) + month + 1);
        calendar.set(Calendar.DATE, 0);
        int nextMonthDay = calendar.get(Calendar.DATE);
        /* 得到实际的天数*/
        calendar.set(Calendar.DATE, (nextMonthDay - monthDay + day) > 0 ? nextMonthDay - monthDay + day : day);
        return sdf.format(calendar.getTime());
    }

}