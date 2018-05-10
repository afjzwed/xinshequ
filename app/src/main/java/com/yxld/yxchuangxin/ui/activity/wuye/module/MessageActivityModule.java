package com.yxld.yxchuangxin.ui.activity.wuye.module;

import com.yxld.yxchuangxin.data.api.HttpAPIWrapper;
import com.yxld.yxchuangxin.ui.activity.base.ActivityScope;
import com.yxld.yxchuangxin.ui.activity.wuye.MessageActivityActivity;
import com.yxld.yxchuangxin.ui.activity.wuye.contract.MessageActivityContract;
import com.yxld.yxchuangxin.ui.activity.wuye.presenter.MessageActivityPresenter;

import dagger.Module;
import dagger.Provides;

/**
 * @author hu
 * @Package com.yxld.yxchuangxin.ui.activity.wuye
 * @Description: The moduele of MessageActivityActivity, provide field for MessageActivityActivity
 * @date 2017/06/14
 */
@Module
public class MessageActivityModule {
    private final MessageActivityContract.View mView;


    public MessageActivityModule(MessageActivityContract.View view) {
        this.mView = view;
    }

    @Provides
    @ActivityScope
    public MessageActivityPresenter provideMessageActivityPresenter(HttpAPIWrapper httpAPIWrapper) {
        return new MessageActivityPresenter(httpAPIWrapper, mView);
    }

    @Provides
    @ActivityScope
    public MessageActivityActivity provideMessageActivityActivity() {
        return (MessageActivityActivity) mView;
    }
}