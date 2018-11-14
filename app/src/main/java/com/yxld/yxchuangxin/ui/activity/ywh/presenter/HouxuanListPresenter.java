package com.yxld.yxchuangxin.ui.activity.ywh.presenter;

import android.support.annotation.NonNull;
import android.util.Log;

import com.socks.library.KLog;
import com.yxld.yxchuangxin.base.BaseEntity;
import com.yxld.yxchuangxin.contain.Contains;
import com.yxld.yxchuangxin.data.api.HttpAPIWrapper;
import com.yxld.yxchuangxin.entity.HouxuanRenBean;
import com.yxld.yxchuangxin.ui.activity.ywh.contract.HouxuanListContract;
import com.yxld.yxchuangxin.ui.activity.ywh.HouxuanListActivity;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;

/**
 * @author William
 * @Package com.yxld.yxchuangxin.ui.activity.ywh
 * @Description: presenter of HouxuanListActivity
 * @date 2018/11/14 09:53:34
 */
public class HouxuanListPresenter implements HouxuanListContract.HouxuanListContractPresenter {

    HttpAPIWrapper httpAPIWrapper;
    private final HouxuanListContract.View mView;
    private CompositeDisposable mCompositeDisposable;
    private HouxuanListActivity mActivity;

    @Inject
    public HouxuanListPresenter(@NonNull HttpAPIWrapper httpAPIWrapper, @NonNull HouxuanListContract.View view,
                                HouxuanListActivity activity) {
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
        Log.e("wh", "Contains.uuid " + Contains.uuid);
        Disposable subscribe = httpAPIWrapper.getTjcbzList1(map).subscribe(new Consumer<HouxuanRenBean>() {
            @Override
            public void accept(HouxuanRenBean baseEntity) throws Exception {
                Log.e("wh", "baseEntity" + baseEntity);
                mView.getTjcbzSuccess(baseEntity);
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

    @Override
    public void comitLy(Map map) {
        Disposable subscribe = httpAPIWrapper.getTjcb1(map).subscribe(new Consumer<BaseEntity>() {
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