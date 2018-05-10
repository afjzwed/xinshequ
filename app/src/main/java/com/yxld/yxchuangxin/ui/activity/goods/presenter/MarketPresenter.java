package com.yxld.yxchuangxin.ui.activity.goods.presenter;

import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;

import com.yxld.yxchuangxin.data.api.HttpAPIWrapper;
import com.yxld.yxchuangxin.ui.activity.goods.MallFragment;
import com.yxld.yxchuangxin.ui.activity.goods.MineFragment;
import com.yxld.yxchuangxin.ui.activity.goods.ShopCartFragment;
import com.yxld.yxchuangxin.ui.activity.goods.contract.MarketContract;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;

/**
 * @author hu
 * @Package com.yxld.yxchuangxin.ui.activity.goods
 * @Description: presenter of MarketActivity
 * @date 2017/06/12
 */
public class MarketPresenter implements MarketContract.MarketContractPresenter{

    HttpAPIWrapper httpAPIWrapper;
    private MarketContract.View mView;
    private CompositeDisposable mCompositeDisposable;

    @Inject
    public MarketPresenter(@NonNull HttpAPIWrapper httpAPIWrapper, @NonNull MarketContract.View view) {
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
    public void initAdapterData() {
        List<Fragment> fragments = new ArrayList<>();
        fragments.add(new MallFragment());
        fragments.add(new ShopCartFragment());
        fragments.add(new MineFragment());

        List<String> titles = new ArrayList<>();
        titles.add("商城");
        titles.add("购物车");
        titles.add("我的");

        mView.setAdapter(fragments,titles);
    }

}