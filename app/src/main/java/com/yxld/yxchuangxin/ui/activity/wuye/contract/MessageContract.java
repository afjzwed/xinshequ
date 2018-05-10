package com.yxld.yxchuangxin.ui.activity.wuye.contract;

import com.yxld.yxchuangxin.entity.CxwyMessage;
import com.yxld.yxchuangxin.ui.activity.base.BasePresenter;
import com.yxld.yxchuangxin.ui.activity.base.BaseView;

import java.util.Map;

/**
 * @author hu
 * @Package The contract for MessageFragment
 * @Description: $description
 * @date 2017/06/14
 */
public interface MessageContract {
    interface View extends BaseView<MessageContractPresenter> {
        /**
         * 设置通知列表
         */
        void setMessage(CxwyMessage message);
    }

    interface MessageContractPresenter extends BasePresenter {
        /**
         * 获取通知
         */
        void getMessage(Map map);
    }
}