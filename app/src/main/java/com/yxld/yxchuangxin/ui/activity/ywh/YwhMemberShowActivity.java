package com.yxld.yxchuangxin.ui.activity.ywh;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.yxld.yxchuangxin.R;
import com.yxld.yxchuangxin.application.AppConfig;
import com.yxld.yxchuangxin.base.BaseActivity;

import com.yxld.yxchuangxin.base.BaseEntity;
import com.yxld.yxchuangxin.ui.activity.ywh.component.DaggerYwhMemberShowComponent;
import com.yxld.yxchuangxin.ui.activity.ywh.contract.YwhMemberShowContract;
import com.yxld.yxchuangxin.ui.activity.ywh.module.YwhMemberShowModule;
import com.yxld.yxchuangxin.ui.activity.ywh.presenter.YwhMemberShowPresenter;
import com.yxld.yxchuangxin.ui.adapter.ywh.YwhMemberShowAdapter;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author William
 * @Package com.yxld.yxchuangxin.ui.activity.wuye
 * @Description: 业主大会人员公示
 * @date 2018/11/07 20:37:02
 */

public class YwhMemberShowActivity extends BaseActivity implements YwhMemberShowContract.View {

    @Inject
    YwhMemberShowPresenter mPresenter;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    private YwhMemberShowAdapter ywhMemberShowAdapter;
    private List<String> memberList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initView() {
        setContentView(R.layout.activity_ywh_membershow);
        ButterKnife.bind(this);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        memberList = new ArrayList<>();
        ywhMemberShowAdapter = new YwhMemberShowAdapter();

        recyclerView.setAdapter(ywhMemberShowAdapter);//绑定适配器
        ywhMemberShowAdapter.setNewData(memberList);

        // TODO: 2018/11/10 人员名单确认以后意见反馈隐藏
        if (true) {
            tvMenu.setVisibility(View.VISIBLE);
        } else {
            tvMenu.setVisibility(View.GONE);
        }
        tvMenu.setText("意见反馈");
        tvMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(FkyjActivity.class);
            }
        });


    }

    @Override
    protected void initData() {
        for (int i = 0; i < 10; i++) {
            memberList.add(i + "");
        }
        ywhMemberShowAdapter.setNewData(memberList);

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
        DaggerYwhMemberShowComponent
                .builder()
                .appComponent(((AppConfig) getApplication()).getApplicationComponent())
                .ywhMemberShowModule(new YwhMemberShowModule(this))
                .build()
                .inject(this);
    }

    @Override
    public void setPresenter(YwhMemberShowContract.YwhMemberShowContractPresenter presenter) {
        mPresenter = (YwhMemberShowPresenter) presenter;
    }

    @Override
    public void showProgressDialog() {
        progressDialog.show();
    }

    @Override
    public void closeProgressDialog() {
        progressDialog.hide();
    }

}