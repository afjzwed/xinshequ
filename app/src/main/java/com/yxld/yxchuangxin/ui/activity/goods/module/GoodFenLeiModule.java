package com.yxld.yxchuangxin.ui.activity.goods.module;

import com.yxld.yxchuangxin.data.api.HttpAPIWrapper;
import com.yxld.yxchuangxin.ui.activity.base.ActivityScope;
import com.yxld.yxchuangxin.ui.activity.goods.GoodFenLeiFragment;
import com.yxld.yxchuangxin.ui.activity.goods.contract.GoodFenLeiContract;
import com.yxld.yxchuangxin.ui.activity.goods.presenter.GoodFenLeiPresenter;

import dagger.Module;
import dagger.Provides;

/**
 * @author xlei
 * @Package com.yxld.yxchuangxin.ui.activity.goods
 * @Description: The moduele of GoodFenLeiFragment, provide field for GoodFenLeiFragment
 * @date 2017/10/19 09:28:17
 */
@Module
public class GoodFenLeiModule {
    private final GoodFenLeiContract.View mView;


    public GoodFenLeiModule(GoodFenLeiContract.View view) {
        this.mView = view;
    }

    @Provides
    @ActivityScope
    public GoodFenLeiPresenter provideGoodFenLeiPresenter(HttpAPIWrapper httpAPIWrapper, GoodFenLeiFragment mFragment) {
        return new GoodFenLeiPresenter(httpAPIWrapper, mView, mFragment);
    }

    @Provides
    @ActivityScope
    public GoodFenLeiFragment provideGoodFenLeiFragment() {
        return (GoodFenLeiFragment) mView;
    }
}