package com.yxld.yxchuangxin.ui.adapter.rim;

import android.util.Log;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.yxld.yxchuangxin.R;
import com.yxld.yxchuangxin.Utils.StringUitl;
import com.yxld.yxchuangxin.data.api.API;
import com.yxld.yxchuangxin.db.green.SPInfo;
import com.yxld.yxchuangxin.entity.CxwyProductInfo;
import com.yxld.yxchuangxin.entity.ShopCarList;
import com.yxld.yxchuangxin.view.ShapedImageView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hu on 2017/5/5.
 * 预约篮的适配器
 */

public class PushProductHorizenAdapter extends BaseQuickAdapter<ShopCarList.ShopCarBean, BaseViewHolder> {
    public PushProductHorizenAdapter(List<ShopCarList.ShopCarBean> data) {
        super(R.layout.item_business_push_product_horizen_list, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, ShopCarList.ShopCarBean item) {
        Log.e("wh", "item " + item.toString());

        helper.setText(R.id.product_count, "¥ " + StringUitl.get2xiaoshu(item.getProductPreferentialPrice()) + "");
        helper.setText(R.id.product_name, item.getProductName()+"");
        helper.setText(R.id.product_num,"x " + item.getCartNum() + "");
        ShapedImageView shapedImageView = helper.getView(R.id.siv_product);
        String[] split = item.getProductImage().split(",");
        Glide.with(mContext)
                .load(API.PIC + split[0])
                .into(shapedImageView);
    }
}