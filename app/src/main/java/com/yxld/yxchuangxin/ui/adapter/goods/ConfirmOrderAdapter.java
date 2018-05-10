package com.yxld.yxchuangxin.ui.adapter.goods;

import android.net.Uri;
import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.yxld.yxchuangxin.R;
import com.yxld.yxchuangxin.Utils.StringUitl;
import com.yxld.yxchuangxin.data.api.API;
import com.yxld.yxchuangxin.entity.SureOrderEntity;

import java.util.List;

/**
 * @author: Yuan.Y.Q
 * @date: 2017/6/22
 * @descprition:
 */

public class ConfirmOrderAdapter extends BaseQuickAdapter<SureOrderEntity, BaseViewHolder> {
    public ConfirmOrderAdapter(@Nullable List<SureOrderEntity> data) {
        super(R.layout.item_confirm_order_product_img, data);
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, SureOrderEntity url) {
        baseViewHolder.setText(R.id.tv_cart_product_title, url.getGoodsShop());
        baseViewHolder.setText(R.id.tv_cart_product_price1, "¥ " + StringUitl.get2xiaoshu(Double.parseDouble(url.getGoodsRmb())));
        baseViewHolder.setText(R.id.tv_shuliang, "× " + url.getGoodsNum());
        Uri imgUrl  = Uri.parse(API.PIC + StringUitl.replaceEndFenHao(url.getGoodsSrc().split(",")[0]));
        ImageView iv = baseViewHolder.getView(R.id.iv_cart_product);
        Glide.with(mContext)
                .load(imgUrl)
                .placeholder(R.mipmap.sp_default)
                .into(iv);
    }
}
