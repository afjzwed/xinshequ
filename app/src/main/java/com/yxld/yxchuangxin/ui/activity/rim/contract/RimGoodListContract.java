package com.yxld.yxchuangxin.ui.activity.rim.contract;

import com.yxld.yxchuangxin.entity.CxwyBusinessInfo;
import com.yxld.yxchuangxin.entity.CxwyClassifyInfo;
import com.yxld.yxchuangxin.entity.CxwyProductInfo;
import com.yxld.yxchuangxin.ui.activity.base.BasePresenter;
import com.yxld.yxchuangxin.ui.activity.base.BaseView;

import java.util.Map;

/**
 * @author xlei
 * @Package The contract for RimGoodListFragment
 * @Description: $description
 * @date 2017/12/13 10:44:31
 */
public interface RimGoodListContract {
    interface View extends BaseView<RimGoodListContractPresenter> {
        /**
         *
         */
        void showProgressDialog();

        /**
         *
         */
        void closeProgressDialog();

        /**
         * 获取分类信息的返回所需要调用的方法
         */
        void onGetClassify(CxwyClassifyInfo info);

        /**
         * 获取最终商品信息的返回
         * @param info
         */
        void ongetProductinfo(CxwyProductInfo info);
    }

    interface RimGoodListContractPresenter extends BasePresenter {

        void getClassifyinfo(Map map);
        /**
         * 获取最终商品信息
         * @param map
         */
        void getProductinfo(Map map);
    }
}