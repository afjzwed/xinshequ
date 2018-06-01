package com.yxld.yxchuangxin.ui.activity.wuye.contract;

import android.content.Intent;

import com.yxld.yxchuangxin.base.BaseEntity;
import com.yxld.yxchuangxin.entity.BaseBack2;
import com.yxld.yxchuangxin.entity.DoorInfo;
import com.yxld.yxchuangxin.ui.activity.base.BasePresenter;
import com.yxld.yxchuangxin.ui.activity.base.BaseView;

import java.util.Map;

/**
 * @author xlei
 * @Package The contract for MenJinShareFragment
 * @Description: $description
 * @date 2018/05/26 13:36:35
 */
public interface MenJinShareContract {
    interface View extends BaseView<MenJinShareContractPresenter> {
        /**
         *
         */
        void showProgressDialog();

        /**
         *
         */
        void closeProgressDialog();

        void setOnResult(String name, String number);

        void setDoorMima(BaseBack2 baseEntity);

        void setDoorList(DoorInfo baseEntity);
    }

    interface MenJinShareContractPresenter extends BasePresenter {
        //        /**
//         *
//         */
//        void getBusinessInfo(Map map);
        void onActivityResult(int requestCode, int resultCode, Intent data);

        void getDoorMima(Map<String, String> map);

        void getDoorList();
    }
}