package com.yxld.yxchuangxin.ui.activity.goods;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.sdk.android.ams.common.util.StringUtil;
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
import com.yxld.yxchuangxin.base.BaseEntity;
import com.yxld.yxchuangxin.contain.Contains;
import com.yxld.yxchuangxin.data.api.API;
import com.yxld.yxchuangxin.entity.CxwyMallCart;
import com.yxld.yxchuangxin.entity.CxwyMallProduct;
import com.yxld.yxchuangxin.entity.goods.MallNewProduct;
import com.yxld.yxchuangxin.entity.goods.ShopNewList;
import com.yxld.yxchuangxin.ui.activity.goods.component.DaggerGoodFenLeiComponent;
import com.yxld.yxchuangxin.ui.activity.goods.contract.GoodFenLeiContract;
import com.yxld.yxchuangxin.ui.activity.goods.module.GoodFenLeiModule;
import com.yxld.yxchuangxin.ui.activity.goods.presenter.GoodFenLeiPresenter;
import com.yxld.yxchuangxin.ui.activity.xiongmai.lib.sdk.struct.Strings;
import com.yxld.yxchuangxin.ui.adapter.goods.MallGoodsList2Adapter;
import com.yxld.yxchuangxin.ui.adapter.wuye.SpacesItemDecoration;
import com.yxld.yxchuangxin.view.MallGoodsListRankView;
import com.zhy.autolayout.AutoRelativeLayout;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

import static com.yxld.yxchuangxin.ui.activity.goods.MallGoodsListActivity.CODE_REQUEST_DETAIL;

/**
 * @author xlei
 * @Package com.yxld.yxchuangxin.ui.activity.goods
 * @Description: $description
 * @date 2017/10/19 09:28:17
 */

public class GoodFenLeiFragment extends MyBaseFragment implements GoodFenLeiContract.View, SwipeRefreshLayout.OnRefreshListener, BaseQuickAdapter.RequestLoadMoreListener {

    private static final int ONE_PAGE_SIZE = 10;
    @Inject
    GoodFenLeiPresenter mPresenter;
    private static final String FEN_LEI_TYPE = "fen_lei1_type";
    private static final String FEN_LEI_TYPE2 = "fen_lei2_type";
    @BindView(R.id.rank_sale)
    MallGoodsListRankView mRankSale;
    @BindView(R.id.rank_price)
    MallGoodsListRankView mRankPrice;
    @BindView(R.id.rank_popular)
    MallGoodsListRankView mRankPopular;
    @BindView(R.id.recycler_mall_goods_list)
    RecyclerView mRecyclerMallGoodsList;
    @BindView(R.id.refresh_loading)
    SwipeRefreshLayout mRefreshLoading;
    // @BindView(R.id.tv_mall_goods_shop_cart_num)
    TextView mTvMallGoodsShopCartNum;
    //  @BindView(R.id.layout_goods_list_shop_cart)
    AutoRelativeLayout mLayoutGoodsListShopCart;
    // @BindView(R.id.rl_goods_list_root)
    AutoRelativeLayout mRlGoodsListRoot;
    private String mFeiLei1;
    private String mFeiLei2;
    private Unbinder bind;


    /**
     * 零食、饮品、米面油...所有、以及各种精选
     */
    private String mCurrentProduct;

    private List<MallNewProduct> mProducts;
    private MallGoodsList2Adapter mGoodsAdapter;
    private List<MallGoodsListRankView> mRankViews;
    private List<String> mTempCartContanier;//避免多次请求服务器设置一个临时的容器用来判断该商品是否已经加入过购物车
    /**
     * 当前按照价格、销量还是人气来排序
     */
    private int mCurrentRankType;

    /**
     * 首页推荐进来的还是通过菜单进来的
     */
    private int mInType = 0x000002;

    private int mCurrentPage;

    private int mTotalNumProducts;

    private boolean mCartChanged;

    public static GoodFenLeiFragment newInstance(String param, String feiLei2) {
        GoodFenLeiFragment fragment = new GoodFenLeiFragment();
        Bundle args = new Bundle();
        args.putString(FEN_LEI_TYPE, param);
        args.putString(FEN_LEI_TYPE2, feiLei2);
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_goods_fenlei, null);
        bind = ButterKnife.bind(this, view);
        if (getArguments() != null) {
            mFeiLei1 = getArguments().getString(FEN_LEI_TYPE);
            mFeiLei2 = getArguments().getString(FEN_LEI_TYPE2);
            if ("10000".equals(mFeiLei1)) {
                mFeiLei1 = "";
            }
        }

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initEvent(savedInstanceState);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public void fetchData() {
        mRefreshLoading.post(new Runnable() {
            @Override
            public void run() {
                mRefreshLoading.setRefreshing(true);
                loadDataFromServer(true);
            }
        });
    }

