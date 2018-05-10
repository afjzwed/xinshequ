package com.yxld.yxchuangxin.ui.activity.goods.presenter;
import android.support.annotation.NonNull;

import com.yxld.yxchuangxin.base.BaseEntity;
import com.yxld.yxchuangxin.data.api.HttpAPIWrapper;
import com.yxld.yxchuangxin.ui.activity.goods.contract.AddAddressContract;
import com.yxld.yxchuangxin.ui.activity.goods.AddAddressActivity;

import java.util.Map;

import javax.inject.Inject;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;

/**
 * @author hu
 * @Package com.yxld.yxchuangxin.ui.activity.goods
 * @Description: presenter of AddAddressActivity
 * @date 2017/06/22 17:36:39
 */
public class AddAddressPresenter implements AddAddressContract.AddAddressContractPresenter{

    HttpAPIWrapper httpAPIWrapper;
    private final AddAddressContract.View mView;
    private CompositeDisposable mCompositeDisposable;
    private AddAddressActivity mActivity;

    @Inject
    public AddAddressPresenter(@NonNull HttpAPIWrapper httpAPIWrapper, @NonNull AddAddressContract.View view, AddAddressActivity activity) {
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
    public void addAddress(Map<String, String> params) {
        mView.showProgressDialog();
        mCompositeDisposable.add(httpAPIWrapper.addAddress(params)
        .subscribe(new Consumer<BaseEntity>() {
            @Override
            public void accept(@io.reactivex.annotations.NonNull BaseEntity entity) throws Exception {
                mView.closeProgressDialog();
                mView.onAddAddressSucceed(entity);
            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(@io.reactivex.annotations.NonNull Throwable throwable) throws Exception {
                mView.closeProgressDialog();
                mView.onAddAddressFailed();
            }
        }, new Action() {
            @Override
            public void run() throws Exception {

            }
        }));
    }

    @Override
    public void updateAddress(Map<String, String> params) {
        mView.showProgressDialog();
        mCompositeDisposable.add(httpAPIWrapper.updateAddress(params)

        .subscribe(new Consumer<BaseEntity>() {
            @Override
            public void accept(@io.reactivex.annotations.NonNull BaseEntity entity) throws Exception {
                mView.closeProgressDialog();
                mView.onAddAddressSucceed(entity);
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
}