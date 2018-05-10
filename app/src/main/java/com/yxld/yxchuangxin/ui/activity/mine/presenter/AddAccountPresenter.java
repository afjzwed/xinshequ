package com.yxld.yxchuangxin.ui.activity.mine.presenter;

import android.support.annotation.NonNull;

import com.socks.library.KLog;
import com.yxld.yxchuangxin.Utils.ToastUtil;
import com.yxld.yxchuangxin.data.api.HttpAPIWrapper;
import com.yxld.yxchuangxin.entity.LoginEntity;
import com.yxld.yxchuangxin.ui.activity.mine.AddAccountActivity;
import com.yxld.yxchuangxin.ui.activity.mine.contract.AddAccountContract;

import java.util.Map;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;

/**
 * @author hzp
 * @Package com.yxld.yxchuangxin.ui.activity.mine
 * @Description: presenter of AddAccountActivity
 * @date 2017/10/11 09:49:50
 */
public class AddAccountPresenter implements AddAccountContract.AddAccountContractPresenter{

    HttpAPIWrapper httpAPIWrapper;
    private final AddAccountContract.View mView;
    private CompositeDisposable mCompositeDisposable;
    private AddAccountActivity mActivity;

    @Inject
    public AddAccountPresenter(@NonNull HttpAPIWrapper httpAPIWrapper, @NonNull AddAccountContract.View view, AddAccountActivity activity) {
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
    public void login(Map map) {
        mView.showProgressDialog();
        Disposable disposable = httpAPIWrapper.Login(map)
                .subscribe(new Consumer<LoginEntity>() {
                    @Override
                    public void accept(LoginEntity loginEntity) throws Exception {
                        //isSuccesse
                        if (loginEntity.getStatus() == 2) {
                            ToastUtil.show(mActivity, loginEntity.getMSG());
                            mView.closeProgressDialog();
                            return;
                        }
                        mView.login(loginEntity);
                        mView.closeProgressDialog();
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
                        mView.closeProgressDialog();
                    }
                });
        mCompositeDisposable.add(disposable);
    }

    @Override
    public void loginPlot(Map params) {
        Disposable disposable = httpAPIWrapper.getloginPlot(params)
                .subscribe(loginPhoneEntity -> {
                    mView.setLoginPhone(loginPhoneEntity);
                    KLog.i("onSuccesse");
                }, throwable -> {
                    KLog.i("onError");
                }, () -> {
                    KLog.i("onMiaoshaComplete");
                });
        mCompositeDisposable.add(disposable);
    }
}