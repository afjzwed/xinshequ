package com.yxld.yxchuangxin.ui.activity.wuye;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.yxld.yxchuangxin.R;
import com.yxld.yxchuangxin.Utils.TimeUtil;
import com.yxld.yxchuangxin.Utils.ToastUtil;
import com.yxld.yxchuangxin.application.AppConfig;
import com.yxld.yxchuangxin.base.BaseActivity;
import com.yxld.yxchuangxin.contain.Contains;
import com.yxld.yxchuangxin.entity.Accredit;
import com.yxld.yxchuangxin.entity.AccreditDetail;
import com.yxld.yxchuangxin.ui.activity.wuye.component.DaggerFangxingDetaillComponent;
import com.yxld.yxchuangxin.ui.activity.wuye.contract.FangxingDetaillContract;
import com.yxld.yxchuangxin.ui.activity.wuye.module.FangxingDetaillModule;
import com.yxld.yxchuangxin.ui.activity.wuye.presenter.FangxingDetaillPresenter;
import com.yxld.yxchuangxin.ui.adapter.wuye.FangxingDetailAdapter;
import com.zhy.autolayout.AutoRelativeLayout;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author hu
 * @Package com.yxld.yxchuangxin.ui.activity.wuye
 * @Description: $description
 * @date 2017/06/14
 */

public class FangxingDetaillActivity extends BaseActivity implements FangxingDetaillContract.View {

    @Inject
    FangxingDetaillPresenter mPresenter;

    @Inject
    FangxingDetailAdapter fangxingDetailAdapter;

    @BindView(R.id.recycerView)
    RecyclerView recycerView;
    @BindView(R.id.tv_address)
    TextView tvAddress;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.tv_apply_time)
    TextView tvApplyTime;
    @BindView(R.id.tv_pass_time)
    TextView tvPassTime;
    @BindView(R.id.tv_project)
    TextView tvProject;
    @BindView(R.id.tv_status)
    TextView tvStatus;
    @BindView(R.id.rl_fangxing)
    AutoRelativeLayout rlFangxing;
    @BindView(R.id.bt_pass)
    Button btPass;
    private Accredit.DataBean dataBean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initView() {
        setContentView(R.layout.activity_fangxing_detaill);
        ButterKnife.bind(this);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    protected void initData() {
        recycerView.setAdapter(fangxingDetailAdapter);
        recycerView.setNestedScrollingEnabled(false);
        dataBean = (Accredit.DataBean) getIntent().getSerializableExtra("item");
        Map<String, String> map = new HashMap<>();
        map.put("pcId", dataBean.getId());
        mPresenter.getFangxingDetail(map);
        tvAddress.setText(dataBean.getNum());
        tvProject.setText(dataBean.getLoupanid());
        tvName.setText(dataBean.getName());
        tvApplyTime.setText(TimeUtil.timesTamp2YearMonthDay(dataBean.getApplyTime()));
        if (dataBean.getStatus() == 0) {
            tvStatus.setText("待放行");
        }
        if (dataBean.getStatus() == 1) {
            tvPassTime.setText(TimeUtil.timesTamp2YearMonthDay(Long.parseLong(dataBean.getPtime())));
            tvStatus.setText("已放行");
            rlFangxing.setVisibility(View.GONE);
        }
        if (!isYezhu()) {
            rlFangxing.setVisibility(View.GONE);
        }
    }

    @Override
    protected void setupActivityComponent() {
        DaggerFangxingDetaillComponent
                .builder()
                .appComponent(((AppConfig) getApplication()).getApplicationComponent())
                .fangxingDetaillModule(new FangxingDetaillModule(this))
                .build()
                .inject(this);
    }

    private boolean isYezhu() {
        if (Contains.user == null || Contains.appYezhuFangwus.size() == 0 || Contains.appYezhuFangwus.get(0) == null || Contains.appYezhuFangwus.get(0).getFwLoupanId() == 0
                || "".equals(Contains.appYezhuFangwus.get(0).getFwLoupanId())) {
            Toast.makeText(this, "需要在后台去配置您的业主信息", Toast.LENGTH_SHORT).show();
            return false;
        } else {
            //getFwyzType >1 就不是家人。权限低
            if (Contains.appYezhuFangwus.get(Contains.curFangwu).getFwyzType() > 1) {
                return false;
            } else {
                return true;
            }
        }
    }

    @Override
    public void setPresenter(FangxingDetaillContract.FangxingDetaillContractPresenter presenter) {
        mPresenter = (FangxingDetaillPresenter) presenter;
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
    public void setList(AccreditDetail detail) {
        fangxingDetailAdapter.setNewData(detail.getData());
    }

    @Override
    public void onPassAccredit() {
        ToastUtil.show(this, "放行成功");
        finish();
    }

    @OnClick(R.id.bt_pass)
    public void onViewClicked() {
        Map<String, String> map = new HashMap<>();
        map.put("pcId", dataBean.getId());
        mPresenter.passAccredit(map);
    }

    @Override
    protected void onDestroy() {
        mPresenter.unsubscribe();
        super.onDestroy();
    }
}