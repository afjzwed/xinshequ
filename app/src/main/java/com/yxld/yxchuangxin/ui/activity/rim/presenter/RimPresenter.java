package com.yxld.yxchuangxin.ui.activity.rim.presenter;

import android.support.annotation.NonNull;

import com.socks.library.KLog;
import com.yxld.yxchuangxin.data.api.HttpAPIWrapper;
import com.yxld.yxchuangxin.entity.CxwyBusiness;
import com.yxld.yxchuangxin.ui.activity.rim.contract.RimContract;

import java.util.Map;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;

/**
 * @author hu
 * @Package com.yxld.yxchuangxin.ui.activity.rim
 * @Description: presenter of RimActivity
 * @date 2017/06/12
 */
public class RimPresenter implements RimContract.RimContractPresenter {

    HttpAPIWrapper httpAPIWrapper;
    private final RimContract.View mView;
    private CompositeDisposable mCompositeDisposable;

    @Inject
    public RimPresenter(@NonNull HttpAPIWrapper httpAPIWrapper, @NonNull RimContract.View view) {
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
    }


    @Override
    public void loadShangJiaList(Map<String, String> map) {
        Disposable disposable = httpAPIWrapper.getBusinessList(map)
                .subscribe(new Consumer<CxwyBusiness>() {
                    @Override
                    public void accept(CxwyBusiness business) throws Exception {
                        KLog.i("onSuccesse");
                        mView.setShangJiaList(business);
                        mView.closeProgressDialog();
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        KLog.i("onError"+throwable.toString());
                        mView.closeProgressDialog();
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
}