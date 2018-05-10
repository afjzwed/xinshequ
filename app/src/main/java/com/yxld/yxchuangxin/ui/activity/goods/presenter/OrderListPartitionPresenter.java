package com.yxld.yxchuangxin.ui.activity.goods.presenter;

import android.support.annotation.NonNull;

import com.socks.library.KLog;
import com.yxld.yxchuangxin.base.BaseEntity;
import com.yxld.yxchuangxin.contain.Contains;
import com.yxld.yxchuangxin.data.api.HttpAPIWrapper;
import com.yxld.yxchuangxin.entity.CxwyMallOrder;
import com.yxld.yxchuangxin.entity.goods.BaseEntityAll;
import com.yxld.yxchuangxin.entity.goods.MallNewOrder;
import com.yxld.yxchuangxin.entity.goods.MallOrderSuggest;
import com.yxld.yxchuangxin.ui.activity.goods.OrderListPartitionFragment;
import com.yxld.yxchuangxin.ui.activity.goods.contract.OrderListPartitionContract;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;

/**
 * @author Yuan.Y.Q
 * @Package com.yxld.yxchuangxin.ui.activity.goods
 * @Description: presenter of OrderListPartitionFragment
 * @date 2017/06/21 17:10:04
 */
public class OrderListPartitionPresenter implements OrderListPartitionContract.OrderListPartitionContractPresenter {
    public static final int STATUS_ORDER_DELETE = 0;//删除订单
    public static final int STATUS_ORDER_CANCEL = 1;//取消订单
    public static final int STATUS_ORDER_CONFIRM = 2;//确认收货


    public static final int STATUS_ORDER_TUI_HUO = 4;//退货
    public static final int STATUS_ORDER_TUI_KUAN = 5;//退款

    public static final int STATUS_PAY_NOW = 6;
    public static final int STATUS_COMMENT_NOW = 7;
    public static final int STATUS_SHOU_HOU = 8;


    HttpAPIWrapper httpAPIWrapper;
    private OrderListPartitionContract.View mView;
    private CompositeDisposable mCompositeDisposable;
    private OrderListPartitionFragment mFragment;

    @Inject
    public OrderListPartitionPresenter(@NonNull HttpAPIWrapper httpAPIWrapper, @NonNull OrderListPartitionContract.View view, OrderListPartitionFragment fragment) {
        mView = view;
        this.httpAPIWrapper = httpAPIWrapper;
        mCompositeDisposable = new CompositeDisposable();
        this.mFragment = fragment;
    }

    @Override
    public void subscribe() {

    }

    @Override
    public void unsubscribe() {
        if (!mCompositeDisposable.isDisposed()) {
            mCompositeDisposable.dispose();
            mCompositeDisposable.clear();
        }
        mView = null;
        mFragment = null;
    }

