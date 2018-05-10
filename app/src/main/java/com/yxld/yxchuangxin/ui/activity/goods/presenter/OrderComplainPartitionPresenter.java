package com.yxld.yxchuangxin.ui.activity.goods.presenter;

import android.support.annotation.NonNull;

import com.yxld.yxchuangxin.contain.Contains;
import com.yxld.yxchuangxin.data.api.HttpAPIWrapper;
import com.yxld.yxchuangxin.entity.CxwyComplain;
import com.yxld.yxchuangxin.entity.OrderComplainEntity;
import com.yxld.yxchuangxin.entity.goods.MallOrderSuggest;
import com.yxld.yxchuangxin.ui.activity.goods.contract.OrderComplainPartitionContract;
import com.yxld.yxchuangxin.ui.activity.goods.OrderComplainPartitionFragment;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;

/**
 * @author Yuan.Y.Q
 * @Package com.yxld.yxchuangxin.ui.activity.goods
 * @Description: presenter of OrderComplainPartitionFragment
 * @date 2017/06/22 18:31:29
 */
public class OrderComplainPartitionPresenter implements OrderComplainPartitionContract.OrderComplainPartitionContractPresenter {

    HttpAPIWrapper httpAPIWrapper;
    private OrderComplainPartitionContract.View mView;
    private CompositeDisposable mCompositeDisposable;
    private OrderComplainPartitionFragment mFragment;

    @Inject
    public OrderComplainPartitionPresenter(@NonNull HttpAPIWrapper httpAPIWrapper, @NonNull OrderComplainPartitionContract.View view, OrderComplainPartitionFragment fragment) {
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
        }
        mView = null;
    }

    @Override
    public void getOrderComplainDataFromServer(String type, int nextPage, int onePageSize) {
        Map<String, String> params = new HashMap<>();
        params.put("bianhao", type);
        params.put("uuid", Contains.uuid);

        Disposable disposable = httpAPIWrapper.getSuggestList(params).subscribe(new Consumer<MallOrderSuggest>() {
            @Override
            public void accept(@io.reactivex.annotations.NonNull MallOrderSuggest mallOrderSuggest) throws Exception {
                mView.onOrderComplainDataBacked(mallOrderSuggest);
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
//        Observable<OrderComplainEntity> observable = null;
//        if ("未处理".equals(type)) {
//            observable = httpAPIWrapper.getOrderComplainNotDeal(params);
//        } else if ("处理中".equals(type)) {
//            observable = httpAPIWrapper.getOrderComplainNotDeal(params);//未处理的接口
////            observable = httpAPIWrapper.getOrderComplainDealing(params);
//        } else {
//            observable = httpAPIWrapper.getOrderComplainDealed(params);
//        }
//        mCompositeDisposable.add(observable.subscribe(new Consumer<OrderComplainEntity>() {
//            @Override
//            public void accept(@io.reactivex.annotations.NonNull OrderComplainEntity cxwyComplain) throws Exception {
//                mView.onOrderComplainDataBacked(cxwyComplain);
//            }
//        }, new Consumer<Throwable>() {
//            @Override
//            public void accept(@io.reactivex.annotations.NonNull Throwable throwable) throws Exception {
//                mView.onLoadDataFailed();
//            }
//        }, new Action() {
//            @Override
//            public void run() throws Exception {
//
//            }
//        }));
    }


}