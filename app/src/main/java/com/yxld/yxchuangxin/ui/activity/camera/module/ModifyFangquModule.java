package com.yxld.yxchuangxin.ui.activity.camera.module;

import com.yxld.yxchuangxin.data.api.HttpAPIWrapper;
import com.yxld.yxchuangxin.ui.activity.base.ActivityScope;
import com.yxld.yxchuangxin.ui.activity.camera.ModifyFangquActivity;
import com.yxld.yxchuangxin.ui.activity.camera.contract.ModifyFangquContract;
import com.yxld.yxchuangxin.ui.activity.camera.presenter.ModifyFangquPresenter;

import dagger.Module;
import dagger.Provides;

/**
 * @author hzp
 * @Package com.yxld.yxchuangxin.ui.activity.camera
 * @Description: The moduele of ModifyFangquActivity, provide field for ModifyFangquActivity
 * @date 2017/09/19 17:43:44
 */
@Module
public class ModifyFangquModule {
    private final ModifyFangquContract.View mView;


    public ModifyFangquModule(ModifyFangquContract.View view) {
        this.mView = view;
    }

    @Provides
    @ActivityScope
    public ModifyFangquPresenter provideModifyFangquPresenter(HttpAPIWrapper httpAPIWrapper, ModifyFangquActivity mActivity) {
        return new ModifyFangquPresenter(httpAPIWrapper, mView, mActivity);
    }

    @Provides
    @ActivityScope
    public ModifyFangquActivity provideModifyFangquActivity() {
        return (ModifyFangquActivity) mView;
    }
}