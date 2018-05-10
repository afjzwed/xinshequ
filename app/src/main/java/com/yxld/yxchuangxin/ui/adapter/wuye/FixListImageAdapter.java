package com.yxld.yxchuangxin.ui.adapter.wuye;

import android.support.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.yxld.yxchuangxin.R;
import com.yxld.yxchuangxin.view.RoundImageView;

import java.util.List;

import static com.yxld.yxchuangxin.data.api.API.PIC;

/**
 * 作者：hu on 2017/6/27
 * 邮箱：365941593@qq.com
 * 描述：
 */

public class FixListImageAdapter extends BaseQuickAdapter<String, BaseViewHolder> {

    public FixListImageAdapter(@Nullable List<String> data) {
        super(R.layout.item_image, data);
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, String s) {
        RoundImageView imageView = baseViewHolder.getView(R.id.iv_image);
        Glide.with(mContext)
                .load(PIC + s)
                .into(imageView);
    }
}
