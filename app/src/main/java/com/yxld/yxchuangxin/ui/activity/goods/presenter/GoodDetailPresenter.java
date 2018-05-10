package com.yxld.yxchuangxin.ui.activity.goods.presenter;

import android.support.annotation.NonNull;

import com.socks.library.KLog;
import com.yxld.yxchuangxin.base.BaseEntity;
import com.yxld.yxchuangxin.contain.Contains;
import com.yxld.yxchuangxin.data.api.HttpAPIWrapper;
import com.yxld.yxchuangxin.entity.MyAllComment;
import com.yxld.yxchuangxin.entity.goods.MallNewProduct1;
import com.yxld.yxchuangxin.entity.goods.ShopCart;
import com.yxld.yxchuangxin.ui.activity.goods.contract.GoodDetailContract;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;

/**
 * @author hu
 * @Package com.yxld.yxchuangxin.ui.activity.goods
 * @Description: presenter of GoodDetailActivity
 * @date 2017/06/16
 */
public class GoodDetailPresenter implements GoodDetailContract.GoodDetailContractPresenter {

    HttpAPIWrapper httpAPIWrapper;
    private GoodDetailContract.View mView;
    private CompositeDisposable mCompositeDisposable;

    @Inject
    public GoodDetailPresenter(@NonNull HttpAPIWrapper httpAPIWrapper, @NonNull GoodDetailContract.View view) {
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
        mView = null;
    }

    @Override
    public void loadGoodsDetailFromServer(String productId) {
        Map<String, String> params = new HashMap<>();
        params.put("uuid", Contains.uuid);
        params.put("goodsId", productId);
        Disposable disposable = httpAPIWrapper.getGoodsInfo(params)
                .subscribe(new Consumer<MallNewProduct1>() {
                    @Override
                    public void accept(@io.reactivex.annotations.NonNull MallNewProduct1 product) throws Exception {
                        mView.closeProgressDialog();
                        mView.onLoadGoodsDetailSucceed(product);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(@io.reactivex.annotations.NonNull Throwable throwable) throws Exception {
                        mView.closeProgressDialog();
                        mView.onLoadGoodsDetailFailed();
                        throwable.printStackTrace();
                    }
                }, new Action() {
                    @Override
                    public void run() throws Exception {

                    }
                });
        mCompositeDisposable.add(disposable);
    }

    /**
     * 加载评论
     *
     * @param productId
     */
    @Override
    public void loadCommentFromServer(String productId) {
        Map<String, String> params = new HashMap<>();
        params.put("goodsId", productId);
        params.put("page", "1");
        params.put("rows", "1");
        params.put("uuid", Contains.uuid);
        Disposable disposable = httpAPIWrapper.getGoodsAppraise(params)
                .subscribe(new Consumer<MyAllComment>() {
                    @Override
                    public void accept(@io.reactivex.annotations.NonNull MyAllComment comment) throws Exception {
                        mView.closeProgressDialog();
                        mView.onLoadCommentSucceed(comment);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(@io.reactivex.annotations.NonNull Throwable throwable) throws Exception {
                        mView.closeProgressDialog();
                        mView.onLoadGoodsDetailFailed();
                        throwable.printStackTrace();
                    }
                }, new Action() {
                    @Override
                    public void run() throws Exception {

                    }
                });
        mCompositeDisposable.add(disposable);
    }

    @Override
    public void add2ShopCart(Map map) {
        mView.showProgressDialog();
        Disposable disposable = httpAPIWrapper.addGood2ShopCart(map)
                .subscribe(new Consumer<BaseEntity>() {
                    @Override
                    public void accept(@io.reactivex.annotations.NonNull BaseEntity baseEntity) throws Exception {
                        mView.closeProgressDialog();
                        mView.onAdd2ShopCartSuccess(baseEntity);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(@io.reactivex.annotations.NonNull Throwable throwable) throws Exception {
                        KLog.i("error");
                        throwable.printStackTrace();
                        mView.closeProgressDialog();
                        mView.onLoadFailed();
                    }
                }, new Action() {
                    @Override
                    public void run() throws Exception {
                        KLog.e("Detail", "Complete");
                        mView.closeProgressDialog();
                    }
                });
        mCompositeDisposable.add(disposable);
    }

    @Override
    public void loadShopCartFromServer() {
        Map<String, String> params = new HashMap<>();
        params.put("uuid", Contains.uuid);
        Disposable disposable = httpAPIWrapper.getShopCart1(params)
                .subscribe(new Consumer<ShopCart>() {
                    @Override
                    public void accept(@io.reactivex.annotations.NonNull ShopCart cxwyMallCart) throws Exception {
                        mView.onLoadShopCartSucceed(cxwyMallCart);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(@io.reactivex.annotations.NonNull Throwable throwable) throws Exception {
                        throwable.printStackTrace();
                        mView.onLoadFailed();
                    }
                }, new Action() {
                    @Override
                    public void run() throws Exception {

                    }
                });
        mCompositeDisposable.add(disposable);
    }
}