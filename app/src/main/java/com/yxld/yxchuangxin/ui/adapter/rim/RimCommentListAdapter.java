package com.yxld.yxchuangxin.ui.adapter.rim;

import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.yxld.yxchuangxin.R;
import com.yxld.yxchuangxin.data.api.API;
import com.yxld.yxchuangxin.entity.RimOrderCommentBean;

import java.util.List;


/**
 * @author wwx
 * @Package com.yxld.yxchuangxin.ui.adapter.rim.RimCommentListAdapter
 * @Description: 欣周边评价列表适配器
 * @date 2017/06/17
 */
public class RimCommentListAdapter extends BaseQuickAdapter<RimOrderCommentBean.DataBean,
        BaseViewHolder> {
    private ImageView commentHead;
    private TextView commentName;
    private TextView commentTime;
    private TextView commentContent;
    private TextView commentReply;
    private RatingBar ratingBar;
    public RimCommentListAdapter(List<RimOrderCommentBean.DataBean> data) {
        super(R.layout.rim_comment_item, data);
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, RimOrderCommentBean.DataBean data) {

        commentHead = baseViewHolder.getView(R.id.iv_comment_head);
        commentName = baseViewHolder.getView(R.id.tv_comment_name);
        commentTime = baseViewHolder.getView(R.id.tv_comment_time);
        commentContent = baseViewHolder.getView(R.id.tv_comment_content);
        commentReply = baseViewHolder.getView(R.id.tv_comment_reply);
        ratingBar = baseViewHolder.getView(R.id.rb_commnet_praise);

        commentName.setText(data.getOrderUserName());
        commentTime.setText(data.getOrderEvaluateEvaTime());
        commentContent.setText(data.getOrderEvaluateEvaContent());
        ratingBar.setRating(data.getOrderEvaluateEvaLevel());
        if (TextUtils.isEmpty(data.getOrderEvaluateReplyContent())) {
            commentReply.setVisibility(View.GONE);
        } else {
            commentReply.setVisibility(View.VISIBLE);
            commentReply.setText("商家回复："+ data.getOrderEvaluateReplyContent());
        }
    }
}
