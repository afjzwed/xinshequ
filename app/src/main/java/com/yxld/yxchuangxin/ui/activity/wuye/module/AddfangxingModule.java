package com.yxld.yxchuangxin.ui.activity.wuye.module;

import com.yxld.yxchuangxin.data.api.HttpAPIWrapper;
import com.yxld.yxchuangxin.entity.AddFangxing;
import com.yxld.yxchuangxin.ui.activity.base.ActivityScope;
import com.yxld.yxchuangxin.ui.activity.wuye.AddfangxingActivity;
import com.yxld.yxchuangxin.ui.activity.wuye.contract.AddfangxingContract;
import com.yxld.yxchuangxin.ui.activity.wuye.presenter.AddfangxingPresenter;
import com.yxld.yxchuangxin.ui.adapter.wuye.AddFangxingAdapter;

import java.util.ArrayList;
import java.util.List;

import dagger.Module;
import dagger.Provides;

/**
 * @author hu
 * @Package com.yxld.yxchuangxin.ui.activity.wuye
 * @Description: The moduele of AddfangxingActivity, provide field for AddfangxingActivity
 * @date 2017/06/13
 */
@Module
public class AddfangxingModule {
    private final AddfangxingContract.View mView;


    public AddfangxingModule(AddfangxingContract.View view) {
        this.mView = view;
    }

    @Provides
    @ActivityScope
    public AddfangxingPresenter provideAddfangxingPresenter(HttpAPIWrapper httpAPIWrapper, AddfangxingActivity activity) {
        return new AddfangxingPresenter(httpAPIWrapper, mView, activity);
    }

    @Provides
    @ActivityScope
    public List<AddFangxing> provideList() {
        List<AddFangxing> list = new ArrayList<>();

        return list;
    }

    @Provides
    @ActivityScope
    public AddFangxingAdapter provideAddFangxingAdapter(List<AddFangxing> back) {
        return new AddFangxingAdapter(back);
    }


    @Provides
    @ActivityScope
    public AddfangxingActivity provideAddfangxingActivity() {
        return (AddfangxingActivity) mView;
    }
}