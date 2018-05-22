package com.yxld.yxchuangxin.ui.adapter.wuye;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.yxld.yxchuangxin.R;
import com.yxld.yxchuangxin.entity.LiuCheng;

import java.util.List;

/**
 * @author xlei
 * @Date 2018/5/14.
 */

public class LiuChengAdapter extends BaseQuickAdapter<LiuCheng, BaseViewHolder> {


    public LiuChengAdapter(List<LiuCheng> data) {
        super(R.layout.item_liucheng, data);
    }

    @Override
    protected void convert(BaseViewHolder viewHolder, LiuCheng liuCheng) {


//        if (viewHolder.getAdapterPosition()==this.getData().size()-1){
//            viewHolder.setTextColor(R.id.tv_status, mContext.getResources().getColor(R.color.blue));
//          //  viewHolder.setBackgroundRes(R.id.view_bg, mContext.getResources().get(R.drawable.circle_blue));
//            // viewHolder.setTextColor(R.id.tv_name, mContext.getResources().getColor(R.color.blue));
//            //  viewHolder.setTextColor(R.id.tv_name，mContext.getResources().getColor(R.color.blue));
//            viewHolder.setBackgroundRes(R.id.view_bg, R.drawable.circle_blue);
//        }
        viewHolder.setText(R.id.tv_status,"报修状态："+ liuCheng.getZhuangtaiName());
        switch (liuCheng.getZhuangtai()) {
            case 0:
               viewHolder.setText(R.id.tv_name,"报修人："+liuCheng.getName());
               viewHolder.setText(R.id.tv_time,liuCheng.getTime());
                break;
            case 1:
                viewHolder.setText(R.id.tv_name,"负责人："+liuCheng.getName());
                viewHolder.setText(R.id.tv_time,liuCheng.getTime());
                break;
            case 2:
                viewHolder.setText(R.id.tv_name,"维修人："+liuCheng.getName());
                viewHolder.setText(R.id.tv_time,liuCheng.getTime());
                break;
            case 3:
                viewHolder.setVisible(R.id.tv_name,false);
                viewHolder.setVisible(R.id.tv_time,false);
                break;
            case 4:
                viewHolder.setVisible(R.id.tv_name,false);
                viewHolder.setVisible(R.id.tv_time,false);
                break;
            case 5:
                viewHolder.setText(R.id.tv_name,"查验人："+liuCheng.getName());
                viewHolder.setText(R.id.tv_time,liuCheng.getTime());
                break;
            case 6:
                viewHolder.setText(R.id.tv_name,"回访人："+liuCheng.getName());
                viewHolder.setText(R.id.tv_time,liuCheng.getTime());
                break;
            default:
                break;
        }

    }
}
