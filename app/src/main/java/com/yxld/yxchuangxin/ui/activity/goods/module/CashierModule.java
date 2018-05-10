package com.yxld.yxchuangxin.ui.activity.goods.module;

import com.yxld.yxchuangxin.data.api.HttpAPIWrapper;
import com.yxld.yxchuangxin.ui.activity.base.ActivityScope;
import com.yxld.yxchuangxin.ui.activity.goods.CashierActivity;
import com.yxld.yxchuangxin.ui.activity.goods.contract.CashierContract;
import com.yxld.yxchuangxin.ui.activity.goods.presenter.CashierPresenter;

import dagger.Module;
import dagger.Provides;

/**
 * @author Yuan.Y.Q
 * @Package com.yxld.yxchuangxin.ui.activity.goods
 * @Description: The moduele of CashierActivity, provide field for CashierActivity
 * @date 2017/06/27 19:49:09
 */
@Module
public class CashierModule {
    private final CashierContract.View mView;


    public CashierModule(CashierContract.View view) {
        this.mView = view;
    }

    @Provides
    @ActivityScope
    public CashierPresenter provideCashierPresenter(HttpAPIWrapper httpAPIWrapper, CashierActivity mActivity) {
        return new CashierPresenter(httpAPIWrapper, mView, mActivity);
    }

    @Provides
    @ActivityScope
    public CashierActivity provideCashierActivity() {
        return (CashierActivity) mView;
    }
}