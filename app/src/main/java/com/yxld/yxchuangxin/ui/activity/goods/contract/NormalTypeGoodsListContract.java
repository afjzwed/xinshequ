package com.yxld.yxchuangxin.ui.activity.goods.contract;

import com.yxld.yxchuangxin.ui.activity.base.BasePresenter;
import com.yxld.yxchuangxin.ui.activity.base.BaseView;

/**
 * @author Yuan.Y.Q
 * @Package The contract for NormalTypeGoodsListActivity
 * @Description: $description
 * @date 2017/06/16
 */
public interface NormalTypeGoodsListContract {
    interface View extends BaseView<NormalTypeGoodsListContractPresenter> {
    }

    interface NormalTypeGoodsListContractPresenter extends BasePresenter {
    }
}