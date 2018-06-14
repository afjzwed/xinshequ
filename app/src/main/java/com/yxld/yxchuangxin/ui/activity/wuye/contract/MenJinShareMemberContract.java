package com.yxld.yxchuangxin.ui.activity.wuye.contract;

import com.yxld.yxchuangxin.base.BaseEntity;
import com.yxld.yxchuangxin.entity.AppYezhuFangwu;
import com.yxld.yxchuangxin.ui.activity.base.BasePresenter;
import com.yxld.yxchuangxin.ui.activity.base.BaseView;

import java.util.List;
import java.util.Map;

/**
 * @author xlei
 * @Package The contract for MenJinShareMemberActivity
 * @Description: $description
 * @date 2018/06/11 14:53:04
 */
public interface MenJinShareMemberContract {
    interface View extends BaseView<MenJinShareMemberContractPresenter> {
        /**
         *
         */
        void showProgressDialog();

        /**
         *
         */
        void closeProgressDialog();

        /**
         * 设置入住成员
         *
         * @param data
         */
        void setMember(List<AppYezhuFangwu> data);

        void saveSuccess(BaseEntity baseEntity);

        /**
         * 获取已设置的成员
         */
        void getMembered();
    }

    interface MenJinShareMemberContractPresenter extends BasePresenter {
        //        /**
//         *
//         */
//        void getBusinessInfo(Map map);
        void getAllLiveMember(Map map);

        void getDoorShareMember(Map map);
        void saveDoorShareMember(Map map);
    }
}