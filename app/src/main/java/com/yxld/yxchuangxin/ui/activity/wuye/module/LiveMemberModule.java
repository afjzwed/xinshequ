package com.yxld.yxchuangxin.ui.activity.wuye.module;

import com.yxld.yxchuangxin.data.api.HttpAPIWrapper;
import com.yxld.yxchuangxin.entity.AppYezhuFangwu;
import com.yxld.yxchuangxin.ui.activity.base.ActivityScope;
import com.yxld.yxchuangxin.ui.activity.wuye.LiveMemberActivity;
import com.yxld.yxchuangxin.ui.activity.wuye.contract.LiveMemberContract;
import com.yxld.yxchuangxin.ui.activity.wuye.presenter.LiveMemberPresenter;
import com.yxld.yxchuangxin.ui.adapter.wuye.LiveMemberAdapter;

import java.util.ArrayList;
import java.util.List;

import dagger.Module;
import dagger.Provides;

/**
 * @author hu
 * @Package com.yxld.yxchuangxin.ui.activity.wuye
 * @Description: The moduele of LiveMemberActivity, provide field for LiveMemberActivity
 * @date 2017/06/07
 */
@Module
public class LiveMemberModule {
    private final LiveMemberContract.View mView;


    public LiveMemberModule(LiveMemberContract.View view) {
        this.mView = view;
    }

    @Provides
    @ActivityScope
    public LiveMemberPresenter provideLiveMemberPresenter(HttpAPIWrapper httpAPIWrapper, LiveMemberActivity activity) {
        return new LiveMemberPresenter(httpAPIWrapper, mView, activity);
    }

    @Provides
    @ActivityScope
    public List<AppYezhuFangwu> provideAppYezhuFangwu() {
        return new ArrayList<AppYezhuFangwu>();
    }

    @Provides
    @ActivityScope
    public LiveMemberAdapter provideLiveMemberAdapter(List<AppYezhuFangwu> data) {
        return new LiveMemberAdapter(data);
    }

    @Provides
    @ActivityScope
    public LiveMemberActivity provideLiveMemberActivity() {
        return (LiveMemberActivity) mView;
    }
}