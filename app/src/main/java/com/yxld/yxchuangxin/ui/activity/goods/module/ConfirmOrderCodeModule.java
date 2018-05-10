package com.yxld.yxchuangxin.ui.activity.goods.module;

import com.yxld.yxchuangxin.data.api.HttpAPIWrapper;
import com.yxld.yxchuangxin.ui.activity.base.ActivityScope;
import com.yxld.yxchuangxin.ui.activity.goods.ConfirmOrderCodeActivity;
import com.yxld.yxchuangxin.ui.activity.goods.contract.ConfirmOrderCodeContract;
import com.yxld.yxchuangxin.ui.activity.goods.presenter.ConfirmOrderCodePresenter;

import dagger.Module;
import dagger.Provides;

/**
 * @author xlei
 * @Package com.yxld.yxchuangxin.ui.activity.goods
 * @Description: The moduele of ConfirmOrderCodeActivity, provide field for ConfirmOrderCodeActivity
 * @date 2018/03/21 08:46:31
 */
@Module
public class ConfirmOrderCodeModule {
    private final ConfirmOrderCodeContract.View mView;


    public ConfirmOrderCodeModule(ConfirmOrderCodeContract.View view) {
        this.mView = view;
    }

    @Provides
    @ActivityScope
    public ConfirmOrderCodePresenter provideConfirmOrderCodePresenter(HttpAPIWrapper httpAPIWrapper, ConfirmOrderCodeActivity mActivity) {
        return new ConfirmOrderCodePresenter(httpAPIWrapper, mView, mActivity);
    }

    @Provides
    @ActivityScope
    public ConfirmOrderCodeActivity provideConfirmOrderCodeActivity() {
        return (ConfirmOrderCodeActivity) mView;
    }
}