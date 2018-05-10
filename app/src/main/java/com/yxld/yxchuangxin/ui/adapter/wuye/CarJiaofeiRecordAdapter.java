package com.yxld.yxchuangxin.ui.adapter.wuye;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.yxld.yxchuangxin.R;
import com.yxld.yxchuangxin.Utils.StringUitl;
import com.yxld.yxchuangxin.entity.CarJiaofeiRecord;

import java.util.List;

/**
 * 作者：hu on 2017/7/6
 * 邮箱：365941593@qq.com
 * 描述：
 */

public class CarJiaofeiRecordAdapter extends BaseQuickAdapter<CarJiaofeiRecord.DataBean, BaseViewHolder> {

    public CarJiaofeiRecordAdapter(@Nullable List<CarJiaofeiRecord.DataBean> data) {
        super(R.layout.item_car_jiaofei_record, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, CarJiaofeiRecord.DataBean item) {
        helper.setText(R.id.tv_car_number, item.getMediaNo());
        if (!StringUitl.isEmpty(item.getPayResult()) && "00".equals(item.getPayResult())) {
            helper.setText(R.id.tv_jiaofei_status, "成功");
        } else {
            helper.setText(R.id.tv_jiaofei_status, "失败");
        }

        helper.setText(R.id.tv_jiaofei_month, item.getPayMonth() + "");
        helper.setText(R.id.tv_jiaofei_time, item.getPayTime().substring(0, item.getPayTime().length()-2));
        helper.setText(R.id.tv_jiaofei_money, item.getPayMoney() + "");
    }
}
