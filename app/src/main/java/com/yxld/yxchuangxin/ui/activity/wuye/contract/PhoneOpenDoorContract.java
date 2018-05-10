package com.yxld.yxchuangxin.ui.activity.wuye.contract;

import com.yxld.yxchuangxin.ui.activity.base.BasePresenter;
import com.yxld.yxchuangxin.ui.activity.base.BaseView;
/**
 * @author hu
 * @Package The contract for PhoneOpenDoorActivity
 * @Description: $description
 * @date 2017/06/06
 */
public interface PhoneOpenDoorContract {
    interface View extends BaseView<PhoneOpenDoorContractPresenter> {
    }

    interface PhoneOpenDoorContractPresenter extends BasePresenter {
    }
}