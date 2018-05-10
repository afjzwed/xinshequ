package com.yxld.yxchuangxin.ui.activity.wuye.module;

import com.yxld.yxchuangxin.data.api.HttpAPIWrapper;
import com.yxld.yxchuangxin.entity.RoomRent;
import com.yxld.yxchuangxin.ui.activity.base.ActivityScope;
import com.yxld.yxchuangxin.ui.activity.wuye.RoomRentActivity;
import com.yxld.yxchuangxin.ui.activity.wuye.contract.RoomRentContract;
import com.yxld.yxchuangxin.ui.activity.wuye.presenter.RoomRentPresenter;
import com.yxld.yxchuangxin.ui.adapter.wuye.RoomRentAdapter;

import java.util.ArrayList;
import java.util.List;

import dagger.Module;
import dagger.Provides;

/**
 * @author hu
 * @Package com.yxld.yxchuangxin.ui.activity.wuye
 * @Description: The moduele of RoomRentActivity, provide field for RoomRentActivity
 * @date 2017/06/16
 */
@Module
public class RoomRentModule {
    private final RoomRentContract.View mView;


    public RoomRentModule(RoomRentContract.View view) {
        this.mView = view;
    }

    @Provides
    @ActivityScope
    public RoomRentPresenter provideRoomRentPresenter(HttpAPIWrapper httpAPIWrapper, RoomRentActivity activity) {
        return new RoomRentPresenter(httpAPIWrapper, mView, activity);
    }

    @Provides
    @ActivityScope
    public List<RoomRent.YezhuBean> provideList() {
        List<RoomRent.YezhuBean> list = new ArrayList<>();
        return list;
    }

    @Provides
    @ActivityScope
    public RoomRentAdapter provideRoomRentAdapter(List<RoomRent.YezhuBean> list) {
        return new RoomRentAdapter(list);
    }


    @Provides
    @ActivityScope
    public RoomRentActivity provideRoomRentActivity() {
        return (RoomRentActivity) mView;
    }
}