package com.yxld.yxchuangxin.ui.activity.rim.module;

import com.yxld.yxchuangxin.data.api.HttpAPIWrapper;
import com.yxld.yxchuangxin.entity.CxwyBusiness;
import com.yxld.yxchuangxin.ui.activity.base.ActivityScope;
import com.yxld.yxchuangxin.ui.activity.rim.BusinessListActivity;
import com.yxld.yxchuangxin.ui.activity.rim.contract.BusinessListContract;
import com.yxld.yxchuangxin.ui.activity.rim.presenter.BusinessListPresenter;
import com.yxld.yxchuangxin.ui.adapter.rim.BusinessListAdapter;

import java.util.ArrayList;
import java.util.List;

import dagger.Module;
import dagger.Provides;

/**
 * @author hu
 * @Package com.yxld.yxchuangxin.ui.activity.rim
 * @Description: The moduele of BusinessListActivity, provide field for BusinessListActivity
 * @date 2017/06/16
 */
@Module
public class BusinessListModule {
    private final BusinessListContract.View mView;


    public BusinessListModule(BusinessListContract.View view) {
        this.mView = view;
    }

    @Provides
    @ActivityScope
    public BusinessListPresenter provideBusinessListPresenter(HttpAPIWrapper httpAPIWrapper) {
        return new BusinessListPresenter(httpAPIWrapper, mView);
    }

    @Provides
    @ActivityScope
    public List<CxwyBusiness.DataBean> provideList() {
        List<CxwyBusiness.DataBean> list = new ArrayList<>();
        for (int i = 0; i < 15; i++) {

            list.add(new CxwyBusiness.DataBean());
        }
        return list;
    }

    @Provides
    @ActivityScope
    public BusinessListAdapter provideBusinessListAdapter(List<CxwyBusiness.DataBean> list) {
        return new BusinessListAdapter(list);
    }


    @Provides
    @ActivityScope
    public BusinessListActivity provideBusinessListActivity() {
        return (BusinessListActivity) mView;
    }
}