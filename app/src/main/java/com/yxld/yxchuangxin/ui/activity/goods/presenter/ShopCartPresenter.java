package com.yxld.yxchuangxin.ui.activity.goods.presenter;

import android.support.annotation.NonNull;

import com.socks.library.KLog;
import com.yxld.yxchuangxin.Utils.ToastUtil;
import com.yxld.yxchuangxin.base.BaseActivity;
import com.yxld.yxchuangxin.base.BaseEntity;
import com.yxld.yxchuangxin.contain.Contains;
import com.yxld.yxchuangxin.data.api.HttpAPIWrapper;
import com.yxld.yxchuangxin.entity.CxwyMallCart;
import com.yxld.yxchuangxin.entity.goods.ShopCart;
import com.yxld.yxchuangxin.ui.activity.goods.ShopCartFragment;
import com.yxld.yxchuangxin.ui.activity.goods.contract.ShopCartContract;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;

/**
 * @author yuan
 * @Package com.yxld.yxchuangxin.ui.activity.goods
 * @Description: presenter of ShopCartFragment
 * @date 2017/06/14
 */
public class ShopCartPresenter implements ShopCartContract.ShopCartContractPresenter {

    HttpAPIWrapper httpAPIWrapper;
    private ShopCartContract.View mView;
    private CompositeDisposable mCompositeDisposable;
    private ShopCartFragment mFragment;

    @Inject
    public ShopCartPresenter(@NonNull HttpAPIWrapper httpAPIWrapper, @NonNull ShopCartContract.View view, @NonNull ShopCartFragment fragment) {
        mView = view;
        this.httpAPIWrapper = httpAPIWrapper;
        mCompositeDisposable = new CompositeDisposable();
        mFragment = fragment;
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
    public void getShopCartProductsFromServer() {
        Map<String, String> params = new HashMap<>();
        params.put("uuid", Contains.uuid);
        Disposable disposable = httpAPIWrapper.getShopCart1(params)
                .subscribe(new Consumer<ShopCart>() {
                    @Override
                    public void accept(@io.reactivex.annotations.NonNull ShopCart shopCart) throws Exception {
                        mView.onLoadCartProductSuccess(shopCart);

                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(@io.reactivex.annotations.NonNull Throwable throwable) throws Exception {
                    }
                }, new Action() {
                    @Override
                    public void run() throws Exception {

                    }
                });
        mCompositeDisposable.add(disposable);
    }

    @Override
    public void changeCartCount(String num, String bianhao) {
        Map<String, String> params = new HashMap<>();
        params.put("cartNum", num);
        params.put("cartSpbianhao", bianhao);
        params.put("uuid", Contains.uuid);
        Disposable disposable = httpAPIWrapper.getShopCartUpdate(params)
                .subscribe(new Consumer<BaseEntity>() {
                    @Override
                    public void accept(@io.reactivex.annotations.NonNull BaseEntity baseEntity) throws Exception {
                        if (baseEntity.getStatus() == 1) {
                            mView.changeCartCountSuccess();
                        } else {
                            ToastUtil.show(mFragment.getActivity(), baseEntity.getMSG());
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(@io.reactivex.annotations.NonNull Throwable throwable) throws Exception {
                        throwable.printStackTrace();
                        mView.onLoadProductFailed();
                    }
                }, new Action() {
                    @Override
                    public void run() throws Exception {

                    }
                });
        mCompositeDisposable.add(disposable);
    }

    @Override
    public void deleteCart(List<Integer> cartsId) {
        StringBuilder sb = new StringBuilder();
        for (int id : cartsId) {
            sb.append(id).append(",");
        }
        sb.delete(sb.lastIndexOf(","), sb.length());
        KLog.i("商品id" + sb.toString());
        Map<String, String> params = new HashMap<>();
        params.put("shangpinIds[]", sb.toString());
        params.put("uuid", Contains.uuid);
        Disposable disposable = httpAPIWrapper.getShopCartDeLelte(params)
                .subscribe(new Consumer<BaseEntity>() {
                    @Override
                    public void accept(@io.reactivex.annotations.NonNull BaseEntity baseEntity) throws Exception {
                        if (baseEntity.status == 1) {
                            mView.onDeleteCartSucceed();
                        } else {
                            mView.onLoadProductFailed(baseEntity.getMSG());
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(@io.reactivex.annotations.NonNull Throwable throwable) throws Exception {
                        throwable.printStackTrace();
                        mView.onLoadProductFailed();
                    }
                }, new Action() {
                    @Override
                    public void run() throws Exception {

                    }
                });
        mCompositeDisposable.add(disposable);
    }

}