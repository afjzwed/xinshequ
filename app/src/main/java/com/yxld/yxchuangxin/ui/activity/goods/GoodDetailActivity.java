package com.yxld.yxchuangxin.ui.activity.goods;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.TextView;

import com.socks.library.KLog;
import com.viewpagerindicator.CirclePageIndicator;
import com.yxld.yxchuangxin.R;
import com.yxld.yxchuangxin.Utils.CxUtil;
import com.yxld.yxchuangxin.Utils.StringUitl;
import com.yxld.yxchuangxin.Utils.ToastUtil;
import com.yxld.yxchuangxin.Utils.UIUtils;
import com.yxld.yxchuangxin.application.AppConfig;
import com.yxld.yxchuangxin.base.BaseActivity;
import com.yxld.yxchuangxin.base.BaseEntity;
import com.yxld.yxchuangxin.contain.Contains;
import com.yxld.yxchuangxin.data.api.API;
import com.yxld.yxchuangxin.entity.CxwyMallCart;
import com.yxld.yxchuangxin.entity.MyAllComment;
import com.yxld.yxchuangxin.entity.SureOrderEntity;
import com.yxld.yxchuangxin.entity.goods.MallNewProduct;
import com.yxld.yxchuangxin.entity.goods.MallNewProduct1;
import com.yxld.yxchuangxin.entity.goods.ShopCart;
import com.yxld.yxchuangxin.ui.activity.goods.component.DaggerGoodDetailComponent;
import com.yxld.yxchuangxin.ui.activity.goods.contract.GoodDetailContract;
import com.yxld.yxchuangxin.ui.activity.goods.module.GoodDetailModule;
import com.yxld.yxchuangxin.ui.activity.goods.presenter.GoodDetailPresenter;
import com.yxld.yxchuangxin.ui.adapter.goods.GoodsCommentAdapter;
import com.yxld.yxchuangxin.ui.adapter.goods.MyGoodsDetailAdapter;
import com.yxld.yxchuangxin.view.GoodDetailWebView;
import com.yxld.yxchuangxin.view.ListenedScrollView;
import com.yxld.yxchuangxin.view.SlideDetailsLayout;
import com.yxld.yxchuangxin.view.VpSwipeRefreshLayout;
import com.zhy.autolayout.AutoRelativeLayout;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.yxld.yxchuangxin.ui.activity.goods.ConfirmOrderActivity.ENTER_TYPE;

/**
 * @author hu
 * @Package com.yxld.yxchuangxin.ui.activity.goods
 * @Description: $description  商品详情界面
 * @date 2017/06/16
 */

