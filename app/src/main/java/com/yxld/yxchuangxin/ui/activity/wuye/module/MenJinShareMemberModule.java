package com.yxld.yxchuangxin.ui.activity.wuye.module;

import com.yxld.yxchuangxin.data.api.HttpAPIWrapper;
import com.yxld.yxchuangxin.entity.AppYezhuFangwu;
import com.yxld.yxchuangxin.ui.activity.base.ActivityScope;
import com.yxld.yxchuangxin.ui.activity.wuye.MenJinShareMemberActivity;
import com.yxld.yxchuangxin.ui.activity.wuye.contract.MenJinShareMemberContract;
import com.yxld.yxchuangxin.ui.activity.wuye.presenter.MenJinShareMemberPresenter;
import com.yxld.yxchuangxin.ui.adapter.wuye.LiveMemberAdapter;
import com.yxld.yxchuangxin.ui.adapter.wuye.MenJinShareAdapter;

import java.util.ArrayList;
import java.util.List;

import dagger.Module;
import dagger.Provides;

/**
 * @author xlei
 * @Package com.yxld.yxchuangxin.ui.activity.wuye
 * @Description: The moduele of MenJinShareMemberActivity, provide field for MenJinShareMemberActivity
 * @date 2018/06/11 14:53:04
 */
@Module
public class MenJinShareMemberModule {
    private final MenJinShareMemberContract.View mView;


    public MenJinShareMemberModule(MenJinShareMemberContract.View view) {
        this.mView = view;
    }

    @Provides
    @ActivityScope
    public MenJinShareMemberPresenter provideMenJinShareMemberPresenter(HttpAPIWrapper httpAPIWrapper, MenJinShareMemberActivity mActivity) {
        return new MenJinShareMemberPresenter(httpAPIWrapper, mView, mActivity);
    }

    @Provides
    @ActivityScope
    public MenJinShareMemberActivity provideMenJinShareMemberActivity() {
        return (MenJinShareMemberActivity) mView;
    }
    @Provides
    @ActivityScope
    public List<AppYezhuFangwu> provideAppYezhuFangwu() {
        return new ArrayList<AppYezhuFangwu>();
    }

    @Provides
    @ActivityScope
    public MenJinShareAdapter provideLiveMemberAdapter(List<AppYezhuFangwu> data) {
        return new MenJinShareAdapter(data);
    }
}