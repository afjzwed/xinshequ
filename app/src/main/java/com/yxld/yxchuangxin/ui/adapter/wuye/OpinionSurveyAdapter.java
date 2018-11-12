package com.yxld.yxchuangxin.ui.adapter.wuye;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.yxld.yxchuangxin.R;
import com.yxld.yxchuangxin.entity.OpinionSurveyEntity;

/**
 * Created by William on 2018/11/12.
 */

public class OpinionSurveyAdapter extends BaseQuickAdapter<OpinionSurveyEntity.DataBean, BaseViewHolder> {

    public OpinionSurveyAdapter() {
        super(R.layout.item_opinionsurvey_list);
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, OpinionSurveyEntity.DataBean dataBean) {
        baseViewHolder.setText(R.id.tv_title, dataBean.getSubjectTitle()).setText(R.id.tv_time, dataBean.getStartTime
                () + "至" + dataBean.getEndTime());
    }
}
