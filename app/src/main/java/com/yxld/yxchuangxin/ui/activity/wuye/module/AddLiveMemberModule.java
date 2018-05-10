package com.yxld.yxchuangxin.ui.activity.wuye.module;

import com.yxld.yxchuangxin.data.api.HttpAPIWrapper;
import com.yxld.yxchuangxin.ui.activity.base.ActivityScope;
import com.yxld.yxchuangxin.ui.activity.wuye.AddLiveMemberActivity;
import com.yxld.yxchuangxin.ui.activity.wuye.contract.AddLiveMemberContract;
import com.yxld.yxchuangxin.ui.activity.wuye.presenter.AddLiveMemberPresenter;

import dagger.Module;
import dagger.Provides;

/**
 * @author hu
 * @Package com.yxld.yxchuangxin.ui.activity.wuye
 * @Description: The moduele of AddLiveMemberActivity, provide field for AddLiveMemberActivity
 * @date 2017/06/07
 */
@Module
public class AddLiveMemberModule {
    private final AddLiveMemberContract.View mView;


    public AddLiveMemberModule(AddLiveMemberContract.View view) {
        this.mView = view;
    }

    @Provides
    @ActivityScope
    public AddLiveMemberPresenter provideAddLiveMemberPresenter(HttpAPIWrapper httpAPIWrapper, AddLiveMemberActivity activity) {
        return new AddLiveMemberPresenter(httpAPIWrapper, mView, activity);
    }

    @Provides
    @ActivityScope
    public AddLiveMemberActivity provideAddLiveMemberActivity() {
        return (AddLiveMemberActivity) mView;
    }
}