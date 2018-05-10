package com.yxld.yxchuangxin.ui.activity.wuye.presenter;

import android.support.annotation.NonNull;

import com.socks.library.KLog;
import com.yxld.yxchuangxin.data.api.HttpAPIWrapper;
import com.yxld.yxchuangxin.entity.MsgAndSuccess;
import com.yxld.yxchuangxin.ui.activity.wuye.AddfangxingActivity;
import com.yxld.yxchuangxin.ui.activity.wuye.contract.AddfangxingContract;

import java.util.Map;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;

/**
 * @author hu
 * @Package com.yxld.yxchuangxin.ui.activity.wuye
 * @Description: presenter of AddfangxingActivity
 * @date 2017/06/13
 */
public class AddfangxingPresenter implements AddfangxingContract.AddfangxingContractPresenter{

    HttpAPIWrapper httpAPIWrapper;
    private AddfangxingContract.View mView;
    private CompositeDisposable mCompositeDisposable;
    private AddfangxingActivity mActivity;

    @Inject
    public AddfangxingPresenter(@NonNull HttpAPIWrapper httpAPIWrapper, @NonNull AddfangxingContract.View view, AddfangxingActivity activity) {
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
    public void comfirmAccredit(Map map) {
        mView.showProgressDialog();
        Disposable disposable = httpAPIWrapper.comfirmAccredit(map)
                .subscribe(new Consumer<MsgAndSuccess>() {
                    @Override
                    public void accept(MsgAndSuccess success) throws Exception {
                        //isSuccesse
                        KLog.i("onSuccesse");
                        mView.closeProgressDialog();
                        if (success.isSuccess()) {
                            mView.onComfirmSuccess();
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