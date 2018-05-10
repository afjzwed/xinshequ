package com.yxld.yxchuangxin.ui.activity.wuye.presenter;
import android.support.annotation.NonNull;

import com.yxld.yxchuangxin.data.api.HttpAPIWrapper;
import com.yxld.yxchuangxin.ui.activity.wuye.contract.PhoneOpenDoorContract;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;

/**
 * @author hu
 * @Package com.yxld.yxchuangxin.ui.activity.wuye
 * @Description: presenter of PhoneOpenDoorActivity
 * @date 2017/06/06
 */
public class PhoneOpenDoorPresenter implements PhoneOpenDoorContract.PhoneOpenDoorContractPresenter{

    HttpAPIWrapper httpAPIWrapper;
    private PhoneOpenDoorContract.View mView;
    private CompositeDisposable mCompositeDisposable;

    @Inject
    public PhoneOpenDoorPresenter(@NonNull HttpAPIWrapper httpAPIWrapper, @NonNull PhoneOpenDoorContract.View view) {
        mView = view;
        this.httpAPIWrapper = httpAPIWrapper;
        mCompositeDisposable = new CompositeDisposable();
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

//    @Override
//    public void getUser(LinkedHashMap data) {
//        Disposable disposable = httpAPIWrapper.getUser(data)
//                .subscribe(new Consumer<User>() {
//                    @Override
//                    public void accept(User user) throws Exception {
//                        //isSuccesse
//                        KLog.i("onSuccesse");
//                        mView.setText(user);
//                    }
//                }, new Consumer<Throwable>() {
//                    @Override
//                    public void accept(Throwable throwable) throws Exception {
//                        //onError
//                        KLog.i("onError");
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