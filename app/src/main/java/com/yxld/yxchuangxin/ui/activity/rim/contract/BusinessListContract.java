package com.yxld.yxchuangxin.ui.activity.rim.contract;

import com.yxld.yxchuangxin.entity.CxwyBusiness;
import com.yxld.yxchuangxin.ui.activity.base.BasePresenter;
import com.yxld.yxchuangxin.ui.activity.base.BaseView;

import java.util.List;
import java.util.Map;

/**
 * @author hu
 * @Package The contract for BusinessListActivity
 * @Description: $description
 * @date 2017/06/16
 */
public interface BusinessListContract {
    interface View extends BaseView<BusinessListContractPresenter> {
        void setList(List<CxwyBusiness.DataBean> list);
    }

    interface BusinessListContractPresenter extends BasePresenter {
        void getBusinessList(Map map);
    }
}