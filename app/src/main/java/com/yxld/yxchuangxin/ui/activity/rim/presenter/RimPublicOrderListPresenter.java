package com.yxld.yxchuangxin.ui.activity.rim.presenter;

import android.os.Bundle;
import android.support.annotation.NonNull;

import com.socks.library.KLog;
import com.yxld.yxchuangxin.Utils.ToastUtil;
import com.yxld.yxchuangxin.base.BaseEntityService;
import com.yxld.yxchuangxin.data.api.HttpAPIWrapper;
import com.yxld.yxchuangxin.entity.RimDeleteOrderBean;
import com.yxld.yxchuangxin.entity.SJComplain;
import com.yxld.yxchuangxin.entity.SJOrder;
import com.yxld.yxchuangxin.ui.activity.rim.contract.RimPublicOrderListContract;

import java.util.LinkedHashMap;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;

/**
 * @author wwx
 * @Package com.yxld.yxchuangxin.ui.activity.rim
 * @Description: presenter of RimPublicOrderListFragment
 * @date 2017/06/17
 */
public class RimPublicOrderListPresenter implements RimPublicOrderListContract
        .RimPublicOrderListContractPresenter {

    HttpAPIWrapper httpAPIWrapper;
    private final RimPublicOrderListContract.View mView;
    private CompositeDisposable mCompositeDisposable;

    @Inject
    public RimPublicOrderListPresenter(@NonNull HttpAPIWrapper httpAPIWrapper, @NonNull
            RimPublicOrderListContract.View view) {
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
    public void getRimOrderList(LinkedHashMap<String, String> data, boolean isRefresh) {
        Disposable disposable = httpAPIWrapper.getRimOrderList(data)
                .subscribe(new Consumer<SJOrder>() {
                    @Override
                    public void accept(SJOrder order) throws Exception {
                        //这里接收数据项
                        KLog.i("成功的回调" + order.toString());
//                        if (order == null || order.getTotal() == 0) {
//                            mView.setError();
//                        }
//                        mView.setData(order);
                        if (order.getSuccess().equals("1")) {
                            if (isRefresh) {
                                mView.setData(true, order);
                            } else {
                                mView.setData(false, order);
                            }
                        } else {
                            mView.setError();
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        //这里接收onError
                        KLog.i("onError的回调");
                        mView.setError();
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

    @Override
    public void requestConfirOrder(LinkedHashMap<String, String> data) {
        Disposable disposable = httpAPIWrapper.requestConfirOrder(data)
                .subscribe(new Consumer<BaseEntityService>() {
                    @Override
                    public void accept(BaseEntityService order) throws Exception {
                        //这里接收数据项
                        KLog.i("成功的回调" + order.toString());
                        if (order.getStatus() == 1) {
                            mView.requestFinish("确认订单成功", true);
                            mView.jump(1);
                        } else {
                            mView.requestFinish(order.getMsg(), false);
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        //这里接收onError
                        KLog.i("onError的回调");
                        mView.requestFinish("确认订单失败", false);
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

    @Override
    public void requestCancelOrder(LinkedHashMap<String, String> data) {
        mView.showProgressDialog();
        Disposable disposable = httpAPIWrapper.rimCancelOrder(data)
                .subscribe(new Consumer<BaseEntityService>() {
                    @Override
                    public void accept(BaseEntityService data) throws Exception {
                        mView.closeProgressDialog();
                        //这里接收数据项
                        KLog.i("成功的回调" + data.toString());
                        if (data.getStatus() == 1) {
                            mView.statusChange();
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        mView.closeProgressDialog();
                        //这里接收onError
                        KLog.i("onError的回调");
//                        mView.getRimComplainDetailFinish("确认订单失败",false);
                    }
                }, new Action() {
                    @Override
                    public void run() throws Exception {
                        mView.closeProgressDialog();
                        //这里接收onComplete。
                        KLog.i("run的回调");
                    }
                });
        mCompositeDisposable.add(disposable);
    }

    @Override
    public void getRimComplainDetail(LinkedHashMap<String, String> data, Bundle bundle) {
        mView.showProgressDialog();
        Disposable disposable = httpAPIWrapper.getRimComplainDetail(data)
                .subscribe(new Consumer<SJComplain>() {
                    @Override
                    public void accept(SJComplain data) throws Exception {
                        mView.closeProgressDialog();
                        if (data.getStatus() == 1) {
                            //这里接收数据项
                            if (null == data.getData() || data.getData().size() == 0) {//未投诉
                                mView.getRimComplainDetailFinish(false, bundle, null);
                            } else {//已投诉
                                mView.getRimComplainDetailFinish(true, bundle, data);
                            }
                            KLog.i("成功的回调" + data.toString());
                        }

                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        mView.closeProgressDialog();
                        //这里接收onError
                        KLog.i("onError的回调");
//                        mView.getRimComplainDetailFinish("确认订单失败",false);
                    }
                }, new Action() {
                    @Override
                    public void run() throws Exception {
                        mView.closeProgressDialog();
                        //这里接收onComplete。
                        KLog.i("run的回调");
                    }
                });
        mCompositeDisposable.add(disposable);
    }

    @Override
    public void deleteRimOrder(LinkedHashMap<String, String> data) {
        mView.showProgressDialog();
        Disposable disposable = httpAPIWrapper.deleteRimOrder(data)
                .subscribe(new Consumer<RimDeleteOrderBean>() {
                    @Override
                    public void accept(RimDeleteOrderBean data) throws Exception {
                        mView.closeProgressDialog();
                        //这里接收数据项
                        KLog.i("成功的回调" + data.toString());
                        if (data.getStatus() == 1) {
                            mView.requestFinish("删除订单成功", true);
                        } else {
                            mView.requestFinish("删除订单失败，请稍后重试", false);
                        }

                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        mView.closeProgressDialog();
                        //这里接收onError
                        KLog.i("onError的回调");
//                        mView.getRimComplainDetailFinish("确认订单失败",false);
                    }
                }, new Action() {
                    @Override
                    public void run() throws Exception {
                        mView.closeProgressDialog();
                        //这里接收onComplete。
                        KLog.i("run的回调");
                    }
                });
        mCompositeDisposable.add(disposable);
    }

    @Override
    public void getOrderBuyCheck(LinkedHashMap<String, String> data) {
        mView.showProgressDialog();
        Disposable disposable = httpAPIWrapper.getOrderBayCheck(data)
                .subscribe(new Consumer<BaseEntityService>() {
                    @Override
                    public void accept(BaseEntityService data) throws Exception {
                        mView.closeProgressDialog();
                        //这里接收数据项
                        KLog.i("成功的回调" + data.toString());
                        if (data.getStatus() == 1) {
                            mView.selectPaymentMethod();
                        } else {
                            ToastUtil.showShort(data.getMsg());
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        mView.closeProgressDialog();
                        //这里接收onError
                        KLog.i("onError的回调");
//                        mView.getRimComplainDetailFinish("确认订单失败",false);
                    }
                }, new Action() {
                    @Override
                    public void run() throws Exception {
                        mView.closeProgressDialog();
                        //这里接收onComplete。
                        KLog.i("run的回调");
                    }
                });
        mCompositeDisposable.add(disposable);
    }
}