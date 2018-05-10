package com.yxld.yxchuangxin.ui.activity.camera.contract;

import com.yxld.yxchuangxin.entity.PaianYezhuJiashu;
import com.yxld.yxchuangxin.ui.activity.base.BasePresenter;
import com.yxld.yxchuangxin.ui.activity.base.BaseView;

import java.util.ArrayList;
import java.util.Map;

/**
 * @author hzp
 * @Package The contract for InformPersonActivity
 * @Description: $description
 * @date 2017/09/19 11:17:32
 */
public interface InformPersonContract {
    interface View extends BaseView<InformPersonContractPresenter> {
        /**
         *
         */
        void showProgressDialog();

        /**
         *
         */
        void closeProgressDialog();

        void setPerson(PaianYezhuJiashu person);

        void setYijiaJiashu(ArrayList<String> yijiaJiashu);
    }

    interface InformPersonContractPresenter extends BasePresenter {
        void getInformPerson(Map map);

        void findYezhuJiashu(Map map, String mac);

        void saveNumber(Map map);
    }
}