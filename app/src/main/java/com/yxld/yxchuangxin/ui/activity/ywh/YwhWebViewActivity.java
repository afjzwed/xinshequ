package com.yxld.yxchuangxin.ui.activity.ywh;

import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;

import com.yxld.yxchuangxin.R;
import com.yxld.yxchuangxin.application.AppConfig;
import com.yxld.yxchuangxin.base.BaseActivity;
import com.yxld.yxchuangxin.ui.activity.ywh.component.DaggerYwhWebViewComponent;
import com.yxld.yxchuangxin.ui.activity.ywh.contract.YwhWebViewContract;
import com.yxld.yxchuangxin.ui.activity.ywh.module.YwhWebViewModule;
import com.yxld.yxchuangxin.ui.activity.ywh.presenter.YwhWebViewPresenter;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author William
 * @Package com.yxld.yxchuangxin.ui.activity.ywh
 * @Description: $description
 * @date 2018/11/14 14:14:11
 */

public class YwhWebViewActivity extends BaseActivity implements YwhWebViewContract.View {

    @Inject
    YwhWebViewPresenter mPresenter;
    @BindView(R.id.webview)
    WebView webview;

    private String address;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initView() {
//        needFront = true;
        setContentView(R.layout.activity_ywh_webview);
        ButterKnife.bind(this);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        address = bundle.getString("address");


        String url = "http://p9zwbgynz.bkt.clouddn.com/2018_PDF.pdf";

        WebSettings webSettings = webview.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setAllowFileAccess(true);
        webSettings.setAllowFileAccessFromFileURLs(true);
        webSettings.setAllowUniversalAccessFromFileURLs(true);



        webview.loadUrl("http://mozilla.github.io/pdf.js/web/viewer.html?file=" + address);


    }

    @Override
    protected void initData() {

    }

    @Override
    protected void setupActivityComponent() {
        DaggerYwhWebViewComponent
                .builder()
                .appComponent(((AppConfig) getApplication()).getApplicationComponent())
                .ywhWebViewModule(new YwhWebViewModule(this))
                .build()
                .inject(this);
    }

    @Override
    public void setPresenter(YwhWebViewContract.YwhWebViewContractPresenter presenter) {
        mPresenter = (YwhWebViewPresenter) presenter;
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