package com.yxld.yxchuangxin.ui.activity.wuye;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.yxld.yxchuangxin.R;
import com.yxld.yxchuangxin.Utils.StringUitl;
import com.yxld.yxchuangxin.application.AppConfig;
import com.yxld.yxchuangxin.base.BaseActivity;
import com.yxld.yxchuangxin.data.api.API;
import com.yxld.yxchuangxin.entity.LocalAd;
import com.yxld.yxchuangxin.ui.activity.wuye.component.DaggerAboutOurComponent;
import com.yxld.yxchuangxin.ui.activity.wuye.contract.AboutOurContract;
import com.yxld.yxchuangxin.ui.activity.wuye.module.AboutOurModule;
import com.yxld.yxchuangxin.ui.activity.wuye.presenter.AboutOurPresenter;
import com.zhy.autolayout.AutoRelativeLayout;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author hu
 * @Package com.yxld.yxchuangxin.ui.activity.wuye
 * @Description: $description
 * @date 2017/06/23 09:20:36
 */

public class AboutOurActivity extends BaseActivity implements AboutOurContract.View {

    @Inject
    AboutOurPresenter mPresenter;
    @BindView(R.id.textView)
    TextView textView;
    @BindView(R.id.textView2)
    TextView textView2;
    @BindView(R.id.activity_main)
    AutoRelativeLayout activityMain;
    @BindView(R.id.img_logo)
    ImageView imgLogo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        needFront = true;
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initView() {
        setContentView(R.layout.activity_about_our);
        ButterKnife.bind(this);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    protected void initData() {
        mPresenter.getData();
        textView2.setText("Copyright  ©2016-"+ StringUitl.getCurYear()+"   湖南创欣物联科技有限公司");
    }

    @Override
    protected void setupActivityComponent() {
        DaggerAboutOurComponent
                .builder()
                .appComponent(((AppConfig) getApplication()).getApplicationComponent())
                .aboutOurModule(new AboutOurModule(this))
                .build()
                .inject(this);
    }

    @Override
    public void setPresenter(AboutOurContract.AboutOurContractPresenter presenter) {
        mPresenter = (AboutOurPresenter) presenter;
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
    public void setData(LocalAd localAd) {
        if (localAd.status==1){
            Glide.with(this).load(API.PIC+localAd.getRows().getGongsilogo()).into(imgLogo);
            textView.setText(localAd.getRows().getGongsijieshao());
        }
    }

    @Override
    protected void onDestroy() {
        mPresenter.unsubscribe();
        super.onDestroy();
    }

}