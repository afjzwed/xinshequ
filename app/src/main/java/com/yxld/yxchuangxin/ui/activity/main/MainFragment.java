package com.yxld.yxchuangxin.ui.activity.main;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Message;
import android.os.RemoteException;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshHeader;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.scwang.smartrefresh.layout.listener.SimpleMultiPurposeListener;
import com.socks.library.KLog;
import com.yanzhenjie.permission.AndPermission;
import com.yanzhenjie.permission.PermissionListener;
import com.yxld.yxchuangxin.HomeService;
import com.yxld.yxchuangxin.R;
import com.yxld.yxchuangxin.Utils.ToastUtil;
import com.yxld.yxchuangxin.Utils.UIUtils;
import com.yxld.yxchuangxin.application.AppConfig;
import com.yxld.yxchuangxin.base.BaseFragment;
import com.yxld.yxchuangxin.contain.Contains;
import com.yxld.yxchuangxin.data.api.API;
import com.yxld.yxchuangxin.entity.CxwyMallPezhi;
import com.yxld.yxchuangxin.entity.GoodsKind;
import com.yxld.yxchuangxin.entity.LocalAd;
import com.yxld.yxchuangxin.entity.MainToMarket;
import com.yxld.yxchuangxin.entity.MallClassify;
import com.yxld.yxchuangxin.entity.User;
import com.yxld.yxchuangxin.entity.test.MainShopSale;
import com.yxld.yxchuangxin.ui.activity.camera.DeviceActivity;
import com.yxld.yxchuangxin.ui.activity.common.AisleActivity;
import com.yxld.yxchuangxin.ui.activity.goods.GoodDetailActivity;
import com.yxld.yxchuangxin.ui.activity.goods.GoodsFenLeiActivity;
import com.yxld.yxchuangxin.ui.activity.main.component.DaggerMainComponent;
import com.yxld.yxchuangxin.ui.activity.main.contract.MainContract;
import com.yxld.yxchuangxin.ui.activity.main.module.Mainmodule;
import com.yxld.yxchuangxin.ui.activity.main.presenter.MainPresenter;
import com.yxld.yxchuangxin.ui.activity.mine.MultiAccountActivity;
import com.yxld.yxchuangxin.ui.activity.rim.WebViewActivity;
import com.yxld.yxchuangxin.ui.activity.wuye.CarManageActivity;
import com.yxld.yxchuangxin.ui.activity.wuye.FixActivity;
import com.yxld.yxchuangxin.ui.activity.wuye.MenJinActivity;
import com.yxld.yxchuangxin.ui.activity.wuye.MessageActivityActivity;
import com.yxld.yxchuangxin.ui.activity.wuye.RoomRentActivity;
import com.yxld.yxchuangxin.ui.activity.wuye.WuyeMoneyActivity;
import com.yxld.yxchuangxin.ui.activity.xiongmai.DeviceLoginActivity;
import com.yxld.yxchuangxin.ui.activity.xiongmai.lib.funsdk.support.FunSupport;
import com.yxld.yxchuangxin.ui.adapter.main.MainMiaoshaAdapter;
import com.yxld.yxchuangxin.ui.adapter.main.MainShopAdapter;
import com.yxld.yxchuangxin.ui.adapter.main.WuyeAdapter1;
import com.yxld.yxchuangxin.view.AutoCardView;
import com.yxld.yxchuangxin.view.GridDividerItemDecoration;
import com.yxld.yxchuangxin.view.ImageCycleView;
import com.yxld.yxchuangxin.view.MiaoshaTimeView;
import com.yxld.yxchuangxin.view.TranslucentScrollView;
import com.zhy.autolayout.AutoLinearLayout;
import com.zhy.autolayout.AutoRelativeLayout;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.pedant.SweetAlert.SweetAlertDialog;

import static com.yxld.yxchuangxin.ui.activity.goods.GoodDetailActivity.KEY_PRODUCT_ID;
import static com.yxld.yxchuangxin.ui.activity.goods.MallFragment.TO_FEILEI_MALLCLASSFY;
import static com.yxld.yxchuangxin.ui.activity.goods.MallFragment.TO_FEILEI_TYPE;

