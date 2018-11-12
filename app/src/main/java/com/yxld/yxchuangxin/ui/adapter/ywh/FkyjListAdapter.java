package com.yxld.yxchuangxin.ui.adapter.ywh;

import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.yxld.yxchuangxin.R;
import com.yxld.yxchuangxin.base.BaseEntity;
import com.yxld.yxchuangxin.entity.YwhFkyj;

import java.util.List;

/**
 * Created by Administrator on 2018/11/9.
 */

public class FkyjListAdapter extends BaseQuickAdapter<YwhFkyj.DataBean, BaseViewHolder> {

    private TextView tv_reply;
    private TextView tv_reply_content;

    public FkyjListAdapter(List<YwhFkyj.DataBean> data) {
        super(R.layout.item_fkyj_list, data);
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, YwhFkyj.DataBean dataBean) {
        tv_reply = baseViewHolder.getView(R.id.tv_reply);
        tv_reply_content = baseViewHolder.getView(R.id.tv_reply_content);


        baseViewHolder.setText(R.id.tv_time, dataBean.getSubtime()).setText(R.id.tv_content,dataBean.getResultdesc());
        if (TextUtils.isEmpty(dataBean.getResultcontent())) {
            tv_reply.setText("未回复");
            tv_reply.setTextColor(0xff2d97ff);
            tv_reply_content.setVisibility(View.GONE);
        } else {
            tv_reply.setText("已回复");
            tv_reply.setTextColor(0xff00b404);
            tv_reply_content.setVisibility(View.VISIBLE);
            tv_reply_content.setText(dataBean.getResultcontent());
        }
    }
}
