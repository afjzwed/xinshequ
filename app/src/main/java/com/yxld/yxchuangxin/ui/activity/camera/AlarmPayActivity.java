package com.yxld.yxchuangxin.ui.activity.camera;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.view.inputmethod.InputMethodManager;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;

import com.alipay.sdk.app.PayTask;
import com.socks.library.KLog;
import com.yxld.yxchuangxin.R;
import com.yxld.yxchuangxin.Utils.AlipyUtil;
import com.yxld.yxchuangxin.Utils.CxUtil;
import com.yxld.yxchuangxin.Utils.HttpUtils;
import com.yxld.yxchuangxin.Utils.PayResult;
import com.yxld.yxchuangxin.Utils.SpUtil;
import com.yxld.yxchuangxin.Utils.StringUitl;
import com.yxld.yxchuangxin.Utils.TimeUtil;
import com.yxld.yxchuangxin.Utils.ToastUtil;
import com.yxld.yxchuangxin.Utils.UIUtils;
import com.yxld.yxchuangxin.application.AppConfig;
import com.yxld.yxchuangxin.base.BaseActivity;
import com.yxld.yxchuangxin.contain.ContainValue;
import com.yxld.yxchuangxin.contain.Contains;
import com.yxld.yxchuangxin.contain.PayContain;
import com.yxld.yxchuangxin.data.api.API;
import com.yxld.yxchuangxin.entity.XuFeiBean;
import com.yxld.yxchuangxin.ui.activity.camera.component.DaggerAlarmPayComponent;
import com.yxld.yxchuangxin.ui.activity.camera.contract.AlarmPayContract;
import com.yxld.yxchuangxin.ui.activity.camera.module.AlarmPayModule;
import com.yxld.yxchuangxin.ui.activity.camera.presenter.AlarmPayPresenter;
import com.yxld.yxchuangxin.ui.activity.rim.WebViewActivity;
import com.yxld.yxchuangxin.view.CustomPopWindow;
import com.yxld.yxchuangxin.view.datepicker.NumericWheelAdapter;
import com.yxld.yxchuangxin.view.datepicker.WheelView;
import com.yxld.yxchuangxin.xsq.wxapi.WechatPay;
import com.zhy.autolayout.AutoLinearLayout;


import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author xlei
 * @Package com.yxld.yxchuangxin.ui.activity.camera
 * @Description: $description
 * @date 2017/09/28 09:59:31
 */

public class AlarmPayActivity extends BaseActivity implements AlarmPayContract.View, View.OnClickListener {

    @Inject
    AlarmPayPresenter mPresenter;
    @BindView(R.id.tv_neirong)
    TextView tvNeirong;
    @BindView(R.id.tv_shangcitime)
    TextView tvShangcitime;
    @BindView(R.id.tv_dangqiantime)
    TextView tvDangqiantime;
    @BindView(R.id.tv_leixing)
    TextView tvLeixing;
    @BindView(R.id.tv_qixian)
    TextView tvQixian;
    @BindView(R.id.tv_danjia)
    TextView tvDanjia;
    @BindView(R.id.tv_zongjia)
    TextView tvZongjia;
    @BindView(R.id.surePay)
    Button btnSurePay;
    @BindView(R.id.tv_xieyi)
    TextView tvXieyi;


    private WheelView mWheelView;
    /**
     * 年限和内容  选择滚轮的适配器公用
     */
    private NumericWheelAdapter mNianxianAdapter;
    /**
     * 年限滚轮数据
     */
    private ArrayList<String> qiXianList = new ArrayList<>();
    /**
     * 1为增值服务 2为流量服务  默认为1增值服务
     */
    private int NeiRong = 1;
    /**
     * 缴费内容滚轮数据
     */
    private ArrayList<String> neirongList = new ArrayList<>();
    /**
     * 增值服务数据
     */
    private List<XuFeiBean.XuFeiContent> zengZhiList;
    /**
     * 基础服务数据
     */
    private List<XuFeiBean.XuFeiContent> jiChuList;

    DecimalFormat df = new DecimalFormat("0.00");
    /**
     * 生成的订单
     */
    private String mOrder = "";
    /**
     * 选择的缴费时限
     */
    private String mQixian = "";
    /**
     * 支付的金额
     */
    private float mMoney = 0;
    private String mMac = "";

