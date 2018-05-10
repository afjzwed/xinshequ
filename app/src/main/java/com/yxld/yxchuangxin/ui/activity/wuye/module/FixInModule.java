package com.yxld.yxchuangxin.ui.activity.wuye.module;

import com.yxld.yxchuangxin.data.api.HttpAPIWrapper;
import com.yxld.yxchuangxin.entity.CxwyBaoxiu;
import com.yxld.yxchuangxin.ui.activity.base.ActivityScope;
import com.yxld.yxchuangxin.ui.activity.wuye.FixInFragment;
import com.yxld.yxchuangxin.ui.activity.wuye.contract.FixInContract;
import com.yxld.yxchuangxin.ui.activity.wuye.presenter.FixInPresenter;
import com.yxld.yxchuangxin.ui.adapter.wuye.FixiListAdapter;

import java.util.ArrayList;
import java.util.List;

import dagger.Module;
import dagger.Provides;

/**
 * @author hu
 * @Package com.yxld.yxchuangxin.ui.activity.wuye
 * @Description: The moduele of FixInFragment, provide field for FixInFragment
 * @date 2017/06/15
 */
@Module
public class FixInModule {
    private final FixInContract.View mView;


    public FixInModule(FixInContract.View view) {
        this.mView = view;
    }

    @Provides
    @ActivityScope
    public FixInPresenter provideFixInPresenter(HttpAPIWrapper httpAPIWrapper) {
        return new FixInPresenter(httpAPIWrapper, mView);
    }

    @Provides
    @ActivityScope
    public List<CxwyBaoxiu.RowsBean> provideList() {
        List<CxwyBaoxiu.RowsBean> list = new ArrayList<>();
        return list;
    }

    @Provides
    @ActivityScope
    public FixiListAdapter provideFixiListAdapter(List<CxwyBaoxiu.RowsBean> list) {
        return new FixiListAdapter(list);
    }

    @Provides
    @ActivityScope
    public FixInFragment provideFixInFragment() {
        return (FixInFragment) mView;
    }
}