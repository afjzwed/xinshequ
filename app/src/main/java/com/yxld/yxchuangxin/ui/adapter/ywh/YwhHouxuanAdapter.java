package com.yxld.yxchuangxin.ui.adapter.ywh;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.yxld.yxchuangxin.R;
import com.yxld.yxchuangxin.entity.HouxuanRenBean;

/**
 * Created by William on 2018/11/14.
 */

public class YwhHouxuanAdapter extends BaseQuickAdapter<HouxuanRenBean.DataBean, BaseViewHolder> {
    public YwhHouxuanAdapter() {
        super(R.layout.adapter_tuijian);
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, HouxuanRenBean.DataBean dataBean) {
        baseViewHolder.setText(R.id.tv_name, dataBean.getName()).setText(R.id.tv_qu, dataBean.getArea())
                .setText(R.id.tv_ld, dataBean.getBuilding()).setText(R.id.tv_dy, dataBean.getUnit()).setText(R.id.tv_fh, dataBean.getRoomNumber())
                .addOnClickListener(R.id.tv_tj);
    }
}
