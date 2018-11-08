package com.yxld.yxchuangxin.ui.activity.ywh;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.yxld.yxchuangxin.R;
import com.yxld.yxchuangxin.application.AppConfig;
import com.yxld.yxchuangxin.base.BaseActivity;
import com.yxld.yxchuangxin.ui.activity.ywh.component.DaggerCheckNoticeComponent;
import com.yxld.yxchuangxin.ui.activity.ywh.contract.CheckNoticeContract;
import com.yxld.yxchuangxin.ui.activity.ywh.module.CheckNoticeModule;
import com.yxld.yxchuangxin.ui.activity.ywh.presenter.CheckNoticePresenter;

import javax.inject.Inject;

import butterknife.ButterKnife;

/**
 * @author William
 * @Package com.yxld.yxchuangxin.ui.activity.ywh
 * @Description: 查看通知
 * @date 2018/11/08 17:11:57
 */

public class CheckNoticeActivity extends BaseActivity implements CheckNoticeContract.View {

    @Inject
    CheckNoticePresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initView() {
        setContentView(R.layout.activity_ywh_checknotice);
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

}