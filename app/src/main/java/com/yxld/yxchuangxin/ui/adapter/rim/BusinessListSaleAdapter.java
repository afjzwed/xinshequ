package com.yxld.yxchuangxin.ui.adapter.rim;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.yxld.yxchuangxin.R;
import com.yxld.yxchuangxin.entity.CxwyBusinessInfo;

import java.util.List;

/**
 * Created by hu on 2017/5/10.
 */

public class BusinessListSaleAdapter extends BaseQuickAdapter<CxwyBusinessInfo.DataBean.ActivityBean,BaseViewHolder> {
    public BusinessListSaleAdapter(List<CxwyBusinessInfo.DataBean.ActivityBean> data) {
        super(R.layout.item_sale_list, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, CxwyBusinessInfo.DataBean.ActivityBean item) {
        helper.setText(R.id.tv_content, item.getActivityExplain());
        helper.setTextColor(R.id.tv_content, 0xff909090);
    }
}
