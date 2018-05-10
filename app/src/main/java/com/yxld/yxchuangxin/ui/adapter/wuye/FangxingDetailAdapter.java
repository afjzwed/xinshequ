package com.yxld.yxchuangxin.ui.adapter.wuye;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.yxld.yxchuangxin.R;
import com.yxld.yxchuangxin.entity.AccreditDetail;

import java.util.List;

/**
 * 作者：hu on 2017/6/14
 * 邮箱：365941593@qq.com
 * 描述：
 */

public class FangxingDetailAdapter extends BaseQuickAdapter<AccreditDetail.DataBean, BaseViewHolder> {


    public FangxingDetailAdapter(@Nullable List<AccreditDetail.DataBean> data) {
        super(R.layout.item_fangxing_detail, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, AccreditDetail.DataBean item) {
        helper.setText(R.id.tv_name, item.getGoodsName())
                .setText(R.id.tv_count, item.getGoodsNum() + "")
                .setText(R.id.tv_desc, item.getGoodsDesc());
    }
}
