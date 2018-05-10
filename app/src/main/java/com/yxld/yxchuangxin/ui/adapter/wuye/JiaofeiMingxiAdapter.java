package com.yxld.yxchuangxin.ui.adapter.wuye;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.yxld.yxchuangxin.R;
import com.yxld.yxchuangxin.entity.JiaofeiMingxi;

import java.util.List;

/**
 * 作者：hu on 2017/7/4
 * 邮箱：365941593@qq.com
 * 描述：
 */

public class JiaofeiMingxiAdapter extends BaseQuickAdapter<JiaofeiMingxi.HouseBean, BaseViewHolder> {

    public JiaofeiMingxiAdapter(@Nullable List<JiaofeiMingxi.HouseBean> data) {
        super(R.layout.item_jiaofei_mingxi, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, JiaofeiMingxi.HouseBean houseBean) {
        helper.setText(R.id.tv_time, houseBean.getTime())
                .setText(R.id.tv_status, houseBean.getStatusM())
                .setText(R.id.tv_money, houseBean.getFees())
                .setText(R.id.tv_zhinajin, houseBean.getLateFees());
        if (houseBean.getStatusM().equals("未缴费")){
            helper.setTextColor(R.id.tv_status,android.graphics.Color.RED);
        }else {
            helper.setTextColor(R.id.tv_status, android.graphics.Color.GREEN);
        }
    }
}
