package com.yxld.yxchuangxin.ui.activity.ywh.contract;

import com.yxld.yxchuangxin.base.BaseEntity;
import com.yxld.yxchuangxin.entity.HouxuanRenBean;
import com.yxld.yxchuangxin.entity.YwhTj;
import com.yxld.yxchuangxin.ui.activity.base.BasePresenter;
import com.yxld.yxchuangxin.ui.activity.base.BaseView;

import java.util.Map;

/**
 * @author William
 * @Package The contract for HouxuanListActivity
 * @Description: $description
 * @date 2018/11/14 09:53:34
 */
public interface HouxuanListContract {
    interface View extends BaseView<HouxuanListContractPresenter> {
        /**
         *
         */
        void showProgressDialog();

        /**
         *
         */
        void closeProgressDialog();

        void getTjcbzSuccess(HouxuanRenBean baseEntity);

        void commitLySuccess(BaseEntity baseEntity);
    }

    interface HouxuanListContractPresenter extends BasePresenter {
        void getTjcbz(Map map);

        void comitLy(Map map);
    }
}