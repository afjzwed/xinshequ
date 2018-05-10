package com.yxld.yxchuangxin.ui.adapter.wuye;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.yxld.yxchuangxin.R;
import com.yxld.yxchuangxin.Utils.TimeUtil;
import com.yxld.yxchuangxin.entity.Accredit;

import java.util.List;

/**
 * 作者：hu on 2017/6/13
 * 邮箱：365941593@qq.com
 * 描述：
 */

public class FangxingAdapter extends BaseQuickAdapter<Accredit.DataBean, BaseViewHolder> {

    public FangxingAdapter(List<Accredit.DataBean> data) {
        super(R.layout.item_fangxing, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, Accredit.DataBean item) {
        helper.setText(R.id.tv_name, item.getName())
                .setText(R.id.tv_room, item.getNum());
        helper.setText(R.id.tv_time, TimeUtil.timesTamp2YearMonthDay(item.getApplyTime()));
        if (item.getStatus() == 0) {
            helper.setText(R.id.tv_state, "待放行");
        }
        if (item.getStatus() ==1) {
            helper.setText(R.id.tv_state, "已放行");
        }
    }
}
