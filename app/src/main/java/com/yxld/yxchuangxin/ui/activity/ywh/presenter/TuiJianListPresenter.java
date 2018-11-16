package com.yxld.yxchuangxin.ui.activity.ywh.presenter;

import android.support.annotation.NonNull;
import android.util.Log;

import com.socks.library.KLog;
import com.yxld.yxchuangxin.base.BaseEntity;
import com.yxld.yxchuangxin.contain.Contains;
import com.yxld.yxchuangxin.data.api.HttpAPIWrapper;
import com.yxld.yxchuangxin.entity.YwhTj;
import com.yxld.yxchuangxin.ui.activity.ywh.TuiJianListActivity;
import com.yxld.yxchuangxin.ui.activity.ywh.contract.TuiJianListContract;

import java.util.Map;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;

/**
 * @author xlei
 * @Package com.yxld.yxchuangxin.ui.activity.ywh
 * @Description: presenter of TuiJianListActivity
 * @date 2018/11/08 10:54:14
 */
public class TuiJianListPresenter implements TuiJianListContract.TuiJianListContractPresenter{

    HttpAPIWrapper httpAPIWrapper;
    private final TuiJianListContract.View mView;
    private CompositeDisposable mCompositeDisposable;
    private TuiJianListActivity mActivity;

    @Inject
    public TuiJianListPresenter(@NonNull HttpAPIWrapper httpAPIWrapper, @NonNull TuiJianListContract.View view, TuiJianListActivity activity) {
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
    public void getTjcbz(Map map) {
        Disposable subscribe = httpAPIWrapper.getTjcbzList(map).subscribe(new Consumer<YwhTj>() {
            @Override
            public void accept(YwhTj baseEntity) throws Exception {
                mView.getTjcbzSuccess(baseEntity);
            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {
                KLog.i("onError");
                mView.closeProgressDialog();
            }
        }, new Action() {
            @Override
            public void run() throws Exception {
                KLog.i("onComplete");
            }
        });
        mCompositeDisposable.add(subscribe);
    }

    @Override
    public void comitLy(Map map) {
        Disposable subscribe = httpAPIWrapper.getTjcbz(map).subscribe(new Consumer<BaseEntity>() {
            @Override
            public void accept(BaseEntity baseEntity) throws Exception {
                mView.commitLySuccess(baseEntity);
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
}