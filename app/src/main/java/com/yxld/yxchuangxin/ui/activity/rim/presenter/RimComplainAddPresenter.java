package com.yxld.yxchuangxin.ui.activity.rim.presenter;

import android.support.annotation.NonNull;

import com.socks.library.KLog;
import com.yxld.yxchuangxin.base.BaseEntityService;
import com.yxld.yxchuangxin.data.api.HttpAPIWrapper;
import com.yxld.yxchuangxin.ui.activity.rim.contract.RimComplainAddContract;

import java.util.Map;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;

/**
 * @author wwx
 * @Package com.yxld.yxchuangxin.ui.activity.rim
 * @Description: presenter of RimComplainAddActivity
 * @date 2017/06/17
 */
public class RimComplainAddPresenter implements RimComplainAddContract.RimComplainAddContractPresenter{

    HttpAPIWrapper httpAPIWrapper;
    private final RimComplainAddContract.View mView;
    private CompositeDisposable mCompositeDisposable;

    @Inject
    public RimComplainAddPresenter(@NonNull HttpAPIWrapper httpAPIWrapper, @NonNull RimComplainAddContract.View view) {
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
    public void addComplanData(Map map) {
        mView.showProgressDialog();
        Disposable disposable = httpAPIWrapper.addRimComplain(map)
                .subscribe(new Consumer<BaseEntityService>() {
                    @Override
                    public void accept(BaseEntityService baseEntityService) throws Exception {
                        mView.closeProgressDialog();
                        //这里接收数据项
                        if (baseEntityService.getSuccess() == 1) {
                            KLog.i("成功的回调");
                            mView.requestSuccess();
                        } else {
                            mView.onError();
                        }

                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        mView.closeProgressDialog();
                        //这里接收onError
                        KLog.i("onError的回调");
                    }
                }, new Action() {
                    @Override
                    public void run() throws Exception {
                        mView.closeProgressDialog();
                        //这里接收onComplete。
                        KLog.i("run的回调");
                    }
                });
        mCompositeDisposable.add(disposable);
    }
}