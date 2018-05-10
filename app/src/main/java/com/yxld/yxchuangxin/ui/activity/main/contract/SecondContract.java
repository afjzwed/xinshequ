package com.yxld.yxchuangxin.ui.activity.main.contract;


import com.yxld.yxchuangxin.entity.ActivityOrder;
import com.yxld.yxchuangxin.ui.activity.base.BasePresenter;
import com.yxld.yxchuangxin.ui.activity.base.BaseView;

import java.util.LinkedHashMap;

/**
 * Created by hu on 2017/5/16.
 */

public interface SecondContract {
    interface View extends BaseView<SecondPresenter> {
        void setData(ActivityOrder data);
        void setError();
    }

    interface SecondPresenter extends BasePresenter {
        void getList(LinkedHashMap<String, String> data);
    }
}
