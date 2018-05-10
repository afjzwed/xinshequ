package com.yxld.yxchuangxin.ui.activity.wuye.presenter;

import android.support.annotation.NonNull;

import com.socks.library.KLog;
import com.yxld.yxchuangxin.data.api.HttpAPIWrapper;
import com.yxld.yxchuangxin.entity.OpenDoorCode;
import com.yxld.yxchuangxin.ui.activity.wuye.YeZhuFragment;
import com.yxld.yxchuangxin.ui.activity.wuye.contract.YeZhuContract;

import java.util.Map;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;

/**
 * @author hu
 * @Package com.yxld.yxchuangxin.ui.activity.wuye
 * @Description: presenter of YeZhuFragment
 * @date 2017/06/06
 */
public class YeZhuPresenter implements YeZhuContract.YeZhuContractPresenter{

    HttpAPIWrapper httpAPIWrapper;
    private YeZhuContract.View mView;
    private CompositeDisposable mCompositeDisposable;

    private YeZhuFragment yeZhuFragment;
    private Disposable mDisposable;

    @Inject
    public YeZhuPresenter(@NonNull HttpAPIWrapper httpAPIWrapper, @NonNull YeZhuContract.View view, YeZhuFragment yeZhuFragment) {
        mView = view;
        this.httpAPIWrapper = httpAPIWrapper;
        mCompositeDisposable = new CompositeDisposable();
        this.yeZhuFragment = yeZhuFragment;
    }
    @Override
    public void subscribe() {

    }

    @Override
    public void unsubscribe() {
        if (!mCompositeDisposable.isDisposed()) {
             mCompositeDisposable.dispose();
        }

        if (mDisposable != null && !mDisposable.isDisposed()) {
            mDisposable.dispose();
        }
        mView = null;
        yeZhuFragment = null;
    }

    Map<String, String> qRMap;
    @Override
    public void getQRCodeInfo(Map map) {
        if (mDisposable != null && !mDisposable.isDisposed()) {
            mDisposable.dispose();
        }
        mView.showProgressDialog();
        qRMap = map;
        Disposable disposable = httpAPIWrapper.getQRCode(map)
                .subscribe(new Consumer<OpenDoorCode>() {
                    @Override
                    public void accept(OpenDoorCode code) throws Exception {
                        //isSuccesse
                        KLog.i("onSuccesse");
                        mView.creatQRcode(code);
                        mView.closeProgressDialog();
                        startTime();
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        //onError
                        KLog.i("onError");
                        mView.closeProgressDialog();
//                        ToastUtil.show(yeZhuFragment.getContext(), yeZhuFragment.getString(R.string.loading_failed_1));
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
    private void startTime() {
        Observable.interval(0, 1, TimeUnit.SECONDS).take(30+ 1)
                .map(new Function<Long, Long>() {
                    @Override
                    public Long apply(@NonNull Long aLong) throws Exception {
                        return 30 - aLong;
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())//发射用的是observeOn
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(@NonNull Disposable disposable) throws Exception {
                        KLog.i("开始倒计时");
                        mDisposable = disposable;
                    }
                })
                .subscribeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Long>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        KLog.i("2");
                    }

                    @Override
                    public void onNext(@NonNull Long remainTime) {
                        KLog.i(remainTime);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        KLog.i("4");
                    }

                    @Override
                    public void onComplete() {
                        KLog.i("倒计时完成");
                        getQRCodeInfo(qRMap);
                    }
                });
    }
}