package com.yxld.yxchuangxin.ui.activity.camera.presenter;

import android.support.annotation.NonNull;

import com.socks.library.KLog;
import com.yxld.yxchuangxin.Utils.ToastUtil;
import com.yxld.yxchuangxin.application.AppConfig;
import com.yxld.yxchuangxin.base.BaseEntity;
import com.yxld.yxchuangxin.data.api.HttpAPIWrapper;
import com.yxld.yxchuangxin.ui.activity.camera.ModifyFangquActivity;
import com.yxld.yxchuangxin.ui.activity.camera.contract.ModifyFangquContract;

import java.util.Map;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;

/**
 * @author hzp
 * @Package com.yxld.yxchuangxin.ui.activity.camera
 * @Description: presenter of ModifyFangquActivity
 * @date 2017/09/19 17:43:45
 */
public class ModifyFangquPresenter implements ModifyFangquContract.ModifyFangquContractPresenter{

    HttpAPIWrapper httpAPIWrapper;
    private final ModifyFangquContract.View mView;
    private CompositeDisposable mCompositeDisposable;
    private ModifyFangquActivity mActivity;

    @Inject
    public ModifyFangquPresenter(@NonNull HttpAPIWrapper httpAPIWrapper, @NonNull ModifyFangquContract.View view, ModifyFangquActivity activity) {
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
    public void updateFangqu(Map map) {
        mView.showProgressDialog();
        Disposable disposable = httpAPIWrapper.updateFangqu(map)
                .subscribe(new Consumer<BaseEntity>() {
                    @Override
                    public void accept(BaseEntity entity) throws Exception {
                        //isSuccesse
                        mView.closeProgressDialog();
                        ToastUtil.displayShortToast(entity.getMSG());
                        AppConfig.getInstance().mAppActivityManager.finishActivity(ModifyFangquActivity.class);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        KLog.i("onError");
                        throwable.printStackTrace();
                        mView.closeProgressDialog();
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