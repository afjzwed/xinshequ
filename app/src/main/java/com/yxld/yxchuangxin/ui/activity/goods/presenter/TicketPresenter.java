package com.yxld.yxchuangxin.ui.activity.goods.presenter;

import android.support.annotation.NonNull;

import com.socks.library.KLog;
import com.yxld.yxchuangxin.contain.Contains;
import com.yxld.yxchuangxin.data.api.HttpAPIWrapper;
import com.yxld.yxchuangxin.entity.CxwyDianziquan;
import com.yxld.yxchuangxin.ui.activity.goods.contract.TicketContract;
import com.yxld.yxchuangxin.ui.activity.goods.TicketActivity;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;

/**
 * @author hu
 * @Package com.yxld.yxchuangxin.ui.activity.goods
 * @Description: presenter of TicketActivity
 * @date 2017/06/22 10:47:40
 */
public class TicketPresenter implements TicketContract.TicketContractPresenter {

    HttpAPIWrapper httpAPIWrapper;
    private TicketContract.View mView;
    private CompositeDisposable mCompositeDisposable;
    private TicketActivity mActivity;

    @Inject
    public TicketPresenter(@NonNull HttpAPIWrapper httpAPIWrapper, @NonNull TicketContract.View view, TicketActivity activity) {
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
        mActivity = null;
    }


    @Override
    public void loadDianZiQuanListFromServer(int page, int rows) {
        mView.showProgressDialog();
        Map<String, String> params = new HashMap<>();
        params.put("uuid", Contains.uuid);
        params.put("rows", rows + "");
        params.put("page", page + "");
        Disposable disposable = httpAPIWrapper.getDianzijuanList(params)
                .subscribe(new Consumer<CxwyDianziquan>() {
                    @Override
                    public void accept(@io.reactivex.annotations.NonNull CxwyDianziquan dianziquan) throws Exception {
                        KLog.i("电子券返回......");
                        mView.closeProgressDialog();
                        mView.onLoadDianZiQuanListSucceed(dianziquan);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(@io.reactivex.annotations.NonNull Throwable throwable) throws Exception {
                        mView.closeProgressDialog();
                        mView.onLoadDianZiQuanListFailed();
                    }
                }, new Action() {
                    @Override
                    public void run() throws Exception {

                    }
                });
        mCompositeDisposable.add(disposable);
    }
}