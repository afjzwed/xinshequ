package com.yxld.yxchuangxin.ui.activity.camera.module;

import com.yxld.yxchuangxin.data.api.HttpAPIWrapper;
import com.yxld.yxchuangxin.entity.BaoJingEntity;
import com.yxld.yxchuangxin.ui.activity.base.ActivityScope;
import com.yxld.yxchuangxin.ui.activity.camera.AllBaoJingActivity;
import com.yxld.yxchuangxin.ui.activity.camera.contract.AllBaoJingContract;
import com.yxld.yxchuangxin.ui.activity.camera.presenter.AllBaoJingPresenter;
import com.yxld.yxchuangxin.ui.adapter.camera.BaoJingAdapter;

import java.util.ArrayList;
import java.util.List;

import dagger.Module;
import dagger.Provides;

/**
 * @author hzp
 * @Package com.yxld.yxchuangxin.ui.activity.camera
 * @Description: The moduele of AllBaoJingActivity, provide field for AllBaoJingActivity
 * @date 2017/09/07 01:26:53
 */
@Module
public class AllBaoJingModule {
    private final AllBaoJingContract.View mView;


    public AllBaoJingModule(AllBaoJingContract.View view) {
        this.mView = view;
    }

    @Provides
    @ActivityScope
    public AllBaoJingPresenter provideAllBaoJingPresenter(HttpAPIWrapper httpAPIWrapper, AllBaoJingActivity mActivity) {
        return new AllBaoJingPresenter(httpAPIWrapper, mView, mActivity);
    }

    @Provides
    @ActivityScope
    public AllBaoJingActivity provideAllBaoJingActivity() {
        return (AllBaoJingActivity) mView;
    }

    @Provides
    @ActivityScope
    public List<BaoJingEntity.DataBean.RowsBean> provideBaoJingEntity() {
        List<BaoJingEntity.DataBean.RowsBean> list = new ArrayList<>();
        return list;
    }

    @Provides
    @ActivityScope
    public BaoJingAdapter provideBaoJingAdapter(List<BaoJingEntity.DataBean.RowsBean> list) {
        return new BaoJingAdapter(list);
    }
}