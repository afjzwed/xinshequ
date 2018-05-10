package com.yxld.yxchuangxin.ui.activity.wuye.module;

import com.yxld.yxchuangxin.data.api.HttpAPIWrapper;
import com.yxld.yxchuangxin.entity.CxwyMessage;
import com.yxld.yxchuangxin.ui.activity.base.ActivityScope;
import com.yxld.yxchuangxin.ui.activity.wuye.ActivityFragment;
import com.yxld.yxchuangxin.ui.activity.wuye.contract.ActivityContract;
import com.yxld.yxchuangxin.ui.activity.wuye.presenter.ActivityPresenter;
import com.yxld.yxchuangxin.ui.adapter.wuye.ActivityAdapter;

import java.util.ArrayList;
import java.util.List;

import dagger.Module;
import dagger.Provides;

/**
 * @author hu
 * @Package com.yxld.yxchuangxin.ui.activity.wuye
 * @Description: The moduele of ActivityFragment, provide field for ActivityFragment
 * @date 2017/06/14
 */
@Module
public class ActivityModule {
    private final ActivityContract.View mView;


    public ActivityModule(ActivityContract.View view) {
        this.mView = view;
    }

    @Provides
    @ActivityScope
    public ActivityPresenter provideActivityPresenter(HttpAPIWrapper httpAPIWrapper) {
        return new ActivityPresenter(httpAPIWrapper, mView);
    }

    @Provides
    @ActivityScope
    public List<CxwyMessage.RowsBean> provideList() {
        List<CxwyMessage.RowsBean> list = new ArrayList<>();
        return list;
    }

    @Provides
    @ActivityScope
    public ActivityAdapter provideActivityAdapter(List<CxwyMessage.RowsBean> list) {
        return new ActivityAdapter(list);
    }

    @Provides
    @ActivityScope
    public ActivityFragment provideActivityFragment() {
        return (ActivityFragment) mView;
    }
}