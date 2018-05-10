package com.yxld.yxchuangxin.ui.activity.wuye.module;

import com.yxld.yxchuangxin.data.api.HttpAPIWrapper;
import com.yxld.yxchuangxin.entity.CxwyComplain;
import com.yxld.yxchuangxin.ui.activity.base.ActivityScope;
import com.yxld.yxchuangxin.ui.activity.wuye.ComplainListActivity;
import com.yxld.yxchuangxin.ui.activity.wuye.contract.ComplainListContract;
import com.yxld.yxchuangxin.ui.activity.wuye.presenter.ComplainListPresenter;
import com.yxld.yxchuangxin.ui.adapter.wuye.ComplainListAdapter;

import java.util.ArrayList;
import java.util.List;

import dagger.Module;
import dagger.Provides;

/**
 * @author hu
 * @Package com.yxld.yxchuangxin.ui.activity.wuye
 * @Description: The moduele of ComplainListActivity, provide field for ComplainListActivity
 * @date 2017/06/20 13:31:40
 */
@Module
public class ComplainListModule {
    private final ComplainListContract.View mView;


    public ComplainListModule(ComplainListContract.View view) {
        this.mView = view;
    }

    @Provides
    @ActivityScope
    public ComplainListPresenter provideComplainListPresenter(HttpAPIWrapper httpAPIWrapper, ComplainListActivity mActivity) {
        return new ComplainListPresenter(httpAPIWrapper, mView, mActivity);
    }

    @Provides
    @ActivityScope
    public List<CxwyComplain.ListBean> provideList() {
        List<CxwyComplain.ListBean> list = new ArrayList<>();
        return list;
    }
    @Provides
    @ActivityScope
    public ComplainListAdapter provideComplainListAdapter(List<CxwyComplain.ListBean> list) {
        return new ComplainListAdapter(list);
    }

    @Provides
    @ActivityScope
    public ComplainListActivity provideComplainListActivity() {
        return (ComplainListActivity) mView;
    }
}