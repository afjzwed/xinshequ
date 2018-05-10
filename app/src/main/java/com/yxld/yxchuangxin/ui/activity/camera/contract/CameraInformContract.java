package com.yxld.yxchuangxin.ui.activity.camera.contract;

import com.yxld.yxchuangxin.base.BaseEntity;
import com.yxld.yxchuangxin.entity.camera.ShareFamily;
import com.yxld.yxchuangxin.entity.camera.Shared;
import com.yxld.yxchuangxin.ui.activity.base.BasePresenter;
import com.yxld.yxchuangxin.ui.activity.base.BaseView;

import java.util.ArrayList;
import java.util.Map;

/**
 * @author hzp
 * @Package The contract for CameraInformActivity
 * @Description: $description
 * @date 2017/10/18 09:18:09
 */
public interface CameraInformContract {
    interface View extends BaseView<CameraInformContractPresenter> {
        /**
         *
         */
        void showProgressDialog();

        /**
         *
         */
        void closeProgressDialog();

        void onAllJiashuBack(ShareFamily shareFamily);

        void onSharedBack(Shared shared);
        void onAddBack(BaseEntity entity);

        void setYijiaJiashu(ArrayList<String> list, ArrayList<String> idList);
    }

    interface CameraInformContractPresenter extends BasePresenter {
        void getJiashu(Map map, String deviceId);
        void getShared(Map map);
        void addShare(Map map);
    }
}