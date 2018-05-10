package com.yxld.yxchuangxin.ui.activity.wuye.presenter;

import android.Manifest;
import android.support.annotation.NonNull;

import com.socks.library.KLog;
import com.yanzhenjie.permission.AndPermission;
import com.yanzhenjie.permission.PermissionListener;
import com.yxld.yxchuangxin.R;
import com.yxld.yxchuangxin.Utils.ToastUtil;
import com.yxld.yxchuangxin.Utils.UpdateManager;
import com.yxld.yxchuangxin.base.BaseEntity;
import com.yxld.yxchuangxin.data.api.HttpAPIWrapper;
import com.yxld.yxchuangxin.entity.CxwyAppVersion;
import com.yxld.yxchuangxin.ui.activity.wuye.UpdateActivity;
import com.yxld.yxchuangxin.ui.activity.wuye.contract.UpdateContract;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;

/**
 * @author hu
 * @Package com.yxld.yxchuangxin.ui.activity.wuye
 * @Description: presenter of UpdateActivity
 * @date 2017/06/23 09:25:59
 */
public class UpdatePresenter implements UpdateContract.UpdateContractPresenter{

    HttpAPIWrapper httpAPIWrapper;
    private UpdateContract.View mView;
    private CompositeDisposable mCompositeDisposable;
    private UpdateActivity mActivity;

    private CxwyAppVersion mVersion;

    /** 动态获取定位权限*/
    public final static int REQUEST_CODE_ASK_WRITE_EXTERNAL_STORAGE = 124;

    @Inject
    public UpdatePresenter(@NonNull HttpAPIWrapper httpAPIWrapper, @NonNull UpdateContract.View view, UpdateActivity activity) {
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
    public void getLastVersion() {
        mView.showProgressDialog();
        Map<String, String> map = new HashMap<>();
        Disposable disposable = httpAPIWrapper.getAppVersionInfo(map)
                .subscribe(new Consumer<CxwyAppVersion>() {
                    @Override
                    public void accept(CxwyAppVersion version) throws Exception {
                        //isSuccesse
                        KLog.i("onSuccesse");
                        mView.closeProgressDialog();
                        mView.setLastVersion(version);
                        mVersion = version;
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
    public void getUpdatePermission() {
        AndPermission.with(mActivity)
                .requestCode(REQUEST_CODE_ASK_WRITE_EXTERNAL_STORAGE)
                .permission(
                        Manifest.permission.WRITE_EXTERNAL_STORAGE
                )
                .rationale((requestCode, rationale) -> {
                            AndPermission.rationaleDialog(mActivity, rationale).show();
                        }
                )
                .callback(permissionListener)
                .start();
    }

    private PermissionListener permissionListener = new PermissionListener() {
        @Override
        public void onSucceed(int requestCode, List<String> grantedPermissions) {
            // 权限申请成功回调。
            if (requestCode == REQUEST_CODE_ASK_WRITE_EXTERNAL_STORAGE) {
                alertUpdate();
            }
        }

        @Override
        public void onFailed(int requestCode, List<String> deniedPermissions) {
            // 权限申请失败回调。
            if(requestCode == REQUEST_CODE_ASK_WRITE_EXTERNAL_STORAGE) {
                // TODO ...
            }
        }
    };
    private  void alertUpdate(){
        // 这里来检测版本是否需要更新
        UpdateManager mUpdateManager = new UpdateManager(mActivity, mVersion.getVer().getVersionDownloadUrl());
        mUpdateManager.checkUpdateInfo(mVersion.getVer().getVersionUId(),mVersion.getVer().getVersionExplain(),mVersion.getVer().getVersionIsCompulsory());
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
                            mView.loginOutSuccess();
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