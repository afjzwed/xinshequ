package com.yxld.yxchuangxin.ui.adapter.ywh;


import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.yxld.yxchuangxin.R;
import com.yxld.yxchuangxin.entity.YwhTj;


/**
 * Created by Administrator on 2018/11/8.
 */

public class YwhTuiJianAdapter extends BaseQuickAdapter<YwhTj.ResultsBean,BaseViewHolder> {
    public YwhTuiJianAdapter() {
        super(R.layout.adapter_tuijian);
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, YwhTj.ResultsBean s) {
        baseViewHolder.setText(R.id.tv_name, s.getCfname()).setText(R.id.tv_qu, s.getExpect())
                .setText(R.id.tv_ld, s.getBuilding()).setText(R.id.tv_dy, s.getUnit()).setText(R.id.tv_fh, s.getRoomNumber())
                .addOnClickListener(R.id.tv_tj);
    }
}
