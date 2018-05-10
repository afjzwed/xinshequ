package com.yxld.yxchuangxin.ui.activity.wuye.contract;

import android.content.Intent;

import com.yxld.yxchuangxin.ui.activity.base.BasePresenter;
import com.yxld.yxchuangxin.ui.activity.base.BaseView;

import java.util.Map;

/**
 * @author hu
 * @Package The contract for AddLiveMemberActivity
 * @Description: $description
 * @date 2017/06/07
 */
public interface AddLiveMemberContract {
    interface View extends BaseView<AddLiveMemberContractPresenter> {
        void setContactNumber(String name, String number);
        void onAddBack(String msg);
    }

    interface AddLiveMemberContractPresenter extends BasePresenter {
        void onActivityResult(int requestCode, int resultCode, Intent data);
        void addChengyuan(Map data);
    }
}