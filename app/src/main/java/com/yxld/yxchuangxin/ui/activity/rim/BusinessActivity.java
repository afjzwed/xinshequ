package com.yxld.yxchuangxin.ui.activity.rim;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Bitmap;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.TextAppearanceSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.BitmapImageViewTarget;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.socks.library.KLog;
import com.yxld.yxchuangxin.R;
import com.yxld.yxchuangxin.Utils.PopWindowUtil;
import com.yxld.yxchuangxin.Utils.StringUitl;
import com.yxld.yxchuangxin.Utils.ToastUtil;
import com.yxld.yxchuangxin.application.AppConfig;
import com.yxld.yxchuangxin.base.BaseActivity;
import com.yxld.yxchuangxin.base.BaseEntity;
import com.yxld.yxchuangxin.contain.Contains;
import com.yxld.yxchuangxin.data.api.API;
import com.yxld.yxchuangxin.entity.CxwyBusinessInfo;
import com.yxld.yxchuangxin.entity.CxwyProductInfo;
import com.yxld.yxchuangxin.entity.ShopCarList;
import com.yxld.yxchuangxin.ui.activity.rim.component.DaggerBusinessComponent;
import com.yxld.yxchuangxin.ui.activity.rim.contract.BusinessContract;
import com.yxld.yxchuangxin.ui.activity.rim.module.BusinessModule;
import com.yxld.yxchuangxin.ui.activity.rim.presenter.BusinessPresenter;
import com.yxld.yxchuangxin.ui.adapter.rim.MyChooseProductAdapter;
import com.yxld.yxchuangxin.ui.adapter.rim.RimGoodTitleAdapter;
import com.yxld.yxchuangxin.view.ConfirmDialog;
import com.yxld.yxchuangxin.view.CustomPopWindow;
import com.yxld.yxchuangxin.view.RimSpDetailDialog;
import com.zhy.autolayout.AutoFrameLayout;
import com.zhy.autolayout.AutoLinearLayout;
import com.zhy.autolayout.AutoRelativeLayout;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author hu
 * @Package com.yxld.yxchuangxin.ui.activity.rim
 * @Description: $description 商家商品界面
 * @date 2017/06/17
 */

