package com.yxld.yxchuangxin.ui.activity.ywh.presenter;
import android.support.annotation.NonNull;

import com.socks.library.KLog;
import com.yxld.yxchuangxin.base.BaseEntity;
import com.yxld.yxchuangxin.data.api.HttpAPIWrapper;
import com.yxld.yxchuangxin.entity.SJOrder;
import com.yxld.yxchuangxin.entity.YwhFkyj;
import com.yxld.yxchuangxin.ui.activity.ywh.contract.Fkyj2Contract;
import com.yxld.yxchuangxin.ui.activity.ywh.Fkyj2Fragment;

import java.util.LinkedHashMap;
import java.util.Map;

import javax.inject.Inject;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;

/**
 * @author xlei
 * @Package com.yxld.yxchuangxin.ui.activity.ywh
 * @Description: presenter of Fkyj2Fragment
 * @date 2018/11/09 13:42:26
 */
public class Fkyj2Presenter implements Fkyj2Contract.Fkyj2ContractPresenter{

    HttpAPIWrapper httpAPIWrapper;
    private final Fkyj2Contract.View mView;
    private CompositeDisposable mCompositeDisposable;
    private Fkyj2Fragment mFragment;

    @Inject
    public Fkyj2Presenter(@NonNull HttpAPIWrapper httpAPIWrapper, @NonNull Fkyj2Contract.View view, Fkyj2Fragment fragment) {
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
    public void getData1(LinkedHashMap<String, String> map, boolean isRefresh) {
        Disposable disposable = httpAPIWrapper.getFkyjList(map)
                .subscribe(new Consumer<YwhFkyj>() {
                    @Override
                    public void accept(YwhFkyj order) throws Exception {
                        //这里接收数据项
                        KLog.i("成功的回调" + order.toString());
                        if (order.isSuccess()) {
                            if (isRefresh) {
                                mView.setData(true, order);
                            } else {
                                mView.setData(false, order);
                            }
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
    public void getData3(LinkedHashMap<String, String> map, boolean isRefresh) {
        Disposable disposable = httpAPIWrapper.getFkyjList(map)
                .subscribe(new Consumer<YwhFkyj>() {
                    @Override
                    public void accept(YwhFkyj order) throws Exception {
                        //这里接收数据项
                        KLog.i("成功的回调" + order.toString());
                        if (order.isSuccess()) {
                            if (isRefresh) {
                                mView.setData(true, order);
                            } else {
                                mView.setData(false, order);
                            }
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
    public void getData2(Map map) {
        Disposable disposable = httpAPIWrapper.getLhlb(map)
                .subscribe(new Consumer<YwhFkyj>() {
                    @Override
                    public void accept(YwhFkyj order) throws Exception {
                        //这里接收数据项
                        KLog.i("成功的回调" + order.toString());
                    mView.setData2(order);
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