    @Override
    public void getOrderListFromServer(String orderStatus, int requestPage, int onePageSize) {
        Map<String, String> params = new HashMap<>();
        params.put("rows", onePageSize + "");
        params.put("page", requestPage + "");
        params.put("uuid", Contains.uuid);
        //订单状态 1待支付、2待发货、3待收货、4待评价，为空则查询所有订单
        params.put("state", "全部".equals(orderStatus) ? "" : "待支付".equals(orderStatus) ? "1" :
                "待发货".equals(orderStatus) ? "2" : "待收货".equals(orderStatus) ? "3" : "4");
        Disposable disposable = httpAPIWrapper.getMallOrder(params)
                .subscribe(new Consumer<MallNewOrder>() {
                    @Override
                    public void accept(@io.reactivex.annotations.NonNull MallNewOrder cxwyMallOrder) throws Exception {
                        mView.onOrderDataBacked(cxwyMallOrder);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(@io.reactivex.annotations.NonNull Throwable throwable) throws Exception {
                        mView.onLoadDataFailed();
                    }
                }, new Action() {
                    @Override
                    public void run() throws Exception {
                        KLog.e("Order", orderStatus + " Complete");
                    }
                });
        mCompositeDisposable.add(disposable);
    }

    @Override
    public void updateOrderStatus(String orderId, int status, String reason) {
        Map<String, String> params = new HashMap<>();
        params.put("ordernum", orderId);
        params.put("uuid", Contains.uuid);
        if (status == STATUS_ORDER_DELETE) {
            //删除订单
            Disposable disposable = httpAPIWrapper.hiddenOrder(params)
                    .subscribe(new Consumer<BaseEntity>() {
                        @Override
                        public void accept(@io.reactivex.annotations.NonNull BaseEntity entity) throws Exception {
                            mView.onUpdateOrderStatusSucceed(entity, status);
                        }
                    }, new Consumer<Throwable>() {
                        @Override
                        public void accept(@io.reactivex.annotations.NonNull Throwable throwable) throws Exception {
                            mView.onLoadDataFailed();
                        }
                    }, new Action() {
                        @Override
                        public void run() throws Exception {

                        }
                    });
            mCompositeDisposable.add(disposable);
        } else if (status == STATUS_ORDER_CANCEL) {
            //取消订单
            params.put("reason", reason);
            Disposable disposable = httpAPIWrapper.cancelOrder(params)
                    .subscribe(new Consumer<BaseEntity>() {
                        @Override
                        public void accept(@io.reactivex.annotations.NonNull BaseEntity entity) throws Exception {
                            mView.onUpdateOrderStatusSucceed(entity, status);
                        }
                    }, new Consumer<Throwable>() {
                        @Override
                        public void accept(@io.reactivex.annotations.NonNull Throwable throwable) throws Exception {
                            mView.onLoadDataFailed();
                        }
                    }, new Action() {
                        @Override
                        public void run() throws Exception {

                        }
                    });
            mCompositeDisposable.add(disposable);
        } else if (status == STATUS_ORDER_TUI_HUO) {
            //退货
            params.put("ord.dingdanBeiyong4", reason);
            params.put("ord.dingdanZhuangtai", "退货中");
        } else if (status == STATUS_ORDER_CONFIRM) {
            //确认收货
            params.put("ord.dingdanZhuangtai", "待评价");
        }


    }

    @Override
    public void updateOrderStatusForTuiKuan(String orderId, int status, String reason) {
        Map<String, String> params = new HashMap<>();
        params.put("ord.dingdanId", orderId);
        params.put("uuid", Contains.uuid);
        params.put("ord.dingdanZhuangtai", "退款中");
        params.put("ord.dingdanBeiyong4", reason);
        Disposable disposable = httpAPIWrapper.updateOrderStatusForTuiKuan(params)
                .subscribe(new Consumer<BaseEntity>() {
                    @Override
                    public void accept(@io.reactivex.annotations.NonNull BaseEntity entity) throws Exception {
                        mView.onUpdateOrderStatusSucceed(entity, status);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(@io.reactivex.annotations.NonNull Throwable throwable) throws Exception {
                        mView.onLoadDataFailed();
                    }
                }, new Action() {
                    @Override
                    public void run() throws Exception {

                    }
                });
        mCompositeDisposable.add(disposable);
    }

    @Override
    public void getComplainOrderList(String ordid) {
        Map<String, String> map = new HashMap<>();
        map.put("uuid", Contains.uuid);
        map.put("bianhao", ordid);
        Disposable disposable = httpAPIWrapper.getSuggestList(map).subscribe(new Consumer<MallOrderSuggest>() {
            @Override
            public void accept(@io.reactivex.annotations.NonNull MallOrderSuggest mallOrderSuggest) throws Exception {
                mView.onLoadCompainOrderListSucceed(mallOrderSuggest,ordid);
            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(@io.reactivex.annotations.NonNull Throwable throwable) throws Exception {
                mView.onLoadDataFailed();
            }
        }, new Action() {
            @Override
            public void run() throws Exception {

            }
        });
        mCompositeDisposable.add(disposable);
    }

    @Override
    public void checkPayNow(Map<String, String> container) {
        Map<String, String> params = new HashMap<>();
        params.put("uuid", Contains.uuid);
        params.put("bianhao", container.get("orderBianhao"));
        Disposable disposable = httpAPIWrapper.getGoodsXiaJia(params)
                .subscribe(new Consumer<BaseEntityAll>() {
                    @Override
                    public void accept(@io.reactivex.annotations.NonNull BaseEntityAll entity) throws Exception {
                        mView.onCheckPayNowSucceed(entity, container);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(@io.reactivex.annotations.NonNull Throwable throwable) throws Exception {
                        mView.onLoadDataFailed();
                    }
                }, new Action() {
                    @Override
                    public void run() throws Exception {

                    }
                });
        mCompositeDisposable.add(disposable);
    }

}