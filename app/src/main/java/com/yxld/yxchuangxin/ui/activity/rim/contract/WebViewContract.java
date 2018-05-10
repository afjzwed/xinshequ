package com.yxld.yxchuangxin.ui.activity.rim.contract;

import com.yxld.yxchuangxin.ui.activity.base.BasePresenter;
import com.yxld.yxchuangxin.ui.activity.base.BaseView;
/**
 * @author wwx
 * @Package The contract for WebViewActivity
 * @Description: $description
 * @date 2017/06/17
 */
public interface WebViewContract {
    interface View extends BaseView<WebViewContractPresenter> {
        void initWebView();
    }

    interface WebViewContractPresenter extends BasePresenter {
    }
}