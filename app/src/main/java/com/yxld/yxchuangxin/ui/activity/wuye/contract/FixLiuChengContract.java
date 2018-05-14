package com.yxld.yxchuangxin.ui.activity.wuye.contract;

import com.yxld.yxchuangxin.entity.LiuCheng;
import com.yxld.yxchuangxin.ui.activity.base.BasePresenter;
import com.yxld.yxchuangxin.ui.activity.base.BaseView;

import java.util.Map;

/**
 * @author xlei
 * @Package The contract for FixLiuChengActivity
 * @Description: $description
 * @date 2018/05/14 10:23:41
 */
public interface FixLiuChengContract {
    interface View extends BaseView<FixLiuChengContractPresenter> {
        /**
         *
         */
        void showProgressDialog();

        /**
         *
         */
        void closeProgressDialog();

        void setData(LiuCheng liuCheng);
    }

    interface FixLiuChengContractPresenter extends BasePresenter {
        //        /**
//         *
//         */
//        void getBusinessInfo(Map map);
        void getData(Map<String, String> map);
    }
}