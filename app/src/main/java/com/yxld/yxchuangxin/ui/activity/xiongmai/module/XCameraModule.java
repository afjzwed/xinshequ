package com.yxld.yxchuangxin.ui.activity.xiongmai.module;

import com.yxld.yxchuangxin.data.api.HttpAPIWrapper;
import com.yxld.yxchuangxin.ui.activity.base.ActivityScope;
import com.yxld.yxchuangxin.ui.activity.xiongmai.XCameraActivity;
import com.yxld.yxchuangxin.ui.activity.xiongmai.contract.XCameraContract;
import com.yxld.yxchuangxin.ui.activity.xiongmai.presenter.XCameraPresenter;

import dagger.Module;
import dagger.Provides;

/**
 * @author hu
 * @Package com.yxld.yxchuangxin.ui.activity.xiongmai
 * @Description: The moduele of XCameraActivity, provide field for XCameraActivity
 * @date 2017/07/17 16:30:10
 */
@Module
public class XCameraModule {
    private final XCameraContract.View mView;


    public XCameraModule(XCameraContract.View view) {
        this.mView = view;
    }

    @Provides
    @ActivityScope
    public XCameraPresenter provideXCameraPresenter(HttpAPIWrapper httpAPIWrapper, XCameraActivity mActivity) {
        return new XCameraPresenter(httpAPIWrapper, mView, mActivity);
    }

    @Provides
    @ActivityScope
    public XCameraActivity provideXCameraActivity() {
        return (XCameraActivity) mView;
    }
}