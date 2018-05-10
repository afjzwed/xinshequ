package com.yxld.yxchuangxin.ui.activity.main.presenter;

import android.support.annotation.NonNull;

import com.socks.library.KLog;
import com.yxld.yxchuangxin.data.api.HttpAPIWrapper;
import com.yxld.yxchuangxin.ui.activity.main.SecondActivity;
import com.yxld.yxchuangxin.ui.activity.main.contract.SecondContract;

import java.util.LinkedHashMap;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;


/**
 * Created by hu on 2017/5/16.
 */

public class SecondPresenter implements SecondContract.SecondPresenter {

    HttpAPIWrapper httpAPIWrapper;
    private final SecondContract.View mView;
    private CompositeDisposable mSubscriptions;
    private SecondActivity mActivity;

    @Inject
    public SecondPresenter(@NonNull HttpAPIWrapper httpAPIWrapper, @NonNull SecondContract.View view, SecondActivity mActivity) {
        mView = view;
        this.httpAPIWrapper = httpAPIWrapper;
        mSubscriptions = new CompositeDisposable();
        this.mActivity = mActivity;
    }

    @Override
    public void subscribe() {

    }

    @Override
    public void unsubscribe() {
        mSubscriptions.dispose();
    }

    @Override
    public void getList(LinkedHashMap<String, String> data) {
        KLog.i(mActivity.data.getData().getList().size());
//        Disposable disposable = httpAPIWrapper.getList(data)
//                .subscribe(new Consumer<ActivityOrder>() {
//                    @Override
//                    public void accept(ActivityOrder order) throws Exception {
//                        //这里接收数据项
//                        KLog.i("成功的回调");
//                        mView.setData(order);
//                    }
//                }, new Consumer<Throwable>() {
//                    @Override
//                    public void accept(Throwable throwable) throws Exception {
//                        //这里接收onError
//                        KLog.i("onError的回调");
//                        mView.setError();
//                    }
//                }, new Action() {
//                    @Override
//                    public void run() throws Exception {
//                        //这里接收onComplete。
//                        KLog.i("run的回调");
//                    }
//                });
//        mSubscriptions.add(disposable);
    }
}
