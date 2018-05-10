package com.yxld.yxchuangxin.ui.activity.goods.presenter;

import android.support.annotation.NonNull;

import com.yxld.yxchuangxin.base.BaseEntity;
import com.yxld.yxchuangxin.contain.Contains;
import com.yxld.yxchuangxin.data.api.HttpAPIWrapper;
import com.yxld.yxchuangxin.entity.IsNight;
import com.yxld.yxchuangxin.entity.OrderRemainDianZiQuanEntity;
import com.yxld.yxchuangxin.entity.YezhuDainZhiQuan;
import com.yxld.yxchuangxin.entity.goods.BaseEntityAll;
import com.yxld.yxchuangxin.entity.goods.DiZiJuanGuiZei;
import com.yxld.yxchuangxin.entity.goods.ShopDianZiJuan;
import com.yxld.yxchuangxin.ui.activity.goods.contract.ConfirmOrderContract;
import com.yxld.yxchuangxin.ui.activity.goods.ConfirmOrderActivity;

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
 * @Description: presenter of ConfirmOrderActivity
 * @date 2017/06/22 11:07:51
 */
public class ConfirmOrderPresenter implements ConfirmOrderContract.ConfirmOrderContractPresenter {

    HttpAPIWrapper httpAPIWrapper;
    private ConfirmOrderContract.View mView;
    private CompositeDisposable mCompositeDisposable;
    private ConfirmOrderActivity mActivity;

    @Inject
    public ConfirmOrderPresenter(@NonNull HttpAPIWrapper httpAPIWrapper, @NonNull ConfirmOrderContract.View view, ConfirmOrderActivity activity) {
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
        mView = null;
        mActivity = null;
    }

    @Override
    public void loadDianZiQuan() {
        Map<String, String> params = new HashMap<>();
        params.put("uuid", Contains.uuid);
        Disposable disposable = httpAPIWrapper.getShopDizijuan(params)
                .subscribe(new Consumer<ShopDianZiJuan>() {
                    @Override
                    public void accept(@io.reactivex.annotations.NonNull ShopDianZiJuan yezhuDainZhiQuan) throws Exception {
                        mView.onLoadDianZiQuanSucceed(yezhuDainZhiQuan);
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
    public void loadOrderRemainDianZiQuan(Map<String,String> params) {
//        Map<String, String> params = new HashMap<>();
//        params.put("money", "100.00");
//        params.put("uuid", Contains.uuid);
        mCompositeDisposable.add(httpAPIWrapper.getDiZiQuanGuiZei(params).subscribe(new Consumer<DiZiJuanGuiZei>() {
            @Override
            public void accept(@io.reactivex.annotations.NonNull DiZiJuanGuiZei diZiJuanGuiZei) throws Exception {
                mView.onLoadOrderRemainDianZiQuanSucceed(diZiJuanGuiZei);
            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(@io.reactivex.annotations.NonNull Throwable throwable) throws Exception {

            }
        }, new Action() {
            @Override
            public void run() throws Exception {

            }
        }));

    }

    @Override
    public void addMallOrder(Map<String, String> params) {
        Disposable disposable = httpAPIWrapper.jieSuanShopCart(params)
                .subscribe(new Consumer<OrderRemainDianZiQuanEntity>() {
                    @Override
                    public void accept(@io.reactivex.annotations.NonNull OrderRemainDianZiQuanEntity entity) throws Exception {
                        mView.onAddOrderSuccess(entity);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(@io.reactivex.annotations.NonNull Throwable throwable) throws Exception {
                        throwable.printStackTrace();
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
    public void isNight() {
        Map<String, String> map = new HashMap<>();
        map.put("uuid", Contains.uuid);
        Disposable disposable = httpAPIWrapper.isNight(map)
                .subscribe(new Consumer<IsNight>() {
                    @Override
                    public void accept(@io.reactivex.annotations.NonNull IsNight isNight) throws Exception {
                        mView.setIsNight(isNight);
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
    public void getOrder(String bianhao) {
        Map<String, String> map = new HashMap<>();
        map.put("bianhao", bianhao);
        map.put("uuid", Contains.uuid);
        Disposable disposable = httpAPIWrapper.getGoodsOrder(map).subscribe(new Consumer<BaseEntityAll>() {
            @Override
            public void accept(@io.reactivex.annotations.NonNull BaseEntityAll baseEntityAll) throws Exception {
                mView.onLoadOrderSuccess(baseEntityAll);

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