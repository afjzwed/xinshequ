package com.yxld.yxchuangxin.ui.activity.common.contract;

import com.yxld.yxchuangxin.entity.CxwyCommon;
import com.yxld.yxchuangxin.ui.activity.base.BasePresenter;
import com.yxld.yxchuangxin.ui.activity.base.BaseView;

import java.util.List;
import java.util.Map;

/**
 * @author hu
 * @Package The contract for AisleActivity
 * @Description: $description
 * @date 2017/06/08
 */
public interface AisleContract {
    interface View extends BaseView<AisleContractPresenter> {
        void setAdapter(List<CxwyCommon.DataBean.CvoListBean> data);
    }

    interface AisleContractPresenter extends BasePresenter {
        void getCommonToken(Map map);
        void getCommon(Map map);
        void aisleAdapterOnItemClick(int position);
    }
}