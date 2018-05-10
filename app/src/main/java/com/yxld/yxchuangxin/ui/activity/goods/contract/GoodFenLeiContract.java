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
 * @author xlei
 * @Package The contract for GoodFenLeiFragment
 * @Description: $description
 * @date 2017/10/19 09:28:17
 */
public interface GoodFenLeiContract {
    interface View extends BaseView<GoodFenLeiContractPresenter> {
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

        void onAdd2ShopCartSuccess(android.view.View view, String imgUrl);

        void onLoadShopCartSucceed(int count);
    }

    interface GoodFenLeiContractPresenter extends BasePresenter {

        void loadMallGoodsFromServer(String feilei1,String feilei2, int rankType, int rankMethod, int inType, int currentPage, int onePageNum);

        void add2ShopCart(Map map, android.view.View view, String imgUrl);

        void loadShopCartFromServer();

        /**
         * 屏蔽某些特定的商品
         *
         * @param list
         */
        List<CxwyMallProduct> shieldGood(ShopList list);
    }
}