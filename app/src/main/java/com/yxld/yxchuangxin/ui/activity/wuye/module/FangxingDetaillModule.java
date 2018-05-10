package com.yxld.yxchuangxin.ui.activity.wuye.module;

import android.support.v7.widget.LinearLayoutManager;

import com.yxld.yxchuangxin.data.api.HttpAPIWrapper;
import com.yxld.yxchuangxin.entity.AccreditDetail;
import com.yxld.yxchuangxin.ui.activity.base.ActivityScope;
import com.yxld.yxchuangxin.ui.activity.wuye.FangxingDetaillActivity;
import com.yxld.yxchuangxin.ui.activity.wuye.contract.FangxingDetaillContract;
import com.yxld.yxchuangxin.ui.activity.wuye.presenter.FangxingDetaillPresenter;
import com.yxld.yxchuangxin.ui.adapter.wuye.AutoLinearLayoutManager;
import com.yxld.yxchuangxin.ui.adapter.wuye.FangxingDetailAdapter;

import java.util.ArrayList;
import java.util.List;

import dagger.Module;
import dagger.Provides;

/**
 * @author hu
 * @Package com.yxld.yxchuangxin.ui.activity.wuye
 * @Description: The moduele of FangxingDetaillActivity, provide field for FangxingDetaillActivity
 * @date 2017/06/14
 */
@Module
public class FangxingDetaillModule {
    private final FangxingDetaillContract.View mView;


    public FangxingDetaillModule(FangxingDetaillContract.View view) {
        this.mView = view;
    }

    @Provides
    @ActivityScope
    public FangxingDetaillPresenter provideFangxingDetaillPresenter(HttpAPIWrapper httpAPIWrapper, FangxingDetaillActivity activity) {
        return new FangxingDetaillPresenter(httpAPIWrapper, mView, activity);
    }

    @Provides
    @ActivityScope
    public FangxingDetaillActivity provideFangxingDetaillActivity() {
        return (FangxingDetaillActivity) mView;
    }

    @Provides
    @ActivityScope
    public List<AccreditDetail.DataBean> provideList() {
        List<AccreditDetail.DataBean> list = new ArrayList<>();
        return list;
    }


    @Provides
    @ActivityScope
    public FangxingDetailAdapter provideFangxingDetailAdapter(List<AccreditDetail.DataBean> list) {
        return new FangxingDetailAdapter(list);
    }

    @Provides
    @ActivityScope
    public AutoLinearLayoutManager provideAutoLinearLayoutManager(FangxingDetaillActivity activity) {
        return new AutoLinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false);
    }
}