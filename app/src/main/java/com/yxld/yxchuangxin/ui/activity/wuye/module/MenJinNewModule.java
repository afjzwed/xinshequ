package com.yxld.yxchuangxin.ui.activity.wuye.module;

import com.yxld.yxchuangxin.data.api.HttpAPIWrapper;
import com.yxld.yxchuangxin.ui.activity.base.ActivityScope;
import com.yxld.yxchuangxin.ui.activity.wuye.MenJinNewActivity;
import com.yxld.yxchuangxin.ui.activity.wuye.contract.MenJinNewContract;
import com.yxld.yxchuangxin.ui.activity.wuye.presenter.MenJinNewPresenter;

import dagger.Module;
import dagger.Provides;

/**
 * @author xlei
 * @Package com.yxld.yxchuangxin.ui.activity.wuye
 * @Description: The moduele of MenJinNewActivity, provide field for MenJinNewActivity
 * @date 2018/05/26 11:11:04
 */
@Module
public class MenJinNewModule {
    private final MenJinNewContract.View mView;


    public MenJinNewModule(MenJinNewContract.View view) {
        this.mView = view;
    }

    @Provides
    @ActivityScope
    public MenJinNewPresenter provideMenJinNewPresenter(HttpAPIWrapper httpAPIWrapper, MenJinNewActivity mActivity) {
        return new MenJinNewPresenter(httpAPIWrapper, mView, mActivity);
    }

    @Provides
    @ActivityScope
    public MenJinNewActivity provideMenJinNewActivity() {
        return (MenJinNewActivity) mView;
    }
}