public class BusinessActivity extends BaseActivity implements BusinessContract.View, View.OnClickListener,
        RimGoodListFragment.OnItemClickListener, RimGoodListFragment.OnDialogShowListener {

    @Inject
    BusinessPresenter mPresenter;
    @BindView(R.id.bussiness_image)
    ImageView bussinessImage;
    @BindView(R.id.bussiness_name)
    TextView bussinessName;
    @BindView(R.id.ratingBar)
    RatingBar ratingBar;
    @BindView(R.id.bussiness_fraction)
    TextView bussinessFraction;
    @BindView(R.id.iv_arrow)
    ImageView ivArrow;
    @BindView(R.id.tv_sale)
    TextView tvSale;
    @BindView(R.id.iv_sale)
    ImageView ivSale;
    @BindView(R.id.head_layout)
    AutoLinearLayout headLayout;
    @BindView(R.id.tv1)
    TextView tv1;
    @BindView(R.id.bt_push_product)
    Button btPushProduct;
    @BindView(R.id.shopping_cart_bottom)
    AutoLinearLayout shoppingCartBottom;
    @BindView(R.id.shopping_cart)
    ImageView shoppingCart;
    @BindView(R.id.tv_product_count)
    TextView tvProductCount;
    @BindView(R.id.shopping_cart_layout)
    AutoRelativeLayout shoppingCartLayout;
    @BindView(R.id.recycler_title)
    RecyclerView mRecyclerTitle;
    @BindView(R.id.fragment_container)
    AutoFrameLayout mFragmentContainer;
    AutoRelativeLayout mRlLayout;
    @BindView(R.id.textView8)
    TextView mTextView8;
    @BindView(R.id.all_business_name)
    AutoRelativeLayout mAllBusinessName;
    private int businessProduceType; //1 商家2 服务
    private RimGoodTitleAdapter adapter;
    private CxwyBusinessInfo mBusinessInfo;//商家数据，用于展示和传递下级页面数据
    private double score;//商家星级
    private double startMoney;//商家起送金额

    private List<ShopCarList.ShopCarBean> mShopCarBeanList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        needFront = true;
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initView() {
        setContentView(R.layout.activity_business);
        ButterKnife.bind(this);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mRlLayout = (AutoRelativeLayout) findViewById(R.id.root_layout);
        btPushProduct.setOnClickListener(this);
        ivSale.setOnClickListener(this);
        tvSale.setOnClickListener(this);
        ivArrow.setOnClickListener(this);
        shoppingCart.setOnClickListener(this);
        mAllBusinessName.setOnClickListener(this);
    }

    @Override
    protected void initData() {
//        //蒙层效果
//        Glide.with(this).load(R.mipmap.icon_camera).bitmapTransform(new BlurTransformation
// (this, 100))
//                .into(new SimpleTarget<GlideDrawable>() {
//                    @TargetApi(Build.VERSION_CODES.KITKAT)
//                    @Override
//                    public void onResourceReady(GlideDrawable resource, GlideAnimation<? super
//                            GlideDrawable> glideAnimation) {
//                        headLayout.setBackground(resource);
//                    }
//                });
        businessProduceType = getIntent().getIntExtra("businessProduceType", 0);
        if (businessProduceType == 1) {
            btPushProduct.setText("立即购买");
        } else if (businessProduceType == 2) {
            btPushProduct.setText("立即预约");
        }
        if ("modify".equals(getIntent().getStringExtra("flag"))) {
            btPushProduct.setText("立即修改");
        }
        if (mShopCarBeanList == null) {
            mShopCarBeanList = new ArrayList<>();
        }
        Map<String, String> map = new HashMap<>();    //?businessNumber=%1$s&uuId=%2$s
        map.put("businessNumber", getIntent().getStringExtra("businessNumber"));
        map.put("uuId", Contains.uuid);
        mPresenter.getBusinessInfo(map);
        loadDataFromServers();
    }

    private void loadDataFromServers() {
        Map<String, String> map = new HashMap<>();    //?businessNumber=%1$s&uuId=%2$s
        map.put("businessNumber", getIntent().getStringExtra("businessNumber"));
        map.put("uuId", Contains.uuid);
        mPresenter.getShopCarList(map);
    }

    @Override
    protected void setupActivityComponent() {
        DaggerBusinessComponent.builder().appComponent(((AppConfig) getApplication()).getApplicationComponent())
                .businessModule(new BusinessModule(this)).build().inject(this);
    }

    @Override
    public void setPresenter(BusinessContract.BusinessContractPresenter presenter) {
        mPresenter = (BusinessPresenter) presenter;
    }


    @Override
    public void setFragmentAdapter(CxwyBusinessInfo info) {
        if (info.getStatus() == 1) {
            mBusinessInfo = info;
            score = info.getData().getScore();
            bussinessFraction.setText(score + "分");
            ratingBar.setRating((float) score);
            startMoney = info.getData().getStartMoney();
            bussinessName.setText(info.getData().getBusiness().getBusinessName());
            Glide.with(this).load(API.PIC + info.getData().getBusiness().getBusinessLogo()).into(bussinessImage);
            setPushBtn();
//        //设置title
//        for (int i = 0; i < info.getData().getProductClassify().size(); i++) {
//            titles.add(info.getData().getProductClassify().get(i).getClassifyName());
//        }
//        FragmentPagerAdapter mAdapter = new FragmentPagerAdapter(getSupportFragmentManager()) {
//
//            @Override
//            public Fragment getItem(int position) {
//                GoodListFragment goodListFragment = new GoodListFragment();
//                Bundle args = new Bundle();
//                args.putString("businessNumber", info.getData().getBusiness().getBusinessNumber
// ());
//                args.putString("classifyId", info.getData().getProductClassify().get(position)
// .getClassifyId() + "");
//                goodListFragment.setArguments(args);
//                return goodListFragment;
//            }
//
//            @Override
//            public int getCount() {
//                return titles.size();
//            }
//
//            @Override
//            public CharSequence getPageTitle(int position) {
//                return titles.get(position);
//            }
//
//        };
//        viewPager.setAdapter(mAdapter);
//        tabLayout.setupWithViewPager(viewPager);
//        tabLayout.setVisibility(View.VISIBLE);
            adapter = new RimGoodTitleAdapter(info.getData().getProductClassify());
            mRecyclerTitle.setLayoutManager(new LinearLayoutManager(this));
            mRecyclerTitle.setAdapter(adapter);
            adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                    //表示点击的是同一个条目不做处理
                    if (adapter.getData().get(i).isSelect()) {
                        return;
                    }
                    //重置bg选中的条目
                    setBgSelect(i);
                    FragmentManager manager = getSupportFragmentManager();
                    FragmentTransaction ft = manager.beginTransaction();
                    RimGoodListFragment leftFragment = new RimGoodListFragment();
                    Bundle args = new Bundle();
                    args.putString("businessNumber", info.getData().getBusiness().getBusinessNumber());
                    args.putString("classifyId", info.getData().getProductClassify().get(i).getClassifyId() + "");
                    leftFragment.setArguments(args);
                    ft.replace(R.id.fragment_container, leftFragment).commit();
                }
            });
            if (info.getData().getProductClassify().size() > 0) {
                FragmentManager manager = getSupportFragmentManager();
                FragmentTransaction ft = manager.beginTransaction();
                RimGoodListFragment leftFragment = new RimGoodListFragment();
                Bundle args = new Bundle();
                args.putString("businessNumber", info.getData().getBusiness().getBusinessNumber());
                args.putString("classifyId", info.getData().getProductClassify().get(0).getClassifyId() + "");
                leftFragment.setArguments(args);
                ft.replace(R.id.fragment_container, leftFragment).commit();
                setBgSelect(0);
            }
            //初始化活动
            initSaleAdapter(info, 0);
        } else {
            onError(info.msg, info.status);
        }
    }

    /**
     * 设置被点击的条目改变背景色
     *
     * @param position
     */
    private void setBgSelect(int position) {
        for (int i = 0; i < adapter.getData().size(); i++) {
            if (position == i) {
                adapter.getData().get(i).setSelect(true);
            } else {
                adapter.getData().get(i).setSelect(false);
            }
        }
        adapter.notifyDataSetChanged();

    }

    //设置活动
    @Override
    public void setSaleAdapter(CxwyBusinessInfo data, int flag) {
        //判空，防止因为没有数据返回而报的空指针
        if (data.getData().getActivity().size() == 0) {
            KLog.i("活动为0");
            tvSale.setText("共" + 0 + "个活动");
            mTextView8.setVisibility(View.INVISIBLE);
            return;
        }
        tvSale.setText("共" + data.getData().getActivity().size() + "个活动");
        StringBuffer s = new StringBuffer();
        for (int i = 0; i < data.getData().getActivity().size(); i++) {
            if (i == data.getData().getActivity().size() - 1 || data.getData().getActivity().size() == 1) {
                s.append(data.getData().getActivity().get(i).getActivityName());
            } else {
                s.append(data.getData().getActivity().get(i).getActivityName() + ",");
            }
        }
        mTextView8.setVisibility(View.VISIBLE);
        mTextView8.setText(s.toString());
//        recyclerViewSale.setLayoutManager(new LinearLayoutManager(this));
//        List<CxwyBusinessInfo.DataBean.ActivityBean> list = new ArrayList<>();
//        if (flag == 0) {
//            list.add(data.getData().getActivity().get(0));
//            ObjectAnimator.ofFloat(ivSale, "rotation", 0, 180).setDuration(200).start();
//        } else {
//            list.addAll(data.getData().getActivity());
//            ObjectAnimator.ofFloat(ivSale, "rotation", 180, 360).setDuration(200).start();
//        }
//        SaleAdapter saleAdapter = new SaleAdapter(list);
//        recyclerViewSale.setAdapter(saleAdapter);
    }

    @Override
    public void setProductCount(int allItem, CharSequence money) {
        productItemCountChangeAnimation(tvProductCount);
        tvProductCount.setVisibility(View.VISIBLE);
        tvProductCount.setText(allItem + "");
        tv1.setText(money);
    }

    @Override
    public void setProductOver99(CharSequence money) {
        tvProductCount.setVisibility(View.VISIBLE);
        tvProductCount.setText("99+");
        tv1.setText(money);
    }

    @Override
    public void setProductCountNone(CharSequence money) {
        tv1.setText(money);
        tvProductCount.setVisibility(View.INVISIBLE);
        tvProductCount.setText("");
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
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.shopping_cart:
                //点击购物车
                showManagePop(shoppingCart);
                break;
            case R.id.bt_push_product:
                if (allItem > 0) {
                    if (businessProduceType == 1) {
                        //商家
                    } else {
                        //服务
                    }
                    if (getIntent().getStringExtra("flag").equals("modify")) {
//                        修改订单的逻辑
                        if (allPrice >= startMoney) {
                            updateOrder();
                        } else {
                            ToastUtil.show(BusinessActivity.this, "很抱歉，商品价格低于起送金额" + startMoney);
                        }
                    } else {
                        //先判断起送金额再跳转到生成订单的页面
                        if (allPrice >= startMoney) {
                            String businessNumber = getIntent().getStringExtra("businessNumber");
                            startBusinessPushProductActivity(businessNumber);
                        } else {
                            ToastUtil.show(BusinessActivity.this, "很抱歉，商品价格低于起送金额" + startMoney);
                        }
                    }
//                    if (isShowManagePop) {
//                        showOrOffManagePop();
//                    }
                } else {
                    ToastUtil.show(BusinessActivity.this, "至少选择一件商品");
                }
                break;
            case R.id.tv_sale:
            case R.id.iv_sale:
                controlleSaleCount();
                break;
            case R.id.iv_arrow:
            case R.id.all_business_name:
                CxwyBusinessInfo.DataBean.BusinessBean business = mBusinessInfo.getData().getBusiness();
                startRimShopDetailActivity(business, score);
                break;
            default:
                break;
        }
    }

    /**
     * 修改订单上传商品信息
     */
    private void updateOrder() {
        Map<String, String> map = new HashMap<>();
        for (int i = 0; i < mShopCarBeanList.size(); i++) {
            // 拼接商品信息
            map.put("list[" + i + "].orderDetailsBusinessPrice", mShopCarBeanList.get(i).getProductPrice() + "");
            map.put("list[" + i + "].orderDetailsPreferentialPrice", mShopCarBeanList.get(i)
                    .getProductPreferentialPrice() + "");
            map.put("list[" + i + "].orderDetailsProductId", mShopCarBeanList.get(i).getProductId() + "");
            map.put("list[" + i + "].orderDetailsProductImg", mShopCarBeanList.get(i).getProductImage() + "");
            map.put("list[" + i + "].orderDetailsProductName", mShopCarBeanList.get(i).getProductName() + "");
            map.put("list[" + i + "].orderDetailsProductNumber", mShopCarBeanList.get(i).getCartNum() + "");
            map.put("list[" + i + "].orderDetailsProductPrice", mShopCarBeanList.get(i).getProductPrice() + "");
            map.put("list[" + i + "].orderDetailsBusinessNumber", mShopCarBeanList.get(i).getProductBusinessNumber()
                    + "");

        }
        map.put("uuId", Contains.uuid);
        map.put("orderId", getIntent().getStringExtra("orderNumber"));
        mPresenter.updataOrder(map);
    }

    private boolean isShowOneActivity = false;

    public void controlleSaleCount() {
        if (isShowOneActivity) {
            isShowOneActivity = false;
            initSaleAdapter(mBusinessInfo, 1);
        } else {
            isShowOneActivity = true;
            initSaleAdapter(mBusinessInfo, 0);
        }
    }

    /**
     * 展示打折信息的适配器
     *
     * @param data 商家的信息
     * @param flag 0表示只展示一个活动，非0表示展示多个活动
     */
    public void initSaleAdapter(CxwyBusinessInfo data, int flag) {

        setSaleAdapter(data, flag);
    }

    /**
     * 跳转到提交订单界面
     *
     * @param businessNumber
     */
    public void startBusinessPushProductActivity(String businessNumber) {
        Intent intent = new Intent(this, BusinessPushProductActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString("businessNumber", businessNumber);
        bundle.putInt("businessProduceType", businessProduceType);
        bundle.putParcelableArrayList("ShopCarBeanList", (ArrayList<? extends Parcelable>) mShopCarBeanList);
        Contains.mBusinessInfo = mBusinessInfo;
        intent.putExtras(bundle);
        this.startActivityForResult(intent, 0);
    }

    /**
     * 跳转到商家首页界面
     *
     * @param business
     * @param score
     */
    public void startRimShopDetailActivity(CxwyBusinessInfo.DataBean.BusinessBean business, double score) {
        Intent intent = new Intent(this, RimShopDetailActivity.class);
        Bundle bundle = new Bundle();
        bundle.putDouble("score", score);
        bundle.putSerializable("business", business);
        intent.putExtras(bundle);
        this.startActivity(intent);
    }

    /**
     * 显示详情dialog
     */
    private RimSpDetailDialog dialog;

    private void showGoogDetailPop1(CxwyProductInfo.DataBean pro) {
        dialog = new RimSpDetailDialog(this, pro);
        dialog.setCanceledOnTouchOutside(true);
        dialog.setAddListener(new RimSpDetailDialog.addListener() {
            @Override
            public void add(View view, String url) {
                onProductClick(pro, view, url);
            }
        });
        dialog.show();
    }

    /**
     * fragment中Recyclerview的条目点击之后的回调方法
     * 条目中的+ 和详情中的+
     * 点击添加商品按钮
     *
     * @param data
     */
    @Override
    public synchronized void onProductClick(final CxwyProductInfo.DataBean data, View view, String url) {
        Map<String, String> map = new HashMap<>();
        map.put("uuId", Contains.uuid);
        map.put("productId", data.getProductId() + "");
        map.put("businessNumber", data.getProductBusinessNumber());
        mPresenter.addShopCar(map, view, url);

    }

    /**
     * 动画。数量改变时调用
     *
     * @param v
     */
    private void productItemCountChangeAnimation(View v) {
        ObjectAnimator x = ObjectAnimator.ofFloat(v, "scaleX", 1.0f, 1.2f, 1.0f);
        ObjectAnimator y = ObjectAnimator.ofFloat(v, "scaleY", 1.0f, 1.2f, 1.0f);
        AnimatorSet set = new AnimatorSet();
        set.play(x).with(y);
        set.setDuration(200);
        set.start();
    }

    /**
     * 打开或者关闭预约栏的方法
     */
    private MyChooseProductAdapter mMyChooseProductAdapter;

    private void showManagePop(View v) {
        if (mShopCarBeanList == null) {
            ToastUtil.showShort("请求购物车失败");
        }
        if (allItem == 0 || mShopCarBeanList != null && mShopCarBeanList.size() == 0) {
            ToastUtil.showShort("当前没有任何商品");
            return;
        }
        View view = LayoutInflater.from(this).inflate(R.layout.pop_manage_product, null);
        AutoLinearLayout ll_content = (AutoLinearLayout) view.findViewById(R.id.pop_manage_product);
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        mMyChooseProductAdapter = new MyChooseProductAdapter(mShopCarBeanList, this);
        mMyChooseProductAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                Map<String, String> map = new HashMap<String, String>();
                switch (view.getId()) {
                    case R.id.tv_product_add:
                        map.put("uuId", Contains.uuid);
                        map.put("type", "1");
                        map.put("productId", mShopCarBeanList.get(position).getProductId() + "");
                        map.put("businessNumber", mShopCarBeanList.get(position).getProductBusinessNumber());
                        mPresenter.updataShopCar(map);
                        break;
                    case R.id.tv_product_reduce:
                        if (mShopCarBeanList.get(position).getCartNum() <= 1) {
                            return;
                        }
                        map.put("uuId", Contains.uuid);
                        map.put("type", "2");
                        map.put("productId", mShopCarBeanList.get(position).getProductId() + "");
                        map.put("businessNumber", mShopCarBeanList.get(position).getProductBusinessNumber());
                        mPresenter.updataShopCar(map);
                        break;
                    case R.id.iv_product_delet:
                        ConfirmDialog.showDialog(BusinessActivity.this, "欣提示", "是否删除选中商品？", new ConfirmDialog
                                .OnConfirmListener() {
                            @Override
                            public void onCancel() {

                            }

                            @Override
                            public void onConfirm() {
                                map.put("uuId", Contains.uuid);
                                map.put("shangpinIds[]", mShopCarBeanList.get(position).getProductId() + "");
                                map.put("businessNumber", mShopCarBeanList.get(position).getProductBusinessNumber());
                                mPresenter.deleteShopCar(map);
                            }
                        });
                        break;
                }
            }
        });
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.getItemAnimator().setRemoveDuration(1000);
        recyclerView.setAdapter(mMyChooseProductAdapter);
        TextView tv_release = (TextView) view.findViewById(R.id.tv_release);
        tv_release.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mShopCarBeanList == null || mShopCarBeanList.size() <= 0) {
                    return;
                }
                ConfirmDialog.showDialog(BusinessActivity.this, "欣提示", "是否清空全部商品？", new ConfirmDialog
                        .OnConfirmListener() {
                    @Override
                    public void onCancel() {

                    }

                    @Override
                    public void onConfirm() {
                        StringBuilder sb = new StringBuilder();
                        for (ShopCarList.ShopCarBean id : mShopCarBeanList) {
                            sb.append(id.getProductId()).append(",");
                        }
                        sb.delete(sb.lastIndexOf(","), sb.length());
                        KLog.i("购物车商品id" + sb.toString());
                        Map<String, String> map = new HashMap<String, String>();
                        map.put("uuId", Contains.uuid);
                        map.put("shangpinIds[]", sb.toString());
                        map.put("businessNumber", mShopCarBeanList.get(0).getProductBusinessNumber());
                        mPresenter.deleteShopCar(map);
                    }
                });
                // mPresenter.releaseAllProduct();

            }
        });
        TextView textView = (TextView) view.findViewById(R.id.tv_manage_product_title);
        //根据商品和服务显示不同的购物车标题
        if (businessProduceType == 1) {
            textView.setText("");
        } else if (businessProduceType == 2) {
            textView.setText("待工作人员上门计价支付");
        }
        PopWindowUtil.showFullScreenPopWindow(this, v, view, ll_content);
    }

    @Override
    public void showManageAdapter(MyChooseProductAdapter adapter, RecyclerView recyclerView) {
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.getItemAnimator().setRemoveDuration(1000);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void addShopCarSuccess(BaseEntity baseEntity, View view, String url) {
        if (baseEntity.getStatus() == 1) {
            onItemShopCartImageClick(view, url);
            loadDataFromServers();
        } else {
            onError(baseEntity.msg, baseEntity.status);
        }
    }

    @Override
    public void getShopCarListSuccess(ShopCarList baseEntity) {
        if (baseEntity.status == 1) {
            caculateAllPrice(baseEntity);
            mShopCarBeanList.clear();
            mShopCarBeanList.addAll(baseEntity.getData());
            if (mMyChooseProductAdapter != null) {
                mMyChooseProductAdapter.notifyDataSetChanged();
            }
        } else {
            onError(baseEntity.msg, baseEntity.status);
        }
    }

    @Override
    public void deleteShopCarSuccess(BaseEntity baseEntity) {
        if (baseEntity.status == 1) {
            loadDataFromServers();
        } else {
            onError(baseEntity.msg, baseEntity.status);
        }
    }

    @Override
    public void updataShopCarSuccess(BaseEntity baseEntity) {
        if (baseEntity.status == 1) {
            loadDataFromServers();
        } else {
            onError(baseEntity.msg, baseEntity.status);
        }
    }

    @Override
    public void updataOrderSuccess(BaseEntity baseEntity) {
        if (baseEntity.status == 1) {
            Intent intent = new Intent(BusinessActivity.this, BusinessOrderDetailActivity.class);
            intent.putExtra("orderNumber", getIntent().getStringExtra("orderNumber"));
            intent.putExtra("businessNumber", getIntent().getStringExtra("businessNumber"));
            intent.putExtra("businessProduceType", businessProduceType);
            startActivity(intent);
            AppConfig.getInstance().mAppActivityManager.finishActivity(BusinessOrderDetailActivity.class);
            finish();
        } else {
            onError(baseEntity.msg, baseEntity.status);
        }
    }

    /**
     * 计算所有的商品的价格，并且展示
     */
    private double allPrice = 0;
    private int allItem = 0;

    private void caculateAllPrice(ShopCarList data) {
        allItem = data.getTotal();
        allPrice = 0;
        for (int j = 0; j < data.getData().size(); j++) {
            allPrice += data.getData().get(j).getProductPreferentialPrice() * data.getData().get(j).getCartNum();
        }
//        if (allItem != 0) {
        String str1 = "预计: ";
        String str2 = "¥ ";
        String str3 = StringUitl.get2xiaoshu(allPrice) + "";
        String str4 = "";
        if (businessProduceType == 1) {
            str4 = "";
        } else if (businessProduceType == 2) {
            str4 = "\n待工作人员上门计价支付";
        }

        SpannableStringBuilder money = new SpannableStringBuilder(str1 + str2 + str3 + str4);
        ColorStateList black = ColorStateList.valueOf(ContextCompat.getColor(this, R.color.cb_textcolor_checked));
        ColorStateList red = ColorStateList.valueOf(ContextCompat.getColor(this, R.color.business_red));
        ColorStateList gary = ColorStateList.valueOf(ContextCompat.getColor(this, R.color.business_gary));

        money.setSpan(new TextAppearanceSpan(null, 0, 40, black, black), 0, str1.length(), Spannable
                .SPAN_EXCLUSIVE_INCLUSIVE);
        money.setSpan(new TextAppearanceSpan(null, 0, 40, red, red), str1.length(), str1.length() + str2.length(),
                Spannable.SPAN_EXCLUSIVE_INCLUSIVE);
        money.setSpan(new TextAppearanceSpan(null, 0, 40, red, red), str1.length() + str2.length(), str1.length() +
                str2.length() + str3.length(), Spannable.SPAN_EXCLUSIVE_INCLUSIVE);
        money.setSpan(new TextAppearanceSpan(null, 0, 30, gary, gary), str1.length() + str2.length() + str3.length(),
                str1.length() + str2.length() + str3.length() + str4.length(), Spannable.SPAN_EXCLUSIVE_INCLUSIVE);
        if (allItem > 99) {
            setProductOver99(money);
        } else if (allItem == 0) {
            setProductCountNone(money);
        } else {
            setProductCount(allItem, money);
        }
//        } else {
//            setProductCountNone();
//        }

        setPushBtn();
    }

    /**
     * 设置提交按钮
     */
    private void setPushBtn() {
        if (startMoney > 0 && allPrice < startMoney) {
            btPushProduct.setEnabled(false);
            btPushProduct.setText("¥ " + StringUitl.get2xiaoshu(startMoney) + "起送");
            btPushProduct.setBackgroundColor(getResources().getColor(R.color.color_797979));
        } else {
            btPushProduct.setEnabled(true);
            btPushProduct.setBackgroundColor(getResources().getColor(R.color.color_main_color_1));
            if (businessProduceType == 1) {
                btPushProduct.setText("立即购买");
            } else if (businessProduceType == 2) {
                btPushProduct.setText("立即预约");
            }
            if ("modify".equals(getIntent().getStringExtra("flag"))) {
                btPushProduct.setText("立即修改");
            }
        }
    }


    @Override
    public void onBackPressed() {
        if (CustomPopWindow.onBackPressed()) {
            return;
        }
        super.onBackPressed();
    }

    /**
     * 商品列表中点击商品弹出dialog的回调
     *
     * @param data
     */
    @Override
    public void showDialog(CxwyProductInfo.DataBean data) {
        showGoogDetailPop1(data);//显示商品详情弹窗
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 0 && resultCode == 2) {
            shoppingCart.performClick();
            // btPushProduct.setText("立即修改");
        }
    }

    /**
     * 创建动画
     * 贝塞尔曲线中间过程的点的坐标
     */
    private float[] mCurrentPosition = new float[2];
    PathMeasure mPathMeasure;

    private void onItemShopCartImageClick(View iv, String url) {
        //一、创造出执行动画的主题---text
        //代码new一个imageview，图片资源是上面的imageview的图片
        // (这个图片就是执行动画的图片，从开始位置出发，经过一个抛物线（贝塞尔曲线），移动到购物车里)
        //得到商品图片的坐标（用于计算动画开始的坐标）
        int startLoc[] = new int[2];
        iv.getLocationOnScreen(startLoc);
        //确定商品详情dialog view 的位置
        if (dialog != null) {
            dialog.dismiss();
        }
//        二、计算动画开始/结束点的坐标的准备工作
        //得到父布局的起始点坐标（用于辅助计算动画开始/结束时的点的坐标）
        int[] parentLocation = new int[2];
        mRlLayout.getLocationInWindow(parentLocation);


        //得到购物车图片的坐标(用于计算动画结束后的坐标)
        int endLoc[] = new int[2];
        shoppingCart.getLocationInWindow(endLoc);
        ImageView dotView = (ImageView) createAnimationViewer(url);
        mRlLayout.addView(dotView);
        //        三、正式开始计算动画开始/结束的坐标
        //开始掉落的商品的起始点：商品起始点-父布局起始点+该商品图片的一半
//        float startX = startLoc[0] - parentLocation[0] + iv.getWidth() / 2;
//        float startY = startLoc[1] - parentLocation[1] + iv.getHeight() / 2;
        float startX = startLoc[0];
        float startY = startLoc[1];
        //商品掉落后的终点坐标：购物车起始点-父布局起始点+购物车图片的1/5
        float toX = endLoc[0] - parentLocation[0] + shoppingCart.getWidth() / 5;
        float toY = endLoc[1] - parentLocation[1];

//        四、计算中间动画的插值坐标（贝塞尔曲线）（其实就是用贝塞尔曲线来完成起终点的过程）
        //开始绘制贝塞尔曲线
        Path path = new Path();
        //移动到起始点（贝塞尔曲线的起点）
        path.moveTo(startX, startY);
        //使用二次萨贝尔曲线：注意第一个起始坐标越大，贝塞尔曲线的横向距离就会越大，一般按照下面的式子取即可
        path.quadTo((startX + toX) / 2, startY, toX, toY);
        //mPathMeasure用来计算贝塞尔曲线的曲线长度和贝塞尔曲线中间插值的坐标，
        // 如果是true，path会形成一个闭环
        mPathMeasure = new PathMeasure(path, false);

        //★★★属性动画实现（从0到贝塞尔曲线的长度之间进行插值计算，获取中间过程的距离值）
        ValueAnimator valueAnimator = ValueAnimator.ofFloat(0, mPathMeasure.getLength());
        valueAnimator.setDuration(600);
        // 匀速线性插值器
        valueAnimator.setInterpolator(new LinearInterpolator());
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                // 当插值计算进行时，获取中间的每个值，
                // 这里这个值是中间过程中的曲线长度（下面根据这个值来得出中间点的坐标值）
                float value = (Float) animation.getAnimatedValue();
                // ★★★★★获取当前点坐标封装到mCurrentPosition
                // boolean getPosTan(float distance, float[] pos, float[] tan) ：
                // 传入一个距离distance(0<=distance<=getLength())，然后会计算当前距
                // 离的坐标点和切线，pos会自动填充上坐标，这个方法很重要。
                mPathMeasure.getPosTan(value, mCurrentPosition, null);
                //mCurrentPosition此时就是中间距离点的坐标值
                // 移动的商品图片（动画图片）的坐标设置为该中间点的坐标
                dotView.setTranslationX(mCurrentPosition[0]);
                dotView.setTranslationY(mCurrentPosition[1]);
            }
        });
//      五、 开始执行动画
        valueAnimator.start();

//      六、动画结束后的处理
        valueAnimator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            //当动画结束后：
            @Override
            public void onAnimationEnd(Animator animation) {

                // 把移动的图片imageview从父布局里移除
                mRlLayout.removeView(dotView);
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
    }

    /**
     * 创建移动的view
     *
     * @param url
     * @return
     */
    private View createAnimationViewer(String url) {
        ImageView view = new ImageView(this);

        AutoRelativeLayout.LayoutParams params = new AutoRelativeLayout.LayoutParams(100, 100);
        view.setLayoutParams(params);
        String s = "";
        if (url.contains(API.PIC)) {
            s = url;
        } else {
            s = API.PIC + url;
        }

        Glide.with(this).load(s).asBitmap().centerCrop().into(new BitmapImageViewTarget(view) {
            @Override
            protected void setResource(Bitmap resource) {
                RoundedBitmapDrawable circularBitmapDrawable = RoundedBitmapDrawableFactory.create(getResources(),
                        resource);
                circularBitmapDrawable.setCircular(true);
                view.setImageDrawable(circularBitmapDrawable);
            }
        });
        return view;
    }

}