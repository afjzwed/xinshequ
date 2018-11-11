package com.yxld.yxchuangxin.ui.activity.ywh.presenter;
import android.support.annotation.NonNull;

import com.socks.library.KLog;
import com.yxld.yxchuangxin.base.BaseEntity;
import com.yxld.yxchuangxin.data.api.HttpAPIWrapper;
import com.yxld.yxchuangxin.ui.activity.ywh.contract.CheckNoticeContract;
import com.yxld.yxchuangxin.ui.activity.ywh.CheckNoticeActivity;

import java.util.Map;

import javax.inject.Inject;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;

/**
 * @author William
 * @Package com.yxld.yxchuangxin.ui.activity.ywh
 * @Description: presenter of CheckNoticeActivity
 * @date 2018/11/08 17:11:57
 */
public class CheckNoticePresenter implements CheckNoticeContract.CheckNoticeContractPresenter{

    HttpAPIWrapper httpAPIWrapper;
    private final CheckNoticeContract.View mView;
    private CompositeDisposable mCompositeDisposable;
    private CheckNoticeActivity mActivity;

    @Inject
    public CheckNoticePresenter(@NonNull HttpAPIWrapper httpAPIWrapper, @NonNull CheckNoticeContract.View view, CheckNoticeActivity activity) {
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