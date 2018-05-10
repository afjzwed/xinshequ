package com.yxld.yxchuangxin.ui.activity.wuye;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.yxld.yxchuangxin.R;
import com.yxld.yxchuangxin.Utils.TimeUtil;
import com.yxld.yxchuangxin.Utils.ToastUtil;
import com.yxld.yxchuangxin.application.AppConfig;
import com.yxld.yxchuangxin.base.BaseActivity;
import com.yxld.yxchuangxin.contain.Contains;
import com.yxld.yxchuangxin.data.api.API;
import com.yxld.yxchuangxin.entity.CarList;
import com.yxld.yxchuangxin.ui.activity.rim.WebViewActivity;
import com.yxld.yxchuangxin.ui.activity.wuye.component.DaggerCarAddMoneyComponent;
import com.yxld.yxchuangxin.ui.activity.wuye.contract.CarAddMoneyContract;
import com.yxld.yxchuangxin.ui.activity.wuye.module.CarAddMoneyModule;
import com.yxld.yxchuangxin.ui.activity.wuye.presenter.CarAddMoneyPresenter;
import com.yxld.yxchuangxin.view.AutoLinkStyleTextView;
import com.zhy.autolayout.AutoRelativeLayout;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.refactor.library.SmoothCheckBox;

/**
 * @author hu
 * @Package com.yxld.yxchuangxin.ui.activity.wuye
 * @Description: $description
 * @date 2017/07/06 15:05:41
 */

public class CarAddMoneyActivity extends BaseActivity implements CarAddMoneyContract.View {

    @Inject
    CarAddMoneyPresenter mPresenter;
    @BindView(R.id.tv_month_money)
    TextView tvMonthMoney;
    @BindView(R.id.tv_last_time)
    TextView tvLastTime;
    @BindView(R.id.tv_all_money)
    TextView tvAllMoney;
    @BindView(R.id.checkbox)
    SmoothCheckBox checkbox;
    @BindView(R.id.xieyi)
    AutoLinkStyleTextView xieyi;
    @BindView(R.id.bt_submit)
    Button btSubmit;
    @BindView(R.id.tv_jilu)
    TextView tvJilu;
    @BindView(R.id.tv_car_number)
    TextView tvCarNumber;
    @BindView(R.id.iv_jian)
    AutoRelativeLayout ivJian;
    @BindView(R.id.tv_count)
    TextView tvCount;
    @BindView(R.id.iv_jia)
    AutoRelativeLayout ivJia;
    private CarList.DataBean car;

    //缴费的月数
    int jiaofeiMonth = 1;

    //缴费的费用
    int allMoney;
    //每月的费用
    int MonthFee;

    //缴费到期的时间戳，
    private long lastTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initView() {
        setContentView(R.layout.activity_car_add_money);
        ButterKnife.bind(this);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        xieyi.setOnClickCallBack(new AutoLinkStyleTextView.ClickCallBack() {
            @Override
            public void onClick(int position) {
                if (position == 0) {
                    Intent cz = new Intent();
                    cz.setClass(CarAddMoneyActivity.this, // context
                            WebViewActivity.class);// 跳转的activity
                    Bundle cz1 = new Bundle();
                    cz1.putString("name", "停车位使用协议");
                    cz1.putString("address", API.PAY_CAR_XIEYI);
                    cz.putExtras(cz1);
                    startActivity(cz);
                }
            }
        });
    }

    @Override
    protected void initData() {
        checkbox.setChecked(true);
        car = (CarList.DataBean) getIntent().getSerializableExtra("car");
        tvCarNumber.setText(car.getClmediaNo());
        tvMonthMoney.setText("¥" + car.getParkFee() + "元/月");
        MonthFee = car.getParkFee();
        allMoney = MonthFee * jiaofeiMonth;
        tvAllMoney.setText("¥" + allMoney);
        lastTime = mPresenter.caculateTime(jiaofeiMonth, car.getCardTimr() + "");
        tvLastTime.setText(TimeUtil.timesTamp2YearMonthDay(lastTime));
    }

