package com.yxld.yxchuangxin.ui.activity.goods.presenter;

import android.support.annotation.NonNull;

import com.socks.library.KLog;
import com.yxld.yxchuangxin.Utils.ToastUtil;
import com.yxld.yxchuangxin.base.BaseEntity;
import com.yxld.yxchuangxin.data.api.HttpAPIWrapper;
import com.yxld.yxchuangxin.entity.Onlymsg;
import com.yxld.yxchuangxin.ui.activity.goods.MarketJainyiActivity;
import com.yxld.yxchuangxin.ui.activity.goods.contract.MarketJainyiContract;

import java.util.Map;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;

/**
 * @author hu
 * @Package com.yxld.yxchuangxin.ui.activity.goods
 * @Description: presenter of MarketJainyiActivity
 * @date 2017/06/22 16:19:40
 */
public class MarketJainyiPresenter implements MarketJainyiContract.MarketJainyiContractPresenter {

    HttpAPIWrapper httpAPIWrapper;
    private final MarketJainyiContract.View mView;
    private CompositeDisposable mCompositeDisposable;
    private MarketJainyiActivity mActivity;

    @Inject
    public MarketJainyiPresenter(@NonNull HttpAPIWrapper httpAPIWrapper, @NonNull MarketJainyiContract.View view, MarketJainyiActivity activity) {
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
    public void saveJianYi(Map map) {
        mView.showProgressDialog();
        Disposable disposable = httpAPIWrapper.commitMallSuggest(map)
                .subscribe(new Consumer<BaseEntity>() {
                    @Override
                    public void accept(BaseEntity onlymsg) throws Exception {
                        //isSuccesse
                        KLog.i("onSuccesse");
                        mView.closeProgressDialog();
                        mView.onJianYiBack(onlymsg);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        //onError
                        KLog.i("onError");
                        throwable.printStackTrace();
                        mView.closeProgressDialog();
//                        ToastUtil.show(mActivity, mActivity.getString(R.string.loading_failed_1));
                    }
                }, new Action() {
                    @Override
                    public void run() throws Exception {
                        //onComplete
                        KLog.i("onComplete");
                    }
                });
        mCompositeDisposable.add(disposable);
    }

}