package com.yxld.yxchuangxin.ui.activity.ywh.presenter;
import android.support.annotation.NonNull;

import com.socks.library.KLog;
import com.yxld.yxchuangxin.base.BaseEntity;
import com.yxld.yxchuangxin.contain.Contains;
import com.yxld.yxchuangxin.data.api.HttpAPIWrapper;
import com.yxld.yxchuangxin.entity.DoorInfo;
import com.yxld.yxchuangxin.ui.activity.ywh.contract.FourthContract;
import com.yxld.yxchuangxin.ui.activity.ywh.FourthFragment;

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
 * @Description: presenter of FourthFragment
 * @date 2018/11/08 11:20:19
 */
public class FourthPresenter implements FourthContract.FourthContractPresenter{

    HttpAPIWrapper httpAPIWrapper;
    private final FourthContract.View mView;
    private CompositeDisposable mCompositeDisposable;
    private FourthFragment mFragment;

    @Inject
    public FourthPresenter(@NonNull HttpAPIWrapper httpAPIWrapper, @NonNull FourthContract.View view, FourthFragment fragment) {
        mView = view;
        this.httpAPIWrapper = httpAPIWrapper;
        mCompositeDisposable = new CompositeDisposable();
        this.mFragment = fragment;
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
    public void getFourthData() {
        Map<String, String> map = new HashMap<>();
        map.put("uuid", Contains.uuid);
        httpAPIWrapper.getDoorList(map).subscribe(new Consumer<BaseEntity>() {
            @Override
            public void accept(BaseEntity baseEntity) throws Exception {
                mView.setFourthData(baseEntity);
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