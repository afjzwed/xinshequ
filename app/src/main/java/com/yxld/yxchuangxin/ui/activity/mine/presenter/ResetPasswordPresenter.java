package com.yxld.yxchuangxin.ui.activity.mine.presenter;

import android.support.annotation.NonNull;

import com.socks.library.KLog;
import com.yxld.yxchuangxin.R;
import com.yxld.yxchuangxin.Utils.ToastUtil;
import com.yxld.yxchuangxin.base.BaseEntity;
import com.yxld.yxchuangxin.data.api.HttpAPIWrapper;
import com.yxld.yxchuangxin.ui.activity.mine.ResetPasswordActivity;
import com.yxld.yxchuangxin.ui.activity.mine.contract.ResetPasswordContract;

import java.util.Map;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;

/**
 * @author hu
 * @Package com.yxld.yxchuangxin.ui.activity.mine
 * @Description: presenter of ResetPasswordActivity
 * @date 2017/06/23 11:03:59
 */
public class ResetPasswordPresenter implements ResetPasswordContract.ResetPasswordContractPresenter {

    HttpAPIWrapper httpAPIWrapper;
    private final ResetPasswordContract.View mView;
    private CompositeDisposable mCompositeDisposable;
    private ResetPasswordActivity mActivity;

    @Inject
    public ResetPasswordPresenter(@NonNull HttpAPIWrapper httpAPIWrapper, @NonNull ResetPasswordContract.View view, ResetPasswordActivity activity) {
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
    public void getUpdatePwd(Map map) {
        mView.showProgressDialog();
        Disposable disposable = httpAPIWrapper.getPassUpdate(map)
                .subscribe(new Consumer<BaseEntity>() {
                    @Override
                    public void accept(BaseEntity entity) throws Exception {
                        //isSuccesse
                        KLog.i("onSuccesse");
                        mView.closeProgressDialog();
                        if (entity.getStatus() == 0) {
                            mView.onUpdatePasswordSuccess();
                        } else {
                            ToastUtil.show(mActivity, "修改密码失败");
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

    @Override
    public void getLoginOut(Map map) {
        mView.showProgressDialog();
        Disposable disposable = httpAPIWrapper.getLoginOut(map)
                .subscribe(new Consumer<BaseEntity>() {
                    @Override
                    public void accept(BaseEntity entity) throws Exception {
                        //isSuccesse
                        KLog.i("onSuccesse");
                        mView.closeProgressDialog();
                        if (entity.getStatus() == 0) {
                            mView.loginOut();
                        } else {
                            ToastUtil.show(mActivity, "退出登录失败");
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