package com.yxld.yxchuangxin.ui.activity.wuye.module;

import com.yxld.yxchuangxin.data.api.HttpAPIWrapper;
import com.yxld.yxchuangxin.entity.CxwyMessage;
import com.yxld.yxchuangxin.ui.activity.base.ActivityScope;
import com.yxld.yxchuangxin.ui.activity.wuye.MessageFragment;
import com.yxld.yxchuangxin.ui.activity.wuye.contract.MessageContract;
import com.yxld.yxchuangxin.ui.activity.wuye.presenter.MessagePresenter;
import com.yxld.yxchuangxin.ui.adapter.wuye.MessageAdapter;

import java.util.ArrayList;
import java.util.List;

import dagger.Module;
import dagger.Provides;

/**
 * @author hu
 * @Package com.yxld.yxchuangxin.ui.activity.wuye
 * @Description: The moduele of MessageFragment, provide field for MessageFragment
 * @date 2017/06/14
 */
@Module
public class MessageModule {
    private final MessageContract.View mView;


    public MessageModule(MessageContract.View view) {
        this.mView = view;
    }

    @Provides
    @ActivityScope
    public MessagePresenter provideMessagePresenter(HttpAPIWrapper httpAPIWrapper) {
        return new MessagePresenter(httpAPIWrapper, mView);
    }

    @Provides
    @ActivityScope
    public List<CxwyMessage.RowsBean> provideList() {
        List<CxwyMessage.RowsBean> list = new ArrayList<>();
        return list;
    }

    @Provides
    @ActivityScope
    public MessageAdapter provideMessageAdapter(List<CxwyMessage.RowsBean> list) {
        return new MessageAdapter(list);
    }

    @Provides
    @ActivityScope
    public MessageFragment provideMessageFragment() {
        return (MessageFragment) mView;
    }
}