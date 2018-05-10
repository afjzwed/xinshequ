package com.yxld.yxchuangxin.ui.activity.goods.presenter;
import android.support.annotation.NonNull;

import com.yxld.yxchuangxin.base.BaseEntity;
import com.yxld.yxchuangxin.data.api.HttpAPIWrapper;
import com.yxld.yxchuangxin.ui.activity.goods.contract.MarketComplainContract;
import com.yxld.yxchuangxin.ui.activity.goods.MarketComplainActivity;

import java.util.Map;

import javax.inject.Inject;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;

/**
 * @author hu
 * @Package com.yxld.yxchuangxin.ui.activity.goods
 * @Description: presenter of MarketComplainActivity
 * @date 2017/06/22 15:30:24
 */
public class MarketComplainPresenter implements MarketComplainContract.MarketComplainContractPresenter{

    HttpAPIWrapper httpAPIWrapper;
    private final MarketComplainContract.View mView;
    private CompositeDisposable mCompositeDisposable;
    private MarketComplainActivity mActivity;

    @Inject
    public MarketComplainPresenter(@NonNull HttpAPIWrapper httpAPIWrapper, @NonNull MarketComplainContract.View view, MarketComplainActivity activity) {
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
    public void toComplain(Map<String, String> params) {
        mView.showProgressDialog();
        mCompositeDisposable.add(httpAPIWrapper.suggestOrder(params)
        .subscribe(new Consumer<BaseEntity>() {
            @Override
            public void accept(@io.reactivex.annotations.NonNull BaseEntity entity) throws Exception {
                mView.closeProgressDialog();
                mView.onComplainSucceed(entity);

            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(@io.reactivex.annotations.NonNull Throwable throwable) throws Exception {
                mView.closeProgressDialog();
                mView.onComplainFailed();
            }
        }, new Action() {
            @Override
            public void run() throws Exception {

            }
        }));
    }

}