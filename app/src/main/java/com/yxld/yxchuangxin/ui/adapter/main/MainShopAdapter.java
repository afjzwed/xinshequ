package com.yxld.yxchuangxin.ui.adapter.main;

import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.yxld.yxchuangxin.R;
import com.yxld.yxchuangxin.entity.MallClassify;

import java.util.List;

/**
 * 作者：hu on 2017/6/2
 * 邮箱：365941593@qq.com
 * 描述：
 */

public class MainShopAdapter extends BaseQuickAdapter<MallClassify.RowsBean, BaseViewHolder> {

    public MainShopAdapter(List<MallClassify.RowsBean> data) {
        super(R.layout.adapter_main_shop, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, MallClassify.RowsBean item) {
        helper.setText(R.id.tv_shop_name, item.getFenleiMing());
        ImageView imageView = helper.getView(R.id.iv_shop);
        switch (helper.getLayoutPosition()) {
            case 0:
                helper.setText(R.id.tv_shop_sale, "满199减20");
                Glide.with(mContext)
                        .load(R.mipmap.main_activity_liangyou)
                        .into(imageView);
                break;
            case 1:
                helper.setText(R.id.tv_shop_sale, "满100减10");
                Glide.with(mContext)
                        .load(R.mipmap.main_activity_lingshi)
                        .into(imageView);
                break;
            case 2:
                helper.setText(R.id.tv_shop_sale, "满199减10");
                Glide.with(mContext)
                        .load(R.mipmap.main_activity_riyong)
                        .into(imageView);
                break;
            case 3:
                helper.setText(R.id.tv_shop_sale, "满299减10");
                Glide.with(mContext)
                        .load(R.mipmap.main_activity_baihuo)
                        .into(imageView);
                break;
        }
    }
}
