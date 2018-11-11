package com.yxld.yxchuangxin.ui.activity.ywh.presenter;
import android.support.annotation.NonNull;

import com.socks.library.KLog;
import com.yxld.yxchuangxin.base.BaseEntity;
import com.yxld.yxchuangxin.contain.Contains;
import com.yxld.yxchuangxin.data.api.HttpAPIWrapper;
import com.yxld.yxchuangxin.ui.activity.ywh.contract.YeWeiHuiContract;
import com.yxld.yxchuangxin.ui.activity.ywh.YeWeiHuiActivity;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;

/**
 * @author William
 * @Package com.yxld.yxchuangxin.ui.activity.wuye
 * @Description: presenter of YeWeiHuiActivity
 * @date 2018/11/07 11:49:57
 */
public class YeWeiHuiPresenter implements YeWeiHuiContract.YeWeiHuiContractPresenter{

    HttpAPIWrapper httpAPIWrapper;
    private final YeWeiHuiContract.View mView;
    private CompositeDisposable mCompositeDisposable;
    private YeWeiHuiActivity mActivity;

    @Inject
    public YeWeiHuiPresenter(@NonNull HttpAPIWrapper httpAPIWrapper, @NonNull YeWeiHuiContract.View view, YeWeiHuiActivity activity) {
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
    public void getData() {
        Map<String, String> map = new HashMap<>();
        map.put("uuid", Contains.uuid);
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