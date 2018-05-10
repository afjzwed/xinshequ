package com.yxld.yxchuangxin.ui.activity.goods.module;

import com.yxld.yxchuangxin.data.api.HttpAPIWrapper;
import com.yxld.yxchuangxin.entity.MyAllComment;
import com.yxld.yxchuangxin.ui.activity.base.ActivityScope;
import com.yxld.yxchuangxin.ui.activity.goods.MyEvaluateActivity;
import com.yxld.yxchuangxin.ui.activity.goods.contract.MyEvaluateContract;
import com.yxld.yxchuangxin.ui.activity.goods.presenter.MyEvaluatePresenter;
import com.yxld.yxchuangxin.ui.adapter.MyEvaluateAdapter;

import java.util.ArrayList;

import dagger.Module;
import dagger.Provides;

/**
 * @author hzp
 * @Package com.yxld.yxchuangxin.ui.activity.goods
 * @Description: The moduele of MyEvaluateActivity, provide field for MyEvaluateActivity
 * @date 2017/10/23 13:55:27
 */
@Module
public class MyEvaluateModule {
    private final MyEvaluateContract.View mView;


    public MyEvaluateModule(MyEvaluateContract.View view) {
        this.mView = view;
    }

    @Provides
    @ActivityScope
    public MyEvaluatePresenter provideMyEvaluatePresenter(HttpAPIWrapper httpAPIWrapper, MyEvaluateActivity mActivity) {
        return new MyEvaluatePresenter(httpAPIWrapper, mView, mActivity);
    }

    @Provides
    @ActivityScope
    public MyEvaluateActivity provideMyEvaluateActivity() {
        return (MyEvaluateActivity) mView;
    }
    @Provides
    @ActivityScope
    public MyEvaluateAdapter provideMyEvaluateAdapter() {
        ArrayList<MyAllComment.DataBean> list = new ArrayList<>();
        return new MyEvaluateAdapter(list);
    }
}