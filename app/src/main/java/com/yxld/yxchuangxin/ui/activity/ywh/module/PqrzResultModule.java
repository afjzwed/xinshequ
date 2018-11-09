package com.yxld.yxchuangxin.ui.activity.ywh.module;

import com.yxld.yxchuangxin.data.api.HttpAPIWrapper;
import com.yxld.yxchuangxin.ui.activity.base.ActivityScope;
import com.yxld.yxchuangxin.ui.activity.ywh.PqrzResultActivity;
import com.yxld.yxchuangxin.ui.activity.ywh.contract.PqrzResultContract;
import com.yxld.yxchuangxin.ui.activity.ywh.presenter.PqrzResultPresenter;

import dagger.Module;
import dagger.Provides;

/**
 * @author xlei
 * @Package com.yxld.yxchuangxin.ui.activity.ywh
 * @Description: The moduele of PqrzResultActivity, provide field for PqrzResultActivity
 * @date 2018/11/09 14:52:55
 */
@Module
public class PqrzResultModule {
    private final PqrzResultContract.View mView;


    public PqrzResultModule(PqrzResultContract.View view) {
        this.mView = view;
    }

    @Provides
    @ActivityScope
    public PqrzResultPresenter providePqrzResultPresenter(HttpAPIWrapper httpAPIWrapper, PqrzResultActivity mActivity) {
        return new PqrzResultPresenter(httpAPIWrapper, mView, mActivity);
    }

    @Provides
    @ActivityScope
    public PqrzResultActivity providePqrzResultActivity() {
        return (PqrzResultActivity) mView;
    }
}