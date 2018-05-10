package com.yxld.yxchuangxin.ui.activity.rim.contract;

import com.yxld.yxchuangxin.ui.activity.base.BasePresenter;
import com.yxld.yxchuangxin.ui.activity.base.BaseView;
/**
 * @author wwx
 * @Package The contract for ItemServiceFragment
 * @Description: $description
 * @date 2017/06/16
 */
public interface ItemServiceContract {
    interface View extends BaseView<ItemServiceContractPresenter> {
    }

    interface ItemServiceContractPresenter extends BasePresenter {
    }
}