package com.yxld.yxchuangxin.ui.activity.ywh.module;

import com.yxld.yxchuangxin.data.api.HttpAPIWrapper;
import com.yxld.yxchuangxin.ui.activity.base.ActivityScope;
import com.yxld.yxchuangxin.ui.activity.ywh.RecommendMemberActivity;
import com.yxld.yxchuangxin.ui.activity.ywh.contract.RecommendMemberContract;
import com.yxld.yxchuangxin.ui.activity.ywh.presenter.RecommendMemberPresenter;

import dagger.Module;
import dagger.Provides;

/**
 * @author William
 * @Package com.yxld.yxchuangxin.ui.activity.wuye
 * @Description: The moduele of RecommendMemberActivity, provide field for RecommendMemberActivity
 * @date 2018/11/07 19:36:36
 */
@Module
public class RecommendMemberModule {
    private final RecommendMemberContract.View mView;


    public RecommendMemberModule(RecommendMemberContract.View view) {
        this.mView = view;
    }

    @Provides
    @ActivityScope
    public RecommendMemberPresenter provideRecommendMemberPresenter(HttpAPIWrapper httpAPIWrapper, RecommendMemberActivity mActivity) {
        return new RecommendMemberPresenter(httpAPIWrapper, mView, mActivity);
    }

    @Provides
    @ActivityScope
    public RecommendMemberActivity provideRecommendMemberActivity() {
        return (RecommendMemberActivity) mView;
    }
}