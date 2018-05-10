package com.yxld.yxchuangxin.ui.activity.goods.contract;

import android.view.View;

import com.yxld.yxchuangxin.base.BaseEntity;
import com.yxld.yxchuangxin.entity.CxwyMallCart;
import com.yxld.yxchuangxin.entity.CxwyMallComment;
import com.yxld.yxchuangxin.entity.CxwyMallProduct;
import com.yxld.yxchuangxin.entity.MyAllComment;
import com.yxld.yxchuangxin.entity.goods.MallNewProduct;
import com.yxld.yxchuangxin.entity.goods.MallNewProduct1;
import com.yxld.yxchuangxin.entity.goods.ShopCart;
import com.yxld.yxchuangxin.ui.activity.base.BasePresenter;
import com.yxld.yxchuangxin.ui.activity.base.BaseView;

import java.util.Map;

/**
 * @author hu
 * @Package The contract for GoodDetailActivity
 * @Description: $description
 * @date 2017/06/16
 */
public interface GoodDetailContract {
    interface View extends BaseView<GoodDetailContractPresenter> {
        void showProgressDialog();

        void closeProgressDialog();

        void onLoadGoodsDetailSucceed(MallNewProduct1 product);

        void onLoadGoodsDetailFailed();

        void onLoadCommentSucceed(MyAllComment comment);

        void onAdd2ShopCartSuccess(BaseEntity entity);

        void onLoadShopCartSucceed(ShopCart cart);

        void onLoadFailed();

    }

    interface GoodDetailContractPresenter extends BasePresenter {
        void loadGoodsDetailFromServer(String productId);
        void loadCommentFromServer(String productId);

        void add2ShopCart(Map map);

        void loadShopCartFromServer();
    }
}