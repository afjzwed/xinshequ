package com.yxld.yxchuangxin.ui.activity.rim.contract;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.yxld.yxchuangxin.base.BaseEntity;
import com.yxld.yxchuangxin.entity.BaseBack;
import com.yxld.yxchuangxin.entity.CxwyBusinessInfo;
import com.yxld.yxchuangxin.entity.CxwyProductInfo;
import com.yxld.yxchuangxin.entity.ShopCarList;
import com.yxld.yxchuangxin.ui.activity.base.BasePresenter;
import com.yxld.yxchuangxin.ui.activity.base.BaseView;
import com.yxld.yxchuangxin.ui.adapter.rim.MyChooseProductAdapter;

import java.util.Map;

/**
 * @author hu
 * @Package The contract for BusinessActivity
 * @Description: $description
 * @date 2017/06/17
 */
public interface BusinessContract {
    interface View extends BaseView<BusinessContractPresenter> {
        /**
         * 设置fragment的适配器
         */
        void setFragmentAdapter(CxwyBusinessInfo info);

        /**
         * 设置打折的适配器
         */
        void setSaleAdapter(CxwyBusinessInfo data, int flag);

        /**
         * 设置选中的商品的数量
         *
         * @param allItem
         * @param money
         */
        void setProductCount(int allItem, CharSequence money);

        /**
         * 当选中的商品的数量超过99
         *
         * @param money
         */
        void setProductOver99(CharSequence money);

        /**
         * 选中的商品数为0
         */
        void setProductCountNone(CharSequence money);

        /**
         *
         */
        void showProgressDialog();

        /**
         *
         */
        void closeProgressDialog();

        /**
         *
         */
        void showManageAdapter(MyChooseProductAdapter adapter, RecyclerView recyclerView);

        /**
         * 添加购物车成功
         *
         * @param baseEntity
         */
        void addShopCarSuccess(BaseEntity baseEntity, android.view.View view,String url);

        /**
         * 查询购物车列表成功
         *
         * @param baseEntity
         */
        void getShopCarListSuccess(ShopCarList baseEntity);

        /**
         * 删除购物车商品成功
         *
         * @param baseEntity
         */
        void deleteShopCarSuccess(BaseEntity baseEntity);

        /**
         * 更新购物车商品成功
         *
         * @param baseEntity
         */
        void updataShopCarSuccess(BaseEntity baseEntity);
        /**
         * 确认修改商品
         */
        void updataOrderSuccess(BaseEntity baseEntity);
    }

    interface BusinessContractPresenter extends BasePresenter {
        /**
         * 获取商家一级分类
         *
         * @param map
         */
        void getBusinessInfo(Map map);

        /**
         * 添加购物车
         *
         * @param map
         */
        void addShopCar(Map map, android.view.View view,String url);

        /**
         * 添加购物车商品
         *
         * @param map
         */
        void getShopCarList(Map map);

        /**
         * 删除购物车商品
         *
         * @param map
         */
        void deleteShopCar(Map map);

        /**
         * 更新购物车商品
         *
         * @param map
         */
        void updataShopCar(Map map);
        /**
         * 确认修改商品订单
         *
         * @param map
         */
        void updataOrder(Map map);
    }
}