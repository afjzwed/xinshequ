package com.yxld.yxchuangxin.ui.activity.camera.presenter;

import android.support.annotation.NonNull;

import com.socks.library.KLog;
import com.yxld.yxchuangxin.base.BaseEntity;
import com.yxld.yxchuangxin.contain.Contains;
import com.yxld.yxchuangxin.data.api.HttpAPIWrapper;
import com.yxld.yxchuangxin.entity.camera.ShareFamily;
import com.yxld.yxchuangxin.entity.camera.Shared;
import com.yxld.yxchuangxin.ui.activity.camera.CameraInformActivity;
import com.yxld.yxchuangxin.ui.activity.camera.contract.CameraInformContract;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;

/**
 * @author hzp
 * @Package com.yxld.yxchuangxin.ui.activity.camera
 * @Description: presenter of CameraInformActivity
 * @date 2017/10/18 09:18:09
 */
public class CameraInformPresenter implements CameraInformContract.CameraInformContractPresenter{

    HttpAPIWrapper httpAPIWrapper;
    private final CameraInformContract.View mView;
    private CompositeDisposable mCompositeDisposable;
    private CameraInformActivity mActivity;
    private ShareFamily mShareFamily;

    @Inject
    public CameraInformPresenter(@NonNull HttpAPIWrapper httpAPIWrapper, @NonNull CameraInformContract.View view, CameraInformActivity activity) {
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
    public void getJiashu(Map map, String deviceId) {
        Disposable disposable = httpAPIWrapper.getCameraShareFamily(map)
                .subscribe(new Consumer<ShareFamily>() {
                    @Override
                    public void accept(ShareFamily list) throws Exception {
                        mShareFamily = list;
                        mView.onAllJiashuBack(list);
                        Map<String, String> map2 = new HashMap<>();
                        map2.put("uuid", Contains.uuid);
                        map2.put("sb_ipc_id", deviceId);
                        getShared(map2);
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

    /**
     * 获取已经添加的家属
     * @param map
     */
    @Override
    public void getShared(Map map) {
        Disposable disposable = httpAPIWrapper.getShared(map)
                .subscribe(new Consumer<Shared>() {
                    @Override
                    public void accept(Shared list) throws Exception {
                        perJiajiaJiashu(list);
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

    /**
     * 处理数据，把所有的可以添加的家属和已经添加的家属作比较
     * id相同的话，就给personList添加一些用户信息用来做显示用
     * @param shared
     */
    private void perJiajiaJiashu(Shared shared) {
        //用户集合
        ArrayList<String> personList = new ArrayList<>();
        //用户id的集合
        ArrayList<String> idList = new ArrayList<>();
        for (int i = 0; i < shared.getData().size(); i++) {
            for (int j = 0; j < mShareFamily.getData().size(); j++) {
                if (mShareFamily.getData().get(j).getYezhuId() == shared.getData().get(i).getFxYezhuId()) {
                    personList.add(mShareFamily.getData().get(j).getYezhuName() + "    " + mShareFamily.getData().get(j).getYezhuShouji());
                    idList.add(mShareFamily.getData().get(j).getYezhuId() + "");
                    break;
                }
            }
        }
        mView.closeProgressDialog();
        mView.setYijiaJiashu(personList, idList);
    }

    @Override
    public void addShare(Map map) {
        mView.showProgressDialog();
        Disposable disposable = httpAPIWrapper.addCameraShare(map)
                .subscribe(new Consumer<BaseEntity>() {
                    @Override
                    public void accept(BaseEntity entity) throws Exception {
                        mView.closeProgressDialog();
                        mView.onAddBack(entity);
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