    private void initEvent(Bundle savedInstanceState) {
        mCurrentProduct = mFeiLei1;
        mCurrentRankType = 0;
        mCurrentPage = 1;
        mTotalNumProducts = 0;
        mCartChanged = false;
        initEvent();
        setRankViewsEvent();
        mTvMallGoodsShopCartNum = (TextView) getActivity().findViewById(R.id.tv_mall_goods_shop_cart_num);
        mLayoutGoodsListShopCart = (AutoRelativeLayout) getActivity().findViewById(R.id.layout_goods_list_shop_cart);
        mRlGoodsListRoot = (AutoRelativeLayout) getActivity().findViewById(R.id.root_layout);
        mRefreshLoading.setOnRefreshListener(this);
        UIUtils.configSwipeRefreshLayoutColors(mRefreshLoading);
        if (mTempCartContanier == null) {
            mTempCartContanier = new ArrayList<>();
        }
    }

    private void setRankViewsEvent() {
        mRankSale.onViewClicked();
        mRankViews.add(mRankSale);
        mRankViews.add(mRankPrice);
        mRankViews.add(mRankPopular);
    }

    private void initEvent() {
        mProducts = new ArrayList<>();
        mRankViews = new ArrayList<>();
        mGoodsAdapter = new MallGoodsList2Adapter(mProducts);
        GridLayoutManager manager = new GridLayoutManager(getActivity(), 2);
        mRecyclerMallGoodsList.setLayoutManager(manager);
        SpacesItemDecoration decoration = new SpacesItemDecoration(8);
        mRecyclerMallGoodsList.setPadding(8, 8, 8, 8);
        mRecyclerMallGoodsList.addItemDecoration(decoration);
        mRecyclerMallGoodsList.setAdapter(mGoodsAdapter);
        mGoodsAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter baseQuickAdapter, View view, int position) {
                //view 为绑定该点击事件的view
                //int pos 为当前item的位置
                if (view.getTag().equals("Detail")) {
                    Intent intent = new Intent(getActivity(), GoodDetailActivity.class);
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

        mGoodsAdapter.setOnLoadMoreListener(this, mRecyclerMallGoodsList);


    }


    private boolean hasRemain(CxwyMallProduct product) {
        if (product.getShangpinNum() == 0) {
            ToastUtil.show(getActivity(), "商品缺货哦");
            return false;
        }

        if (product.getShangpinHave() != null && product.getShangpinHave() == 0) {
            ToastUtil.show(getActivity(), "商品已经缺货哦");
            return false;
        }
        return true;
    }

    @Override
    protected void setupFragmentComponent() {
        DaggerGoodFenLeiComponent
                .builder()
                .appComponent(((AppConfig) getActivity().getApplication()).getApplicationComponent())
                .goodFenLeiModule(new GoodFenLeiModule(this))
                .build()
                .inject(this);
    }

    @Override
    public void setPresenter(GoodFenLeiContract.GoodFenLeiContractPresenter presenter) {
        mPresenter = (GoodFenLeiPresenter) presenter;
    }

    @Override
    protected void initDataFromLocal() {

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
        if (mRefreshLoading == null) {
            return;
        }
        mRefreshLoading.setRefreshing(false);
        mGoodsAdapter.loadMoreComplete();
        if (list.status == 1) {
            if (mCurrentPage == 1) {
                //第一次加载或者重新刷新
                mProducts.clear();
            }

            int startPos = mProducts.size();
            mProducts.addAll(list.getRows());
            //当前没有数据
            if (mProducts.size() == 0) {
                View view = LayoutInflater.from(getActivity()).inflate(R.layout.layout_empty_data, null);
                TextView textView = (TextView) view.findViewById(R.id.tv_content);
                textView.setText("暂无商品");
                mGoodsAdapter.setEmptyView(view);
            }
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
            onError(list.getMSG(),list.getStatus());
            mGoodsAdapter.loadMoreFail();
        }
    }

    @Override
    public void onLoadFailed() {
        if (mRefreshLoading == null) {
            return;
        }
        mRefreshLoading.setRefreshing(false);
    }

    @Override
    public void onLoadFailed(String msg) {
        mRefreshLoading.setRefreshing(false);
        ToastUtil.show(this.getActivity(), msg);
    }


    @Override
    public void onAdd2ShopCartSuccess(View view, String imgUrl) {
        onItemShopCartImageClick(view, imgUrl);
        mPresenter.loadShopCartFromServer();

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

    /**
     * 点击item中的购物车图标
     *
     * @param view ImageView
     */
    private void onItemShopCartImageClick(View view, String imgUrl) {
        //获取被点击的ImageView的当前屏幕位置
        int[] itemCartLocation = new int[2];
        view.getLocationInWindow(itemCartLocation);
        //获取购物车当前的屏幕位置
        int[] shopCartLocation = new int[2];
        mTvMallGoodsShopCartNum.getLocationInWindow(shopCartLocation);
        //创建需要移动的View
        ImageView dotView = (ImageView) createAnimationViewer(itemCartLocation, imgUrl);
        mRlGoodsListRoot.addView(dotView);
        KLog.i("xx1" + shopCartLocation[0] + " " + itemCartLocation[0]);
        KLog.i("yy1" + shopCartLocation[1] + " " + itemCartLocation[1]);
        int[] dotViewLocation = new int[2];
        dotView.getLocationInWindow(dotViewLocation);
        //  KLog.(dotViewLocation[0]+"++"+dotViewLocation);
        int xOff = shopCartLocation[0] - itemCartLocation[0];
        int yOff = shopCartLocation[1] - itemCartLocation[1];

        TranslateAnimation translateAnimation = new TranslateAnimation(0, xOff, 0, yOff);
        translateAnimation.setRepeatCount(0);
        translateAnimation.setFillAfter(true);
        translateAnimation.setInterpolator(new AccelerateInterpolator());
        translateAnimation.setDuration(600);
        translateAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                dotView.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                mRlGoodsListRoot.removeView(dotView);
                //抖一抖动画
                CxUtil.actionAndAction(mLayoutGoodsListShopCart, getActivity());
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }

        });

