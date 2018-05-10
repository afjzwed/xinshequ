package com.yxld.yxchuangxin.ui.activity.goods.presenter;

import android.support.annotation.NonNull;

import com.yxld.yxchuangxin.contain.Contains;
import com.yxld.yxchuangxin.data.api.HttpAPIWrapper;
import com.yxld.yxchuangxin.entity.StateOrderNum;
import com.yxld.yxchuangxin.ui.activity.goods.contract.MineContract;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.subjects.Subject;

/**
 * @author yuan
 * @Package .ui.activity.goods
 * @Description: presenter of MineFragment
 * @date 2017/06/14
 */
public class MinePresenter implements MineContract.MineContractPresenter {

    HttpAPIWrapper httpAPIWrapper;
    private MineContract.View mView;
    private CompositeDisposable mCompositeDisposable;

    @Inject
    public MinePresenter(@NonNull HttpAPIWrapper httpAPIWrapper, @NonNull MineContract.View view) {
        mView = view;
        this.httpAPIWrapper = httpAPIWrapper;
        mCompositeDisposable = new CompositeDisposable();
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
    public void loadOrderStatusFromServer() {
        mView.showProgressDialog();
        Map<String, String> pamrams = new HashMap<>();
        pamrams.put("uuid", Contains.uuid);
        httpAPIWrapper.getOrderCount(pamrams)
                .subscribe(new Consumer<StateOrderNum>() {
                    @Override
                    public void accept(@io.reactivex.annotations.NonNull StateOrderNum orderNum) throws Exception {
                        mView.closeProgressDialog();
                        mView.onLoadOrderStatusSucceed(orderNum);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(@io.reactivex.annotations.NonNull Throwable throwable) throws Exception {
                        mView.closeProgressDialog();
                        mView.onLoadOrderStatusFailed();
                    }
                }, new Action() {
                    @Override
                    public void run() throws Exception {

                    }
                });
    }

}