package com;

import android.os.Build;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.socks.library.KLog;
import com.yxld.yxchuangxin.R;
import com.yxld.yxchuangxin.base.BaseActivity;
import com.yxld.yxchuangxin.view.GoodDetailWebView;
import com.yxld.yxchuangxin.view.SlideDetailsLayout;
import com.zhy.autolayout.AutoLinearLayout;
import com.zhy.autolayout.AutoRelativeLayout;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by William on 2017/12/1.
 */

public class AtestActivity extends BaseActivity {


    @BindView(R.id.btn_add_to_cart)
    Button btnAddToCart;
    @BindView(R.id.btn_buy_now)
    Button btnBuyNow;
    @BindView(R.id.ll_container)
    AutoLinearLayout llContainer;
    @BindView(R.id.indexGoods)
    ViewPager indexGoods;
    @BindView(R.id.indicator)
    View indicator;
    @BindView(R.id.tv_good_name)
    TextView tvGoodName;
    @BindView(R.id.tv_good_price)
    TextView tvGoodPrice;
    @BindView(R.id.tv_xiaoliang)
    TextView tvXiaoliang;
    @BindView(R.id.tv_goods_remain_num)
    TextView tvGoodsRemainNum;
    @BindView(R.id.tv_guige)
    TextView tvGuige;
    @BindView(R.id.tv_peisongfangshi)
    TextView tvPeisongfangshi;
    @BindView(R.id.tv_send_address)
    TextView tvSendAddress;
    @BindView(R.id.iv_jian)
    AutoRelativeLayout ivJian;
    @BindView(R.id.tv_count)
    EditText tvCount;
    @BindView(R.id.iv_jia)
    AutoRelativeLayout ivJia;
    @BindView(R.id.tv_comment_count)
    TextView tvCommentCount;
    @BindView(R.id.rl_comment_root)
    AutoRelativeLayout rlCommentRoot;
    @BindView(R.id.recycerView)
    RecyclerView recycerView;
    @BindView(R.id.tv_all_comment)
    TextView tvAllComment;
    @BindView(R.id.slidedetails_behind)
    GoodDetailWebView slidedetailsBehind;
    @BindView(R.id.slidedetailslayout)
    SlideDetailsLayout slidedetailslayout;
    @BindView(R.id.swip_refresh)
    SwipeRefreshLayout swipRefresh;
    @BindView(R.id.tv_mall_goods_shop_cart_num)
    TextView tvMallGoodsShopCartNum;
    @BindView(R.id.layout_goods_list_shop_cart)
    AutoRelativeLayout layoutGoodsListShopCart;
    @BindView(R.id.iv_mall_goods_list_back)
    AutoRelativeLayout ivMallGoodsListBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        needFront = true;
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        setContentView(R.layout.activity_good_detail_test);
        ButterKnife.bind(this);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setVisibility(View.GONE);
//        swipRefresh.setRefreshing(true);
      /*  if (null == swipRefresh) {
            Log.e("wh", "fsldjdflkj");
        } else {
            swipRefresh.setRefreshing(false);
        }
*/



        slidedetailslayout.setOnSlideDetailsListener(new SlideDetailsLayout
                .OnSlideDetailsListener() {
            @Override
            public void onStatucChanged(SlideDetailsLayout.Status status) {
                if (status == SlideDetailsLayout.Status.OPEN) {
                    KLog.i("onStatucChanged11111" + SlideDetailsLayout.Status.OPEN);
                    final WebSettings settings = slidedetailsBehind.getSettings();
                    settings.setJavaScriptEnabled(true);
                    settings.setSupportZoom(true);
                    settings.setBuiltInZoomControls(true);
                    settings.setUseWideViewPort(true);
                    settings.setDomStorageEnabled(true);
                    slidedetailsBehind.setWebViewClient(new WebViewClient() {

                        @Override
                        public boolean shouldOverrideUrlLoading(WebView view, String url) {
                            view.loadUrl(url);
                            return true;
                        }
                    });
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.ECLAIR_MR1) {
                        new Object() {
                            public void setLoadWithOverviewMode(boolean overview) {
                                settings.setLoadWithOverviewMode(overview);
                            }
                        }.setLoadWithOverviewMode(true);
                    }

                    settings.setCacheMode(WebSettings.LOAD_DEFAULT);

                    getWindow().getDecorView().post(new Runnable() {
                        @Override
                        public void run() {
                            //  slidedetailsBehind.loadDataWithBaseURL("about:blank", ""+mProduct
                            // .getShangpinBody(), "text/html", "utf-8", null);
//                            slidedetailsBehind.loadUrl(API.URL_GOODS_DESTAIL_WEB + mProductId);
                            // TODO: 2017/11/8
//                            slidedetailsBehind.loadDataWithBaseURL("about:blank", "" + "",
// "text/html", "utf-8", null);
//                            String s = API.PIC + mImgUrls.get(mImgUrls.size() - 1);
                          /*  if (!TextUtils.isEmpty(detailUrl)) {
                                slidedetailsBehind.loadUrl(API.PIC + detailUrl);
                            }*/
                        }
                    });
                    Log.e("wh", "4");
                    swipRefresh.setRefreshing(false);
                    swipRefresh.setEnabled(false);
                } else {
                    Log.e("wh", "5");
                    KLog.i("onStatucChanged111111111");
                    slidedetailslayout.setWebViewToTop(true);
                    swipRefresh.setEnabled(true);
                    swipRefresh.setRefreshing(true);
                }
            }
        });
    }

    @Override
    protected void setupActivityComponent() {

    }
}
