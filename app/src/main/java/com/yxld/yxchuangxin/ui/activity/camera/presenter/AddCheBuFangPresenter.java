package com.yxld.yxchuangxin.ui.activity.camera.presenter;

import android.support.annotation.NonNull;

import com.socks.library.KLog;
import com.yxld.yxchuangxin.Utils.ToastUtil;
import com.yxld.yxchuangxin.base.BaseEntity;
import com.yxld.yxchuangxin.data.api.HttpAPIWrapper;
import com.yxld.yxchuangxin.ui.activity.camera.AddCheBuFangActivity;
import com.yxld.yxchuangxin.ui.activity.camera.contract.AddCheBuFangContract;

import java.util.Map;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * @author hzp
 * @Package com.yxld.yxchuangxin.ui.activity.camera
 * @Description: presenter of AddCheBuFangActivity
 * @date 2017/09/05 18:10:45
 */
public class AddCheBuFangPresenter implements AddCheBuFangContract.AddCheBuFangContractPresenter{

    HttpAPIWrapper httpAPIWrapper;
    private final AddCheBuFangContract.View mView;
    private CompositeDisposable mCompositeDisposable;
    private AddCheBuFangActivity mActivity;

    @Inject
    public AddCheBuFangPresenter(@NonNull HttpAPIWrapper httpAPIWrapper, @NonNull AddCheBuFangContract.View view, AddCheBuFangActivity activity) {
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
    public void saveBufang(Map map) {

        Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(@io.reactivex.annotations.NonNull ObservableEmitter<String> e) throws Exception {

            }
        }).subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(@io.reactivex.annotations.NonNull String s) throws Exception {

                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(@io.reactivex.annotations.NonNull Throwable throwable) throws Exception {

                    }
                });

        mView.showProgressDialog();
        Disposable disposable = httpAPIWrapper.saveBufang(map)
                .subscribe(new Consumer<BaseEntity>() {
                    @Override
                    public void accept(BaseEntity entity) throws Exception {
                        //isSuccesse
                        KLog.i("onSuccesse");
                        KLog.i(entity.getMSG());
                        mView.closeProgressDialog();
                        mView.addSuccesse(entity.getMSG());
                        if (entity.getStatus() == 0) {
                            ToastUtil.displayShortToast("设置成功");
                        } else {
                            ToastUtil.displayShortToast("设置失败");
                        }
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