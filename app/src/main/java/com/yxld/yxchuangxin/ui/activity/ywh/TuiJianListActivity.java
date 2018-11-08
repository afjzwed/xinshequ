package com.yxld.yxchuangxin.ui.activity.ywh;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.yxld.yxchuangxin.R;
import com.yxld.yxchuangxin.application.AppConfig;
import com.yxld.yxchuangxin.base.BaseActivity;
import com.yxld.yxchuangxin.ui.activity.ywh.component.DaggerTuiJianListComponent;
import com.yxld.yxchuangxin.ui.activity.ywh.contract.TuiJianListContract;
import com.yxld.yxchuangxin.ui.activity.ywh.module.TuiJianListModule;
import com.yxld.yxchuangxin.ui.activity.ywh.presenter.TuiJianListPresenter;
import com.yxld.yxchuangxin.ui.adapter.goods.TuiJianAdapter;
import com.yxld.yxchuangxin.ui.adapter.ywh.YwhTuiJianAdapter;
import com.yxld.yxchuangxin.view.YwhTjDialog;

import java.util.Arrays;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author xlei
 * @Package com.yxld.yxchuangxin.ui.activity.ywh
 * @Description: $description
 * @date 2018/11/08 10:54:14
 */

public class TuiJianListActivity extends BaseActivity implements TuiJianListContract.View {

    @Inject
    TuiJianListPresenter mPresenter;
    @BindView(R.id.rv) RecyclerView rv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initView() {
        setContentView(R.layout.activity_tuijian_list);
        ButterKnife.bind(this);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setTitle("筹备组人员推荐");
        toolbar.setBackgroundColor(getResources().getColor(R.color.color_2d97ff));
        initRv();
        testDialog();
    }

    private void testDialog() {
        YwhTjDialog dialog = new YwhTjDialog(this);
        dialog.show();

    }

    private void initRv() {
        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.setAdapter(new YwhTuiJianAdapter(Arrays.asList("小明","小号","橡胶管")));
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void setupActivityComponent() {
        DaggerTuiJianListComponent
                .builder()
                .appComponent(((AppConfig) getApplication()).getApplicationComponent())
                .tuiJianListModule(new TuiJianListModule(this))
                .build()
                .inject(this);
    }

    @Override
    public void setPresenter(TuiJianListContract.TuiJianListContractPresenter presenter) {
        mPresenter = (TuiJianListPresenter) presenter;
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