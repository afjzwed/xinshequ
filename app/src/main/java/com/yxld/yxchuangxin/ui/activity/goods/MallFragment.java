package com.yxld.yxchuangxin.ui.activity.goods;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.BitmapImageViewTarget;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshHeader;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.scwang.smartrefresh.layout.listener.SimpleMultiPurposeListener;
import com.socks.library.KLog;
import com.yhy.gvp.adapter.GVPAdapter;
import com.yhy.gvp.listener.OnItemClickListener;
import com.yhy.gvp.widget.GridViewPager;
import com.youth.banner.Banner;
import com.youth.banner.listener.OnBannerListener;
import com.yxld.yxchuangxin.R;
import com.yxld.yxchuangxin.Utils.CxUtil;
import com.yxld.yxchuangxin.Utils.StringUitl;
import com.yxld.yxchuangxin.Utils.ToastUtil;
import com.yxld.yxchuangxin.Utils.UIUtils;
import com.yxld.yxchuangxin.application.AppConfig;
import com.yxld.yxchuangxin.base.BaseEntity;
import com.yxld.yxchuangxin.contain.Contains;
import com.yxld.yxchuangxin.data.api.API;
import com.yxld.yxchuangxin.entity.GoodsKind;
import com.yxld.yxchuangxin.entity.IsNight;
import com.yxld.yxchuangxin.entity.MallClassify;
import com.yxld.yxchuangxin.entity.goods.MallNewProduct;
import com.yxld.yxchuangxin.entity.goods.ShopNewList;
import com.yxld.yxchuangxin.ui.activity.goods.component.DaggerMallComponent;
import com.yxld.yxchuangxin.ui.activity.goods.contract.MallContract;
import com.yxld.yxchuangxin.ui.activity.goods.module.MallModule;
import com.yxld.yxchuangxin.ui.activity.goods.presenter.MallPresenter;
import com.yxld.yxchuangxin.ui.activity.main.HomeActivity;
import com.yxld.yxchuangxin.ui.adapter.goods.MallNormalTypeAdapter;
import com.yxld.yxchuangxin.ui.adapter.goods.TuiJianAdapter;
import com.yxld.yxchuangxin.ui.adapter.goods.XinPingAdapter;
import com.yxld.yxchuangxin.ui.adapter.goods.YejianAdapter;
import com.yxld.yxchuangxin.view.GlideImagerBannerLoader;
import com.yxld.yxchuangxin.view.ImageCycleView;
import com.yxld.yxchuangxin.view.MenuView;
import com.yxld.yxchuangxin.view.TranslucentScrollView;
import com.zhy.autolayout.AutoLinearLayout;
import com.zhy.autolayout.AutoRelativeLayout;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.yxld.yxchuangxin.ui.activity.goods.GoodDetailActivity.KEY_PRODUCT_ID;

/**
 * @author Yuan.Y.Q
 * @Package .ui.activity.goods
 * @Description: $description
 * @date 2017/06/14
 */

public class MallFragment extends MyBaseFragment implements MallContract.View, SwipeRefreshLayout.OnRefreshListener, MenuView.OnMenuItemClickListener {
    public static final int CODE_FOR_GOODS_LIST_SHOP_CART = 0x000001;
    public static final int IN_TYPE_NORMAL_GOODS = 0x000002;//普通菜单进入商品列表
    public static final int IN_TYPE_MIAO_SHA = 0x0000003; //秒杀推荐进入商品列表 //Todo 暂时没有用到，老版的是点击查看所有
    public static final String TO_FEILEI_TYPE = "to_fenlei_type"; //进入分类列表的类型
    public static final String TO_FEILEI_MALLCLASSFY = "to_fenlei_mallclassfy"; //进入分类列表的实体类

    @Inject
    MallPresenter mPresenter;
    @Inject
    TuiJianAdapter tuijianAdapter;
    @Inject
    XinPingAdapter xinPingAdapter;
    @Inject
    MallNormalTypeAdapter mNormalTypeAdapter;
    /**
     * 菜单
     */
    @BindView(R.id.rv_mall_types)
    RecyclerView rvMallNormalTypes;

