package com.yxld.yxchuangxin.ui.activity.ywh;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.yxld.yxchuangxin.R;
import com.yxld.yxchuangxin.application.AppConfig;
import com.yxld.yxchuangxin.base.BaseActivity;

import com.yxld.yxchuangxin.ui.activity.ywh.component.DaggerResultShowComponent;
import com.yxld.yxchuangxin.ui.activity.ywh.contract.ResultShowContract;
import com.yxld.yxchuangxin.ui.activity.ywh.module.ResultShowModule;
import com.yxld.yxchuangxin.ui.activity.ywh.presenter.ResultShowPresenter;

import javax.inject.Inject;

import butterknife.ButterKnife;

/**
 * @author William
 * @Package com.yxld.yxchuangxin.ui.activity.wuye
 * @Description: 业主大会结果公示
 * @date 2018/11/07 20:09:47
 */

public class ResultShowActivity extends BaseActivity implements ResultShowContract.View {

    @Inject
    ResultShowPresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initView() {
        setContentView(R.layout.activity_ywh_result_show);
        ButterKnife.bind(this);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    protected void initData() {

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_ywh_advise, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);
        switch (item.getItemId()) {
            case R.id.ywh_advise:
                Toast.makeText(this, "意见反馈", Toast.LENGTH_SHORT).show();
                break;
            case android.R.id.home:
                finish();
                System.gc();
                break;
        }
        return true;
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

}