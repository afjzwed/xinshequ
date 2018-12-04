package com.yxld.yxchuangxin.ui.activity.main.contract;

import com.yxld.yxchuangxin.entity.GoodsKind;
import com.yxld.yxchuangxin.entity.MallClassify;
import com.yxld.yxchuangxin.ui.activity.base.BasePresenter;
import com.yxld.yxchuangxin.ui.activity.base.BaseView;

import java.util.Map;

/**
 * @author xlei
 * @Package The contract for MainNewFragment
 * @Description: $description
 * @date 2018/11/16 10:36:55
 */
public interface MainNewContract {
    interface View extends BaseView<MainNewContractPresenter> {
        /**
         *
         */
        void showProgressDialog();

        /**
         *
         */
        void closeProgressDialog();

        /**
         * 设置滚动通知
         */
        void setAction(String content);

        //设置秒杀的数据
        void setMiaoShaDatas(GoodsKind goodsKind);

        void setFenleiAdapter(MallClassify mallClassify);

    }

    interface MainNewContractPresenter extends BasePresenter {
        //        /**
//         *
//         */
//        void getBusinessInfo(Map map);
        void getAction(Map data);

        //获取秒杀的数据(改为取商城新品上架的数据)
        void getMianShaData();

        void getBanner(Map data);

        //获取分类数据
        void getFenlei();

        //检测版本更新
        void getLastVersion();
    }
}