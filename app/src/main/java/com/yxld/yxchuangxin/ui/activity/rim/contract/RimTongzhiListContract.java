package com.yxld.yxchuangxin.ui.activity.rim.contract;

import com.yxld.yxchuangxin.entity.SJOrder;
import com.yxld.yxchuangxin.ui.activity.base.BasePresenter;
import com.yxld.yxchuangxin.ui.activity.base.BaseView;

import java.util.LinkedHashMap;

/**
 * @author wwx
 * @Package The contract for RimTongzhiListActivity
 * @Description: $description
 * @date 2017/06/17
 */
public interface RimTongzhiListContract {
    interface View extends BaseView<RimTongzhiListContractPresenter> {
        void setData(SJOrder data);
        void setError();
        void setEmptyData();
    }

    interface RimTongzhiListContractPresenter extends BasePresenter {
        void getRimTongzhiList(LinkedHashMap<String, String> data);
    }
}