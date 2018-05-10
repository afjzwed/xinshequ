package com.yxld.yxchuangxin.ui.activity.goods.contract;

import com.yxld.yxchuangxin.entity.CxwyDianziquan;
import com.yxld.yxchuangxin.ui.activity.base.BasePresenter;
import com.yxld.yxchuangxin.ui.activity.base.BaseView;
/**
 * @author hu
 * @Package The contract for TicketActivity
 * @Description: $description
 * @date 2017/06/22 10:47:40
 */
public interface TicketContract {
    interface View extends BaseView<TicketContractPresenter> {
        /**
         *
         */
        void showProgressDialog();

        /**
         *
         */
        void closeProgressDialog();

        void onLoadDianZiQuanListSucceed(CxwyDianziquan dianZiQuanList);
        void onLoadDianZiQuanListFailed();
    }

    interface TicketContractPresenter extends BasePresenter {
//        /**
//         *
//         */
//        void getBusinessInfo(Map map);

        void loadDianZiQuanListFromServer(int page,int rows);
    }
}