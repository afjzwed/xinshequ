package com.yxld.yxchuangxin.ui.activity.rim;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alipay.sdk.app.PayTask;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemChildClickListener;
import com.socks.library.KLog;
import com.yxld.yxchuangxin.R;
import com.yxld.yxchuangxin.Utils.AlipyUtil;
import com.yxld.yxchuangxin.Utils.CxUtil;
import com.yxld.yxchuangxin.Utils.HttpUtils;
import com.yxld.yxchuangxin.Utils.PayResult;
import com.yxld.yxchuangxin.Utils.StringUitl;
import com.yxld.yxchuangxin.Utils.ToastUtil;
import com.yxld.yxchuangxin.Utils.UIUtils;
import com.yxld.yxchuangxin.Utils.YinLianPayUtil;
import com.yxld.yxchuangxin.application.AppConfig;
import com.yxld.yxchuangxin.base.BaseFragment;
import com.yxld.yxchuangxin.contain.Contains;
import com.yxld.yxchuangxin.contain.PayContain;
import com.yxld.yxchuangxin.entity.SJComplain;
import com.yxld.yxchuangxin.entity.SJOrder;
import com.yxld.yxchuangxin.ui.activity.rim.component.DaggerRimPublicOrderListComponent;
import com.yxld.yxchuangxin.ui.activity.rim.contract.RimPublicOrderListContract;
import com.yxld.yxchuangxin.ui.activity.rim.module.RimPublicOrderListModule;
import com.yxld.yxchuangxin.ui.activity.rim.presenter.RimPublicOrderListPresenter;
import com.yxld.yxchuangxin.ui.adapter.rim.RimOrderListAdapter;
import com.yxld.yxchuangxin.view.ConfirmDialog;
import com.yxld.yxchuangxin.view.CustomLoadMoreView;
import com.yxld.yxchuangxin.xsq.wxapi.WechatPay;
import com.zhy.autolayout.AutoLinearLayout;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import cn.refactor.library.SmoothCheckBox;

import static com.yxld.yxchuangxin.R.id.ll_popup;
import static com.yxld.yxchuangxin.R.id.pop_pay;

/**
 * @author wwx
 * @Package com.yxld.yxchuangxin.ui.activity.rim
 * @Description: 欣周边 订单列表
 * @date 2017/06/17
 */

