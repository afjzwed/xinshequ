package com.yxld.yxchuangxin.ui.activity.goods.presenter;

import android.support.annotation.NonNull;

import com.socks.library.KLog;
import com.yxld.yxchuangxin.base.BaseEntity;
import com.yxld.yxchuangxin.data.api.HttpAPIWrapper;
import com.yxld.yxchuangxin.ui.activity.goods.PayWaySelectActivity;
import com.yxld.yxchuangxin.ui.activity.goods.contract.PayWaySelectContract;

import java.util.Map;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;

import static com.yxld.yxchuangxin.base.BaseActivity.STATUS_CODE_OK;

/**
 * @author wwx
 * @Package com.yxld.yxchuangxin.ui.activity.goods
 * @Description: presenter of PayWaySelectActivity
 * @date 2017/06/27 16:12:50
 */
public class PayWaySelectPresenter implements PayWaySelectContract.PayWaySelectContractPresenter{

    HttpAPIWrapper httpAPIWrapper;
    private final PayWaySelectContract.View mView;
    private CompositeDisposable mCompositeDisposable;
    private PayWaySelectActivity mActivity;

    @Inject
    public PayWaySelectPresenter(@NonNull HttpAPIWrapper httpAPIWrapper, @NonNull PayWaySelectContract.View view, PayWaySelectActivity activity) {
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
    public void alipyPaySuccess(Map map) {
        mView.showProgressDialog();
        Disposable disposable = httpAPIWrapper.updateOrderByAlipySuccess(map)
                .subscribe(new Consumer<BaseEntity>() {
                    @Override
                    public void accept(BaseEntity info) throws Exception {
                        //这里接收数据项
                        KLog.i("成功的回调"+info.toString());
                        // 获取请求码
                        mView.closeProgressDialog();
                        if (info.status != STATUS_CODE_OK) {
                            mView.requestAlipyError(info.MSG);
                            return;
                        }
                        mView.onAlipaySucceed();
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        //这里接收onError
                        KLog.i("onError的回调");
                        mView.closeProgressDialog();
                        mView.requestAlipyError("更新订单信息失败请联系管理员");
                    }
                }, new Action() {
                    @Override
                    public void run() throws Exception {
                        //这里接收onComplete。
                        KLog.i("run的回调");
                    }
                });
        mCompositeDisposable.add(disposable);
    }
}