package com.yxld.yxchuangxin.ui.adapter.wuye;

import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.yxld.yxchuangxin.R;
import com.yxld.yxchuangxin.contain.Contains;
import com.yxld.yxchuangxin.entity.AppYezhuFangwu;

import java.util.List;

import cn.refactor.library.SmoothCheckBox;

/**
 * 作者：hu on 2017/6/7
 * 邮箱：365941593@qq.com
 * 描述：
 */

public class MenJinShareAdapter extends BaseQuickAdapter<AppYezhuFangwu, BaseViewHolder> {

    public MenJinShareAdapter(List<AppYezhuFangwu> data) {
        super(R.layout.item_share_member, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, AppYezhuFangwu item) {
        helper.setText(R.id.tv_name, item.getFwDanyuan());
        String phone = item.getFwLoudong().substring(0, 3) + "****" + item.getFwLoudong().substring(7, 11);
        helper.setText(R.id.tv_phone, phone);
        String id = "";
//        try {
//            id = item.getFwFanghao().substring(0, 3) + "***********" + item.getFwFanghao().substring(14, item
//                    .getFwFanghao().length());
//        } catch (Exception e) {
//            id = "******************";
//        }
        helper.setText(R.id.tv_Identity, item.getFwFanghao());
        if (item.getFwId() == 2) {
            id = item.getFwFanghao().substring(0, 3) + "***********" + item.getFwFanghao().substring(14, item
                    .getFwFanghao().length());
            helper.setText(R.id.tv_Identity, id);
        }
            if (item.getFwId() == 0) {
                helper.setText(R.id.tv_live_Identity, "产权人");
                helper.setTextColor(R.id.tv_live_Identity, mContext.getResources().getColor(R.color.main_color));
                helper.setImageDrawable(R.id.iv_avater, mContext.getResources().getDrawable(R.mipmap.rzcy_js));
            } else if (item.getFwId() == 1) {
                helper.setText(R.id.tv_live_Identity, "家属");
                helper.setTextColor(R.id.tv_live_Identity, mContext.getResources().getColor(R.color.main_color));
                helper.setImageDrawable(R.id.iv_avater, mContext.getResources().getDrawable(R.mipmap.rzcy_js));
            } else if (item.getFwId() == 2) {
                helper.setText(R.id.tv_live_Identity, "租客");
                helper.setTextColor(R.id.tv_live_Identity, mContext.getResources().getColor(R.color.color_8ecc5c));
                helper.setImageDrawable(R.id.iv_avater, mContext.getResources().getDrawable(R.mipmap.rzcy_zk));
            } else if (item.getFwId() == 3) {
                helper.setText(R.id.tv_live_Identity, "其他");
                helper.setTextColor(R.id.tv_live_Identity, mContext.getResources().getColor(R.color.main_color));
                helper.setImageDrawable(R.id.iv_avater, mContext.getResources().getDrawable(R.mipmap.rzcy_js));
            } else {
                helper.setText(R.id.tv_live_Identity, "历史产权人");
                helper.setTextColor(R.id.tv_live_Identity, mContext.getResources().getColor(R.color.main_color));
                helper.setImageDrawable(R.id.iv_avater, mContext.getResources().getDrawable(R.mipmap.rzcy_js));
            }
        helper.setText(R.id.tv_sex, item.getSex() == 0 ? "男" : "女");
        SmoothCheckBox checkBox = helper.getView(R.id.is_check);
        checkBox.setChecked(item.isMenjinSave());
        checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkBox.setChecked(!checkBox.isChecked(), true);
                item.setMenjinSave(checkBox.isChecked());
            }
        });
    }
}