public class RimPublicOrderListFragment extends BaseFragment implements
        RimPublicOrderListContract.View,
        SwipeRefreshLayout.OnRefreshListener {

    @Inject
    RimPublicOrderListPresenter mPresenter;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.swipeLayouts)
    SwipeRefreshLayout swipeLayouts;
    @BindView(R.id.public_recylist_main_id)
    AutoLinearLayout publicRecylistMainId;

    Unbinder unbinder;

    @Inject
    RimOrderListAdapter rimOrderListAdapter;
    @Inject
    public SJOrder data;

    int orderState = 0;
    private int curPosition;
    private PopupWindow cancelPop = null;
    private AutoLinearLayout llCancelpopup;
    private View view;
    private PopupWindow payPop = null;
    private AutoLinearLayout llPaypopup;

    private View loadingView;//正在加载
    private View notDataView;//无数据
    private View errorView;//请求错误
    private List<SJOrder> orderBeanList = new ArrayList<>();

    private RimOrderListActivityActivity mActivity;

    private int page;//分页数
    private int rows = 6;//每页加载数

    /**
     * 支付方式 0 支付宝 1 微信 2 银联
     */
    private Integer paymentMethod = 1;//默认支付方式为微信
    private final int PAY_ALIPY = 0;
    private final int PAY_WEIXIN = 1;
    private final int PAY_YINLIAN = 2;
    private AlipyUtil alipyUtil = new AlipyUtil();
    public final int SDK_PAY_FLAG_RIM = 1;


    private final int CODE_REQUEST_COMMENT = 101;

    /**
     * 订单价格
     */
    private String orderMoney = "";
    /**
     * 商品名称或店铺名称
     */
    private String orderShop = "";
    /**
     * 详情
     */
    private String orderDetails = "";
    /**
     * 订单编号
     */
    private String orderBianhao = "";

    public static RimPublicOrderListFragment newInstance(int param) {
        RimPublicOrderListFragment fragment = new RimPublicOrderListFragment();
        Bundle args = new Bundle();
        args.putInt("state", param);
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable
            Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.public_recyclerview, null);
        unbinder = ButterKnife.bind(this, view);
        Bundle mBundle = getArguments();
        orderState = mBundle.getInt("state");
        UIUtils.configSwipeRefreshLayoutColors(swipeLayouts);
        //swipeLayouts.setColorSchemeColors(Color.rgb(47, 223, 189));
        swipeLayouts.setOnRefreshListener(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        loadingView = getActivity().getLayoutInflater().inflate(R.layout.loading_view,
                (ViewGroup) recyclerView
                .getParent(), false);
        notDataView = getActivity().getLayoutInflater().inflate(R.layout.empty_view, (ViewGroup)
                recyclerView
                .getParent(), false);

        notDataView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               initData();
            }
        });
        rimOrderListAdapter.setEmptyView(loadingView);
        rimOrderListAdapter.setLoadMoreView(new CustomLoadMoreView());
        rimOrderListAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                getData(false);
            }
        }, recyclerView);


        rimOrderListAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(final BaseQuickAdapter adapter, final View view, final int
                    position) {
                Intent intent = new Intent(getActivity(), BusinessOrderDetailActivity.class);
                Bundle bundle = new Bundle();
                intent.putExtra("orderNumber", rimOrderListAdapter.getData().get(position)
                        .getOrderNumber());
                intent.putExtra("businessNumber", rimOrderListAdapter.getData().get(position)
                        .getOrderBusinessNumber());
                intent.putExtra("businessProduceType", rimOrderListAdapter.getData().get(position)
                        .getProduceType());
                startActivityForResult(intent, CODE_REQUEST_COMMENT);
                //  startActivity(BusinessOrderDetailActivity.class, bundle);
            }
        });
        recyclerView.addOnItemTouchListener(ClickListener);
        recyclerView.setAdapter(rimOrderListAdapter);

        initData();
        return view;
    }

    @Override
    protected void setupFragmentComponent() {
        DaggerRimPublicOrderListComponent
                .builder()
                .appComponent(((AppConfig) getActivity().getApplication())
                        .getApplicationComponent())
                .rimPublicOrderListModule(new RimPublicOrderListModule(this))
                .build()
                .inject(this);
    }

    @Override
    public void setPresenter(RimPublicOrderListContract.RimPublicOrderListContractPresenter
                                     presenter) {
        mPresenter = (RimPublicOrderListPresenter) presenter;
    }

    @Override
    protected void initDataFromLocal() {

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
//        mFragmentCallback=null;
        mPresenter.unsubscribe();
        unbinder.unbind();
        cancelPop = null;
        payPop = null;
        PayContain.weixinPayResult = null;
        PayContain.requestPayModule = null;
        PayContain.yinLianPay = null;
        PayContain.payResult = null;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mPresenter.unsubscribe();
        cancelPop = null;
        payPop = null;
        PayContain.weixinPayResult = null;
        PayContain.requestPayModule = null;
        PayContain.yinLianPay = null;
        PayContain.payResult = null;
    }

    @Override
    public void initData() {
        page = 1;
        swipeLayouts.setRefreshing(true);//显示加载进度条.要在主线程中执行
        rimOrderListAdapter.setEnableLoadMore(false);//这里的作用是防止下拉刷新的时候还可以上拉加载，设置为true就是自动加载更多
        getData(true);
//        rimOrderListAdapter.setNewData(data.getData());
//        rimOrderListAdapter.notifyDataSetChanged();
    }

    @Override
    public void getData(boolean isRefresh) {
        LinkedHashMap<String, String> map = new LinkedHashMap<>();
        map.put("orderStatus", orderState + "");
        map.put("uuId", Contains.uuid);
        map.put("page", page + "");
        map.put("rows", rows + "");
        mPresenter.getRimOrderList(map,isRefresh);
    }

    @Override
    public void setData(boolean isRefresh,SJOrder data) {
        page++;
        orderBeanList = data.getData();
        final int size = orderBeanList == null ? 0 : orderBeanList.size();

        if (isRefresh) {
            if (size > 0) {

            } else {
                rimOrderListAdapter.setEmptyView(notDataView);
            }
            rimOrderListAdapter.setNewData(orderBeanList);//将首次数据塞入适配器的方法
        } else {
            if (size > 0) {
                rimOrderListAdapter.addData(orderBeanList);//加载更多时直接将更多数据塞入适配器
            }
        }

        if (size < rows) {
            //第一页如果不够一页就不显示没有更多数据布局
            rimOrderListAdapter.loadMoreEnd(isRefresh);//不传参数默认false,表示数据全部加载完毕没有更多数据  加载结束
            // 这里设置为false可用来显示没有更多数据item
        } else {
            rimOrderListAdapter.loadMoreComplete();//加载完成（注意不是加载结束，而是本次数据加载结束并且还有下页数据）
        }

        rimOrderListAdapter.setEnableLoadMore(true);//自动加载更多
        swipeLayouts.setRefreshing(false);//加载完成,不显示进度条

    }

    @Override
    public void setError() {
        rimOrderListAdapter.setEnableLoadMore(true);//自动加载更多
        swipeLayouts.setRefreshing(false);//加载完成,不显示进度条
        rimOrderListAdapter.setEmptyView(notDataView);
        rimOrderListAdapter.setNewData(new ArrayList<SJOrder>());
        ToastUtil.show(mActivity, "加载失败");
    }

    @Override
    public void setEmptyData() {
        SJOrder siorder = new SJOrder();
        siorder.setData(new ArrayList<>());
        rimOrderListAdapter.setNewData(siorder.getData());
        rimOrderListAdapter.notifyDataSetChanged();
    }

    @Override
    public void requestFinish(String msg, boolean isRefresh) {
        ToastUtil.show(getActivity(), msg);
        if (isRefresh) {
//            swipeLayouts.setRefreshing(true);
            statusChange();
        }
    }

    @Override
    public void getRimComplainDetailFinish(boolean isFinish, Bundle bundle, SJComplain data) {
        if (isFinish) {//已投诉则跳投诉详情页面
            Bundle bun = new Bundle();
            bun.putSerializable("SJComplain", data);
            startActivity(RimComplainDetailActivity.class, bun);
        } else {//未投诉则跳投诉页面
            startActivity(RimComplainAddActivity.class, bundle);
        }
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
    public void initCancelWindow() {
        cancelPop = new PopupWindow(getActivity());
        View view = getActivity().getLayoutInflater().inflate(R.layout
                .rim_order_cancel_popupwindows, null);
        llCancelpopup = (AutoLinearLayout) view.findViewById(ll_popup);

        cancelPop.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        cancelPop.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        cancelPop.setBackgroundDrawable(new BitmapDrawable());
        cancelPop.setFocusable(true);
        cancelPop.setOutsideTouchable(true);
        cancelPop.setContentView(view);

        RelativeLayout parent = (RelativeLayout) view.findViewById(R.id.parent);
        TextView reason1 = (TextView) view.findViewById(R.id.tv_reason1);
        TextView reason2 = (TextView) view.findViewById(R.id.tv_reason2);
        TextView reason3 = (TextView) view.findViewById(R.id.tv_reason3);
        TextView cancel = (TextView) view.findViewById(R.id.cancel);

        LinkedHashMap<String, String> map = new LinkedHashMap<>();
        map.put("uuId", Contains.uuid);
        map.put("orderNumber", rimOrderListAdapter.getData().get(curPosition)
                .getOrderNumber());

        parent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cancelPop.dismiss();
                llCancelpopup.clearAnimation();
            }
        });
        //信息填写错误
        reason1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                map.put("orderCancelRemark", getText(reason1));
                mPresenter.requestCancelOrder(map);
                cancelPop.dismiss();
                llCancelpopup.clearAnimation();
            }
        });
        //我不想要了
        reason2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                map.put("orderCancelRemark", getText(reason2));
                mPresenter.requestCancelOrder(map);
                cancelPop.dismiss();
                llCancelpopup.clearAnimation();
            }
        });
        //其它
        reason3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                map.put("orderCancelRemark", getText(reason3));
                mPresenter.requestCancelOrder(map);
                cancelPop.dismiss();
                llCancelpopup.clearAnimation();
            }
        });
        cancel.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                cancelPop.dismiss();
                llCancelpopup.clearAnimation();
            }
        });
    }

    @Override
    public void statusChange() {
//        mFragmentCallback.orderChangedStatus("",1);
//        parentFragment.fun();
        mActivity.fun();
    }

    @Override
    public void initPayWindow() {
        payPop = new PopupWindow(getActivity());
        View view = getActivity().getLayoutInflater().inflate(R.layout
                .rim_order_pay_popupwindows, null);
        llPaypopup = (AutoLinearLayout) view.findViewById(pop_pay);
        payPop.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        payPop.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        payPop.setBackgroundDrawable(new BitmapDrawable());
        payPop.setFocusable(true);
        payPop.setOutsideTouchable(true);
        payPop.setContentView(view);

        RelativeLayout parent = (RelativeLayout) view.findViewById(R.id.pop_pay_mask);
        TextView comfirmPay = (TextView) view.findViewById(R.id.tv_comfirm_pay);
        TextView cancalPay = (TextView) view.findViewById(R.id.tv_cancal_pay);
        SmoothCheckBox alipyChecked = (SmoothCheckBox) view.findViewById(R.id.checkBoxAliPay);
        SmoothCheckBox weixinChecked = (SmoothCheckBox) view.findViewById(R.id.checkBoxWeiXin);
        SmoothCheckBox yinlianChecked = (SmoothCheckBox) view.findViewById(R.id.checkBoxYl);

        switch (paymentMethod) {
            case PAY_ALIPY:
                alipyChecked.setChecked(true, true);
                break;
            case PAY_WEIXIN:
                weixinChecked.setChecked(true, true);
                break;
            case PAY_YINLIAN:
                yinlianChecked.setChecked(true, true);
                break;
            default:
                break;
        }

        cancalPay.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                payPop.dismiss();
                llPaypopup.clearAnimation();
            }
        });
        comfirmPay.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