    /**
     * 当前截止时间
     */
    private String currentTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        PayContain.weixinPayResult = null;
        PayContain.requestPayModule = null;
        PayContain.yinLianPay = null;
        PayContain.payResult = null;
    }

    @Override
    protected void initView() {
        setContentView(R.layout.activity_alarm_pay);
        ButterKnife.bind(this);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }

    @Override
    protected void initData() {


        // mMac = "139A31373138";
        Intent intent = getIntent();
        tvLeixing.setText(intent.getStringExtra("zhujileixi"));
        tvShangcitime.setText(intent.getStringExtra("time").split(" ")[0]);
        mMac = intent.getStringExtra("mac");
        //mMac = "3455645123";
        Map<String, String> map = new HashMap<>(16);
        map.put("uuid", Contains.uuid);
        map.put("mac", mMac);
        mPresenter.getData(map);
    }

    @Override
    protected void setupActivityComponent() {
        DaggerAlarmPayComponent
                .builder()
                .appComponent(((AppConfig) getApplication()).getApplicationComponent())
                .alarmPayModule(new AlarmPayModule(this))
                .build()
                .inject(this);
    }

    @Override
    public void setPresenter(AlarmPayContract.CameraPayContractPresenter presenter) {
        mPresenter = (AlarmPayPresenter) presenter;
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
    public void setData(XuFeiBean s) {

        zengZhiList = s.getZengzhi();
        jiChuList = s.getJichu();
        currentTime = s.getShijian();
        //初始化续费内容   默认选择缴费内容为居家安防增值
        neirongList.add(0, "居家安防增值服务");
        neirongList.add(1, "居家安防流量服务");
        tvNeirong.setText(neirongList.get(0));
        NeiRong = 1;
        //初始化增值对应的期限   默认选择缴费第一个
        qiXianList.clear();
        for (int i = 0; i < zengZhiList.size(); i++) {
            qiXianList.add(zengZhiList.get(i).getShixian() + " 个月");
        }
        tvQixian.setText(qiXianList.get(0));
        mQixian = qiXianList.get(0).split(" ")[0];
        //默认设置对应的时间
        // TODO: 2017/11/28
//        String time = TimeUtil.addMonthTime(tvShangcitime.getText().toString(), Integer.parseInt(mQixian));
//        tvDangqiantime.setText(time);
        setTime(mQixian);
        //默认设置对应金额
        for (int i = 0; i < zengZhiList.size(); i++) {
            //根据时限设置不同的金额
            if ((zengZhiList.get(i).getShixian() + "").equals(mQixian)) {
                tvZongjia.setText("¥ " + zengZhiList.get(i).getMoney() + "");
                mMoney = zengZhiList.get(i).getMoney();
                tvDanjia.setText(df.format(zengZhiList.get(i).getMoney() / (float) zengZhiList.get(i).getShixian()) + "元/月");
            }
        }

    }

    //设置生成的订单
    @Override
    public void setOrder(String order) {
        mOrder = order;
        //弹出支付选择的popwindow
        showPopWindow(btnSurePay);
    }


    /**
     * 选择期限 确定支付 协议的点击事件
     *
     * @param view
     */
    @OnClick({R.id.tv_qixian, R.id.surePay, R.id.tv_xieyi, R.id.tv_neirong})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_qixian:
                //弹出续费年限滚轮选择
                showWheelView(tvQixian, qiXianList, 0);
                break;
            case R.id.tv_neirong:
                //弹出续费内容滚轮选择
                showWheelView(tvNeirong, neirongList, 1);
                break;
            case R.id.surePay:
                //确认支付    选择支付类型 生成订单
                if (tvNeirong.getText() == null || "".equals(tvNeirong.getText().toString())) {
                    ToastUtil.show(AlarmPayActivity.this, "请选择续费内容");
                    return;
                } else if (mQixian == null || "".equals(mQixian)) {
                    ToastUtil.show(AlarmPayActivity.this, "请选择续费期限");
                    return;
                } else {
                    Map<String, String> map = new HashMap<>(16);
                    map.put("uuid", Contains.uuid);
                    map.put("paianGoujiType.shixian", mQixian);
                    map.put("paianGoujiType.feiyongLeixin", NeiRong + "");
                    map.put("mac", mMac);
                    KLog.i("uuid" + Contains.uuid + "paianGoujiType.shixian" + mQixian + "paianGoujiType.feiyongLeixin" + NeiRong);
                    mPresenter.getOrder(map);
                }
                break;
            case R.id.tv_xieyi:
                //点击协议跳转
                showXieyi();
                break;
            default:
                break;
        }
    }

    /**
     * int payMethod
     * 支付方式  0未选择    支付宝 1 微信 2 银联 3
     */
    private int payMethod = 0;
    public static final int PAY_ALIPY = 1;
    public static final int PAY_WEIXIN = 2;
    public static final int PAY_YINLIAN = 3;
    CheckBox chZhifubao;
    CheckBox chWeixin;
    CheckBox chYinlian;

    private void showPopWindow(View v) {
        View view = LayoutInflater.from(AlarmPayActivity.this).inflate(R.layout.dialog_pay_select, null);
        AutoLinearLayout ll_content = (AutoLinearLayout) view.findViewById(R.id.ll_content);
        chZhifubao = (CheckBox) ll_content.findViewById(R.id.alipy_checked);
        chWeixin = (CheckBox) ll_content.findViewById(R.id.weixin_checked);
        chYinlian = (CheckBox) ll_content.findViewById(R.id.yinlian_checked);
        TextView tvCommit = (TextView) ll_content.findViewById(R.id.tv_commit);
        chZhifubao.setOnClickListener(this);
        chWeixin.setOnClickListener(this);
        chYinlian.setOnClickListener(this);
        tvCommit.setOnClickListener(this);
        new CustomPopWindow.PopupWindowBuilder(this)
                .setClippingEnable(false)
                .setFocusable(false)
                .setView(view)
                .setContenView(ll_content)
                .size(UIUtils.getDisplayWidth(this), UIUtils.getDisplayHeigh(this))
                .create()
                .showAtLocation(v, Gravity.CENTER, 0, 0);

    }

    /**
     * 选择支付方式checkbox的点击事件 确定按钮的点击事件
     * 判断选择的支付方式
     * int payMethod
     * 支付方式  0未选择    支付宝 1 微信 2 银联 3
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.alipy_checked:
                if (chZhifubao.isChecked()) {
                    chWeixin.setChecked(false);
                    chYinlian.setChecked(false);
                    payMethod = PAY_ALIPY;
                } else {
                    payMethod = 0;
                }
                break;
            case R.id.weixin_checked:
                if (chWeixin.isChecked()) {
                    chYinlian.setChecked(false);
                    chZhifubao.setChecked(false);
                    payMethod = PAY_WEIXIN;
                } else {
                    payMethod = 0;
                }
                break;
            case R.id.yinlian_checked:
                if (chYinlian.isChecked()) {
                    chZhifubao.setChecked(false);
                    chWeixin.setChecked(false);
                    payMethod = PAY_YINLIAN;
                } else {
                    payMethod = 0;
                }
                break;
            case R.id.tv_commit:
                if (isFastClick()) {
                    //确认支付方式 开始进行支付
                    switch (payMethod) {
                        case 0:
                            ToastUtil.show(AlarmPayActivity.this, "请选择支付方式");
                            break;
                        case 1:
                            zhiFuBaoPay();
                            break;
                        case 2:
                            wxPay();
                            break;
                        case 3:
                            yinLianPay();
                            break;
                        default:
                            break;
                    }
                }
                break;
            default:
                break;
        }
    }


    private void showXieyi() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        AlertDialog alertDialog = builder.create();
        View view = View.inflate(this, R.layout.dialog_xieyi, null);
        TextView confirm = (TextView) view.findViewById(R.id.confirm);
        CheckBox checkbox = (CheckBox) view.findViewById(R.id.checkbox);
        WebView webView = (WebView) view.findViewById(R.id.webview);
        checkbox.setChecked(SpUtil.getBoolean(AlarmPayActivity.this, ContainValue.NOSHOWXIEYI, false));
        webView.getSettings().setBuiltInZoomControls(true);
        webView.getSettings().setDefaultFontSize(16);
        webView.getSettings().setDisplayZoomControls(false);
        webView.getSettings().setSupportZoom(true);
        webView.getSettings().setLoadWithOverviewMode(true);
        webView.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
        webView.getSettings().setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
        webView.getSettings().setDefaultTextEncodingName("UTF -8");
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setAllowContentAccess(true);
        webView.getSettings().setAppCacheEnabled(false);
        webView.getSettings().setUseWideViewPort(true);
        webView.getSettings().setLoadWithOverviewMode(true);
        webView.getSettings().setLayoutAlgorithm(
                WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        webView.loadUrl(API.URL_XIEYI);
        checkbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                SpUtil.putBoolean(AlarmPayActivity.this, ContainValue.NOSHOWXIEYI, isChecked);
            }
        });
        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
            }
        });
        alertDialog.setView(view);
        alertDialog.setCanceledOnTouchOutside(false);
        alertDialog.setCancelable(false);
        alertDialog.show();
    }

    /**
     * 显示滚轮view 并设置数据
     *
     * @param showView 在哪个view上显示
     * @param list     wheelview中的数据
     * @param flag     滚轮类型
     */
    private void showWheelView(View showView, ArrayList<String> list, int flag) {
        InputMethodManager imm = (InputMethodManager) this.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(showView.getWindowToken(), 0);
        View view = LayoutInflater.from(AlarmPayActivity.this).inflate(R.layout.picker_jiaofeinianxian, null);
        AutoLinearLayout ll_content = (AutoLinearLayout) view.findViewById(R.id.ll_content);
        ll_content.setAnimation(AnimationUtils.loadAnimation(this, R.anim.pop_manage_product_in));
        TextView submit = (TextView) ll_content.findViewById(R.id.submit);
        TextView tv_title = (TextView) ll_content.findViewById(R.id.tv_title);
        if (flag == 0) {
            tv_title.setText("请选择缴费期限");
        } else if (flag == 1) {
            tv_title.setText("请选择缴费内容");
        }
        mWheelView = (WheelView) ll_content.findViewById(R.id.nianxian);
        mNianxianAdapter = new NumericWheelAdapter(AlarmPayActivity.this, 0, list.size() - 1, "", list);
        mNianxianAdapter.setTextSize(15);
        mWheelView.setViewAdapter(mNianxianAdapter);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CustomPopWindow.onBackPressed();
                if (flag == 0) {
                    //
                    if (list.size() == 0) {
                        ToastUtil.show(AlarmPayActivity.this, "请选择续费期限!");
                        return;
                    }
                    int currentItem = mWheelView.getCurrentItem();
                    if (!tvQixian.getText().toString().equals(list.get(currentItem))) {
                        tvZongjia.setText("");
                        mMoney = 0;
                        mQixian = "";
                        tvDanjia.setText("");
                    }
                    tvQixian.setText(list.get(currentItem));
                    //取“3 个月”中的3
                    mQixian = list.get(currentItem).split(" ")[0];
                    //设置续费后调整当前时间
                    if (mQixian != null) {
                        //// TODO: 2017/11/28
//                        String s = TimeUtil.addMonthTime(tvShangcitime.getText().toString(), Integer.parseInt(mQixian));
//                        tvDangqiantime.setText(s);
                        setTime(mQixian);
                    }
                    //表示增值服务
                    if (NeiRong == 1) {
                        for (int i = 0; i < zengZhiList.size(); i++) {
                            //根据时限设置不同的金额
                            if ((zengZhiList.get(i).getShixian() + "").equals(mQixian)) {
                                tvZongjia.setText("¥ " + zengZhiList.get(i).getMoney() + "");
                                mMoney = zengZhiList.get(i).getMoney();
                                tvDanjia.setText(df.format(zengZhiList.get(i).getMoney() / (float) zengZhiList.get(i).getShixian()) + "元/月");
                            }
                        }
                    } else {
                        for (int i = 0; i < jiChuList.size(); i++) {
                            //根据时限设置不同的金额
                            if ((jiChuList.get(i).getShixian() + "").equals(mQixian)) {
                                tvZongjia.setText("¥ " + jiChuList.get(i).getMoney() + "");
                                mMoney = jiChuList.get(i).getMoney();
                                tvDanjia.setText(df.format(jiChuList.get(i).getMoney() / (float) jiChuList.get(i).getShixian()) + "元/月");
                            }
                        }
                    }
                } else if (flag == 1) {
                    if (list.size() == 0) {
                        ToastUtil.show(AlarmPayActivity.this, "请选择缴费内容!");
                        return;
                    }
                    int currentItem = mWheelView.getCurrentItem();
                    //重置期限 金额 时间
                    if (!tvNeirong.getText().toString().equals(list.get(currentItem))) {
                        tvQixian.setText("");
                        tvDanjia.setText("");
                        tvZongjia.setText("");
                        mMoney = 0;
                        mQixian = "";
                        tvDangqiantime.setText("");
                    }
                    tvNeirong.setText(list.get(currentItem));
                    //表示增值服务 初始化增值服务的期限
                    if (currentItem == 0) {
                        NeiRong = 1;
                        //初始化增值对应的期限   默认选择缴费第一个
                        qiXianList.clear();
                        for (int i = 0; i < zengZhiList.size(); i++) {
                            qiXianList.add(zengZhiList.get(i).getShixian() + " 个月");
                        }
                        if (qiXianList.size() <= 0) {
                            return;
                        }
                        tvQixian.setText(qiXianList.get(0));
                        mQixian = qiXianList.get(0).split(" ")[0];
                        //默认设置对应的时间
                        //TODO: 2017/11/28
//                        String time = TimeUtil.addMonthTime(tvShangcitime.getText().toString(), Integer.parseInt(mQixian));
//                        tvDangqiantime.setText(time);
                        setTime(mQixian);
                        //默认设置对应金额
                        for (int i = 0; i < zengZhiList.size(); i++) {
                            //根据时限设置不同的金额
                            if ((zengZhiList.get(i).getShixian() + "").equals(mQixian)) {
                                tvZongjia.setText("¥ " + zengZhiList.get(i).getMoney() + "");
                                mMoney = zengZhiList.get(i).getMoney();
                                tvDanjia.setText(df.format(zengZhiList.get(i).getMoney() / (float) zengZhiList.get(i).getShixian()) + "元/月");
                            }
                        }
                    } else {
                        //表示流量服务 初始化流量服务的期限
                        NeiRong = 2;
                        qiXianList.clear();
                        if (jiChuList.size() <= 0) {
                            return;
                        }
                        for (int i = 0; i < jiChuList.size(); i++) {
                            qiXianList.add(jiChuList.get(i).getShixian() + " 个月");
                        }
                        if (qiXianList.size() <= 0) {
                            return;
                        }
                        tvQixian.setText(qiXianList.get(0));
                        mQixian = qiXianList.get(0).split(" ")[0];
                        //默认设置对应的时间
                        // TODO: 2017/11/28
//                        String time = TimeUtil.addMonthTime(tvShangcitime.getText().toString(), Integer.parseInt(mQixian));
//                        tvDangqiantime.setText(time);
                        setTime(mQixian);
                        //默认设置对应金额
                        for (int i = 0; i < jiChuList.size(); i++) {
                            //根据时限设置不同的金额
                            if ((jiChuList.get(i).getShixian() + "").equals(mQixian)) {
                                tvZongjia.setText("¥ " + jiChuList.get(i).getMoney() + "");
                                mMoney = jiChuList.get(i).getMoney();
                                tvDanjia.setText(df.format(jiChuList.get(i).getMoney() / (float) jiChuList.get(i).getShixian()) + "元/月");
                            }
                        }
                    }

                }
            }
        });

        new CustomPopWindow.PopupWindowBuilder(this)
                .setClippingEnable(false)
                .setFocusable(true)
                .setView(view)
                .setContenView(ll_content)
                .size(UIUtils.getDisplayWidth(this), UIUtils.getDisplayHeigh(this))
                .create()
                .showAtLocation(showView, Gravity.CENTER, 0, 0);
    }

    /**
     * 当前时间的设置 加上续费期限
     *
     * @param mmqixian
     */
    private void setTime(String mmqixian) {
        if (!StringUitl.isEmpty(currentTime)) {
            if (!StringUitl.isEmpty(currentTime.split(" ")[0])) {
                String time = TimeUtil.addMonthTime(currentTime.split(" ")[0], Integer.parseInt(mmqixian));
                tvDangqiantime.setText(time);
            }
        } else {
            String time = TimeUtil.addMonthTime(tvShangcitime.getText().toString(), Integer.parseInt(mQixian));
            tvDangqiantime.setText(time);
        }

    }

    /**
     * 防止多次点击
     */
    private static final int MIN_CLICK_DELAY_TIME = 1000;
    private long lastClickTime;

    public boolean isFastClick() {
        boolean flag = false;
        long curClickTime = System.currentTimeMillis();
        if ((curClickTime - lastClickTime) >= MIN_CLICK_DELAY_TIME) {
            flag = true;
        }
        lastClickTime = curClickTime;
        return flag;
    }


    /************************************************************************支付开始***************************************/
    /**********************************银联支付*************************************/
    private void yinLianPay() {
        String yuming_api = "http://xungeng.hnchxwl.com";
        if (mMoney == 0) {
            return;
        }
        float a = mMoney * 100;
        int money = (int) a;
        Intent intent = new Intent();
        intent.setClass(this, WebViewActivity.class);
        Bundle ylzf = new Bundle();
        ylzf.putString("name", "银联支付");
        ylzf.putString("address", yuming_api + "/CHINAPAY_DEMO/signServlet.do?BusiType=0001&Version=20140728&CommodityMsg=wwxtest&MerPageUrl=" + yuming_api + "/CHINAPAY_DEMO/pgReturn.do&MerBgUrl=" + yuming_api + "/CHINAPAY_DEMO/bgReturn.do&MerId=531121608230001&" +
                "MerOrderNo=" + mOrder + "&OrderAmt=" + money + "&TranDate=" + StringUitl.getNowDateShort() + "&TranTime=" + StringUitl.getTimeShort() + "&MerResv=1");
        intent.putExtras(ylzf);
        KLog.i(yuming_api + "/CHINAPAY_DEMO/signServlet.do?BusiType=0001&Version=20140728&CommodityMsg=wwxtest&MerPageUrl=" + yuming_api + "/CHINAPAY_DEMO/pgReturn.do&MerBgUrl=" + yuming_api + "/CHINAPAY_DEMO/bgReturn.do&MerId=531121608230001&" +
                "MerOrderNo=" + mOrder + "&OrderAmt=" + money + "&TranDate=" + StringUitl.getNowDateShort() + "&TranTime=" + StringUitl.getTimeShort() + "&MerResv=1");
        startActivity(intent);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (PayContain.weixinPayResult != null && PayContain.weixinPayResult == PayContain.WEI_XIN_CHECKED
                && PayContain.payResult != null && PayContain.payResult == PayContain.PAY_SUCCESS
                && PayContain.requestPayModule != null && PayContain.requestPayModule == PayContain.MODULE_CL_ORDER) {
            PayContain.weixinPayResult = null;
            PayContain.requestPayModule = null;
            AlarmPayActivity.this.finish();
            setResult(0);
        }
    }

    /**********************************微信支付*************************************/
    private void wxPay() {
        boolean wx = CxUtil.isWeixinAvilible(this);
        if (!wx) {
            Toast.makeText(this, getResources().getString(R.string.weixin_no_install), Toast.LENGTH_SHORT).show();
        } else {
            PayContain.weixinPayResult = PayContain.WEI_XIN_CHECKED;
            PayContain.requestPayModule = PayContain.MODULE_CL_ORDER;
            new CreateOrderThread().start();
        }
    }

    public class CreateOrderThread extends Thread {
        @Override
        public void run() {
            String result = WechatPay.createOrder(mOrder, mMoney + "", tvNeirong.getText().toString(), mOrder);
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
                WechatPay.pay(AlarmPayActivity.this, result);
            }
        }
    };


    /**********************************支付宝支付*************************************/

    /**
     * 支付宝
     */
    private AlipyUtil alipyUtil = new AlipyUtil();
    public final int SDK_PAY_FLAG = 1;

    private void zhiFuBaoPay() {
        alipyUtil.getOrderInfo(mOrder, tvNeirong.getText().toString(), tvNeirong.getText().toString(), mMoney + "", new HttpUtils.CallBack() {
            @Override
            public void onRequestComplete(String payInfo) {
                Runnable payRunnable = new Runnable() {
                    @Override
                    public void run() {
                        // 构造PayTask 对象
                        PayTask alipay = new PayTask(AlarmPayActivity.this);
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
                    // 同步返回需要验证的信息
                    String resultInfo = payResult.getResult();

                    String resultStatus = payResult.getResultStatus();
                    // 判断resultStatus 为“9000”则代表支付成功，具体状态码代表含义可参考接口文档
                    if (TextUtils.equals(resultStatus, "9000")) {
                        Toast.makeText(AlarmPayActivity.this, "支付成功", Toast.LENGTH_SHORT).show();
                        Log.d("geek", "支付成功result" + payResult.getResult());
                        AlarmPayActivity.this.finish();
                        setResult(0);
                    } else {
                        // 判断resultStatus 为非"9000"则代表可能支付失败
                        // "8000"代表支付结果因为支付渠道原因或者系统原因还在等待支付结果确认，最终交易是否成功以服务端异步通知为准（小概率状态）
                        if (TextUtils.equals(resultStatus, "8000")) {
                            Toast.makeText(AlarmPayActivity.this, "支付结果确认中", Toast.LENGTH_SHORT).show();
                        } else {
                            // 其他值就可以判断为支付失败，包括用户主动取消支付，或者系统返回的错误
                            Toast.makeText(AlarmPayActivity.this, "支付失败,请重试", Toast.LENGTH_SHORT).show();
                        }
                    }
                    break;
                }
                default:
                    break;
            }
        }

    };

}