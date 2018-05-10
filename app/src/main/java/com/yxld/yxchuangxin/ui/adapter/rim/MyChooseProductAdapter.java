package com.yxld.yxchuangxin.ui.adapter.rim;

import android.app.Activity;

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
 * Created by hu on 2017/5/4.
 * 预约篮的适配器
 */

public class MyChooseProductAdapter extends BaseQuickAdapter<ShopCarList.ShopCarBean, BaseViewHolder> {
    private Activity mActivity;
    public MyChooseProductAdapter(List<ShopCarList.ShopCarBean> data, Activity activity) {
        super(R.layout.item_mychoose_product_list, data);
        mActivity = activity;
    }

    @Override
    protected void convert(BaseViewHolder helper, ShopCarList.ShopCarBean item) {
        helper.setText(R.id.tv_choosed_product_name, item.getProductName());
        helper.setText(R.id.tv_product_count, item.getCartNum() + "");
        helper.setText(R.id.tv_choosed_product_price, StringUitl.get2xiaoshu(item.getProductPreferentialPrice()) + "");
        helper.addOnClickListener(R.id.tv_product_add)
                .addOnClickListener(R.id.tv_product_reduce)
                .addOnClickListener(R.id.iv_product_delet);
        ShapedImageView shapedImageView = helper.getView(R.id.iv_choosed_product);
        if (item.getProductImage().split(",").length>0){
            Glide.with(mActivity)
                    .load(API.PIC+item.getProductImage().split(",")[0])
                    .diskCacheStrategy(DiskCacheStrategy.NONE)
                    .into(shapedImageView);
        }

    }
}
