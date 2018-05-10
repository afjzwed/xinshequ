package com.yxld.yxchuangxin.ui.activity.goods.contract;

import android.support.v4.app.Fragment;

import com.yxld.yxchuangxin.ui.activity.base.BasePresenter;
import com.yxld.yxchuangxin.ui.activity.base.BaseView;

import java.util.List;

/**
 * @author hu
 * @Package The contract for MarketActivity
 * @Description: $description
 * @date 2017/06/12
 */
public interface MarketContract {
    interface View extends BaseView<MarketContractPresenter> {
        void setAdapter(List<Fragment> fragments, List<String> titles);
    }

    interface MarketContractPresenter extends BasePresenter {
        void initAdapterData();
    }
}