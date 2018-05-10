package com.yxld.yxchuangxin.ui.adapter.wuye;

import android.support.annotation.Nullable;
import android.text.TextUtils;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.yxld.yxchuangxin.R;
import com.yxld.yxchuangxin.entity.CxwyMessage;

import java.util.List;

/**
 * 作者：hu on 2017/6/14
 * 邮箱：365941593@qq.com
 * 描述：
 */

public class ActivityAdapter extends BaseQuickAdapter<CxwyMessage.RowsBean, BaseViewHolder> {

    public ActivityAdapter(@Nullable List<CxwyMessage.RowsBean> data) {
        super(R.layout.item_message, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, CxwyMessage.RowsBean item) {
        String tongzhiNeirong = "";
        StringBuilder stringBuilder ;
        if (!TextUtils.isEmpty(item.getTongzhiNeirong())) {
            tongzhiNeirong = item.getTongzhiNeirong();
            stringBuilder = new StringBuilder(tongzhiNeirong);
            for (int i = 0; i < tongzhiNeirong.length(); i++) {
                if (stringBuilder.indexOf(">") == stringBuilder.length() - 1) {
                    int i1 = stringBuilder.indexOf("<");
                    stringBuilder.delete(i1, stringBuilder.length());
                    break;
                } else {
                    int i1 = stringBuilder.indexOf("<");
                    int i2 = stringBuilder.indexOf(">");
                    stringBuilder.delete(i1, i2 + 1);
                }
            }
            tongzhiNeirong = stringBuilder.toString().replace("&nbsp", " ");
        }
        helper.setText(R.id.tv_title, item.getTongzhiBiaoti())
                .setText(R.id.tv_time, item.getTongzhiFabushijian())
                .setText(R.id.tv_content, tongzhiNeirong);
        helper.setImageDrawable(R.id.iv_kind, mContext.getResources().getDrawable(R.mipmap
                .tzhd_hd));
    }
}
