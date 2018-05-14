package com.yxld.yxchuangxin.ui.activity.wuye.presenter;
import android.support.annotation.NonNull;

import com.socks.library.KLog;
import com.yxld.yxchuangxin.contain.Contains;
import com.yxld.yxchuangxin.data.api.HttpAPIWrapper;
import com.yxld.yxchuangxin.entity.LocalAd;
import com.yxld.yxchuangxin.ui.activity.wuye.AboutOurActivity;
import com.yxld.yxchuangxin.ui.activity.wuye.contract.AboutOurContract;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;

/**
 * @author hu
 * @Package com.yxld.yxchuangxin.ui.activity.wuye
 * @Description: presenter of AboutOurActivity
 * @date 2017/06/23 09:20:36
 */
public class AboutOurPresenter implements AboutOurContract.AboutOurContractPresenter{

    HttpAPIWrapper httpAPIWrapper;
    private AboutOurContract.View mView;
    private CompositeDisposable mCompositeDisposable;
    private AboutOurActivity mActivity;

    @Inject
    public AboutOurPresenter(@NonNull HttpAPIWrapper httpAPIWrapper, @NonNull AboutOurContract.View view, AboutOurActivity activity) {
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
    public void getData() {
        Map<String, String> params = new HashMap<>();
        params.put("uuid", Contains.uuid);
        Disposable disposable = httpAPIWrapper.getLocalAd(params).subscribe(new Consumer<LocalAd>() {
            @Override
            public void accept(@io.reactivex.annotations.NonNull LocalAd baseEntity) throws Exception {
                KLog.i(baseEntity.toString());
                mView.setData(baseEntity);
            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(@io.reactivex.annotations.NonNull Throwable throwable) throws Exception {
            }
        }, new Action() {
            @Override
            public void run() throws Exception {

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