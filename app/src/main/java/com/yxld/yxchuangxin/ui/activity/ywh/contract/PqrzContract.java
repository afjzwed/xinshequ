package com.yxld.yxchuangxin.ui.activity.ywh.contract;

import com.yxld.yxchuangxin.base.BaseEntity;
import com.yxld.yxchuangxin.entity.YwhHouse;
import com.yxld.yxchuangxin.ui.activity.base.BasePresenter;
import com.yxld.yxchuangxin.ui.activity.base.BaseView;

import java.util.Map;

/**
 * @author xlei
 * @Package The contract for PqrzActivity
 * @Description: $description
 * @date 2018/11/09 08:55:03
 */
public interface PqrzContract {
    interface View extends BaseView<PqrzContractPresenter> {
        /**
         *
         */
        void showProgressDialog();

        /**
         *
         */
        void closeProgressDialog();

        void uploadimg(String token);

        void getHoustSuccess(YwhHouse baseEntity);

        void commitSuccess(BaseEntity baseEntity);
    }

    interface PqrzContractPresenter extends BasePresenter {
//        /**
//         *
//         */
        void getQnToken();

        void getHouse();
        void commit(Map map);
    }
}