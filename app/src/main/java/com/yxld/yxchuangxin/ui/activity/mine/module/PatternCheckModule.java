package com.yxld.yxchuangxin.ui.activity.mine.module;

import com.yxld.yxchuangxin.data.api.HttpAPIWrapper;
import com.yxld.yxchuangxin.ui.activity.base.ActivityScope;
import com.yxld.yxchuangxin.ui.activity.mine.PatternCheckActivity;
import com.yxld.yxchuangxin.ui.activity.mine.contract.PatternCheckContract;
import com.yxld.yxchuangxin.ui.activity.mine.presenter.PatternCheckPresenter;

import dagger.Module;
import dagger.Provides;

/**
 * @author xlei
 * @Package com.yxld.yxchuangxin.ui.activity.mine
 * @Description: The moduele of PatternCheckActivity, provide field for PatternCheckActivity
 * @date 2018/04/04 14:08:08
 */
@Module
public class PatternCheckModule {
    private final PatternCheckContract.View mView;


    public PatternCheckModule(PatternCheckContract.View view) {
        this.mView = view;
    }

    @Provides
    @ActivityScope
    public PatternCheckPresenter providePatternCheckPresenter(HttpAPIWrapper httpAPIWrapper, PatternCheckActivity mActivity) {
        return new PatternCheckPresenter(httpAPIWrapper, mView, mActivity);
    }

    @Provides
    @ActivityScope
    public PatternCheckActivity providePatternCheckActivity() {
        return (PatternCheckActivity) mView;
    }
}