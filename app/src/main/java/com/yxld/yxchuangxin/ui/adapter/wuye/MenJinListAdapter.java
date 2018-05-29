package com.yxld.yxchuangxin.ui.adapter.wuye;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.yxld.yxchuangxin.R;
import com.yxld.yxchuangxin.entity.DoorInfo;

import java.util.List;

/**
 * @author xlei
 * @Date 2018/5/26.
 */

public class MenJinListAdapter extends BaseQuickAdapter<DoorInfo.DoorInfoBean, BaseViewHolder> {
    public MenJinListAdapter(@Nullable List<DoorInfo.DoorInfoBean> data) {
        super(R.layout.item_menjin_list, data);
    }

    @Override
    protected void convert(BaseViewHolder viewHolder, DoorInfo.DoorInfoBean s) {
        viewHolder.setText(R.id.tv_name, s.getName());
        viewHolder.setText(R.id.tv_type, s.getType().equals("0") ? "大门" : "单元门");
        viewHolder.setText(R.id.tv_mima, s.getLixian_mima());
        viewHolder.addOnClickListener(R.id.tv_open);
    }
}
