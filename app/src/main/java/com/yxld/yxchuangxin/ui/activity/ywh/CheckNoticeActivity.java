package com.yxld.yxchuangxin.ui.activity.ywh;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ScrollView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.yxld.yxchuangxin.R;
import com.yxld.yxchuangxin.application.AppConfig;
import com.yxld.yxchuangxin.base.BaseActivity;
import com.yxld.yxchuangxin.base.BaseEntity;
import com.yxld.yxchuangxin.data.api.API;
import com.yxld.yxchuangxin.entity.YwhCurrentflow;
import com.yxld.yxchuangxin.ui.activity.ywh.component.DaggerCheckNoticeComponent;
import com.yxld.yxchuangxin.ui.activity.ywh.contract.CheckNoticeContract;
import com.yxld.yxchuangxin.ui.activity.ywh.module.CheckNoticeModule;
import com.yxld.yxchuangxin.ui.activity.ywh.presenter.CheckNoticePresenter;
import com.yxld.yxchuangxin.ui.adapter.ywh.YwhAccessoryAdapter;
import com.zhy.autolayout.AutoLinearLayout;

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
    @BindView(R.id.autoll)
    AutoLinearLayout autoll;
    @BindView(R.id.sv)
    ScrollView sv;

    private int position = 0;//根据这个值判断意见反馈的上级页面从而判断使用哪个接口
    private int isYjfk = 0;//意见反馈是否显示的标志 0显示 1不显示
    private YwhCurrentflow.DataBean.FlowBean.GongshiBean data;//公示
    private List<YwhCurrentflow.DataBean.FlowBean.ConfirmPeopleBean> listdata;//人员列表
    private List<YwhCurrentflow.DataBean.FlowBean.FilesBean> file_listdata;//附件列表
    private YwhAccessoryAdapter ywhAccessoryAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initView() {
        setContentView(R.layout.activity_ywh_checknotice);
        ButterKnife.bind(this);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // TODO: 2018/11/12 整个页面内容都从上级页面取
        data = getIntent().getParcelableExtra("ywh_gongshi");
        listdata = getIntent().getParcelableArrayListExtra("data");
        position = getIntent().getIntExtra("ywh_position", 0);
        isYjfk = getIntent().getIntExtra("isYjfk", 0);
        file_listdata = getIntent().getParcelableArrayListExtra("ywh_member_list");

        if (isYjfk == 0) {
            tvMenu.setVisibility(View.VISIBLE);
        } else {
            tvMenu.setVisibility(View.GONE);
        }
        tvMenu.setText("意见反馈");
        tvMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CheckNoticeActivity.this, FkyjActivity.class);
                intent.putExtra("ywh_gongshiId", data.getId());
                intent.putExtra("ywh_position", position);
                startActivity(intent);
            }
        });
        if (data != null) {
            titleRecommendMember.setText("" + data.getTitle());
            tvSendTime.setText("发布时间：" + data.getStarttime());
            tvNotice.setText("" + data.getContent());
        }

        if (listdata != null && listdata.size() > 0) {
            autoll.setVisibility(View.VISIBLE);
        } else {
            autoll.setVisibility(View.GONE);
        }

        recyclerView.setNestedScrollingEnabled(false);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        ywhAccessoryAdapter = new YwhAccessoryAdapter();
        ywhAccessoryAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter baseQuickAdapter, View view, int position) {
//                YwhCurrentflow.DataBean.FlowBean.FilesBean filesBean = ywhAccessoryAdapter.getData().get(position);
//                Intent intent = new Intent(CheckNoticeActivity.this, YwhWebViewActivity.class);
//                Bundle bundle = new Bundle();
//                bundle.putString("address", filesBean.getUrl());
//                intent.putExtras(bundle);
//                startActivity(intent);
                YwhCurrentflow.DataBean.FlowBean.FilesBean filesBean = ywhAccessoryAdapter.getData().get(position);
                Uri uri = Uri.parse(API.ywh_pic + filesBean.getUrl());
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });
        recyclerView.setAdapter(ywhAccessoryAdapter);//绑定适配器
    }

    @Override
    protected void initData() {
        //网络请求 获得数据
//        Map<String, String> map = new HashMap<>();
//        map.put("uuid", Contains.uuid);
//        mPresenter.getData(map);

        if (file_listdata != null && file_listdata.size() > 0) {
            recyclerView.setVisibility(View.VISIBLE);
        } else {
            recyclerView.setVisibility(View.GONE);
        }
        recyclerView.setFocusable(false);
        if (file_listdata != null && file_listdata.size() > 0) {
            ywhAccessoryAdapter.setNewData(file_listdata);
        }
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
//        Intent intent = new Intent(this, CymdActivity.class);
//        intent.putExtra("isYjfk", 1);
//        startActivity(intent);
        Bundle bundle = new Bundle();
        bundle.putParcelableArrayList("data", (ArrayList<? extends Parcelable>) listdata);
        bundle.putInt("isYjfk", 1);
        startActivity(CymdActivity.class, bundle);//成员名单公示
    }
}