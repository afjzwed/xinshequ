package com.yxld.yxchuangxin.ui.activity.goods.contract;

import com.yxld.yxchuangxin.base.BaseEntity;
import com.yxld.yxchuangxin.entity.GoodsKind;
import com.yxld.yxchuangxin.entity.IsNight;
import com.yxld.yxchuangxin.entity.MallClassify;
import com.yxld.yxchuangxin.entity.goods.ShopNewList;
import com.yxld.yxchuangxin.ui.activity.base.BasePresenter;
import com.yxld.yxchuangxin.ui.activity.base.BaseView;

import java.util.Map;

/**
 * @author yuan
 * @Package The contract for MallFragment
 * @Description: $description
 * @date 2017/06/14
 */
public interface MallContract {
    interface View extends BaseView<MallContractPresenter> {
        void onLoadFailed();

        void setFenleiAdapter(MallClassify mallClassify);

        void setShangpin(GoodsKind goodsKind);

        void setShopCartCount(int count);
        void addGood2ShopCartSuccess(BaseEntity baseEntity,android.view.View view, String imgurl);

        void setIsNight(boolean isFirst,IsNight isNight);

        void setNightData(ShopNewList shopNewList);
    }

    interface MallContractPresenter extends BasePresenter {
        void getFenlei();

        void getGoodsKinds();

        void getShopCart();

        void addGood2ShopCart(Map map, android.view.View view,String imgUrl);

        void isNight(boolean isFirst);

        void getNightGoodList(int page, int rows);
    }
}