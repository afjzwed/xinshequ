package com.yxld.yxchuangxin.ui.activity.goods.module;

import com.yxld.yxchuangxin.data.api.HttpAPIWrapper;
import com.yxld.yxchuangxin.ui.activity.base.ActivityScope;
import com.yxld.yxchuangxin.ui.activity.goods.GoodsFenLeiActivity;
import com.yxld.yxchuangxin.ui.activity.goods.contract.GoodsFenLeiContract;
import com.yxld.yxchuangxin.ui.activity.goods.presenter.GoodsFenLeiPresenter;

import dagger.Module;
import dagger.Provides;

/**
 * @author xlei
 * @Package com.yxld.yxchuangxin.ui.activity.goods
 * @Description: The moduele of GoodsFenLeiActivity, provide field for GoodsFenLeiActivity
 * @date 2017/10/19 08:58:40
 */
@Module
public class GoodsFenLeiModule {
    private final GoodsFenLeiContract.View mView;


    public GoodsFenLeiModule(GoodsFenLeiContract.View view) {
        this.mView = view;
    }

    @Provides
    @ActivityScope
    public GoodsFenLeiPresenter provideGoodsFenLeiPresenter(HttpAPIWrapper httpAPIWrapper, GoodsFenLeiActivity mActivity) {
        return new GoodsFenLeiPresenter(httpAPIWrapper, mView, mActivity);
    }

    @Provides
    @ActivityScope
    public GoodsFenLeiActivity provideGoodsFenLeiActivity() {
        return (GoodsFenLeiActivity) mView;
    }
}