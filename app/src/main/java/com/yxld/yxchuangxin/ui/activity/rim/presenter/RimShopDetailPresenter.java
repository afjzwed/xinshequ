package com.yxld.yxchuangxin.ui.activity.rim.presenter;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;

import com.socks.library.KLog;
import com.yxld.yxchuangxin.data.api.HttpAPIWrapper;
import com.yxld.yxchuangxin.entity.RimShopDetailBean;
import com.yxld.yxchuangxin.ui.activity.rim.RimShopCommentListActivity;
import com.yxld.yxchuangxin.ui.activity.rim.RimShopDetailActivity;
import com.yxld.yxchuangxin.ui.activity.rim.WebViewActivity;
import com.yxld.yxchuangxin.ui.activity.rim.contract.RimShopDetailContract;

import java.util.Map;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;

/**
 * @author William
 * @Package com.yxld.yxchuangxin.ui.activity.rimshopdetail
 * @Description: presenter of RimShopDetailActivity
 * @date 2017/12/15 12:04:35
 */
public class RimShopDetailPresenter implements RimShopDetailContract
        .RimShopDetailContractPresenter {

    HttpAPIWrapper httpAPIWrapper;
    private final RimShopDetailContract.View mView;
    private CompositeDisposable mCompositeDisposable;
    private RimShopDetailActivity mActivity;

    @Inject
    public RimShopDetailPresenter(@NonNull HttpAPIWrapper httpAPIWrapper, @NonNull
            RimShopDetailContract.View view, RimShopDetailActivity activity) {
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

    /**
     * 跳转到商家评价列表界面
     * @param businessNumber
     * @param score
     * @param logo
     * @param appraiseNum
     */
    public void startRimShopCommentListActivity(String businessNumber, double score, String logo,
                                                int appraiseNum) {
        Intent intent = new Intent(mActivity, RimShopCommentListActivity.class);
        intent.putExtra("businessNumber", businessNumber);
        intent.putExtra("score", score);
        intent.putExtra("logo", logo);
        intent.putExtra("appraiseNum", appraiseNum);
        mActivity.startActivity(intent);
    }

    /**
     * 跳转到服务流程界面
     */
    public void startRimFlowProcessActivity() {
        // TODO: 2017/12/18 这里应该要传商家参数
        Intent intent = new Intent(mActivity, WebViewActivity.class);
        Bundle xq = new Bundle();
        xq.putString("name", "e袋洗");
        xq.putString("address", "http://www.edaixi.com");
        intent.putExtras(xq);
        mActivity.startActivity(intent);
    }

    /**
     * 跳转到商家详情界面
     */
    public void startWebViewActivity() {
        // TODO: 2017/12/18 这里应该要传商家参数
        Intent intent = new Intent(mActivity, WebViewActivity.class);
        Bundle xq = new Bundle();
        xq.putString("name", "e袋洗");
        xq.putString("address", "http://www.edaixi.com");
        intent.putExtras(xq);
        mActivity.startActivity(intent);
    }

    @Override
    public void getRimShopDetail(Map map) {
                mView.showProgressDialog();
        Disposable disposable = httpAPIWrapper.getRimShopDetail(map)
                .subscribe(new Consumer<RimShopDetailBean>() {
                    @Override
                    public void accept(RimShopDetailBean data) throws Exception {
                        //isSuccesse
                  /*      KLog.i("onSuccesse");
                        mView.setData(user);
                      //mView.closeProgressDialog();*/
                        mView.closeProgressDialog();
                        if (data.getSuccess().equals("1")) {
                            mView.setData(data);
                        } else {
                            mView.setEmptyData(data);
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        //onError
                        KLog.i("onError");
                        mView.setError();
                        throwable.printStackTrace();
                        mView.closeProgressDialog();
                        //ToastUtil.show(mActivity, mActivity.getString(R.string.loading_failed_1));
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

    public void makeCall(String phoneNum) {
        // TODO: 2017/12/18 这里没有请求权限 之后添加
        Intent intent = new Intent(Intent.ACTION_DIAL);
        Uri data = Uri.parse("tel:" + phoneNum);
        intent.setData(data);
        mActivity.startActivity(intent);
    }
}