package com.yxld.yxchuangxin.ui.activity.mine.presenter;

import android.support.annotation.NonNull;

import com.socks.library.KLog;
import com.yxld.yxchuangxin.Utils.StringUitl;
import com.yxld.yxchuangxin.Utils.ToastUtil;
import com.yxld.yxchuangxin.Utils.VersionUtil;
import com.yxld.yxchuangxin.application.AppConfig;
import com.yxld.yxchuangxin.data.api.HttpAPIWrapper;
import com.yxld.yxchuangxin.db.green.UserInfo;
import com.yxld.yxchuangxin.entity.LoginEntity;
import com.yxld.yxchuangxin.ui.activity.mine.MultiAccountActivity;
import com.yxld.yxchuangxin.ui.activity.mine.contract.MultiAccountContract;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;

/**
 * @author hzp
 * @Package com.yxld.yxchuangxin.ui.activity.mine
 * @Description: presenter of MultiAccountActivity
 * @date 2017/10/11 09:31:27
 */
public class MultiAccountPresenter implements MultiAccountContract.MultiAccountContractPresenter{

    HttpAPIWrapper httpAPIWrapper;
    private final MultiAccountContract.View mView;
    private CompositeDisposable mCompositeDisposable;
    private MultiAccountActivity mActivity;

    @Inject
    public MultiAccountPresenter(@NonNull HttpAPIWrapper httpAPIWrapper, @NonNull MultiAccountContract.View view, MultiAccountActivity activity) {
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
    public void switchAccount(UserInfo userInfo) {
        mView.showProgressDialog();
        Map<String, String> params = new HashMap<String, String>();
        params.put("shouji", userInfo.getPhoneNumber());
        params.put("pwd", StringUitl.getMD5(userInfo.getPassword()));
        params.put("macAddr", StringUitl.getDeviceId(AppConfig.getInstance()));
        params.put("id", userInfo.getXiangmuId() + "");
        params.put("app_version", VersionUtil.getAppVersionName(AppConfig.getInstance()));
        params.put("mobile_type", "A");
        params.put("mobile_brand", StringUitl.getPhoneBrand() + StringUitl.getPhoneModel());
        params.put("mobile_version", StringUitl.getPhoneSysVersion());
        login(params, userInfo);
    }

    @Override
    public void login(Map map, UserInfo userInfo) {
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
                        mView.loginSuccessPre(loginEntity);
                        loginSuceess(userInfo);
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

    private void loginSuceess(UserInfo userInfo) {
        KLog.i("登录成功，。修改数据库");
        List<UserInfo> list = AppConfig.getGreenDaoSession().getUserInfoDao().loadAll();
        for (int i = 0; i < list.size(); i++) {
            UserInfo userInfo1 = list.get(i);
            if (userInfo1.getPhoneNumber().equals(userInfo.getPhoneNumber()) && userInfo1.getLouPan().equals(userInfo.getLouPan())) {
                KLog.i("修改为登录状态" + userInfo1.getLouPan());
                userInfo1.setIsLogin(true);
                AppConfig.getGreenDaoSession().getUserInfoDao().update(userInfo1);
            } else {
                userInfo1.setIsLogin(false);
                KLog.i("修改为未登录状态" + userInfo1.getLouPan());
                AppConfig.getGreenDaoSession().getUserInfoDao().update(userInfo1);
            }
        }
        mView.loginSuccess(userInfo);
    };
}