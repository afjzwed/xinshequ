package com.yxld.yxchuangxin.ui.activity.camera.presenter;

import android.support.annotation.NonNull;
import android.widget.Toast;

import com.socks.library.KLog;
import com.yxld.yxchuangxin.base.BaseEntity;
import com.yxld.yxchuangxin.data.api.HttpAPIWrapper;
import com.yxld.yxchuangxin.ui.activity.camera.CameraAddActivity;
import com.yxld.yxchuangxin.ui.activity.camera.contract.CameraAddContract;

import java.util.Map;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;

/**
 * @author hu
 * @Package com.yxld.yxchuangxin.ui.activity.camera
 * @Description: presenter of CameraAddActivity
 * @date 2017/06/21 10:21:55
 */
public class CameraAddPresenter implements CameraAddContract.CameraAddContractPresenter{

    HttpAPIWrapper httpAPIWrapper;
    private final CameraAddContract.View mView;
    private CompositeDisposable mCompositeDisposable;
    private CameraAddActivity mActivity;

    @Inject
    public CameraAddPresenter(@NonNull HttpAPIWrapper httpAPIWrapper, @NonNull CameraAddContract.View view, CameraAddActivity activity) {
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
    public void cameraAdd(Map map) {
        mView.showProgressDialog();
        Disposable disposable = httpAPIWrapper.getCameraAdd(map)
                .subscribe(new Consumer<BaseEntity>() {
                    @Override
                    public void accept(BaseEntity entity) throws Exception {
                        //isSuccesse
                        KLog.i("onSuccesse");
                        mView.closeProgressDialog();
                        if (entity.status != mActivity.STATUS_CODE_OK) {
                            mActivity.onError(entity.MSG);
                            return;
                        }
                        Toast.makeText(mActivity, "设备添加成功", Toast.LENGTH_SHORT).show();
                        mActivity.finish();
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