//                ToastUtil.show(getActivity(),"点击成功，走支付流程");
                LinkedHashMap<String, String> map = new LinkedHashMap<>();
                map.put("orderNumber", rimOrderListAdapter.getData().get(curPosition)
                        .getOrderNumber());
                map.put("uuId", Contains.uuid);
                mPresenter.getOrderBuyCheck(map);

                payPop.dismiss();
                llPaypopup.clearAnimation();
            }
        });

        alipyChecked.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alipyChecked.setChecked(!alipyChecked.isChecked(), true);
                if (alipyChecked.isChecked()) {
                    paymentMethod = PAY_ALIPY;
                    if (weixinChecked.isChecked()) {
                        weixinChecked.setChecked(false, true);
                    }
                    if (yinlianChecked.isChecked()) {
                        yinlianChecked.setChecked(false, true);
                    }
                } else {
                    paymentMethod = null;
                }
            }
        });
        weixinChecked.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                weixinChecked.setChecked(!weixinChecked.isChecked(), true);
                if (weixinChecked.isChecked()) {
                    paymentMethod = PAY_WEIXIN;
                    if (alipyChecked.isChecked()) {
                        alipyChecked.setChecked(false, true);
                    }
                    if (yinlianChecked.isChecked()) {
                        yinlianChecked.setChecked(false, true);
                    }
                } else {
                    paymentMethod = null;
                }
            }
        });

        yinlianChecked.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                yinlianChecked.setChecked(!yinlianChecked.isChecked(), true);
                if (yinlianChecked.isChecked()) {
                    paymentMethod = PAY_YINLIAN;
                    if (alipyChecked.isChecked()) {
                        alipyChecked.setChecked(false, true);
                    }
                    if (weixinChecked.isChecked()) {
                        weixinChecked.setChecked(false, true);
                    }
                } else {
                    paymentMethod = null;
                }
            }
        });

    }

    @Override
    public void selectPaymentMethod() {
        if (paymentMethod == null) {
            ToastUtil.show(getActivity(), getResources().getString(R.string.select_payment_method));
            return;
        }

        switch (paymentMethod) {
            case PAY_ALIPY:
                alipayPay();
                break;
            case PAY_WEIXIN:
                weixinPay();
                break;
            case PAY_YINLIAN:
                yinlianPay();
                break;
            default:
                break;
        }
    }

    @Override
    public void alipayPay() {
        //String orderInfo = alipyUtil.getOrderInfo(orderBianhao, orderShop, orderDetails,
        // orderMoney);
        alipyUtil.getOrderInfo(orderBianhao, orderShop, orderShop, orderMoney, new HttpUtils.CallBack
                () {
            @Override
            public void onRequestComplete(String payInfo) {
                Runnable payRunnable = new Runnable() {

                    @Override
                    public void run() {
                        // 构造PayTask 对象
                        PayTask alipay = new PayTask(getActivity());
                        // 调用支付接口，获取支付结果
                        String result = alipay.pay(payInfo, true);

                        Message msg = new Message();
                        msg.what = SDK_PAY_FLAG_RIM;
                        msg.obj = result;
                        mHandler.sendMessage(msg);
                    }
                };

                // 必须异步调用
                Thread payThread = new Thread(payRunnable);
                payThread.start();
            }
        });


    }

    @Override
    public void weixinPay() {
        boolean wx = CxUtil.isWeixinAvilible(getActivity());
        if (!wx) {
            Toast.makeText(getActivity(), getResources().getString(R.string.weixin_no_install),
                    Toast.LENGTH_SHORT).show();
        } else {
            PayContain.weixinPayResult = PayContain.WEI_XIN_CHECKED;
            new CreateOrderThread().start();
        }
    }

    private YinLianPayUtil mYinLianPayUtil = new YinLianPayUtil();

    @Override
    public void yinlianPay() {
//        String yuming_api = "http://www.hnchxwl.com/";
//        if (!StringUitl.isNoEmpty(orderMoney)) {
//            return;
//        }
//        float a = Float.parseFloat(orderMoney) * 100;
//        int money = (int) a;
//        Intent intent = new Intent();
//        intent.setClass(getActivity(), WebViewActivity.class);
//        Bundle ylzf = new Bundle();
//        //Todo 多了一个斜杠？
//        String str = yuming_api + "/CHINAPAY_DEMO/signServlet" +
//                ".do?BusiType=0001&Version=20140728&CommodityMsg=wwxtest&MerPageUrl=" +
//                yuming_api + "/CHINAPAY_DEMO/pgReturn.do&MerBgUrl=" + yuming_api +
//                "/CHINAPAY_DEMO/bgReturn.do&MerId=531121608230001&" +
//                "MerOrderNo=" + orderBianhao + "&OrderAmt=" + money + "&TranDate=" + StringUitl
//                .getNowDateShort() + "&TranTime=" + StringUitl.getTimeShort() + "&MerResv=1";
//        ylzf.putString("name", "银联支付");
//        ylzf.putString("address", str);
////        KLog.i("YinLian",str);
//
//        intent.putExtras(ylzf);
//        PayContain.yinLianPay = PayContain.YIN_LIAN_PAY_CHECKED;
//        startActivity(intent);
        //银联的钱单位 分    ×100
        mYinLianPayUtil.getTN(orderBianhao, "1", new HttpUtils.CallBack() {
            @Override
            public void onRequestComplete(String result) {
                KLog.i(result + "---");
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (StringUitl.isEmpty(result)) {
                            ToastUtil.showShort("验签失败");
                            return;
                        }
                        mYinLianPayUtil.doStartUnionPayPlugin(getActivity(), result, "01");
                    }
                });

            }
        });
    }

    @Override
    public void onAlipaySucceed() {
        statusChange();
    }

    @Override
    public void jump(int i) {
//        parentFragment.getViewPager().setCurrentItem(i);
        mActivity.getViewPager().setCurrentItem(i);
    }

