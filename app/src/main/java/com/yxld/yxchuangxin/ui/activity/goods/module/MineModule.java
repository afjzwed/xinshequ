package com.yxld.yxchuangxin.ui.activity.goods.module;

import com.yxld.yxchuangxin.data.api.HttpAPIWrapper;
import com.yxld.yxchuangxin.ui.activity.base.ActivityScope;
import com.yxld.yxchuangxin.ui.activity.goods.MineFragment;
import com.yxld.yxchuangxin.ui.activity.goods.contract.MineContract;
import com.yxld.yxchuangxin.ui.activity.goods.presenter.MinePresenter;

import dagger.Module;
import dagger.Provides;

/**
 * @author yuan
 * @Package .ui.activity.goods
 * @Description: The moduele of MineFragment, provide field for MineFragment
 * @date 2017/06/14
 */
@Module
public class MineModule {
    private final MineContract.View mView;


    public MineModule(MineContract.View view) {
        this.mView = view;
    }

    @Provides
    @ActivityScope
    public MinePresenter provideMinePresenter(HttpAPIWrapper httpAPIWrapper) {
        return new MinePresenter(httpAPIWrapper, mView);
    }

    @Provides
    @ActivityScope
    public MineFragment provideMineFragment() {
        return (MineFragment) mView;
    }
}