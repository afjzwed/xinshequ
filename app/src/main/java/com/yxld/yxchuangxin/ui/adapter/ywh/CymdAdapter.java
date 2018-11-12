package com.yxld.yxchuangxin.ui.adapter.ywh;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.yxld.yxchuangxin.R;
import com.yxld.yxchuangxin.entity.YwhCurrentflow;

import java.util.List;

/**
 * Created by Administrator on 2018/11/9.
 */

public class CymdAdapter extends BaseQuickAdapter<YwhCurrentflow.DataBean.FlowBean.ConfirmPeopleBean,BaseViewHolder> {
    public CymdAdapter() {
        super(R.layout.item_cymd);
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, YwhCurrentflow.DataBean.FlowBean.ConfirmPeopleBean s) {
        baseViewHolder.setText(R.id.tv_name, s.getCfname()).setText(R.id.tv_ld, s.getBuilding() + s.getUnit() + s.getExpect() + s.getRoomNumber())
                .setText(R.id.tv_content, s.getDriscipt());
    }
}