//    @Override
//    public void onLoadMoreRequested() {
//        if (rimOrderListAdapter.getData().size() < pageSize) {
//            //显示的总数都小于一次请求的数量
//            rimOrderListAdapter.loadMoreEnd(true);
//        } else {
//            //当前显示的item大于总数
//            if (mCurrentCounter >= total) {
//                rimOrderListAdapter.loadMoreEnd(mLoadMoreEndGone);//true is gone,false is visible
//            } else {
//                if (isErr) {
//                    //加载更多的逻辑
//                    LinkedHashMap<String, String> data = new LinkedHashMap<>();
//                    data.put("orderStatus", "1");
//                    data.put("uuId", Contains.uuid);
//                    data.put("rows", pageSize + "");
//                    if (rimOrderListAdapter.getData().size() % pageSize == 0) {
//                        data.put("page", (rimOrderListAdapter.getData().size() / pageSize + 1) +
//                                "");
//                    } else {
//                        data.put("page", (rimOrderListAdapter.getData().size() / pageSize + 2) +
//                                "");
//                    }
//                    mPresenter.getRimOrderList(data);
//                } else {
//                    isErr = true;
//                    rimOrderListAdapter.loadMoreFail();
//                }
//            }
//        }
//    }

    //item点击事件
    private OnItemChildClickListener ClickListener = new OnItemChildClickListener() {
        @Override
        public void onSimpleItemChildClick(BaseQuickAdapter adapter, View view, int position) {
            curPosition = position;
            int tag = (int) view.getTag();

            Bundle bundle = new Bundle();
            bundle.putString("orderNumber", rimOrderListAdapter.getData().get(position)
                    .getOrderNumber());
            bundle.putString("complainBusinessNumber", rimOrderListAdapter.getData().get
                    (position).getOrderBusinessNumber());

            switch (tag) {
                case 1: { //投诉 不用刷新视图
                    LinkedHashMap<String, String> map = new LinkedHashMap<>();
                    map.put("orderNumber", rimOrderListAdapter.getData().get(position)
                            .getOrderNumber());
                    map.put("uuId", Contains.uuid);
                    mPresenter.getRimComplainDetail(map, bundle);
                    break;
                }
                case 2: //动态 不用刷新视图
                    startActivity(RimOrderDynamicActivity.class, bundle);
                    break;
                case 3: //取消 刷新视图
                    initCancelWindow();
                    llCancelpopup.startAnimation(AnimationUtils.loadAnimation(
                            getActivity(),
                            R.anim.activity_translate_in));
                    cancelPop.showAtLocation(view, Gravity.BOTTOM, 0, 0);
                    break;
                case 4: // 立即支付
                    // TODO: 2018/12/11 关闭周边商家支付
//                    ToastUtil.showShort("支付通道暂时关闭");

                    initPayWindow();
                    orderBianhao = rimOrderListAdapter.getData().get(position).getOrderNumber();
                    orderShop = rimOrderListAdapter.getData().get(position).getProductNames();
                    orderMoney= rimOrderListAdapter.getData().get(position).getOrderFactMoney()+"";
                    Log.e("wh", "orderMoney这里" + orderMoney);
                    llPaypopup.startAnimation(AnimationUtils.loadAnimation(
                            getActivity(),
                            R.anim.activity_translate_in));
                    payPop.showAtLocation(view, Gravity.BOTTOM, 0, 0);
                    break;
                case 5: //确认送达 打开注释可用
                    LinkedHashMap<String, String> data = new LinkedHashMap<>();
                    data.put("orderNumber", rimOrderListAdapter.getData().get(position)
                            .getOrderNumber());
                    data.put("uuId", Contains.uuid);
                    mPresenter.requestConfirOrder(data);
                    break;
                case 6: { //立即评价
                    Intent intent = new Intent(getActivity(), RimCommentAddActivity.class);
                    intent.putExtra("orderNumber", rimOrderListAdapter.getData().get(position)
                            .getOrderNumber());
                    intent.putExtra("business_logo", rimOrderListAdapter.getData().get
                            (position).getBusinessLogo());
                    startActivityForResult(intent, CODE_REQUEST_COMMENT);
                    break;
                }
                case 7: { //删除订单

                    ConfirmDialog.showDialog(getActivity(), "欣提示", "订单删除将无法恢复？", new ConfirmDialog
                            .OnConfirmListener() {
                        @Override
                        public void onCancel() {

                        }

                        @Override
                        public void onConfirm() {
                            LinkedHashMap<String, String> map = new LinkedHashMap<>();
                            map.put("orderNumber", rimOrderListAdapter.getData().get(position)
                                    .getOrderNumber());
                            map.put("uuId", Contains.uuid);
                            mPresenter.deleteRimOrder(map);
                        }
                    });
                    break;
                }
                case 8: { //查看评价
                    Intent intent = new Intent(getActivity(), RimCommentListActivity.class);
                    intent.putExtra("orderNumber", rimOrderListAdapter.getData().get(position)
                            .getOrderNumber());
                    intent.putExtra("businessNumber", rimOrderListAdapter.getData().get(position).getOrderBusinessNumber());
                    intent.putExtra("logo", rimOrderListAdapter.getData().get(position).getBusiness_logo());
                    startActivity(intent);
//                    startActivityForResult(intent, CODE_REQUEST_COMMENT);
                    break;
                }
                default:
                    break;
            }
        }
    };

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof RimOrderListActivityActivity) {
            mActivity = (RimOrderListActivityActivity) context;
        } else {
            throw new ClassCastException(context.toString()
                    + " must implement PatrolBadgeCallback");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
//        mFragmentCallback = null;
    }

    @Override
    public void onRefresh() {
        initData();
    }

    //微信支付
    public class CreateOrderThread extends Thread {
        @Override
        public void run() {
            String result = WechatPay.createOrder(orderBianhao, orderMoney, orderShop,
                    orderBianhao);
            Message msg = createOrderHandler.obtainMessage();
            msg.what = 0;
            msg.obj = result;
            createOrderHandler.sendMessage(msg);
        }
    }

    //微信支付
    Handler createOrderHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == 0) {
                String result = (String) msg.obj;
                WechatPay.pay(getActivity(), result);
            }
        }
    };

    @Override
    public void onResume() {
        super.onResume();
        if (PayContain.weixinPayResult != null && PayContain.weixinPayResult == PayContain
                .WEI_XIN_CHECKED
                && PayContain.payResult != null && PayContain.payResult == PayContain.PAY_SUCCESS) {
            KLog.i("onResume 微信支付成功");
            PayContain.weixinPayResult = null;
            PayContain.payResult = null;
            onAlipaySucceed();
        } else if (PayContain.weixinPayResult != null && PayContain.weixinPayResult == PayContain
                .WEI_XIN_CHECKED
                && PayContain.payResult != null && PayContain.payResult == PayContain.PAY_FAIL) {
            KLog.i("onResume 微信支付失败");
        }
    }

    //支付宝支付回调
    @SuppressLint("HandlerLeak")
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case SDK_PAY_FLAG_RIM: {
                    PayResult payResult = new PayResult((String) msg.obj);
                    /**
                     * 同步返回的结果必须放置到服务端进行验证（验证的规则请看https://doc.open.alipay.com/doc2/
                     * detail.htm?spm=0.0.0.0.xdvAU6&treeId=59&articleId=103665&
                     * docType=1) 建议商户依赖异步通知
                     */
                    String resultInfo = payResult.getResult();// 同步返回需要验证的信息

                    String resultStatus = payResult.getResultStatus();
                    // 判断resultStatus 为“9000”则代表支付成功，具体状态码代表含义可参考接口文档
                    if (TextUtils.equals(resultStatus, "9000")) {
                        Toast.makeText(getActivity(), "支付成功", Toast.LENGTH_SHORT).show();
                        Log.d("geek", "支付成功result" + payResult.getResult());
                        onAlipaySucceed();
                    } else {
                        // 判断resultStatus 为非"9000"则代表可能支付失败
                        // "8000"代表支付结果因为支付渠道原因或者系统原因还在等待支付结果确认，最终交易是否成功以服务端异步通知为准（小概率状态）
                        if (TextUtils.equals(resultStatus, "8000")) {
                            Toast.makeText(getActivity(), "支付结果确认中", Toast.LENGTH_SHORT).show();
                        } else {
                            // 其他值就可以判断为支付失败，包括用户主动取消支付，或者系统返回的错误
                            Toast.makeText(getActivity(), "支付失败,请重试", Toast.LENGTH_SHORT).show();
                        }
                    }
                    break;
                }
                default:
                    break;
            }
        }

    };

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case CODE_REQUEST_COMMENT: {
                //if (resultCode == RESULT_OK) {
                statusChange();
                break;
                //}
            }
            default:
                super.onActivityResult(requestCode, resultCode, data);
        }

    }
}