package com.yxld.yxchuangxin.ui.activity.wuye.module;

import com.yxld.yxchuangxin.data.api.HttpAPIWrapper;
import com.yxld.yxchuangxin.ui.activity.base.ActivityScope;
import com.yxld.yxchuangxin.ui.activity.wuye.MenjinHelpActivity;
import com.yxld.yxchuangxin.ui.activity.wuye.contract.MenjinHelpContract;
import com.yxld.yxchuangxin.ui.activity.wuye.presenter.MenjinHelpPresenter;

import dagger.Module;
import dagger.Provides;

/**
 * @author hu
 * @Package com.yxld.yxchuangxin.ui.activity.wuye
 * @Description: The moduele of MenjinHelpActivity, provide field for MenjinHelpActivity
 * @date 2017/06/26 15:48:18
 */
@Module
public class MenjinHelpModule {
    private final MenjinHelpContract.View mView;


    public MenjinHelpModule(MenjinHelpContract.View view) {
        this.mView = view;
    }

    @Provides
    @ActivityScope
    public MenjinHelpPresenter provideMenjinHelpPresenter(HttpAPIWrapper httpAPIWrapper, MenjinHelpActivity mActivity) {
        return new MenjinHelpPresenter(httpAPIWrapper, mView, mActivity);
    }

    @Provides
    @ActivityScope
    public MenjinHelpActivity provideMenjinHelpActivity() {
        return (MenjinHelpActivity) mView;
    }
}