package com.yxld.yxchuangxin.ui.adapter.camera;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.yxld.yxchuangxin.R;

import java.util.List;

/**
 * 作者：Android on 2017/9/5
 * 邮箱：365941593@qq.com
 * 描述：
 */

public class WeekAdapter extends BaseQuickAdapter<String, BaseViewHolder> {

    public WeekAdapter(@Nullable List<String> data) {
        super(R.layout.week_item, data);
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, String baseBack) {
        baseViewHolder.setText(R.id.tv_xingqi,baseBack);
    }
}
