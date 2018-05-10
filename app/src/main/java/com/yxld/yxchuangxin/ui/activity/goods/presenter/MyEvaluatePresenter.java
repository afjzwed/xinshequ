package com.yxld.yxchuangxin.ui.activity.goods.presenter;

import android.support.annotation.NonNull;

import com.yxld.yxchuangxin.contain.Contains;
import com.yxld.yxchuangxin.data.api.HttpAPIWrapper;
import com.yxld.yxchuangxin.entity.MyAllComment;
import com.yxld.yxchuangxin.ui.activity.goods.MyEvaluateActivity;
import com.yxld.yxchuangxin.ui.activity.goods.contract.MyEvaluateContract;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;

/**
 * @author hzp
 * @Package com.yxld.yxchuangxin.ui.activity.goods
 * @Description: presenter of MyEvaluateActivity
 * @date 2017/10/23 13:55:27
 */
public class MyEvaluatePresenter implements MyEvaluateContract.MyEvaluateContractPresenter{

    HttpAPIWrapper httpAPIWrapper;
    private final MyEvaluateContract.View mView;
    private CompositeDisposable mCompositeDisposable;
    private MyEvaluateActivity mActivity;

    @Inject
    public MyEvaluatePresenter(@NonNull HttpAPIWrapper httpAPIWrapper, @NonNull MyEvaluateContract.View view, MyEvaluateActivity activity) {
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
    public void getMyEvaluate() {
        Map<String, String> params = new HashMap<>();
        params.put("uuid", Contains.uuid);
        Disposable disposable = httpAPIWrapper.getMyEvaluate(params)
                .subscribe(new Consumer<MyAllComment>() {
                    @Override
                    public void accept(@io.reactivex.annotations.NonNull MyAllComment myAllComment) throws Exception {
                        mView.onGetEvaluateSuccess(myAllComment);
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
}