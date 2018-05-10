package com.yxld.yxchuangxin.ui.adapter.rim;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.yxld.yxchuangxin.R;
import com.yxld.yxchuangxin.entity.SJComplain;

import java.util.List;

/**
 * 作者：wwx on 2017/6/16 0016 18:11
 * 邮箱：wanwenxiu0709@foxmail.com
 * 描述：
 */

public class RimComplainAdapter extends BaseQuickAdapter<SJComplain, BaseViewHolder> {

    public RimComplainAdapter(List<SJComplain> data) {
        super(R.layout.rim_complain_item, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, SJComplain item) {
//        Log.d("geek", "item.getComplainStatus " + item.getComplainStatus() + " convert: item" +
//                item.toString());
        //（1-待处理；2-处理中；3-已处理）
        String state = "";
       /* if(item.getComplainStatus() == 1) state = "待处理";
        if(item.getComplainStatus() == 2) state = "处理中";
        if(item.getComplainStatus() == 3) state = "已处理";*/
        if (item.getComplainStatus() == 1) {
            state = "待处理";
        } else if (item.getComplainStatus() == 2) {
            state = "已处理";
        }

        helper.setText(R.id.tv_complaintBianhao, item.getComplainOrderNumber())
                .setText(R.id.tv_complaintTime, item.getComplainTime())
                .setText(R.id.tv_complaintcontent, item.getComplainContent())
                .setText(R.id.tv_complaintState, state).addOnClickListener(R.id.item_main);

    }
}
