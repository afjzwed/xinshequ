package com.yxld.yxchuangxin.ui.activity.main.presenter;

import android.support.annotation.NonNull;

import com.yxld.yxchuangxin.data.api.HttpAPIWrapper;
import com.yxld.yxchuangxin.ui.activity.main.contract.TwoContract;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;

/**
 * @author hu
 * @Package com.example.hu.myapplication.ui.activity.main
 * @Description: $description
 * @date 2017/05/18
 */
public class TwoPresenter implements TwoContract.TwoContractPresenter{

    HttpAPIWrapper httpAPIWrapper;
    private final TwoContract.View mView;
    private CompositeDisposable mSubscriptions;

    @Inject
    public TwoPresenter(@NonNull HttpAPIWrapper httpAPIWrapper, @NonNull TwoContract.View view) {
        mView = view;
        this.httpAPIWrapper = httpAPIWrapper;
        mSubscriptions = new CompositeDisposable();
    }
    @Override
    public void subscribe() {

    }

    @Override
    public void unsubscribe() {
        mSubscriptions.dispose();
    }
}