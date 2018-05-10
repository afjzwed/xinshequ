package com.yxld.yxchuangxin.ui.adapter.goods;

import android.graphics.Color;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.yxld.yxchuangxin.R;
import com.yxld.yxchuangxin.Utils.StringUitl;
import com.yxld.yxchuangxin.data.api.API;
import com.yxld.yxchuangxin.entity.CxwyMallOrder;
import com.yxld.yxchuangxin.entity.CxwyMallSale;
import com.yxld.yxchuangxin.entity.goods.MallNewOrder;
import com.yxld.yxchuangxin.entity.goods.MallNewOrderDetails;
import com.zhy.autolayout.AutoLinearLayout;
import com.zhy.autolayout.AutoRelativeLayout;

import java.text.DecimalFormat;
import java.util.List;

/**
 * @author: Yuan.Y.Q
 * @date: 2017/6/21
 * @descprition:
 */

public class OrderListAdapter extends BaseQuickAdapter<MallNewOrder, BaseViewHolder> {
    private OnItemClickListener mListener;

    public OrderListAdapter(@Nullable List<MallNewOrder> data) {
        super(R.layout.item_order_list, data);
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, MallNewOrder orderEntity) {
        StringBuilder sBuilder = new StringBuilder();
        String totalPrice = StringUitl.get2xiaoshu(orderEntity.getShijiJine());
        sBuilder.append("共").append(orderEntity.getShangpinNum()).append("件商品 ")
                .append("实付款¥ ").append(StringUitl.get2xiaoshu(orderEntity.getShijiJine()))
                .append(" (电子券- ¥ ").append(StringUitl.get2xiaoshu(orderEntity.getDianziquan()))
                .append(")(配送费+ ¥ ").append(StringUitl.get2xiaoshu(orderEntity.getPeisongfei())).append(")");

        baseViewHolder.setText(R.id.tv_place_order_time, "下单时间: " + orderEntity.getXiadanShijian())
                .setText(R.id.tv_order_list_desc, sBuilder.toString());

        TextView tvOrderType = baseViewHolder.getView(R.id.tv_place_order_type);
        TextView btnLeft = baseViewHolder.getView(R.id.btn_order_left);
        TextView btnMid = baseViewHolder.getView(R.id.btn_order_mid);
        TextView btnRight = baseViewHolder.getView(R.id.btn_order_right);
        AutoRelativeLayout rlToDetail = baseViewHolder.getView(R.id.rl_order_list_to_detail);
//订单状态 1待支付、2待发货、3待收货、4待评价、5已完成、6退货中、7退款中、8已退款、9已取消、
        tvOrderType.setText(orderEntity.getZhuangTaiString(orderEntity.getZhuangtai()));
        tvOrderType.setTextColor(mContext.getResources().getColor(R.color.color_ff9934));//默认颜色
        String orderStatus = orderEntity.getZhuangTaiString(orderEntity.getZhuangtai());


        if ("待支付".equals(orderStatus)) {
            btnLeft.setVisibility(View.GONE);
            btnMid.setVisibility(View.VISIBLE);

            btnRight.setBackgroundResource(R.drawable.btn_cart_order_ok);
            btnRight.setTextColor(Color.parseColor("#ffffff"));
            btnRight.setText("立即支付");

            btnMid.setBackgroundResource(R.drawable.shape_btn_bg_cancal_order_normal);
            btnMid.setTextColor(Color.parseColor("#909090"));
            btnMid.setText("取消订单");

            btnMid.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mListener != null) {
                        mListener.onCancelOrderClick(orderEntity.getBianhao() + "");
                    }
                }
            });

            btnRight.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mListener != null) {
                        mListener.onPayNowClick(orderEntity, totalPrice);
                    }
                }
            });

        } else if ("待发货".equals(orderStatus)) {
            btnLeft.setVisibility(View.GONE);
            btnMid.setVisibility(View.GONE);

            btnRight.setBackgroundResource(R.drawable.shape_btn_bg_cancal_order_normal);
            btnRight.setTextColor(Color.parseColor("#909090"));
            btnRight.setText("订单投诉");

            btnMid.setBackgroundResource(R.drawable.shape_btn_bg_cancal_order_normal);
            btnMid.setTextColor(Color.parseColor("#909090"));
            btnMid.setText("退款");


            btnRight.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mListener != null) {
                        mListener.on2ComplainActivityClick(orderEntity.getBianhao() + "");
                    }
                }
            });

            btnMid.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mListener != null) {
                        mListener.onBackMoneyClick(orderEntity.getId() + "");
                    }
                }
            });

        } else if ("待取货".equals(orderStatus)) {
            btnLeft.setVisibility(View.GONE);
            btnMid.setVisibility(View.GONE);

            btnRight.setText("订单投诉");
            btnRight.setBackgroundResource(R.drawable.shape_btn_bg_cancal_order_normal);
            btnRight.setTextColor(Color.parseColor("#909090"));

            btnRight.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mListener != null) {
                        mListener.on2ComplainActivityClick(orderEntity.getBianhao() + "");
                    }
                }
            });

        } else if ("待收货".equals(orderStatus)) {
            btnLeft.setVisibility(View.GONE);
            btnMid.setVisibility(View.GONE);
            btnRight.setBackgroundResource(R.drawable.shape_btn_bg_cancal_order_normal);
            btnRight.setTextColor(Color.parseColor("#909090"));
            btnRight.setText("订单投诉");
            btnMid.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mListener != null) {

                    }
                }
            });

            btnRight.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mListener != null) {
                        mListener.on2ComplainActivityClick(orderEntity.getBianhao() + "");
                    }
                }
            });
        } else if ("待评价".equals(orderStatus)) {
            btnLeft.setVisibility(View.VISIBLE);
            if (orderEntity.getIsShouhou()==1){
                btnMid.setVisibility(View.GONE);
            }else {
                btnMid.setVisibility(View.VISIBLE);
            }


            btnRight.setBackgroundResource(R.drawable.btn_cart_order_ok);
            btnRight.setTextColor(Color.parseColor("#ffffff"));
            btnRight.setText("立即评价");

            btnMid.setBackgroundResource(R.drawable.shape_btn_bg_cancal_order_normal);
            btnMid.setTextColor(Color.parseColor("#909090"));
            btnMid.setText("申请售后");

            btnLeft.setBackgroundResource(R.drawable.shape_btn_bg_cancal_order_normal);
            btnLeft.setTextColor(Color.parseColor("#909090"));
            btnLeft.setText("订单投诉");

            btnRight.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mListener != null) {
                        mListener.onCommentNowClick(orderEntity.getBianhao() + "", orderEntity.getOrderDetails());
                    }
                }
            });

            btnMid.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mListener != null) {
                        mListener.onBackGoodsClick(orderEntity.getId() + "", orderEntity.getOrderDetails());
                    }
                }
            });

            btnLeft.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mListener != null) {
                        mListener.on2ComplainActivityClick(orderEntity.getBianhao() + "");
                    }
                }
            });

        } else if ("退款中".equals(orderStatus) || "退货中".equals(orderStatus)) {
            tvOrderType.setTextColor(mContext.getResources().getColor(R.color.color_ff6966));
            btnLeft.setVisibility(View.GONE);
            btnMid.setVisibility(View.GONE);

            btnRight.setText("删除订单");
            btnRight.setBackgroundResource(R.drawable.shape_btn_bg_cancal_order_normal);
            btnRight.setTextColor(Color.parseColor("#909090"));

        } else if ("退款完成".equals(orderStatus) || "退货完成".equals(orderStatus) || "已完成".equals(orderStatus)) {
            if (!"已完成".equals(orderStatus)) {
                tvOrderType.setTextColor(mContext.getResources().getColor(R.color.color_ff6966));
            }
            btnLeft.setVisibility(View.GONE);
            btnMid.setVisibility(View.VISIBLE);
            btnRight.setVisibility(View.VISIBLE);

            btnRight.setBackgroundResource(R.drawable.shape_btn_bg_cancal_order_normal);
            btnRight.setTextColor(Color.parseColor("#909090"));
            btnRight.setText("订单投诉");


            btnMid.setBackgroundResource(R.drawable.shape_btn_bg_cancal_order_normal);
            btnMid.setTextColor(Color.parseColor("#909090"));
            btnMid.setText("删除订单");
            btnRight.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mListener != null) {
                        mListener.on2ComplainActivityClick(orderEntity.getBianhao() + "");
                    }
                }
            });

            btnMid.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mListener != null) {
                        mListener.onDeleteOrderClick(orderEntity.getBianhao() + "");
                    }
                }
            });

        } else if ("已取消".equals(orderStatus)) {
            btnLeft.setVisibility(View.GONE);
            btnMid.setVisibility(View.GONE);

            btnRight.setText("删除订单");
            btnRight.setBackgroundResource(R.drawable.shape_btn_bg_cancal_order_normal);
            btnRight.setTextColor(Color.parseColor("#909090"));
        }


        rlToDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mListener != null) {
                    mListener.on2DetailActivityClick(orderEntity);
                }
            }
        });


        if ("退货中".equals(orderStatus) || "退款中".equals(orderStatus) || "退款完成".equals(orderStatus)
                || "取消订单".equals(orderStatus) || "退货完成".equals(orderStatus) || "已取消".equals(orderStatus)) {
            btnRight.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mListener != null) {
                        mListener.onDeleteOrderClick(orderEntity.getBianhao() + "");
                    }
                }
            });
        }

        RecyclerView recycler = baseViewHolder.getView(R.id.recycler_order_products);
        LinearLayoutManager manager = new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false);
        recycler.setLayoutManager(manager);
        OrderListProductAdapter adapter = new OrderListProductAdapter(orderEntity.getOrderDetails());
        recycler.setAdapter(adapter);

