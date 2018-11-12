package com.yxld.yxchuangxin.ui.activity.wuye.module;

import com.yxld.yxchuangxin.data.api.HttpAPIWrapper;
import com.yxld.yxchuangxin.ui.activity.base.ActivityScope;
import com.yxld.yxchuangxin.ui.activity.wuye.OpinionSurveyActivity;
import com.yxld.yxchuangxin.ui.activity.wuye.contract.OpinionSurveyContract;
import com.yxld.yxchuangxin.ui.activity.wuye.presenter.OpinionSurveyPresenter;

import dagger.Module;
import dagger.Provides;

/**
 * @author William
 * @Package com.yxld.yxchuangxin.ui.activity.wuye
 * @Description: The moduele of OpinionSurveyActivity, provide field for OpinionSurveyActivity
 * @date 2018/11/12 18:08:34
 */
@Module
public class OpinionSurveyModule {
    private final OpinionSurveyContract.View mView;


    public OpinionSurveyModule(OpinionSurveyContract.View view) {
        this.mView = view;
    }

    @Provides
    @ActivityScope
    public OpinionSurveyPresenter provideOpinionSurveyPresenter(HttpAPIWrapper httpAPIWrapper, OpinionSurveyActivity mActivity) {
        return new OpinionSurveyPresenter(httpAPIWrapper, mView, mActivity);
    }

    @Provides
    @ActivityScope
    public OpinionSurveyActivity provideOpinionSurveyActivity() {
        return (OpinionSurveyActivity) mView;
    }
}