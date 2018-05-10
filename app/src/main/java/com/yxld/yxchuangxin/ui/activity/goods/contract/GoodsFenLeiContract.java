package com.yxld.yxchuangxin.ui.activity.goods.contract;

import com.yxld.yxchuangxin.entity.MallClassify;
import com.yxld.yxchuangxin.ui.activity.base.BasePresenter;
import com.yxld.yxchuangxin.ui.activity.base.BaseView;

import java.util.Map;

/**
 * @author xlei
 * @Package The contract for GoodsFenLeiActivity
 * @Description: $description
 * @date 2017/10/19 08:58:40
 */
public interface GoodsFenLeiContract {
    interface View extends BaseView<GoodsFenLeiContractPresenter> {
        /**
         *
         */
        void showProgressDialog();

        /**
         *
         */
        void closeProgressDialog();
        void loadFenLei2Success(MallClassify mallClassify);
    }

    interface GoodsFenLeiContractPresenter extends BasePresenter {
//        /**
//         *
//         */
//        void getBusinessInfo(Map map);

        void getFenLei2(Map map);

    }
}