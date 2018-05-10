package com.yxld.yxchuangxin.ui.adapter.rim;

import android.content.Context;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.yxld.yxchuangxin.R;
import com.yxld.yxchuangxin.entity.SJOrderStatus;

import java.util.List;

/**
 * 作者：wwx on 2017/5/8 0002 16:48
 * 邮箱：wanwenxiu0709@foxmail.com
 * 描述：欣周边 动态跟踪 适配器
 */

public class OrderDynamicAdapter extends BaseQuickAdapter<SJOrderStatus,BaseViewHolder> {
    private Context mcontext;
    private View view_circle;
    private TextView tvOrder;

    public OrderDynamicAdapter(Context context, List<SJOrderStatus> data) {
        super(R.layout.rim_dynamic_order_item,data);
        mcontext = context;
    }

    @Override
    protected void convert(BaseViewHolder helper, SJOrderStatus item) {
        view_circle = helper.getView(R.id.view_circle);
        tvOrder = helper.getView(R.id.tv_order_state);
        if(helper.getAdapterPosition() == 0){
            view_circle.setBackgroundResource(R.drawable.circle_blue);
            tvOrder.setTextColor(mcontext.getResources().getColor(R.color.color_0079C2));
        }else{
            view_circle.setBackgroundResource(R.drawable.circle_e6e6e6);
            tvOrder.setTextColor(mcontext.getResources().getColor(R.color.color_646464));
        }
        helper.setText(R.id.tv_order_time, item.getOrderStatusChangeTime());

        //订单状态（1-待付款；2-待接单；3-待发货；4-待确认；5-待评价；6-订单完成；7-待回复；8-已拒单；9-取消订单；10-待取件；11-清洗中；12-送还中；13-退款中；）
        switch (item.getOrderStatusStatus()){
            case 1:
                helper.setText(R.id.tv_order_state,"订单状态：待付款")
                .setText(R.id.tv_order_destail,"工作人员"+item.getOrderStatusOperatorName()+"已上门取件验货核价");
                break;
            case 2:
                helper.setText(R.id.tv_order_state,"订单状态：待接单")
                        .setText(R.id.tv_order_destail,"请保持电话畅通，我们会尽快为您服务");
                break;
            case 3:
                helper.setText(R.id.tv_order_state,"订单状态：待发货")
                        .setText(R.id.tv_order_destail,"请保持电话畅通，我们会尽快为您服务");
                break;
            case 4:
                helper.setText(R.id.tv_order_state,"订单状态：待确认")
                        .setText(R.id.tv_order_destail,"工作人员已送达");
                break;
            case 5:
                helper.setText(R.id.tv_order_state,"订单状态：待评价")
                        .setText(R.id.tv_order_destail,"您的评价对其它人很有帮助哦");
                break;
            case 6:
                helper.setText(R.id.tv_order_state,"订单状态：已完成")
                        .setText(R.id.tv_order_destail,"感谢您的支持，期待下次再为您服务");
                break;
            case 7:
                helper.setText(R.id.tv_order_state,"订单状态：待回复")
                        .setText(R.id.tv_order_destail,"感谢您的支持，期待下次再为您服务");
                break;
            case 8:
                helper.setText(R.id.tv_order_state,"订单状态：已拒单")
                        .setText(R.id.tv_order_destail,"感谢您的支持，期待下次再为您服务");
                break;
            case 9:
                helper.setText(R.id.tv_order_state,"订单状态：已取消订单")
                        .setText(R.id.tv_order_destail,"感谢您的支持，期待下次再为您服务");
                break;
            case 10:
                helper.setText(R.id.tv_order_state,"订单状态：待取件");

                String text = "工作人员"+item.getOrderStatusOperatorName()+"已接单 电话:"+ item.getOrderStatusOperatorPhone();

                int index = text.indexOf(":")+1;
                Log.d("geek", text+"convert: "+index);
                TextView tvDestail = helper.getView(R.id.tv_order_destail);

                SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(text);
                ForegroundColorSpan bcSpan = new ForegroundColorSpan(mcontext.getResources().getColor(R.color.color_bcbcbc));
                ForegroundColorSpan blueSpan = new ForegroundColorSpan(mcontext.getResources().getColor(R.color.color_0079C2));
                spannableStringBuilder.setSpan(bcSpan, 0, index, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                spannableStringBuilder.setSpan(blueSpan, index, text.length(), Spannable.SPAN_INCLUSIVE_INCLUSIVE);
                tvDestail.setText(spannableStringBuilder);
                break;
            case 11:
                helper.setText(R.id.tv_order_state,"订单状态：服务中")
                        .setText(R.id.tv_order_destail,"正在为您服务中");
                break;
            case 12:
                helper.setText(R.id.tv_order_state,"订单状态：送还中")
                        .setText(R.id.tv_order_destail,"工作人员已在送回途中");
                break;
            case 13:
                helper.setText(R.id.tv_order_state,"订单状态：退款中")
                        .setText(R.id.tv_order_destail,"感谢您的支持，期待下次再为您服务");
                break;
            default:
                break;
        }

    }

}
