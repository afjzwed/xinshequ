package com.yxld.yxchuangxin.ui.activity.ywh;

import android.os.Bundle;

import com.yxld.yxchuangxin.R;
import com.yxld.yxchuangxin.application.AppConfig;
import com.yxld.yxchuangxin.base.BaseActivity;
import com.yxld.yxchuangxin.ui.activity.ywh.component.DaggerYwhWebViewComponent;
import com.yxld.yxchuangxin.ui.activity.ywh.contract.YwhWebViewContract;
import com.yxld.yxchuangxin.ui.activity.ywh.module.YwhWebViewModule;
import com.yxld.yxchuangxin.ui.activity.ywh.presenter.YwhWebViewPresenter;

import javax.inject.Inject;

import butterknife.ButterKnife;
import es.voghdev.pdfviewpager.library.RemotePDFViewPager;
import es.voghdev.pdfviewpager.library.adapter.PDFPagerAdapter;
import es.voghdev.pdfviewpager.library.remote.DownloadFile;

/**
 * @author William
 * @Package com.yxld.yxchuangxin.ui.activity.ywh
 * @Description: $description
 * @date 2018/11/14 14:14:11
 */

public class YwhWebViewActivity extends BaseActivity implements YwhWebViewContract.View, DownloadFile.Listener {

    @Inject
    YwhWebViewPresenter mPresenter;
//    @BindView(R.id.pdfViewPager)
//    PDFViewPager pdfViewPager;

    private PDFPagerAdapter adapter;
    private  RemotePDFViewPager remotePDFViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initView() {
        setContentView(R.layout.activity_ywh_webview);
        ButterKnife.bind(this);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        String url = "http://www.cals.uidaho.edu/edComm/curricula/CustRel_curriculum/content/sample.pdf";

        remotePDFViewPager = new RemotePDFViewPager(this, url, this);
        remotePDFViewPager.setId(R.id.pdfViewPager);

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

        ((PDFPagerAdapter) remotePDFViewPager.getAdapter()).close();
    }

    @Override
    public void onSuccess(String url, String destinationPath) {
        adapter = new PDFPagerAdapter(this, System.currentTimeMillis() + "AdobeXMLFormsSamples.pdf");
        remotePDFViewPager.setAdapter(adapter);
//        setContentView(pdfViewPager);
    }

    @Override
    public void onFailure(Exception e) {

    }

    @Override
    public void onProgressUpdate(int progress, int total) {

    }
}