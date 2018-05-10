package com.yxld.yxchuangxin.ui.activity.wuye;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

import com.yxld.yxchuangxin.R;
import com.yxld.yxchuangxin.Utils.DensityUtil;
import com.yxld.yxchuangxin.application.AppConfig;
import com.yxld.yxchuangxin.base.BaseActivity;
import com.yxld.yxchuangxin.entity.CxwyMessage;
import com.yxld.yxchuangxin.ui.activity.wuye.component.DaggerMessageActivityDetailComponent;
import com.yxld.yxchuangxin.ui.activity.wuye.contract.MessageActivityDetailContract;
import com.yxld.yxchuangxin.ui.activity.wuye.module.MessageActivityDetailModule;
import com.yxld.yxchuangxin.ui.activity.wuye.presenter.MessageActivityDetailPresenter;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author hu
 * @Package com.yxld.yxchuangxin.ui.activity.wuye
 * @Description: $description
 * @date 2017/06/26 19:57:52
 */

public class MessageActivityDetailActivity extends BaseActivity implements
        MessageActivityDetailContract.View {

    @Inject
    MessageActivityDetailPresenter mPresenter;
    @BindView(R.id.tv_leixing)
    TextView tvLeixing;
    @BindView(R.id.tv_content)
    TextView tvContent;
    @BindView(R.id.tv_time)
    TextView tvTime;
    @BindView(R.id.tv_faqiren)
    TextView tvFaqiren;
    @BindView(R.id.web_view)
    WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    String test;

    @Override
    protected void initView() {
        setContentView(R.layout.activity_message_activity_detail);
        ButterKnife.bind(this);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    protected void initData() {
        if (getIntent().getStringExtra("flag").equals("activity")) {
            tvLeixing.setText("活 动");
        } else {
            tvLeixing.setText("通 知");
        }
        Bundle bundle = getIntent().getExtras();
        CxwyMessage.RowsBean bean = (CxwyMessage.RowsBean) bundle.getSerializable("entity");
//        tvContent.setText(bean.getTongzhiNeirong());
        Log.e("wh", "通知内容" + bean.getTongzhiNeirong());
        test = bean.getTongzhiNeirong();

     /*   Spanned spanned;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            Log.e("wh", "1");
            spanned = Html.fromHtml(test, Html.FROM_HTML_MODE_LEGACY, new Html.ImageGetter() {
                @Override
                public Drawable getDrawable(String source) {
                    InputStream is;
                    try {
                        Log.e("wh", "2" + source);
                        is = (InputStream) new URL(source).getContent();
                        Drawable d = Drawable.createFromStream(is, "src");
                        d.setBounds(0, 0, d.getIntrinsicWidth(),
                                d.getIntrinsicHeight());
                        is.close();
                        return d;
                    } catch (Exception e) {
                        Log.e("wh", "3");
                        return null;
                    }
                }
            }, null);
        } else {
            spanned = Html.fromHtml(test, new Html.ImageGetter() {
                @Override
                public Drawable getDrawable(String source) {
                    InputStream is;
                    try {
                        is = (InputStream) new URL(source).getContent();
                        Drawable d = Drawable.createFromStream(is, "src");
                        d.setBounds(0, 0, d.getIntrinsicWidth(),
                                d.getIntrinsicHeight());
                        is.close();
                        return d;
                    } catch (Exception e) {
                        return null;
                    }
                }
            }, null);
        }*/

//        tvContent.setText(spanned);
       /* Document doc= Jsoup.parse(test);
        Elements elements=doc.getElementsByTag("img");
        for (Element element : elements) {
            element.attr("width","100%").attr("height","auto");

        }*/


        WebSettings settings = webView.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        settings.setUseWideViewPort(true);//任意比例缩放 将图片调整到适合webview的大小
        settings.setLoadWithOverviewMode(true);// 缩放至屏幕的大小
        settings.setDomStorageEnabled(true);//开启DOM storage API功能
        settings.setJavaScriptCanOpenWindowsAutomatically(true);//支持通过JS打开新窗口
        settings.setBuiltInZoomControls(true);
        settings.setSupportZoom(true);
        settings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
        settings.setDefaultTextEncodingName("UTF -8");
        settings.setAllowContentAccess(true);
        settings.setAppCacheEnabled(false);
        int screenWidth = DensityUtil.getScreenWidth(this);//屏幕宽度
        int size = 42 * screenWidth / 1080;
        settings.setDefaultFontSize(size);//设置字体大小
        settings.setDisplayZoomControls(false);//隐藏原生的缩放控件

        webView.setWebViewClient(new WebViewClient());
        webView.setWebChromeClient(new WebChromeClient());
        if (!TextUtils.isEmpty(test)) {
            webView.loadDataWithBaseURL(null, test, "text/html", "utf-8", null);
        }
        tvTime.setText(bean.getTongzhiFabushijian());
        tvFaqiren.setText(bean.getTongzhiLuopan());
    }

   /* private class MyWebViewClient extends WebViewClient {

        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
            imgReset();//重置webview中img标签的图片大小
            // html加载完成之后，添加监听图片的点击js函数
//            addImageClickListner();
        }

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadDataWithBaseURL(null, test, "text/html", "utf-8", null);
            return true;
        }
    }

    *//**
     * 对图片进行重置大小，宽度就是手机屏幕宽度，高度根据宽度比便自动缩放
     **//*
    private void imgReset() {
        webView.loadUrl("javascript:(function(){" +
                "var objs = document.getElementsByTagName('img'); " +
                "for(var i=0;i<objs.length;i++)  " +
                "{"
                + "var img = objs[i];   " +
                "    img.style.maxWidth = '100%'; img.style.height = 'auto';  " +
                "}" +
                "})()");
    }*/

    @Override
    protected void setupActivityComponent() {
        DaggerMessageActivityDetailComponent
                .builder()
                .appComponent(((AppConfig) getApplication()).getApplicationComponent())
                .messageActivityDetailModule(new MessageActivityDetailModule(this))
                .build()
                .inject(this);
    }

    @Override
    public void setPresenter(MessageActivityDetailContract.MessageActivityDetailContractPresenter
                                     presenter) {
        mPresenter = (MessageActivityDetailPresenter) presenter;
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