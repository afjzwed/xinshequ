package com.yxld.yxchuangxin.ui.adapter.main;

import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.yxld.yxchuangxin.R;
import com.yxld.yxchuangxin.entity.ActivityOrder;
import com.yxld.yxchuangxin.ui.activity.main.SecondActivity;

import java.util.List;

/**
 * Created by hu on 2017/5/17.
 */

public class ActivityAdapter extends BaseQuickAdapter<ActivityOrder.DataBean.ListBean, BaseViewHolder> {

    SecondActivity mActivity;

    public ActivityAdapter(SecondActivity mActivity, List<ActivityOrder.DataBean.ListBean> data) {
        super(R.layout.lv_activity_order_chuxing, data);
        this.mActivity = mActivity;
    }

    @Override
    protected void convert(BaseViewHolder helper, ActivityOrder.DataBean.ListBean item) {
        ImageView img = helper.getView(R.id.iv_main);
        Glide.with(mActivity)
                .load(item.getVideoShotUrl())
                .into(img);
        helper.setText(R.id.tv_activity_chufashijian, item.getBespeakTime());
    }

}
