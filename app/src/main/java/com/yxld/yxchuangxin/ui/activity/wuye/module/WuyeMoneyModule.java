package com.yxld.yxchuangxin.ui.activity.wuye.module;

import com.yxld.yxchuangxin.data.api.HttpAPIWrapper;
import com.yxld.yxchuangxin.ui.activity.base.ActivityScope;
import com.yxld.yxchuangxin.ui.activity.wuye.WuyeMoneyActivity;
import com.yxld.yxchuangxin.ui.activity.wuye.contract.WuyeMoneyContract;
import com.yxld.yxchuangxin.ui.activity.wuye.presenter.WuyeMoneyPresenter;

import dagger.Module;
import dagger.Provides;

/**
 * @author hu
 * @Package com.yxld.yxchuangxin.ui.activity.wuye
 * @Description: The moduele of WuyeMoneyActivity, provide field for WuyeMoneyActivity
 * @date 2017/06/06
 */
@Module
public class WuyeMoneyModule {
    private final WuyeMoneyContract.View mView;


    public WuyeMoneyModule(WuyeMoneyContract.View view) {
        this.mView = view;
    }

    @Provides
    @ActivityScope
    public WuyeMoneyPresenter provideWuyeMoneyPresenter(HttpAPIWrapper httpAPIWrapper, WuyeMoneyActivity activity) {
        return new WuyeMoneyPresenter(httpAPIWrapper, mView, activity);
    }

    @Provides
    @ActivityScope
    public WuyeMoneyActivity provideWuyeMoneyActivity() {
        return (WuyeMoneyActivity) mView;
    }
}