package com.yxld.yxchuangxin.ui.activity.rim.module;

import com.yxld.yxchuangxin.data.api.HttpAPIWrapper;
import com.yxld.yxchuangxin.ui.activity.base.ActivityScope;
import com.yxld.yxchuangxin.ui.activity.rim.ItemServiceFragment;
import com.yxld.yxchuangxin.ui.activity.rim.contract.ItemServiceContract;
import com.yxld.yxchuangxin.ui.activity.rim.presenter.ItemServicePresenter;

import dagger.Module;
import dagger.Provides;

/**
 * @author wwx
 * @Package com.yxld.yxchuangxin.ui.activity.rim
 * @Description: The moduele of ItemServiceFragment, provide field for ItemServiceFragment
 * @date 2017/06/16
 */
@Module
public class ItemServiceModule {
    private final ItemServiceContract.View mView;


    public ItemServiceModule(ItemServiceContract.View view) {
        this.mView = view;
    }

    @Provides
    @ActivityScope
    public ItemServicePresenter provideItemServicePresenter(HttpAPIWrapper httpAPIWrapper) {
        return new ItemServicePresenter(httpAPIWrapper, mView);
    }

    @Provides
    @ActivityScope
    public ItemServiceFragment provideItemServiceFragment() {
        return (ItemServiceFragment) mView;
    }
}