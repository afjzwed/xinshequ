package com.yxld.yxchuangxin.ui.activity.wuye.presenter;

import android.support.annotation.NonNull;

import com.socks.library.KLog;
import com.yxld.yxchuangxin.data.api.HttpAPIWrapper;
import com.yxld.yxchuangxin.entity.JiaofeiMingxi;
import com.yxld.yxchuangxin.ui.activity.wuye.JiaofeiListActivity;
import com.yxld.yxchuangxin.ui.activity.wuye.contract.JiaofeiListContract;

import java.util.Map;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;

/**
 * @author hu
 * @Package com.yxld.yxchuangxin.ui.activity.wuye
 * @Description: presenter of JiaofeiListActivity
 * @date 2017/07/01 13:34:37
 */
public class JiaofeiListPresenter implements JiaofeiListContract.JiaofeiListContractPresenter{

    HttpAPIWrapper httpAPIWrapper;
    private JiaofeiListContract.View mView;
    private CompositeDisposable mCompositeDisposable;
    private JiaofeiListActivity mActivity;

    @Inject
    public JiaofeiListPresenter(@NonNull HttpAPIWrapper httpAPIWrapper, @NonNull JiaofeiListContract.View view, JiaofeiListActivity activity) {
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
    public void getList(Map map) {
        mView.showProgressDialog();
        Disposable disposable = httpAPIWrapper.getJiaofei(map)
                .subscribe(new Consumer<JiaofeiMingxi>() {
                    @Override
                    public void accept(JiaofeiMingxi mingxi) throws Exception {
                        //isSuccesse
                        KLog.i("onSuccesse");
                        mView.closeProgressDialog();
                        mView.setList(mingxi);
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