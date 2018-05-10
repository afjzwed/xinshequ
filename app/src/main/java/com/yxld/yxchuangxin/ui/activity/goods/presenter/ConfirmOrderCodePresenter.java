package com.yxld.yxchuangxin.ui.activity.goods.presenter;

import android.support.annotation.NonNull;

import com.yxld.yxchuangxin.base.BaseEntity;
import com.yxld.yxchuangxin.contain.Contains;
import com.yxld.yxchuangxin.data.api.HttpAPIWrapper;
import com.yxld.yxchuangxin.entity.goods.BaseEntityAll;
import com.yxld.yxchuangxin.entity.goods.DiZiJuanGuiZei;
import com.yxld.yxchuangxin.entity.goods.MallNewOrder;
import com.yxld.yxchuangxin.entity.goods.ShopDianZiJuan;
import com.yxld.yxchuangxin.ui.activity.goods.contract.ConfirmOrderCodeContract;
import com.yxld.yxchuangxin.ui.activity.goods.ConfirmOrderCodeActivity;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;

/**
 * @author xlei
 * @Package com.yxld.yxchuangxin.ui.activity.goods
 * @Description: presenter of ConfirmOrderCodeActivity
 * @date 2018/03/21 08:46:31
 */
public class ConfirmOrderCodePresenter implements ConfirmOrderCodeContract.ConfirmOrderCodeContractPresenter {

    HttpAPIWrapper httpAPIWrapper;
    private final ConfirmOrderCodeContract.View mView;
    private CompositeDisposable mCompositeDisposable;
    private ConfirmOrderCodeActivity mActivity;

    @Inject
    public ConfirmOrderCodePresenter(@NonNull HttpAPIWrapper httpAPIWrapper, @NonNull ConfirmOrderCodeContract.View view, ConfirmOrderCodeActivity activity) {
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
                        //mView.onLoadDataFailed();
                    }
                }, new Action() {
                    @Override
                    public void run() throws Exception {

                    }
                });
        mCompositeDisposable.add(disposable);
    }

    @Override
    public void loadOrderRemainDianZiQuan(Map<String, String> params) {
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
    public void getPosOrderDetail(Map<String, String> map) {
        mCompositeDisposable.add(httpAPIWrapper.getPosOrder(map).subscribe(new Consumer<MallNewOrder>() {
            @Override
            public void accept(@io.reactivex.annotations.NonNull MallNewOrder entity) throws Exception {
                mView.getOrderDetailSucceed(entity);
            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(@io.reactivex.annotations.NonNull Throwable throwable) throws Exception {
                mView.getOrderDetailFailed();
            }
        }, new Action() {
            @Override
            public void run() throws Exception {

            }
        }));
    }

    @Override
    public void confirmOrder(Map<String, String> map) {
        mView.showProgressDialog();
        mCompositeDisposable.add(httpAPIWrapper.confirmOrder(map).subscribe(new Consumer<BaseEntityAll>() {
            @Override
            public void accept(@io.reactivex.annotations.NonNull BaseEntityAll entity) throws Exception {
                mView.confirmOrderSucceed(entity);
                mView.closeProgressDialog();
            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(@io.reactivex.annotations.NonNull Throwable throwable) throws Exception {
                mView.closeProgressDialog();
            }
        }, new Action() {
            @Override
            public void run() throws Exception {

            }
        }));
    }

//    @Override
//    public void getUser(HashMap map) {
//        //mView.showProgressDialog();
//        Disposable disposable = httpAPIWrapper.getUser(map)
//                .subscribe(new Consumer<User>() {
//                    @Override
//                    public void accept(User user) throws Exception {
//                        //isSuccesse
//                        KLog.i("onSuccesse");
//                        mView.setText(user);
//                      //mView.closeProgressDialog();
//                    }
//                }, new Consumer<Throwable>() {
//                    @Override
//                    public void accept(Throwable throwable) throws Exception {
//                        //onError
//                        KLog.i("onError");
//                        throwable.printStackTrace();
//                      //mView.closeProgressDialog();
//                      //ToastUtil.show(mActivity, mActivity.getString(R.string.loading_failed_1));
//                    }
//                }, new Action() {
//                    @Override
//                    public void run() throws Exception {
//                        //onComplete
//                        KLog.i("onComplete");
//                    }
//                });
//        mCompositeDisposable.add(disposable);
//    }
}