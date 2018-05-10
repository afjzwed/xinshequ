package com.yxld.yxchuangxin.ui.activity.goods.presenter;
import android.support.annotation.NonNull;

import com.yxld.yxchuangxin.contain.Contains;
import com.yxld.yxchuangxin.data.api.HttpAPIWrapper;
import com.yxld.yxchuangxin.entity.CxwyMallOrder;
import com.yxld.yxchuangxin.entity.OrderDetailEntity;
import com.yxld.yxchuangxin.ui.activity.goods.contract.OrderDetailContract;
import com.yxld.yxchuangxin.ui.activity.goods.OrderDetailActivity;

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
 * @Description: presenter of OrderDetailActivity
 * @date 2017/06/22 15:45:54
 */
public class OrderDetailPresenter implements OrderDetailContract.OrderDetailContractPresenter{

    HttpAPIWrapper httpAPIWrapper;
    private final OrderDetailContract.View mView;
    private CompositeDisposable mCompositeDisposable;
    private OrderDetailActivity mActivity;

    @Inject
    public OrderDetailPresenter(@NonNull HttpAPIWrapper httpAPIWrapper, @NonNull OrderDetailContract.View view, OrderDetailActivity activity) {
        mView = view;
        this.httpAPIWrapper = httpAPIWrapper;
        mCompositeDisposable = new CompositeDisposable();
        this.mActivity = activity;
    }
    @Override
    public void subscribe() {

    }

    @Override
    public void unsubscribe() {
        if (!mCompositeDisposable.isDisposed()) {
             mCompositeDisposable.dispose();
        }
    }

    @Override
    public void loadOrderDetailFromServer(String orderId) {
        Map<String,String> params = new HashMap<>();
        params.put("sale.saleDingdanId",orderId);
        params.put("uuid", Contains.uuid);
        Disposable disposable = httpAPIWrapper.getOrderDetail(params)
                .subscribe(new Consumer<OrderDetailEntity>() {
                    @Override
                    public void accept(@io.reactivex.annotations.NonNull OrderDetailEntity order) throws Exception {
                        mView.onLoadOrderDetailSucceed(order);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(@io.reactivex.annotations.NonNull Throwable throwable) throws Exception {
                        throwable.printStackTrace();
                        mView.onLoadOrderDetailFailed();
                    }
                }, new Action() {
                    @Override
                    public void run() throws Exception {

                    }
                });
        mCompositeDisposable.add(disposable);
    }
}