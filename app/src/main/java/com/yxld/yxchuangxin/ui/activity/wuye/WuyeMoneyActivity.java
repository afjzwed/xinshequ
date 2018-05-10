package com.yxld.yxchuangxin.ui.activity.wuye;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.jaredrummler.materialspinner.MaterialSpinner;
import com.socks.library.KLog;
import com.yxld.yxchuangxin.R;
import com.yxld.yxchuangxin.Utils.StringUitl;
import com.yxld.yxchuangxin.Utils.ToastUtil;
import com.yxld.yxchuangxin.application.AppConfig;
import com.yxld.yxchuangxin.base.BaseActivity;
import com.yxld.yxchuangxin.contain.Contains;
import com.yxld.yxchuangxin.contain.PayContain;
import com.yxld.yxchuangxin.entity.WyFwApp;
import com.yxld.yxchuangxin.ui.activity.rim.WebViewActivity;
import com.yxld.yxchuangxin.ui.activity.wuye.component.DaggerWuyeMoneyComponent;
import com.yxld.yxchuangxin.ui.activity.wuye.contract.WuyeMoneyContract;
import com.yxld.yxchuangxin.ui.activity.wuye.module.WuyeMoneyModule;
import com.yxld.yxchuangxin.ui.activity.wuye.presenter.WuyeMoneyPresenter;
import com.zhy.autolayout.AutoRelativeLayout;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.refactor.library.SmoothCheckBox;

import static com.yxld.yxchuangxin.R.id.tv_count;
import static com.yxld.yxchuangxin.contain.Contains.pay;

/**
 * @author hu
 * @Package com.yxld.yxchuangxin.ui.activity.wuye
 * @Description: $description
 * @date 2017/06/06
 */

public class WuyeMoneyActivity extends BaseActivity implements WuyeMoneyContract.View, SmoothCheckBox.OnCheckedChangeListener {

    @Inject
    WuyeMoneyPresenter mPresenter;
    @BindView(R.id.tv_xiangmu)
    MaterialSpinner tvXiangmu;
    @BindView(R.id.textView3)
    TextView textView3;
    @BindView(R.id.tv_qianjiao)
    TextView tvQianjiao;
    @BindView(R.id.textView4)
    TextView textView4;
    @BindView(R.id.tv_yujiao)
    TextView tvYujiao;
    @BindView(R.id.view2)
    View view2;
    @BindView(R.id.tv_mingxi)
    TextView tvMingxi;
    @BindView(R.id.tv_last_time)
    TextView tvLastTime;
    @BindView(R.id.iv1)
    ImageView iv1;
    @BindView(R.id.iv2)
    ImageView iv2;
    @BindView(R.id.iv3)
    ImageView iv3;
    @BindView(R.id.bt_submit)
    Button btSubmit;
    @BindView(R.id.cb_zhifubao)
    SmoothCheckBox cbZhifubao;
    @BindView(R.id.cb_weixin)
    SmoothCheckBox cbWeixin;
    @BindView(R.id.cb_yinlian)
    SmoothCheckBox cbYinlian;
    @BindView(R.id.iv_jian)
    AutoRelativeLayout ivJian;
    @BindView(tv_count)
    TextView tvCount;
    @BindView(R.id.iv_jia)
    AutoRelativeLayout ivJia;
    @BindView(R.id.tv_zongji)
    TextView tvZongji;

    //总计交的费用
    private double allMoney;
    //预交的费用
    private double preMoney;
    //欠缴的费用
    private double needMoney;

    //交费月数
    private int jiaofeiMonth;

    //所选房屋的面积
    private double area;

    //所选房屋每平米每月的物业费
    private double oneAreaPrice;

    //欠缴月数
    private int arrearMonth;

    //缴费的房屋id
    private int fangwuId;

    //滞纳金
    private double zhinajin = 0;

    //缴费的截止日期
    private String endTime;

    DecimalFormat df = new DecimalFormat("######0.00");

//    BigDecimal payMoney = new BigDecimal();

