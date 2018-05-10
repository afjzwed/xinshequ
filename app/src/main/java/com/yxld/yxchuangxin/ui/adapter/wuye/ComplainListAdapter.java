package com.yxld.yxchuangxin.ui.adapter.wuye;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.yxld.yxchuangxin.R;
import com.yxld.yxchuangxin.entity.CxwyComplain;

import java.util.List;

/**
 * 作者：hu on 2017/6/26
 * 邮箱：365941593@qq.com
 * 描述：
 */

public class ComplainListAdapter extends BaseQuickAdapter<CxwyComplain.ListBean, BaseViewHolder> {
    public ComplainListAdapter(@Nullable List<CxwyComplain.ListBean> data) {
        super(R.layout.item_complain, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, CxwyComplain.ListBean item) {
        helper.setText(R.id.tv_content, item.getTousuNeirong());
        helper.setText(R.id.tv_time, item.getTousuTime());
        helper.setText(R.id.tv_status, item.getTousuStatus());
        if (item.getTousuType().equals("1")) {
            helper.setText(R.id.tv_leixing, "投诉内容");
            helper.setImageDrawable(R.id.iv_leixing, mContext.getResources().getDrawable(R.mipmap.tslb_ts));
            helper.setVisible(R.id.tv_complain_leixing, true);
            helper.setText(R.id.tv_complain_leixing, item.getTousuTest2());
            if (item.getTousuFankuiyijian().equals("")) {
                helper.setVisible(R.id.reply, false);
            } else {
                helper.setVisible(R.id.reply, true);
                helper.setText(R.id.tv_reply_content, item.getTousuFankuiyijian());
                try {
                    helper.setText(R.id.tv_reply_time, item.getTousuXmsolutiontime().substring(0, 19));
                } catch (Exception e) {

                }
            }
        } else {
            helper.setText(R.id.tv_leixing, "建议内容");
            helper.setImageDrawable(R.id.iv_leixing, mContext.getResources().getDrawable(R.mipmap.tslb_jy));
            helper.setVisible(R.id.tv_complain_leixing, false);
            if (item.getTousuFankuiyijian().equals("")) {
                helper.setVisible(R.id.reply, false);
            } else {
                helper.setVisible(R.id.reply, true);
                helper.setText(R.id.tv_reply_content, item.getTousuFankuiyijian());}
        }
    }
}
