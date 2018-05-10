package com.yxld.yxchuangxin.ui.activity.mine.module;

import com.yxld.yxchuangxin.data.api.HttpAPIWrapper;
import com.yxld.yxchuangxin.ui.activity.base.ActivityScope;
import com.yxld.yxchuangxin.ui.activity.mine.PatternProveActivity;
import com.yxld.yxchuangxin.ui.activity.mine.contract.PatternProveContract;
import com.yxld.yxchuangxin.ui.activity.mine.presenter.PatternProvePresenter;

import dagger.Module;
import dagger.Provides;

/**
 * @author xlei
 * @Package com.yxld.yxchuangxin.ui.activity.mine
 * @Description: The moduele of PatternProveActivity, provide field for PatternProveActivity
 * @date 2018/04/04 11:25:15
 */
@Module
public class PatternProveModule {
    private final PatternProveContract.View mView;


    public PatternProveModule(PatternProveContract.View view) {
        this.mView = view;
    }

    @Provides
    @ActivityScope
    public PatternProvePresenter providePatternProvePresenter(HttpAPIWrapper httpAPIWrapper, PatternProveActivity mActivity) {
        return new PatternProvePresenter(httpAPIWrapper, mView, mActivity);
    }

    @Provides
    @ActivityScope
    public PatternProveActivity providePatternProveActivity() {
        return (PatternProveActivity) mView;
    }
}