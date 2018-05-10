package com.yxld.yxchuangxin.ui.adapter.rim;

import android.app.Activity;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.yxld.yxchuangxin.Utils.DensityUtil;
import com.yxld.yxchuangxin.data.api.API;
import com.yxld.yxchuangxin.view.RoundCornersTransformation;

/**
 * Created by William on 2017/12/12.
 */

public class RimGoodDetailAdapter extends PagerAdapter {

    private String[] list;
    private Activity mActivity;

    public RimGoodDetailAdapter(Activity activity, String[] list) {
        this.list = list;
        this.mActivity = activity;
    }

    @Override
    public int getCount() {
        return list.length;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    private ImageView imageView;

    @Override
    public Object instantiateItem(ViewGroup container, int position) {

        imageView = new ImageView(mActivity);
        imageView.setScaleType(ImageView.ScaleType.FIT_XY);

        int screenWidth = DensityUtil.getScreenWidth(mActivity);//屏幕宽度
        int size = 24 * screenWidth / 1080;
        RoundCornersTransformation transformation =
                new RoundCornersTransformation(mActivity,size, RoundCornersTransformation
                        .CornerType.TOP);
        /*if (position % 2 == 0) {
            Glide.with(mActivity)
                    .load(R.mipmap.gyem_bj)
                    .bitmapTransform(transformation)
                    .placeholder(R.mipmap.gyem_bj)
                    .error(R.mipmap.gyem_bj)
                    .skipMemoryCache(true)
                    .diskCacheStrategy(DiskCacheStrategy.RESULT)
                    .into(imageView);
        } else {
            Glide.with(mActivity)
                    .load(R.mipmap.gyem_logo)
                    .bitmapTransform(transformation)
                    .placeholder(R.mipmap.gyem_bj)
                    .error(R.mipmap.gyem_bj)
                    .skipMemoryCache(true)
                    .diskCacheStrategy(DiskCacheStrategy.RESULT)
                    .into(imageView);
        }*/
        Glide.with(mActivity)
                .load(API.PIC+list[position])
//                .bitmapTransform(transformation)
                .skipMemoryCache(true)
                .diskCacheStrategy(DiskCacheStrategy.RESULT)
                .into(imageView);

        container.addView(imageView);
        return imageView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }
}
