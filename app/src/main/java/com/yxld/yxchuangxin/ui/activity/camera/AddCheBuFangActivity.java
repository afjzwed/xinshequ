package com.yxld.yxchuangxin.ui.activity.camera;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.socks.library.KLog;
import com.yxld.yxchuangxin.R;
import com.yxld.yxchuangxin.Utils.ToastUtil;
import com.yxld.yxchuangxin.Utils.UIUtils;
import com.yxld.yxchuangxin.application.AppConfig;
import com.yxld.yxchuangxin.base.BaseActivity;
import com.yxld.yxchuangxin.contain.Contains;
import com.yxld.yxchuangxin.entity.FangquEntity;
import com.yxld.yxchuangxin.entity.FangquList;
import com.yxld.yxchuangxin.ui.activity.camera.component.DaggerAddCheBuFangComponent;
import com.yxld.yxchuangxin.ui.activity.camera.contract.AddCheBuFangContract;
import com.yxld.yxchuangxin.ui.activity.camera.module.AddCheBuFangModule;
import com.yxld.yxchuangxin.ui.activity.camera.presenter.AddCheBuFangPresenter;
import com.yxld.yxchuangxin.ui.adapter.camera.CheckFangQuAdapter;
import com.yxld.yxchuangxin.ui.adapter.camera.SevenDayAdapter;
import com.yxld.yxchuangxin.view.CustomPopWindow;
import com.yxld.yxchuangxin.view.RxDialogWheelDayMinute;
import com.yxld.yxchuangxin.view.datepicker.NumericWheelAdapter;
import com.yxld.yxchuangxin.view.datepicker.WheelView;
import com.zhy.autolayout.AutoLinearLayout;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author hzp
 * @Package com.yxld.yxchuangxin.ui.activity.camera
 * @Description: $description
 * @date 2017/09/05 18:10:45
 */

public class AddCheBuFangActivity extends BaseActivity implements AddCheBuFangContract.View {

    @Inject
    AddCheBuFangPresenter mPresenter;

    @Inject
    SevenDayAdapter sevenDayAdapter;

    @Inject
    CheckFangQuAdapter checkFangQuAdapter;
    @BindView(R.id.recycerView)
    RecyclerView recycerView;
    @BindView(R.id.recycerView_fangqu)
    RecyclerView recycerViewFangqu;
    @BindView(R.id.tv_confirm)
    TextView tvConfirm;
    @BindView(R.id.tv_cacanl)
    TextView tvCacanl;
    @BindView(R.id.check_bufangshijian)
    AutoLinearLayout checkBufangshijian;
    @BindView(R.id.check_chefangshijian)
    AutoLinearLayout checkChefangshijian;
    @BindView(R.id.jieshu_zhuangtai)
    TextView jieshuZhuangtai;
    @BindView(R.id.check_jieshuzhuangtai)
    AutoLinearLayout checkJieshuzhuangtai;
    @BindView(R.id.tv_bufang_shijain)
    TextView tvBufangShijain;
    @BindView(R.id.tv_chefang_shijian)
    TextView tvChefangShijian;
    private WheelView fangqu;

