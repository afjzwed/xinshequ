package com.yxld.yxchuangxin.ui.activity.ywh.presenter;
import android.support.annotation.NonNull;

import com.socks.library.KLog;
import com.yxld.yxchuangxin.base.BaseEntity;
import com.yxld.yxchuangxin.data.api.HttpAPIWrapper;
import com.yxld.yxchuangxin.ui.activity.ywh.contract.ResultShowContract;
import com.yxld.yxchuangxin.ui.activity.ywh.ResultShowActivity;

import java.util.Map;

import javax.inject.Inject;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;

/**
 * @author William
 * @Package com.yxld.yxchuangxin.ui.activity.wuye
 * @Description: presenter of ResultShowActivity
 * @date 2018/11/07 20:09:47
 */
public class ResultShowPresenter implements ResultShowContract.ResultShowContractPresenter{

    HttpAPIWrapper httpAPIWrapper;
    private final ResultShowContract.View mView;
    private CompositeDisposable mCompositeDisposable;
    private ResultShowActivity mActivity;

    @Inject
    public ResultShowPresenter(@NonNull HttpAPIWrapper httpAPIWrapper, @NonNull ResultShowContract.View view, ResultShowActivity activity) {
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
    public void getData(Map map) {
        httpAPIWrapper.getDoorList(map).subscribe(new Consumer<BaseEntity>() {
            @Override
            public void accept(BaseEntity baseEntity) throws Exception {
                mView.setData(baseEntity);
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
    }
}