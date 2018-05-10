package com.yxld.yxchuangxin.ui.adapter.goods;

import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.yxld.yxchuangxin.R;
import com.yxld.yxchuangxin.data.api.API;
import com.yxld.yxchuangxin.entity.MallClassify;

import java.util.List;

/**
 * @author: Yuan.Y.Q
 * @date: 2017/6/16
 * @descprition:
 */

public class MallNormalTypeAdapter extends BaseQuickAdapter<MallClassify.RowsBean, BaseViewHolder> {
    public MallNormalTypeAdapter(@Nullable List<MallClassify.RowsBean> data) {
        super(R.layout.item_mall_normal_types, data);
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, MallClassify.RowsBean peizhi) {
        baseViewHolder.setText(R.id.tv_mall_normal_type, peizhi.getFenleiMing())
                .setTag(R.id.ll_mall_normal_root, peizhi.getId())
                .addOnClickListener(R.id.ll_mall_normal_root);
        ImageView iv = baseViewHolder.getView(R.id.iv_mall_normal_type);
//        Uri uri = Uri.parse(API.PIC + StringUitl.replaceEndFenHao(peizhi.getMallPeizhiValue()));
        if ("所有".equals(peizhi.getFenleiMing())) {
            Glide.with(mContext)
                    .load(R.mipmap.qidai_small)
                    .into(iv);
        } else {

            Glide.with(mContext)
                    .load(API.PIC + peizhi.getFenleiTubiao())
                    .into(iv);
        }
    }
}
