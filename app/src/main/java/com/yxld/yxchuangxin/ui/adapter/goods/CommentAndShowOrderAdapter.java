package com.yxld.yxchuangxin.ui.adapter.goods;

import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.yxld.yxchuangxin.R;
import com.yxld.yxchuangxin.Utils.StringUitl;
import com.yxld.yxchuangxin.data.api.API;
import com.yxld.yxchuangxin.entity.CxwyMallSale;
import com.yxld.yxchuangxin.entity.goods.MallNewOrderDetails;
import com.yxld.yxchuangxin.view.MyEditTextView;

import java.util.List;

/**
 * @author: Yuan.Y.Q
 * @date: 2017/6/29
 * @descprition:
 */

public class CommentAndShowOrderAdapter extends BaseQuickAdapter<MallNewOrderDetails,BaseViewHolder>{

    public CommentAndShowOrderAdapter(@Nullable List<MallNewOrderDetails> data) {
        super(R.layout.item_add_comment,data);
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, MallNewOrderDetails cxwyMallSale) {
        baseViewHolder.setText(R.id.tv_title,cxwyMallSale.getShangpinMing());
        final TextView tvCommentNum =  baseViewHolder.getView(R.id.tv_comment_num);
        MyEditTextView editText = baseViewHolder.getView(R.id.edit_add_comment);
        StringUitl.setEditTextInhibitInputSpeOnlyChnese500(editText);

        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                tvCommentNum.setText(s.toString().length()+"");
                cxwyMallSale.setComment(s.toString());
            }
        });
        editText.setText("好评");
        ImageView view = baseViewHolder.getView(R.id.iv_comment_product);
        Glide.with(mContext)
                .load(API.PIC+ StringUitl.replaceEndFenHao(cxwyMallSale.getSuoluetu()))
                .into(view);

        RatingBar ratingBar = baseViewHolder.getView(R.id.ratingBar);
        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                cxwyMallSale.setRatingNum(rating);
            }
        });
    }
}
