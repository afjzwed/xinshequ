package com.yxld.yxchuangxin.ui.activity.wuye;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.yxld.yxchuangxin.R;
import com.yxld.yxchuangxin.application.AppConfig;
import com.yxld.yxchuangxin.base.BaseActivity;
import com.yxld.yxchuangxin.contain.Contains;
import com.yxld.yxchuangxin.entity.CxwyComplain;
import com.yxld.yxchuangxin.ui.activity.wuye.component.DaggerComplainListComponent;
import com.yxld.yxchuangxin.ui.activity.wuye.contract.ComplainListContract;
import com.yxld.yxchuangxin.ui.activity.wuye.module.ComplainListModule;
import com.yxld.yxchuangxin.ui.activity.wuye.presenter.ComplainListPresenter;
import com.yxld.yxchuangxin.ui.adapter.wuye.ComplainListAdapter;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author hu
 * @Package com.yxld.yxchuangxin.ui.activity.wuye
 * @Description: $description
 * @date 2017/06/20 13:31:40
 */

public class ComplainListActivity extends BaseActivity implements ComplainListContract.View {

    @Inject
    ComplainListPresenter mPresenter;

    @Inject
    ComplainListAdapter adapter;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    private View notDataView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initView() {
        setContentView(R.layout.activity_complain_list);
        ButterKnife.bind(this);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        notDataView = getLayoutInflater().inflate(R.layout.layout_empty_data, (ViewGroup) recyclerView.getParent(), false);
        adapter.setEmptyView(notDataView);
        recyclerView.setAdapter(adapter);
    }

    @Override
    protected void initData() {
        //yezhuid
        Map<String, String> map = new HashMap<>();
        map.put("yezhuid", Contains.user.getYezhuId() + "");
        mPresenter.getComplainList(map);
    }

    @Override
    protected void setupActivityComponent() {
        DaggerComplainListComponent
                .builder()
                .appComponent(((AppConfig) getApplication()).getApplicationComponent())
                .complainListModule(new ComplainListModule(this))
                .build()
                .inject(this);
    }

    @Override
    public void setPresenter(ComplainListContract.ComplainListContractPresenter presenter) {
        mPresenter = (ComplainListPresenter) presenter;
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
    public void setComplainList(CxwyComplain list) {
        adapter.setNewData(list.getList());
    }

    @Override
    protected void onDestroy() {
        mPresenter.unsubscribe();
        super.onDestroy();
    }
}