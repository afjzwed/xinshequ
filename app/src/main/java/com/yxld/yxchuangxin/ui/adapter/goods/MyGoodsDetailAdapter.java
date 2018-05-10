package com.yxld.yxchuangxin.ui.adapter.goods;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.socks.library.KLog;
import com.yxld.yxchuangxin.R;
import com.yxld.yxchuangxin.Utils.StringUitl;
import com.yxld.yxchuangxin.data.api.API;
import com.yxld.yxchuangxin.view.AutoCardView;

import java.util.List;

/**
 * @author: Yuan.Y.Q
 * @date: 2017/7/5
 * @descprition:
 */

public class MyGoodsDetailAdapter extends PagerAdapter {
    private List<String> mUrls;
    private Context mContext;

    public MyGoodsDetailAdapter(List<String> mUrls, Context mContext) {
        this.mUrls = mUrls;
        this.mContext = mContext;
    }

    @Override
    public int getCount() {
        return mUrls.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return (view == object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_goods_detail_img, null);
        ImageView imageView = (ImageView) view.findViewById(R.id.iv_good);
        String url = API.PIC+ StringUitl.replaceEndFenHao(mUrls.get(position));
        Glide.with(mContext)
                .load(url)
                .into(imageView);

        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
       container.removeView((View) object);
    }

}
