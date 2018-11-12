package com.yxld.yxchuangxin.ui.activity.ywh.presenter;
import android.support.annotation.NonNull;

import com.socks.library.KLog;
import com.yxld.yxchuangxin.base.BaseEntity;
import com.yxld.yxchuangxin.data.api.HttpAPIWrapper;
import com.yxld.yxchuangxin.entity.YwhFkyj;
import com.yxld.yxchuangxin.ui.activity.ywh.contract.Fkyj1Contract;
import com.yxld.yxchuangxin.ui.activity.ywh.Fkyj1Fragment;

import java.util.Map;

import javax.inject.Inject;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;

/**
 * @author xlei
 * @Package com.yxld.yxchuangxin.ui.activity.ywh
 * @Description: presenter of Fkyj1Fragment
 * @date 2018/11/09 13:26:28
 */
public class Fkyj1Presenter implements Fkyj1Contract.Fkyj1ContractPresenter{

    HttpAPIWrapper httpAPIWrapper;
    private final Fkyj1Contract.View mView;
    private CompositeDisposable mCompositeDisposable;
    private Fkyj1Fragment mFragment;

    @Inject
    public Fkyj1Presenter(@NonNull HttpAPIWrapper httpAPIWrapper, @NonNull Fkyj1Contract.View view, Fkyj1Fragment fragment) {
        mView = view;
        this.httpAPIWrapper = httpAPIWrapper;
        mCompositeDisposable = new CompositeDisposable();
        this.mFragment = fragment;
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
    public void conmitFkyjInfo1(Map map) {
        Disposable disposable = httpAPIWrapper.commitFkyj(map)
                .subscribe(new Consumer<BaseEntity>() {
                    @Override
                    public void accept(BaseEntity order) throws Exception {
                        //这里接收数据项
                        KLog.i("成功的回调" + order.toString());
                        if (order.msg.equals("操作成功")) {
                            mView.setData();
                        } else {
                            mView.setError();
                        }
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

    @Override
    public void commitFkyj2(Map map) {
        Disposable disposable = httpAPIWrapper.getTjyj(map)
                .subscribe(new Consumer<BaseEntity>() {
                    @Override
                    public void accept(BaseEntity order) throws Exception {
                        //这里接收数据项
                        KLog.i("成功的回调" + order.toString());
                        mView.commitFkyjSuccess(order);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        //这里接收onError
                        KLog.i("onError的回调");
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