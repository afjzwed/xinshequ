package com.yxld.yxchuangxin.ui.activity.rim.contract;

import com.yxld.yxchuangxin.entity.SJComplain;
import com.yxld.yxchuangxin.ui.activity.base.BasePresenter;
import com.yxld.yxchuangxin.ui.activity.base.BaseView;
import com.yxld.yxchuangxin.ui.adapter.rim.RimComplainAdapter;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author wwx
 * @Package The contract for RimComplainListActivity
 * @Description: $description
 * @date 2017/06/16
 */
public interface RimComplainListContract {
    interface View extends BaseView<RimComplainListContractPresenter> {
        void setAdapter(RimComplainAdapter adapter);
        void startLoadMore();
        void endLoadMore();
        void showLoading();
        void hideLoading();
        void initPaginate();
        void showProgressDialog();
        void closeProgressDialog();

        //获得投诉详情完成
        void getRimComplainDetailFinish(boolean isFinish, SJComplain data);
    }

    interface RimComplainListContractPresenter extends BasePresenter {
        void getComplanData(Map map,boolean isaddload);

        /**
         * 获取订单详情接口
         */
        void getRimComplainDetail(LinkedHashMap<String, String> data);
    }
}