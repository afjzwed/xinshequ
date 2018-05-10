package com.yxld.yxchuangxin.ui.activity.wuye.presenter;

import android.support.annotation.NonNull;

import com.socks.library.KLog;
import com.yxld.yxchuangxin.data.api.HttpAPIWrapper;
import com.yxld.yxchuangxin.entity.CxwyBaoxiu;
import com.yxld.yxchuangxin.ui.activity.wuye.contract.FixInContract;

import java.util.Map;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;

/**
 * @author hu
 * @Package com.yxld.yxchuangxin.ui.activity.wuye
 * @Description: presenter of FixInFragment
 * @date 2017/06/15
 */
public class FixInPresenter implements FixInContract.FixInContractPresenter{

    HttpAPIWrapper httpAPIWrapper;
    private FixInContract.View mView;
    private CompositeDisposable mCompositeDisposable;

    @Inject
    public FixInPresenter(@NonNull HttpAPIWrapper httpAPIWrapper, @NonNull FixInContract.View view) {
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
    public void getRepairAllList(Map map) {
        Disposable disposable = httpAPIWrapper.getRepairAllList(map)
                .subscribe(new Consumer<CxwyBaoxiu>() {
                    @Override
                    public void accept(CxwyBaoxiu baoxiu) throws Exception {
                        //isSuccesse
                        KLog.i("onSuccesse");
                        mView.setAdapter(baoxiu);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        //onError
                        KLog.i("onError");
                        throwable.printStackTrace();
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

    @Override
    public void getRepairOtherList(Map map) {
        Disposable disposable = httpAPIWrapper.getRepairOtherList(map)
                .subscribe(new Consumer<CxwyBaoxiu>() {
                    @Override
                    public void accept(CxwyBaoxiu baoxiu) throws Exception {
                        //isSuccesse
                        KLog.i("onSuccesse");
                        mView.setAdapter(baoxiu);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        //onError
                        KLog.i("onError");
                        throwable.printStackTrace();
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