package com.yxld.yxchuangxin.ui.adapter.rim;

import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.yxld.yxchuangxin.R;
import com.yxld.yxchuangxin.data.api.API;
import com.yxld.yxchuangxin.entity.SJOrder;

import java.util.List;

/**
 * 作者：wwx on 2017/5/4 0002 10:22
 * 邮箱：wanwenxiu0709@foxmail.com
 * 描述：欣周边 订单列表 适配器
 */

public class RimOrderListAdapter extends BaseQuickAdapter<SJOrder, BaseViewHolder> {

    //投诉
    private int ORDERTOUSU = 1;
    //动态跟踪
    private int ORDERDYNAMIC = 2;
    //取消订单
    private int ORDERCANCEL = 3;
    //立即支付
    private int ORDERPAY = 4;
    //确认送达
    private int ORDERSURE = 5;
    //立即评价
    private int ORDERCOMMENT = 6;
    //删除订单
    private int ORDERDELETE = 7;
    //查看评价
    private int ORDERCOMMENTCHECK = 8;

    private ImageView shoplogo;

    public RimOrderListAdapter(List<SJOrder> data) {
        super(R.layout.rim_order_ing_item_layout, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, SJOrder item) {
//        Log.d("geek", "convert: item" + item.toString());
        shoplogo = helper.getView(R.id.img_story_icon);

        Glide.with(mContext)
                .load(API.PIC + item.getBusinessLogo())
//                .placeholder(R.drawable.default_recommed_icon)
//                .error(R.drawable.default_recommed_icon)
                .crossFade()//渐入渐出
                .centerCrop()
                .skipMemoryCache(true)
                .diskCacheStrategy(DiskCacheStrategy.RESULT)
                .into(shoplogo);
        //        订单状态（1-待付款；2-待接单；3-待发货；4-待确认；5-待评价；6-订单完成；7-待回复；8-已拒单；9-取消订单；10-待取件；11-服务中；12-送还中；13
// -退款中；14-服务中）
//        订单状态（1-待付款；2-待接单；3-待发货；4-待确认；5-待评价；6-订单完成；7-待回复；8-已拒单；9-取消订单；10-待取件；11-处理中；12-送还中；13
// -退款中；14-服务中）
        //订单状态（1-待付款；2-待接单；3-待发货；4-待确认；5-待评价；6-订单完成；7-待回复；8-已拒单；9-取消订单；10-待取件；11-清洗中；12-送还中；13-退款中；）
        String orderState = "";
        switch (item.getOrderStatus()) {
            case 1:
                orderState = "待支付";
                helper.setVisible(R.id.btn_small, false)
                        .setVisible(R.id.btn_big_gray_1, false)
                        .setText(R.id.btn_big_gray_2, "取消订单").setVisible(R.id.btn_big_gray_2,
                        true).addOnClickListener(R.id.btn_big_gray_2).setTag(R.id.btn_big_gray_2,
                        ORDERCANCEL)
                        .setText(R.id.btn_big_blue, "立即支付").setVisible(R.id.btn_big_blue, true)
                        .addOnClickListener(R.id.btn_big_blue).setTag(R.id.btn_big_blue, ORDERPAY);
                break;
            case 2:
                orderState = "待接单";
                helper.setText(R.id.btn_small, "投诉").setVisible(R.id.btn_small, true)
                        .addOnClickListener(R.id.btn_small).setTag(R.id.btn_small, ORDERTOUSU)
                        .setVisible(R.id.btn_big_gray_1, false)
                        .setText(R.id.btn_big_gray_2, "取消订单").setVisible(R.id.btn_big_gray_2,
                        true).addOnClickListener(R.id.btn_big_gray_2).setTag(R.id.btn_big_gray_2,
                        ORDERCANCEL)
                        .setVisible(R.id.btn_big_blue, false);
                break;
            case 3:
                orderState = "待发货";
                helper.setText(R.id.btn_small, "投诉").setVisible(R.id.btn_small, true)
                        .addOnClickListener(R.id.btn_small).setTag(R.id.btn_small, ORDERTOUSU)
                        .setText(R.id.btn_big_gray_1, "动态跟踪").setVisible(R.id.btn_big_gray_1,
                        true).addOnClickListener(R.id.btn_big_gray_1).setTag(R.id.btn_big_gray_1,
                        ORDERDYNAMIC)
                        .setVisible(R.id.btn_big_gray_2, false)
                        .setVisible(R.id.btn_big_blue, false);
                break;
            case 4:
                orderState = "待确认";
                helper.setText(R.id.btn_small, "投诉").setVisible(R.id.btn_small, true)
                        .addOnClickListener(R.id.btn_small).setTag(R.id.btn_small, ORDERTOUSU)
                        .setText(R.id.btn_big_gray_1, "动态跟踪").setVisible(R.id.btn_big_gray_1,
                        true).addOnClickListener(R.id.btn_big_gray_1).setTag(R.id.btn_big_gray_1,
                        ORDERDYNAMIC)
                        .setText(R.id.btn_big_blue, "确认送达").setVisible(R.id.btn_big_blue, true)
                        .addOnClickListener(R.id.btn_big_blue).setTag(R.id.btn_big_blue, ORDERSURE)
                        .setVisible(R.id.btn_big_gray_2, false);
                break;
            case 5:
                orderState = "待评价";
                helper.setText(R.id.btn_small, "投诉").setVisible(R.id.btn_small, true)
                        .addOnClickListener(R.id.btn_small).setTag(R.id.btn_small, ORDERTOUSU)
                        .setText(R.id.btn_big_gray_1, "动态跟踪").setVisible(R.id.btn_big_gray_1,
                        true).addOnClickListener(R.id.btn_big_gray_1).setTag(R.id.btn_big_gray_1,
                        ORDERDYNAMIC)
                        .setText(R.id.btn_big_blue, "立即评价").setVisible(R.id.btn_big_blue, true)
                        .addOnClickListener(R.id.btn_big_blue).setTag(R.id.btn_big_blue,
                        ORDERCOMMENT)
                        .setVisible(R.id.btn_big_gray_2, false);
                break;
            case 6:
                orderState = "已完成";
                helper.setText(R.id.btn_small, "投诉").setVisible(R.id.btn_small, true)
                        .addOnClickListener(R.id.btn_small).setTag(R.id.btn_small, ORDERTOUSU)
                        .setText(R.id.btn_big_gray_1, "动态跟踪").setVisible(R.id.btn_big_gray_1,
                        true).addOnClickListener(R.id.btn_big_gray_1).setTag(R.id.btn_big_gray_1,
                        ORDERDYNAMIC)
                        .setText(R.id.btn_big_blue, "查看评价").setVisible(R.id.btn_big_blue, true)
                        .addOnClickListener(R.id.btn_big_blue).setTag(R.id.btn_big_blue,
                        ORDERCOMMENTCHECK)
                        .setVisible(R.id.btn_big_gray_2, false);
                break;
            case 7:
                orderState = "待回复";
                helper.setText(R.id.btn_small, "投诉").setVisible(R.id.btn_small, true)
                        .addOnClickListener(R.id.btn_small).setTag(R.id.btn_small, ORDERTOUSU)
                        .setText(R.id.btn_big_gray_1, "动态跟踪").setVisible(R.id.btn_big_gray_1,
                        true).addOnClickListener(R.id.btn_big_gray_1).setTag(R.id.btn_big_gray_1,
                        ORDERDYNAMIC)
                        .setVisible(R.id.btn_big_gray_2, false)
                        .setVisible(R.id.btn_big_blue, false);
                break;
            case 8:
                orderState = "已拒单";
//                helper.setText(R.id.btn_small, "投诉").setVisible(R.id.btn_small, true)
//                        .addOnClickListener(R.id.btn_small).setTag(R.id.btn_small, ORDERTOUSU)
//                        .setText(R.id.btn_big_gray_1, "动态跟踪").setVisible(R.id.btn_big_gray_1,
//                        true).addOnClickListener(R.id.btn_big_gray_1).setTag(R.id.btn_big_gray_1,
//                        ORDERDYNAMIC)
//                        .setVisible(R.id.btn_big_gray_2, false)
//                        .setVisible(R.id.btn_big_blue, false);
                // TODO: 2018/2/28 测试通知暂时将动态跟踪注释
                helper.setText(R.id.btn_small, "投诉").setVisible(R.id.btn_small, true)
                        .addOnClickListener(R.id.btn_small).setTag(R.id.btn_small, ORDERTOUSU)
                        .setVisible(R.id.btn_big_gray_1,false)
                        .setVisible(R.id.btn_big_gray_2, false)
                        .setVisible(R.id.btn_big_blue, false);
                break;
            case 9:
                orderState = "已取消";
                helper.setText(R.id.btn_small, "投诉").setVisible(R.id.btn_small, true)
                        .addOnClickListener(R.id.btn_small).setTag(R.id.btn_small, ORDERTOUSU)
                        .setVisible(R.id.btn_big_gray_1, false)
                        .setText(R.id.btn_big_gray_2, "删除订单").setVisible(R.id.btn_big_gray_2,
                        true).addOnClickListener(R.id.btn_big_gray_2).setTag(R.id.btn_big_gray_2,
                        ORDERDELETE)
                        .setVisible(R.id.btn_big_blue, false);
                break;
            case 10:
                orderState = "待取件";
                helper.setText(R.id.btn_small, "投诉").setVisible(R.id.btn_small, true)
                        .addOnClickListener(R.id.btn_small).setTag(R.id.btn_small, ORDERTOUSU)
                        .setText(R.id.btn_big_gray_1, "动态跟踪").setVisible(R.id.btn_big_gray_1,
                        true).addOnClickListener(R.id.btn_big_gray_1).setTag(R.id.btn_big_gray_1,
                        ORDERDYNAMIC)
                        .setVisible(R.id.btn_big_gray_2, false)
                        .setVisible(R.id.btn_big_blue, false);
                break;
            case 11:
                orderState = "服务中";
                helper.setText(R.id.btn_small, "投诉").setVisible(R.id.btn_small, true)
                        .addOnClickListener(R.id.btn_small).setTag(R.id.btn_small, ORDERTOUSU)
                        .setText(R.id.btn_big_gray_1, "动态跟踪").setVisible(R.id.btn_big_gray_1,
                        true).addOnClickListener(R.id.btn_big_gray_1).setTag(R.id.btn_big_gray_1,
                        ORDERDYNAMIC)
                        .setVisible(R.id.btn_big_gray_2, false)
                        .setVisible(R.id.btn_big_blue, false);
                break;
            case 12:
                orderState = "送还中";
                helper.setText(R.id.btn_small, "投诉").setVisible(R.id.btn_small, true)
                        .addOnClickListener(R.id.btn_small).setTag(R.id.btn_small, ORDERTOUSU)
                        .setText(R.id.btn_big_gray_1, "动态跟踪").setVisible(R.id.btn_big_gray_1,
                        true).addOnClickListener(R.id.btn_big_gray_1).setTag(R.id.btn_big_gray_1,
                        ORDERDYNAMIC)
                        .setText(R.id.btn_big_blue, "确认送达").setVisible(R.id.btn_big_blue, true)
                        .addOnClickListener(R.id.btn_big_blue).setTag(R.id.btn_big_blue, ORDERSURE)
                        .setVisible(R.id.btn_big_gray_2, false);
                break;
            case 13:
                orderState = "退款中";
                helper.setText(R.id.btn_small, "投诉").setVisible(R.id.btn_small, true)
                        .addOnClickListener(R.id.btn_small).setTag(R.id.btn_small, ORDERTOUSU)
                        .setText(R.id.btn_big_gray_1, "动态跟踪").setVisible(R.id.btn_big_gray_1,
                        true).addOnClickListener(R.id.btn_big_gray_1).setTag(R.id.btn_big_gray_1,
                        ORDERDYNAMIC)
                        .setVisible(R.id.btn_big_gray_2, false)
                        .setVisible(R.id.btn_big_blue, false);
            case 14:
                orderState = "服务中";
                helper.setText(R.id.btn_small, "投诉").setVisible(R.id.btn_small, true)
                        .addOnClickListener(R.id.btn_small).setTag(R.id.btn_small, ORDERTOUSU)
                        .setText(R.id.btn_big_gray_1, "动态跟踪").setVisible(R.id.btn_big_gray_1,
                        true).addOnClickListener(R.id.btn_big_gray_1).setTag(R.id.btn_big_gray_1,
                        ORDERDYNAMIC)
                        .setVisible(R.id.btn_big_gray_2, false)
                        .setVisible(R.id.btn_big_blue, false);
            default:
                break;
        }

        helper.setText(R.id.tv_story_name, item.getOrderBusinessName())
                .setText(R.id.tv_order_state, orderState)
                .setText(R.id.tv_order_bianhao, item.getOrderNumber())
                .setText(R.id.tv_order_time, item.getOrderOrderTime())
                .setText(R.id.tv_order_totalmoney, "¥ " + String.valueOf(item.getOrderFactMoney()));
    }
}
