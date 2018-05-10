package com.yxld.yxchuangxin.ui.adapter.goods;

import android.graphics.Color;
import android.support.annotation.Nullable;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.socks.library.KLog;
import com.yxld.yxchuangxin.R;
import com.yxld.yxchuangxin.contain.Contains;
import com.yxld.yxchuangxin.entity.CxwyMallAdd;

import java.util.List;

/**
 * 作者：hu on 2017/6/22
 * 邮箱：365941593@qq.com
 * 描述：
 */

public class ReceiveAddressAdapter extends BaseQuickAdapter<CxwyMallAdd, BaseViewHolder> {
    public ReceiveAddressAdapter(@Nullable List<CxwyMallAdd> data) {
        super(R.layout.item_revive_address, data);
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, CxwyMallAdd back) {
        baseViewHolder.setText(R.id.tv_address_name, back.getAddName() + "  " + back.getAddTel())
                .setText(R.id.tv_address_list_address, back.getAddAdd())
                .addOnClickListener(R.id.ll_name_and_address).setTag(R.id.ll_name_and_address,"Root")
                .addOnClickListener(R.id.tv_address_default).setTag(R.id.tv_address_default,"Default")
                .addOnClickListener(R.id.tv_edit).setTag(R.id.tv_edit,"Edit")
                .addOnClickListener(R.id.tv_delet).setTag(R.id.tv_delet,"Delete");

        TextView tvDefault = baseViewHolder.getView(R.id.tv_address_default);
        if (back.getAddStatus()==0) {
            tvDefault.setSelected(true);
            tvDefault.setTextColor(Color.WHITE);
        } else {
            tvDefault.setSelected(false);
            tvDefault.setTextColor(mContext.getResources().getColor(R.color.color_646464));
        }
    }

    public void OnOtherDefaultClicked(int position){
        for (int i = 0 ; i < mData.size() ;i++) {
            CxwyMallAdd entity = mData.get(i);
            if (entity.getAddStatus()==0 && i == position) {
                return;
            } else if (entity.getAddStatus()==0) {
                entity.setAddStatus(1);
                notifyItemChanged(i);
            } else if (i == position) {
                entity.setAddStatus(0);
                notifyItemChanged(i);
                KLog.i("默认地址修改成功");
                Contains.defuleAddress = entity;
            }
        }
    }
}
