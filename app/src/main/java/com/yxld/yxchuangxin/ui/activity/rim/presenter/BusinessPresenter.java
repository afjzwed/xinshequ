package com.yxld.yxchuangxin.ui.activity.rim.presenter;

import android.support.annotation.NonNull;
import android.view.View;

import com.socks.library.KLog;
import com.yxld.yxchuangxin.base.BaseEntity;
import com.yxld.yxchuangxin.data.api.HttpAPIWrapper;
import com.yxld.yxchuangxin.entity.CxwyBusinessInfo;
import com.yxld.yxchuangxin.entity.ShopCarList;
import com.yxld.yxchuangxin.ui.activity.rim.BusinessActivity;
import com.yxld.yxchuangxin.ui.activity.rim.contract.BusinessContract;

import java.util.Map;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;

/**
 * @author hu
 * @Package com.yxld.yxchuangxin.ui.activity.rim
 * @Description: presenter of BusinessActivity
 * @date 2017/06/17
 */
public class BusinessPresenter implements BusinessContract.BusinessContractPresenter {

    HttpAPIWrapper httpAPIWrapper;
    private final BusinessContract.View mView;
    private CompositeDisposable mCompositeDisposable;
    private BusinessActivity mActivity;


    @Inject
    public BusinessPresenter(@NonNull HttpAPIWrapper httpAPIWrapper, @NonNull BusinessContract.View view, BusinessActivity activity) {
        mView = view;
        this.httpAPIWrapper = httpAPIWrapper;
        mCompositeDisposable = new CompositeDisposable();
        mActivity = activity;
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
    public void getBusinessInfo(Map map) {
        mView.showProgressDialog();
        Disposable disposable = httpAPIWrapper.getBusinessInfo(map)
                .subscribe(new Consumer<CxwyBusinessInfo>() {
                    @Override
                    public void accept(CxwyBusinessInfo info) throws Exception {
                        //isSuccesse
                        KLog.i("onSuccesse");
                        mView.setFragmentAdapter(info);
                        mView.closeProgressDialog();
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        //onError
                        KLog.i("onError");
                        throwable.printStackTrace();
                        mView.closeProgressDialog();
//                        ToastUtil.showShort(mActivity.getResources().getString(R.string.loading_failed_1));
                    }
                }, new Action() {
                    @Override
                    public void run() throws Exception {
                        //onComplete
                        KLog.i("onComplete");
                    }
                });
        mCompositeDisposable.add(disposable);
    }

    @Override
    public void addShopCar(Map map, View view, String url) {
        Disposable subscribe = httpAPIWrapper.addShopCar(map).subscribe(new Consumer<BaseEntity>() {
            @Override
            public void accept(@io.reactivex.annotations.NonNull BaseEntity baseEntity) throws Exception {
                mView.addShopCarSuccess(baseEntity, view, url);
            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(@io.reactivex.annotations.NonNull Throwable throwable) throws Exception {

            }
        }, new Action() {
            @Override
            public void run() throws Exception {

            }
        });
        mCompositeDisposable.add(subscribe);
    }

    @Override
    public void getShopCarList(Map map) {
        Disposable subscribe = httpAPIWrapper.getShopCarList(map).subscribe(new Consumer<ShopCarList>() {
            @Override
            public void accept(@io.reactivex.annotations.NonNull ShopCarList baseEntity) throws Exception {
                mView.getShopCarListSuccess(baseEntity);
            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(@io.reactivex.annotations.NonNull Throwable throwable) throws Exception {

            }
        }, new Action() {
            @Override
            public void run() throws Exception {

            }
        });
        mCompositeDisposable.add(subscribe);
    }

    @Override
    public void deleteShopCar(Map map) {
        Disposable subscribe = httpAPIWrapper.deleteShopCar(map).subscribe(new Consumer<BaseEntity>() {
            @Override
            public void accept(@io.reactivex.annotations.NonNull BaseEntity baseEntity) throws Exception {
                mView.deleteShopCarSuccess(baseEntity);
            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(@io.reactivex.annotations.NonNull Throwable throwable) throws Exception {

            }
        }, new Action() {
            @Override
            public void run() throws Exception {

            }
        });
        mCompositeDisposable.add(subscribe);
    }

    @Override
    public void updataShopCar(Map map) {
        Disposable subscribe = httpAPIWrapper.updataShopCar(map).subscribe(new Consumer<BaseEntity>() {
            @Override
            public void accept(@io.reactivex.annotations.NonNull BaseEntity baseEntity) throws Exception {
                mView.updataShopCarSuccess(baseEntity);
            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(@io.reactivex.annotations.NonNull Throwable throwable) throws Exception {

            }
        }, new Action() {
            @Override
            public void run() throws Exception {

            }
        });
        mCompositeDisposable.add(subscribe);
    }

    @Override
    public void updataOrder(Map map) {
        Disposable onComplete = httpAPIWrapper.updataOrder(map).subscribe(new Consumer<BaseEntity>() {
            @Override
            public void accept(@io.reactivex.annotations.NonNull BaseEntity baseEntity) throws Exception {
                mView.updataOrderSuccess(baseEntity);
            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(@io.reactivex.annotations.NonNull Throwable throwable) throws Exception {
                KLog.i("onError" + throwable.toString());
            }
        }, new Action() {
            @Override
            public void run() throws Exception {
                KLog.i("onComplete");
            }
        });
        mCompositeDisposable.add(onComplete);
    }


}