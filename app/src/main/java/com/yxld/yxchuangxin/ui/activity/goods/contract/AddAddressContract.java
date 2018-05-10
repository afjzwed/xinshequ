package com.yxld.yxchuangxin.ui.activity.goods.contract;

import com.yxld.yxchuangxin.base.BaseEntity;
import com.yxld.yxchuangxin.ui.activity.base.BasePresenter;
import com.yxld.yxchuangxin.ui.activity.base.BaseView;

import java.util.Map;

/**
 * @author hu
 * @Package The contract for AddAddressActivity
 * @Description: $description
 * @date 2017/06/22 17:36:39
 */
public interface AddAddressContract {
    interface View extends BaseView<AddAddressContractPresenter> {
        /**
         *
         */
        void showProgressDialog();

        /**
         *
         */
        void closeProgressDialog();

        void onAddAddressSucceed(BaseEntity entity);
        void onAddAddressFailed();
    }

    interface AddAddressContractPresenter extends BasePresenter {
//        /**
//         *
//         */
//        void getBusinessInfo(Map map);

        void addAddress(Map<String,String> params);


        void updateAddress(Map<String,String> params);
    }
}