package com.yxld.yxchuangxin.ui.activity.mine.module;

import com.socks.library.KLog;
import com.yxld.yxchuangxin.application.AppConfig;
import com.yxld.yxchuangxin.data.api.HttpAPIWrapper;
import com.yxld.yxchuangxin.db.green.UserInfo;
import com.yxld.yxchuangxin.ui.activity.base.ActivityScope;
import com.yxld.yxchuangxin.ui.activity.mine.MultiAccountActivity;
import com.yxld.yxchuangxin.ui.activity.mine.contract.MultiAccountContract;
import com.yxld.yxchuangxin.ui.activity.mine.presenter.MultiAccountPresenter;
import com.yxld.yxchuangxin.ui.adapter.mine.MultiAccountAdapter;

import java.util.List;

import dagger.Module;
import dagger.Provides;

/**
 * @author hzp
 * @Package com.yxld.yxchuangxin.ui.activity.mine
 * @Description: The moduele of MultiAccountActivity, provide field for MultiAccountActivity
 * @date 2017/10/11 09:31:27
 */
@Module
public class MultiAccountModule {
    private final MultiAccountContract.View mView;


    public MultiAccountModule(MultiAccountContract.View view) {
        this.mView = view;
    }

    @Provides
    @ActivityScope
    public MultiAccountPresenter provideMultiAccountPresenter(HttpAPIWrapper httpAPIWrapper, MultiAccountActivity mActivity) {
        return new MultiAccountPresenter(httpAPIWrapper, mView, mActivity);
    }

    @Provides
    @ActivityScope
    public MultiAccountActivity provideMultiAccountActivity() {
        return (MultiAccountActivity) mView;
    }
    @Provides
    @ActivityScope
    public MultiAccountAdapter provideMultiAccountAdapter() {
        List<UserInfo> list = AppConfig.getGreenDaoSession().getUserInfoDao().loadAll();
        KLog.i("保存的账户个数:" + list.size());
        return new MultiAccountAdapter(list);
    }
}