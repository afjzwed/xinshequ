package com.yxld.yxchuangxin.ui.activity.goods.module;

import com.yxld.yxchuangxin.data.api.HttpAPIWrapper;
import com.yxld.yxchuangxin.ui.activity.base.ActivityScope;
import com.yxld.yxchuangxin.ui.activity.goods.AddressManageActivity;
import com.yxld.yxchuangxin.ui.activity.goods.contract.AddressManageContract;
import com.yxld.yxchuangxin.ui.activity.goods.presenter.AddressManagePresenter;

import dagger.Module;
import dagger.Provides;

/**
 * @author hu
 * @Package com.yxld.yxchuangxin.ui.activity.goods
 * @Description: The moduele of AddressManageActivity, provide field for AddressManageActivity
 * @date 2017/06/22 17:20:34
 */
@Module
public class AddressManageModule {
    private final AddressManageContract.View mView;


    public AddressManageModule(AddressManageContract.View view) {
        this.mView = view;
    }

    @Provides
    @ActivityScope
    public AddressManagePresenter provideAddressManagePresenter(HttpAPIWrapper httpAPIWrapper, AddressManageActivity mActivity) {
        return new AddressManagePresenter(httpAPIWrapper, mView, mActivity);
    }

    @Provides
    @ActivityScope
    public AddressManageActivity provideAddressManageActivity() {
        return (AddressManageActivity) mView;
    }
}