    private FangquEntity fangquEntity;
    private NumericWheelAdapter zhuangtaiAdapter;
    private RxDialogWheelDayMinute mRxDialogWheelYearMonthDay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initView() {
        setContentView(R.layout.activity_add_che_bu_fang);
        ButterKnife.bind(this);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    protected void initData() {
        this.fangquEntity = Contains.fangquEntity;
        GridLayoutManager gl = new GridLayoutManager(this, 3);
        recycerView.setLayoutManager(gl);
        recycerView.setAdapter(sevenDayAdapter);
        recycerView.setNestedScrollingEnabled(false);
        LinearLayoutManager ll = new LinearLayoutManager(this);
        recycerViewFangqu.setLayoutManager(ll);
        recycerViewFangqu.setAdapter(checkFangQuAdapter);
        checkFangQuAdapter.setNewData(getFangqu());
        KLog.i(checkFangQuAdapter.getData().size());
        recycerViewFangqu.setNestedScrollingEnabled(false);
        checkFangQuAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {

            }
        });
    }

    private List<FangquList> getFangqu() {
        KLog.i("进入提供数据的方法");
        List<FangquList> list = new ArrayList<>();
        for (int i = 0; i < Contains.fangquEntity.getData().size(); i++) {
            KLog.i(Contains.fangquEntity.getData().size());
            if (i == 0) {
                FangquList fangquList = new FangquList();
                fangquList.setFangQuName(Contains.fangquEntity.getData().get(i).getShebeiFangquLeixin());
                FangquEntity.DataBean dataBean = Contains.fangquEntity.getData().get(i);
                List<FangquEntity.DataBean> list1 = new ArrayList<>();
                list1.add(dataBean);
                fangquList.setFangQuList(list1);
                list.add(fangquList);
                continue;
            }
            for (int j = 0; j < list.size(); j++) {
                if (Contains.fangquEntity.getData().get(i).getShebeiFangquLeixin().equals(list.get(j).getFangQuName())) {
                    list.get(j).getFangQuList().add(Contains.fangquEntity.getData().get(i));
                    KLog.i("有了，添加就行");
                    break;
                }
                if (j == list.size() - 1) {
                    KLog.i("没有，需要new一个");
                    FangquList fangquList = new FangquList();
                    fangquList.setFangQuName(Contains.fangquEntity.getData().get(i).getShebeiFangquLeixin());
                    FangquEntity.DataBean dataBean = Contains.fangquEntity.getData().get(i);
                    List<FangquEntity.DataBean> list1 = new ArrayList<>();
                    list1.add(dataBean);
                    fangquList.setFangQuList(list1);
                    list.add(fangquList);
                    break;
                }
            }
        }
        return list;
    }


    @Override
    protected void setupActivityComponent() {
        DaggerAddCheBuFangComponent
                .builder()
                .appComponent(((AppConfig) getApplication()).getApplicationComponent())
                .addCheBuFangModule(new AddCheBuFangModule(this))
                .build()
                .inject(this);
    }

    @Override
    public void setPresenter(AddCheBuFangContract.AddCheBuFangContractPresenter presenter) {
        mPresenter = (AddCheBuFangPresenter) presenter;
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
    public void addSuccesse(String content) {
        setResult(5);
        AppConfig.getInstance().mAppActivityManager.finishActivity(TimeCheBuFangActivity.class);
        finish();
        overridePendingTransition(0, R.anim.activity_translate_out_1);
    }

    @OnClick({R.id.tv_confirm, R.id.tv_cacanl, R.id.check_bufangshijian, R.id.check_chefangshijian, R.id.check_jieshuzhuangtai})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_confirm:
                if (tvBufangShijain.getText().toString().equals("")) {
                    ToastUtil.displayShortToast("请选择布防时间");
                    return;
                }
                if (tvChefangShijian.getText().toString().equals("")) {
                    ToastUtil.displayShortToast("请选择撤防时间");
                    return;
                }
                if (jieshuZhuangtai.getText().toString().equals("")) {
                    ToastUtil.displayShortToast("请选择结束状态");
                    return;
                }
                submitData();
                break;
            case R.id.tv_cacanl:
                finish();
                overridePendingTransition(0, R.anim.activity_translate_out_1);
                break;
            case R.id.check_bufangshijian:
                initWheelYearMonthDayDialog(0);
                break;
            case R.id.check_chefangshijian:
                initWheelYearMonthDayDialog(1);
                break;
            case R.id.check_jieshuzhuangtai:
                showWheelView(jieshuZhuangtai);
                break;
        }
    }

    private void submitData() {
        boolean isCheckWeek = false;
        String weeks = "";
        for (int i = 0; i < sevenDayAdapter.getData().size(); i++) {
            if (sevenDayAdapter.getData().get(i).isCheck()) {
                weeks += "1,";
                isCheckWeek = true;
            } else {
                weeks += "0,";
            }
        }
        if (!isCheckWeek) {
            ToastUtil.displayShortToast("请选择布防的星期数");
            return;
        }
        KLog.i(weeks);
        String fangqu = "";
        for (int i = 0; i < checkFangQuAdapter.getData().size(); i++) {
            for (int j = 0; j < checkFangQuAdapter.getData().get(i).getFangQuList().size(); j++) {
//                if (checkFangQuAdapter.getData().get(i).getFangQuList().get(j).isCheck()) {
                fangqu += checkFangQuAdapter.getData().get(i).getFangQuList().get(j).getShebeiFangquBianhao() + ",";
//                }
            }
        }
        if (fangqu.length() == 0) {
            ToastUtil.displayShortToast("请选择布防区域");
            return;
        }
        Map<String, String> map = new HashMap<>();
        map.put("uuid", Contains.uuid);
        map.put("weeks", weeks.substring(0, weeks.length() - 1));
        map.put("fangqu", fangqu.substring(0, fangqu.length() - 1));  //0 ---128
        map.put("chefangleixing", jieshuZhuangtai.getText().equals("撤防") ? "0" : "1");  //撤防类型  0撤防 1留守布防
        map.put("bufangshijian", tvBufangShijain.getText().toString());
        map.put("chefangshijian", tvChefangShijian.getText().toString());
        map.put("mac", getIntent().getStringExtra("mac"));
        KLog.i(map);
        mPresenter.saveBufang(map);
    }

    @Override
    public void onBackPressed() {
        if (CustomPopWindow.onBackPressed()) {
            return;
        }
        finish();
        overridePendingTransition(0, R.anim.activity_translate_out_1);
    }

    ArrayList<String> zhuangtaiList;

    private void showWheelView(View showView) {
        zhuangtaiList = new ArrayList<>();
        zhuangtaiList.add("撤防");                      //撤防类型  0撤防 1留守布防
        zhuangtaiList.add("留守布防");
        zhuangtaiAdapter = new NumericWheelAdapter(this, 0, zhuangtaiList.size() - 1, "", zhuangtaiList);
        zhuangtaiAdapter.setTextSize(15);
        View view = LayoutInflater.from(this).inflate(R.layout.picker_fangqu, null);
        AutoLinearLayout ll_content = (AutoLinearLayout) view.findViewById(R.id.ll_content);
        ll_content.setAnimation(AnimationUtils.loadAnimation(this, R.anim.pop_manage_product_in));
        TextView submit = (TextView) ll_content.findViewById(R.id.submit);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                jieshuZhuangtai.setText(zhuangtaiList.get(fangqu.getCurrentItem()));
                CustomPopWindow.onBackPressed();
            }
        });
        fangqu = (WheelView) ll_content.findViewById(R.id.fangqu);
        fangqu.setViewAdapter(zhuangtaiAdapter);
        new CustomPopWindow.PopupWindowBuilder(this)
                .setClippingEnable(false)
                .setFocusable(true)
                .setView(view)
                .setContenView(ll_content)
                .size(UIUtils.getDisplayWidth(this), UIUtils.getDisplayHeigh(this))
                .create()
                .showAtLocation(showView, Gravity.CENTER, 0, 0);
    }

    private void initWheelYearMonthDayDialog(int flag) {
        // ------------------------------------------------------------------选择日期开始
        mRxDialogWheelYearMonthDay = new RxDialogWheelDayMinute(this, 1994, 2017);
        mRxDialogWheelYearMonthDay.getTv_sure().setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View arg0) {
                        if (flag == 0) {
                            tvBufangShijain.setText(mRxDialogWheelYearMonthDay.getSelectorMonth() + ":" + mRxDialogWheelYearMonthDay.getSelectorDay());
                        } else {
                            tvChefangShijian.setText(mRxDialogWheelYearMonthDay.getSelectorMonth() + ":" + mRxDialogWheelYearMonthDay.getSelectorDay());
                        }
                        mRxDialogWheelYearMonthDay.cancel();
                    }
                });
        mRxDialogWheelYearMonthDay.getTv_cancle().setOnClickListener(
                new View.OnClickListener() {

                    @Override
                    public void onClick(View arg0) {
                        mRxDialogWheelYearMonthDay.cancel();
                    }
                });
        mRxDialogWheelYearMonthDay.show();
        // ------------------------------------------------------------------选择日期结束
    }

}