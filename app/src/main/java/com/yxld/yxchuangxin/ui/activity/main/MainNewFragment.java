package com.yxld.yxchuangxin.ui.activity.main;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
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

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.orhanobut.logger.Logger;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshHeader;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.scwang.smartrefresh.layout.listener.SimpleMultiPurposeListener;
import com.socks.library.KLog;
import com.yanzhenjie.permission.AndPermission;
import com.yanzhenjie.permission.PermissionListener;
import com.youth.banner.Banner;
import com.youth.banner.listener.OnBannerListener;
import com.yxld.yxchuangxin.R;
import com.yxld.yxchuangxin.Utils.ToastUtil;
import com.yxld.yxchuangxin.Utils.UIUtils;
import com.yxld.yxchuangxin.application.AppConfig;
import com.yxld.yxchuangxin.base.BaseFragment;
import com.yxld.yxchuangxin.contain.Contains;
import com.yxld.yxchuangxin.entity.GoodsKind;
import com.yxld.yxchuangxin.entity.MainToMarket;
import com.yxld.yxchuangxin.entity.MallClassify;
import com.yxld.yxchuangxin.ui.activity.camera.DeviceActivity;
import com.yxld.yxchuangxin.ui.activity.common.AisleActivity;
import com.yxld.yxchuangxin.ui.activity.goods.GoodDetailActivity;
import com.yxld.yxchuangxin.ui.activity.goods.GoodsFenLeiActivity;
import com.yxld.yxchuangxin.ui.activity.main.component.DaggerMainNewComponent;
import com.yxld.yxchuangxin.ui.activity.main.contract.MainNewContract;
import com.yxld.yxchuangxin.ui.activity.main.module.MainNewModule;
import com.yxld.yxchuangxin.ui.activity.main.presenter.MainNewPresenter;
import com.yxld.yxchuangxin.ui.activity.mine.MultiAccountActivity;
import com.yxld.yxchuangxin.ui.activity.wuye.CarManageActivity;
import com.yxld.yxchuangxin.ui.activity.wuye.FixActivity;
import com.yxld.yxchuangxin.ui.activity.wuye.MenJinActivity;
import com.yxld.yxchuangxin.ui.activity.wuye.MenJinNewActivity;
import com.yxld.yxchuangxin.ui.activity.wuye.MessageActivityActivity;
import com.yxld.yxchuangxin.ui.activity.wuye.WuyeMoneyActivity;
import com.yxld.yxchuangxin.ui.adapter.main.MainMiaoshaAdapter;
import com.yxld.yxchuangxin.ui.adapter.main.MainNewAdapter;
import com.yxld.yxchuangxin.ui.adapter.main.MainShopAdapter;
import com.yxld.yxchuangxin.ui.adapter.main.WuyeAdapter1;
import com.yxld.yxchuangxin.view.GlideImagerBannerLoader;
import com.yxld.yxchuangxin.view.GridDividerItemDecoration;
import com.zhy.autolayout.AutoLinearLayout;
import com.zhy.autolayout.AutoRelativeLayout;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.pedant.SweetAlert.SweetAlertDialog;

import static com.yxld.yxchuangxin.ui.activity.goods.GoodDetailActivity.KEY_PRODUCT_ID;
import static com.yxld.yxchuangxin.ui.activity.goods.MallFragment.TO_FEILEI_MALLCLASSFY;
import static com.yxld.yxchuangxin.ui.activity.goods.MallFragment.TO_FEILEI_TYPE;

/**
 * @author xlei
 * @Package com.yxld.yxchuangxin.ui.activity.main
 * @Description: $description
 * @date 2018/11/16 10:36:55
 */

public class MainNewFragment extends BaseFragment implements MainNewContract.View {

