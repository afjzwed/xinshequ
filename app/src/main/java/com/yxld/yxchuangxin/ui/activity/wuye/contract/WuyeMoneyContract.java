package com.yxld.yxchuangxin.ui.activity.wuye.contract;

import com.yxld.yxchuangxin.entity.WyFwApp;
import com.yxld.yxchuangxin.ui.activity.base.BasePresenter;
import com.yxld.yxchuangxin.ui.activity.base.BaseView;

import java.util.Map;

/**
 * @author hu
 * @Package The contract for WuyeMoneyActivity
 * @Description: $description
 * @date 2017/06/06
 */
public interface WuyeMoneyContract {
    interface View extends BaseView<WuyeMoneyContractPresenter> {
        /**
         *
         */
        void showProgressDialog();

        /**
         *
         */
        void closeProgressDialog();

        /**
         * 获取房屋信息之后，显示信息
         */
        void showInfo(WyFwApp wyFwApp);

        /**
         * 支付成功
         */
        void onPaySuccess();

        void yinlianPay(int jfRecId);
    }

    interface WuyeMoneyContractPresenter extends BasePresenter {
        /**
         * 获取房屋信息
         * @param map
         */
        void getHouse(Map map);
        /**
         * 初始化物业费支付
         * @param map
         */
        void getwuyePay(Map map);

        void setOtherParam(String bianhao, double allMoney);

        void mingxiOnclick(int position);

        float caculateZhiNaJin(int needJiao, int arrMonth, float rate);
    }
}