    @BindView(R.id.menuView)
    MenuView menuView;
    /**
     * 轮播图
     */
    @BindView(R.id.iv_mall_ads)
    ImageCycleView ivMallAds;
    //日间布局
    @BindView(R.id.swipeLayouts)
    SmartRefreshLayout swipeLayouts;
    @BindView(R.id.recycerViewXinPin)
    RecyclerView recycerViewXinPin;
    @BindView(R.id.xinpin_root)
    AutoLinearLayout xinpinRoot;
    @BindView(R.id.rexiao_root)
    AutoLinearLayout rexiaoRoot;
    @BindView(R.id.iv_miaosha)
    ImageView ivMiaosha;
    @BindView(R.id.recycerViewTuiJian)
    RecyclerView recycerViewTuiJian;
    @BindView(R.id.tuijian_root)
    AutoLinearLayout tuijianRoot;
    @BindView(R.id.iv_rexiao1)
    ImageView ivRexiao1;
    @BindView(R.id.iv_rexiao2)
    ImageView ivRexiao2;
    @BindView(R.id.iv_rexiao3)
    ImageView ivRexiao3;
    @BindView(R.id.iv_rexiao4)
    ImageView ivRexiao4;
    @BindView(R.id.iv_rexiao5)
    ImageView ivRexiao5;
    @BindView(R.id.iv_rexiao6)
    ImageView ivRexiao6;
    @BindView(R.id.rexiao_root1)
    AutoRelativeLayout rexiaoRoot1;
    @BindView(R.id.head_market_root)
    AutoRelativeLayout headMarketRoot;
    @BindView(R.id.market_search)
    AutoRelativeLayout marketSearch;
    @BindView(R.id.toolbarBusiness)
    Toolbar toolbar;
    @BindView(R.id.ll_market_activity_root)
    AutoLinearLayout mLlMarketActivityRoot;
    //夜间布局
    @BindView(R.id.iv_mall_ads_two)
    ImageCycleView mIvMallAdsTwo;
    @BindView(R.id.recyclerView_yejian)
    RecyclerView mRecyclerViewYejian;
    @BindView(R.id.swipeLayoutsTwo)
    SmartRefreshLayout mSwipeLayoutsTwo;
    @BindView(R.id.status_layout)
    AutoRelativeLayout mStatusBar;
    @BindView(R.id.gvp_content_b)
    GridViewPager mGvpContentB;

    @BindView(R.id.banner)
    Banner mBanner;
    @BindView(R.id.banner2)
    Banner mBanner2;
    @BindView(R.id.scrollView)
    TranslucentScrollView mScrollView;
    @BindView(R.id.scrollView2)
    TranslucentScrollView mScrollView2;
    /**
     * 跟布局
     */
    private AutoRelativeLayout mRlGoodsListRoot;


    private HomeActivity mActivity;

    /**
     * 分类菜单实体类
     */
    private MallClassify mMallClassify;


    private List<MallNewProduct> yejianList;
    private YejianAdapter yeJianAdapter;
    private boolean isNight = false;                     //是否是夜间的标志

    private int onePageSize = 6;          //夜间商品每页的数量
    private int currentPage = 1;           //夜间商品当前的页数
    private int total = 0;                  //夜间商品的总数
    private ArrayList<String> bannerList;
    private List<GoodsKind.RowsBean.BannerListsBean> mBannerListsBeen;

