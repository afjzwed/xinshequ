package com.yxld.yxchuangxin.ui.adapter.camera;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.yxld.yxchuangxin.R;
import com.yxld.yxchuangxin.entity.HostEntiti;

import java.util.List;

import info.hoang8f.android.segmented.SegmentedGroup;

/**
 * 作者：Android on 2017/9/4
 * 邮箱：365941593@qq.com
 * 描述：
 */

public class AlarmListAdapter extends BaseQuickAdapter<HostEntiti.DataBean, BaseViewHolder> {

    private CheckChangeListener checkChangeListener;

    public AlarmListAdapter(@Nullable List<HostEntiti.DataBean> data) {
        super(R.layout.alarm_list_item, data);
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, HostEntiti.DataBean baseBack) {
        baseViewHolder.setText(R.id.tv_name, baseBack.getZhujiShebeiName());
        baseViewHolder.addOnClickListener(R.id.xufei);
        //设置到期时间
        if (baseBack.getZhujiJiezhiTime() != null && !"".equals(baseBack.getZhujiJiezhiTime())) {
            baseViewHolder.setText(R.id.tv_disconnect2, baseBack.getZhujiJiezhiTime().split(" ")[0]);
            baseViewHolder.setVisible(R.id.tv_disconnect2, true);
        }
        if (baseBack.getZhujiZaixianZhuangtai().equals("1") && baseBack.getZhujiBuchefangZhuangtai() != null) {
            baseViewHolder.setImageDrawable(R.id.iv_avater, mContext.getResources().getDrawable(R.mipmap.bg_02));
            baseViewHolder.addOnClickListener(R.id.tv_chebufang);
            baseViewHolder.addOnClickListener(R.id.lishibaojing);
            baseViewHolder.addOnClickListener(R.id.tv_tongzhi);
            baseViewHolder.addOnClickListener(R.id.button11);
            baseViewHolder.addOnClickListener(R.id.button12);
            baseViewHolder.addOnClickListener(R.id.button13);
            baseViewHolder.addOnClickListener(R.id.iv_avater);
            SegmentedGroup segmented1 = baseViewHolder.getView(R.id.segmented1);
            baseViewHolder.setVisible(R.id.segmented1, true);
            baseViewHolder.setVisible(R.id.tv_disconnect, false);
           // baseViewHolder.setVisible(R.id.tv_disconnect2, false);
            if (baseBack.getZhujiBuchefangZhuangtai().equals("0")) {   //撤防
                segmented1.check(R.id.button11);
            } else if (baseBack.getZhujiBuchefangZhuangtai().equals("1")) {
                segmented1.check(R.id.button12);
            } else {
                segmented1.check(R.id.button13);
            }
            if (baseBack.getZhujiBaojingZhuangtai().equals("1")) {    //1报警 0未报警
                baseViewHolder.setVisible(R.id.iv_baojing, true);
            } else {
                baseViewHolder.setVisible(R.id.iv_baojing, false);
            }
//            if (baseBack.getZhujiMingdiZhuangtai().equals("1")) {         ////鸣笛状态0未鸣笛 1鸣笛
//                segmented2.check(R.id.button21);
//            } else {
//                segmented2.check(R.id.button22);
//            }
        } else {
            baseViewHolder.setImageDrawable(R.id.iv_avater, mContext.getResources().getDrawable(R.mipmap.bj_01));
            baseViewHolder.setVisible(R.id.iv_baojing, false);
            baseViewHolder.setVisible(R.id.segmented1, false);
//            baseViewHolder.setVisible(segmented2, false);
            baseViewHolder.setVisible(R.id.tv_disconnect, true);
            baseViewHolder.setVisible(R.id.tv_disconnect2, true);
        }
        if (baseBack.getZhujiIfYezhu().equals("1")) {
            baseViewHolder.setVisible(R.id.tv_tongzhi, true);
        } else {
            baseViewHolder.setVisible(R.id.tv_tongzhi, false);
        }
    }

    public void setCheckChangeListener(CheckChangeListener checkChangeListener) {
        this.checkChangeListener = checkChangeListener;
    }

}
