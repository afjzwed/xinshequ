package com.yxld.yxchuangxin.ui.activity.camera.presenter;

import android.support.annotation.NonNull;

import com.socks.library.KLog;
import com.yxld.yxchuangxin.data.api.HttpAPIWrapper;
import com.yxld.yxchuangxin.entity.BaoJingEntity;
import com.yxld.yxchuangxin.ui.activity.camera.AllBaoJingActivity;
import com.yxld.yxchuangxin.ui.activity.camera.contract.AllBaoJingContract;

import java.util.Map;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;

/**
 * @author hzp
 * @Package com.yxld.yxchuangxin.ui.activity.camera
 * @Description: presenter of AllBaoJingActivity
 * @date 2017/09/07 01:26:53
 */
public class AllBaoJingPresenter implements AllBaoJingContract.AllBaoJingContractPresenter{

    HttpAPIWrapper httpAPIWrapper;
    private final AllBaoJingContract.View mView;
    private CompositeDisposable mCompositeDisposable;
    private AllBaoJingActivity mActivity;

    @Inject
    public AllBaoJingPresenter(@NonNull HttpAPIWrapper httpAPIWrapper, @NonNull AllBaoJingContract.View view, AllBaoJingActivity activity) {
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
    public void getBaoJingList(Map map) {
//        mView.showProgressDialog();
        Disposable disposable = httpAPIWrapper.getBaoJingList(map)
                .subscribe(new Consumer<BaoJingEntity>() {
                    @Override
                    public void accept(BaoJingEntity entity) throws Exception {
                        //isSuccesse
                        mView.closeProgressDialog();
                        mView.setAdapter(entity);
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