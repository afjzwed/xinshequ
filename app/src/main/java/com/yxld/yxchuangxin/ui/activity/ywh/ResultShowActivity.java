package com.yxld.yxchuangxin.ui.activity.ywh;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.yxld.yxchuangxin.R;
import com.yxld.yxchuangxin.application.AppConfig;
import com.yxld.yxchuangxin.base.BaseActivity;
import com.yxld.yxchuangxin.base.BaseEntity;
import com.yxld.yxchuangxin.entity.YwhCurrentflow;
import com.yxld.yxchuangxin.ui.activity.ywh.component.DaggerResultShowComponent;
import com.yxld.yxchuangxin.ui.activity.ywh.contract.ResultShowContract;
import com.yxld.yxchuangxin.ui.activity.ywh.module.ResultShowModule;
import com.yxld.yxchuangxin.ui.activity.ywh.presenter.ResultShowPresenter;
import com.yxld.yxchuangxin.ui.adapter.ywh.YwhAccessoryAdapter;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author William
 * @Package com.yxld.yxchuangxin.ui.activity.wuye
 * @Description: 业主大会结果公示
 * @date 2018/11/07 20:09:47
 */

public class ResultShowActivity extends BaseActivity implements ResultShowContract.View {

    @Inject
    ResultShowPresenter mPresenter;
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
    @BindView(R.id.sv)
    ScrollView mScrollView;

    private YwhAccessoryAdapter ywhAccessoryAdapter;
    private YwhCurrentflow.DataBean.FlowBean.GongshiBean data;//公示
    private List<YwhCurrentflow.DataBean.FlowBean.FilesBean> listdata;//附件列表

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initView() {
        setContentView(R.layout.activity_ywh_checknotice);
        ButterKnife.bind(this);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        tvMenu.setVisibility(View.VISIBLE);
        tvMenu.setText("意见反馈");
        tvMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ResultShowActivity.this,FkyjActivity.class);
                intent.putExtra("ywh_position", 4);
                startActivity(intent);
            }
        });

        // TODO: 2018/11/12 整个页面内容都从上级页面取

        data = getIntent().getParcelableExtra("ywh_gongshi");
        listdata = getIntent().getParcelableArrayListExtra("ywh_member_list");
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setNestedScrollingEnabled(false);
        ywhAccessoryAdapter = new YwhAccessoryAdapter();
        ywhAccessoryAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter baseQuickAdapter, View view, int position) {
//                ywhAccessoryAdapter.getData().get(position);
                Toast.makeText(ResultShowActivity.this, "点击" + position, Toast.LENGTH_SHORT).show();
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

//        List<String> list = new ArrayList<>();
//        for (int i = 0; i < 10; i++) {
//            list.add("1");
//        }
//        ywhAccessoryAdapter.setNewData(list);
        recyclerView.setVisibility(View.VISIBLE);
        recyclerView.setFocusable(false);
        if (listdata != null && listdata.size() > 0) {
            ywhAccessoryAdapter.setNewData(listdata);
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
        DaggerResultShowComponent
                .builder()
                .appComponent(((AppConfig) getApplication()).getApplicationComponent())
                .resultShowModule(new ResultShowModule(this))
                .build()
                .inject(this);
    }

    @Override
    public void setPresenter(ResultShowContract.ResultShowContractPresenter presenter) {
        mPresenter = (ResultShowPresenter) presenter;
    }

    @Override
    public void showProgressDialog() {
        progressDialog.show();
    }

    @Override
    public void closeProgressDialog() {
        progressDialog.hide();
    }

    @OnClick({R.id.tv_click_name1})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_click_name1:
                Intent intent = new Intent(this, YwhMemberShowActivity.class);
                intent.putExtra("isYjfk", 0);
                startActivity(intent);
                break;
        }
    }
}