package com.yxld.yxchuangxin.ui.activity.rim.presenter;

import android.support.annotation.NonNull;

import com.socks.library.KLog;
import com.yxld.yxchuangxin.data.api.HttpAPIWrapper;
import com.yxld.yxchuangxin.entity.RimOrderCommentBean;
import com.yxld.yxchuangxin.ui.activity.rim.contract.RimCommentListContract;

import java.util.Map;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;

/**
 * @author wwx
 * @Package com.yxld.yxchuangxin.ui.activity.rim
 * @Description: presenter of RimCommentListActivity
 * @date 2017/06/17
 */
public class RimCommentListPresenter implements RimCommentListContract.RimCommentListContractPresenter{

    HttpAPIWrapper httpAPIWrapper;
    private final RimCommentListContract.View mView;
    private CompositeDisposable mCompositeDisposable;

    @Inject
    public RimCommentListPresenter(@NonNull HttpAPIWrapper httpAPIWrapper, @NonNull RimCommentListContract.View view) {
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
    public void getRimCommentList(Map map, boolean isRefresh) {
        mView.showProgressDialog();
        Disposable disposable = httpAPIWrapper.getRimOrderComment(map)
                .subscribe(new Consumer<RimOrderCommentBean>() {
                    @Override
                    public void accept(RimOrderCommentBean order) throws Exception {
                        //这里接收数据项
                        KLog.i("成功的回调"+order.toString());
                        mView.closeProgressDialog();
                        if (order.getSuccess().equals("1")) {
                            mView.setData(isRefresh, order);
                        } else {
                            mView.setEmptyData(order);
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        //这里接收onError
                        KLog.i("onError的回调");
                        mView.closeProgressDialog();
                        mView.setError();
                    }
                }, new Action() {
                    @Override
                    public void run() throws Exception {
                        //这里接收onComplete。
                        KLog.i("run的回调");
                        mView.closeProgressDialog();
                    }
                });
        mCompositeDisposable.add(disposable);
    }
}

