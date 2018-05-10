package com.yxld.yxchuangxin.ui.adapter.mine;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.socks.library.KLog;
import com.yxld.yxchuangxin.R;
import com.yxld.yxchuangxin.db.green.UserInfo;

import java.util.List;

/**
 * 作者：Android on 2017/10/12
 * 邮箱：365941593@qq.com
 * 描述：
 */

public class MultiAccountAdapter extends BaseQuickAdapter<UserInfo, BaseViewHolder> {

    public MultiAccountAdapter(@Nullable List<UserInfo> data) {
        super(R.layout.item_multi_account, data);
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, UserInfo userInfo) {
        baseViewHolder.setText(R.id.phone, "账号：" + userInfo.getPhoneNumber())
                .setText(R.id.xiaoqu, "小区：" + userInfo.getLouPan());
        if (userInfo.getIsLogin()) {
            KLog.i("登录的账号为：" + userInfo.getPhoneNumber() + userInfo.getLouPan());
            baseViewHolder.setVisible(R.id.islogin,true);
        } else {
            KLog.i("没有登录的账号为：" + userInfo.getPhoneNumber() + userInfo.getLouPan());
            baseViewHolder.setVisible(R.id.islogin,false);
        }
//        baseViewHolder.setVisible(R.id.islogin, userInfo.getIsLogin()? true : false);
    }

}
