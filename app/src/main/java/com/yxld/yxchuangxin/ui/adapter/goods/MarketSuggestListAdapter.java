package com.yxld.yxchuangxin.ui.adapter.goods;

import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.yxld.yxchuangxin.R;
import com.yxld.yxchuangxin.contain.Contains;
import com.yxld.yxchuangxin.entity.goods.MallOrderSuggest;

import java.util.List;

/**
 * @author xlei
 * @Date 2017/11/2.
 */

public class MarketSuggestListAdapter extends BaseQuickAdapter<MallOrderSuggest.RowsBean, BaseViewHolder> {

    private TextView view;

    public MarketSuggestListAdapter(@Nullable List<MallOrderSuggest.RowsBean> data) {
        super(R.layout.item_market_suggest, data);
    }

    @Override
    protected void convert(BaseViewHolder viewHolder, MallOrderSuggest.RowsBean dataBean) {
        viewHolder.setText(R.id.tv_comment_phone, Contains.user.getYezhuShouji() + "");
        viewHolder.setText(R.id.tv_comment_content, dataBean.getTsjyNeirong());
        //2017/11/8 这里将状态显示改为时间显示，原有时间显示隐藏
        //        viewHolder.setText(R.id.tv_comment_time, dataBean.getTsjyTijiaoShijian());
        //        viewHolder.setText(R.id.tv_zhuangtai, dataBean.getTsjyZhuangtai() == 1 ? "处理中" : dataBean.getTsjyZhuangtai() == 2 ? "处理完成" : "未处理");
        viewHolder.setText(R.id.tv_zhuangtai, dataBean.getTsjyTijiaoShijian());
        /*if (dataBean.getTsjyChuliJieguo() != null && !"".equals(dataBean.getTsjyChuliJieguo())) {
            viewHolder.setVisible(R.id.tv_jieguo, true);
            viewHolder.setText(R.id.tv_jieguo, "商家回复："+dataBean.getTsjyChuliJieguo());
        }*/
        view = viewHolder.getView(R.id.tv_jieguo);
        if (dataBean.getTsjyChuliJieguo() != null && !"".equals(dataBean.getTsjyChuliJieguo())) {
            view.setVisibility(View.VISIBLE);
            viewHolder.setText(R.id.tv_jieguo, "商家回复：" + dataBean.getTsjyChuliJieguo());
        } else {
            view.setVisibility(View.GONE);
        }
        //  viewHolder.setText(R.id.tv_huifu_content,dataBean.get)
    }
}
