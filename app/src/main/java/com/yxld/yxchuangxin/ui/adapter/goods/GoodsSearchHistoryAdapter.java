package com.yxld.yxchuangxin.ui.adapter.goods;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.yxld.yxchuangxin.R;
import com.yxld.yxchuangxin.entity.SearchHistoryEntityWh;

import java.util.List;

/**
 * @author: Yuan.Y.Q
 * @date: 2017/6/24
 * @descprition:
 */

public class GoodsSearchHistoryAdapter extends BaseQuickAdapter<SearchHistoryEntityWh,BaseViewHolder> {
    public GoodsSearchHistoryAdapter(@Nullable List<SearchHistoryEntityWh> data) {
        super(R.layout.item_search_history,data);
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, SearchHistoryEntityWh s) {
        baseViewHolder.setText(R.id.tv_search_history,s.getU_search());
        if(baseViewHolder.getLayoutPosition() == mData.size()-1){
            baseViewHolder.setVisible(R.id.view_search_history_line,false);
        }
    }
}
