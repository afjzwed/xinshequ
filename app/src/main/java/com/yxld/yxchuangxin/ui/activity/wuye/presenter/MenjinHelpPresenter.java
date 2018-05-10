package com.yxld.yxchuangxin.ui.activity.wuye.presenter;
import android.support.annotation.NonNull;

import com.yxld.yxchuangxin.data.api.HttpAPIWrapper;
import com.yxld.yxchuangxin.ui.activity.wuye.MenjinHelpActivity;
import com.yxld.yxchuangxin.ui.activity.wuye.contract.MenjinHelpContract;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;

/**
 * @author hu
 * @Package com.yxld.yxchuangxin.ui.activity.wuye
 * @Description: presenter of MenjinHelpActivity
 * @date 2017/06/26 15:48:18
 */
public class MenjinHelpPresenter implements MenjinHelpContract.MenjinHelpContractPresenter{

    HttpAPIWrapper httpAPIWrapper;
    private MenjinHelpContract.View mView;
    private CompositeDisposable mCompositeDisposable;
    private MenjinHelpActivity mActivity;

    @Inject
    public MenjinHelpPresenter(@NonNull HttpAPIWrapper httpAPIWrapper, @NonNull MenjinHelpContract.View view, MenjinHelpActivity activity) {
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
}