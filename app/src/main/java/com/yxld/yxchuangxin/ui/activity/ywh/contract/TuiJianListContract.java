package com.yxld.yxchuangxin.ui.activity.ywh.contract;

import com.yxld.yxchuangxin.base.BaseEntity;
import com.yxld.yxchuangxin.entity.YwhTj;
import com.yxld.yxchuangxin.ui.activity.base.BasePresenter;
import com.yxld.yxchuangxin.ui.activity.base.BaseView;

import java.util.Map;

/**
 * @author xlei
 * @Package The contract for TuiJianListActivity
 * @Description: $description
 * @date 2018/11/08 10:54:14
 */
public interface TuiJianListContract {
    interface View extends BaseView<TuiJianListContractPresenter> {
        /**
         *
         */
        void showProgressDialog();

        /**
         *
         */
        void closeProgressDialog();

        void getTjcbzSuccess(YwhTj baseEntity);

        void commitLySuccess(BaseEntity baseEntity);
    }

    interface TuiJianListContractPresenter extends BasePresenter {
        //        /**
//         *
//         */
//        void getBusinessInfo(Map map);
        void getTjcbz(Map map);

        void comitLy(Map map);
    }
}