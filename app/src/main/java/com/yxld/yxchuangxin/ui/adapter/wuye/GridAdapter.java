package com.yxld.yxchuangxin.ui.adapter.wuye;

import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.yxld.yxchuangxin.R;
import com.yxld.yxchuangxin.ui.activity.index.util.ImageItem;

import java.util.ArrayList;

/**
 * 作者：hu on 2017/6/15
 * 邮箱：365941593@qq.com
 * 描述：
 */
public class GridAdapter extends BaseQuickAdapter<ImageItem, BaseViewHolder> {

    int size;

    public GridAdapter(@Nullable ArrayList<ImageItem> data) {
        super(R.layout.item_fix, data);
        size = data.size();
    }

    @Override
    protected void convert(BaseViewHolder helper, ImageItem item) {
        if (item.isSelected()) {
            helper.setImageBitmap(R.id.iv_content, item.getBitmap())
                    .addOnClickListener(R.id.iv_delet)
                    .setVisible(R.id.iv_delet, true);
        } else {
            ImageView imageView = helper.getView(R.id.iv_content);
            Glide.with(mContext)
                    .load(R.mipmap.icon_addpic_unfocused)
                    .into(imageView);
            helper.setVisible(R.id.iv_delet, false);
        }

    }

}
