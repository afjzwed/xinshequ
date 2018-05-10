package com.yxld.yxchuangxin.ui.adapter.rim;

import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.yxld.yxchuangxin.R;
import com.yxld.yxchuangxin.entity.RimCommentListBean;

/**
 * Created by William on 2017/12/18.
 */

public class RimShopCommentListAdapter extends BaseQuickAdapter<RimCommentListBean.DataBean.PingjiaListBean, BaseViewHolder> {

    private ImageView commentHead;
    private TextView commentName;
    private TextView commentTime;
    private TextView commentContent;
    private TextView commentReply;
    private RatingBar ratingBar;

    public RimShopCommentListAdapter() {
        super(R.layout.item_rim_shopcomment_list);
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, RimCommentListBean.DataBean.PingjiaListBean data) {
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
      /*  Glide.with(mContext)
                .load(API.PIC+data.)
                .placeholder(R.drawable.default_recommed_icon)
                .error(R.drawable.default_recommed_icon)
                .crossFade()//渐入渐出
                .centerCrop()
                .skipMemoryCache(true)
                .diskCacheStrategy(DiskCacheStrategy.RESULT)
                .bitmapTransform(new CropCircleTransformation(MyApplication.context))
                .into(viewHolder.avatar);*/
    }
}
