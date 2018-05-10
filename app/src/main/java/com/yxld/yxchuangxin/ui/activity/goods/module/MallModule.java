package com.yxld.yxchuangxin.ui.activity.goods.module;

import com.yxld.yxchuangxin.data.api.HttpAPIWrapper;
import com.yxld.yxchuangxin.ui.activity.base.ActivityScope;
import com.yxld.yxchuangxin.ui.activity.goods.MallFragment;
import com.yxld.yxchuangxin.ui.activity.goods.contract.MallContract;
import com.yxld.yxchuangxin.ui.activity.goods.presenter.MallPresenter;
import com.yxld.yxchuangxin.ui.adapter.goods.MallNormalTypeAdapter;
import com.yxld.yxchuangxin.ui.adapter.goods.TuiJianAdapter;
import com.yxld.yxchuangxin.ui.adapter.goods.XinPingAdapter;
import com.yxld.yxchuangxin.ui.adapter.goods.YejianAdapter;

import java.util.ArrayList;

import dagger.Module;
import dagger.Provides;

/**
 * @author yuan
 * @Package .ui.activity.goods
 * @Description: The moduele of MallFragment, provide field for MallFragment
 * @date 2017/06/14
 */
@Module
public class MallModule {
    private final MallContract.View mView;


    public MallModule(MallContract.View view) {
        this.mView = view;
    }

    @Provides
    @ActivityScope
    public MallPresenter provideMallPresenter(HttpAPIWrapper httpAPIWrapper, MallFragment fragment) {
        return new MallPresenter(httpAPIWrapper, mView, fragment);
    }

    @Provides
    @ActivityScope
    public MallFragment provideMallFragment() {
        return (MallFragment) mView;
    }

    @Provides
    @ActivityScope
    public XinPingAdapter provideXinPingAdapter() {
        return new XinPingAdapter(new ArrayList<>());
    }

    @Provides
    @ActivityScope
    public TuiJianAdapter provideTuiJianAdapter() {
        return new TuiJianAdapter(new ArrayList<>());
    }

    @Provides
    @ActivityScope
    public MallNormalTypeAdapter provideMallNormalTypeAdapter() {
        return new MallNormalTypeAdapter(new ArrayList<>());
    }
    @Provides
    @ActivityScope
    public YejianAdapter provideYejianAdapter() {
        return new YejianAdapter(new ArrayList<>());
    }
}