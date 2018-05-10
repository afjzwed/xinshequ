package com.yxld.yxchuangxin.ui.adapter.goods;

import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.yxld.yxchuangxin.R;
import com.yxld.yxchuangxin.Utils.StringUitl;
import com.yxld.yxchuangxin.data.api.API;
import com.yxld.yxchuangxin.entity.goods.MallNewProduct;

import java.util.List;

/**
 * @author: Yuan.Y.Q
 * @date: 2017/6/21
 * @descprition:
 */

public class MallGoodsList2Adapter extends BaseQuickAdapter<MallNewProduct, BaseViewHolder> {
    public MallGoodsList2Adapter(@Nullable List<MallNewProduct> data) {
        super(R.layout.item_goods_fenlei, data);
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, MallNewProduct dataBean) {

        baseViewHolder.setText(R.id.tv_mall_goods_price, "¥ " + StringUitl.get2xiaoshu(dataBean.getShoujia()))
                .setText(R.id.tv_mall_goods_stock_num, "库存" + dataBean.getKuncun())
                .setText(R.id.tv_mall_goods_product_title, dataBean.getShangpinMing())
                .setTag(R.id.root_layout, "Detail")
                .addOnClickListener(R.id.root_layout)
                .setTag(R.id.iv_mall_goods_cart, "Add2ShopCart")
                .addOnClickListener(R.id.iv_mall_goods_cart);
        String s = StringUitl.replaceEndFenHao(dataBean.getZhutu());
        ImageView iv = baseViewHolder.getView(R.id.iv_mall_goods_product);
        Glide.with(mContext)
                .load(API.PIC + s)
                .into(iv);

    }
}
