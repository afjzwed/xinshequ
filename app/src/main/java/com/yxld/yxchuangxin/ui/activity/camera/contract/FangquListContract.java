package com.yxld.yxchuangxin.ui.activity.camera.contract;

import com.yxld.yxchuangxin.entity.FangquEntity;
import com.yxld.yxchuangxin.ui.activity.base.BasePresenter;
import com.yxld.yxchuangxin.ui.activity.base.BaseView;

import java.util.Map;

/**
 * @author hzp
 * @Package The contract for FangquListActivity
 * @Description: $description
 * @date 2017/09/07 01:27:17
 */
public interface FangquListContract {
    interface View extends BaseView<FangquListContractPresenter> {
        /**
         *
         */
        void showProgressDialog();

        /**
         *
         */
        void closeProgressDialog();

        void setAdapter(FangquEntity fangquEntity);
    }

    interface FangquListContractPresenter extends BasePresenter {
//        /**
//         *
//         */
//        void getBusinessInfo(Map map);

        void getFangquList(Map map);
    }
}