//        adapter.setOnProductClickListener(new OnProductClickListener() {
//            @Override
//            public void onClick(MallNewOrderDetails shangPingId) {
//                if (mListener != null) {
//                    mListener.on2DetailActivityClick(shangPingId);
//                }
//            }
//        });
    }

    private String getRealPay(MallNewOrder orderEntity) {
        DecimalFormat decimalFormat = new DecimalFormat("#0.00");
        double totalPrice = 0;
        for (MallNewOrderDetails sale : orderEntity.getOrderDetails()) {
            totalPrice += sale.getShangpinZongjia();
        }
        totalPrice += orderEntity.getPeisongfei();
        double youHui = orderEntity.getDianziquan();
        return decimalFormat.format(totalPrice - youHui);
    }

    public static class OrderListProductAdapter extends BaseQuickAdapter<MallNewOrderDetails, BaseViewHolder> {

        public OrderListProductAdapter(@Nullable List<MallNewOrderDetails> data) {
            super(R.layout.item_order_list_product, data);
        }

        @Override
        protected void convert(BaseViewHolder baseViewHolder, MallNewOrderDetails orderProductEntity) {
            baseViewHolder.setText(R.id.tv_order_product_title, orderProductEntity.getShangpinMing())
                    .setText(R.id.tv_order_product_spec, "规格:" + orderProductEntity.getShangpinGuige())
                    .setText(R.id.tv_order_product_price, "¥ " + (StringUitl.get2xiaoshu(orderProductEntity.getShangpinShoujia())))
                    .setText(R.id.tv_order_product_num, "x" + orderProductEntity.getShangpinShuliang());
//                    .setTag(R.id.ll_order_product_root,"Product")
//                    .addOnClickListener(R.id.ll_order_product_root);

            ImageView icon = baseViewHolder.getView(R.id.iv_order_product);
            String url = API.PIC + StringUitl.replaceEndFenHao(orderProductEntity.getSuoluetu());
            Glide.with(mContext)
                    .load(url)
                    .into(icon);

            AutoLinearLayout rootLayout = baseViewHolder.getView(R.id.ll_order_product_root);
            rootLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mListener != null) {
                        mListener.onClick(orderProductEntity);
                    }
                }
            });

        }

        private OnProductClickListener mListener;

        public void setOnProductClickListener(OnProductClickListener listener) {
            mListener = listener;
        }
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        mListener = listener;
    }

    public interface OnItemClickListener {
        void on2DetailActivityClick(MallNewOrder orderDetail);

        void on2ComplainActivityClick(String orderId);

        void onCancelOrderClick(String orderId);

        void onPayNowClick(MallNewOrder order, String shouldPay);

        void onBackMoneyClick(String orderId);

        void onBackGoodsClick(String orderId, List<MallNewOrderDetails> sales);

        void onConfirmDeliveryClick(String orderId);//确认收货

        void onCommentNowClick(String orderId, List<MallNewOrderDetails> sales);

        void onDeleteOrderClick(String orderId);
    }

    public interface OnProductClickListener {
        void onClick(MallNewOrderDetails orderDetail);
    }
}
