package com.yxld.yxchuangxin.ui.adapter.ywh;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.yxld.yxchuangxin.R;

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

    }
}
