package com.yxld.yxchuangxin.ui.activity.camera.presenter;

import android.support.annotation.NonNull;

import com.socks.library.KLog;
import com.yxld.yxchuangxin.base.BaseEntity;
import com.yxld.yxchuangxin.data.api.HttpAPIWrapper;
import com.yxld.yxchuangxin.ui.activity.camera.CameraSettingActivity;
import com.yxld.yxchuangxin.ui.activity.camera.contract.CameraSettingContract;

import java.util.Map;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;

import static com.yxld.yxchuangxin.ui.activity.camera.presenter.DeviceListPresenter.LoginID;

/**
 * @author hu
 * @Package com.yxld.yxchuangxin.ui.activity.camera
 * @Description: presenter of CameraSettingActivity
 * @date 2017/06/21 10:21:20
 */
public class CameraSettingPresenter implements CameraSettingContract.CameraSettingContractPresenter{

    HttpAPIWrapper httpAPIWrapper;
    private final CameraSettingContract.View mView;
    private CompositeDisposable mCompositeDisposable;
    private CameraSettingActivity mActivity;

    @Inject
    public CameraSettingPresenter(@NonNull HttpAPIWrapper httpAPIWrapper, @NonNull CameraSettingContract.View view, CameraSettingActivity activity) {
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

//    @Override
//    public void getUser(HashMap map) {
//        //mView.showProgressDialog();
//        Disposable disposable = httpAPIWrapper.getUser(map)
//                .subscribe(new Consumer<User>() {
//                    @Override
//                    public void accept(User user) throws Exception {
//                        //isSuccesse
//                        KLog.i("onSuccesse");
//                        mView.setText(user);
//                      //mView.closeProgressDialog();
//                    }
//                }, new Consumer<Throwable>() {
//                    @Override
//                    public void accept(Throwable throwable) throws Exception {
//                        //onError
//                        KLog.i("onError");
//                        throwable.printStackTrace();
//                      //mView.closeProgressDialog();
//                      //ToastUtil.show(mActivity, mActivity.getString(R.string.loading_failed_1));
//                    }
//                }, new Action() {
//                    @Override
//                    public void run() throws Exception {
//                        //onComplete
//                        KLog.i("onComplete");
//                    }
//                });
//        mCompositeDisposable.add(disposable);
//    }

    //转换Loginid
    public String getUserID() {
        String usId;
        try {
            usId = "0" + String.valueOf((Integer.parseInt(LoginID) & 0x7fffffff));
            return usId;
        } catch (NumberFormatException e) {
            return LoginID;
        }
    }

    @Override
    public void getCameraUpdate(Map map) {
        mView.showProgressDialog();
        Disposable disposable = httpAPIWrapper.getCameraUpdate(map)
                .subscribe(new Consumer<BaseEntity>() {
                    @Override
                    public void accept(BaseEntity entity) throws Exception {
                        //isSuccesse
                        KLog.i("onSuccesse");
                        mView.closeProgressDialog();
                        mView.setNewPassWord();
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