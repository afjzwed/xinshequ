package com.yxld.yxchuangxin.ui.adapter.wuye;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.yxld.yxchuangxin.R;
import com.yxld.yxchuangxin.entity.RoomRent;

import java.util.List;

/**
 * 作者：hu on 2017/6/16
 * 邮箱：365941593@qq.com
 * 描述：
 */

public class RoomRentAdapter extends BaseQuickAdapter<RoomRent.YezhuBean, BaseViewHolder> {
    public RoomRentAdapter(@Nullable List<RoomRent.YezhuBean> data) {
        super(R.layout.item_room_rent, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, RoomRent.YezhuBean item) {
        String room = item.getXmName() + item.getFwLoudong() + "栋" + item.getFwDanyuan() + "单元" + item.getFwFanghao();
        helper.setText(R.id.tv_address, room);
        if (item.getFwIsChuzu() == 0) {
            helper.setText(R.id.tv_rent_status, "未出租");
            helper.setText(R.id.bt_rent_status, "未出租");
            helper.setVisible(R.id.bt_cancal_rent, false);
        } else {
            helper.setText(R.id.tv_rent_status, "已出租");
            helper.setText(R.id.bt_rent_status, "已出租");
            helper.setVisible(R.id.bt_cancal_rent, true);

        }
        helper.addOnClickListener(R.id.bt_rent_status);
        helper.addOnClickListener(R.id.bt_cancal_rent);
        helper.setText(R.id.tv_status, item.getJfFwTypeLeixing());
        helper.setText(R.id.tv_rent_info, item.getYezhu_name() + " " + item.getYezhu_shouji());
        helper.setText(R.id.tv_start_time, item.getFwyzRztime());
    }
}
