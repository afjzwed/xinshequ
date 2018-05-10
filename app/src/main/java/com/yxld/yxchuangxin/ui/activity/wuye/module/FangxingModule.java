package com.yxld.yxchuangxin.ui.activity.wuye.module;

import com.yxld.yxchuangxin.data.api.HttpAPIWrapper;
import com.yxld.yxchuangxin.entity.Accredit;
import com.yxld.yxchuangxin.ui.activity.base.ActivityScope;
import com.yxld.yxchuangxin.ui.activity.wuye.FangxingActivity;
import com.yxld.yxchuangxin.ui.activity.wuye.contract.FangxingContract;
import com.yxld.yxchuangxin.ui.activity.wuye.presenter.FangxingPresenter;
import com.yxld.yxchuangxin.ui.adapter.wuye.FangxingAdapter;

import java.util.ArrayList;
import java.util.List;

import dagger.Module;
import dagger.Provides;

/**
 * @author hu
 * @Package com.yxld.yxchuangxin.ui.activity.wuye
 * @Description: The moduele of FangxingActivity, provide field for FangxingActivity
 * @date 2017/06/13
 */
@Module
public class FangxingModule {
    private final FangxingContract.View mView;


    public FangxingModule(FangxingContract.View view) {
        this.mView = view;
    }

    @Provides
    @ActivityScope
    public FangxingPresenter provideFangxingPresenter(HttpAPIWrapper httpAPIWrapper, FangxingActivity activity) {
        return new FangxingPresenter(httpAPIWrapper, mView, activity);
    }

    @Provides
    @ActivityScope
    public FangxingActivity provideFangxingActivity() {
        return (FangxingActivity) mView;
    }

    @Provides
    @ActivityScope
    public List<Accredit.DataBean> provideList() {
        List<Accredit.DataBean> list = new ArrayList<>();
        return list;
    }

    @Provides
    @ActivityScope
    public FangxingAdapter provideFangxingAdapter(List<Accredit.DataBean> list) {
        return new FangxingAdapter(list);
    }

}