package com.yxld.yxchuangxin.ui.activity.rim.presenter;

import android.support.annotation.NonNull;
import android.util.Log;

import com.socks.library.KLog;
import com.yxld.yxchuangxin.contain.Contains;
import com.yxld.yxchuangxin.data.api.HttpAPIWrapper;
import com.yxld.yxchuangxin.entity.CxwyBusinessInfo;
import com.yxld.yxchuangxin.entity.PushOrder;
import com.yxld.yxchuangxin.entity.RimActivityDiscount;
import com.yxld.yxchuangxin.entity.ShopCarList;
import com.yxld.yxchuangxin.ui.activity.rim.BusinessPushProductActivity;
import com.yxld.yxchuangxin.ui.activity.rim.contract.BusinessPushProductContract;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
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
 * @Description: presenter of BusinessPushProductActivity
 * @date 2017/06/19 11:24:46
 */
public class BusinessPushProductPresenter implements BusinessPushProductContract
        .BusinessPushProductContractPresenter {

    HttpAPIWrapper httpAPIWrapper;
    private final BusinessPushProductContract.View mView;
    private CompositeDisposable mCompositeDisposable;
    private BusinessPushProductActivity mActivity;

    //记录各个商品的数量
    private List<ShopCarList.ShopCarBean> chooseClassifyList = new ArrayList<>();
    private CxwyBusinessInfo mInfo;
    private String fcxx;
    private double allPrice;

    @Inject
    public BusinessPushProductPresenter(@NonNull HttpAPIWrapper httpAPIWrapper, @NonNull
            BusinessPushProductContract.View view, BusinessPushProductActivity activity) {
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
    public void getAddress() {
        fcxx = Contains.appYezhuFangwus.get(Contains.curFangwu).getXiangmuLoupan()
                + Contains.appYezhuFangwus.get(Contains.curFangwu).getFwLoudong()
                + "栋 "
                + Contains.appYezhuFangwus.get(Contains.curFangwu).getFwDanyuan()
                + "单元 "
                + Contains.appYezhuFangwus.get(Contains.curFangwu).getFwFanghao()
                .toString() + "号";

        mView.upDateAddress(Contains.user.getYezhuName(), Contains.user.getYezhuShouji(), fcxx);
    }


    @Override
    public void getBusinessInfoAndProduct(String num, List<ShopCarList.ShopCarBean> list) {
       chooseClassifyList = list;
        mInfo = Contains.mBusinessInfo;
        mView.upDateBusinessInfoAndProduct(list);

    }

    @Override
    public void upLoadOrderInfo(Map<String, String> map) {
        for (int i = 0; i < chooseClassifyList.size(); i++) {
            // 拼接商品信息
            map.put("list[" + i + "].orderDetailsBusinessPrice", chooseClassifyList.get(i)
                    .getProductPrice() + "");
            map.put("list[" + i + "].orderDetailsPreferentialPrice", chooseClassifyList.get(i)
                    .getProductPreferentialPrice() + "");
            map.put("list[" + i + "].orderDetailsProductId", chooseClassifyList.get(i)
                    .getProductId() + "");
            map.put("list[" + i + "].orderDetailsProductImg", chooseClassifyList.get(i)
                    .getProductImage() + "");
            map.put("list[" + i + "].orderDetailsProductName", chooseClassifyList.get(i)
                    .getProductName() + "");
            map.put("list[" + i + "].orderDetailsProductNumber", chooseClassifyList.get(i).getCartNum()
                    + "");
            map.put("list[" + i + "].orderDetailsProductPrice", chooseClassifyList.get(i)
                    .getProductPrice()*chooseClassifyList.get(i).getCartNum() + "");
            map.put("list[" + i + "].orderDetailsBusinessNumber", chooseClassifyList.get(i)
                    .getProductBusinessNumber() + "");
        }
        map.put("orderBusinessName", mInfo.getData().getBusiness().getBusinessName() + "");
        map.put("orderBusinessNumber", mInfo.getData().getBusiness().getBusinessNumber() + "");
        map.put("orderUserAddress", fcxx);
        map.put("uuId", Contains.uuid);
        mView.showProgressDialog();
        Disposable disposable = httpAPIWrapper.addOrder(map)
                .subscribe(new Consumer<PushOrder>() {
                    @Override
                    public void accept(PushOrder order) throws Exception {
                        //isSuccesse
                        mView.closeProgressDialog();
                        mView.commitOrdSuccess(order);

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

    /**
     * 计算所有产品的数量和价格
     *
     * @param businessNumber
     */
    public void getAllPriceAndProductCount(String businessNumber) {
        int count = 0;
        allPrice = 0;
        for (int j = 0; j < chooseClassifyList.size(); j++) {
            allPrice += chooseClassifyList.get(j).getProductPreferentialPrice() * chooseClassifyList.get(j).getCartNum();
            count += chooseClassifyList.get(j).getCartNum();
        }
        mView.setProductInfo(count + "", allPrice + "", mInfo.getData().getBusiness()
                .getBusinessName());

        Map<String, String> map = new HashMap<>();
        map.put("uuId", Contains.uuid);
        map.put("businessNumber", businessNumber);
        map.put("productNum", count + "");
        map.put("totalMoney", allPrice + "");
        Log.e("william", map.toString());
        StringBuilder sb = new StringBuilder();
        for (ShopCarList.ShopCarBean id : chooseClassifyList) {
            sb.append(id.getProductId()).append(",");
        }
        sb.delete(sb.lastIndexOf(","), sb.length());
        KLog.i("购物车商品id" + sb.toString());
        map.put("shangpinIds[]", sb.toString());
        getActivityDiscount(map);
    }

    /**
     * 获取打折信息
     *
     * @param map
     */
    private void getActivityDiscount(Map<String, String> map) {
        mView.showProgressDialog();
        Disposable disposable = httpAPIWrapper.getRimActivityDiscount(map)
                .subscribe(new Consumer<RimActivityDiscount>() {
                    @Override
                    public void accept(RimActivityDiscount data) throws Exception {
                        //isSuccesse
                        KLog.i("onSuccesse");

                        mView.setDiscountInfo(data);
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