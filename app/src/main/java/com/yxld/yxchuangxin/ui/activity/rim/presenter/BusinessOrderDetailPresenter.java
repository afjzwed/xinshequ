package com.yxld.yxchuangxin.ui.activity.rim.presenter;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;

import com.socks.library.KLog;
import com.yxld.yxchuangxin.base.BaseEntity;
import com.yxld.yxchuangxin.data.api.HttpAPIWrapper;
import com.yxld.yxchuangxin.entity.CxwyOrderInfo;
import com.yxld.yxchuangxin.entity.ShopCarList;
import com.yxld.yxchuangxin.ui.activity.rim.BusinessOrderDetailActivity;
import com.yxld.yxchuangxin.ui.activity.rim.RimCommentAddActivity;
import com.yxld.yxchuangxin.ui.activity.rim.RimComplainAddActivity;
import com.yxld.yxchuangxin.ui.activity.rim.RimOrderDynamicActivity;
import com.yxld.yxchuangxin.ui.activity.rim.contract.BusinessOrderDetailContract;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;

/**
 * @author hu
 * @Package com.yxld.yxchuangxin.ui.activity.rim
 * @Description: presenter of BusinessOrderDetailActivity
 * @date 2017/06/20 14:05:57
 */
public class BusinessOrderDetailPresenter implements BusinessOrderDetailContract.BusinessOrderDetailContractPresenter {

    HttpAPIWrapper httpAPIWrapper;
    private final BusinessOrderDetailContract.View mView;
    private CompositeDisposable mCompositeDisposable;
    private BusinessOrderDetailActivity mActivity;
    private CxwyOrderInfo mInfo;
    private int allSize;
    private List<ShopCarList.ShopCarBean> chooseClassifyList;

    @Inject
    public BusinessOrderDetailPresenter(@NonNull HttpAPIWrapper httpAPIWrapper, @NonNull BusinessOrderDetailContract.View view, BusinessOrderDetailActivity activity) {
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
    public void getBusinessOrderDetail(Map map) {
        mView.showProgressDialog();
        Disposable disposable = httpAPIWrapper.getOrderInfo(map)
                .subscribe(new Consumer<CxwyOrderInfo>() {
                    @Override
                    public void accept(CxwyOrderInfo info) throws Exception {
                        //isSuccesse
                        KLog.i("onSuccesse");
                        mInfo = info;
                        cacultePriceAndCount();
                        mView.setOrderInfo(info, chooseClassifyList);
                        mView.closeProgressDialog();
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

    @Override
    public void opreateOrder(View v) {
        //操作订单
        if (mInfo.getData().getOrder().getOrderStatus() == 2) {
            //取消逻辑
//            showOrOffCancalOrder();
        }
        if (mInfo.getData().getOrder().getOrderStatus() == 1) {
            //支付逻辑
//            showOrOffPayPop();
        }
        if (mInfo.getData().getOrder().getOrderStatus() == 5) {
            //评价逻辑
            Bundle bundle = new Bundle();
            bundle.putString("orderNumber", mInfo.getData().getOrder().getOrderNumber());
            bundle.putString("complainBusinessNumber", mInfo.getData().getOrder().getOrderBusinessNumber());
            Intent intent = new Intent(mActivity, RimCommentAddActivity.class);
            intent.putExtras(bundle);
            mActivity.startActivity(intent);
        }
        if (mInfo.getData().getOrder().getOrderStatus() == 13) {
            //确认送达
//            confirmorder();
        }
    }

    @Override
    public void getOrderDynamic() {
        Bundle bundle = new Bundle();
        bundle.putString("orderNumber", mInfo.getData().getOrder().getOrderNumber());
        bundle.putString("complainBusinessNumber", mInfo.getData().getOrder().getOrderBusinessNumber());
        Intent intent = new Intent(mActivity, RimOrderDynamicActivity.class);
        intent.putExtras(bundle);
        mActivity.startActivity(intent);
    }

    @Override
    public void Complain() {
        Bundle bundle = new Bundle();
        bundle.putString("orderNumber", mInfo.getData().getOrder().getOrderNumber());
        bundle.putString("complainBusinessNumber", mInfo.getData().getOrder().getOrderBusinessNumber());
        Intent intent = new Intent(mActivity, RimComplainAddActivity.class);
        intent.putExtras(bundle);
        mActivity.startActivity(intent);
    }

    @Override
    public void detailToCar(Map map) {
        mView.showProgressDialog();
        Disposable onComplete = httpAPIWrapper.detailsToCar(map).subscribe(new Consumer<BaseEntity>() {
            @Override
            public void accept(@io.reactivex.annotations.NonNull BaseEntity baseEntity) throws Exception {
                mView.closeProgressDialog();
                mView.detailsToCarSuccess(baseEntity);
            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(@io.reactivex.annotations.NonNull Throwable throwable) throws Exception {
                mView.closeProgressDialog();
                KLog.i("onError" + throwable.toString());
            }
        }, new Action() {
            @Override
            public void run() throws Exception {
                KLog.i("onComplete");
            }
        });
        mCompositeDisposable.add(onComplete);
    }

    private void cacultePriceAndCount() {
        allSize = 0;
        chooseClassifyList = new ArrayList<>();
        for (int i = 0; i < mInfo.getData().getOrderDetails().size(); i++) {
            ShopCarList.ShopCarBean spInfo = new ShopCarList.ShopCarBean();
            spInfo.setProductBusinessNumber(mInfo.getData().getOrderDetails().get(i).getOrderDetailsBusinessNumber());
            spInfo.setProductId(mInfo.getData().getOrderDetails().get(i).getOrderDetailsProductId());
            spInfo.setProductPrice(mInfo.getData().getOrderDetails().get(i).getOrderDetailsProductPrice());
            spInfo.setProductPreferentialPrice(mInfo.getData().getOrderDetails().get(i).getOrderDetailsPreferentialPrice());
            spInfo.setProductImage(mInfo.getData().getOrderDetails().get(i).getOrderDetailsProductImg());
            spInfo.setProductName(mInfo.getData().getOrderDetails().get(i).getOrderDetailsProductName());
            spInfo.setCartNum(mInfo.getData().getOrderDetails().get(i).getOrderDetailsProductNumber());
            chooseClassifyList.add(spInfo);
        }

        mView.setCountAndPrice(chooseClassifyList.size() + "");
    }

//    @Override
//    public void getUser(HashMap map) {
//        //mView.showProgressDialog();
//        Disposable disposable = httpAPIWrapper.getUser(map)
//                .subscribe(new Consumer<User>() {
//                    @Override
//                    public void accept(User user) throws Exception {
//                        //isSuccesse
//                        KLog.i("onSuccesse");
//                        mView.setText(user);
//                      //mView.closeProgressDialog();
//                    }
//                }, new Consumer<Throwable>() {
//                    @Override
//                    public void accept(Throwable throwable) throws Exception {
//                        //onError
//                        KLog.i("onError");
//                        throwable.printStackTrace();
//                      //mView.closeProgressDialog();
//                      //ToastUtil.show(mActivity, mActivity.getString(R.string.loading_failed_1));
//                    }
//                }, new Action() {
//                    @Override
//                    public void run() throws Exception {
//                        //onComplete
//                        KLog.i("onComplete");
//                    }
//                });
//        mCompositeDisposable.add(disposable);
//    }
}