public class GoodDetailActivity extends BaseActivity implements GoodDetailContract.View,
        SwipeRefreshLayout.OnRefreshListener {
    public static final String KEY_PRODUCT_ID = "key_product_id";
    private static final String INSTANCE_PRODUCT_ID = "instance_product_id";

    @Inject
    GoodDetailPresenter mPresenter;
    @BindView(R.id.indexGoods)
    ViewPager indexGoods;
    @BindView(R.id.slidedetails_behind)
    GoodDetailWebView slidedetailsBehind;
    @BindView(R.id.indicator)
    CirclePageIndicator indicator;
    @BindView(R.id.tv_good_name)
    TextView tvGoodName;
    @BindView(R.id.tv_good_price)
    TextView tvGoodPrice;
    @BindView(R.id.slidedetailslayout)
    SlideDetailsLayout slidedetailslayout;
    @BindView(R.id.btn_add_to_cart)
    Button btnAddToCart;
    @BindView(R.id.btn_buy_now)
    Button btnBuyNow;
    @BindView(R.id.tv_mall_goods_shop_cart_num)
    TextView tvMallGoodsShopCartNum;
    @BindView(R.id.layout_goods_list_shop_cart)
    AutoRelativeLayout layoutGoodsListShopCart;
    @BindView(R.id.tv_goods_remain_num)
    TextView tvGoodsRemainNum;
    @BindView(R.id.iv_jian)
    AutoRelativeLayout ivJian;
    @BindView(R.id.tv_count)
    TextView tvCount;
    @BindView(R.id.iv_jia)
    AutoRelativeLayout ivJia;
    @BindView(R.id.tv_comment_count)
    TextView tvCommentCount;
    @BindView(R.id.tv_all_comment)
    TextView tvAllComment;
    @BindView(R.id.tv_send_address)
    TextView tvSendAddress;
    @BindView(R.id.recycerView)
    RecyclerView recycerView;
    @BindView(R.id.rl_comment_root)
    AutoRelativeLayout rlCommentRoot;
    @BindView(R.id.scroll_view)
    ListenedScrollView scrollView;
    @BindView(R.id.tv_xiaoliang)
    TextView mTvXiaoliang;
    @BindView(R.id.tv_guige)
    TextView mTvGuige;
    @BindView(R.id.tv_peisongfangshi)
    TextView mTvPeisongfangshi;
    @BindView(R.id.swip_refresh)
    VpSwipeRefreshLayout mSwipRefresh;

    /**
     * 商品id
     */
    private String mProductId;

    /**
     * 商品实体类
     */
    private MallNewProduct mProduct;

    private List<String> mTempCartContanier;

    private List<String> mImgUrls;//轮播图数据集合

    private String detailUrl;//商品图文详情url


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        needFront = true;
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initView() {
        setContentView(R.layout.activity_good_detail);
        ButterKnife.bind(this);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setVisibility(View.GONE);
        toolbar.setBackgroundColor(getResources().getColor(R.color.transparent));
        UIUtils.configSwipeRefreshLayoutColors(mSwipRefresh);
        mSwipRefresh.setOnRefreshListener(this);
        slidedetailsBehind.setOnScrollChangeListener(new GoodDetailWebView.OnScrollChangeListener
                () {
            @Override
            public void onPageEnd(int l, int t, int oldl, int oldt) {
                slidedetailslayout.setWebViewToTop(false);
                KLog.i("onPageEnd111");
            }

            @Override
            public void onPageTop(int l, int t, int oldl, int oldt) {
                slidedetailslayout.setWebViewToTop(true);
                KLog.i("onPageTop1111");
            }

            @Override
            public void onScrollChanged(int l, int t, int oldl, int oldt) {
                KLog.i("onScrollChanged111");
                slidedetailslayout.setWebViewToTop(false);
                KLog.i("图片的高度为=" + indexGoods.getHeight());
                double rate = 0;
                if (t > indexGoods.getHeight() - toolbar.getHeight()) {
                    KLog.i(t);
                    rate = ((t - (indexGoods.getHeight() - toolbar.getHeight())) / (double)
                            toolbar.getHeight());
                }
                if (rate >= 1) {
                    rate = 1;
                }
                KLog.i("rate==" + rate);
                toolbar.setBackgroundColor(Color.argb((int) ((255) * rate), (int) ((227) *
                        rate), (int) ((88) * rate), (int) ((81) * rate)));
            }
        });
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
                            if (!TextUtils.isEmpty(detailUrl)) {
                                slidedetailsBehind.loadUrl(API.PIC + detailUrl);
                            }
                        }
                    });
                } else {
                    KLog.i("onStatucChanged111111111");
                    slidedetailslayout.setWebViewToTop(true);
                }
            }
        });

        tvCount.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (mProduct == null) {
                    ToastUtil.show(GoodDetailActivity.this, "数据未加载成功，请重新加载");
                    return;
                }
                if (TextUtils.isEmpty(s)) {
                    return;
                }

                if (!s.toString().matches("\\d+")) {
                    ToastUtil.show(GoodDetailActivity.this, "客官，请输入正确的数量格式哦~");
                    tvCount.setText("1");
                    tvCount.onEditorAction(tvCount.getText().toString().length());
                    return;
                }


                int num = Integer.parseInt(s.toString());
                if (mProduct.getKuncun() == 0 && num == 1) {
                    return;
                }

                if (mProduct.getKuncun() == 0) {
                    ToastUtil.show(GoodDetailActivity.this, "没有库存啦~客官");
                    tvCount.setText("1");
                    tvCount.onEditorAction(tvCount.getText().toString().length());
                    return;
                }

                if (mProduct.getKuncun() < num) {
                    ToastUtil.show(GoodDetailActivity.this, "没有那么多库存了，客官");
                    tvCount.setText(String.valueOf(mProduct.getKuncun()));
                    tvCount.onEditorAction(tvCount.getText().toString().length());
                    return;
                }

                if (num < 1) {
                    ToastUtil.show(GoodDetailActivity.this, "商品数量不能小于1哦~");
                    tvCount.setText("1");
                    tvCount.onEditorAction(tvCount.getText().toString().length());
                    return;
                }


            }
        });
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putString(INSTANCE_PRODUCT_ID, mProductId);
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        if (savedInstanceState != null && savedInstanceState.containsKey(INSTANCE_PRODUCT_ID)) {
            mProductId = savedInstanceState.getString(INSTANCE_PRODUCT_ID);
        }
        super.onRestoreInstanceState(savedInstanceState);
    }

    @Override
    protected void onResume() {
        super.onResume();
        tvSendAddress.setText(Contains.curSelectXiaoQuName);

        // 如果购物车不为空 购物车的数量不少于0的话 就显示装载购物车数量的容器
        if (Contains.shopCartNum != null && Contains.shopCartNum != 0) {
            tvMallGoodsShopCartNum.setVisibility(View.VISIBLE);
            if (Contains.shopCartNum >= 100) {
                tvMallGoodsShopCartNum.setText("99+");
            } else {
                tvMallGoodsShopCartNum.setText(String.valueOf(Contains.shopCartNum));
            }
        } else {
            // 否则就隐藏
            tvMallGoodsShopCartNum.setVisibility(View.INVISIBLE);
        }
    }

    @Override
    protected void initData() {
        scrollView.setOnScrollListener(new ListenedScrollView.OnScrollListener() {
            @Override
            public void onBottomArrived() {
                KLog.i("图片的高度为=" + indexGoods.getHeight());
            }

            @Override
            public void onScrollStateChanged(ListenedScrollView view, int scrollState) {
                KLog.i("图片的高度为=" + indexGoods.getHeight());
            }

            @Override
            public void onScrollChanged(int l, int t, int oldl, int oldt) {
                KLog.i("图片的高度为=" + indexGoods.getHeight());
                double rate = 0;
                if (t > indexGoods.getHeight() - toolbar.getHeight()) {
                    KLog.i(t);
                    rate = ((t - (indexGoods.getHeight() - toolbar.getHeight())) / (double)
                            toolbar.getHeight());
                }
                if (rate >= 1) {
                    rate = 1;
                }
                KLog.i("rate==" + rate);
                toolbar.setBackgroundColor(Color.argb((int) ((255) * rate), (int) ((227) *
                        rate), (int) ((88) * rate), (int) ((81) * rate)));

            }
        });
        if (mTempCartContanier == null) {
            mTempCartContanier = new ArrayList<>();
        }
        Bundle bundle = getIntent().getExtras();
        mProductId = bundle.getString(KEY_PRODUCT_ID);
        loadDataFromServer();
    }

    private void loadDataFromServer() {
        if (TextUtils.isEmpty(mProductId)) {
            ToastUtil.show(GoodDetailActivity.this, "没有找到商品id");
            return;
        }
        showProgressDialog();
        mPresenter.loadGoodsDetailFromServer(mProductId);
        mPresenter.loadCommentFromServer(mProductId);
        mPresenter.loadShopCartFromServer();
    }

    @Override
    public void onLoadGoodsDetailSucceed(MallNewProduct1 mallProduct) {
        if (mSwipRefresh.isRefreshing()) {
            mSwipRefresh.setRefreshing(false);
        }
        if (mallProduct.getStatus() == 1) {
            MallNewProduct product = mallProduct.getRows();
            mImgUrls = new ArrayList<>();
//            Log.e("wh", " "+product.getZhutu()+"");
            detailUrl = product.getXiangqing();
            String[] imgs = product.getZhutu().split(",");
            Log.e("whdetailUrl", "size" + imgs.length + "  " + imgs.toString());
            for (String str : imgs) {
                mImgUrls.add(str);
            }

            MyGoodsDetailAdapter mAdapter = new MyGoodsDetailAdapter(mImgUrls, this);
//            indexGoods.setPageTransformer(true, new ZoomOutPageTransformer());
            indexGoods.setAdapter(mAdapter);
//            indexGoods.setPageMargin(20);
            indexGoods.setOffscreenPageLimit(5);
            indicator.setViewPager(indexGoods);

            tvGoodName.setText(product.getShangpinMing());
            tvGoodPrice.setText("¥ " + StringUitl.get2xiaoshu(product.getShoujia()));

//            SpannableStringBuilder builder = new SpannableStringBuilder();
//            builder.append("¥ 未知");
//            builder.setSpan(new StrikethroughSpan(), 0, builder.length(), 0);
//            tvGoodsOldPrice.setText(builder);
            //  |
            if (product.getShangpinDajian() == 1) {
                mTvPeisongfangshi.setText("大件配送");
            } else {
                mTvPeisongfangshi.setText("正常配送");
            }
            mTvXiaoliang.setText("销量" + product.getXiaoliang() + "");
            tvGoodsRemainNum.setText(" | 剩余" + product.getKuncun() + "件");
            mTvGuige.setText(" |  " + product.getGuige() + "");
            mProduct = product;
        } else {
            onError(mallProduct.MSG, mallProduct.status);
        }
    }

    @Override
    public void onLoadGoodsDetailFailed() {
        if (mSwipRefresh.isRefreshing()) {
            mSwipRefresh.setRefreshing(false);
        }
        ToastUtil.show(GoodDetailActivity.this, getResources().getString(R.string.load_failed));
    }

    @Override
    public void onLoadCommentSucceed(MyAllComment comment) {
        if (mSwipRefresh.isRefreshing()) {
            mSwipRefresh.setRefreshing(false);
        }
        if (comment.status == 1) {
            int mTotalComment = comment.getTotal();
            if (mTotalComment != 0) {
                tvCommentCount.setText("用户评价(" + mTotalComment + "个)");
                tvAllComment.setVisibility(View.VISIBLE);

                LinearLayoutManager manager = new LinearLayoutManager(GoodDetailActivity.this);
                GoodsCommentAdapter mCommentAdapter = new GoodsCommentAdapter(comment.getRows(),
                        true);
                recycerView.setLayoutManager(manager);
                recycerView.setAdapter(mCommentAdapter);
            }
            else {
                //当评价数据为空时设置假数据
                tvCommentCount.setText("用户评价(" + mTotalComment + "个)");
                tvAllComment.setVisibility(View.VISIBLE);
                List<MyAllComment.DataBean> list = new ArrayList<>();
                MyAllComment.DataBean dataBean=new MyAllComment.DataBean();
                dataBean.setPingjiaDengji(0);
                dataBean.setPingjiaNeirong("用户暂时没有评价");
                list.add(dataBean);
                LinearLayoutManager manager = new LinearLayoutManager(GoodDetailActivity.this);
                GoodsCommentAdapter mCommentAdapter = new GoodsCommentAdapter(list,
                        false);
                recycerView.setLayoutManager(manager);
                recycerView.setAdapter(mCommentAdapter);
            }
        } else {
            onError(comment.MSG, comment.status);
        }
    }

    @Override
    public void onAdd2ShopCartSuccess(BaseEntity entity) {
        if (mSwipRefresh.isRefreshing()) {
            mSwipRefresh.setRefreshing(false);
        }
        if (entity.status == 1) {
            mPresenter.loadShopCartFromServer();
            CxUtil.actionAndAction(layoutGoodsListShopCart, GoodDetailActivity.this);

        } else {
            onError(entity.getMSG(), entity.getStatus());
        }
    }

    @Override
    public void onLoadShopCartSucceed(ShopCart cart) {
        if (mSwipRefresh.isRefreshing()) {
            mSwipRefresh.setRefreshing(false);
        }
        closeProgressDialog();
        if (cart.status == 1) {
            Contains.shopCartList = cart.getRows();
            Contains.shopCartNum = cart.getTotal();
            if (cart.getTotal() > 0) {
                tvMallGoodsShopCartNum.setVisibility(View.VISIBLE);
                if (Contains.shopCartNum >= 100) {
                    tvMallGoodsShopCartNum.setText("99+");
                } else {
                    tvMallGoodsShopCartNum.setText(String.valueOf(Contains.shopCartNum));
                }
            }
        } else {
            //onError(cart.MSG, cart.status);
        }
    }

    private boolean cartContainsTheProduct() {
        if (mTempCartContanier.size() > 0) {
            return true;
        }
        for (CxwyMallCart cart : Contains.CartList) {
            if (cart.getCartShangpNum().equals(mProductId)) {
                return true;
            }
        }

        return false;
    }

    @Override
    public void onLoadFailed() {
        if (mSwipRefresh.isRefreshing()) {
            mSwipRefresh.setRefreshing(false);
        }
        ToastUtil.show(GoodDetailActivity.this, getResources().getString(R.string.load_failed));
    }


    @OnClick({R.id.btn_add_to_cart, R.id.btn_buy_now, R.id.iv_jia, R.id.iv_jian,
            R.id.layout_goods_list_shop_cart, R.id.rl_comment_root, R.id.tv_all_comment})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_add_to_cart:
                add2Cart();
                break;
            case R.id.btn_buy_now:
                toBuyNow();
                break;
            case R.id.iv_jia:
                toUpdateCount(1);
                break;
            case R.id.iv_jian:
                toUpdateCount(0);
                break;
            case R.id.layout_goods_list_shop_cart:
                Intent intent = new Intent(GoodDetailActivity.this, SecondShopCartActivity.class);
                startActivity(intent);
                AppConfig.getInstance().mAppActivityManager.finishActivity(SecondShopCartActivity
                        .class);
                break;
            case R.id.rl_comment_root:
                toCommentList();
                break;
            case R.id.tv_all_comment:
                toCommentList();
                break;
            default:
                break;

        }
    }

    private void toCommentList() {
        Intent intent = new Intent(GoodDetailActivity.this, CommentListActivity.class);
        intent.putExtra(KEY_PRODUCT_ID, mProductId);
        startActivity(intent);
    }

    private void toUpdateCount(int type) {
        if (mProduct == null) {
            ToastUtil.show(GoodDetailActivity.this, "数据未加载成功，请重新加载");
            return;
        }
        String sNum = tvCount.getText().toString();
        if (TextUtils.isEmpty(sNum)) {
            sNum = "0";
        }
        int num = Integer.parseInt(sNum);
        if (type == 1) {
            if (mProduct.getKuncun() > num) {
                num++;
            } else {
                ToastUtil.show(GoodDetailActivity.this, "客官，要超出库存了哦");
            }
        } else if (type == 0) {
            if (num > 1) {
                num--;
            } else {
                ToastUtil.show(GoodDetailActivity.this, "客官，不能再少了~");
            }
        }

        tvCount.setText(String.valueOf(num));
    }

    private void add2Cart() {
        if (hasRemains()) {
            Map<String, String> map = new HashMap<String, String>();
            map.put("cartIsDajian", mProduct.getShangpinDajian() + "");
            map.put("cartIsYejian", mProduct.getYejianXiaoshou() + "");
            map.put("cartNum", tvCount.getText().toString());
            map.put("cartSpbianhao", mProduct.getId() + "");
            map.put("cartSpdanjia", mProduct.getShoujia() + "");
            map.put("cartSpguige", mProduct.getGuige());
            map.put("cartSpmingcheng", mProduct.getShangpinMing());
            map.put("cartSpzhutu", mProduct.getZhutu());
            map.put("uuid", Contains.uuid);
            mPresenter.add2ShopCart(map);
        }
    }

    private boolean hasRemains() {
        if (mProduct == null) {
            ToastUtil.show(GoodDetailActivity.this, "未获取到商品详细信息，请重新加载");
            return false;
        }
        if (mProduct.getKuncun() == 0) {
            ToastUtil.show(GoodDetailActivity.this, "商品缺货哦");
            return false;
        }
//        if (mProduct.getShangpinHave() == 0) {
//            ToastUtil.show(GoodDetailActivity.this, "商品已经缺货哦");
//            return false;
//        }
        if (tvCount.getText() == null || "".equals(tvCount.getText().toString()) || "0".equals
                (tvCount.getText().toString())) {
            ToastUtil.show(GoodDetailActivity.this, "请输入正确数量");
            return false;
        }
        //判断购买量是否大于库存量
        int buyNum = Integer.parseInt(tvCount.getText().toString());
        if (buyNum > mProduct.getKuncun()) {
            ToastUtil.show(GoodDetailActivity.this, "库存不足哦");
            return false;
        }

        return true;
    }

    private void toBuyNow() {
        if (hasRemains()) {
            Contains.sureOrderList.clear();
            SureOrderEntity entity = new SureOrderEntity(mProduct.getId() + "", tvCount.getText()
                    .toString(),
                    "", mProduct.getZhutu(), mProduct.getShoujia() + "",
                    mProduct.getShangpinMing(), mProduct.getGuige(), mProduct.getShangpinDajian()
                    + "", mProduct.getYejianXiaoshou() + "");
            Contains.sureOrderList.add(entity);
            Intent intent = new Intent(GoodDetailActivity.this, ConfirmOrderActivity.class);
            intent.putExtra(ENTER_TYPE, "2");
            startActivity(intent);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.unsubscribe();
        mPresenter = null;
        mTempCartContanier = null;
        mProduct = null;
        recycerView = null;
        indexGoods = null;
        indicator = null;
        destroyWebView();
    }

    private void destroyWebView() {
//        if (slidedetailsBehind != null) {
//            // 如果先调用destroy()方法，则会命中if (isDestroyed()) return;这一行代码，需要先onDetachedFromWindow()，再
//            // destory()
//            ViewParent parent = slidedetailsBehind.getParent();
//            if (parent != null) {
//                ((ViewGroup) parent).removeView(slidedetailsBehind);
//            }
//
//            slidedetailsBehind.stopLoading();
//            // 退出时调用此方法，移除绑定的服务，否则某些特定系统会报错
//            slidedetailsBehind.getSettings().setJavaScriptEnabled(false);
//            slidedetailsBehind.clearHistory();
//            slidedetailsBehind.clearView();
//            slidedetailsBehind.removeAllViews();
//
//            try {
//                slidedetailsBehind.destroy();
//            } catch (Throwable ex) {
//
//            }
//        }
    }

    @Override
    protected void setupActivityComponent() {
        DaggerGoodDetailComponent
                .builder()
                .appComponent(((AppConfig) getApplication()).getApplicationComponent())
                .goodDetailModule(new GoodDetailModule(this))
                .build()
                .inject(this);
    }

    @Override
    public void setPresenter(GoodDetailContract.GoodDetailContractPresenter presenter) {
        mPresenter = (GoodDetailPresenter) presenter;
    }


    @Override
    public void showProgressDialog() {
        progressDialog.show();
    }

    @Override
    public void closeProgressDialog() {
        progressDialog.hide();
    }

    @OnClick(R.id.iv_mall_goods_list_back)
    public void onViewClicked() {
        finish();
    }

    @Override
    public void onRefresh() {
        loadDataFromServer();
    }
}