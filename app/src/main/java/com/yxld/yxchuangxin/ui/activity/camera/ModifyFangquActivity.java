package com.yxld.yxchuangxin.ui.activity.camera;

import android.content.Context;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

import com.socks.library.KLog;
import com.yxld.yxchuangxin.R;
import com.yxld.yxchuangxin.Utils.UIUtils;
import com.yxld.yxchuangxin.application.AppConfig;
import com.yxld.yxchuangxin.base.BaseActivity;
import com.yxld.yxchuangxin.contain.Contains;
import com.yxld.yxchuangxin.entity.FangquEntity;
import com.yxld.yxchuangxin.ui.activity.camera.component.DaggerModifyFangquComponent;
import com.yxld.yxchuangxin.ui.activity.camera.contract.ModifyFangquContract;
import com.yxld.yxchuangxin.ui.activity.camera.module.ModifyFangquModule;
import com.yxld.yxchuangxin.ui.activity.camera.presenter.ModifyFangquPresenter;
import com.yxld.yxchuangxin.view.CustomPopWindow;
import com.yxld.yxchuangxin.view.datepicker.NumericWheelAdapter;
import com.yxld.yxchuangxin.view.datepicker.WheelView;
import com.zhy.autolayout.AutoLinearLayout;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author hzp
 * @Package com.yxld.yxchuangxin.ui.activity.camera
 * @Description: $description
 * @date 2017/09/19 17:43:45
 */

public class ModifyFangquActivity extends BaseActivity implements ModifyFangquContract.View {

