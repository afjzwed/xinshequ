package com.yxld.yxchuangxin.ui.activity.rim.presenter;
import android.support.annotation.NonNull;

import com.yxld.yxchuangxin.data.api.HttpAPIWrapper;
import com.yxld.yxchuangxin.ui.activity.rim.RimOrderListActivityActivity;
import com.yxld.yxchuangxin.ui.activity.rim.contract.RimOrderListActivityContract;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;

/**
 * @author xlei
 * @Package com.yxld.yxchuangxin.ui.activity.rim
 * @Description: presenter of RimOrderListActivityActivity
 * @date 2017/12/14 08:25:35
 */
public class RimOrderListActivityPresenter implements RimOrderListActivityContract.RimOrderListActivityContractPresenter{

    HttpAPIWrapper httpAPIWrapper;
    private final RimOrderListActivityContract.View mView;
    private CompositeDisposable mCompositeDisposable;
    private RimOrderListActivityActivity mActivity;

    @Inject
    public RimOrderListActivityPresenter(@NonNull HttpAPIWrapper httpAPIWrapper, @NonNull RimOrderListActivityContract.View view, RimOrderListActivityActivity activity) {
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

}