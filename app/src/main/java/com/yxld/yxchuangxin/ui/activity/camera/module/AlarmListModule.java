package com.yxld.yxchuangxin.ui.activity.camera.module;

import com.yxld.yxchuangxin.data.api.HttpAPIWrapper;
import com.yxld.yxchuangxin.entity.HostEntiti;
import com.yxld.yxchuangxin.ui.activity.base.ActivityScope;
import com.yxld.yxchuangxin.ui.activity.camera.AlarmListFragment;
import com.yxld.yxchuangxin.ui.activity.camera.contract.AlarmListContract;
import com.yxld.yxchuangxin.ui.activity.camera.presenter.AlarmListPresenter;
import com.yxld.yxchuangxin.ui.adapter.camera.AlarmListAdapter;

import java.util.ArrayList;
import java.util.List;

import dagger.Module;
import dagger.Provides;

/**
 * @author hzp
 * @Package com.yxld.yxchuangxin.ui.activity.camera
 * @Description: The moduele of AlarmListFragment, provide field for AlarmListFragment
 * @date 2017/09/04 15:09:38
 */
@Module
public class AlarmListModule {
    private final AlarmListContract.View mView;


    public AlarmListModule(AlarmListContract.View view) {
        this.mView = view;
    }

    @Provides
    @ActivityScope
    public AlarmListPresenter provideAlarmListPresenter(HttpAPIWrapper httpAPIWrapper, AlarmListFragment mFragment) {
        return new AlarmListPresenter(httpAPIWrapper, mView, mFragment);
    }

    @Provides
    @ActivityScope
    public AlarmListFragment provideAlarmListFragment() {
        return (AlarmListFragment) mView;
    }

    @Provides
    @ActivityScope
    public List<HostEntiti.DataBean> provideList() {
        List<HostEntiti.DataBean> list = new ArrayList<>();
//        list.add(new HostEntiti.DataBean());
//        list.add(new HostEntiti.DataBean());
        return list;
    }

    @Provides
    @ActivityScope
    public AlarmListAdapter provideAlarmListAdapter(List<HostEntiti.DataBean> list) {
        return new AlarmListAdapter(list);
    }

}