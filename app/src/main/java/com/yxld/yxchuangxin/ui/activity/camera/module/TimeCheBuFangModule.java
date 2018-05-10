package com.yxld.yxchuangxin.ui.activity.camera.module;

import com.yxld.yxchuangxin.data.api.HttpAPIWrapper;
import com.yxld.yxchuangxin.entity.BuCheFang;
import com.yxld.yxchuangxin.ui.activity.base.ActivityScope;
import com.yxld.yxchuangxin.ui.activity.camera.TimeCheBuFangActivity;
import com.yxld.yxchuangxin.ui.activity.camera.contract.TimeCheBuFangContract;
import com.yxld.yxchuangxin.ui.activity.camera.presenter.TimeCheBuFangPresenter;
import com.yxld.yxchuangxin.ui.adapter.camera.TimeCheBuFangAdapter;

import java.util.ArrayList;
import java.util.List;

import dagger.Module;
import dagger.Provides;

/**
 * @author hzp
 * @Package com.yxld.yxchuangxin.ui.activity.camera
 * @Description: The moduele of TimeCheBuFangActivity, provide field for TimeCheBuFangActivity
 * @date 2017/09/05 17:39:46
 */
@Module
public class TimeCheBuFangModule {
    private final TimeCheBuFangContract.View mView;


    public TimeCheBuFangModule(TimeCheBuFangContract.View view) {
        this.mView = view;
    }

    @Provides
    @ActivityScope
    public TimeCheBuFangPresenter provideTimeCheBuFangPresenter(HttpAPIWrapper httpAPIWrapper, TimeCheBuFangActivity mActivity) {
        return new TimeCheBuFangPresenter(httpAPIWrapper, mView, mActivity);
    }

    @Provides
    @ActivityScope
    public TimeCheBuFangActivity provideTimeCheBuFangActivity() {
        return (TimeCheBuFangActivity) mView;
    }

    @Provides
    @ActivityScope
    public List<BuCheFang.DataBean> provideBuCheFang() {
        List<BuCheFang.DataBean> list = new ArrayList<>();
//        list.add(new BuCheFang.DataBean());
//        list.add(new BuCheFang.DataBean());
//        list.add(new BuCheFang.DataBean());
        return list;
    }

    @Provides
    @ActivityScope
    public TimeCheBuFangAdapter provideTimeCheBuFangAdapter(List<BuCheFang.DataBean> list) {
        return new TimeCheBuFangAdapter(list);
    }

}