package com.yxld.yxchuangxin.ui.adapter.goods;

import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.yxld.yxchuangxin.R;
import com.yxld.yxchuangxin.Utils.StringUitl;
import com.yxld.yxchuangxin.entity.MyAllComment;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: Yuan.Y.Q
 * @date: 2017/6/30
 * @descprition:
 */

public class GoodsCommentAdapter extends BaseQuickAdapter<MyAllComment.DataBean, BaseViewHolder> {
    private boolean mShowTime;

    public GoodsCommentAdapter(@Nullable List<MyAllComment.DataBean> data, boolean showTime) {
        super(R.layout.item_goods_comment, data);
        this.mShowTime = showTime;
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, MyAllComment.DataBean comment) {
//        baseViewHolder.setText(R.id.tv_comment_phone,
//                StringUitl.hasEmptyItem(comment.getHuifurenMing())?"匿名用户":formatPhone(comment.getPingjiaName()))
//                .setText(R.id.tv_comment_content,comment.getPingjiaNeirong());
        if (!StringUitl.isEmpty(comment.getYezhuShouji())) {
            baseViewHolder.setText(R.id.tv_comment_phone, comment.getYezhuShouji().substring(0, 3) + "****" + comment.getYezhuShouji().substring(7, comment.getYezhuShouji().length()));
        }
        if (!StringUitl.isEmpty(comment.getPingjiaNeirong())) {
            baseViewHolder.setText(R.id.tv_comment_content, comment.getPingjiaNeirong());
        }
        TextView tvTime = baseViewHolder.getView(R.id.tv_comment_time);
        if (mShowTime) {
            tvTime.setText(comment.getPingjiaShijian());
        } else {
            tvTime.setVisibility(View.GONE);
        }
        if (baseViewHolder.getLayoutPosition() == mData.size() - 1) {
            baseViewHolder.setVisible(R.id.line, false);
        }
        TextView tvHuifu = baseViewHolder.getView(R.id.tv_huifu);
        if (comment.getHuifu() != null && !"".equals(comment.getHuifu())) {
            tvHuifu.setVisibility(View.VISIBLE);
            tvHuifu.setText("商家回复：" + comment.getHuifu());
        } else {
            tvHuifu.setVisibility(View.GONE);
        }
        RecyclerView recyclerView = baseViewHolder.getView(R.id.recycerView);
        LinearLayoutManager manager = new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(manager);
        CommentStarAdapter viewHolder = new CommentStarAdapter(getStarByResult(comment.getPingjiaDengji()));
        recyclerView.setAdapter(viewHolder);
    }

    private List<Integer> getStarByResult(Integer pingjiaLevel) {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            if (i < pingjiaLevel) {
                list.add(1);
            } else {
                list.add(0);
            }
        }
        return list;
    }

    private String formatPhone(String phone) {
        if (phone.length() < 8) {
            return phone;
        }
        if (phone.matches("\\d+")) {
            return phone.replaceAll("(\\d{3})\\d{4}(\\d+)", "$1****$2");
        }

        return phone;
    }

    public void onDestroy() {
        mContext = null;
    }

    private static class CommentStarAdapter extends BaseQuickAdapter<Integer, BaseViewHolder> {


        public CommentStarAdapter(@Nullable List<Integer> data) {
            super(R.layout.item_goods_comment_star, data);
        }

        @Override
        protected void convert(BaseViewHolder baseViewHolder, Integer integer) {
            if (integer == null) {
                return;
            }
            ImageView iv = baseViewHolder.getView(R.id.iv_star);
            Glide.with(mContext)
                    .load(integer == 1 ? R.mipmap.spxq_pf01 : R.mipmap.spxq_pf02)
                    .into(iv);
        }


    }
}
