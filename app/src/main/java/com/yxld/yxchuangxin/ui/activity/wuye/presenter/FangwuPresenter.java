package com.yxld.yxchuangxin.ui.activity.wuye.presenter;

import android.support.annotation.NonNull;

import com.yxld.yxchuangxin.data.api.HttpAPIWrapper;
import com.yxld.yxchuangxin.ui.activity.wuye.FangwuActivity;
import com.yxld.yxchuangxin.ui.activity.wuye.contract.FangwuContract;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;

/**
 * @author hu
 * @Package com.yxld.yxchuangxin.ui.activity.wuye
 * @Description: presenter of FangwuActivity
 * @date 2017/06/06
 */
public class FangwuPresenter implements FangwuContract.FangwuContractPresenter{

    HttpAPIWrapper httpAPIWrapper;
    private FangwuContract.View mView;
    private CompositeDisposable mCompositeDisposable;
    private FangwuActivity mActivity;

    @Inject
    public FangwuPresenter(@NonNull HttpAPIWrapper httpAPIWrapper, @NonNull FangwuContract.View view, FangwuActivity activity) {
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
        mView = null;
    }
}