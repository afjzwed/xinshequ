package com.yxld.yxchuangxin.ui.adapter.ywh;

import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.yxld.yxchuangxin.R;
import com.yxld.yxchuangxin.data.api.API;

import java.util.List;

/**
 * Created by Administrator on 2018/11/9.
 */

public class ImgAdapter extends BaseQuickAdapter<String,BaseViewHolder> {
    public ImgAdapter(@Nullable List<String> data) {
        super(R.layout.item_img,data);
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, String s) {
        ImageView view = baseViewHolder.getView(R.id.img);
        Glide.with(mContext).load(API.ywh_pic + s).into(view);
    }
}
