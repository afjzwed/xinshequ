package com.yxld.yxchuangxin.ui.activity.rim.presenter;

import android.support.annotation.NonNull;

import com.socks.library.KLog;
import com.yxld.yxchuangxin.data.api.HttpAPIWrapper;
import com.yxld.yxchuangxin.entity.SJOrder;
import com.yxld.yxchuangxin.ui.activity.rim.contract.RimTongzhiListContract;

import java.util.LinkedHashMap;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;

/**
 * @author wwx
 * @Package com.yxld.yxchuangxin.ui.activity.rim
 * @Description: presenter of RimTongzhiListActivity
 * @date 2017/06/17
 */
public class RimTongzhiListPresenter implements RimTongzhiListContract.RimTongzhiListContractPresenter{

    HttpAPIWrapper httpAPIWrapper;
    private final RimTongzhiListContract.View mView;
    private CompositeDisposable mCompositeDisposable;



    @Inject
    public RimTongzhiListPresenter(@NonNull HttpAPIWrapper httpAPIWrapper, @NonNull RimTongzhiListContract.View view) {
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
    public void getRimTongzhiList(LinkedHashMap<String, String> data) {
        Disposable disposable = httpAPIWrapper.getRimTongzhi(data)
                .subscribe(new Consumer<SJOrder>() {
                    @Override
                    public void accept(SJOrder order) throws Exception {
                        //这里接收数据项
                        KLog.i("成功的回调"+order.toString());
                        if(order == null || order.getTotal() == 0){mView.setError();}
                        mView.setData(order);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        //这里接收onError
                        KLog.i("onError的回调");
                        mView.setError();
                    }
                }, new Action() {
                    @Override
                    public void run() throws Exception {
                        //这里接收onComplete。
                        KLog.i("run的回调");
                    }
                });
        mCompositeDisposable.add(disposable);
    }

}