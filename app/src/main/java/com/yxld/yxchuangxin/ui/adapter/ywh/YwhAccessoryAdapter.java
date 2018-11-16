package com.yxld.yxchuangxin.ui.adapter.ywh;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.yxld.yxchuangxin.R;
import com.yxld.yxchuangxin.entity.YwhCurrentflow;

/**
 * Created by William on 2018/11/12.
 */

public class YwhAccessoryAdapter extends BaseQuickAdapter<YwhCurrentflow.DataBean.FlowBean.FilesBean, BaseViewHolder> {

    public YwhAccessoryAdapter() {
        super(R.layout.item_ywh_accessory_list);
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, YwhCurrentflow.DataBean.FlowBean.FilesBean filesBean) {
        baseViewHolder.addOnClickListener(R.id.tv_download);
        baseViewHolder.setText(R.id.tv_title, filesBean.getFilename());

    }
}
