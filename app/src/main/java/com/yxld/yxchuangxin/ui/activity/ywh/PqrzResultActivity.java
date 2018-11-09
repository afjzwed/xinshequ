package com.yxld.yxchuangxin.ui.activity.ywh;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.yxld.yxchuangxin.R;
import com.yxld.yxchuangxin.application.AppConfig;
import com.yxld.yxchuangxin.base.BaseActivity;
import com.yxld.yxchuangxin.ui.activity.ywh.component.DaggerPqrzResultComponent;
import com.yxld.yxchuangxin.ui.activity.ywh.contract.PqrzResultContract;
import com.yxld.yxchuangxin.ui.activity.ywh.module.PqrzResultModule;
import com.yxld.yxchuangxin.ui.activity.ywh.presenter.PqrzResultPresenter;
import com.yxld.yxchuangxin.ui.adapter.ywh.ImgAdapter;
import com.yxld.yxchuangxin.view.GridLayoutSpace;

import java.util.Arrays;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author xlei
 * @Package com.yxld.yxchuangxin.ui.activity.ywh
 * @Description: $description
 * @date 2018/11/09 14:52:55
 */

public class PqrzResultActivity extends BaseActivity implements PqrzResultContract.View {

    @Inject
    PqrzResultPresenter mPresenter;
    @BindView(R.id.rv) RecyclerView rv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initView() {
        needFront = true;
        setContentView(R.layout.activity_pqrz_result);
        ButterKnife.bind(this);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        initRv();
    }

    private void initRv() {
        rv.setLayoutManager(new GridLayoutManager(this, 2));
        rv.addItemDecoration(new GridLayoutSpace(2,20,true));
        rv.setAdapter(new ImgAdapter(Arrays.asList("","","","")));
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void setupActivityComponent() {
        DaggerPqrzResultComponent
                .builder()
                .appComponent(((AppConfig) getApplication()).getApplicationComponent())
                .pqrzResultModule(new PqrzResultModule(this))
                .build()
                .inject(this);
    }

    @Override
    public void setPresenter(PqrzResultContract.PqrzResultContractPresenter presenter) {
        mPresenter = (PqrzResultPresenter) presenter;
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