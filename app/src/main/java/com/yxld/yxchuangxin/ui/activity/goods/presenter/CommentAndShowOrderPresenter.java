package com.yxld.yxchuangxin.ui.activity.goods.presenter;
import android.support.annotation.NonNull;

import com.yxld.yxchuangxin.base.BaseEntity;
import com.yxld.yxchuangxin.data.api.HttpAPIWrapper;
import com.yxld.yxchuangxin.ui.activity.goods.contract.CommentAndShowOrderContract;
import com.yxld.yxchuangxin.ui.activity.goods.CommentAndShowOrderActivity;

import java.util.Map;

import javax.inject.Inject;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;

/**
 * @author Yuan.Y.Q
 * @Package com.yxld.yxchuangxin.ui.activity.goods
 * @Description: presenter of CommentAndShowOrderActivity
 * @date 2017/06/28 20:14:36
 */
public class CommentAndShowOrderPresenter implements CommentAndShowOrderContract.CommentAndShowOrderContractPresenter{

    HttpAPIWrapper httpAPIWrapper;
    private final CommentAndShowOrderContract.View mView;
    private CompositeDisposable mCompositeDisposable;
    private CommentAndShowOrderActivity mActivity;

    @Inject
    public CommentAndShowOrderPresenter(@NonNull HttpAPIWrapper httpAPIWrapper, @NonNull CommentAndShowOrderContract.View view, CommentAndShowOrderActivity activity) {
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
    public void addComment(Map<String, String> params) {
        Disposable disposable = httpAPIWrapper.addComment(params)
                .subscribe(new Consumer<BaseEntity>() {
                    @Override
                    public void accept(@io.reactivex.annotations.NonNull BaseEntity entity) throws Exception {
                        mView.onCommentResponse(entity);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(@io.reactivex.annotations.NonNull Throwable throwable) throws Exception {
                        mView.onCommentFailed();
                    }
                }, new Action() {
                    @Override
                    public void run() throws Exception {

                    }
                });
        mCompositeDisposable.add(disposable);
    }
}