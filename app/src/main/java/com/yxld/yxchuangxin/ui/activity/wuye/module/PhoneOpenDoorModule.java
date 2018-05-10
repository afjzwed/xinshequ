package com.yxld.yxchuangxin.ui.activity.wuye.module;

import com.yxld.yxchuangxin.data.api.HttpAPIWrapper;
import com.yxld.yxchuangxin.ui.activity.base.ActivityScope;
import com.yxld.yxchuangxin.ui.activity.wuye.PhoneOpenDoorActivity;
import com.yxld.yxchuangxin.ui.activity.wuye.contract.PhoneOpenDoorContract;
import com.yxld.yxchuangxin.ui.activity.wuye.presenter.PhoneOpenDoorPresenter;

import dagger.Module;
import dagger.Provides;

/**
 * @author hu
 * @Package com.yxld.yxchuangxin.ui.activity.wuye
 * @Description: The moduele of PhoneOpenDoorActivity, provide field for PhoneOpenDoorActivity
 * @date 2017/06/06
 */
@Module
public class PhoneOpenDoorModule {
    private final PhoneOpenDoorContract.View mView;


    public PhoneOpenDoorModule(PhoneOpenDoorContract.View view) {
        this.mView = view;
    }

    @Provides
    @ActivityScope
    public PhoneOpenDoorPresenter providePhoneOpenDoorPresenter(HttpAPIWrapper httpAPIWrapper) {
        return new PhoneOpenDoorPresenter(httpAPIWrapper, mView);
    }

    @Provides
    @ActivityScope
    public PhoneOpenDoorActivity providePhoneOpenDoorActivity() {
        return (PhoneOpenDoorActivity) mView;
    }
}