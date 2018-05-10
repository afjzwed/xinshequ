package com.yxld.yxchuangxin.ui.activity.wuye.presenter;

import android.support.annotation.NonNull;

import com.socks.library.KLog;
import com.yxld.yxchuangxin.Utils.ToastUtil;
import com.yxld.yxchuangxin.data.api.HttpAPIWrapper;
import com.yxld.yxchuangxin.entity.CarList;
import com.yxld.yxchuangxin.entity.LockCar;
import com.yxld.yxchuangxin.ui.activity.wuye.CarManageActivity;
import com.yxld.yxchuangxin.ui.activity.wuye.contract.CarManageContract;

import java.util.Map;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;

/**
 * @author hu
 * @Package com.yxld.yxchuangxin.ui.activity.wuye
 * @Description: presenter of CarManageActivity
 * @date 2017/06/08
 */
public class CarManagePresenter implements CarManageContract.CarManageContractPresenter{

    HttpAPIWrapper httpAPIWrapper;
    private CarManageContract.View mView;
    private CompositeDisposable mCompositeDisposable;
    private CarManageActivity mActivity;

    @Inject
    public CarManagePresenter(@NonNull HttpAPIWrapper httpAPIWrapper, @NonNull CarManageContract.View view, CarManageActivity activity) {
        mView = view;
        this.httpAPIWrapper = httpAPIWrapper;
        mCompositeDisposable = new CompositeDisposable();
        mActivity = activity;
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
    public void getCarList(Map map) {
        mView.showProgressDialog();
        Disposable disposable = httpAPIWrapper.getCarList(map)
                .subscribe(new Consumer<CarList>() {
                    @Override
                    public void accept(CarList carList) throws Exception {
                        //isSuccesse
                        KLog.i("onSuccesse");
                        mView.closeProgressDialog();
                        mView.setCarList(carList);
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
    public void lockControl(Map map) {
        mView.showProgressDialog();
        Disposable disposable = httpAPIWrapper.carLockControl(map)
                .subscribe(new Consumer<LockCar>() {
                    @Override
                    public void accept(LockCar lockCar) throws Exception {
                        //isSuccesse
                        KLog.i("onSuccesse");
                        mView.closeProgressDialog();
                        mView.onLocakBack();
                        ToastUtil.show(mActivity, lockCar.getMsg());
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