package com.yxld.yxchuangxin.ui.activity.goods;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.BitmapImageViewTarget;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.socks.library.KLog;
import com.yxld.yxchuangxin.R;
import com.yxld.yxchuangxin.Utils.CxUtil;
import com.yxld.yxchuangxin.Utils.StringUitl;
import com.yxld.yxchuangxin.Utils.ToastUtil;
import com.yxld.yxchuangxin.Utils.UIUtils;
import com.yxld.yxchuangxin.application.AppConfig;
import com.yxld.yxchuangxin.base.BaseActivity;
import com.yxld.yxchuangxin.contain.Contains;
import com.yxld.yxchuangxin.data.api.API;
import com.yxld.yxchuangxin.entity.CxwyMallCart;
import com.yxld.yxchuangxin.entity.CxwyMallProduct;
import com.yxld.yxchuangxin.entity.goods.MallNewProduct;
import com.yxld.yxchuangxin.entity.goods.ShopNewList;
import com.yxld.yxchuangxin.ui.activity.goods.component.DaggerMallGoodsListComponent;
import com.yxld.yxchuangxin.ui.activity.goods.contract.MallGoodsListContract;
import com.yxld.yxchuangxin.ui.activity.goods.module.MallGoodsListModule;
import com.yxld.yxchuangxin.ui.activity.goods.presenter.MallGoodsListPresenter;
import com.yxld.yxchuangxin.ui.adapter.goods.MallGoodsList2Adapter;
import com.yxld.yxchuangxin.view.GoodsListSearchView;
import com.yxld.yxchuangxin.view.MallGoodsListRankView;
import com.zhy.autolayout.AutoLinearLayout;
import com.zhy.autolayout.AutoRelativeLayout;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author Yuan.Y.Q
 * @Package com.yxld.yxchuangxin.ui.activity.goods
 * @Description: $description
 * @date 2017/06/19 14:28:26
 */

public class MallGoodsListActivity extends BaseActivity implements MallGoodsListContract.View, SwipeRefreshLayout.OnRefreshListener, BaseQuickAdapter.RequestLoadMoreListener {
    public static final String PRODUCT_NAME = "product_type"; //零食、饮品、米面油...所有、以及各种精选
    public static final String KEY_IN_TYPE = "in_type";
    public static final int CODE_SEARCH = 0x000001;
    public static final int CODE_REQUEST_DETAIL = 0x000002;

    private static final String INSTANCE_PRODUCT_TYPE = "instance_product_type";
    private static final String INSTANCE_RANK_TYPE = "instance_rank_type";
    private static final String INSTANCE_IN_TYPE = "instance_in_type";

    private static final int ONE_PAGE_SIZE = 10;


    @Inject
    MallGoodsListPresenter mPresenter;

    @BindView(R.id.goods_list_status_bar)
    View goodsListStatusBar;

    @BindView(R.id.rank_sale)
    MallGoodsListRankView rankSale;
    @BindView(R.id.rank_price)
    MallGoodsListRankView rankPrice;
    @BindView(R.id.rank_popular)
    MallGoodsListRankView rankPopular;
    @BindView(R.id.recycler_mall_goods_list)
    RecyclerView recyclerMallGoodsList;
    @BindView(R.id.refresh_loading)
    SwipeRefreshLayout refreshLoading;

    /**
     * 购物车的数量
     */
    @BindView(R.id.tv_mall_goods_shop_cart_num)
    TextView tvMallGoodsShopCartNum;

    /**
     * 购物车
     */
    @BindView(R.id.layout_goods_list_shop_cart)
    AutoRelativeLayout layoutGoodsListShopCart;

    @BindView(R.id.rl_goods_list_root)
    AutoRelativeLayout rlGoodsListRoot;

    @BindView(R.id.goods_list_search_view)
    GoodsListSearchView goodsListSearchView;

    /**
     * 零食、饮品、米面油...所有、以及各种精选
     */
    private String mCurrentProduct;

    private List<MallNewProduct> mProducts;
    private MallGoodsList2Adapter mGoodsAdapter;
    private List<MallGoodsListRankView> mRankViews;

    /**
     * 当前按照价格、销量还是人气来排序
     */
    private int mCurrentRankType;

    /**
     * 首页推荐进来的还是通过菜单进来的
     */
    private int mInType;

    private int mCurrentPage;

    private int mTotalNumProducts;


