package com.yxld.yxchuangxin.ui.activity.wuye.presenter;

import android.support.annotation.NonNull;

import com.socks.library.KLog;
import com.yxld.yxchuangxin.data.api.HttpAPIWrapper;
import com.yxld.yxchuangxin.entity.CxwyBaoxiu;
import com.yxld.yxchuangxin.ui.activity.wuye.FixListActivity;
import com.yxld.yxchuangxin.ui.activity.wuye.contract.FixListContract;

import java.util.Map;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;

/**
 * @author hu
 * @Package com.yxld.yxchuangxin.ui.activity.wuye
 * @Description: presenter of FixListActivity
 * @date 2017/06/15
 */
public class FixListPresenter implements FixListContract.FixListContractPresenter{

    HttpAPIWrapper httpAPIWrapper;
    private FixListContract.View mView;
    private CompositeDisposable mCompositeDisposable;
    private FixListActivity mActivity;

    @Inject
    public FixListPresenter(@NonNull HttpAPIWrapper httpAPIWrapper, @NonNull FixListContract.View view, FixListActivity activity) {
        mView = view;
        this.httpAPIWrapper = httpAPIWrapper;
        mCompositeDisposable = new CompositeDisposable();
        mActivity = activity;
    }
    @Override
    public void subscribe() {

    }
//    urlkey=upload/baoxiu/img/2017-12/android_1513050095021,uploadImgUrl=upload/baoxiu/img/2017-12/android_1513050095021;
//    curUploadImgIndex=1,,size=3
//    urlkey=upload/tousujianyi/img/2017-12/android_1513050162470,uploadImgUrl=upload/tousujianyi/img/2017-12/android_1513050162470;
//    curUploadImgIndex=1,,size=3
    @Override
    public void unsubscribe() {
        if (!mCompositeDisposable.isDisposed()) {
             mCompositeDisposable.dispose();
        }
        mView = null;
    }

    @Override
    public void getRepairAllList(Map map) {
        mView.showProgressDialog();
        Disposable disposable = httpAPIWrapper.getRepairAllList(map)
                .subscribe(new Consumer<CxwyBaoxiu>() {
                    @Override
                    public void accept(CxwyBaoxiu baoxiu) throws Exception {
                        //isSuccesse
                        KLog.i("onSuccesse");
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
                        //onComplete
                        KLog.i("onComplete");
                    }
                });
        mCompositeDisposable.add(disposable);
    }
}