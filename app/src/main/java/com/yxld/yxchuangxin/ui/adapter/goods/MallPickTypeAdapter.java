package com.yxld.yxchuangxin.ui.adapter.goods;

import android.graphics.Color;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.yxld.yxchuangxin.R;
import com.zhy.autolayout.AutoRelativeLayout;

import java.util.List;

/**
 * @author: Yuan.Y.Q
 * @date: 2017/6/16
 * @descprition:
 */

public class MallPickTypeAdapter extends BaseQuickAdapter<MallPickTypeAdapter.MallPickTypeBean, BaseViewHolder> {

    public MallPickTypeAdapter(@Nullable List<MallPickTypeAdapter.MallPickTypeBean> data) {
        super(R.layout.item_mall_picked, data);
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, MallPickTypeAdapter.MallPickTypeBean mallPickTypeBean) {
        baseViewHolder.setText(R.id.tv_mall_pick_big_title, mallPickTypeBean.bigTitle)
                .setTextColor(R.id.tv_mall_pick_big_title, Color.parseColor(mallPickTypeBean.bigTitleColor))
                .setText(R.id.tv_mall_pick_small_title, mallPickTypeBean.smallTitle)
                .setTextColor(R.id.tv_mall_pick_small_title, Color.parseColor(mallPickTypeBean.smallTitleColor));
        if (TextUtils.isEmpty(mallPickTypeBean.smallTitle)) {
            baseViewHolder.setVisible(R.id.tv_mall_pick_small_title, false);
        }
        AutoRelativeLayout root = baseViewHolder.getView(R.id.ll_mall_pick_root);
        root.setBackgroundColor(Color.parseColor(mallPickTypeBean.bgColor));
        ImageView iv = baseViewHolder.getView(R.id.iv_mall_pick);
        Glide.with(mContext)
                .load(mallPickTypeBean.imgResId)
                .into(iv);

        switch (baseViewHolder.getLayoutPosition()) {
            case 0:
                baseViewHolder.setText(R.id.tv_mall_pick_small_title, "满199减20");
                break;
            case 1:
                baseViewHolder.setText(R.id.tv_mall_pick_small_title, "满100减10");
                break;
            case 2:
                baseViewHolder.setText(R.id.tv_mall_pick_small_title, "满199减10");
                break;
            case 3:
                baseViewHolder.setText(R.id.tv_mall_pick_small_title, "满299减10");
                break;
        }
    }

    public static class MallPickTypeBean {
        public String bigTitle;
        public String smallTitle;
        public String bgColor;
        public String bigTitleColor;
        public String smallTitleColor;
        public String imgUrl;
        public int imgResId;

        @Override
        public String toString() {
            return "MallPickTypeBean{" +
                    "bigTitle='" + bigTitle + '\'' +
                    ", smallTitle='" + smallTitle + '\'' +
                    ", bgColor=" + bgColor +
                    ", bigTitleColor=" + bigTitleColor +
                    ", smallTitleColor=" + smallTitleColor +
                    ", imgUrl='" + imgUrl + '\'' +
                    ", imgResId=" + imgResId +
                    '}';
        }
    }
}