        dotView.startAnimation(translateAnimation);
    }

    private View createAnimationViewer(int[] itemCartLocation, String url) {
        ImageView view = new ImageView(getActivity());
        AutoRelativeLayout.LayoutParams params = new AutoRelativeLayout.LayoutParams(100, 100);
        params.leftMargin = itemCartLocation[0];
        params.topMargin = itemCartLocation[1];
        Glide.with(getActivity())
                .load(API.PIC + url)
                .asBitmap().centerCrop().into(new BitmapImageViewTarget(view) {
            @Override
            protected void setResource(Bitmap resource) {
                RoundedBitmapDrawable circularBitmapDrawable =
                        RoundedBitmapDrawableFactory.create(getActivity().getResources(), resource);
                circularBitmapDrawable.setCircular(true);
                view.setImageDrawable(circularBitmapDrawable);
            }
        });
        view.setLayoutParams(params);
        return view;
    }

    @Override
    public void onLoadShopCartSucceed(int count) {
        mRefreshLoading.setRefreshing(false);
        if (count > 0) {
            mTvMallGoodsShopCartNum.setVisibility(View.VISIBLE);
            if (count >= 100) {
                mTvMallGoodsShopCartNum.setText("99+");
            } else {
                mTvMallGoodsShopCartNum.setText(count + "");
            }
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        bind.unbind();
        mProducts.clear();
        mProducts = null;
        mGoodsAdapter = null;
        mTempCartContanier.clear();
        mTempCartContanier = null;
        mRankViews.clear();
        mRankViews = null;
    }

    @Override
    public void onResume() {
        super.onResume();
        // 如果购物车不为空 购物车的数量不少于0的话 就显示装载购物车数量的容器
        if (Contains.shopCartNum != null && Contains.shopCartNum != 0) {
            mTvMallGoodsShopCartNum.setVisibility(View.VISIBLE);
            if (Contains.shopCartNum >= 100) {
                mTvMallGoodsShopCartNum.setText("99+");
            } else {
                mTvMallGoodsShopCartNum.setText(String.valueOf(Contains.shopCartNum));
            }
        } else {
            // 否则就隐藏
            mTvMallGoodsShopCartNum.setVisibility(View.INVISIBLE);
        }
    }

    @OnClick({R.id.rank_sale, R.id.rank_price, R.id.rank_popular})
    public void onViewClicked(View view) {
        switch (view.getId()) {
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

    private void loadDataFromServer(boolean showSwipeRefresh) {
        if (mRefreshLoading == null) {
            return;
        }
        mRefreshLoading.setRefreshing(showSwipeRefresh);
        mPresenter.loadMallGoodsFromServer(mCurrentProduct, mFeiLei2, mCurrentRankType, mRankViews.get(mCurrentRankType).getCurrentRankMethod()
                , mInType, mCurrentPage, ONE_PAGE_SIZE);
        mPresenter.loadShopCartFromServer();
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