    private List<String> mTempCartContanier;//避免多次请求服务器设置一个临时的容器用来判断该商品是否已经加入过购物车

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initEvent(savedInstanceState);
    }

    @Override
    protected void onResume() {
        super.onResume();
        KLog.i("--------------------" + Contains.shopCartNum);
        // 如果购物车不为空 购物车的数量不少于0的话 就显示装载购物车数量的容器
        if (Contains.shopCartNum != null && Contains.shopCartNum != 0) {
            tvMallGoodsShopCartNum.setVisibility(View.VISIBLE);
            tvMallGoodsShopCartNum.setText(String.valueOf(Contains.shopCartNum));
        } else {
            // 否则就隐藏
            tvMallGoodsShopCartNum.setVisibility(View.INVISIBLE);
        }
    }

    private void initEvent(Bundle savedInstanceState) {
        Intent intent = getIntent();
        if (savedInstanceState != null) {
            mCurrentProduct = savedInstanceState.getString(INSTANCE_PRODUCT_TYPE);
            mCurrentRankType = savedInstanceState.getInt(INSTANCE_RANK_TYPE);
            mInType = savedInstanceState.getInt(INSTANCE_IN_TYPE);
        } else {
            mCurrentProduct = intent.getStringExtra(PRODUCT_NAME);
            mInType = intent.getIntExtra(KEY_IN_TYPE, MallFragment.IN_TYPE_NORMAL_GOODS);
            mCurrentRankType = 0;
            mCurrentPage = 1;
            mTotalNumProducts = 0;
        }

        initStatus();
        initEvent();
        setRankViewsEvent();

        refreshLoading.setOnRefreshListener(this);
        UIUtils.configSwipeRefreshLayoutColors(refreshLoading);
        refreshLoading.post(new Runnable() {
            @Override
            public void run() {
                refreshLoading.setRefreshing(true);
                loadDataFromServer(true);
            }
        });
    }

    @Override
    protected void initView() {
        setContentView(R.layout.activity_mall_goods_list);
        ButterKnife.bind(this);
        toolbar.setVisibility(View.GONE);
        AutoLinearLayout.LayoutParams lp = new AutoLinearLayout.LayoutParams(UIUtils.getDisplayWidth(this), (int) (UIUtils.getStatusBarHeight(this) * 2));
        goodsListSearchView.setLayoutParams(lp);

    }

    private void initEvent() {
        mProducts = new ArrayList<>();
        mRankViews = new ArrayList<>();
        mGoodsAdapter = new MallGoodsList2Adapter(mProducts);
        GridLayoutManager manager = new GridLayoutManager(this, 2);
        recyclerMallGoodsList.setLayoutManager(manager);
        recyclerMallGoodsList.setAdapter(mGoodsAdapter);

        mGoodsAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter baseQuickAdapter, View view, int position) {
                //view 为绑定该点击事件的view
                //int pos 为当前item的位置
                if (view.getTag().equals("Detail")) {
                    Intent intent = new Intent(MallGoodsListActivity.this, GoodDetailActivity.class);
                    intent.putExtra(GoodDetailActivity.KEY_PRODUCT_ID, mProducts.get(position).getId() + "");
                    startActivityForResult(intent, CODE_REQUEST_DETAIL);
                } else if (view.getTag().equals("Add2ShopCart")) {
                    Map<String, String> map = new HashMap<String, String>();
                    map.put("cartIsDajian", mGoodsAdapter.getData().get(position).getShangpinDajian() + "");
                    map.put("cartIsYejian", mGoodsAdapter.getData().get(position).getYejianXiaoshou() + "");
                    map.put("cartNum", "1");
                    map.put("cartSpbianhao", mGoodsAdapter.getData().get(position).getId() + "");
                    map.put("cartSpdanjia", mGoodsAdapter.getData().get(position).getShoujia() + "");
                    map.put("cartSpguige", mGoodsAdapter.getData().get(position).getGuige());
                    map.put("cartSpmingcheng", mGoodsAdapter.getData().get(position).getShangpinMing());
                    map.put("cartSpzhutu", mGoodsAdapter.getData().get(position).getZhutu());
                    map.put("uuid", Contains.uuid);
                    mPresenter.add2ShopCart(map, view, StringUitl.replaceEndFenHao(mGoodsAdapter.getData().get(position).getZhutu()));
                }
            }
        });

        mGoodsAdapter.setOnLoadMoreListener(this, recyclerMallGoodsList);
        goodsListSearchView.setContent(mCurrentProduct);
        goodsListSearchView.setOnViewClickListener(new GoodsListSearchView.OnViewClickListener() {
            @Override
            public void onBackClick() {
                finish();
            }

            @Override
            public void onSearchTextClick() {
                toSearchActivity();
            }

            @Override
            public void onSearchRecClick() {
                toSearchActivity();
            }
        });
    }


