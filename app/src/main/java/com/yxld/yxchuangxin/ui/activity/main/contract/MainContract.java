package com.yxld.yxchuangxin.ui.activity.main.contract;


import com.yxld.yxchuangxin.entity.CxwyMallPezhi;
import com.yxld.yxchuangxin.entity.GoodsKind;
import com.yxld.yxchuangxin.entity.MallClassify;
import com.yxld.yxchuangxin.entity.User;
import com.yxld.yxchuangxin.ui.activity.base.BasePresenter;
import com.yxld.yxchuangxin.ui.activity.base.BaseView;

import java.util.List;
import java.util.Map;

/**
 * Created by hu on 2017/5/16.
 */

public interface MainContract {
    interface View extends BaseView<MainPresenter> {
        void setText(User baseBack);
        void setShopRecyclerview(MallClassify mallClassify);
        void setMiaoshaRecyclerview(List<GoodsKind.RowsBean.XinpinListsBean> xinpinLists);

        /**
         * 设置轮播图
         */
        void setBanner(CxwyMallPezhi info);
        /**
         * 设置滚动通知
         */
        void setAction(String content);

        //设置秒杀的数据
//        void setMiaoShaDatas(ShopList products);
        void setMiaoShaDatas(GoodsKind goodsKind);

        //刷新失败
        void onRefreshFailure();

        void setFenleiAdapter(MallClassify mallClassify);
    }

    interface MainPresenter extends BasePresenter {
        /**
         * 获取轮播图
         * @param data
         */
        void getBanner(Map data);

        /**
         * 获取滚动通知
         * @param data
         */
        void getAction(Map data);

        //获取秒杀的数据(改为取商城新品上架的数据)
        void getMianShaData();

        void setReStart();

        //获取分类数据
        void getFenlei();
    }
}
