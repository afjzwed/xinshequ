package com.yxld.yxchuangxin.ui.activity.main.contract;


import com.yxld.yxchuangxin.ui.activity.base.BasePresenter;
import com.yxld.yxchuangxin.ui.activity.base.BaseView;

public interface TestContract {
    interface View extends BaseView<TestPresenter> {
        void setText(String text);
        void setButton(String text);
    }

    interface TestPresenter extends BasePresenter {
        void getText();
        void startObservable();
    }
}