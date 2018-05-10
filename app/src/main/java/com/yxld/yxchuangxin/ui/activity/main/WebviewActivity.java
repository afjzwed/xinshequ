package com.yxld.yxchuangxin.ui.activity.main;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import com.yxld.yxchuangxin.R;
import com.yxld.yxchuangxin.application.AppConfig;
import com.yxld.yxchuangxin.base.BaseActivity;
import com.yxld.yxchuangxin.ui.activity.main.component.DaggerWebviewComponent;
import com.yxld.yxchuangxin.ui.activity.main.contract.WebviewContract;
import com.yxld.yxchuangxin.ui.activity.main.module.WebviewModule;
import com.yxld.yxchuangxin.ui.activity.main.presenter.WebviewPresenter;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author hu
 * @Package com.yxld.yxchuangxin.ui.activity.main
 * @Description: $description
 * @date 2017/06/23 09:59:44
 */

public class WebviewActivity extends BaseActivity implements WebviewContract.View {

    @Inject
    WebviewPresenter mPresenter;
    @BindView(R.id.progressBar)
    ProgressBar progressBar;
    @BindView(R.id.WwebView)
    WebView WwebView;
    private String name;
    private String address;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initView() {
        setContentView(R.layout.activity_webview);
        ButterKnife.bind(this);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        name = bundle.getString("name");
        toolbar.setTitle(name);
        address = bundle.getString("address");
    }

    @Override
    protected void initData() {
        init();
        WwebView.getSettings().setCacheMode(
                WebSettings.LOAD_NO_CACHE);
        WwebView.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                if (newProgress == 100) {
                    // 网页加载完成
                    progressBar.setVisibility(View.GONE);
                } else {
                    // 加载中
                    if (View.GONE == progressBar.getVisibility()) {
                        progressBar.setVisibility(View.VISIBLE);
                    }
                    progressBar.setProgress(newProgress);
                }
                super.onProgressChanged(view, newProgress);
            }
        });
    }

    private void init() {
        WwebView.getSettings().setBuiltInZoomControls(true);
        WwebView.getSettings().setDefaultFontSize(16);
        WwebView.getSettings().setDisplayZoomControls(false);
        WwebView.getSettings().setSupportZoom(true);
        WwebView.getSettings().setLoadWithOverviewMode(true);
        WwebView.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
        WwebView.getSettings().setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
        WwebView.getSettings().setDefaultTextEncodingName("UTF -8");
        WwebView.getSettings().setJavaScriptEnabled(true);
        WwebView.getSettings().setAllowContentAccess(true);
        WwebView.getSettings().setAppCacheEnabled(false);
        WwebView.getSettings().setUseWideViewPort(true);
        WwebView.getSettings().setLoadWithOverviewMode(true);
        WwebView.getSettings().setLayoutAlgorithm(
                WebSettings.LayoutAlgorithm.SINGLE_COLUMN);

        // WebView加载web资源
        WwebView.loadUrl(address);
//        WwebView.addJavascriptInterface(new PayJavaScriptInterface(), "js");
        WwebView.loadUrl("javascript:callFromJava('1')");
        // 覆盖WebView默认使用第三方或系统默认浏览器打开网页的行为，使网页用WebView打开
        WwebView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                // 返回值是true的时候控制去WebView打开，为false调用系统浏览器或第三方浏览器
                view.loadUrl(url);

                return true;
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
            }
        });
    }

    @Override
    protected void setupActivityComponent() {
        DaggerWebviewComponent
                .builder()
                .appComponent(((AppConfig) getApplication()).getApplicationComponent())
                .webviewModule(new WebviewModule(this))
                .build()
                .inject(this);
    }

    @Override
    public void setPresenter(WebviewContract.WebviewContractPresenter presenter) {
        mPresenter = (WebviewPresenter) presenter;
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
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (WwebView.canGoBack()) {
                WwebView.goBack();// 返回上一页面
                return true;
            } else {
                finish();// 退出程序
            }
        }
        return super.onKeyDown(keyCode, event);
    }

}