    //支付宝
    public final String PARTNER = "2088121188417300";
    public final String SELLER = "hnchxwl@163.com";
    public final String RSA_PRIVATE = "MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBAK5WXlFMEXppef4KaMuDX+GWZdK+VxlxLghJPJIhyelcJibVAmJZIAPKmVVEFhFilowin6KWtQ0SWIRRKEtt4zkTPtODdVh8aEBEzkdqWoiv99jOdRMz2GoeR4z5AoRfUTFTv6V7B09+C5UesF6EMJRzXfWvqQ9zyRjmogjwExtPAgMBAAECgYEAqw4FRwE7GP/K+a7e+egyOJan26p0rXr2bpzlOIC8qyKGMI3J5BOMrQupfRbsDCzOiDskpJP4mxXIEjPLNI9iZKxieStonOT829mDvuonnAE04JMkbFSD2l25nwfZ4MaX3hoNCLQCyyOhRWg5kToF2cnqIMZXZZWXmELJoTkCkukCQQDT1Ya0UZWnxPWImi+oe9+A57qPVLDu1nVEFCIREUQgIMhN/UEUw6+0lD+f8WA43uCF6ckqjAuGT/uDXB+/dSu9AkEA0q98S/o/lehnxrQRtt0go5d8c9dHsxkDA9X2BoalKcWc8vCdRSGLf1HNsi/HDq+XUecPKkJAWHtVN8qYPRUt+wJAJAEf4xgWyqwkW3JxdT6Qr3UzdVcct4uF5OtTGvmHTbqksPTBkgjsnVGxOrso8qGXIcupoGyrLMn9YsdOshj1NQJBAK3+BM2ONnLrwsBjt3loNutDUKEuOeVbk5TYX1zWV5Iew9YSBh+wa07TVOeB84daVcJq6qhAnHk2KZNwubdARX8CQCRwNRgAYE9ENAlxSIiBM2xhhzs2JK4Fty7++PoKbhD9uSWwDoZq06xoLAEX9YwLOOVZxiw3T1s3dcE9YTuriRE=";
    public final String RSA_PUBLIC = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCuVl5RTBF6aXn+CmjLg1/hlmXSvlcZcS4ISTySIcnpXCYm1QJiWSADyplVRBYRYpaMIp+ilrUNEliEUShLbeM5Ez7Tg3VYfGhARM5HalqIr/fYznUTM9hqHkeM+QKEX1ExU7+lewdPfguVHrBehDCUc131r6kPc8kY5qII8BMbTwIDAQAB";
    public final int SDK_PAY_FLAG = 1;
    //订单编号，随时间生成
    private String bianhao;

    //记录当前选择的房子是否可以交费
    private boolean canJiaoFei = true;

    //记录当前选中的房间
    private int curRoom = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        needFront = true;
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initView() {
        setContentView(R.layout.activity_wuye_money);
        ButterKnife.bind(this);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        tvXiangmu.getPopupWindow().setBackgroundDrawable(getResources().getDrawable(R.drawable.bg_material_spinner));
        cbZhifubao.setOnCheckedChangeListener(this);
        cbWeixin.setOnCheckedChangeListener(this);
        cbYinlian.setOnCheckedChangeListener(this);
    }

    @Override
    protected void initData() {
        Map<String, String> map = new HashMap<>();
        map.put("uuid", Contains.uuid);        //?uuid=%1$s
        mPresenter.getHouse(map);
        tvXiangmu.setGravity(Gravity.CENTER);
    }

    @Override
    protected void setupActivityComponent() {
        DaggerWuyeMoneyComponent
                .builder()
                .appComponent(((AppConfig) getApplication()).getApplicationComponent())
                .wuyeMoneyModule(new WuyeMoneyModule(this))
                .build()
                .inject(this);
    }

    @Override
    public void setPresenter(WuyeMoneyContract.WuyeMoneyContractPresenter presenter) {
        mPresenter = (WuyeMoneyPresenter) presenter;
    }

