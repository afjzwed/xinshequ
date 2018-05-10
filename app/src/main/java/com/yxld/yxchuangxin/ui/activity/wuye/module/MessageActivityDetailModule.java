package com.yxld.yxchuangxin.ui.activity.wuye.module;

import com.yxld.yxchuangxin.data.api.HttpAPIWrapper;
import com.yxld.yxchuangxin.ui.activity.base.ActivityScope;
import com.yxld.yxchuangxin.ui.activity.wuye.MessageActivityDetailActivity;
import com.yxld.yxchuangxin.ui.activity.wuye.contract.MessageActivityDetailContract;
import com.yxld.yxchuangxin.ui.activity.wuye.presenter.MessageActivityDetailPresenter;

import dagger.Module;
import dagger.Provides;

/**
 * @author hu
 * @Package com.yxld.yxchuangxin.ui.activity.wuye
 * @Description: The moduele of MessageActivityDetailActivity, provide field for MessageActivityDetailActivity
 * @date 2017/06/26 19:57:52
 */
@Module
public class MessageActivityDetailModule {
    private final MessageActivityDetailContract.View mView;


    public MessageActivityDetailModule(MessageActivityDetailContract.View view) {
        this.mView = view;
    }

    @Provides
    @ActivityScope
    public MessageActivityDetailPresenter provideMessageActivityDetailPresenter(HttpAPIWrapper httpAPIWrapper, MessageActivityDetailActivity mActivity) {
        return new MessageActivityDetailPresenter(httpAPIWrapper, mView, mActivity);
    }

    @Provides
    @ActivityScope
    public MessageActivityDetailActivity provideMessageActivityDetailActivity() {
        return (MessageActivityDetailActivity) mView;
    }
}