public class MainFragment extends BaseFragment implements MainContract.View, MiaoshaTimeView.MiaoshaWanchengListener {


    @Inject
    MainPresenter mPresenter;
    @Inject
    WuyeAdapter1 mAdapter1;
    @BindView(R.id.scrollView)
    TranslucentScrollView mScrollView;
    @BindView(R.id.tv_menu)
    TextView mTvMenu;
    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;

    private MainShopAdapter mainShopAdapter;
    @Inject
    MainMiaoshaAdapter mainMiaoshaAdapter;


    @BindView(R.id.indexAdvs)
    ImageCycleView indexAdvs;
    @BindView(R.id.recycerViewMiaosha)
    RecyclerView recycerViewMiaosha;
    @BindView(R.id.shopRecyclerview)
    RecyclerView shopRecyclerview;
    @BindView(R.id.miaosha_shijian)
    MiaoshaTimeView miaoshaShijian;
    @BindView(R.id.cv_car)
    AutoCardView cvCar;
    @BindView(R.id.cv_anfang)
    AutoCardView cvAnfang;
    @BindView(R.id.cv_jiaofei)
    AutoCardView cvJiaofei;
    @BindView(R.id.cv_baoxiu)
    AutoCardView cvBaoxiu;
    @BindView(R.id.iv_toutiao)
    ImageView ivToutiao;
    @BindView(R.id.tv_action)
    TextView tvAction;
    @BindView(R.id.iv_tongzhi_more)
    ImageView ivTongzhiMore;
    @BindView(R.id.car)
    ImageView car;
    @BindView(R.id.anfang)
    ImageView anfang;
    @BindView(R.id.jiaofei)
    ImageView jiaofei;
    @BindView(R.id.baoxiu)
    ImageView baoxiu;
    @BindView(R.id.iv_menjin)
    ImageView ivMenjin;
    @BindView(R.id.iv_miaosha)
    ImageView ivMiaosha;
    @BindView(R.id.tv_jiazheng)
    TextView tvJiazheng;
    @BindView(R.id.tv_hua)
    TextView tvHua;
    @BindView(R.id.iv_market)
    ImageView ivMarket;
    @BindView(R.id.xiyi)
    AutoLinearLayout xiyi;
    @BindView(R.id.jiazheng)
    AutoRelativeLayout jiazheng;
    @BindView(R.id.lipin)
    AutoRelativeLayout lipin;
    @BindView(R.id.miaosha_root)
    AutoLinearLayout miaoshaRoot;
    @BindView(R.id.toolbarBusiness)
    Toolbar toolbar;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle
            savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_main, null);
        ButterKnife.bind(this, view);
        toolbar.setLogo(R.mipmap.main_navigation_left);
        toolbar.setTitle(Contains.appYezhuFangwus.get(Contains.curFangwu).getXiangmuLoupan());
        toolbar.setTitleTextColor(getResources().getColor(R.color.white));

        AutoRelativeLayout.LayoutParams lp = new AutoRelativeLayout.LayoutParams(UIUtils.getDisplayWidth(getActivity
                ()), (int) (UIUtils.getStatusBarHeight(getActivity()) * 3));
        toolbar.setLayoutParams(lp);
        toolbar.setPadding(0, (int) (UIUtils.getStatusBarHeight(getActivity()) * 0.8), 0, 0);
        toolbar.setTitleMarginTop((int) (UIUtils.getStatusBarHeight(getActivity()) * 0.55));
        // TODO: 2018/3/12  扫码支付 添加 
        mTvMenu.setVisibility(View.VISIBLE);
        mTvMenu.setText("扫一扫");
        mTvMenu.setTextSize(10);
        Drawable drawable = this.getResources().getDrawable(R.mipmap.saomiao);

        drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
        //width即为你需要设置的图片宽度，height即为你设置的图片的高度

        mTvMenu.setCompoundDrawables(null, drawable, null, null);
        mTvMenu.setCompoundDrawablePadding(1);
        mTvMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //ToastUtil.showShort("查看订单");
                AndPermission.with(getActivity()).requestCode(101).permission(Manifest.permission.CAMERA).rationale(
                        (requestCode, rationale) -> {
                    AndPermission.rationaleDialog(getActivity(), rationale).setNegativeButton("关闭", new
                            DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            ToastUtil.show(getActivity(), "没有权限,您不能扫描二维码,请进入设置打开权限!!!");
                        }
                    }).show();
                }).callback(new PermissionListener() {
                    @Override
                    public void onSucceed(int requestCode, @NonNull List<String> grantPermissions) {
                        startActivity(ScanActivityActivity.class);
                    }

                    @Override
                    public void onFailed(int requestCode, @NonNull List<String> deniedPermissions) {
                        ToastUtil.show(getActivity(), "没有权限,您不能扫描二维码,请进入设置打开权限!!!");
                    }
                }).start();

            }
        });

        try {
            Field field = toolbar.getClass().getDeclaredField("mTitleTextView");
            field.setAccessible(true);
            TextView textView = (TextView) field.get(toolbar);
            textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(MultiAccountActivity.class);
                }
            });
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                refreshLayout.finishRefresh();
                initData();
            }
        });
        refreshLayout.setOnMultiPurposeListener(new SimpleMultiPurposeListener() {

            @Override
            public void onHeaderReleasing(RefreshHeader header, float percent, int offset, int bottomHeight, int
                    extendHeight) {
                toolbar.setAlpha(1 - Math.min(percent, 1));
            }

            @Override
            public void onHeaderPulling(RefreshHeader header, float percent, int offset, int headerHeight, int
                    extendHeight) {
                toolbar.setAlpha(1 - Math.min(percent, 1));
            }
        });
        Bundle mBundle = getArguments();
        Glide.with(this).load(R.mipmap.soye_km).asGif().into(ivMenjin);
        initView();
        mPresenter.setReStart();
        //mPresenter.getLocalAd();
        return view;
    }


    @Override
    protected void initDataFromLocal() {


    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void reLogin(String relogin) {
        if (relogin.equals("reLogin")) {
            initData();
            toolbar.setTitle(Contains.appYezhuFangwus.get(Contains.curFangwu).getXiangmuLoupan());
            KLog.i("toolbar重新设置标题" + Contains.appYezhuFangwus.get(Contains.curFangwu).getXiangmuLoupan());
        }
    }

    protected void initData() {
        Map<String, String> action = new HashMap<>();
        if (Contains.curSelectXiaoQuName != null && !"".equals(Contains.curSelectXiaoQuName) && Contains
                .curSelectXiaoQuId != 0) {
            action.put("luopan", Contains.curSelectXiaoQuId + "");
        } else {
            action.put("luopan", "");
        }

        mPresenter.getAction(action);
        Map<String, String> banner = new HashMap<>();
        banner.put("uuid", Contains.uuid);
        banner.put("xmid", Contains.user.getYezhuXmId() + "");
        mPresenter.getBanner(banner);
        mPresenter.getMianShaData();
        mPresenter.getFenlei();
//        EventBus.getDefault().register(this);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        EventBus.getDefault().register(this);
        super.onCreate(savedInstanceState);
        initData();
    }

    private void initMallData() {
        List<MainShopSale> list = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            MainShopSale mainShopSale = new MainShopSale();
            switch (i) {
                case 0:
                    mainShopSale.setDiscount("满199减10");
                    mainShopSale.setName("粮油");
                    mainShopSale.setUrl(R.mipmap.main_activity_liangyou);
                    break;
                case 1:
                    mainShopSale.setDiscount("满199减10");
                    mainShopSale.setName("零食");
                    mainShopSale.setUrl(R.mipmap.main_activity_lingshi);
                    break;
                case 2:
                    mainShopSale.setDiscount("满199减10");
                    mainShopSale.setName("日用");
                    mainShopSale.setUrl(R.mipmap.main_activity_riyong);
                    break;
                case 3:
                    mainShopSale.setDiscount("满199减10");
                    mainShopSale.setName("百货");
                    mainShopSale.setUrl(R.mipmap.main_activity_baihuo);
                    break;
            }
            list.add(mainShopSale);
        }
//        setShopRecyclerview(list);
    }

    protected void initView() {
        toolbar.setLogo(R.mipmap.main_navigation_left);
        toolbar.setTitle(Contains.appYezhuFangwus.get(Contains.curFangwu).getXiangmuLoupan());
        mScrollView.setTransView(toolbar, getResources().getColor(R.color.main_color), UIUtils.dip2px(50), UIUtils
                .dip2px(100));
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 3);
        mRecyclerView.setLayoutManager(gridLayoutManager);
        Drawable divider = getActivity().getResources().getDrawable(R.drawable.shape_divider);
        GridDividerItemDecoration dividerItemDecoration = new GridDividerItemDecoration(getActivity(),
                GridDividerItemDecoration.GRID_DIVIDER_VERTICAL);
        dividerItemDecoration.setVerticalDivider(divider);
        dividerItemDecoration.setHorizontalDivider(divider);
        mRecyclerView.addItemDecoration(dividerItemDecoration);
        // recycerView.addItemDecoration(new GridDividerItemDecoration(10));
        mRecyclerView.setAdapter(mAdapter1);
        mAdapter1.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                switch (position) {
                    case 0:
                        //开门
                        getActivity().startActivity(new Intent(getActivity(), MenJinActivity.class));
                        HomeActivity activity = (HomeActivity) getActivity();
                        //sendMainMessenge(HomeService.MSG_OPEN_DOOR);
                        Message message = Message.obtain();
                        message.what = HomeService.MSG_ONE_OPEN_DOOR;
                        try {
                            // Log.e(TAG, "发送主消息code1=" + code);
                            activity.getServiceMessenger().send(message);
                        } catch (RemoteException e) {
                            e.printStackTrace();
                        }
                        break;
                    case 1:
                        //物业缴费
                        getActivity().startActivity(new Intent(getActivity(), WuyeMoneyActivity.class));
                        break;
                    case 2:
                        //物业报修
                        getActivity().startActivity(new Intent(getActivity(), FixActivity.class));
                        break;
                    case 3:
                        //居家安防
                        getActivity().startActivity(new Intent(getActivity(), DeviceActivity.class));
                        break;
                    case 4:
                        //车辆管理
                        getActivity().startActivity(new Intent(getActivity(), CarManageActivity.class));
                        break;
                    case 5:
                        //公共监控
                        AppConfig.getInstance().initCommon();
                        getActivity().startActivity(new Intent(getActivity(), AisleActivity.class));
                        break;
                }
            }
        });
    }

    @Override
    protected void setupFragmentComponent() {
        DaggerMainComponent.builder().appComponent(((AppConfig) getActivity().getApplication())
                .getApplicationComponent()).mainmodule(new Mainmodule(this)).build().inject(this);
    }


    @Override
    public void setText(User baseBack) {
        progressDialog.hide();
    }

    @Override
    public void setShopRecyclerview(MallClassify mallClassify) {
//        Log.e("wh ", mallClassify.toString());
        mainShopAdapter = new MainShopAdapter(new ArrayList<>());
        if (mallClassify.getStatus() == 1) {
            List<MallClassify.RowsBean> rows = mallClassify.getRows();
            List<MallClassify.RowsBean> list = new ArrayList<>();
            if (rows.size() > 4) {
                for (int i = 0; i < 4; i++) {
                    list.add(rows.get(i));
                }
            } else {
                for (int i = 0; i < rows.size(); i++) {
                    list.add(rows.get(i));
                }
            }
            mainShopAdapter.setNewData(list);
        } else {
            onError(mallClassify.getMSG(), mallClassify.getStatus());
        }
        mainShopAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                ArrayList<String> list = new ArrayList<String>();
                ArrayList<String> listname = new ArrayList<String>();
                for (int j = 0; j < mainShopAdapter.getData().size(); j++) {
                    list.add(mainShopAdapter.getData().get(j).getId() + "");
                    listname.add(mainShopAdapter.getData().get(j).getFenleiMing());
                }
                ToFeileiActivity(mallClassify, mainShopAdapter.getData().get(i).getId() + "", list, listname);
            }
        });
        shopRecyclerview.setAdapter(mainShopAdapter);
    }

    /**
     * 跳转到分类列表
     *
     * @param mallClassify
     * @param type
     */
    private void ToFeileiActivity(MallClassify mallClassify, String type, ArrayList<String> list, ArrayList<String>
            listname) {
        Intent intent = new Intent(getActivity(), GoodsFenLeiActivity.class);
        intent.putExtra(TO_FEILEI_TYPE, type);
        intent.putStringArrayListExtra("listid", list);
        intent.putStringArrayListExtra("listname", listname);
        intent.putExtra(TO_FEILEI_MALLCLASSFY, mallClassify);
        startActivity(intent);
    }

    @Override
    public void setMiaoshaRecyclerview(List<GoodsKind.RowsBean.XinpinListsBean> xinpinLists) {
        miaoshaRoot.setVisibility(View.VISIBLE);
        mainMiaoshaAdapter.setNewData(xinpinLists);
        LinearLayoutManager ms = new LinearLayoutManager(getActivity());
        ms.setOrientation(LinearLayoutManager.HORIZONTAL);// 设置 recyclerview 布局方式为横向布局
        recycerViewMiaosha.setLayoutManager(ms);
        recycerViewMiaosha.setAdapter(mainMiaoshaAdapter);
        recycerViewMiaosha.setNestedScrollingEnabled(false);
        mainMiaoshaAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
            /*    Intent intent = new Intent(getActivity(), GoodDetailActivity.class);
                intent.putExtra(GoodDetailActivity.KEY_PRODUCT_ID, list.get(i).getShangpinId());
                startActivity(intent);*/
                Intent intent = new Intent(getActivity(), GoodDetailActivity.class);
                intent.putExtra(KEY_PRODUCT_ID, mainMiaoshaAdapter.getData().get(i).getXinpinShangpinId() + "");
                startActivity(intent);
            }
        });

    }

    private ArrayList<String> urls = new ArrayList<>();

    @Override
    public void setBanner(CxwyMallPezhi info) {
        if (info.getLblist() != null && info.getLblist().size() != 0) {
            urls.clear();
            for (int i = 0; i < info.getLblist().size(); i++) {
                urls.add(API.PIC + info.getLblist().get(i).getMallPeizhiValue());
            }
//            Log.d("geek", "首页轮播图路径urls=" + urls.toString());
            indexAdvs.setImageResources(urls, new ImageCycleView.ImageCycleViewListener() {
                @Override
                public void onImageClick(int position, View imageView) {
                    if (position == 0) {  //手机开门
                        if (Contains.user == null || Contains.user.getYezhuType() == null || Contains.user
                                .getYezhuType() != 0 || Contains.appYezhuFangwus.size() == 0) {
                            ToastUtil.show(getActivity(), "业主信息不完善");
                            return;
                        }
                        startActivity(MenJinActivity.class);
                    } else if (position == 1) {  //物业缴费
                        if (Contains.appYezhuFangwus == null || Contains.appYezhuFangwus.size() == 0) {
                            ToastUtil.show(getActivity(), "请配置房屋信息再进行查询");
                            return;
                        }
                        startActivity(WuyeMoneyActivity.class);
                    } else if (position == 2) {  //居家安防
                        startActivity(DeviceActivity.class);
                    } else if (position == 3) {  //便利商店
                        if (Contains.curSelectXiaoQuId == 0 || Contains.curSelectXiaoQuName == null || "".equals
                                (Contains.curSelectXiaoQuName)) {
                            new SweetAlertDialog(getActivity(), SweetAlertDialog.WARNING_TYPE).setTitleText
                                    ("未获取到房屋信息").setContentText("请手动选择小区").setConfirmText("确认")
                                    .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                @Override
                                public void onClick(SweetAlertDialog sDialog) {
//                                            startActivity(SelectPlaceActivity.class);
//                                            sDialog.dismissWithAnimation();
                                }
                            }).show();
                            return;
                        }
                        EventBus.getDefault().post(MainToMarket.Main2Market.home);
                    }
                }
            }, 0);
        }
    }

    @Override
    public void setAction(String content) {
        tvAction.setText(content);
    }

    @Override
    public void setMiaoShaDatas(GoodsKind goodsKind) {
        if (goodsKind.status == 1) {
            List<GoodsKind.RowsBean.XinpinListsBean> xinpinLists = new ArrayList<>();
            xinpinLists = goodsKind.getRows().getXinpinLists();
            if (xinpinLists.size() != 0) {
                miaoshaRoot.setVisibility(View.VISIBLE);
                setMiaoshaRecyclerview(xinpinLists);
            } else {
                KLog.i("设置秒杀不可见");
                miaoshaRoot.setVisibility(View.GONE);
            }
        } else {
            onError(goodsKind.MSG, goodsKind.status);
        }
    }

    @Override
    public void onRefreshFailure() {
        ToastUtil.displayShortToast("刷新数据失败");
    }

    @Override
    public void setFenleiAdapter(MallClassify mallClassify) {
        setShopRecyclerview(mallClassify);
    }

    /**
     * 保存广告信息
     */
    @Override
    public void saveAdInfo(LocalAd localAd) {
        if (localAd.status == 1) {
//            SharedPreferences.Editor edit = sp.edit();
//            edit.putString("")
        }

    }

    @Override
    public void setPresenter(MainContract.MainPresenter presenter) {
        mPresenter = (MainPresenter) presenter;
    }

    public void setImageCircle(ArrayList<String> url) {
        indexAdvs.setImageResources(url, new ImageCycleView.ImageCycleViewListener() {
            @Override
            public void onImageClick(int position, View imageView) {

            }
        }, 0);
    }

    @Override
    public void onResume() {
        super.onResume();
        initMallData();
    }

    //秒杀结束需要做的逻辑
    @Override
    public void onMiaoshaComplete() {

    }

    @OnClick({R.id.iv_menjin, R.id.cv_car, R.id.cv_anfang, R.id.cv_jiaofei, R.id.cv_baoxiu, R.id.tv_action, R.id
            .iv_tongzhi_more, R.id.iv_market, R.id.xiyi, R.id.jiazheng, R.id.lipin})
    public void onViewClicked(View view) {
        Intent intent = new Intent();
        intent.setClass(getActivity(), // context
                WebViewActivity.class);// 跳转的activity\
        switch (view.getId()) {
            case R.id.iv_menjin:
                startActivity(MenJinActivity.class);
                break;
            case R.id.cv_car:
                startActivity(CarManageActivity.class);
                break;
            case R.id.cv_anfang:
                startActivity(DeviceActivity.class);
                break;
            case R.id.cv_jiaofei:
                startActivity(WuyeMoneyActivity.class);
                break;
            case R.id.cv_baoxiu:
                startActivity(FixActivity.class);
                break;
            case R.id.tv_action:
            case R.id.iv_tongzhi_more:
                startActivity(MessageActivityActivity.class);
                break;
            case R.id.iv_market:
                EventBus.getDefault().post(MainToMarket.Main2Market.home);
                break;
            case R.id.xiyi:
                Bundle xy = new Bundle();
                xy.putString("name", "e袋洗");
                xy.putString("address", "http://www.edaixi.com");
                intent.putExtras(xy);
                startActivity(intent);
                break;
            case R.id.jiazheng:
                Bundle jz = new Bundle();
                jz.putString("name", "无忧保姆");
                jz.putString("address", "http://www.51baomu.cn");
                intent.putExtras(jz);
                startActivity(intent);
                break;
            case R.id.lipin:
                Bundle xhps = new Bundle();
                xhps.putString("name", "鲜花网");
                xhps.putString("address", "http://www.hua.com");
                intent.putExtras(xhps);
                startActivity(intent);
                break;
        }
    }


    @Override
    public void onDestroy() {
        mPresenter.unsubscribe();
        EventBus.getDefault().unregister(this);
        super.onDestroy();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }
}