    @Override

    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.mActivity = (HomeActivity) activity;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_mall, container, false);
        ButterKnife.bind(this, view);
        initStatus();
        //跟布局  --》确定购物车的位置
        mRlGoodsListRoot = (AutoRelativeLayout) getActivity().findViewById(R.id.root_layout);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 3);
        GridLayoutManager gridLayoutManager1 = new GridLayoutManager(getActivity(), 3);
        GridLayoutManager gridLayoutManager3 = new GridLayoutManager(getActivity(), 2, LinearLayoutManager.HORIZONTAL, false);
        GridLayoutManager gridLayoutManager4 = new GridLayoutManager(getActivity(), 3);
        recycerViewXinPin.setLayoutManager(gridLayoutManager);
        recycerViewXinPin.setAdapter(xinPingAdapter);
        recycerViewXinPin.setNestedScrollingEnabled(false);
        recycerViewTuiJian.setLayoutManager(gridLayoutManager1);
        recycerViewTuiJian.setAdapter(tuijianAdapter);
        recycerViewTuiJian.setNestedScrollingEnabled(false);
        rvMallNormalTypes.setNestedScrollingEnabled(false);
        rvMallNormalTypes.setLayoutManager(gridLayoutManager3);
        rvMallNormalTypes.setAdapter(mNormalTypeAdapter);
        if (yejianList == null) {
            yejianList = new ArrayList<>();
        }
        yeJianAdapter = new YejianAdapter(yejianList);
        mRecyclerViewYejian.setLayoutManager(gridLayoutManager4);
        mRecyclerViewYejian.setAdapter(yeJianAdapter);
       mRecyclerViewYejian.setNestedScrollingEnabled(false);
        menuView.setMenuItemOnclickListener(this);
        menuView.setClickable(false);
        menuView.bringToFront();
        loadDataFromServer();
        setAdapterEvent();
        return view;
    }

    private void setTrans() {
        mScrollView2.setTransView(toolbar, getResources().getColor(R.color.color_ff9934), UIUtils.dip2px(50), UIUtils.dip2px(100));
        mScrollView.setTransView(headMarketRoot, getResources().getColor(R.color.color_ff9934), UIUtils.dip2px(50), UIUtils.dip2px(100));
    }

    private void initStatus() {
        toolbar.setTitleTextColor(getResources().getColor(R.color.white));
        toolbar.setBackgroundColor(getResources().getColor(R.color.color_ff9934));
        AutoRelativeLayout.LayoutParams lp = new AutoRelativeLayout.LayoutParams(UIUtils.getDisplayWidth(getActivity()), (int) (UIUtils.getStatusBarHeight(getActivity()) * 3));
        AutoRelativeLayout.LayoutParams lp4 = new AutoRelativeLayout.LayoutParams(UIUtils.getDisplayWidth(getActivity()), (int) (UIUtils.getStatusBarHeight(getActivity()) * 3));
        headMarketRoot.setVisibility(View.VISIBLE);
        headMarketRoot.setLayoutParams(lp4);
        toolbar.setLayoutParams(lp);
        toolbar.setPadding(0, (int) (UIUtils.getStatusBarHeight(getActivity()) * 0.8), 0, 0);
        toolbar.setTitleMarginTop((int) (UIUtils.getStatusBarHeight(getActivity()) * 0.55));
        setTrans();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initRefresh();
    }

    private void initRefresh() {
        swipeLayouts.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                refreshlayout.finishRefresh();
                loadDataFromServer();
            }
        });
        swipeLayouts.setOnMultiPurposeListener(new SimpleMultiPurposeListener() {

            @Override
            public void onHeaderReleasing(RefreshHeader header, float percent, int offset, int bottomHeight, int extendHeight) {
                KLog.e("松");
                // headMarketRoot.setVisibility(View.VISIBLE);
                headMarketRoot.setAlpha(1 - Math.min(percent, 1));
               // marketSearch.setVisibility(View.VISIBLE);
            }

            @Override
            public void onHeaderPulling(RefreshHeader header, float percent, int offset, int headerHeight, int extendHeight) {
                // headMarketRoot.setVisibility(View.GONE);
                KLog.e("拉");
                headMarketRoot.setAlpha(1 - Math.min(percent, 1));
              //  marketSearch.setVisibility(View.GONE);
            }
        });
        mSwipeLayoutsTwo.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                refreshlayout.finishRefresh();
                total = 0;
                currentPage = 1;
                loadDataFromServer();
            }
        });
        mSwipeLayoutsTwo.setOnMultiPurposeListener(new SimpleMultiPurposeListener() {

            @Override
            public void onHeaderReleasing(RefreshHeader header, float percent, int offset, int bottomHeight, int extendHeight) {
                KLog.e("松");
                toolbar.setAlpha(1 - Math.min(percent, 1));
            }

            @Override
            public void onHeaderPulling(RefreshHeader header, float percent, int offset, int headerHeight, int extendHeight) {
                KLog.e("拉");
                toolbar.setAlpha(1 - Math.min(percent, 1));
            }
        });
    }

    private void loadDataFromServer() {
        mPresenter.getFenlei();
        mPresenter.getGoodsKinds();
        mPresenter.isNight(false);
    }

    private void setAdapterEvent() {
        mNormalTypeAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter baseQuickAdapter, View view, int pos) {
                ArrayList<String> list = new ArrayList<String>();
                ArrayList<String> listname = new ArrayList<String>();
                for (int i = 0; i < mNormalTypeAdapter.getData().size(); i++) {
                    list.add(mNormalTypeAdapter.getData().get(i).getId() + "");
                    listname.add(mNormalTypeAdapter.getData().get(i).getFenleiMing());
                }

                ToFeileiActivity(mMallClassify, mNormalTypeAdapter.getData().get(pos).getId() + "", list, listname);

            }
        });
        xinPingAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                Intent intent = new Intent(getActivity(), GoodDetailActivity.class);
                intent.putExtra(KEY_PRODUCT_ID, xinPingAdapter.getData().get(i).getXinpinShangpinId() + "");
                startActivity(intent);
            }
        });
        xinPingAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                if (xinPingAdapter.getData().get(i).getKucun() > xinPingAdapter.getData().get(i).getSelectCount()) {
                    Map<String, String> map = new HashMap<String, String>();
                    map.put("cartIsDajian", xinPingAdapter.getData().get(i).getIsDajian() + "");
                    map.put("cartIsYejian", xinPingAdapter.getData().get(i).getIsYejianShangpin() + "");
                    map.put("cartNum", "1");
                    map.put("cartSpbianhao", xinPingAdapter.getData().get(i).getXinpinShangpinId() + "");
                    map.put("cartSpdanjia", xinPingAdapter.getData().get(i).getShoujia() + "");
                    map.put("cartSpguige", xinPingAdapter.getData().get(i).getGuige());
                    map.put("cartSpmingcheng", xinPingAdapter.getData().get(i).getShangpinMing());
                    map.put("cartSpzhutu", xinPingAdapter.getData().get(i).getZhutu());
                    map.put("uuid", Contains.uuid);
                    mPresenter.addGood2ShopCart(map, view, StringUitl.replaceEndFenHao(xinPingAdapter.getData().get(i).getZhutu()));
                } else {
                    ToastUtil.displayShortToast("库存不足哦");
                }
            }
        });
        tuijianAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                Intent intent = new Intent(getActivity(), GoodDetailActivity.class);
                intent.putExtra(KEY_PRODUCT_ID, tuijianAdapter.getData().get(i).getTuijianShangpinId() + "");
                startActivity(intent);
            }
        });
        tuijianAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                Map<String, String> map = new HashMap<String, String>();
                map.put("cartIsDajian", tuijianAdapter.getData().get(i).getIsDajian() + "");
                map.put("cartIsYejian", tuijianAdapter.getData().get(i).getIsYejianShangpin() + "");
                map.put("cartNum", "1");
                map.put("cartSpbianhao", tuijianAdapter.getData().get(i).getTuijianShangpinId() + "");
                map.put("cartSpdanjia", tuijianAdapter.getData().get(i).getShoujia() + "");
                map.put("cartSpguige", tuijianAdapter.getData().get(i).getGuige());
                map.put("cartSpmingcheng", tuijianAdapter.getData().get(i).getShangpinMing());
                map.put("cartSpzhutu", tuijianAdapter.getData().get(i).getZhutu());
                map.put("uuid", Contains.uuid);
                mPresenter.addGood2ShopCart(map, view, StringUitl.replaceEndFenHao(tuijianAdapter.getData().get(i).getZhutu()));
            }
        });

        yeJianAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                if (yeJianAdapter.getData().get(i).getYejianKucun() > yeJianAdapter.getData().get(i).getSelectCount()) {
                    Map<String, String> map = new HashMap<String, String>();
                    map.put("cartIsDajian", yeJianAdapter.getData().get(i).getShangpinDajian() + "");
                    map.put("cartIsYejian", yeJianAdapter.getData().get(i).getYejianXiaoshou() + "");
                    map.put("cartNum", "1");
                    map.put("cartSpbianhao", yeJianAdapter.getData().get(i).getId() + "");
                    map.put("cartSpdanjia", yeJianAdapter.getData().get(i).getShoujia() + "");
                    map.put("cartSpguige", yeJianAdapter.getData().get(i).getGuige());
                    map.put("cartSpmingcheng", yeJianAdapter.getData().get(i).getShangpinMing());
                    map.put("cartSpzhutu", yeJianAdapter.getData().get(i).getZhutu());
                    map.put("uuid", Contains.uuid);
                    mPresenter.addGood2ShopCart(map, view, StringUitl.replaceEndFenHao(yeJianAdapter.getData().get(i).getZhutu()));
                } else {
                    ToastUtil.displayShortToast("库存不足哦");
                }
            }
        });
        yeJianAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                if (yejianList.size() < onePageSize || yejianList.size() >= total) {
                    yeJianAdapter.loadMoreEnd(false);
                    return;
                }
                mPresenter.getNightGoodList(currentPage, onePageSize);
            }
        }, mRecyclerViewYejian);
        yeJianAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                Intent intent = new Intent(getActivity(), GoodDetailActivity.class);
                intent.putExtra(KEY_PRODUCT_ID, yeJianAdapter.getData().get(i).getId() + "");
                startActivity(intent);
            }
        });
    }

    /**
     * 设置轮播图
     *
     * @param adLunBoImgUrls
     */
    private void setAdEvent(ArrayList<String> adLunBoImgUrls) {
        ivMallAds.setVisibility(View.VISIBLE);
        mIvMallAdsTwo.setVisibility(View.VISIBLE);
        Log.d("geek", "首页轮播图路径urls=" + adLunBoImgUrls.toString());
        ivMallAds.setImageResources(adLunBoImgUrls, new ImageCycleView.ImageCycleViewListener() {
            @Override
            public void onImageClick(int position, View imageView) {
                Intent intent = new Intent(getActivity(), GoodDetailActivity.class);
                if (mBannerListsBeen.get(position).getLunboShangpinId() != 0) {
                    intent.putExtra(KEY_PRODUCT_ID, mBannerListsBeen.get(position).getLunboShangpinId() + "");
                    startActivity(intent);
                }

            }
        }, 0);
        mIvMallAdsTwo.setImageResources(adLunBoImgUrls, new ImageCycleView.ImageCycleViewListener() {
            @Override
            public void onImageClick(int position, View imageView) {
                Intent intent = new Intent(getActivity(), GoodDetailActivity.class);
                if (mBannerListsBeen.get(position).getLunboShangpinId() != 0) {
                    intent.putExtra(KEY_PRODUCT_ID, mBannerListsBeen.get(position).getLunboShangpinId() + "");
                    startActivity(intent);
                }
            }
        }, 0);
    }


    @Override
    public void onLoadFailed() {
    }

    /**
     * 设置分类数据
     *
     * @param mallClassify
     */

    private List<MallClassify.RowsBean> rows;

    @Override
    public void setFenleiAdapter(MallClassify mallClassify) {
        if (mallClassify.getStatus() == 1) {
            MallClassify.RowsBean bean = new MallClassify.RowsBean();
            bean.setId(10000);
            bean.setFenleiMing("所有");
            rows = mallClassify.getRows();
            rows.add(0, bean);
            mNormalTypeAdapter.setNewData(rows);
            //添加分类菜单的所有名字
            mMallClassify = mallClassify;
            setGridView();
        } else {
            onError(mallClassify.getMSG(), mallClassify.getStatus());
        }

    }

    /**
     * 设置商品数据
     *
     * @param goodsKind
     */

    @Override
    public void setShangpin(GoodsKind goodsKind) {
        if (goodsKind.getRows().getXinpinLists().size() == 0) {
            xinpinRoot.setVisibility(View.GONE);
        }
        if (goodsKind.getRows().getDianzhangtuijianLists().size() == 0) {
            tuijianRoot.setVisibility(View.GONE);
        }
        if (goodsKind.getRows().getRexiaoLists().size() == 0) {
            rexiaoRoot.setVisibility(View.GONE);
        }
        xinPingAdapter.setNewData(goodsKind.getRows().getXinpinLists());
        tuijianAdapter.setNewData(goodsKind.getRows().getDianzhangtuijianLists());
        //设置banner图的数据和url
        mBannerListsBeen = goodsKind.getRows().getBannerLists();
        bannerList = new ArrayList<>();
        for (int i = 0; i < goodsKind.getRows().getBannerLists().size(); i++) {
            bannerList.add(i, API.PIC + goodsKind.getRows().getBannerLists().get(i).getLunboHuodongtupian());
        }
        //使用 第三方banner
        initBanner(bannerList);
        //使用原生banner
        //setAdEvent(bannerList);
        setReXiao(goodsKind.getRows().getRexiaoLists());
    }

    private void initBanner(List<String> list) {
        mBanner.setVisibility(View.VISIBLE);
        mBanner2.setVisibility(View.VISIBLE);
        //白天banner
        mBanner.setImageLoader(new GlideImagerBannerLoader());
        //设置图片集合
        mBanner.setImages(list);
        //banner设置方法全部调用完毕时最后调用
        mBanner.start();
        mBanner.setOnBannerListener(new OnBannerListener() {
            @Override
            public void OnBannerClick(int position) {
                Intent intent = new Intent(getActivity(), GoodDetailActivity.class);
                if (mBannerListsBeen.get(position).getLunboShangpinId() != 0) {
                    intent.putExtra(KEY_PRODUCT_ID, mBannerListsBeen.get(position).getLunboShangpinId() + "");
                    startActivity(intent);
                }
            }
        });
        //夜间banner2
        mBanner2.setImageLoader(new GlideImagerBannerLoader());
        //设置图片集合
        mBanner2.setImages(list);
        //banner设置方法全部调用完毕时最后调用
        mBanner2.start();
        mBanner2.setOnBannerListener(new OnBannerListener() {
            @Override
            public void OnBannerClick(int position) {
                Intent intent = new Intent(getActivity(), GoodDetailActivity.class);
                if (mBannerListsBeen.get(position).getLunboShangpinId() != 0) {
                    intent.putExtra(KEY_PRODUCT_ID, mBannerListsBeen.get(position).getLunboShangpinId() + "");
                    startActivity(intent);
                }
            }
        });
    }

    private void setReXiao(ArrayList<GoodsKind.RowsBean.RexiaoListsBean> reXiao) {
        if (reXiao.size() >= 1) {
            Glide.with(this)
                    .load(API.PIC + reXiao.get(0).getRexiaoHuodongtupian())
                    .into(ivRexiao1);
            setReXiaoClick(ivRexiao1, reXiao.get(0).getRexiaoShangpinId());
        } else {
            Glide.with(this)
                    .load(R.mipmap.qidai_big)
                    .into(ivRexiao1);
        }
        if (reXiao.size() >= 2) {
            Glide.with(this)
                    .load(API.PIC + reXiao.get(1).getRexiaoHuodongtupian())
                    .into(ivRexiao2);
            setReXiaoClick(ivRexiao2, reXiao.get(1).getRexiaoShangpinId());
        } else {
            Glide.with(this)
                    .load(R.mipmap.qidai_big)
                    .into(ivRexiao2);
        }
        if (reXiao.size() >= 3) {
            Glide.with(this)
                    .load(API.PIC + reXiao.get(2).getRexiaoHuodongtupian())
                    .into(ivRexiao3);
            setReXiaoClick(ivRexiao3, reXiao.get(2).getRexiaoShangpinId());
        } else {
            Glide.with(this)
                    .load(R.mipmap.qidai_small)
                    .into(ivRexiao3);
        }
        if (reXiao.size() >= 4) {
            Glide.with(this)
                    .load(API.PIC + reXiao.get(3).getRexiaoHuodongtupian())
                    .into(ivRexiao4);
            setReXiaoClick(ivRexiao4, reXiao.get(3).getRexiaoShangpinId());
        } else {
            Glide.with(this)
                    .load(R.mipmap.qidai_small)
                    .into(ivRexiao4);
        }
        if (reXiao.size() >= 5) {
            Glide.with(this)
                    .load(API.PIC + reXiao.get(4).getRexiaoHuodongtupian())
                    .into(ivRexiao5);
            setReXiaoClick(ivRexiao5, reXiao.get(4).getRexiaoShangpinId());
        } else {
            Glide.with(this)
                    .load(R.mipmap.qidai_small)
                    .into(ivRexiao5);
        }
        if (reXiao.size() >= 6) {
            Glide.with(this)
                    .load(API.PIC + reXiao.get(5).getRexiaoHuodongtupian())
                    .into(ivRexiao6);
            setReXiaoClick(ivRexiao6, reXiao.get(5).getRexiaoShangpinId());
        } else {
            Glide.with(this)
                    .load(R.mipmap.qidai_small)
                    .into(ivRexiao6);
        }
    }

    private void setReXiaoClick(View view, int productId) {
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), GoodDetailActivity.class);
                intent.putExtra(KEY_PRODUCT_ID, productId + "");
                startActivity(intent);
            }
        });
    }

    /**
     * 设置购物车数量
     *
     * @param count
     */
    @Override
    public void setShopCartCount(int count) {
        Contains.shopCartNum = count;
        menuView.updateCount(count);
    }

    /**
     * 添加购物车成功
     *
     * @param view
     */
    @Override
    public void addGood2ShopCartSuccess(BaseEntity baseEntity, View view, String imgUrl) {
        if (baseEntity.getStatus() == 1) {
            onItemShopCartImageClick(view, imgUrl);
            mPresenter.getShopCart();
        } else {
            onError(baseEntity.getMSG(), baseEntity.getStatus());
        }
    }

    private static boolean flag = false;//是否显示了一次dialog

    @Override
    public void setIsNight(boolean isFirst, IsNight isNight) {

        if (isNight.getRows().equals("日间")) {
            KLog.i("从夜间转为日间");
            this.isNight = false;
            toolbar.setVisibility(View.GONE);
            mStatusBar.setVisibility(View.VISIBLE);
            mSwipeLayoutsTwo.setVisibility(View.GONE);
            swipeLayouts.setVisibility(View.VISIBLE);
        } else {
            toolbar.setVisibility(View.VISIBLE);
            toolbar.setTitle("欣商城");
            KLog.i("从日间转为夜间");
            this.isNight = true;
            currentPage = 1;
            mStatusBar.setVisibility(View.GONE);
            swipeLayouts.setVisibility(View.GONE);
            mSwipeLayoutsTwo.setVisibility(View.VISIBLE);
            mPresenter.getNightGoodList(currentPage, onePageSize);
            if (this.isNight) {
                if (!flag) {
                    flag = true;
                    showIsNiaghtDialog();
                }
            }
        }
    }

    /**
     * 设置夜间数据
     *
     * @param shopNewList
     */

    @Override
    public void setNightData(ShopNewList shopNewList) {
        yeJianAdapter.loadMoreComplete();
        if (shopNewList.getStatus() == 1) {
            if (currentPage == 1) {
                yejianList.clear();
            }
            yejianList.addAll(shopNewList.getRows());
            total = shopNewList.getTotal();
            if (yejianList.size() < total) {
                currentPage++;
            }
            yeJianAdapter.notifyDataSetChanged();
        } else {
            onError(shopNewList.getMSG(), shopNewList.getStatus());
        }
    }

    @Override
    public void onRefresh() {
        loadDataFromServer();
    }

    @Override
    public void fetchData() {
        swipeLayouts.post(new Runnable() {
            @Override
            public void run() {
                loadDataFromServer();
            }
        });
    }

    @Override
    public void onMenuItemClick(View v) {
        switch (v.getId()) {
            case R.id.menu1:
                // ToFeileiActivity(mMallClassify, "");
                if (menuView.isShow) {
                    menuView.close();
                }
                break;
            case R.id.menu2:
                startActivity(SecondShopCartActivity.class);
                break;
            case R.id.menu3:
                startActivity(MineShopActivity.class);
                break;
            default:
                break;
        }
    }

    @OnClick(R.id.menuView)
    public void onViewClicked() {
        if (menuView.isShow) {
            menuView.close();
        }
    }

    /**
     * 跳转到分类列表
     *
     * @param mallClassify
     * @param type
     */
    private void ToFeileiActivity(MallClassify mallClassify, String type, ArrayList<String> list, ArrayList<String> listname) {
        Intent intent = new Intent(getActivity(), GoodsFenLeiActivity.class);
        intent.putExtra(TO_FEILEI_TYPE, type);
        intent.putStringArrayListExtra("listid", list);
        intent.putStringArrayListExtra("listname", listname);
        intent.putExtra(TO_FEILEI_MALLCLASSFY, mallClassify);
        startActivity(intent);
    }

    /**
     * 点击item中的购物车图标
     *
     * @param view ImageView
     */
    private void onItemShopCartImageClick(View view, String url) {
        //获取被点击的ImageView的当前屏幕位置
        int[] itemCartLocation = new int[2];
        view.getLocationInWindow(itemCartLocation);
        //获取购物车当前的屏幕位置
        int[] shopCartLocation = new int[2];
        menuView.getCountMenu().getLocationInWindow(shopCartLocation);
        //创建需要移动的View
        ImageView dotView = (ImageView) createAnimationViewer(itemCartLocation, url);
        mRlGoodsListRoot.addView(dotView);
        KLog.i("xx1" + shopCartLocation[0] + " " + itemCartLocation[0]);
        KLog.i("yy1" + shopCartLocation[1] + " " + itemCartLocation[1]);
        int[] dotViewLocation = new int[2];
        dotView.getLocationInWindow(dotViewLocation);
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
                CxUtil.actionAndAction(menuView, getActivity());
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }

        });

        dotView.startAnimation(translateAnimation);
    }

    private View createAnimationViewer(int[] itemCartLocation, String url) {
        ImageView view = new ImageView(getActivity());

        AutoRelativeLayout.LayoutParams params = new AutoRelativeLayout.LayoutParams
                (100, 100);
        params.leftMargin = itemCartLocation[0];
        params.topMargin = itemCartLocation[1];
        view.setLayoutParams(params);
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
        return view;
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void reLogin(String relogin) {
        if (relogin.equals("reLogin")) {
            loadDataFromServer();
        }
    }

    @Override
    public void onResume() {
        menuView.close();
        mPresenter.getShopCart();
        super.onResume();
//        loadDataFromServer();
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CODE_FOR_GOODS_LIST_SHOP_CART && resultCode == 0x001) {
            //原本是用来跳转到购物车界面的
//            mActivity.onShopCartClick();
        }
    }

    @Override
    protected void setupFragmentComponent() {
        DaggerMallComponent
                .builder()
                .appComponent(((AppConfig) getActivity().getApplication()).getApplicationComponent())
                .mallModule(new MallModule(this))
                .build()
                .inject(this);

    }

    @Override
    public void setPresenter(MallContract.MallContractPresenter presenter) {
        mPresenter = (MallPresenter) presenter;
    }

    @Override
    protected void initDataFromLocal() {

    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
        mNormalTypeAdapter = null;
        mPresenter.unsubscribe();
        mPresenter = null;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mActivity = null;
    }

    /**
     * 晚上销售时间的dialog
     */
    private void showIsNiaghtDialog() {
        AutoRelativeLayout inflate = (AutoRelativeLayout) getActivity().getLayoutInflater().inflate(R.layout.dialog_isnight, null);

        AlertDialog dialog = new AlertDialog.Builder(getActivity(), R.style.dialog_translucent)
                .setView(inflate)
                .setCancelable(true)
                .setOnDismissListener(new DialogInterface.OnDismissListener() {
                    @Override
                    public void onDismiss(DialogInterface dialog) {
                        dialog.dismiss();
                    }
                })
                .show();
        ImageView viewById = (ImageView) inflate.findViewById(R.id.imageView6);
        viewById.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        //倒计时关闭
//        Observable.interval(0, 1, TimeUnit.SECONDS).take(3)
//                .map(new Function<Long, Long>() {
//                    @Override
//                    public Long apply(@NonNull Long aLong) throws Exception {
//                        return 2 - aLong;
//                    }
//                })
//                .observeOn(AndroidSchedulers.mainThread())//发射用的是observeOn
//                .doOnSubscribe(new Consumer<Disposable>() {
//                    @Override
//                    public void accept(@NonNull Disposable disposable) throws Exception {
//                        KLog.i("1");
//                    }
//                })
//                .subscribeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Observer<Long>() {
//                    @Override
//                    public void onSubscribe(@io.reactivex.annotations.NonNull Disposable d) {
//
//                    }
//
//                    @Override
//                    public void onNext(@io.reactivex.annotations.NonNull Long aLong) {
//
//                    }
//
//                    @Override
//                    public void onError(@io.reactivex.annotations.NonNull Throwable e) {
//
//                    }
//
//                    @Override
//                    public void onComplete() {
//                        if (dialog.isShowing()) {
//                            dialog.dismiss();
//                        }
//                    }
//                });

    }

    @OnClick({R.id.market_search})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.market_search:
                Intent intent = new Intent(getActivity(), SearchActivity.class);
                intent.putExtra(SearchActivity.KEY_IN_TYPE, SearchActivity.IN_TYPE_MAIN);
                startActivity(intent);
                break;
            default:
                break;
        }
    }

    private void setGridView() {
        GridGVPAdapter adapter = new GridGVPAdapter();
        mGvpContentB.setGVPAdapter(adapter);
        mGvpContentB.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position) {
                ArrayList<String> list = new ArrayList<String>();
                ArrayList<String> listname = new ArrayList<String>();
                for (int i = 0; i < mNormalTypeAdapter.getData().size(); i++) {
                    list.add(mNormalTypeAdapter.getData().get(i).getId() + "");
                    listname.add(mNormalTypeAdapter.getData().get(i).getFenleiMing());
                }

                ToFeileiActivity(mMallClassify, mNormalTypeAdapter.getData().get(position).getId() + "", list, listname);

            }
        });
    }


    class GridGVPAdapter implements GVPAdapter {
        @Override
        public int getCount() {
            return rows.size();
        }

        @Override
        public Object getItem(int position) {
            return rows.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View inflate = LayoutInflater.from(getActivity()).inflate(R.layout.item_mall_normal_types, null);
            TextView type = (TextView) inflate.findViewById(R.id.tv_mall_normal_type);
            type.setText(rows.get(position).getFenleiMing());
            ImageView iv = (ImageView) inflate.findViewById(R.id.iv_mall_normal_type);
            if ("所有".equals(type.getText())) {
                Glide.with(getActivity())
                        .load(R.mipmap.icon_mall_all)
                        .into(iv);
            } else {

                Glide.with(getActivity())
                        .load(API.PIC + rows.get(position).getFenleiTubiao())
                        .into(iv);
            }
            return inflate;
        }
    }
}