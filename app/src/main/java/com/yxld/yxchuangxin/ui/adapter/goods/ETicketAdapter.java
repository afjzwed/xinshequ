package com.yxld.yxchuangxin.ui.adapter.goods;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.yxld.yxchuangxin.R;
import com.yxld.yxchuangxin.Utils.TimeUtil;
import com.yxld.yxchuangxin.entity.CxwyDianziquan;

import java.util.List;

/**
 * 作者：hu on 2017/6/22
 * 邮箱：365941593@qq.com
 * 描述：
 */

public class ETicketAdapter extends BaseQuickAdapter<CxwyDianziquan.RowsBean, BaseViewHolder> {

    public ETicketAdapter(@Nullable List<CxwyDianziquan.RowsBean> data) {
        super(R.layout.item_eticket, data);
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, CxwyDianziquan.RowsBean dianziquan) {
        baseViewHolder.setText(R.id.tv_ticket_get_time, TimeUtil.timesTamp2Year(Long.valueOf(dianziquan.getGivenTime())))
                .setText(R.id.tv_ticket_effect, getDescByType(dianziquan.getVoucherType()) +"  "+ (dianziquan.getOrderNum() == null ? "" : dianziquan.getOrderNum()))
                //  .setText(R.id.tv_ticket_way, getDescByType(dianziquan.getVoucherType()))
                .setText(R.id.tv_ticket_price, getMoneyByType(dianziquan.getVoucherType(), dianziquan.getDenomination() + ""))
                .setTextColor(R.id.tv_ticket_price, getTextColorByType(dianziquan.getVoucherType()))
                .setBackgroundRes(R.id.root_layout, getBackgroundByType(dianziquan.getVoucherType()));

    }

    private String getMoneyByType(Integer voucherType, String money) {
        String result = "";
        if (voucherType == 1) {
            result = "- ¥ " + money;
        } else if (voucherType == 2) {
            result = "+ ¥ " + money;
        } else {
            result = "¥ " + money;
        }

        return result;
    }

    private int getTextColorByType(Integer voucherType) {
        if (voucherType == 1) {
            //商品消费
            return mContext.getResources().getColor(R.color.color_31a2e8);

        } else {
            return mContext.getResources().getColor(R.color.color_ff5d5b);
        }
    }

    private int getBackgroundByType(Integer voucherType) {
        if (voucherType == 1) {
            //商品消费
            return R.mipmap.dzj_ls;
        } else {
            return R.mipmap.dzj_hs;
        }
    }

    private String getDescByType(int type) {
        String cur_type = "";
        if (type == 3) { // 3
            cur_type = "物业赠送";
        } else if (type == 1) {//商品消费 1
            cur_type = "商城消费";
        } else { //消费退还  2
            cur_type = "消费退还";
        }
        return cur_type;
    }

    public void onDestroy() {
        mContext = null;
        mData = null;
    }
}