    @Inject
    ModifyFangquPresenter mPresenter;
    @BindView(R.id.mingcheng)
    EditText mingcheng;
    @BindView(R.id.check_bufangshijian)
    AutoLinearLayout checkBufangshijian;
    @BindView(R.id.bianhao)
    TextView bianhao;
    @BindView(R.id.check_chefangshijian)
    AutoLinearLayout checkChefangshijian;
    @BindView(R.id.tv_fangquleiixng)
    TextView tvFangquleiixng;
    @BindView(R.id.check_fangquleixing)
    AutoLinearLayout checkFangquleixing;
    @BindView(R.id.tv_mingdizhuangtai)
    TextView tvMingdizhuangtai;
    @BindView(R.id.beizhu)
    EditText beizhu;
    @BindView(R.id.check_jieshuzhuangtai)
    AutoLinearLayout checkJieshuzhuangtai;
    @BindView(R.id.confirm)
    TextView confirm;
    @BindView(R.id.cacanl)
    TextView cacanl;
    private FangquEntity.DataBean dataBean;
    private WheelView wheelView;
    private NumericWheelAdapter xiangmuAdapter;
    private ArrayList<String> fangquList = new ArrayList<>();
    private ArrayList<String> mingdiList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initView() {
        setContentView(R.layout.activity_modify_fangqu);
        ButterKnife.bind(this);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    protected void initData() {
        fangquList.add("普通防区");
        fangquList.add("紧急防区");
        fangquList.add("留守防区");
        mingdiList.add("关");
        mingdiList.add("开");
        dataBean = getIntent().getParcelableExtra("databean");
        mingcheng.setText(dataBean.getShebeiName());
        bianhao.setText(dataBean.getShebeiFangquBianhao());
        tvFangquleiixng.setText(parseFangqu(dataBean.getShebeiFangquLeixin()));
        tvMingdizhuangtai.setText(parseMingdi(dataBean.getShebeiMingliKaiguan()));
        beizhu.setText(dataBean.getShebeiBeizhu());
    }

    private String parseFangqu(String leixing) {
        switch (leixing) {
            case "0":
                return "普通防区";
            case "1":
                return "紧急防区";
            case "2":
                return "留守防区";
            case "普通防区":
                return "0";
            case "紧急防区":
                return "1";
            case "留守防区":
                return "2";
        }
        return "";
    }
    private String parseMingdi(String migndi) {
        switch (migndi) {
            case "0":
                return "关";
            case "1":
                return "开";
            case "关":
                return "0";
            case "开":
                return "1";
        }
        return "";
    }

    @Override
    protected void setupActivityComponent() {
        DaggerModifyFangquComponent
                .builder()
                .appComponent(((AppConfig) getApplication()).getApplicationComponent())
                .modifyFangquModule(new ModifyFangquModule(this))
                .build()
                .inject(this);
    }

    @Override
    public void setPresenter(ModifyFangquContract.ModifyFangquContractPresenter presenter) {
        mPresenter = (ModifyFangquPresenter) presenter;
    }

    @Override
    public void showProgressDialog() {
        progressDialog.show();
    }

    @Override
    public void closeProgressDialog() {
        progressDialog.hide();
    }

    @OnClick({R.id.tv_fangquleiixng, R.id.tv_mingdizhuangtai, R.id.confirm, R.id.cacanl})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_fangquleiixng:
                xiangmuAdapter = new NumericWheelAdapter(ModifyFangquActivity.this, 0, fangquList.size() - 1, "", fangquList);
                xiangmuAdapter.setTextSize(15);
                showWheelView(tvFangquleiixng, 0);
                break;
            case R.id.tv_mingdizhuangtai:
                xiangmuAdapter = new NumericWheelAdapter(ModifyFangquActivity.this, 0, mingdiList.size() - 1, "", mingdiList);
                xiangmuAdapter.setTextSize(15);
                showWheelView(tvMingdizhuangtai, 1);
                break;
            case R.id.confirm:
                updateFangqu();
                break;
            case R.id.cacanl:
                finish();
                break;
        }
    }

    /**
     * * 参数：
     * uuid
     * paianShebei.shebeiName 设备名称
     * paianShebei.shebeiFangquLeixin 防区类型 String
     * paianShebei.shebeiMingliKaiguan 鸣笛开关 String
     * paianShebei.shebeiBeizhu 备注 String
     * paianShebei.shebeiId 设备ID Integer
     * paianShebei.shebeiZhujiMac 主机mac String
     * paianShebei.shebeiFangquBianhao 防区编号 String
     */
    private void updateFangqu() {
        Map<String, String> map = new HashMap<>();
        map.put("uuid", Contains.uuid);
        map.put("paianShebei.shebeiName", mingcheng.getText().toString().trim());
        map.put("paianShebei.shebeiFangquLeixin", parseFangqu(tvFangquleiixng.getText().toString()));
        map.put("paianShebei.shebeiMingliKaiguan", parseMingdi(tvMingdizhuangtai.getText().toString()));
        map.put("paianShebei.shebeiBeizhu", beizhu.getText().toString().trim());
        map.put("paianShebei.shebeiZhujiMac", dataBean.getShebeiZhujiMac());
        map.put("paianShebei.shebeiId", dataBean.getShebeiId() + "");
        map.put("paianShebei.shebeiFangquBianhao", dataBean.getShebeiFangquBianhao());
        KLog.i(map);
        mPresenter.updateFangqu(map);
    }

    private void showWheelView(View showView, int flag) {
        InputMethodManager imm = (InputMethodManager) this.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(showView.getWindowToken(), 0);
        View view = LayoutInflater.from(this).inflate(R.layout.picker_xiangmu, null);
        AutoLinearLayout ll_content = (AutoLinearLayout) view.findViewById(R.id.ll_content);
        ll_content.setAnimation(AnimationUtils.loadAnimation(this, R.anim.pop_manage_product_in));
        TextView submit = (TextView) ll_content.findViewById(R.id.submit);
        TextView tv_title = (TextView) ll_content.findViewById(R.id.tv_title);
        tv_title.setText("请选择项目");
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CustomPopWindow.onBackPressed();
                if (flag == 0) {
                    tvFangquleiixng.setText(fangquList.get(wheelView.getCurrentItem()));
                }
                if (flag == 1) {
                    tvMingdizhuangtai.setText(mingdiList.get(wheelView.getCurrentItem()));
                }
            }
        });
        wheelView = (WheelView) ll_content.findViewById(R.id.fangqu);
        wheelView.setViewAdapter(xiangmuAdapter);
        new CustomPopWindow.PopupWindowBuilder(this)
                .setClippingEnable(false)
                .setFocusable(true)
                .setView(view)
                .setContenView(ll_content)
                .size(UIUtils.getDisplayWidth(this), UIUtils.getDisplayHeigh(this))
                .create()
                .showAtLocation(showView, Gravity.CENTER, 0, 0);
    }
}