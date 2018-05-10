package com.yxld.yxchuangxin.ui.activity.wuye.presenter;

import android.support.annotation.NonNull;

import com.socks.library.KLog;
import com.yxld.yxchuangxin.data.api.HttpAPIWrapper;
import com.yxld.yxchuangxin.entity.Accredit;
import com.yxld.yxchuangxin.ui.activity.wuye.FangxingActivity;
import com.yxld.yxchuangxin.ui.activity.wuye.contract.FangxingContract;

import java.util.Map;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;

/**
 * @author hu
 * @Package com.yxld.yxchuangxin.ui.activity.wuye
 * @Description: presenter of FangxingActivity
 * @date 2017/06/13
 */
public class FangxingPresenter implements FangxingContract.FangxingContractPresenter{

    HttpAPIWrapper httpAPIWrapper;
    private FangxingContract.View mView;
    private CompositeDisposable mCompositeDisposable;
    private FangxingActivity mActivity;

    @Inject
    public FangxingPresenter(@NonNull HttpAPIWrapper httpAPIWrapper, @NonNull FangxingContract.View view, FangxingActivity activity) {
        mView = view;
        this.httpAPIWrapper = httpAPIWrapper;
        mCompositeDisposable = new CompositeDisposable();
        mActivity = activity;
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
    public void getAccreditListProprietor(Map map) {
        mView.showProgressDialog();
        Disposable disposable = httpAPIWrapper.getAccreditListProprietor(map)
                .subscribe(new Consumer<Accredit>() {
                    @Override
                    public void accept(Accredit accredit) throws Exception {
                        //isSuccesse
                        KLog.i("onSuccesse");
                        mView.closeProgressDialog();
                        mView.setAdapter(accredit);
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

    @Override
    public void getAccreditListRent(Map map) {
        mView.showProgressDialog();
        Disposable disposable = httpAPIWrapper.getAccreditListRent(map)
                .subscribe(new Consumer<Accredit>() {
                    @Override
                    public void accept(Accredit accredit) throws Exception {
                        //isSuccesse
                        KLog.i("onSuccesse");
                        mView.closeProgressDialog();
                        mView.setAdapter(accredit);
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

    @Override
    public void getAccreditDetail(Map map) {

    }
}