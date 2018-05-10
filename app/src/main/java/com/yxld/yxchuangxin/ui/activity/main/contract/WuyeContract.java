package com.yxld.yxchuangxin.ui.activity.main.contract;

import com.yxld.yxchuangxin.entity.adapter.Wuye;
import com.yxld.yxchuangxin.ui.activity.base.BasePresenter;
import com.yxld.yxchuangxin.ui.activity.base.BaseView;

import java.util.List;

/**
 * @author hu
 * @Package The contract for WuyeActivity
 * @Description: $description
 * @date 2017/06/05
 */
public interface WuyeContract {
    interface View extends BaseView<WuyeContractPresenter> {
        void setAdapter(List<Wuye.DataBean> dataBean);
    }

    interface WuyeContractPresenter extends BasePresenter {
        void setAdapter();
    }
}