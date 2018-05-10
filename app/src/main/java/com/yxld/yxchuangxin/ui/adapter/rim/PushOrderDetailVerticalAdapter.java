package com.yxld.yxchuangxin.ui.adapter.rim;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.yxld.yxchuangxin.R;
import com.yxld.yxchuangxin.entity.ShopCarList;

import java.util.List;

/**
 * Created by hu on 2017/5/5.
 * 预约篮的适配器
 */

public class PushOrderDetailVerticalAdapter extends BaseQuickAdapter<ShopCarList.ShopCarBean, BaseViewHolder> {
    public PushOrderDetailVerticalAdapter(List<ShopCarList.ShopCarBean> data) {
        super(R.layout.item_business_order_detail_vertical, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, ShopCarList.ShopCarBean item) {
//        helper.setText(R.id.tv_product_count, "X" + item.size() + "");
//
//        helper.setText(R.id.tv_product_name, item.get(0).getProductName());
//
//        helper.setText(R.id.tv_product_price, "¥" + item.size()*item.get(0).getProductPreferentialPrice());
//        ShapedImageView shapedImageView = helper.getView(R.id.siv_product);
//        Glide.with(mContext)
//                .load(item.get(0).getProductImage())
//                .diskCacheStrategy(DiskCacheStrategy.NONE)
//                .into(shapedImageView);

    }
}