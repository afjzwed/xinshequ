package com.yxld.yxchuangxin.ui.activity.camera.presenter;

import android.support.annotation.NonNull;
import android.util.Log;

import com.socks.library.KLog;
import com.yxld.yxchuangxin.Utils.ToastUtil;
import com.yxld.yxchuangxin.data.api.HttpAPIWrapper;
import com.yxld.yxchuangxin.data.api.support.ErrorConsumer;
import com.yxld.yxchuangxin.entity.XuFeiBean;
import com.yxld.yxchuangxin.entity.XuFeiOrder;
import com.yxld.yxchuangxin.ui.activity.camera.contract.AlarmPayContract;
import com.yxld.yxchuangxin.ui.activity.camera.AlarmPayActivity;

import java.util.Map;
import java.util.logging.Logger;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;

/**
 * @author xlei
 * @Package com.yxld.yxchuangxin.ui.activity.camera
 * @Description: presenter of AlarmPayActivity
 * @date 2017/09/28 09:59:31
 */
public class AlarmPayPresenter implements AlarmPayContract.CameraPayContractPresenter {

    HttpAPIWrapper httpAPIWrapper;
    private final AlarmPayContract.View mView;
    private CompositeDisposable mCompositeDisposable;
    private AlarmPayActivity mActivity;

    @Inject
    public AlarmPayPresenter(@NonNull HttpAPIWrapper httpAPIWrapper, @NonNull AlarmPayContract.View view, AlarmPayActivity activity) {
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
    public void getData(Map<String, String> map) {
        mView.showProgressDialog();
        Disposable disposable = httpAPIWrapper.getMoney(map).subscribe(new Consumer<XuFeiBean>() {
            @Override
            public void accept(@io.reactivex.annotations.NonNull XuFeiBean xuFeiBean) throws Exception {
                mView.closeProgressDialog();
                if (xuFeiBean.getStatus() == 0) {
                    mView.setData(xuFeiBean);
                } else {
                    ToastUtil.show(mActivity, xuFeiBean.getMSG());
                }
            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(@io.reactivex.annotations.NonNull Throwable throwable) throws Exception {
                mView.closeProgressDialog();
                KLog.i("onError");
                throwable.printStackTrace();
            }
        }, new Action() {
            @Override
            public void run() throws Exception {
                KLog.i("onComplete");
            }
        });
        mCompositeDisposable.add(disposable);
    }

    @Override
    public void getOrder(Map<String, String> map) {
        mView.showProgressDialog();
        Disposable disposable = httpAPIWrapper.getJiaoFeiOrder(map).subscribe(new Consumer<XuFeiOrder>() {
            @Override
            public void accept(@io.reactivex.annotations.NonNull XuFeiOrder xuFeiOrder) throws Exception {
                mView.closeProgressDialog();
                if (xuFeiOrder.isSuccess()) {
                    KLog.i("onSuccesse");
                    mView.setOrder(xuFeiOrder.getData());
                } else {
                    ToastUtil.show(mActivity, xuFeiOrder.getMsg());
                }

            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(@io.reactivex.annotations.NonNull Throwable throwable) throws Exception {
                mView.closeProgressDialog();
                throwable.printStackTrace();
                KLog.i("onError");
            }
        }, new Action() {
            @Override
            public void run() throws Exception {
                KLog.i("onComplete");
            }
        });
        mCompositeDisposable.add(disposable);

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
}