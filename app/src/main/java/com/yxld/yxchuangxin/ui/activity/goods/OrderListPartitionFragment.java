package com.yxld.yxchuangxin.ui.activity.goods;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.yxld.yxchuangxin.R;
import com.yxld.yxchuangxin.Utils.ToastUtil;
import com.yxld.yxchuangxin.Utils.UIUtils;
import com.yxld.yxchuangxin.application.AppConfig;
import com.yxld.yxchuangxin.base.BaseEntity;
import com.yxld.yxchuangxin.contain.PayContain;
import com.yxld.yxchuangxin.entity.goods.BaseEntityAll;
import com.yxld.yxchuangxin.entity.goods.MallNewOrder;
import com.yxld.yxchuangxin.entity.goods.MallNewOrderDetails;
import com.yxld.yxchuangxin.entity.goods.MallOrderSuggest;
import com.yxld.yxchuangxin.ui.activity.goods.component.DaggerOrderListPartitionComponent;
import com.yxld.yxchuangxin.ui.activity.goods.contract.OrderListPartitionContract;
import com.yxld.yxchuangxin.ui.activity.goods.module.OrderListPartitionModule;
import com.yxld.yxchuangxin.ui.activity.goods.presenter.OrderListPartitionPresenter;
import com.yxld.yxchuangxin.ui.adapter.goods.OrderListAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.yxld.yxchuangxin.ui.activity.goods.OrderListActivity.IS_ZITI;

/**
 * @author Yuan.Y.Q
 * @Package com.yxld.yxchuangxin.ui.activity.goods
 * @Description: 订单列表页面下的各分类fragment
 * @date 2017/06/21 17:10:04
 */

public class OrderListPartitionFragment extends MyBaseFragment implements OrderListPartitionContract.View, BaseQuickAdapter.RequestLoadMoreListener, SwipeRefreshLayout.OnRefreshListener {
    public static final int CODE_REQUEST_PAY = 0x000100;
    public static final int CODE_REQUEST_COMMENT = 0x000101;
    public static final int CODE_REQUEST_SHOUHOU = 0x000102;
    private static final String ARG_ORDER_TYPE = "arg_order_type";//此字段对应的fragment不同分类，具体值由mType接收
    private static final String INSTANCE_STATUS = "instance_status";
    private static final String INSTANCE_NEXT_PAGE = "instance_next_page";
    private static final String INSTANCE_TOTAL_PAGE = "instance_total_page";
    private static final int ONE_PAGE_SIZE = 5;


    @BindView(R.id.recycler_order_list)
    RecyclerView recyclerOrderList;
    @BindView(R.id.refresh_order_list)
    SwipeRefreshLayout swipeLayout;

    @Inject
    OrderListPartitionPresenter mPresenter;


    private OrderListAdapter mOrderAdapter;
    private List<MallNewOrder> mOrderDatas;
    private String mType;//fragment不同分类的标题
    private int mNextPage;
    private int mTotalOrderNum;

    private OnOrderChangedStatus mActivityCallback;

