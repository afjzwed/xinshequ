package com.yxld.yxchuangxin.ui.adapter.goods;

import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.yxld.yxchuangxin.R;
import com.yxld.yxchuangxin.data.api.API;
import com.yxld.yxchuangxin.entity.CxwyMallProduct;
import com.yxld.yxchuangxin.entity.CxwyProductInfo;

import java.util.List;

/**
 * @author: Yuan.Y.Q
 * @date: 2017/6/21
 * @descprition:
 */

public class MallGoodsListAdapter extends BaseQuickAdapter<CxwyMallProduct, BaseViewHolder> {
    public MallGoodsListAdapter(@Nullable List<CxwyMallProduct> data) {
        super(R.layout.item_mall_goods_product,data);
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, CxwyMallProduct dataBean) {

        baseViewHolder.setText(R.id.tv_mall_goods_price,"¥ "+dataBean.getShangpinRmb())
                .setText(R.id.tv_mall_goods_stock_num,"库存"+dataBean.getShangpinNum())
                .setText(R.id.tv_mall_goods_product_title,dataBean.getShangpinShangpName())
                .setTag(R.id.root_layout,"Detail")
                .addOnClickListener(R.id.root_layout)
                .setTag(R.id.iv_mall_goods_cart,"Add2ShopCart")
                .addOnClickListener(R.id.iv_mall_goods_cart);

        ImageView iv = baseViewHolder.getView(R.id.iv_mall_goods_product);
        Glide.with(mContext)
                .load(API.PIC + dataBean.getShangpinImgSrc1().split(";")[0] +"?imageView2/0/w/150/h/150")
                .into(iv);

    }
}
