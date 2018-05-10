package com.yxld.yxchuangxin.ui.adapter.rim;


import android.graphics.Color;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.yxld.yxchuangxin.R;
import com.yxld.yxchuangxin.entity.CxwyBusinessInfo;

import java.util.List;

/**
 * @author xlei
 * @Date 2017/12/13.
 */

public class RimGoodTitleAdapter extends BaseQuickAdapter<CxwyBusinessInfo.DataBean.ProductClassifyBean,
        BaseViewHolder> {


    public RimGoodTitleAdapter(@Nullable List<CxwyBusinessInfo.DataBean.ProductClassifyBean> data) {
        super(R.layout.item_rim_good, data);
    }

    @Override
    protected void convert(BaseViewHolder viewHolder, CxwyBusinessInfo.DataBean.ProductClassifyBean
            productClassifyBean) {
        viewHolder.setText(R.id.tv_title, productClassifyBean.getClassifyName());
        TextView tv = viewHolder.getView(R.id.tv_title);
        if (productClassifyBean.isSelect()) {
            tv.setTextColor(mContext.getResources().getColor(R.color.color_main_color_1));
        } else {
            tv.setTextColor(mContext.getResources().getColor(R.color.background_dark));
        }
    }
}
