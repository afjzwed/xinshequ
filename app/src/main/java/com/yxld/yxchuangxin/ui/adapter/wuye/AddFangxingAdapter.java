package com.yxld.yxchuangxin.ui.adapter.wuye;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.yxld.yxchuangxin.R;
import com.yxld.yxchuangxin.entity.AddFangxing;

import java.util.List;

/**
 * 作者：hu on 2017/6/13
 * 邮箱：365941593@qq.com
 * 描述：
 */

public class AddFangxingAdapter extends BaseQuickAdapter<AddFangxing, BaseViewHolder> {
    public AddFangxingAdapter(List<AddFangxing> data) {
        super(R.layout.item_add_fangxing, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, AddFangxing item) {
        helper.setText(R.id.tv_name, item.getName() + "*" + item.getCount())
                .setText(R.id.tv_remark, item.getReMark())
                .addOnClickListener(R.id.iv_reset);
    }
}
