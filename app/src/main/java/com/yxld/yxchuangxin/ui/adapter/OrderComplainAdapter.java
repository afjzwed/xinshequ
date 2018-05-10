package com.yxld.yxchuangxin.ui.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.yxld.yxchuangxin.R;
import com.yxld.yxchuangxin.entity.CxwyComplain;
import com.yxld.yxchuangxin.entity.OrderComplainEntity;
import com.yxld.yxchuangxin.entity.goods.MallOrderSuggest;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * @author: Yuan.Y.Q
 * @date: 2017/6/22
 * @descprition:
 */

public class OrderComplainAdapter extends BaseQuickAdapter<MallOrderSuggest.DataBean, BaseViewHolder> {
    private SimpleDateFormat format;

    public OrderComplainAdapter(@Nullable List<MallOrderSuggest.DataBean> data) {
        super(R.layout.item_order_complain, data);
        format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss", Locale.CHINA);
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, MallOrderSuggest.DataBean entity) {
        //String stat = entity.getTsjyStat();
        baseViewHolder.setText(R.id.tv_order_complain_type, entity.getTsjyLeixing() == 1 ? "投诉" : "建议");
        baseViewHolder.setText(R.id.tv_order_complain_content, entity.getTsjyNeirong());
        baseViewHolder.setText(R.id.tv_order_complain_time, entity.getTsjyTijiaoShijian());
        baseViewHolder.setText(R.id.tv_order_complain_order_num, entity.getTsjyDindanBianhao());
    }

    private String getDescByType(String stat) {
//        return "0".equals(stat)?"未处理":"1".equals(stat)?"处理中":"已处理";
        return "0".equals(stat) ? "处理中" : "已处理";
    }

    private String handlerTime(OrderComplainEntity.TsjyShijianBean tsjyShijian) {
        Date date = new Date(tsjyShijian.getTime());
        return format.format(date);
    }
}
