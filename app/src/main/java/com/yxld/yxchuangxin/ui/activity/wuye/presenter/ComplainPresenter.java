package com.yxld.yxchuangxin.ui.activity.wuye.presenter;
import android.support.annotation.NonNull;

import com.yxld.yxchuangxin.data.api.HttpAPIWrapper;
import com.yxld.yxchuangxin.ui.activity.wuye.ComplainActivity;
import com.yxld.yxchuangxin.ui.activity.wuye.contract.ComplainContract;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;

/**
 * @author hu
 * @Package com.yxld.yxchuangxin.ui.activity.wuye
 * @Description: presenter of ComplainActivity
 * @date 2017/06/20 09:58:43
 */
public class ComplainPresenter implements ComplainContract.ComplainContractPresenter{

    HttpAPIWrapper httpAPIWrapper;
    private ComplainContract.View mView;
    private CompositeDisposable mCompositeDisposable;
    private ComplainActivity mActivity;

    @Inject
    public ComplainPresenter(@NonNull HttpAPIWrapper httpAPIWrapper, @NonNull ComplainContract.View view, ComplainActivity activity) {
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
}