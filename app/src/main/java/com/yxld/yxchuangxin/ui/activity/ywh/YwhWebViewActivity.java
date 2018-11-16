package com.yxld.yxchuangxin.ui.activity.ywh;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.webkit.DownloadListener;
import android.webkit.WebSettings;
import android.webkit.WebView;


import com.yxld.yxchuangxin.R;
import com.yxld.yxchuangxin.application.AppConfig;
import com.yxld.yxchuangxin.base.BaseActivity;
import com.yxld.yxchuangxin.ui.activity.ywh.component.DaggerYwhWebViewComponent;
import com.yxld.yxchuangxin.ui.activity.ywh.contract.YwhWebViewContract;
import com.yxld.yxchuangxin.ui.activity.ywh.module.YwhWebViewModule;
import com.yxld.yxchuangxin.ui.activity.ywh.presenter.YwhWebViewPresenter;

import java.io.InputStream;

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
//    @BindView(R.id.pdfView)
//    PDFView pdfView;

    private String address;
    private String url;

    private InputStream is;

    private Thread thread ;

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


//        address = "http://p9zwbgynz.bkt.clouddn.com/2018_PDF.pdf";
//        url = "http://www.cals.uidaho.edu/edComm/curricula/CustRel_curriculum/content/sample.pdf";

        WebSettings webSettings = webview.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setAllowFileAccess(true);
        webSettings.setAllowFileAccessFromFileURLs(true);
        webSettings.setAllowUniversalAccessFromFileURLs(true);

//        webview.loadUrl("http://mozilla.github.io/pdf.js/web/viewer.html?file=" + address);
//        webview.loadUrl("http://mozilla.github.io/pdf.js/web/viewer.html?file=" + url);
//        webview.loadUrl(url);

        webview.setDownloadListener(new MyWebViewDownLoadListener());
        webview.loadUrl(address);

//        Uri uri = Uri.parse(address);
//        pdfView.fromUri(uri).load();

//        thread =  new Thread(){
//            @Override
//            public void run() {
//                super.run();
//                HttpClient httpClient = new DefaultHttpClient();
//                HttpGet get = new HttpGet(address);
//                HttpResponse response;
//                try {
//                    response = httpClient.execute(get);
//                    HttpEntity entity = response.getEntity();
//                    long length = entity.getContentLength();
////                    InputStream is = entity.getContent();
//                     is = entity.getContent();
//
//                    if (is != null) {
//                        loadPdf(is);
//                    }
//
////                    mDialogFragment.dismissAllowingStateLoss();
//                } catch (IOException e) {
////                    mDialogFragment.dismissAllowingStateLoss();
//                    e.printStackTrace();
//                }
//            }
//        };
//        thread.start();
    }

    private void loadPdf(InputStream inputStream) {

//        pdfView.fromStream(inputStream)
//                .defaultPage(0)
//                .swipeHorizontal(false)
//                .enableSwipe(true)
//                .enableAnnotationRendering(false)
//                .password(null)
//                .scrollHandle(null)
//                .enableAntialiasing(true)
//                .load();

    }

    private class MyWebViewDownLoadListener implements DownloadListener {
        @Override
        public void onDownloadStart(String url, String userAgent, String contentDisposition, String mimetype, long
                contentLength) {
            Log.e("wh", "点击");
            Uri uri = Uri.parse(address);
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            startActivity(intent);
        }
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
//        try {
//            is.close();
//            thread.interrupt();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        pdfView.recycle();
    }
}