package com.yxld.yxchuangxin.ui.activity.goods.contract;

import com.yxld.yxchuangxin.base.BaseEntity;
import com.yxld.yxchuangxin.entity.CxwyMallCart;
import com.yxld.yxchuangxin.entity.CxwyMallProduct;
import com.yxld.yxchuangxin.entity.ShopList;
import com.yxld.yxchuangxin.entity.goods.ShopNewList;
import com.yxld.yxchuangxin.ui.activity.base.BasePresenter;
import com.yxld.yxchuangxin.ui.activity.base.BaseView;

import java.util.List;
import java.util.Map;

/**
 * @author Yuan.Y.Q
 * @Package The contract for MallGoodsListActivity
 * @Description: $description
 * @date 2017/06/19 14:28:26
 */
public interface MallGoodsListContract {
    interface View extends BaseView<MallGoodsListContractPresenter> {
        /**
         *
         */
        void showProgressDialog();

        /**
         *
         */
        void closeProgressDialog();

        void onMallGoodsBacked(ShopNewList list);

        void onLoadFailed();
        void onLoadFailed(String msg);


        void onAdd2ShopCartSuccess(android.view.View view, String url);

        void onLoadShopCartSucceed(int count);
    }

    interface MallGoodsListContractPresenter extends BasePresenter {
//        /**
//         *
//         */
//        void getBusinessInfo(Map map);

        void loadMallGoodsFromServer(String searchName,int rankType,int rankMethod,int inType,int currentPage,int onePageNum);

        void add2ShopCart(Map map, android.view.View view, String url);

        void loadShopCartFromServer();

        /**
         * 屏蔽某些特定的商品
         * @param list
         */
        List<CxwyMallProduct> shieldGood(ShopList list);
    }
}