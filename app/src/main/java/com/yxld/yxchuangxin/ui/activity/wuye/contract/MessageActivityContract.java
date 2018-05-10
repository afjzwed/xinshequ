package com.yxld.yxchuangxin.ui.activity.wuye.contract;

import com.yxld.yxchuangxin.ui.activity.base.BasePresenter;
import com.yxld.yxchuangxin.ui.activity.base.BaseView;
/**
 * @author hu
 * @Package The contract for MessageActivityActivity
 * @Description: $description
 * @date 2017/06/14
 */
public interface MessageActivityContract {
    interface View extends BaseView<MessageActivityContractPresenter> {
    }

    interface MessageActivityContractPresenter extends BasePresenter {
    }
}