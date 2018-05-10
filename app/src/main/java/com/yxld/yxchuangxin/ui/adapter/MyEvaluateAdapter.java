package com.yxld.yxchuangxin.ui.adapter;

import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.yxld.yxchuangxin.R;
import com.yxld.yxchuangxin.Utils.StringUitl;
import com.yxld.yxchuangxin.data.api.API;
import com.yxld.yxchuangxin.entity.MyAllComment;

import java.util.List;

/**
 * 作者：Android on 2017/10/23
 * 邮箱：365941593@qq.com
 * 描述：
 */

public class MyEvaluateAdapter extends BaseQuickAdapter<MyAllComment.DataBean, BaseViewHolder> {

    public MyEvaluateAdapter(@Nullable List<MyAllComment.DataBean> data) {
        super(R.layout.item_my_evaluate, data);
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, MyAllComment.DataBean entity) {
        baseViewHolder.setText(R.id.content, entity.getPingjiaNeirong());
        baseViewHolder.setText(R.id.tv_name, entity.getShangpinMing());
        baseViewHolder.setText(R.id.tv_price, "¥ "+StringUitl.get2xiaoshu(entity.getJiage())+"");
        ImageView imageView = baseViewHolder.getView(R.id.iv_avater);
        if (entity.getShangpinTu() != null) {
            Glide.with(mContext)
                    .load(API.PIC + entity.getShangpinTu())
                    .into(imageView);
        } else {
            Glide.with(mContext)
                    .load(R.mipmap.qidai_small)
                    .into(imageView);
        }
        if (entity.getHuifu() != null && !entity.getHuifu().equals("")) {
            baseViewHolder.setText(R.id.shangjia_content, "商家回复：" + entity.getHuifu());
            baseViewHolder.setVisible(R.id.shangjia_content, true);
        } else {
            baseViewHolder.setVisible(R.id.shangjia_content, false);
        }
    }
}