//    private void add2ShopCart(View view,int pos){
//        CxwyMallProduct product = mProducts.get(pos);
//        if(hasRemain(product)){
//            refreshLoading.setRefreshing(true);
//            mPresenter.add2ShopCart(product,view,pos);
//        }
//    }

    private boolean hasRemain(CxwyMallProduct product) {
        if (product.getShangpinNum() == 0) {
            ToastUtil.show(MallGoodsListActivity.this, "商品缺货哦");
            return false;
        }

        if (product.getShangpinHave() != null && product.getShangpinHave() == 0) {
            ToastUtil.show(MallGoodsListActivity.this, "商品已经缺货哦");
            return false;
        }
        return true;
    }

    @Override
    public void onAdd2ShopCartSuccess(View view, String url) {
        refreshLoading.setRefreshing(false);
        onItemShopCartImageClick(view, url);
        mPresenter.loadShopCartFromServer();
    }

    @Override
    public void onLoadShopCartSucceed(int count) {
        refreshLoading.setRefreshing(false);
        if (count > 0) {
            tvMallGoodsShopCartNum.setVisibility(View.VISIBLE);
            tvMallGoodsShopCartNum.setText(count + "");
        }
    }

    private boolean cartContainsTheProduct(CxwyMallProduct product) {
        for (CxwyMallCart cart : Contains.CartList) {
            if (cart.getCartShangpNum().equals(product.getShangpinId() + "")) {
                return true;
            }
        }
        for (String str : mTempCartContanier) {
            if (str.equals(product.getShangpinId() + "")) {
                return true;
            }
        }
        return false;
    }

    @Override
    protected void onDestroy() {

        super.onDestroy();
        refreshLoading.setRefreshing(false);
        mPresenter.unsubscribe();
        mPresenter = null;
        mProducts.clear();
        mProducts = null;
        mGoodsAdapter = null;
        mTempCartContanier.clear();
        mTempCartContanier = null;
        mRankViews.clear();
        mRankViews = null;
    }

    private void sendBroadcastCartChanged() {
        Intent intent = new Intent(getResources().getString(R.string.add_shop_cart));
        sendBroadcast(intent);
    }

    /**
     * 点击item中的购物车图标
     *
     * @param view ImageView
     * @param url  当前item的图片
     */
    private void onItemShopCartImageClick(View view, String url) {
        //获取被点击的ImageView的当前屏幕位置
        int[] itemCartLocation = new int[2];
        view.getLocationInWindow(itemCartLocation);

        //获取购物车当前的屏幕位置
        int[] shopCartLocation = new int[2];
        tvMallGoodsShopCartNum.getLocationInWindow(shopCartLocation);

        //创建需要移动的View
        ImageView dotView = (ImageView) createAnimationViewer(itemCartLocation, url);
        rlGoodsListRoot.addView(dotView);

        int xOff = shopCartLocation[0] - itemCartLocation[0];
        int yOff = shopCartLocation[1] - itemCartLocation[1];

        TranslateAnimation translateAnimation = new TranslateAnimation(0, xOff, 0, yOff);
        translateAnimation.setRepeatCount(0);
        translateAnimation.setFillAfter(true);
        translateAnimation.setInterpolator(new AccelerateInterpolator());
        translateAnimation.setDuration(800);
        translateAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                dotView.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                rlGoodsListRoot.removeView(dotView);
                //抖一抖动画
                CxUtil.actionAndAction(layoutGoodsListShopCart, MallGoodsListActivity.this);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }

        });

        dotView.startAnimation(translateAnimation);
    }

    private View createAnimationViewer(int[] itemCartLocation, String url) {
        ImageView view = new ImageView(MallGoodsListActivity.this);
        AutoRelativeLayout.LayoutParams params = new AutoRelativeLayout.LayoutParams
                (100, 100);
        params.leftMargin = itemCartLocation[0];
        params.topMargin = itemCartLocation[1];
        view.setLayoutParams(params);
        Glide.with(this)
                .load(API.PIC + url)
                .asBitmap().centerCrop().into(new BitmapImageViewTarget(view) {
            @Override
            protected void setResource(Bitmap resource) {
                RoundedBitmapDrawable circularBitmapDrawable =
                        RoundedBitmapDrawableFactory.create(getResources(), resource);
                circularBitmapDrawable.setCircular(true);
                view.setImageDrawable(circularBitmapDrawable);
            }
        });
        return view;
    }


    private void setRankViewsEvent() {
        rankSale.onViewClicked();
        mRankViews.add(rankSale);
        mRankViews.add(rankPrice);
        mRankViews.add(rankPopular);
    }

    private void setRankViewClick(MallGoodsListRankView rankView) {
        mTotalNumProducts = 0;
        mCurrentPage = 1;
        mGoodsAdapter.setEnableLoadMore(true);
        for (int i = 0; i < mRankViews.size(); i++) {
            MallGoodsListRankView view = mRankViews.get(i);
            if (view.equals(rankView)) {
                view.onViewClicked();
                mCurrentRankType = i;
                loadDataFromServer(true);
            } else {
                view.onOtherViewClicked();
            }
        }
    }

    private void initStatus() {
        AutoLinearLayout.LayoutParams params = (AutoLinearLayout.LayoutParams) goodsListStatusBar.getLayoutParams();
        params.height = UIUtils.getStatusBarHeight(UIUtils.getContext());
        goodsListStatusBar.setLayoutParams(params);
    }


    @Override
    protected void initData() {
        if (mTempCartContanier == null) {
            mTempCartContanier = new ArrayList<>();
        }
    }

    private void toSearchActivity() {
        Intent intent = new Intent(MallGoodsListActivity.this, SearchActivity.class);
        intent.putExtra(SearchActivity.KEY_IN_TYPE, SearchActivity.IN_TYPE_GOODS_LIST);
        startActivityForResult(intent, CODE_SEARCH);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CODE_SEARCH && resultCode == CODE_SEARCH) {
            mCurrentProduct = data.getExtras().getString(PRODUCT_NAME);
            goodsListSearchView.setContent(mCurrentProduct);
            mCurrentRankType = 0;
            mCurrentPage = 1;
            mTotalNumProducts = 0;
            setRankViewClick(mRankViews.get(mCurrentRankType));
            loadDataFromServer(true);
        } else if (requestCode == CODE_REQUEST_DETAIL && resultCode == CODE_REQUEST_DETAIL) {
            setResult(MallFragment.CODE_FOR_GOODS_LIST_SHOP_CART);
            finish();
        }
    }

    private void loadDataFromServer(boolean showSwipeRefresh) {
        if (refreshLoading == null) {
            return;
        }
        refreshLoading.setRefreshing(showSwipeRefresh);
        mPresenter.loadMallGoodsFromServer(mCurrentProduct, mCurrentRankType, mRankViews.get(mCurrentRankType).getCurrentRankMethod()
                , mInType, mCurrentPage, ONE_PAGE_SIZE);
        mPresenter.loadShopCartFromServer();
    }


    @Override
    protected void setupActivityComponent() {
        DaggerMallGoodsListComponent
                .builder()
                .appComponent(((AppConfig) getApplication()).getApplicationComponent())
                .mallGoodsListModule(new MallGoodsListModule(this))
                .build()
                .inject(this);
    }

    @Override
    public void setPresenter(MallGoodsListContract.MallGoodsListContractPresenter presenter) {
        mPresenter = (MallGoodsListPresenter) presenter;
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
    public void onMallGoodsBacked(ShopNewList list) {
        if (refreshLoading == null) {
            return;
        }
        refreshLoading.setRefreshing(false);
        mGoodsAdapter.loadMoreComplete();
        if (list.status == 1) {
            if (mCurrentPage == 1) {
                //第一次加载或者重新刷新
                mProducts.clear();
            }

            int startPos = mProducts.size();
            mProducts.addAll(list.getRows());
            if (mCurrentPage == 1) {
                mGoodsAdapter.notifyDataSetChanged();
            } else {
                mGoodsAdapter.notifyItemRangeInserted(startPos, list.getRows().size());
            }

            mTotalNumProducts = list.getTotal();
            if (mTotalNumProducts > mProducts.size()) {
                mCurrentPage++;
            }
        } else {
            mGoodsAdapter.loadMoreFail();
            onLoadFailed();
        }
    }

    @Override
    public void onLoadFailed() {
        refreshLoading.setRefreshing(false);
//        ToastUtil.show(MallGoodsListActivity.this, getString(R.string.loading_failed_1));
    }

    @Override
    public void onLoadFailed(String msg) {
        refreshLoading.setRefreshing(false);
        ToastUtil.show(MallGoodsListActivity.this, msg);
    }


    @OnClick({R.id.rank_sale, R.id.rank_price, R.id.rank_popular, R.id.layout_goods_list_shop_cart})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.layout_goods_list_shop_cart:
                //Todo如果要直接跳转到主页的购物车,可以在这里发送广播
                Intent intent = new Intent(MallGoodsListActivity.this, SecondShopCartActivity.class);
                startActivity(intent);
                break;
            case R.id.rank_sale:
                setRankViewClick((MallGoodsListRankView) view);
                break;
            case R.id.rank_price:
                setRankViewClick((MallGoodsListRankView) view);
                break;
            case R.id.rank_popular:
                setRankViewClick((MallGoodsListRankView) view);
                break;
            default:
                break;
        }
    }


    @Override
    public void onRefresh() {
        mCurrentPage = 1;
        mGoodsAdapter.setEnableLoadMore(true);
        loadDataFromServer(true);
    }

    @Override
    public void onLoadMoreRequested() {
        if (mProducts.size() < ONE_PAGE_SIZE - mPresenter.oneremovedCOunt) {
            mGoodsAdapter.loadMoreEnd(false);
        } else if (mProducts.size() >= mTotalNumProducts) {
            mGoodsAdapter.loadMoreEnd(false);
        } else {
            loadDataFromServer(false);
        }
    }
}