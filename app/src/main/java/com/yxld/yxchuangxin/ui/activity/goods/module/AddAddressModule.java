package com.yxld.yxchuangxin.ui.activity.goods.module;

import com.yxld.yxchuangxin.data.api.HttpAPIWrapper;
import com.yxld.yxchuangxin.ui.activity.base.ActivityScope;
import com.yxld.yxchuangxin.ui.activity.goods.AddAddressActivity;
import com.yxld.yxchuangxin.ui.activity.goods.contract.AddAddressContract;
import com.yxld.yxchuangxin.ui.activity.goods.presenter.AddAddressPresenter;

import dagger.Module;
import dagger.Provides;

/**
 * @author hu
 * @Package com.yxld.yxchuangxin.ui.activity.goods
 * @Description: The moduele of AddAddressActivity, provide field for AddAddressActivity
 * @date 2017/06/22 17:36:39
 */
@Module
public class AddAddressModule {
    private final AddAddressContract.View mView;


    public AddAddressModule(AddAddressContract.View view) {
        this.mView = view;
    }

    @Provides
    @ActivityScope
    public AddAddressPresenter provideAddAddressPresenter(HttpAPIWrapper httpAPIWrapper, AddAddressActivity mActivity) {
        return new AddAddressPresenter(httpAPIWrapper, mView, mActivity);
    }

    @Provides
    @ActivityScope
    public AddAddressActivity provideAddAddressActivity() {
        return (AddAddressActivity) mView;
    }
}