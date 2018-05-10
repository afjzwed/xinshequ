package com.yxld.yxchuangxin.ui.activity.xiongmai.presenter;

import android.support.annotation.NonNull;

import com.socks.library.KLog;
import com.yxld.yxchuangxin.data.api.HttpAPIWrapper;
import com.yxld.yxchuangxin.entity.XMsxt;
import com.yxld.yxchuangxin.ui.activity.xiongmai.DeviceLoginActivity;
import com.yxld.yxchuangxin.ui.activity.xiongmai.contract.DeviceLoginContract;

import java.util.Map;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;

/**
 * @author hu
 * @Package com.yxld.yxchuangxin.ui.activity.xiongmai
 * @Description: presenter of DeviceLoginActivity
 * @date 2017/07/18 09:30:54
 */
public class DeviceLoginPresenter implements DeviceLoginContract.DeviceLoginContractPresenter{

    HttpAPIWrapper httpAPIWrapper;
    private final DeviceLoginContract.View mView;
    private CompositeDisposable mCompositeDisposable;
    private DeviceLoginActivity mActivity;

    @Inject
    public DeviceLoginPresenter(@NonNull HttpAPIWrapper httpAPIWrapper, @NonNull DeviceLoginContract.View view, DeviceLoginActivity activity) {
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
    }

    @Override
    public void getSXT(Map map) {
        mView.showProgressDialog();
        Disposable disposable = httpAPIWrapper.getXMsxt(map)
                .subscribe(new Consumer<XMsxt>() {
                    @Override
                    public void accept(XMsxt xMsxt) throws Exception {
                        //isSuccesse
                        KLog.i("onSuccesse");
                        mView.closeProgressDialog();
                        mView.setAdapter(xMsxt);
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