package com.yxld.yxchuangxin.ui.activity.camera.presenter;

import android.support.annotation.NonNull;

import com.socks.library.KLog;
import com.yxld.yxchuangxin.data.api.HttpAPIWrapper;
import com.yxld.yxchuangxin.entity.FangquEntity;
import com.yxld.yxchuangxin.ui.activity.camera.FangquListActivity;
import com.yxld.yxchuangxin.ui.activity.camera.contract.FangquListContract;

import java.util.Map;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;

/**
 * @author hzp
 * @Package com.yxld.yxchuangxin.ui.activity.camera
 * @Description: presenter of FangquListActivity
 * @date 2017/09/07 01:27:17
 */
public class FangquListPresenter implements FangquListContract.FangquListContractPresenter{

    HttpAPIWrapper httpAPIWrapper;
    private final FangquListContract.View mView;
    private CompositeDisposable mCompositeDisposable;
    private FangquListActivity mActivity;

    @Inject
    public FangquListPresenter(@NonNull HttpAPIWrapper httpAPIWrapper, @NonNull FangquListContract.View view, FangquListActivity activity) {
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
    public void getFangquList(Map map) {
        Disposable disposable = httpAPIWrapper.getFangqu(map)
                .subscribe(new Consumer<FangquEntity>() {
                    @Override
                    public void accept(FangquEntity entity) throws Exception {
                        //isSuccesse
                        mView.setAdapter(entity);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        KLog.i("onError");
                        throwable.printStackTrace();
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