    public static OrderListPartitionFragment newInstance(String param) {
        OrderListPartitionFragment fragment = new OrderListPartitionFragment();
        Bundle args = new Bundle();
        args.putString(ARG_ORDER_TYPE, param);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mType = getArguments().getString(ARG_ORDER_TYPE);
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mActivityCallback = (OnOrderChangedStatus) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnHeadlineSelectedListener");
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_order_list, null);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (savedInstanceState != null) {
            mNextPage = savedInstanceState.getInt(INSTANCE_NEXT_PAGE);
            mTotalOrderNum = savedInstanceState.getInt(INSTANCE_TOTAL_PAGE);
        } else {
            mNextPage = 1;
            mTotalOrderNum = 0;

            if (mOrderDatas == null) {
                mOrderDatas = new ArrayList<>();
            }

            mOrderAdapter = new OrderListAdapter(mOrderDatas);
            recyclerOrderList.setLayoutManager(new LinearLayoutManager(getContext()));
            recyclerOrderList.setAdapter(mOrderAdapter);
            UIUtils.configSwipeRefreshLayoutColors(swipeLayout);
            swipeLayout.setOnRefreshListener(this);
            mOrderAdapter.setOnLoadMoreListener(this, recyclerOrderList);
            onOrderClickEvent();
        }
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
    }

    @Override
    public void fetchData() {
        swipeLayout.post(new Runnable() {
            @Override
            public void run() {
                loadDataFromServer(true);
            }
        });
    }

    public void loadDataFromServer(boolean showRefresh) {
        swipeLayout.setRefreshing(showRefresh);
        if (showRefresh) {
            mNextPage = 1;
        }
        mPresenter.getOrderListFromServer(mType, mNextPage, ONE_PAGE_SIZE);
    }

    @Override
    protected void setupFragmentComponent() {
        DaggerOrderListPartitionComponent
                .builder()
                .appComponent(((AppConfig) getActivity().getApplication()).getApplicationComponent())
                .orderListPartitionModule(new OrderListPartitionModule(this))
                .build()
                .inject(this);
    }

    @Override
    public void setPresenter(OrderListPartitionContract.OrderListPartitionContractPresenter presenter) {
        mPresenter = (OrderListPartitionPresenter) presenter;
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
    public void onOrderDataBacked(MallNewOrder orders) {
        swipeLayout.setRefreshing(false);
        mOrderAdapter.loadMoreComplete();
        if (orders.getState() == 1) {
            if (mNextPage == 1) {
                mOrderDatas.clear();
            }
            //Todo RxJavaIO线程处理
            handlerOrderDatas(orders);
            if (mOrderDatas.size() == 0) {
                mOrderAdapter.setEmptyView(R.layout.layout_empty_data, (ViewGroup) recyclerOrderList.getParent());
            }
            mTotalOrderNum = orders.getTotal();
            if (mOrderDatas.size() < mTotalOrderNum) {
                mNextPage++;
            }
            mOrderAdapter.notifyDataSetChanged();
        } else {
            onError(orders.MSG, orders.status);
        }
    }

    private void handlerOrderDatas(MallNewOrder orders) {
        List<MallNewOrder> orderList = orders.getOrders();
        List<MallNewOrderDetails> saleList = orders.getOrderDetails();
        for (MallNewOrder oneOrder : orderList) {
            String orderNum = oneOrder.getBianhao();
            List<MallNewOrderDetails> sales = new ArrayList<>();

            for (MallNewOrderDetails oneSale : saleList) {
                String num = oneSale.getDingdanBianhao();
                if (orderNum.equals(num)) {
                    sales.add(oneSale);
                }
            }
            oneOrder.setOrderDetails(sales);
        }
        mOrderDatas.addAll(orders.getOrders());
    }

    @Override
    public void onLoadDataFailed() {
        swipeLayout.setRefreshing(false);
        ToastUtil.show(getContext(), getResources().getString(R.string.load_failed));
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        swipeLayout.setRefreshing(false);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        mPresenter.unsubscribe();
        mPresenter = null;
        mOrderDatas = null;
        mOrderAdapter = null;
        swipeLayout = null;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mActivityCallback = null;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CODE_REQUEST_PAY && resultCode == CODE_REQUEST_PAY) {
            // TODO: 2017/11/10 支付以后走下面两个方法
            updateDataFromServer();
            mActivityCallback.orderChangedStatus(mType, OrderListPartitionPresenter.STATUS_PAY_NOW);
        } else if (requestCode == CODE_REQUEST_COMMENT && resultCode == CODE_REQUEST_COMMENT) {
            updateDataFromServer();
            mActivityCallback.orderChangedStatus(mType, OrderListPartitionPresenter.STATUS_COMMENT_NOW);
        } else if (requestCode == CODE_REQUEST_SHOUHOU && resultCode == CODE_REQUEST_SHOUHOU) {
            updateDataFromServer();
            mActivityCallback.orderChangedStatus(mType, OrderListPartitionPresenter.STATUS_SHOU_HOU);
        }
    }

    @Override
    public void onLoadMoreRequested() {
        if (mOrderDatas.size() < ONE_PAGE_SIZE || mOrderDatas.size() >= mTotalOrderNum) {
            mOrderAdapter.loadMoreEnd(false);
            return;
        }
        loadDataFromServer(false);
    }

    @Override
    public void onRefresh() {
        loadDataFromServer(true);
    }

    private void onOrderClickEvent() {
        mOrderAdapter.setOnItemClickListener(new OrderListAdapter.OnItemClickListener() {
            @Override
            public void on2DetailActivityClick(MallNewOrder orderId) {
                //查看详情
                Intent intent = new Intent(getContext(), OrderDetailActivity.class);
                //intent.putParcelableArrayListExtra(OrderDetailActivity.KEY_ORDER_DETAIL, (ArrayList<? extends Parcelable>) orderId);
                intent.putExtra(OrderDetailActivity.KEY_ORDER_DETAIL, orderId);
                startActivity(intent);
            }

            @Override
            public void on2ComplainActivityClick(String orderId) {
                //订单投诉
                mPresenter.getComplainOrderList(orderId);
            }

            @Override
            public void onCancelOrderClick(String orderId) {
                //取消订单
                cancelOrder(orderId);
            }

            @Override
            public void onPayNowClick(MallNewOrder order, String shouldPay) {
                //立即支付
                swipeLayout.setRefreshing(true);
                String shopNames = "";
                StringBuilder details = new StringBuilder();
                for (MallNewOrderDetails sale : order.getOrderDetails()) {
                 //   shopNames.append(sale.getShangpinMing());
                    details.append(sale.getShangpinGuige());
                }
                if (order.getOrderDetails().size() > 1) {
                    shopNames = order.getOrderDetails().get(0).getShangpinMing() + "...等";
                } else {
                    shopNames = order.getOrderDetails().get(0).getShangpinMing();
                }
                Map<String, String> container = new HashMap<String, String>();
                container.put("orderId", order.getId() + "");
                container.put("orderMoney", shouldPay);
                container.put("orderShop", shopNames);
                container.put("orderDetails", details.toString());
                container.put("orderBianhao", order.getBianhao());
                // TODO: 2017/11/30 取消请求库存的接口 
                //// TODO: 2017/12/18 增加判断商品是否下架
                mPresenter.checkPayNow(container);
                //设置是否自提还是商城配送 来确定跳转界面 配送方式：1商城配送，2自提 */
                IS_ZITI = order.getPeisongFangshi();
//                Intent intent = new Intent(getContext(), PayWaySelectActivity.class);
//                intent.putExtra("orderId", order.getId() + "");
//                intent.putExtra("orderMoney", shouldPay);
//                intent.putExtra("orderShop", shopNames.toString());
//                intent.putExtra("orderBianhao", order.getBianhao());
//                intent.putExtra("orderDetails", details.toString());
//                intent.putExtra(PayWaySelectActivity.KEY_IN_TYPE, PayWaySelectActivity.IN_TYPE_ORDER_LIST);
//                PayContain.requestPayModule = PayContain.MODULE_MALL_ORDER;
//                startActivityForResult(intent, CODE_REQUEST_PAY);
            }

            @Override
            public void onBackMoneyClick(String orderId) {
                //退款
                tuiKuan(orderId);
            }

            @Override
            public void onBackGoodsClick(String orderId, List<MallNewOrderDetails> list) {
                //退货
                // TODO: 2017/10/25  改成申请售后
                // tuiHuo(orderId);
                ArrayList<MallNewOrderDetails> array = (ArrayList<MallNewOrderDetails>) list;
                Intent intent = new Intent(getActivity(), GoodsSaleActivity.class);
                intent.putExtra("orderId", orderId);
                intent.putParcelableArrayListExtra("list", array);
                startActivityForResult(intent, CODE_REQUEST_SHOUHOU);

            }

            @Override
            public void onConfirmDeliveryClick(String orderId) {
                //确认收货
                confirmShouHuo(orderId);
            }

            @Override
            public void onCommentNowClick(String orderId, List<MallNewOrderDetails> sales) {
                //立即评价
                Intent intent = new Intent(getContext(), CommentAndShowOrderActivity.class);
                intent.putExtra(CommentAndShowOrderActivity.KEY_ORDER_ID, orderId);
                intent.putParcelableArrayListExtra(CommentAndShowOrderActivity.KEY_PRODUCTS, (ArrayList<? extends Parcelable>) sales);
                startActivityForResult(intent, CODE_REQUEST_COMMENT);
            }

            @Override
            public void onDeleteOrderClick(String orderId) {
                //删除订单
                deleteOrder(orderId);
            }

        });
    }

    @Override
    public void onUpdateOrderStatusSucceed(BaseEntity entity, int status) {
        swipeLayout.setRefreshing(false);
        if (entity.getStatus() == 1) {
            ToastUtil.show(getContext(), "操作成功");
            updateDataFromServer();
            mActivityCallback.orderChangedStatus(mType, status);
        } else {
            onError(entity.MSG, entity.status);
        }
    }

    @Override
    public void onLoadCompainOrderListSucceed(MallOrderSuggest mallOrderSuggest, String ordid) {
        if (mallOrderSuggest.getStatus() == 1) {
            if (mallOrderSuggest.getRows() != null && mallOrderSuggest.getRows().size() > 0) {
                //有投诉直接跳到投诉列表
                Intent intent = new Intent(getActivity(), OrderComplainActivity.class);
                intent.putExtra("orderid", mallOrderSuggest.getRows().get(0).getTsjyDindanBianhao());
                intent.putExtra("zhuangtai", mallOrderSuggest.getRows().get(0).getTsjyZhuangtai() + "");
                intent.putExtra("time", mallOrderSuggest.getRows().get(0).getTsjyTijiaoShijian());
                intent.putExtra("content", mallOrderSuggest.getRows().get(0).getTsjyNeirong());
                intent.putExtra("jieguo", mallOrderSuggest.getRows().get(0).getTsjyChuliJieguo());
                startActivity(intent);
            } else {
                Intent intent = new Intent(getActivity(), MarketComplainActivity.class);
                intent.putExtra("orderid", ordid);
                //没有投诉进入投诉
                startActivity(intent);
            }

        } else {
//            onError(mallOrderSuggest.getMsg(), mallOrderSuggest.getStatus());
            onError(mallOrderSuggest.MSG, mallOrderSuggest.status);
        }
    }

    @Override
    public void onCheckPayNowSucceed(BaseEntityAll entity, Map<String, String> params) {
        if (swipeLayout != null && swipeLayout.isRefreshing()) {
            swipeLayout.setRefreshing(false);
        }
        if (entity.status == 1) {
            Intent intent = new Intent(getContext(), PayWaySelectActivity.class);
            intent.putExtra("orderId", entity.getRows());
            intent.putExtra("orderMoney", params.get("orderMoney"));
            intent.putExtra("orderShop", params.get("orderShop"));
            intent.putExtra("orderBianhao", entity.getRows());
            intent.putExtra("orderDetails", params.get("orderDetails"));
            intent.putExtra(PayWaySelectActivity.KEY_IN_TYPE, PayWaySelectActivity.IN_TYPE_ORDER_LIST);
            PayContain.requestPayModule = PayContain.MODULE_MALL_ORDER;
            startActivityForResult(intent, CODE_REQUEST_PAY);
        } else {
            onError(entity.MSG, entity.status);
        }
    }

    private void confirmShouHuo(String orderId) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("提示:确认收货");
        builder.setNegativeButton("取消", null);
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                swipeLayout.setRefreshing(true);
                mPresenter.updateOrderStatus(orderId, OrderListPartitionPresenter.STATUS_ORDER_CONFIRM, null);
            }
        });
        builder.show();
    }

    private void deleteOrder(String orderId) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("提示:订单删除后将无法恢复");
        builder.setNegativeButton("取消", null);
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                swipeLayout.setRefreshing(true);
                mPresenter.updateOrderStatus(orderId, OrderListPartitionPresenter.STATUS_ORDER_DELETE, null);
            }
        });
        builder.show();
    }

    private void cancelOrder(String orderId) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("选择取消原因");
        final String[] cities = {"信息拍写错误", "不想要了", "其它"};
        builder.setItems(cities, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                swipeLayout.setRefreshing(true);
                mPresenter.updateOrderStatus(orderId, OrderListPartitionPresenter.STATUS_ORDER_CANCEL, cities[which]);
            }
        });
        builder.show();
    }

    private void tuiHuo(String orderId) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("请选择退货原因");
        final String[] cities = {"商品问题", "服务问题", "其它"};
        builder.setItems(cities, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                swipeLayout.setRefreshing(true);
                mPresenter.updateOrderStatus(orderId, OrderListPartitionPresenter.STATUS_ORDER_TUI_HUO, cities[which]);
            }
        });
        builder.show();
    }

    private void tuiKuan(String orderId) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("请选择退款原因");
        final String[] cities = {"信息拍写错误", "不想要了", "其它"};
        builder.setItems(cities, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                swipeLayout.setRefreshing(true);
                mPresenter.updateOrderStatusForTuiKuan(orderId, OrderListPartitionPresenter.STATUS_ORDER_TUI_KUAN, cities[which]);
            }
        });
        builder.show();
    }

    public void updateDataFromServer() {
        mTotalOrderNum = 0;
        mNextPage = 1;
        loadDataFromServer(true);
    }

    public interface OnOrderChangedStatus {
        void orderChangedStatus(String type, int changeStatus);
    }


}