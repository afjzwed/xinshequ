package com.yxld.yxchuangxin.ui.activity.camera.contract;

import com.yxld.yxchuangxin.entity.BuCheFang;
import com.yxld.yxchuangxin.entity.FangquEntity;
import com.yxld.yxchuangxin.ui.activity.base.BasePresenter;
import com.yxld.yxchuangxin.ui.activity.base.BaseView;

import java.util.List;
import java.util.Map;

/**
 * @author hzp
 * @Package The contract for TimeCheBuFangActivity
 * @Description: $description
 * @date 2017/09/05 17:39:46
 */
public interface TimeCheBuFangContract {
    interface View extends BaseView<TimeCheBuFangContractPresenter> {
        /**
         *
         */
        void showProgressDialog();

        /**
         *
         */
        void closeProgressDialog();

        void setFangqu(List<FangquEntity.DataBean> list);

        void setBuCheFangList(BuCheFang dataBean);

        void onCancalBuchefangChenggong();
    }

    interface TimeCheBuFangContractPresenter extends BasePresenter {
//        /**
//         *
//         */
//        void getBusinessInfo(Map map);

        void getFangqu(Map map);
        void getTimingBufang(Map map);

        /**
         * 撤销定时撤布防
         */
        void cacanlDingshiCheBuFang(Map map);
    }
}