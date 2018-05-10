package com.yxld.yxchuangxin.ui.activity.rim.contract;

import com.yxld.yxchuangxin.entity.CxwyClassifyInfo;
import com.yxld.yxchuangxin.entity.CxwyProductInfo;
import com.yxld.yxchuangxin.ui.activity.base.BasePresenter;
import com.yxld.yxchuangxin.ui.activity.base.BaseView;

import java.util.Map;

/**
 * @author hu
 * @Package The contract for GoodListFragment
 * @Description: $description
 * @date 2017/06/17
 */
public interface GoodListContract {
    interface View extends BaseView<GoodListContractPresenter> {
        /**
         * 获取分类信息的返回所需要调用的方法
         */
        void onGetClassify(CxwyClassifyInfo info);

        /**
         * 获取最终商品信息的返回
         * @param info
         */
        void ongetProductinfo(CxwyProductInfo info);

        /**
         *
         */
        void showProgressDialog();

        /**
         *
         */
        void closeProgressDialog();
    }

    interface GoodListContractPresenter extends BasePresenter {
        /**
         * 获取商品分类信息的方法
         * @param map
         */
        void getClassifyinfo(Map map);

        /**
         * 获取最终商品信息
         * @param map
         */
        void getProductinfo(Map map);
    }
}