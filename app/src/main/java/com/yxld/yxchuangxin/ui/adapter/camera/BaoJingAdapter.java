package com.yxld.yxchuangxin.ui.adapter.camera;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.yxld.yxchuangxin.R;
import com.yxld.yxchuangxin.entity.BaoJingEntity;

import java.util.List;

/**
 * 作者：Android on 2017/9/7
 * 邮箱：365941593@qq.com
 * 描述：
 */

public class BaoJingAdapter extends BaseQuickAdapter<BaoJingEntity.DataBean.RowsBean, BaseViewHolder> {

    public BaoJingAdapter(@Nullable List<BaoJingEntity.DataBean.RowsBean> data) {
        super(R.layout.baojing_list_item, data);
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, BaoJingEntity.DataBean.RowsBean dataBean) {
        baseViewHolder.setText(R.id.tv_baojing_fangqu, dataBean.getBaojingFangquMingzi());
        baseViewHolder.setText(R.id.tv_baojing_shijian, dataBean.getBaojingShijian());
        baseViewHolder.setText(R.id.tv_baojing_bianhao, dataBean.getBaojingFangquBianhao());
        baseViewHolder.setText(R.id.tv_baojing_fangqu_leixing, dataBean.getBaojingFangquLeixin());
        baseViewHolder.setText(R.id.tv_chefangren, dataBean.getBaojingChuliAdmin());
        if (dataBean.getBaojingChuliShijian() == null) {
            baseViewHolder.setText(R.id.tv_chefang_shijian,"无");
        } else {
            baseViewHolder.setText(R.id.tv_chefang_shijian, dataBean.getBaojingChuliShijian());
        }
    }
}