    @Inject
    MainNewPresenter mPresenter;
    @Inject WuyeAdapter1 mAdapter1;
    @Inject MainMiaoshaAdapter mainMiaoshaAdapter;
    @BindView(R.id.recyclerView) RecyclerView recyclerView;
    @BindView(R.id.smart_refresh) SmartRefreshLayout smartRefresh;
    @BindView(R.id.tv_menu) TextView mTvMenu;
    @BindView(R.id.toolbarBusiness) Toolbar toolbar;
    private MainNewAdapter adapter;
    private List<Integer> list = Arrays.asList(R.mipmap.ic_banner1, R.mipmap.ic_banner2, R.mipmap.ic_banner3, R.mipmap.ic_banner4);
    private List<String> listAdapterdata;
    private View view;
    private MainShopAdapter mainShopAdapter;
    private RecyclerView shopRecyclerview;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (view == null) {
            view = inflater.inflate(R.layout.fragment_main_new, null);
            ButterKnife.bind(this, view);
            initToolbar();
            initRv();
            initHead();
            initHead1();
            initMs();
            initFl();
            initData();
            mPresenter.getLastVersion();
        }
        return view;
    }

    private void initFl() {
        View flHead = LayoutInflater.from(getActivity()).inflate(R.layout.item_main_fl, (ViewGroup) recyclerView.getParent(), false);
        shopRecyclerview = flHead.findViewById(R.id.shopRecyclerview);
        adapter.addHeaderView(flHead);
    }

    private RecyclerView recycerViewMiaosha;
    private AutoLinearLayout miaoshaRoot;

    private void initMs() {
        View msHead = LayoutInflater.from(getActivity()).inflate(R.layout.item_main_miaosha, (ViewGroup) recyclerView.getParent(), false);
        recycerViewMiaosha = msHead.findViewById(R.id.rv_ms);
        recycerViewMiaosha.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL,false));
        recycerViewMiaosha.setAdapter(mainMiaoshaAdapter);
        recycerViewMiaosha.setNestedScrollingEnabled(false);
        mainMiaoshaAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                Intent intent = new Intent(getActivity(), GoodDetailActivity.class);
                intent.putExtra(KEY_PRODUCT_ID, mainMiaoshaAdapter.getData().get(i).getXinpinShangpinId() + "");
                startActivity(intent);
            }
        });
        miaoshaRoot = msHead.findViewById(R.id.miaosha_root);
        ImageView imageView = msHead.findViewById(R.id.iv_market);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EventBus.getDefault().post(MainToMarket.Main2Market.home);
            }
        });
        adapter.addHeaderView(msHead);
    }

    private void initHead1() {
        View headView1 = LayoutInflater.from(getActivity()).inflate(R.layout.item_main_cd, (ViewGroup) recyclerView.getParent(), false);
        RecyclerView rvCd = headView1.findViewById(R.id.rv_cd);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 3);
        rvCd.setLayoutManager(gridLayoutManager);
        Drawable divider = getActivity().getResources().getDrawable(R.drawable.shape_divider);
        GridDividerItemDecoration dividerItemDecoration = new GridDividerItemDecoration(getActivity(),
                GridDividerItemDecoration.GRID_DIVIDER_VERTICAL);
        dividerItemDecoration.setVerticalDivider(divider);
        dividerItemDecoration.setHorizontalDivider(divider);
        rvCd.addItemDecoration(dividerItemDecoration);
        rvCd.setAdapter(mAdapter1);
        mAdapter1.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                switch (position) {
                    case 0:
                        //开门
                        getActivity().startActivity(new Intent(getActivity(), MenJinActivity.class));
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
                        getActivity().startActivity(new Intent(getActivity(), MenJinNewActivity.class));

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
        adapter.addHeaderView(headView1);
    }

    private void initHead() {
        View headView = LayoutInflater.from(getActivity()).inflate(R.layout.item_main_head, (ViewGroup) recyclerView.getParent(), false);
        banner = headView.findViewById(R.id.banner);
        initBanner();
        tvAction = headView.findViewById(R.id.tv_action);
        AutoRelativeLayout relativeLayout = headView.findViewById(R.id.rl_action);
        relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(MessageActivityActivity.class);
            }
        });
        adapter.addHeaderView(headView);
    }

    private int instans = 0;

    private void initRv() {
        listAdapterdata = new ArrayList<>();
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter = new MainNewAdapter(listAdapterdata);
        recyclerView.setAdapter(adapter);
        smartRefresh.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                smartRefresh.finishRefresh();
                initData();
            }
        });
        smartRefresh.setOnMultiPurposeListener(new SimpleMultiPurposeListener() {

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
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
//                Logger.e(dy + "--------");
                instans = instans + dy;
//                Logger.e(dy + "--------" + instans + "toobarhead" + toolbar.getHeight());
                if (instans > toolbar.getHeight()) {
                    toolbar.setBackgroundColor(getResources().getColor(R.color.main_color));
                } else {
                    toolbar.setBackgroundColor(getResources().getColor(R.color.transparent));
                }
            }
        });
    }

    protected void initData() {
        Map<String, String> action = new HashMap<>();
        if (Contains.curSelectXiaoQuName != null && !("".equals(Contains.curSelectXiaoQuName)) && Contains
                .curSelectXiaoQuId != 0) {
            action.put("luopan", Contains.curSelectXiaoQuId + "");
        } else {
            action.put("luopan", "");
        }

        mPresenter.getMianShaData();
        mPresenter.getFenlei();
        mPresenter.getAction(action);
    }

    private void initToolbar() {
        toolbar.setBackgroundColor(getResources().getColor(R.color.transparent));
        toolbar.setLogo(R.mipmap.main_navigation_left);
        toolbar.setTitle(Contains.curSelectXiaoQuName);
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
                AndPermission.with(getActivity()).requestCode(101).permission(Manifest.permission.CAMERA).rationale(
                        (requestCode, rationale) -> {
                            AndPermission.rationaleDialog(getActivity(), rationale).setNegativeButton("关闭", new DialogInterface.OnClickListener() {
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
    }


    @Override
    protected void setupFragmentComponent() {
        DaggerMainNewComponent
                .builder()
                .appComponent(((AppConfig) getActivity().getApplication()).getApplicationComponent())
                .mainNewModule(new MainNewModule(this))
                .build()
                .inject(this);
    }

    @Override
    public void setPresenter(MainNewContract.MainNewContractPresenter presenter) {
        mPresenter = (MainNewPresenter) presenter;
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

    Banner banner;
    TextView tvAction;

    /**
     * 添加轮播图和通知
     *
     * @param content
     */
    @Override
    public void setAction(String content) {
        if (tvAction != null) {
            tvAction.setText(content);
        }
    }

    private void initBanner() {

        banner.setImageLoader(new GlideImagerBannerLoader());
        //设置图片集合
        banner.setImages(list);
        //banner设置方法全部调用完毕时最后调用
        banner.start();
        banner.setOnBannerListener(new OnBannerListener() {
            @Override
            public void OnBannerClick(int position) {
                if (position == 0) {  //手机开门
                    if (Contains.user == null || Contains.user.getYezhuType() == null || Contains.user.getYezhuType()
                            != 0 || Contains.appYezhuFangwus.size() == 0) {
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
                    if (Contains.curSelectXiaoQuId == 0 || Contains.curSelectXiaoQuName == null || "".equals(Contains
                            .curSelectXiaoQuName)) {
                        new SweetAlertDialog(getActivity(), SweetAlertDialog.WARNING_TYPE).setTitleText("未获取到房屋信息")
                                .setContentText("请手动选择小区").setConfirmText("确认").setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
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
        });
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

    private void setMiaoshaRecyclerview(List<GoodsKind.RowsBean.XinpinListsBean> xinpinLists) {
        mainMiaoshaAdapter.setNewData(xinpinLists);

    }

    @Override
    public void setFenleiAdapter(MallClassify mallClassify) {
        mainShopAdapter = new MainShopAdapter(new ArrayList<>());
        if (mallClassify.getStatus() == 1) {
            List<MallClassify.RowsBean> rows = mallClassify.getRows();
            List<MallClassify.RowsBean> list = new ArrayList<>();
            if (rows.size() > 4) {
                for (int i = 0; i < 4; i++) {
                    list.add(rows.get(i));
                }
            } else {
                if (rows.size() > 0) {
                    for (int i = 0; i < rows.size(); i++) {
                        list.add(rows.get(i));
                    }
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
    public void onDestroyView() {
        super.onDestroyView();
    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void reLogin(String relogin) {
        if (relogin.equals("reLogin")) {
            initData();
            toolbar.setTitle(Contains.curSelectXiaoQuName);
            KLog.i("toolbar重新设置标题" + Contains.curSelectXiaoQuName);
            instans = 0;
            recyclerView.smoothScrollToPosition(0);

        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
        mPresenter.unsubscribe();
    }
}