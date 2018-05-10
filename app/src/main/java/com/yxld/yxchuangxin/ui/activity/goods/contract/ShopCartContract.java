package com.yxld.yxchuangxin.ui.activity.goods.contract;

import com.yxld.yxchuangxin.base.BaseEntity;
import com.yxld.yxchuangxin.entity.CxwyMallCart;
import com.yxld.yxchuangxin.entity.goods.ShopCart;
import com.yxld.yxchuangxin.ui.activity.base.BasePresenter;
import com.yxld.yxchuangxin.ui.activity.base.BaseView;

import java.util.List;

/**
 * @author yuan
 * @Package The contract for ShopCartFragment
 * @Description: $description
 * @date 2017/06/14
 */
public interface ShopCartContract {
    interface View extends BaseView<ShopCartContractPresenter> {
        void backShopCartProducts(CxwyMallCart products);

        void onOneCheckBoxNotChecked();

        void onAllChecked(boolean allChecked);

        void onItemChecked();

        void onItemCancelChecked();

        void onItemPriceChanged();

        void onLoadProductFailed();

        void onLoadProductFailed(String msg);

        void onChangedCartCountSucceed(BaseEntity baseEntity, int pos, int count);

        void onDeleteCartSucceed();

        void onLoadCartProductSuccess(ShopCart shopCart);

        void changeCartCountSuccess();

    }

    interface ShopCartContractPresenter extends BasePresenter {
        void getShopCartProductsFromServer();

        void changeCartCount(String num, String bianhao);

        void deleteCart(List<Integer> cartsId);
    }
}