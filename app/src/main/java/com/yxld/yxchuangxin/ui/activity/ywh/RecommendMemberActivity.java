package com.yxld.yxchuangxin.ui.activity.ywh;

import android.os.Bundle;

import com.yxld.yxchuangxin.R;
import com.yxld.yxchuangxin.application.AppConfig;
import com.yxld.yxchuangxin.base.BaseActivity;

import com.yxld.yxchuangxin.ui.activity.ywh.component.DaggerRecommendMemberComponent;
import com.yxld.yxchuangxin.ui.activity.ywh.contract.RecommendMemberContract;
import com.yxld.yxchuangxin.ui.activity.ywh.module.RecommendMemberModule;
import com.yxld.yxchuangxin.ui.activity.ywh.presenter.RecommendMemberPresenter;

import javax.inject.Inject;

import butterknife.ButterKnife;

/**
 * @author William
 * @Package com.yxld.yxchuangxin.ui.activity.wuye
 * @Description: 推荐筹备组成员
 * @date 2018/11/07 19:36:36
 */

public class RecommendMemberActivity extends BaseActivity implements RecommendMemberContract.View {

    @Inject
    RecommendMemberPresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initView() {
        setContentView(R.layout.activity_recommend_member);
        ButterKnife.bind(this);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void setupActivityComponent() {
       DaggerRecommendMemberComponent
               .builder()
               .appComponent(((AppConfig) getApplication()).getApplicationComponent())
               .recommendMemberModule(new RecommendMemberModule(this))
               .build()
               .inject(this);
    }
    @Override
    public void setPresenter(RecommendMemberContract.RecommendMemberContractPresenter presenter) {
        mPresenter = (RecommendMemberPresenter) presenter;
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
    protected void onDestroy() {
        mPresenter.unsubscribe();
        super.onDestroy();
    }

}