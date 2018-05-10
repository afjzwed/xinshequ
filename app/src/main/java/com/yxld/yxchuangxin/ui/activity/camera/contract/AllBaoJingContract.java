package com.yxld.yxchuangxin.ui.activity.camera.contract;

import com.yxld.yxchuangxin.entity.BaoJingEntity;
import com.yxld.yxchuangxin.ui.activity.base.BasePresenter;
import com.yxld.yxchuangxin.ui.activity.base.BaseView;

import java.util.Map;

/**
 * @author hzp
 * @Package The contract for AllBaoJingActivity
 * @Description: $description
 * @date 2017/09/07 01:26:53
 */
public interface AllBaoJingContract {
    interface View extends BaseView<AllBaoJingContractPresenter> {
        /**
         *
         */
        void showProgressDialog();

        /**
         *
         */
        void closeProgressDialog();

        void onBaoJingDataBack();

        void setAdapter(BaoJingEntity baoJingEntity);
    }

    interface AllBaoJingContractPresenter extends BasePresenter {
//        /**
//         *
//         */
//        void getBusinessInfo(Map map);

        void getBaoJingList(Map map);
    }
}