    @Override
    public void onCheckedChanged(SmoothCheckBox smoothCheckBox, boolean b) {
        KLog.i(b);
        if (b) {
            switch (smoothCheckBox.getId()) {
                case R.id.cb_zhifubao:
                    cbWeixin.setChecked(false);
                    cbYinlian.setChecked(false);
                    break;
                case R.id.cb_weixin:
                    cbZhifubao.setChecked(false);
                    cbYinlian.setChecked(false);
                    break;
                case R.id.cb_yinlian:
                    cbWeixin.setChecked(false);
                    cbZhifubao.setChecked(false);
                    break;
            }
        }
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
    public void showInfo(WyFwApp wyFwApp) {
        String[] fc = {};
        fc = new String[wyFwApp.getHouse().size()];
        for (int i = 0; i < wyFwApp.getHouse().size(); i++) {
            fc[i] = wyFwApp.getHouse().get(i).getFwAddr().split("\\/")[0];
        }
        tvXiangmu.setItems(fc);
        tvXiangmu.setSelectedIndex(0);
        setInfo(wyFwApp, 0);
        tvXiangmu.setOnItemSelectedListener((view, position, id, item) -> {
            setInfo(wyFwApp, position);
            curRoom = position;
        });
    }


    void setInfo(WyFwApp wyFwApp, int position) {
        fangwuId = wyFwApp.getHouse().get(position).getFwId();
        endTime = wyFwApp.getHouse().get(position).getJfWyUseEndTime();
        zhinajin = wyFwApp.getHouse().get(position).getArrearLateFees();
        arrearMonth = wyFwApp.getHouse().get(position).getArrearMonth();
        needMoney = wyFwApp.getHouse().get(position).getArrearages();
        if (wyFwApp.getHouse().get(position).getJfWyIsLateFees() == 0) {
            if (wyFwApp.getHouse().get(position).getArrearLateFees() > 0) {
                zhinajin = wyFwApp.getHouse().get(position).getArrearLateFees();
                needMoney += zhinajin;
            }
        }
        if (wyFwApp.getHouse().get(position).getJfWyTypeFeiyong() == null) {
            ToastUtil.show(WuyeMoneyActivity.this, "以上房屋信息有误,请联系物业");
            canJiaoFei = false;
        } else {
            oneAreaPrice = wyFwApp.getHouse().get(position).getJfWyTypeFeiyong();
            canJiaoFei = true;
        }
        //如果欠缴的费用小于0，那么最低交费金额为0
        if (needMoney <= 0) {
            needMoney = 0;
        }
//        needMoney = ;
        tvQianjiao.setText("¥ " + new BigDecimal(needMoney).setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue());
        allMoney = needMoney;
        area = Float.parseFloat(wyFwApp.getHouse().get(position).getFwAddr().split("\\:")[2]);
        try {
            tvLastTime.setText("缴费截止时间" + wyFwApp.getHouse().get(position).getJfWyUseEndTime().substring(0, 10));
        } catch (Exception e) {
            tvLastTime.setText("信息有误");
        }
        jiaofeiMonth = arrearMonth;
        caculatePrePrice();
        tvCount.setText("" + jiaofeiMonth);
    }

    @Override
    public void onPaySuccess() {
        initData();
        ToastUtil.show(this, "缴费成功");
    }

    @OnClick({R.id.iv_jian, R.id.iv_jia, R.id.tv_mingxi, R.id.bt_submit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_jian:
                if (!canJiaoFei) {
                    ToastUtil.show(this, "以上房间信息有误，请联系物业");
                    return;
                }
                if (jiaofeiMonth <= arrearMonth) {
                    return;
                }
                jiaofeiMonth -= 1;
                tvCount.setText("" + jiaofeiMonth);
                caculatePrePrice();
                break;
            case R.id.iv_jia:
                if (!canJiaoFei) {
                    ToastUtil.show(this, "以上房间信息有误，请联系物业");
                    return;
                }
                jiaofeiMonth += 1;
                tvCount.setText("" + jiaofeiMonth);
                caculatePrePrice();
                break;
            case R.id.tv_mingxi:
                if (!canJiaoFei) {
                    ToastUtil.show(this, "以上房间信息有误，请联系物业");
                    return;
                }
                mPresenter.mingxiOnclick(curRoom);
                break;
            case R.id.bt_submit:
                if (!canJiaoFei) {
                    Toast.makeText(this, "以上房间信息有误，请联系物业", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (allMoney == 0) {
                    Toast.makeText(this, "请选择预交费用", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (cbYinlian.isChecked()) {
                    pay = 3;
                    bianhao = Contains.user.getYezhuId() + "wy" + System.currentTimeMillis() + "";
                    surePay();
                    //yinlianPay();
//                    Toast.makeText(this, "暂时无法支持银联支付", Toast.LENGTH_SHORT).show();
                } else if (cbWeixin.isChecked()) {
                    PayContain.weixinPayResult = PayContain.WEI_XIN_CHECKED;
                    PayContain.requestPayModule = PayContain.MODULE_WY_ORDER;
                    pay = 2;
                    bianhao = Contains.user.getYezhuId() + "wy" + System.currentTimeMillis() + "";
                    surePay();
                } else if (cbZhifubao.isChecked()) {
                    pay = 1;
                    bianhao = Contains.user.getYezhuId() + "wy" + System.currentTimeMillis() + "";
                    surePay();
                } else {
                    Toast.makeText(this, "请选择支付方式", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }

    private void caculatePrePrice() {
        preMoney = (jiaofeiMonth - arrearMonth) * area * oneAreaPrice;
        //四舍五入
//        preMoney = new BigDecimal(preMoney).setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue();
        tvYujiao.setText("¥ " + new BigDecimal(preMoney).setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue());
        allMoney = preMoney + needMoney;
        allMoney = new BigDecimal(allMoney).setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue();
        tvZongji.setText("¥ " + allMoney);
        if (zhinajin != 0) {
            tvZongji.setText("¥ " + allMoney + "(其中滞纳金:" + new BigDecimal(zhinajin).setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue() + ")");
        }
    }

    //物业缴费支付
    private void surePay() {
//        if (payController == null) {
//            payController = new PayControllerImpl();
//        }
//        int tmonth = month + Integer.parseInt(Unmonth);
//        Logger.d(Integer.parseInt(Unmonth) + "-----------------------------------");
        String add = mPresenter.addDay(endTime, (jiaofeiMonth));
        KLog.i(add);
        Map<String, String> parm = new HashMap<String, String>();
        parm.put("wp.fwId", fangwuId + "");//缴费房屋id(必填)
        parm.put("wp.month", add);//缴费之后的使用截止时间(必填，格式：2016-12-31 00:00:00 )
        parm.put("wp.s", (jiaofeiMonth) + "");//月数，缴几个月的物业费(必填,用数字 1，2，3。。。)
        parm.put("wp.jfUser", Contains.user.getYezhuName());//缴费人名称(选填，APP端使用业主名称)
        parm.put("wp.jsType", pay + "");//缴费结算方式:0现金；1支付宝；2微信；3银联
        parm.put("wp.znj", zhinajin + "");//滞纳金(必填，如果没有，则传递0)
        parm.put("wp.remark", "0");//备注(选填)
        parm.put("wp.chanquanren", Contains.user.getYezhuName());//产权人(必填，用户业主本身)
        parm.put("wp.jfWyRecLiushui", bianhao);//流水号（选填）
        parm.put("wp.jfTotal", allMoney + "");//实缴物业费
//        parm.put("wp.jfTotal", "0.01");//实缴物业费
        parm.put("wp.jfWyRecPayee", "Android客户端");
        parm.put("uuid", Contains.uuid);
        KLog.i(parm);
        mPresenter.getwuyePay(parm);
        mPresenter.setOtherParam(bianhao, allMoney);
//        mPresenter.setOtherParam(bianhao, 0.01);
    }

    @Override
    protected void onRestart() {
        if (pay == 3) {
            initData();
        }
        super.onRestart();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (PayContain.weixinPayResult !=null && PayContain.weixinPayResult == PayContain.WEI_XIN_CHECKED
                && PayContain.payResult!=null && PayContain.payResult == PayContain.PAY_SUCCESS
                && PayContain.requestPayModule!=null &&  PayContain.requestPayModule == PayContain.MODULE_WY_ORDER){
            initData();
            PayContain.weixinPayResult = null;
            PayContain.requestPayModule = null;
        }
    }

    @Override
    public void yinlianPay(int jfRecId) {
        String yuming_api = "http://www.hnchxwl.com/";
        if (!StringUitl.isNoEmpty(allMoney + "")) {
            return;
        }
        float a = Float.parseFloat(allMoney + "") * 100;
        int money = (int) a;
        Intent intent = new Intent();
        intent.setClass(this, // context
                WebViewActivity.class);// 跳转的activity
        Bundle ylzf = new Bundle();
        ylzf.putString("name", "银联支付");
        ylzf.putString("address", yuming_api+"/CHINAPAY_DEMO/signServlet.do?BusiType=0001&Version=20140728&CommodityMsg=wwxtest&MerPageUrl="+yuming_api+"/CHINAPAY_DEMO/pgReturn.do&MerBgUrl="+yuming_api+"/CHINAPAY_DEMO/bgReturn.do&MerId=531121608230001&" +
                "MerOrderNo=" + bianhao + "&OrderAmt=" + money + "&TranDate=" + StringUitl.getNowDateShort() + "&TranTime=" + StringUitl.getTimeShort() + "&MerResv=1");
        intent.putExtras(ylzf);
//        KLog.i(yuming_api+"/CHINAPAY_DEMO/signServlet.do?BusiType=0001&Version=20140728&CommodityMsg=wwxtest&MerPageUrl="+yuming_api+"/CHINAPAY_DEMO/pgReturn.do&MerBgUrl="+yuming_api+"/CHINAPAY_DEMO/bgReturn.do&MerId=531121608230001&" +
//                "MerOrderNo=" + jfRecId + "&OrderAmt=" + money + "&TranDate=" + StringUitl.getNowDateShort() + "&TranTime=" + StringUitl.getTimeShort() + "&MerResv=1");
        startActivity(intent);
    }
}