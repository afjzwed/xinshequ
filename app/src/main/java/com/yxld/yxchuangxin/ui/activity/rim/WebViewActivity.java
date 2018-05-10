package com.yxld.yxchuangxin.ui.activity.rim;

import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebBackForwardList;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import com.socks.library.KLog;
import com.yxld.yxchuangxin.R;
import com.yxld.yxchuangxin.application.AppConfig;
import com.yxld.yxchuangxin.base.BaseActivity;
import com.yxld.yxchuangxin.ui.activity.rim.component.DaggerWebViewComponent;
import com.yxld.yxchuangxin.ui.activity.rim.contract.WebViewContract;
import com.yxld.yxchuangxin.ui.activity.rim.module.WebViewModule;
import com.yxld.yxchuangxin.ui.activity.rim.presenter.WebViewPresenter;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author wwx
 * @Package com.yxld.yxchuangxin.ui.activity.rim
 * @Description: 网页 webview
 * @date 2017/06/17
 */

public class WebViewActivity extends BaseActivity implements WebViewContract.View {

    @Inject
    WebViewPresenter mPresenter;
    @BindView(R.id.progressBar)
    ProgressBar bar;
    @BindView(R.id.WwebView)
    WebView webView;

    private String name;
    private String address;

    private long[] mHits;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initView() {
        setContentView(R.layout.activity_webview);
        ButterKnife.bind(this);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    protected void initData() {
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        name = bundle.getString("name");
        address = bundle.getString("address");
        getSupportActionBar().setTitle(name);

        mHits = new long[2];
        initWebView();
    }

    @Override
    protected void setupActivityComponent() {
        DaggerWebViewComponent
                .builder()
                .appComponent(((AppConfig) getApplication()).getApplicationComponent())
                .webViewModule(new WebViewModule(this))
                .build()
                .inject(this);
    }

    @Override
    public void setPresenter(WebViewContract.WebViewContractPresenter presenter) {
        mPresenter = (WebViewPresenter) presenter;
    }

    @Override
    public void initWebView() {
        webView.getSettings().setBuiltInZoomControls(true);
        webView.getSettings().setDefaultFontSize(16);
        webView.getSettings().setDisplayZoomControls(false);
        webView.getSettings().setSupportZoom(true);
        webView.getSettings().setLoadWithOverviewMode(true);
        webView.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
        webView.getSettings().setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
        webView.getSettings().setDefaultTextEncodingName("UTF -8");
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setAllowContentAccess(true);
        webView.getSettings().setAppCacheEnabled(false);
        webView.getSettings().setUseWideViewPort(true);
        webView.getSettings().setLoadWithOverviewMode(true);
        webView.getSettings().setLayoutAlgorithm(
                WebSettings.LayoutAlgorithm.SINGLE_COLUMN);

        // WebView加载web资源
        webView.loadUrl(address);
        // 覆盖WebView默认使用第三方或系统默认浏览器打开网页的行为，使网页用WebView打开
        webView.setWebViewClient(new WebViewClient() {
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


        webView.getSettings().setCacheMode(
                WebSettings.LOAD_NO_CACHE);
        webView.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                if (newProgress == 100) {
                    // 网页加载完成
                    bar.setVisibility(View.GONE);
                } else {
                    // 加载中
                    if (View.GONE == bar.getVisibility()) {
                        bar.setVisibility(View.VISIBLE);
                    }
                    bar.setProgress(newProgress);
                }
                super.onProgressChanged(view, newProgress);
            }
        });
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (webView.canGoBack()) {
                System.arraycopy(mHits, 1, mHits, 0, mHits.length - 1);
                mHits[mHits.length - 1] = SystemClock.uptimeMillis();
                if (300 >= (mHits[mHits.length - 1] - mHits[0])) {
                    finish();
                }else {
                    KLog.i("WebView","goBack");
                    webView.goBack();// 返回上一页面
                }
                return true;
            } else {
                finish();// 退出程序
            }
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            if (webView != null && webView.canGoBack()) {
                System.arraycopy(mHits, 1, mHits, 0, mHits.length - 1);
                mHits[mHits.length - 1] = SystemClock.uptimeMillis();
                if (300 >= (mHits[mHits.length - 1] - mHits[0])) {
                    finish();
                }else {
                    KLog.i("WebView","goBack");
                    webView.goBack();// 返回上一页面
                }
                return true;
            } else {
                finish();// 退出程序
            }
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(webView!=null){
            webView.removeAllViews();
            webView.destroy();
            webView = null;
        }
    }
}