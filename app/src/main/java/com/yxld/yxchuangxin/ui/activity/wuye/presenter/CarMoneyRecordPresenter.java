package com.yxld.yxchuangxin.ui.activity.wuye.presenter;

import android.support.annotation.NonNull;

import com.socks.library.KLog;
import com.yxld.yxchuangxin.data.api.HttpAPIWrapper;
import com.yxld.yxchuangxin.entity.CarJiaofeiRecord;
import com.yxld.yxchuangxin.ui.activity.wuye.CarMoneyRecordActivity;
import com.yxld.yxchuangxin.ui.activity.wuye.contract.CarMoneyRecordContract;

import java.util.Map;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;

/**
 * @author hu
 * @Package com.yxld.yxchuangxin.ui.activity.wuye
 * @Description: presenter of CarMoneyRecordActivity
 * @date 2017/07/06 18:00:14
 */
public class CarMoneyRecordPresenter implements CarMoneyRecordContract.CarMoneyRecordContractPresenter{

    HttpAPIWrapper httpAPIWrapper;
    private CarMoneyRecordContract.View mView;
    private CompositeDisposable mCompositeDisposable;
    private CarMoneyRecordActivity mActivity;

    @Inject
    public CarMoneyRecordPresenter(@NonNull HttpAPIWrapper httpAPIWrapper, @NonNull CarMoneyRecordContract.View view, CarMoneyRecordActivity activity) {
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
    public void getCarMoneyRecord(Map map) {
        //mView.showProgressDialog();
        Disposable disposable = httpAPIWrapper.getCarMoneyRecord(map)
                .subscribe(new Consumer<CarJiaofeiRecord>() {
                    @Override
                    public void accept(CarJiaofeiRecord record) throws Exception {
                        //isSuccesse
                        KLog.i("onSuccesse");
                        mView.setList(record);
                        //mView.closeProgressDialog();
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        //onError
                        KLog.i("onError");
                        throwable.printStackTrace();
                        //mView.closeProgressDialog();
                        //ToastUtil.show(mActivity, mActivity.getString(R.string.loading_failed_1));
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