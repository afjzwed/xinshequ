package com.yxld.yxchuangxin.ui.activity.goods.presenter;

import android.support.annotation.NonNull;

import com.yxld.yxchuangxin.base.BaseEntity;
import com.yxld.yxchuangxin.contain.Contains;
import com.yxld.yxchuangxin.data.api.HttpAPIWrapper;
import com.yxld.yxchuangxin.entity.AddressEntity;
import com.yxld.yxchuangxin.entity.CxwyMallAdd;
import com.yxld.yxchuangxin.ui.activity.goods.contract.AddressManageContract;
import com.yxld.yxchuangxin.ui.activity.goods.AddressManageActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Consumer;

/**
 * @author hu
 * @Package com.yxld.yxchuangxin.ui.activity.goods
 * @Description: presenter of AddressManageActivity
 * @date 2017/06/22 17:20:34
 */
public class AddressManagePresenter implements AddressManageContract.AddressManageContractPresenter {

    HttpAPIWrapper httpAPIWrapper;
    private AddressManageContract.View mView;
    private CompositeDisposable mCompositeDisposable;
    private AddressManageActivity mActivity;

    @Inject
    public AddressManagePresenter(@NonNull HttpAPIWrapper httpAPIWrapper, @NonNull AddressManageContract.View view, AddressManageActivity activity) {
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
    }

    @Override
    public void getAddressDataFromServer() {
        mView.showProgressDialog();
        Map<String, String> params = new HashMap<>();
        params.put("uuid", Contains.uuid);
        mCompositeDisposable.add(httpAPIWrapper.getAddressList(params).subscribe(new Consumer<CxwyMallAdd>() {
            @Override
            public void accept(@io.reactivex.annotations.NonNull CxwyMallAdd cxwyMallAdd) throws Exception {
                mView.closeProgressDialog();
                mView.onAddressDataBacked(cxwyMallAdd);
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

    @Override
    public void updateAddress(CxwyMallAdd address, int status, int position) {
        mView.showProgressDialog();
        Map<String, String> params = new HashMap<>();
        params.put("uuid", Contains.uuid);
        params.put("add.addAdd", address.getAddAdd());
        params.put("add.addId", address.getAddId() + "");
        params.put("add.addName", address.getAddName());
        params.put("add.addStatus", status + "");
        params.put("add.addTel", address.getAddTel());
        params.put("add.addVillage", address.getAddVillage());
        mCompositeDisposable.add(httpAPIWrapper.updateAddress(params)
                .subscribe(new Consumer<BaseEntity>() {
                    @Override
                    public void accept(@io.reactivex.annotations.NonNull BaseEntity entity) throws Exception {
                        mView.closeProgressDialog();
                        mView.onAddressChangedSucceed(entity, status, position);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(@io.reactivex.annotations.NonNull Throwable throwable) throws Exception {
                        mView.closeProgressDialog();
                        mView.onLoadDataFailed();
                    }
                }, new Action() {
                    @Override
                    public void run() throws Exception {

                    }
                }));
    }


    @Override
    public void deleteAddress(String addressId, int pos) {
        mView.showProgressDialog();
        Map<String, String> params = new HashMap<>();
        params.put("uuid", Contains.uuid);
        params.put("add.addId", addressId);
        mCompositeDisposable.add(httpAPIWrapper.deleteAddress(params).subscribe(new Consumer<BaseEntity>() {
            @Override
            public void accept(@io.reactivex.annotations.NonNull BaseEntity entity) throws Exception {
                mView.closeProgressDialog();
                mView.onDeleteAddressSucceed(entity, pos);
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