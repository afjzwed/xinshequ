package com.yxld.yxchuangxin.ui.adapter.goods;

import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.yxld.yxchuangxin.R;
import com.yxld.yxchuangxin.Utils.StringUitl;
import com.yxld.yxchuangxin.data.api.API;
import com.yxld.yxchuangxin.entity.GoodsKind;

import java.util.List;

/**
 * 作者：Android on 2017/10/24
 * 邮箱：365941593@qq.com
 * 描述：
 */

public class TuiJianAdapter extends BaseQuickAdapter<GoodsKind.RowsBean.DianzhangtuijianListsBean, BaseViewHolder> {
    public TuiJianAdapter(@Nullable List<GoodsKind.RowsBean.DianzhangtuijianListsBean> data) {
        super(R.layout.item_xinping, data);
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, GoodsKind.RowsBean.DianzhangtuijianListsBean goodBean) {
        baseViewHolder.setText(R.id.name, goodBean.getShangpinMing());
        baseViewHolder.setText(R.id.price, "¥ " + StringUitl.get2xiaoshu(goodBean.getShoujia()))
                .addOnClickListener(R.id.cart);
        //        baseViewHolder.setText(R.id.count, goodBean.getSelectCount() + "");
        //        if (goodBean.getSelectCount() == 0) {
        //            baseViewHolder.setVisible(R.id.count, false);
        //        } else {
        //            baseViewHolder.setVisible(R.id.count, true);
        //        }
        ImageView imageView = baseViewHolder.getView(R.id.iv_avater);
        if (goodBean.getZhutu() != null) {
            String[] img = goodBean.getZhutu().split(",");
            Glide.with(mContext)
                    .load(API.PIC + img[0])
                    .into(imageView);
          /*  Glide.with(mContext)
                    .load(API.PIC + img[0])
                    .placeholder(R.drawable.default_bc)
                    .error(R.drawable.default_bc)
                    .crossFade()
                    .centerCrop()
                    .skipMemoryCache(true)
                    .diskCacheStrategy(DiskCacheStrategy.RESULT)
                    .into(imageView);*/
        }
    }
}
