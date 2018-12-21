package com.yxld.yxchuangxin.ui.adapter.ywh;


import android.text.TextUtils;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.yxld.yxchuangxin.R;
import com.yxld.yxchuangxin.data.api.API;
import com.yxld.yxchuangxin.entity.YwhHouse;


/**
 * Created by Administrator on 2018/11/9.
 */

public class PqrzAdapter extends BaseQuickAdapter<YwhHouse.DataBean, BaseViewHolder> {
    public PqrzAdapter() {
        super(R.layout.item_pqrz);
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, YwhHouse.DataBean s) {
        baseViewHolder.setText(R.id.tv_title, "房屋信息：" + s.getBuilding() + "栋" + s.getUnit() + "单元" + s.getRoomNo() + "房号")
                .addOnClickListener(R.id.img_fcz).addOnClickListener(R.id.img_tx);
        ImageView view = baseViewHolder.getView(R.id.img_fcz);
        ImageView view1 = baseViewHolder.getView(R.id.img_tx);
        if (!TextUtils.isEmpty(s.getDeedImage())) {
            Glide.with(mContext)
                    .load(s.getDeedImage())
                    .skipMemoryCache(true)
                    .diskCacheStrategy(DiskCacheStrategy.NONE)
                    .into(view);
        }
        if (!TextUtils.isEmpty(s.getPaperWork())) {
            Glide.with(mContext)
                    .load(s.getPaperWork())
                    .skipMemoryCache(true)
                    .diskCacheStrategy(DiskCacheStrategy.NONE)
                    .into(view1);
        }

        baseViewHolder.addOnClickListener(R.id.tv_shili1);
        baseViewHolder.addOnClickListener(R.id.tv_shili2);
    }
}
