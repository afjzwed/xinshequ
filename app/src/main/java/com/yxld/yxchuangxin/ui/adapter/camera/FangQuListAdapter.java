package com.yxld.yxchuangxin.ui.adapter.camera;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.yxld.yxchuangxin.R;
import com.yxld.yxchuangxin.Utils.FangquUtil;
import com.yxld.yxchuangxin.entity.FangquEntity;

import java.util.List;

/**
 * 作者：Android on 2017/9/7
 * 邮箱：365941593@qq.com
 * 描述：
 */

public class FangQuListAdapter extends BaseQuickAdapter<FangquEntity.DataBean, BaseViewHolder> {

    public FangQuListAdapter(@Nullable List<FangquEntity.DataBean> data) {
        super(R.layout.fangqu_lsit_item, data);
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, FangquEntity.DataBean dataBean) {
        if (dataBean.getIsAlarm().equals("1")) {               //1为报警 0为未报警
            baseViewHolder.setVisible(R.id.iv_alarm, true);
            baseViewHolder.setVisible(R.id.baojingshijian, true);
//            baseViewHolder.setVisible(R.id.cacanl_bufang, false);
//            baseViewHolder.setBackgroundColor(R.id.ll_fangqu, mContext.getResources().getColor(R.color.color_baojing));
            baseViewHolder.setText(R.id.tv_baojing_shijian, dataBean.getBaojingShijian());

        } else {
            baseViewHolder.setVisible(R.id.iv_alarm, false);
            baseViewHolder.setVisible(R.id.baojingshijian, false);
            baseViewHolder.setBackgroundColor(R.id.ll_fangqu, mContext.getResources().getColor(R.color.white));
//            baseViewHolder.setVisible(R.id.cacanl_bufang, false);`
        }
        if (dataBean.getShebeiMingliKaiguan().equals("1")) {
            baseViewHolder.setImageResource(R.id.iv_avater, R.mipmap.md_on);
        } else {
            baseViewHolder.setImageResource(R.id.iv_avater, R.mipmap.md_off);
        }
        baseViewHolder.addOnClickListener(R.id.modify);
        baseViewHolder.setText(R.id.tv_baojing_fangqu_bianhao, dataBean.getShebeiFangquBianhao());
        baseViewHolder.setText(R.id.tv_shebei_ming, dataBean.getShebeiName())
                .setText(R.id.tv_fangqu_leixing, FangquUtil.parseFangqu(dataBean.getShebeiFangquLeixin()));
    }
}
