package com.yxld.yxchuangxin.ui.activity.camera.module;

import com.yxld.yxchuangxin.data.api.HttpAPIWrapper;
import com.yxld.yxchuangxin.entity.FangquEntity;
import com.yxld.yxchuangxin.ui.activity.base.ActivityScope;
import com.yxld.yxchuangxin.ui.activity.camera.FangquListActivity;
import com.yxld.yxchuangxin.ui.activity.camera.contract.FangquListContract;
import com.yxld.yxchuangxin.ui.activity.camera.presenter.FangquListPresenter;
import com.yxld.yxchuangxin.ui.adapter.camera.FangQuListAdapter;

import java.util.ArrayList;
import java.util.List;

import dagger.Module;
import dagger.Provides;

/**
 * @author hzp
 * @Package com.yxld.yxchuangxin.ui.activity.camera
 * @Description: The moduele of FangquListActivity, provide field for FangquListActivity
 * @date 2017/09/07 01:27:17
 */
@Module
public class FangquListModule {
    private final FangquListContract.View mView;


    public FangquListModule(FangquListContract.View view) {
        this.mView = view;
    }

    @Provides
    @ActivityScope
    public FangquListPresenter provideFangquListPresenter(HttpAPIWrapper httpAPIWrapper, FangquListActivity mActivity) {
        return new FangquListPresenter(httpAPIWrapper, mView, mActivity);
    }

    @Provides
    @ActivityScope
    public FangquListActivity provideFangquListActivity() {
        return (FangquListActivity) mView;
    }

    @Provides
    @ActivityScope
    public List<FangquEntity.DataBean> provideFangquEntity() {
        List<FangquEntity.DataBean> list = new ArrayList<>();
        return list;
    }

    @Provides
    @ActivityScope
    public FangQuListAdapter provideTimeCheBuFangAdapter(List<FangquEntity.DataBean> list) {
        return new FangQuListAdapter(list);
    }
}