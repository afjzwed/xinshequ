package com.yxld.yxchuangxin.ui.activity.ywh.presenter;
import android.support.annotation.NonNull;

import com.socks.library.KLog;
import com.yxld.yxchuangxin.base.BaseEntity;
import com.yxld.yxchuangxin.contain.Contains;
import com.yxld.yxchuangxin.data.api.HttpAPIWrapper;
import com.yxld.yxchuangxin.ui.activity.ywh.contract.CymdContract;
import com.yxld.yxchuangxin.ui.activity.ywh.CymdActivity;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;

/**
 * @author xlei
 * @Package com.yxld.yxchuangxin.ui.activity.ywh
 * @Description: presenter of CymdActivity
 * @date 2018/11/09 09:18:52
 */
public class CymdPresenter implements CymdContract.CymdContractPresenter{

    HttpAPIWrapper httpAPIWrapper;
    private final CymdContract.View mView;
    private CompositeDisposable mCompositeDisposable;
    private CymdActivity mActivity;

    @Inject
    public CymdPresenter(@NonNull HttpAPIWrapper httpAPIWrapper, @NonNull CymdContract.View view, CymdActivity activity) {
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
    public void getList() {
        Map<String, String> map = new HashMap<>();
        map.put("uuid", Contains.uuid);
        Disposable subscribe = httpAPIWrapper.getGsmd(map).subscribe(new Consumer<BaseEntity>() {
            @Override
            public void accept(BaseEntity baseEntity) throws Exception {
                mView.getListSuccess(baseEntity);
            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {
                KLog.i("onError");
            }
        }, new Action() {
            @Override
            public void run() throws Exception {
                KLog.i("onComplete");
            }
        });
        mCompositeDisposable.add(subscribe);
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