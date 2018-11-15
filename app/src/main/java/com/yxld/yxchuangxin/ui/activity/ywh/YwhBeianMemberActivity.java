package com.yxld.yxchuangxin.ui.activity.ywh;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.yxld.yxchuangxin.R;
import com.yxld.yxchuangxin.application.AppConfig;
import com.yxld.yxchuangxin.base.BaseActivity;
import com.yxld.yxchuangxin.entity.YwhCurrentflow;
import com.yxld.yxchuangxin.ui.activity.ywh.component.DaggerYwhBeianMemberComponent;
import com.yxld.yxchuangxin.ui.activity.ywh.contract.YwhBeianMemberContract;
import com.yxld.yxchuangxin.ui.activity.ywh.module.YwhBeianMemberModule;
import com.yxld.yxchuangxin.ui.activity.ywh.presenter.YwhBeianMemberPresenter;
import com.yxld.yxchuangxin.ui.adapter.ywh.YwhBeianMemberAdapter;
import com.yxld.yxchuangxin.ui.adapter.ywh.YwhMemberShowAdapter;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author William
 * @Package com.yxld.yxchuangxin.ui.activity.ywh
 * @Description: $description
 * @date 2018/11/15 09:47:02
 */

public class YwhBeianMemberActivity extends BaseActivity implements YwhBeianMemberContract.View {

    @Inject
    YwhBeianMemberPresenter mPresenter;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    private List<YwhCurrentflow.DataBean.FlowBean.BeianPeoplesBean> listdata;//备案人员列表
    private YwhBeianMemberAdapter ywhMemberShowAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initView() {
        setContentView(R.layout.activity_ywh_beianmember);
        ButterKnife.bind(this);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        listdata = getIntent().getParcelableArrayListExtra("ywh_beian_list");

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        ywhMemberShowAdapter = new YwhBeianMemberAdapter();
        recyclerView.setAdapter(ywhMemberShowAdapter);//绑定适配器

        if (null != listdata && listdata.size() > 0) {
            ywhMemberShowAdapter.setNewData(listdata);
        }
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void setupActivityComponent() {
        DaggerYwhBeianMemberComponent
                .builder()
                .appComponent(((AppConfig) getApplication()).getApplicationComponent())
                .ywhBeianMemberModule(new YwhBeianMemberModule(this))
                .build()
                .inject(this);
    }

    @Override
    public void setPresenter(YwhBeianMemberContract.YwhBeianMemberContractPresenter presenter) {
        mPresenter = (YwhBeianMemberPresenter) presenter;
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