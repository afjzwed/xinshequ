package com.yxld.yxchuangxin.ui.activity.mine.contract;

import com.yxld.yxchuangxin.entity.LoginPhoneEntity;
import com.yxld.yxchuangxin.ui.activity.base.BasePresenter;
import com.yxld.yxchuangxin.ui.activity.base.BaseView;

import java.util.Map;

/**
 * @author hu
 * @Package The contract for FindPasswordActivity
 * @Description: $description
 * @date 2017/06/23 14:14:05
 */
public interface FindPasswordContract {
    interface View extends BaseView<FindPasswordContractPresenter> {
        /**
         *
         */
        void showProgressDialog();

        /**
         *
         */
        void closeProgressDialog();

        /**
         * 设置项目
         */
        void setLoginPhone(LoginPhoneEntity loginPhone);

        /**
         * 开始读秒
         */
        void setStartTime();

        /**
         * 读秒
         */
        void setSecond(String text);

        /**
         * 读秒完成
         */
        void setSecondOver();

        /**
         * 密码修改成功
         */
        void setResetPassWordSuccess();

        /**
         * 验证码验证成功
         */
        void checkCodeCheckSuccess();
    }

    interface FindPasswordContractPresenter extends BasePresenter {
        /**
         *获取项目
         */
        void loginPlot(Map map);

        /**
         * 获取验证码的请求
         */
        void getCheckCode(Map map);

        /**
         * 提交修改密码的请求
         */
        void getReSetPassWord(Map map);

        /**
         * 判断是否存在手机号码？？？？ 我不太清楚这个接口的作用，只知道这么写了
         */
        void getExistShouji(Map map, String phone);

        /**
         * 确认验证码
         */
        void checkCheckCode(String shouji, String checkCode);
    }
}