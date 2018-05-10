package com.yxld.yxchuangxin.ui.activity.goods.contract;

import com.yxld.yxchuangxin.base.BaseEntity;
import com.yxld.yxchuangxin.entity.AddressEntity;
import com.yxld.yxchuangxin.entity.CxwyMallAdd;
import com.yxld.yxchuangxin.ui.activity.base.BasePresenter;
import com.yxld.yxchuangxin.ui.activity.base.BaseView;

import java.util.List;

/**
 * @author hu
 * @Package The contract for AddressManageActivity
 * @Description: $description
 * @date 2017/06/22 17:20:34
 */
public interface AddressManageContract {
    interface View extends BaseView<AddressManageContractPresenter> {
        /**
         *
         */
        void showProgressDialog();

        /**
         *
         */
        void closeProgressDialog();

        void onAddressDataBacked(CxwyMallAdd addresses);

        void onAddressChangedSucceed(BaseEntity entity, int status,int position);

        void onDeleteAddressSucceed(BaseEntity entity,int position);

        void onLoadDataFailed();
    }

    interface AddressManageContractPresenter extends BasePresenter {
//        /**
//         *
//         */
//        void getBusinessInfo(Map map);

        void getAddressDataFromServer();

        void updateAddress(CxwyMallAdd address,int status,int position);

        void deleteAddress(String addressId,int pos);

    }
}