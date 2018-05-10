package com.yxld.yxchuangxin.ui.activity.wuye.presenter;

import android.support.annotation.NonNull;

import com.socks.library.KLog;
import com.yxld.yxchuangxin.data.api.HttpAPIWrapper;
import com.yxld.yxchuangxin.entity.PrePay;
import com.yxld.yxchuangxin.ui.activity.wuye.PaySelectActivity;
import com.yxld.yxchuangxin.ui.activity.wuye.contract.PaySelectContract;

import java.util.Map;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;

/**
 * @author hu
 * @Package com.yxld.yxchuangxin.ui.activity.wuye
 * @Description: presenter of PaySelectActivity
 * @date 2017/07/07 11:10:46
 */
public class PaySelectPresenter implements PaySelectContract.PaySelectContractPresenter{

    HttpAPIWrapper httpAPIWrapper;
    private PaySelectContract.View mView;
    private CompositeDisposable mCompositeDisposable;
    private PaySelectActivity mActivity;

    @Inject
    public PaySelectPresenter(@NonNull HttpAPIWrapper httpAPIWrapper, @NonNull PaySelectContract.View view, PaySelectActivity activity) {
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
        mView = null;
    }

    @Override
    public void alipyPaySuccess(Map map) {

    }

    @Override
    public void prePay(Map map) {
        mView.showProgressDialog();
        Disposable disposable = httpAPIWrapper.carPreChongzhi(map)
                .subscribe(new Consumer<PrePay>() {
                    @Override
                    public void accept(PrePay prePay) throws Exception {
                        //isSuccesse
                        KLog.i("onSuccesse");
                        mView.closeProgressDialog();
                        if (prePay.getSuccess() == 0) {
                            mView.prePayBack(prePay.getData());
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        //onError
                        KLog.i("onError");
                        throwable.printStackTrace();
                        mView.closeProgressDialog();
//                        ToastUtil.show(mActivity, mActivity.getString(R.string.loading_failed_1));
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