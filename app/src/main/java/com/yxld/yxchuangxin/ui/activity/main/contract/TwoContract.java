package com.yxld.yxchuangxin.ui.activity.main.contract;

import com.yxld.yxchuangxin.ui.activity.base.BasePresenter;
import com.yxld.yxchuangxin.ui.activity.base.BaseView;

/**
 * @author hu
 * @Package com.example.hu.myapplication.ui.activity.main
 * @Description: $description
 * @date 2017/05/18
 */
public interface TwoContract {
    interface View extends BaseView<TwoContractPresenter> {
    }

    interface TwoContractPresenter extends BasePresenter {
    }
}