    @Override
    protected void setupActivityComponent() {
        DaggerCarAddMoneyComponent
                .builder()
                .appComponent(((AppConfig) getApplication()).getApplicationComponent())
                .carAddMoneyModule(new CarAddMoneyModule(this))
                .build()
                .inject(this);
    }

    @Override
    public void setPresenter(CarAddMoneyContract.CarAddMoneyContractPresenter presenter) {
        mPresenter = (CarAddMoneyPresenter) presenter;
    }

    @Override
    public void showProgressDialog() {
        progressDialog.show();
    }

    @Override
    public void closeProgressDialog() {
        progressDialog.hide();
    }

    @OnClick({R.id.bt_submit, R.id.tv_jilu, R.id.iv_jian, R.id.iv_jia})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.bt_submit:
                Intent intent1 = new Intent(this, PaySelectActivity.class);
                intent1.putExtra("orderMoney", allMoney + "");
                intent1.putExtra("lpid", Contains.appYezhuFangwus.get(Contains.curFangwu).getFwLoupanId() + "");
                intent1.putExtra("parkNo", car.getClparkNo());
                intent1.putExtra("mediaNo", car.getClmediaNo());
                intent1.putExtra("month", jiaofeiMonth + "");
                intent1.putExtra("startTime", car.getCardTimr() + "");
                intent1.putExtra("cardTimr", lastTime + "");
                intent1.putExtra("isnew", "1");
                startActivity(intent1);
                //// TODO: 2017/7/7 车辆缴费没做
                break;
            case R.id.tv_jilu:
                Intent intent = new Intent(this, CarMoneyRecordActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("car", car);
                intent.putExtras(bundle);
                startActivity(intent);
                break;
            case R.id.iv_jian:
                if (jiaofeiMonth == 1) {
                    return;
                }
                jiaofeiMonth--;
                tvCount.setText(jiaofeiMonth + "");
                allMoney = MonthFee * jiaofeiMonth;
                tvAllMoney.setText("¥" + allMoney);
                mPresenter.caculateTime(jiaofeiMonth, car.getCardTimr() + "");
                lastTime = mPresenter.caculateTime(jiaofeiMonth, car.getCardTimr() + "");
                tvLastTime.setText(TimeUtil.timesTamp2YearMonthDay(lastTime));
                break;
            case R.id.iv_jia:
                jiaofeiMonth++;
                lastTime = mPresenter.caculateTime(jiaofeiMonth, car.getCardTimr() + "");
//                if (lastTime > car.getProtocolTime()) {
//                    ToastUtil.show(this, "缴费日期不能超过协议日期");
//                    jiaofeiMonth--;
//                    lastTime = mPresenter.caculateTime(jiaofeiMonth, car.getCardTimr() + "");
//                    return;
//                }
                tvCount.setText(jiaofeiMonth + "");
                allMoney = MonthFee * jiaofeiMonth;
                tvAllMoney.setText("¥" + allMoney);
                tvLastTime.setText(TimeUtil.timesTamp2YearMonthDay(lastTime));
                break;
            default:
                break;
        }
    }

//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        if (requestCode == 0 && resultCode == PaySelectActivity.PAY_RESULT_SUCCESS) {
//            Map<String, String> map = new HashMap<>();
//            map.put("lpid", Contains.appYezhuFangwus.get(Contains.curFangwu).getFwLoupanId() + "");
//            map.put("parkNo", car.getClplNo());
//            map.put("mediaNo", car.getClmediaNo());
//            map.put("month", jiaofeiMonth + "");
//            map.put("cardTimr", lastTime + "");
//            map.put("money", allMoney + "");
//            map.put("isnew", "1");
//            map.put("rechargeType", data.getStringExtra("payWay"));            //缴费类型
//            map.put("rechargeSerialNum", data.getStringExtra("orderBianhao"));       //缴费序列号，编号
//            map.put("rechargeResult", "1");          //是否成功？
//            KLog.i(map);
//            mPresenter.affordMoney(map);
//        }
//        super.onActivityResult(requestCode, resultCode, data);
//    }

    @Override
    protected void onDestroy() {
        mPresenter.unsubscribe();
        super.onDestroy();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }
}