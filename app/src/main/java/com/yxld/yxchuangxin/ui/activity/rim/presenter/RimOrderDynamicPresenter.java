package com.yxld.yxchuangxin.ui.activity.rim.presenter;

import android.support.annotation.NonNull;

import com.socks.library.KLog;
import com.yxld.yxchuangxin.data.api.HttpAPIWrapper;
import com.yxld.yxchuangxin.entity.SJOrderStatus;
import com.yxld.yxchuangxin.ui.activity.rim.contract.RimOrderDynamicContract;

import java.util.Map;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;

/**
 * @author wwx
 * @Package com.yxld.yxchuangxin.ui.activity.rim
 * @Description: presenter of RimOrderDynamicActivity
 * @date 2017/06/17
 */
public class RimOrderDynamicPresenter implements RimOrderDynamicContract.RimOrderDynamicContractPresenter{

    HttpAPIWrapper httpAPIWrapper;
    private final RimOrderDynamicContract.View mView;
    private CompositeDisposable mCompositeDisposable;

    @Inject
    public RimOrderDynamicPresenter(@NonNull HttpAPIWrapper httpAPIWrapper, @NonNull RimOrderDynamicContract.View view) {
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
    public void requestDynamicData(Map map) {
        mView.showProgressDialog();
        Disposable disposable = httpAPIWrapper.getRimOrderState(map)
                .subscribe(new Consumer<SJOrderStatus>() {
                    @Override
                    public void accept(SJOrderStatus info) throws Exception {
                        mView.closeProgressDialog();
                        //这里接收数据项
                        KLog.i("成功的回调"+info.toString());
                        if (info.getSuccess().equals("1")) {
                            mView.requestSuccess(info);
                        } else {
                            mView.requestError(info.getMsg());
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        mView.closeProgressDialog();
                        //这里接收onError
                        KLog.i("onError的回调");
                        mView.requestError("请求失败");
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