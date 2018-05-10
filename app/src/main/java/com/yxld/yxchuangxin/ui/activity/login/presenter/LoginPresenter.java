package com.yxld.yxchuangxin.ui.activity.login.presenter;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;

import com.socks.library.KLog;
import com.yxld.yxchuangxin.Utils.ToastUtil;
import com.yxld.yxchuangxin.data.api.HttpAPIWrapper;
import com.yxld.yxchuangxin.entity.LoginEntity;
import com.yxld.yxchuangxin.ui.activity.login.LoginActivity;
import com.yxld.yxchuangxin.ui.activity.login.contract.LoginContract;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;

/**
 * @author hu
 * @Package com.yxld.yxchuangxin.ui.activity.login
 * @Description: presenter of LoginActivity
 * @date 2017/05/23
 */
public class LoginPresenter implements LoginContract.LoginContractPresenter {

    HttpAPIWrapper httpAPIWrapper;
    private final LoginContract.View mView;
    private CompositeDisposable mCompositeDisposable;
    private String loginTel = "";
    private LoginActivity mActivity;

    @Inject
    public LoginPresenter(@NonNull HttpAPIWrapper httpAPIWrapper, @NonNull LoginContract.View view, @NonNull LoginActivity mActivity) {
        mView = view;
        this.httpAPIWrapper = httpAPIWrapper;
        mCompositeDisposable = new CompositeDisposable();
        this.mActivity = mActivity;

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

    /**
     * 判断是否安装了老版的欣社区
     *
     * @param context
     * @return
     */
    public static boolean isWeixinAvilible(Context context) {
        final PackageManager packageManager = context.getPackageManager();// 获取packagemanager
        List<PackageInfo> pinfo = packageManager.getInstalledPackages(0);// 获取所有已安装程序的包信息
        if (pinfo != null) {
            for (int i = 0; i < pinfo.size(); i++) {
                String pn = pinfo.get(i).packageName;
                if (pn.equals("com.yxld.yxchuangxin")) {
                    return true;
                }
            }
        }

        return false;
    }
}