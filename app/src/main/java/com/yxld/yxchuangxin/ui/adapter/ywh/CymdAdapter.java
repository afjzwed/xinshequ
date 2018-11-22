package com.yxld.yxchuangxin.ui.adapter.ywh;



import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.yxld.yxchuangxin.R;
import com.yxld.yxchuangxin.entity.YwhCurrentflow;



/**
 * Created by Administrator on 2018/11/9.
 */

public class CymdAdapter extends BaseQuickAdapter<YwhCurrentflow.DataBean.FlowBean.ConfirmPeopleBean, BaseViewHolder> {
    public CymdAdapter() {
        super(R.layout.item_cymd);
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, YwhCurrentflow.DataBean.FlowBean.ConfirmPeopleBean s) {
        baseViewHolder.setText(R.id.tv_name, s.getCfname()).setText(R.id.tv_ld,  s
                .getExpect()+"期-"+s.getBuilding()+"栋-" + s.getUnit() +"单元-" + s.getRoomNumber())
                .setText(R.id.tv_content, "简介：" + s.getDriscipt());
    }
}
