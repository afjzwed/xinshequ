package com.yxld.yxchuangxin.ui.activity.ywh;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.yxld.yxchuangxin.R;
import com.yxld.yxchuangxin.application.AppConfig;
import com.yxld.yxchuangxin.base.BaseActivity;
import com.yxld.yxchuangxin.base.BaseEntity;
import com.yxld.yxchuangxin.ui.activity.ywh.component.DaggerCheckNoticeComponent;
import com.yxld.yxchuangxin.ui.activity.ywh.contract.CheckNoticeContract;
import com.yxld.yxchuangxin.ui.activity.ywh.module.CheckNoticeModule;
import com.yxld.yxchuangxin.ui.activity.ywh.presenter.CheckNoticePresenter;
import com.yxld.yxchuangxin.ui.adapter.ywh.YwhAccessoryAdapter;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author William
 * @Package com.yxld.yxchuangxin.ui.activity.ywh
 * @Description: 查看通知
 * @date 2018/11/08 17:11:57
 */

public class CheckNoticeActivity extends BaseActivity implements CheckNoticeContract.View {

    @Inject
    CheckNoticePresenter mPresenter;
    @BindView(R.id.title_recommend_member)
    TextView titleRecommendMember;
    @BindView(R.id.tv_send_time)
    TextView tvSendTime;
    @BindView(R.id.tv_notice)
    TextView tvNotice;
    @BindView(R.id.tv_click_name1)
    TextView tvClickName1;
    @BindView(R.id.rv)
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initView() {
        setContentView(R.layout.activity_ywh_checknotice);
        ButterKnife.bind(this);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        recyclerView.setVisibility(View.GONE);

        tvMenu.setVisibility(View.VISIBLE);
        tvMenu.setText("意见反馈");
        tvMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(FkyjActivity.class);
            }
        });

        // TODO: 2018/11/12 整个页面内容都从上级页面取


    }

    @Override
    protected void initData() {
        //网络请求 获得数据
//        Map<String, String> map = new HashMap<>();
//        map.put("uuid", Contains.uuid);
//        mPresenter.getData(map);

    }

    @Override
    public void setData(BaseEntity baseEntity) {

    }

    @Override
    protected void onDestroy() {
        mPresenter.unsubscribe();
        super.onDestroy();
    }

    @Override
    protected void setupActivityComponent() {
        DaggerCheckNoticeComponent
                .builder()
                .appComponent(((AppConfig) getApplication()).getApplicationComponent())
                .checkNoticeModule(new CheckNoticeModule(this))
                .build()
                .inject(this);
    }

    @Override
    public void setPresenter(CheckNoticeContract.CheckNoticeContractPresenter presenter) {
        mPresenter = (CheckNoticePresenter) presenter;
    }

    @Override
    public void showProgressDialog() {
        progressDialog.show();
    }

    @Override
    public void closeProgressDialog() {
        progressDialog.hide();
    }

    @OnClick(R.id.tv_click_name1)
    public void onViewClicked() {
        Intent intent = new Intent(this, CymdActivity.class);
        startActivity(intent);
    }
}