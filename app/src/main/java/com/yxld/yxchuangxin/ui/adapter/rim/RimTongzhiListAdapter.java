package com.yxld.yxchuangxin.ui.adapter.rim;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.yxld.yxchuangxin.R;
import com.yxld.yxchuangxin.entity.SJOrder;

import java.util.List;


/**
 * @author wwx
 * @Package com.yxld.yxchuangxin.ui.adapter.rim.RimTongzhiListAdapter
 * @Description: 欣周边通知列表适配器
 * @date 2017/06/17
 */
public class RimTongzhiListAdapter extends BaseQuickAdapter<SJOrder, BaseViewHolder> {


    public RimTongzhiListAdapter(List<SJOrder> data) {
        super(R.layout.rim_tongzhi_item, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, SJOrder item) {
        helper.setText(R.id.tv_time, item.getOrderOrderTime())
                .setText(R.id.tv_story_name,item.getOrderBusinessName());
    }

}
