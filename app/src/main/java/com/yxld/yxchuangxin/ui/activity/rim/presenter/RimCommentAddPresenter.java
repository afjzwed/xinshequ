package com.yxld.yxchuangxin.ui.activity.rim.presenter;

import android.support.annotation.NonNull;

import com.socks.library.KLog;
import com.yxld.yxchuangxin.base.BaseEntityService;
import com.yxld.yxchuangxin.data.api.HttpAPIWrapper;
import com.yxld.yxchuangxin.ui.activity.rim.contract.RimCommentAddContract;

import java.util.Map;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;

/**
 * @author wwx
 * @Package com.yxld.yxchuangxin.ui.activity.rim
 * @Description: presenter of RimCommentAddActivity
 * @date 2017/06/17
 */
public class RimCommentAddPresenter implements RimCommentAddContract.RimCommentAddContractPresenter{

    HttpAPIWrapper httpAPIWrapper;
    private final RimCommentAddContract.View mView;
    private CompositeDisposable mCompositeDisposable;

    @Inject
    public RimCommentAddPresenter(@NonNull HttpAPIWrapper httpAPIWrapper, @NonNull RimCommentAddContract.View view) {
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
    }

    @Override
    public void addCommentData(Map map) {
        Disposable disposable = httpAPIWrapper.addRimComment(map)
                .subscribe(new Consumer<BaseEntityService>() {
                    @Override
                    public void accept(BaseEntityService baseEntityService) throws Exception {
                        //这里接收数据项
                        KLog.i("成功的回调");
                        if (baseEntityService.getSuccess() == 1) {
                            mView.requestSuccess();
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        //这里接收onError
                        KLog.i("onError的回调");
                        mView.requestFail("添加失败");
                    }
                }, new Action() {
                    @Override
                    public void run() throws Exception {
                        //这里接收onComplete。
                        KLog.i("run的回调");
                    }
                });
        mCompositeDisposable.add(disposable);
    }
}