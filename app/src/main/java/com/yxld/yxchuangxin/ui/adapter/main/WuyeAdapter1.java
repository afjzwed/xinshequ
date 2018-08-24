package com.yxld.yxchuangxin.ui.adapter.main;

import android.view.View;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.yxld.yxchuangxin.R;
import com.yxld.yxchuangxin.entity.adapter.Wuye;

import java.util.List;

/**
 * 作者：hu on 2017/6/5
 * 邮箱：365941593@qq.com
 * 描述：
 */

public class WuyeAdapter1 extends BaseQuickAdapter<Wuye.DataBean.ListBean, BaseViewHolder> {

    public WuyeAdapter1(List<Wuye.DataBean.ListBean> data) {
        super(R.layout.item_gridview, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, Wuye.DataBean.ListBean item) {
        helper.setText(R.id.textView2, item.getName());
        ImageView imageView = helper.getView(R.id.imageView);
//        TextView textView = helper.getView(R.id.tv_shiyong);
//        if ("居家安防".equals(item.getName())) {
//            textView.setVisibility(View.VISIBLE);
//            setAnimation(textView);
//        } else {
//            textView.clearAnimation();
//            textView.setVisibility(View.GONE);
//        }
        Glide.with(mContext)
                .load(mContext.getResources().getIdentifier(item.getIcon(), "mipmap", mContext.getPackageName()))
                .into(imageView);
    }
    private void setAnimation(TextView textView) {
        /** 设置缩放动画 */
        final ScaleAnimation animation = new ScaleAnimation(0.5f, 1.0f, 0.5f, 1.0f,
                Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        animation.setDuration(1000);//设置动画持续时间
        animation.setRepeatCount(-1);//设置重复次数
        animation.setRepeatMode(Animation.REVERSE);//重复 缩小和放大效果
        textView.startAnimation(animation);
    }
}
